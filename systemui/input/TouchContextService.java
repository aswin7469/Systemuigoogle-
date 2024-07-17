package com.google.android.systemui.input;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManagerGlobal;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.IServiceCallback;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.Trace;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.util.Log;
import android.view.DisplayInfo;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.android.internal.os.BackgroundThread;
import com.google.input.ContextPacket;
import com.google.input.IConfigPropertyNamespaceListener;
import com.google.input.ITouchContextService;
import com.google.input.ITouchContextService$Stub$Proxy;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TouchContextService {
    public static final String INTERFACE_NAME = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), ITouchContextService.DESCRIPTOR, "/default");
    public final ArrayList mActivePropertyNamespaces;
    public final AnonymousClass5 mAdaptiveTouchSensitivityObserver;
    public final AudioManager mAudioManager;
    public final AnonymousClass4 mAudioModeListener = new AudioManager.OnModeChangedListener() {
        public final void onModeChanged(int i) {
            TouchContextService touchContextService = TouchContextService.this;
            if (i != touchContextService.mPreviousAudioMode) {
                touchContextService.mPreviousAudioMode = i;
                touchContextService.updateTouchContext();
            }
        }
    };
    public final ContentResolver mContentResolver;
    public final AnonymousClass3 mDisplayListener = new DisplayManager.DisplayListener() {
        public final void onDisplayChanged(int i) {
            Trace.beginSection("TouchContextService.mDisplayListener#onDisplayChanged");
            if (i != 0) {
                Trace.endSection();
                return;
            }
            try {
                DisplayInfo displayInfo = DisplayManagerGlobal.getInstance().getDisplayInfo(i);
                if (displayInfo == null) {
                    Log.e("TouchContextService.java", "could not get DisplayInfo for display " + i + ".");
                    return;
                }
                int i2 = displayInfo.rotation;
                TouchContextService touchContextService = TouchContextService.this;
                if (i2 == touchContextService.mPreviousRotation) {
                    Trace.endSection();
                    return;
                }
                touchContextService.mPreviousRotation = i2;
                touchContextService.updateTouchContext();
                Trace.endSection();
            } finally {
                Trace.endSection();
            }
        }

        public final void onDisplayAdded(int i) {
        }

        public final void onDisplayRemoved(int i) {
        }
    };
    public final DisplayManager mDisplayManager;
    public int mPreviousAudioMode;
    public int mPreviousRotation;
    public final ArrayList mPropertiesChangedListeners;
    public final Object mPropertiesLock;
    public final AnonymousClass2 mPropertyNamespaceListener = new IConfigPropertyNamespaceListener() {
        {
            markVintfStability();
            attachInterface(this, IConfigPropertyNamespaceListener.DESCRIPTOR);
        }

        public final int getMaxTransactionId() {
            return 16777214;
        }

        public final String getTransactionName(int i) {
            if (i == 1) {
                return "onResult";
            }
            switch (i) {
                case 16777214:
                    return "getInterfaceHash";
                case 16777215:
                    return "getInterfaceVersion";
                default:
                    return null;
            }
        }

        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            String str = IConfigPropertyNamespaceListener.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            } else if (i == 16777215) {
                parcel2.writeNoException();
                parcel2.writeInt(2);
                return true;
            } else if (i == 16777214) {
                parcel2.writeNoException();
                parcel2.writeString("aca4c2d71594b00b5aa82cf5554538a829bca02a");
                return true;
            } else if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                String[] createStringArray = parcel.createStringArray();
                parcel.enforceNoDataAvail();
                Log.i("TouchContextService.java", "IConfigPropertyNamespaceListener received namespaces: \"" + String.join("\", \"", createStringArray) + "\"");
                synchronized (TouchContextService.this.mPropertiesLock) {
                    try {
                        TouchContextService.this.mActivePropertyNamespaces.clear();
                        for (int i3 = 0; i3 < TouchContextService.this.mPropertiesChangedListeners.size(); i3++) {
                            DeviceConfig.removeOnPropertiesChangedListener((DeviceConfig.OnPropertiesChangedListener) TouchContextService.this.mPropertiesChangedListeners.get(i3));
                        }
                        TouchContextService.this.mPropertiesChangedListeners.clear();
                        for (int i4 = 0; i4 < createStringArray.length; i4++) {
                            TouchContextService.this.mActivePropertyNamespaces.add(createStringArray[i4]);
                            TouchContextService touchContextService = TouchContextService.this;
                            touchContextService.mPropertiesChangedListeners.add(new PropertiesChangedListener());
                            DeviceConfig.addOnPropertiesChangedListener(createStringArray[i4], BackgroundThread.getExecutor(), (DeviceConfig.OnPropertiesChangedListener) TouchContextService.this.mPropertiesChangedListeners.get(i4));
                            TouchContextService.m1750$$Nest$mconfigPropertiesChanged(TouchContextService.this, DeviceConfig.getProperties(createStringArray[i4], new String[0]));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return true;
            }
        }

        public final IBinder asBinder() {
            return this;
        }
    };
    public final AnonymousClass5 mScreenProtectorModeObserver;
    public final AnonymousClass1 mServiceCallback;
    public final Object mServiceLock;
    public ITouchContextService mTouchContextService;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class PropertiesChangedListener implements DeviceConfig.OnPropertiesChangedListener {
        public PropertiesChangedListener() {
        }

        public final void onPropertiesChanged(DeviceConfig.Properties properties) {
            TouchContextService.m1750$$Nest$mconfigPropertiesChanged(TouchContextService.this, properties);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Object, com.google.input.PropertyPacket] */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: -$$Nest$mconfigPropertiesChanged  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1750$$Nest$mconfigPropertiesChanged(com.google.android.systemui.input.TouchContextService r6, android.provider.DeviceConfig.Properties r7) {
        /*
            r6.getClass()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Set r1 = r7.getKeyset()
            java.util.Iterator r1 = r1.iterator()
        L_0x0010:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0063
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            com.google.input.PropertyPacket r3 = new com.google.input.PropertyPacket
            r3.<init>()
            java.lang.String r4 = r7.getNamespace()
            r3.namespaceName = r4
            r3.propertyName = r2
            java.lang.String r4 = ""
            java.lang.String r2 = r7.getString(r2, r4)
            r3.propertyValue = r2
            r0.add(r3)
            java.lang.String r2 = "TouchContextService.java"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Property changed: namespace = \""
            r4.<init>(r5)
            java.lang.String r5 = r3.namespaceName
            r4.append(r5)
            java.lang.String r5 = "\", name = \""
            r4.append(r5)
            java.lang.String r5 = r3.propertyName
            r4.append(r5)
            java.lang.String r5 = "\", value = \""
            r4.append(r5)
            java.lang.String r3 = r3.propertyValue
            r4.append(r3)
            java.lang.String r3 = "\""
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            android.util.Log.i(r2, r3)
            goto L_0x0010
        L_0x0063:
            java.lang.Object r7 = r6.mServiceLock
            monitor-enter(r7)
            com.google.input.ITouchContextService r1 = r6.mTouchContextService     // Catch:{ all -> 0x007e }
            if (r1 != 0) goto L_0x0071
            java.lang.String r1 = "TouchContextService.java"
            java.lang.String r2 = "mTouchContextService is null."
            android.util.Log.e(r1, r2)     // Catch:{ all -> 0x007e }
        L_0x0071:
            com.google.input.ITouchContextService r6 = r6.mTouchContextService     // Catch:{ all -> 0x007e }
            if (r6 != 0) goto L_0x0080
            java.lang.String r6 = "TouchContextService.java"
            java.lang.String r0 = "configPropertiesChanged: Failed to get TouchContextService."
            android.util.Log.e(r6, r0)     // Catch:{ all -> 0x007e }
            monitor-exit(r7)     // Catch:{ all -> 0x007e }
            goto L_0x0090
        L_0x007e:
            r6 = move-exception
            goto L_0x0091
        L_0x0080:
            com.google.input.ITouchContextService$Stub$Proxy r6 = (com.google.input.ITouchContextService$Stub$Proxy) r6     // Catch:{ RemoteException -> 0x0087 }
            r6.configPropertiesChanged(r0)     // Catch:{ RemoteException -> 0x0087 }
            monitor-exit(r7)     // Catch:{ all -> 0x007e }
            goto L_0x0090
        L_0x0087:
            r6 = move-exception
            java.lang.String r0 = "TouchContextService.java"
            java.lang.String r1 = "configPropertiesChanged: Failed to update config properties."
            android.util.Log.e(r0, r1, r6)     // Catch:{ all -> 0x007e }
            monitor-exit(r7)     // Catch:{ all -> 0x007e }
        L_0x0090:
            return
        L_0x0091:
            monitor-exit(r7)     // Catch:{ all -> 0x007e }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.input.TouchContextService.m1750$$Nest$mconfigPropertiesChanged(com.google.android.systemui.input.TouchContextService, android.provider.DeviceConfig$Properties):void");
    }

    public TouchContextService(Context context) {
        AnonymousClass1 r1 = new IServiceCallback.Stub() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.input.ITouchContextService$Stub$Proxy} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.input.ITouchContextService$Stub$Proxy} */
            /* JADX WARNING: type inference failed for: r0v9, types: [java.lang.Object, com.google.input.ITouchContextService$Stub$Proxy] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void onRegistration(java.lang.String r4, android.os.IBinder r5) {
                /*
                    r3 = this;
                    if (r5 != 0) goto L_0x000a
                    java.lang.String r3 = "TouchContextService.java"
                    java.lang.String r4 = "ServiceCallback.onRegistration: binder is `null`."
                    android.util.Log.e(r3, r4)
                    return
                L_0x000a:
                    java.lang.String r0 = com.google.android.systemui.input.TouchContextService.INTERFACE_NAME
                    boolean r1 = r0.equals(r4)
                    if (r1 != 0) goto L_0x0022
                    java.lang.String r3 = "TouchContextService.java"
                    java.lang.String r5 = "onServiceRegistration name mismatch – received name: \""
                    java.lang.String r1 = "\", expected name: \""
                    java.lang.String r2 = "\""
                    java.lang.String r4 = androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0.m(r5, r4, r1, r0, r2)
                    android.util.Log.e(r3, r4)
                    return
                L_0x0022:
                    com.google.android.systemui.input.TouchContextService r3 = com.google.android.systemui.input.TouchContextService.this
                    r3.getClass()
                    com.google.android.systemui.input.TouchContextService$$ExternalSyntheticLambda0 r4 = new com.google.android.systemui.input.TouchContextService$$ExternalSyntheticLambda0     // Catch:{ RemoteException -> 0x008b }
                    r4.<init>(r3, r5)     // Catch:{ RemoteException -> 0x008b }
                    r0 = 0
                    r5.linkToDeath(r4, r0)     // Catch:{ RemoteException -> 0x008b }
                    java.lang.Object r4 = r3.mServiceLock
                    monitor-enter(r4)
                    java.lang.String r0 = com.google.input.ITouchContextService.DESCRIPTOR     // Catch:{ all -> 0x0053 }
                    android.os.IInterface r0 = r5.queryLocalInterface(r0)     // Catch:{ all -> 0x0053 }
                    if (r0 == 0) goto L_0x0042
                    boolean r1 = r0 instanceof com.google.input.ITouchContextService     // Catch:{ all -> 0x0053 }
                    if (r1 == 0) goto L_0x0042
                    com.google.input.ITouchContextService r0 = (com.google.input.ITouchContextService) r0     // Catch:{ all -> 0x0053 }
                    goto L_0x0049
                L_0x0042:
                    com.google.input.ITouchContextService$Stub$Proxy r0 = new com.google.input.ITouchContextService$Stub$Proxy     // Catch:{ all -> 0x0053 }
                    r0.<init>()     // Catch:{ all -> 0x0053 }
                    r0.mRemote = r5     // Catch:{ all -> 0x0053 }
                L_0x0049:
                    r3.mTouchContextService = r0     // Catch:{ all -> 0x0053 }
                    com.google.android.systemui.input.TouchContextService$2 r5 = r3.mPropertyNamespaceListener     // Catch:{ RemoteException -> 0x0055 }
                    com.google.input.ITouchContextService$Stub$Proxy r0 = (com.google.input.ITouchContextService$Stub$Proxy) r0     // Catch:{ RemoteException -> 0x0055 }
                    r0.getConfigPropertyNamespaces(r5)     // Catch:{ RemoteException -> 0x0055 }
                    goto L_0x005d
                L_0x0053:
                    r3 = move-exception
                    goto L_0x0089
                L_0x0055:
                    r5 = move-exception
                    java.lang.String r0 = "TouchContextService.java"
                    java.lang.String r1 = "Failed to get property namespaces."
                    android.util.Log.e(r0, r1, r5)     // Catch:{ all -> 0x0053 }
                L_0x005d:
                    monitor-exit(r4)     // Catch:{ all -> 0x0053 }
                    android.media.AudioManager r4 = r3.mAudioManager
                    java.util.concurrent.Executor r5 = com.android.internal.os.BackgroundThread.getExecutor()
                    com.google.android.systemui.input.TouchContextService$4 r0 = r3.mAudioModeListener
                    r4.addOnModeChangedListener(r5, r0)
                    android.media.AudioManager r4 = r3.mAudioManager
                    int r4 = r4.getMode()
                    r3.mPreviousAudioMode = r4
                    android.hardware.display.DisplayManager r4 = r3.mDisplayManager
                    com.google.android.systemui.input.TouchContextService$3 r5 = r3.mDisplayListener
                    android.os.Handler r0 = com.android.internal.os.BackgroundThread.getHandler()
                    r4.registerDisplayListener(r5, r0)
                    android.os.Handler r4 = com.android.internal.os.BackgroundThread.getHandler()
                    com.google.android.systemui.input.TouchContextService$$ExternalSyntheticLambda1 r5 = new com.google.android.systemui.input.TouchContextService$$ExternalSyntheticLambda1
                    r5.<init>(r3)
                    r4.post(r5)
                    goto L_0x0093
                L_0x0089:
                    monitor-exit(r4)     // Catch:{ all -> 0x0053 }
                    throw r3
                L_0x008b:
                    r3 = move-exception
                    java.lang.String r4 = "TouchContextService.java"
                    java.lang.String r5 = "Failed to link to death on ITouchContextService. Binder is probably dead."
                    android.util.Log.e(r4, r5, r3)
                L_0x0093:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.input.TouchContextService.AnonymousClass1.onRegistration(java.lang.String, android.os.IBinder):void");
            }
        };
        new ContentObserver(this, new Handler(Looper.getMainLooper()), 0) {
            public final /* synthetic */ TouchContextService this$0;

            {
                this.this$0 = r1;
            }

            public final void onChange(boolean z) {
                switch (1) {
                    case 0:
                        super.onChange(z);
                        Settings.Secure.getInt(this.this$0.mContentResolver, "adaptive_touch_sensitivity_enabled", -1);
                        this.this$0.updateTouchContext();
                        return;
                    default:
                        super.onChange(z);
                        Settings.Secure.getInt(this.this$0.mContentResolver, "touch_sensitivity_enabled", -1);
                        this.this$0.updateTouchContext();
                        return;
                }
            }
        };
        new ContentObserver(this, new Handler(Looper.getMainLooper()), 1) {
            public final /* synthetic */ TouchContextService this$0;

            {
                this.this$0 = r1;
            }

            public final void onChange(boolean z) {
                switch (1) {
                    case 0:
                        super.onChange(z);
                        Settings.Secure.getInt(this.this$0.mContentResolver, "adaptive_touch_sensitivity_enabled", -1);
                        this.this$0.updateTouchContext();
                        return;
                    default:
                        super.onChange(z);
                        Settings.Secure.getInt(this.this$0.mContentResolver, "touch_sensitivity_enabled", -1);
                        this.this$0.updateTouchContext();
                        return;
                }
            }
        };
        this.mPropertiesLock = new Object();
        this.mPropertiesChangedListeners = new ArrayList();
        this.mActivePropertyNamespaces = new ArrayList();
        this.mServiceLock = new Object();
        this.mPreviousRotation = -1;
        this.mPreviousAudioMode = 0;
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        AudioManager audioManager = (AudioManager) context.getSystemService(AudioManager.class);
        this.mAudioManager = audioManager;
        this.mPreviousAudioMode = audioManager.getMode();
        this.mContentResolver = context.getContentResolver();
        try {
            String str = INTERFACE_NAME;
            if (!ServiceManager.isDeclared(str)) {
                Log.d("TouchContextService.java", "No ITouchContextService declared in manifest, not sending input context.");
                return;
            }
            try {
                ServiceManager.registerForNotifications(str, r1);
            } catch (RemoteException e) {
                Log.e("TouchContextService.java", "Failed to register for notifications.", e);
            }
        } catch (SecurityException e2) {
            Log.e("TouchContextService.java", "Unable to check if AIDL service is declared. " + e2);
        }
    }

    public final void updateTouchContext() {
        ContextPacket contextPacket = new ContextPacket();
        contextPacket.orientation = (byte) this.mPreviousRotation;
        contextPacket.audioMode = (byte) this.mPreviousAudioMode;
        synchronized (this.mServiceLock) {
            if (this.mTouchContextService == null) {
                Log.e("TouchContextService.java", "mTouchContextService is null.");
            }
            ITouchContextService iTouchContextService = this.mTouchContextService;
            if (iTouchContextService == null) {
                Log.e("TouchContextService.java", "Failed to get touch context service, dropping context packet.");
                return;
            }
            try {
                ((ITouchContextService$Stub$Proxy) iTouchContextService).updateContext(contextPacket);
            } catch (RemoteException e) {
                Log.e("TouchContextService.java", "Failed to send input context packet.", e);
            }
        }
    }
}
