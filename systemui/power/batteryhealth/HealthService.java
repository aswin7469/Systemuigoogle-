package com.google.android.systemui.power.batteryhealth;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.UserHandle;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class HealthService extends Service {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final boolean healthDebugEnabled = Build.IS_DEBUGGABLE;
    public static final Set supportedCallers = SetsKt.setOf("com.android.settings", "com.android.systemui", "com.google.android.apps.diagnosticstool", "com.google.android.apps.pixel.support", "com.google.android.settings.intelligence");
    public final HealthService$binder$1 binder = new HealthService$binder$1(this);
    public final Context context;
    public final boolean healthFeatureEnabled;
    public final RemoteCallbackList healthListeners = new RemoteCallbackList();
    public final HealthManager healthManager;
    public final ContextScope mainScope = CoroutineScopeKt.MainScope();
    public final HealthService$special$$inlined$observable$1 registeredListenerNum$delegate = new HealthService$special$$inlined$observable$1(this);
    public Job subscribeJob;

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(HealthService.class, "registeredListenerNum", "getRegisteredListenerNum()I", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl};
    }

    public HealthService(Context context2, HealthManager healthManager2, Resources resources) {
        this.context = context2;
        this.healthManager = healthManager2;
        this.healthFeatureEnabled = resources.getBoolean(2131034121);
    }

    public static final String[] access$ensureSupportedCallers(HealthService healthService) {
        healthService.getClass();
        int callingUid = Binder.getCallingUid();
        ExifInterface$$ExternalSyntheticOutline0.m("ensureSupportedCallers: pkg=", "HealthService", callingUid);
        String[] packagesForUid = healthService.context.getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            int length = packagesForUid.length;
            int i = 0;
            while (i < length) {
                if (!supportedCallers.contains(packagesForUid[i])) {
                    i++;
                }
            }
            throw new SecurityException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("ensureSupportedCallers: ", Arrays.toString(packagesForUid)));
        }
        return packagesForUid;
    }

    public static final Object access$notifyListeners(HealthService healthService, Function1 function1, Continuation continuation) {
        healthService.getClass();
        Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new HealthService$notifyListeners$2(healthService, function1, (Continuation) null));
        if (coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return coroutineScope;
        }
        return Unit.INSTANCE;
    }

    public final IBinder onBind(Intent intent) {
        Log.i("HealthService", "HealthService bound");
        if (this.healthFeatureEnabled) {
            return this.binder;
        }
        return new Binder();
    }

    public final void onCreate() {
        super.onCreate();
        if (healthDebugEnabled) {
            HealthManager healthManager2 = this.healthManager;
            healthManager2.getClass();
            if (HealthManager.healthDebugEnabled) {
                Log.d("HealthManager", "register healthDebugReceiver");
                BroadcastDispatcher.registerReceiver$default(healthManager2.broadcastDispatcher, healthManager2.healthDebugReceiver, new IntentFilter("com.google.android.systemui.BATTERY_HEALTH_DEBUG"), (Executor) null, (UserHandle) null, 0, (String) null, 60);
            }
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        if (healthDebugEnabled) {
            HealthManager healthManager2 = this.healthManager;
            healthManager2.getClass();
            if (HealthManager.healthDebugEnabled) {
                Log.d("HealthManager", "unregister healthDebugReceiver");
                healthManager2.broadcastDispatcher.unregisterReceiver(healthManager2.healthDebugReceiver);
            }
        }
    }

    public static /* synthetic */ void getHealthListeners$annotations() {
    }

    public static /* synthetic */ void getSubscribeJob$annotations() {
    }
}
