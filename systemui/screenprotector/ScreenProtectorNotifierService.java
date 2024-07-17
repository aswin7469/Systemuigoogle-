package com.google.android.systemui.screenprotector;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IServiceCallback;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import com.google.input.algos.spd.IScreenProtectorDetectorListener;
import com.google.input.algos.spd.IScreenProtectorDetectorService;
import com.google.input.algos.spd.ScreenProtectorNotifierPacket;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ScreenProtectorNotifierService {
    public static final String INTERFACE_NAME = ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder(), IScreenProtectorDetectorService.DESCRIPTOR, "/default");
    public final Context mContext;
    public IScreenProtectorDetectorService mDetectorService;
    public final AnonymousClass2 mListener;
    public NotificationManager mNotificationManager;
    public int mNotifyId = 0;
    public AnonymousClass4 mScreenProtectorModeObserver;
    public final AnonymousClass1 mServiceCallback;
    public final Object mServiceLock = new Object();
    public AnonymousClass3 mSharedPreferenceChangeListener;
    public byte mStatus = -1;

    /* renamed from: -$$Nest$mupdateScreenProtectorNotification  reason: not valid java name */
    public static void m1713$$Nest$mupdateScreenProtectorNotification(ScreenProtectorNotifierService screenProtectorNotifierService, int i) {
        String str;
        ScreenProtectorNotifierService screenProtectorNotifierService2 = screenProtectorNotifierService;
        int i2 = i;
        Context context = screenProtectorNotifierService2.mContext;
        String string = context.getString(2131953705);
        if (i2 == 1) {
            str = context.getString(2131953706);
        } else if (i2 == 2) {
            str = context.getString(2131953701);
        } else {
            return;
        }
        Log.i("ScreenProtectorNotifierService", "Creating ScreenProtectorNotification: notifyID = " + screenProtectorNotifierService2.mNotifyId + " -> " + i2 + ".");
        int i3 = screenProtectorNotifierService2.mNotifyId;
        if (!(i3 == 0 || i3 == i2)) {
            screenProtectorNotifierService2.mNotificationManager.cancelAsUser("ScreenProtectorNotifierService", i3, UserHandle.ALL);
        }
        screenProtectorNotifierService2.mNotifyId = i2;
        Intent intent = new Intent(context, ScreenProtectorNotificationActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("notify_id", i2);
        PendingIntent activityAsUser = PendingIntent.getActivityAsUser(screenProtectorNotifierService2.mContext, 0, intent, 201326592, (Bundle) null, UserHandle.CURRENT);
        NotificationChannel notificationChannel = new NotificationChannel("ScreenProtectorNotificationChannel", string, 2);
        Notification.Builder style = new Notification.Builder(context, "ScreenProtectorNotificationChannel").setAutoCancel(true).setContentIntent(activityAsUser).setContentTitle(string).setContentText(str).setVisibility(1).setSmallIcon(17301642).setStyle(new Notification.BigTextStyle().bigText(str));
        String string2 = context.getString(17040952);
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", string2);
        style.addExtras(bundle);
        screenProtectorNotifierService2.mNotificationManager.createNotificationChannel(notificationChannel);
        screenProtectorNotifierService2.mNotificationManager.notifyAsUser("ScreenProtectorNotifierService", screenProtectorNotifierService2.mNotifyId, style.build(), UserHandle.ALL);
    }

    public ScreenProtectorNotifierService(Context context) {
        AnonymousClass1 r0 = new IServiceCallback.Stub() {
            public final void onRegistration(String str, IBinder iBinder) {
                if (iBinder == null) {
                    Log.e("ScreenProtectorNotifierService", "ServiceCallback.onRegistration() binder is null.");
                    return;
                }
                String str2 = ScreenProtectorNotifierService.INTERFACE_NAME;
                if (!str2.equals(str)) {
                    Log.e("ScreenProtectorNotifierService", MotionLayout$$ExternalSyntheticOutline0.m("onServiceRegistration name mismatch – received name: \"", str, "\", expected name: \"", str2, "\""));
                    return;
                }
                ScreenProtectorNotifierService screenProtectorNotifierService = ScreenProtectorNotifierService.this;
                screenProtectorNotifierService.getClass();
                try {
                    iBinder.linkToDeath(new ScreenProtectorNotifierService$$ExternalSyntheticLambda0(screenProtectorNotifierService, iBinder), 0);
                    NotificationManager notificationManager = (NotificationManager) screenProtectorNotifierService.mContext.getSystemService(NotificationManager.class);
                    screenProtectorNotifierService.mNotificationManager = notificationManager;
                    if (notificationManager == null) {
                        Log.i("ScreenProtectorNotifierService", "Failed to initialize mNotificationManager.");
                    } else {
                        AnonymousClass3 r5 = new SharedPreferences.OnSharedPreferenceChangeListener() {
                            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                                if (str.equals("notification_enabled")) {
                                    ScreenProtectorNotifierService.this.updateNotificationPreference();
                                } else if (str.equals("notification_response")) {
                                    ScreenProtectorNotifierService screenProtectorNotifierService = ScreenProtectorNotifierService.this;
                                    byte b = (byte) ScreenProtectorSharedPreferenceUtils.getSharedPreferences(screenProtectorNotifierService.mContext).getInt("notification_response", -1);
                                    if (b != -1) {
                                        StringBuilder m = GenericDocument$$ExternalSyntheticOutline0.m("updateNotificationResponse() response = ", (int) b, ", status = ");
                                        m.append(screenProtectorNotifierService.mStatus);
                                        Log.i("ScreenProtectorNotifierService", m.toString());
                                        ScreenProtectorNotifierPacket screenProtectorNotifierPacket = new ScreenProtectorNotifierPacket();
                                        screenProtectorNotifierPacket.detectorStatus = screenProtectorNotifierService.mStatus;
                                        screenProtectorNotifierPacket.response = b;
                                        synchronized (screenProtectorNotifierService.mServiceLock) {
                                            try {
                                                ((IScreenProtectorDetectorService.Stub.Proxy) screenProtectorNotifierService.mDetectorService).updateNotifierPacket(screenProtectorNotifierPacket);
                                            } catch (RemoteException e) {
                                                Log.e("ScreenProtectorNotifierService", "Failed to updateNotifierPacket.", e);
                                            }
                                        }
                                    }
                                }
                            }
                        };
                        screenProtectorNotifierService.mSharedPreferenceChangeListener = r5;
                        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(screenProtectorNotifierService.mContext).registerOnSharedPreferenceChangeListener(r5);
                        ContentResolver contentResolver = screenProtectorNotifierService.mContext.getContentResolver();
                        Uri uriFor = Settings.Secure.getUriFor("touch_sensitivity_enabled");
                        AnonymousClass4 r1 = new ContentObserver(new Handler(Looper.getMainLooper())) {
                            public final void onChange(boolean z) {
                                super.onChange(z);
                                ScreenProtectorNotifierService.this.updateScreenProtectorMode();
                            }
                        };
                        screenProtectorNotifierService.mScreenProtectorModeObserver = r1;
                        contentResolver.registerContentObserver(uriFor, false, r1);
                        synchronized (screenProtectorNotifierService.mServiceLock) {
                            screenProtectorNotifierService.getScreenProtectorDetectorService();
                            screenProtectorNotifierService.registerScreenProtectorDetectorLister();
                        }
                        screenProtectorNotifierService.updateNotificationPreference();
                        screenProtectorNotifierService.updateScreenProtectorMode();
                    }
                } catch (RemoteException e) {
                    Log.e("ScreenProtectorNotifierService", "Failed to link to death on IScreenProtectorDetectorService.", e);
                }
                Log.i("ScreenProtectorNotifierService", "IServiceCallback registered - " + ScreenProtectorNotifierService.INTERFACE_NAME);
            }
        };
        this.mListener = new IScreenProtectorDetectorListener.Stub() {
            {
                markVintfStability();
                attachInterface(this, IScreenProtectorDetectorListener.DESCRIPTOR);
            }
        };
        this.mContext = context;
        String str = INTERFACE_NAME;
        if (!ServiceManager.isDeclared(str)) {
            Log.i("ScreenProtectorNotifierService", str + "not declared in manifest, will not send notification.");
            return;
        }
        try {
            ServiceManager.registerForNotifications(str, r0);
        } catch (RemoteException e) {
            Log.e("ScreenProtectorNotifierService", "Failed to register for notifications.", e);
        }
    }

    /* JADX WARNING: type inference failed for: r3v4, types: [com.google.input.algos.spd.IScreenProtectorDetectorService$Stub$Proxy, java.lang.Object] */
    public final void getScreenProtectorDetectorService() {
        IScreenProtectorDetectorService iScreenProtectorDetectorService;
        if (this.mDetectorService == null) {
            String str = INTERFACE_NAME;
            IBinder service = ServiceManager.getService(str);
            if (service == null) {
                Log.e("ScreenProtectorNotifierService", "Failed to get IScreenProtectorDetectorService despite being declared.");
                return;
            }
            int i = IScreenProtectorDetectorService.Stub.$r8$clinit;
            IInterface queryLocalInterface = service.queryLocalInterface(IScreenProtectorDetectorService.DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IScreenProtectorDetectorService)) {
                ? obj = new Object();
                obj.mRemote = service;
                iScreenProtectorDetectorService = obj;
            } else {
                iScreenProtectorDetectorService = (IScreenProtectorDetectorService) queryLocalInterface;
            }
            this.mDetectorService = iScreenProtectorDetectorService;
            Log.i("ScreenProtectorNotifierService", "Service initialized - " + str);
        }
    }

    public final void registerScreenProtectorDetectorLister() {
        Parcel obtain;
        IScreenProtectorDetectorService iScreenProtectorDetectorService = this.mDetectorService;
        if (iScreenProtectorDetectorService != null) {
            try {
                AnonymousClass2 r3 = this.mListener;
                IScreenProtectorDetectorService.Stub.Proxy proxy = (IScreenProtectorDetectorService.Stub.Proxy) iScreenProtectorDetectorService;
                obtain = Parcel.obtain(proxy.mRemote);
                obtain.writeInterfaceToken(IScreenProtectorDetectorService.DESCRIPTOR);
                obtain.writeStrongInterface(r3);
                if (proxy.mRemote.transact(1, obtain, (Parcel) null, 1)) {
                    obtain.recycle();
                    return;
                }
                throw new RemoteException("Method registerListener is unimplemented.");
            } catch (RemoteException e) {
                Log.e("ScreenProtectorNotifierService", "Failed to registerListener.", e);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        }
    }

    public final void updateNotificationPreference() {
        boolean z = ScreenProtectorSharedPreferenceUtils.getSharedPreferences(this.mContext).getBoolean("notification_enabled", true);
        synchronized (this.mServiceLock) {
            try {
                ((IScreenProtectorDetectorService.Stub.Proxy) this.mDetectorService).updateScreenProtectorNotificationPreference(z);
            } catch (RemoteException e) {
                Log.e("ScreenProtectorNotifierService", "Failed to updateNotificationPreference.", e);
            }
        }
    }

    public final void updateScreenProtectorMode() {
        boolean z = false;
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "touch_sensitivity_enabled", 0) != 0) {
            z = true;
        }
        synchronized (this.mServiceLock) {
            try {
                ((IScreenProtectorDetectorService.Stub.Proxy) this.mDetectorService).updateScreenProtectorMode(z);
            } catch (RemoteException e) {
                Log.e("ScreenProtectorNotifierService", "Failed to updateScreenProtectorMode.", e);
            }
        }
    }
}
