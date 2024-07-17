package com.google.android.systemui.input;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.DisplayManagerGlobal;
import android.media.AudioManager;
import android.os.IServiceCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.DeviceConfig;
import android.util.Log;
import android.view.DisplayInfo;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import com.google.input.IConfigPropertyNamespaceListener;
import com.google.input.ITouchContextService;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TouchContextService {
    public static final String INTERFACE_NAME = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), ITouchContextService.DESCRIPTOR, "/default");
    public final ArrayList mActivePropertyNamespaces = new ArrayList();
    public final AudioManager mAudioManager;
    public final AnonymousClass4 mAudioModeListener = new AudioManager.OnModeChangedListener() {
        public final void onModeChanged(int i) {
            TouchContextService touchContextService = TouchContextService.this;
            if (i != touchContextService.mPreviousAudioMode) {
                touchContextService.mPreviousAudioMode = i;
                TouchContextService.m1712$$Nest$mupdateTouchContext(touchContextService);
            }
        }
    };
    public final AnonymousClass3 mDisplayListener = new DisplayManager.DisplayListener() {
        public final void onDisplayChanged(int i) {
            if (i == 0) {
                DisplayInfo displayInfo = DisplayManagerGlobal.getInstance().getDisplayInfo(i);
                if (displayInfo == null) {
                    Log.e("TouchContextService.java", "could not get DisplayInfo for display " + i + ".");
                    return;
                }
                int i2 = displayInfo.rotation;
                TouchContextService touchContextService = TouchContextService.this;
                if (i2 != touchContextService.mPreviousRotation) {
                    touchContextService.mPreviousRotation = i2;
                    TouchContextService.m1712$$Nest$mupdateTouchContext(touchContextService);
                }
            }
        }

        public final void onDisplayAdded(int i) {
        }

        public final void onDisplayRemoved(int i) {
        }
    };
    public final DisplayManager mDisplayManager;
    public int mPreviousAudioMode = 0;
    public int mPreviousRotation = -1;
    public final ArrayList mPropertiesChangedListeners = new ArrayList();
    public final Object mPropertiesLock = new Object();
    public final AnonymousClass2 mPropertyNamespaceListener = new IConfigPropertyNamespaceListener.Stub() {
        {
            markVintfStability();
            attachInterface(this, IConfigPropertyNamespaceListener.DESCRIPTOR);
        }
    };
    public final AnonymousClass1 mServiceCallback;
    public final Object mServiceLock = new Object();
    public ITouchContextService mTouchContextService;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class PropertiesChangedListener implements DeviceConfig.OnPropertiesChangedListener {
        public PropertiesChangedListener() {
        }

        public final void onPropertiesChanged(DeviceConfig.Properties properties) {
            TouchContextService.m1711$$Nest$mconfigPropertiesChanged(TouchContextService.this, properties);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Object, com.google.input.PropertyPacket] */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: -$$Nest$mconfigPropertiesChanged  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1711$$Nest$mconfigPropertiesChanged(com.google.android.systemui.input.TouchContextService r6, android.provider.DeviceConfig.Properties r7) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.input.TouchContextService.m1711$$Nest$mconfigPropertiesChanged(com.google.android.systemui.input.TouchContextService, android.provider.DeviceConfig$Properties):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: -$$Nest$mupdateTouchContext  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m1712$$Nest$mupdateTouchContext(com.google.android.systemui.input.TouchContextService r4) {
        /*
            r4.getClass()
            com.google.input.ContextPacket r0 = new com.google.input.ContextPacket
            r0.<init>()
            int r1 = r4.mPreviousRotation
            byte r1 = (byte) r1
            r0.orientation = r1
            int r1 = r4.mPreviousAudioMode
            byte r1 = (byte) r1
            r0.audioMode = r1
            java.lang.Object r1 = r4.mServiceLock
            monitor-enter(r1)
            com.google.input.ITouchContextService r2 = r4.mTouchContextService     // Catch:{ all -> 0x002d }
            if (r2 != 0) goto L_0x0020
            java.lang.String r2 = "TouchContextService.java"
            java.lang.String r3 = "mTouchContextService is null."
            android.util.Log.e(r2, r3)     // Catch:{ all -> 0x002d }
        L_0x0020:
            com.google.input.ITouchContextService r4 = r4.mTouchContextService     // Catch:{ all -> 0x002d }
            if (r4 != 0) goto L_0x002f
            java.lang.String r4 = "TouchContextService.java"
            java.lang.String r0 = "Failed to get touch context service, dropping context packet."
            android.util.Log.e(r4, r0)     // Catch:{ all -> 0x002d }
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            goto L_0x003f
        L_0x002d:
            r4 = move-exception
            goto L_0x0040
        L_0x002f:
            com.google.input.ITouchContextService$Stub$Proxy r4 = (com.google.input.ITouchContextService$Stub$Proxy) r4     // Catch:{ RemoteException -> 0x0036 }
            r4.updateContext(r0)     // Catch:{ RemoteException -> 0x0036 }
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            goto L_0x003f
        L_0x0036:
            r4 = move-exception
            java.lang.String r0 = "TouchContextService.java"
            java.lang.String r2 = "Failed to send input context packet."
            android.util.Log.e(r0, r2, r4)     // Catch:{ all -> 0x002d }
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
        L_0x003f:
            return
        L_0x0040:
            monitor-exit(r1)     // Catch:{ all -> 0x002d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.input.TouchContextService.m1712$$Nest$mupdateTouchContext(com.google.android.systemui.input.TouchContextService):void");
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
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        AudioManager audioManager = (AudioManager) context.getSystemService(AudioManager.class);
        this.mAudioManager = audioManager;
        this.mPreviousAudioMode = audioManager.getMode();
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
}
