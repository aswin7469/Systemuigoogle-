package com.google.android.systemui.statusbar.phone;

import android.app.AlarmManager;
import android.content.Context;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.google.android.systemui.power.batteryhealth.HealthManager;
import dagger.internal.Preconditions;
import java.util.Optional;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class CentralSurfacesGoogleModule_ProvideHealthManagerFactory implements Provider {
    public static Optional provideHealthManager(Context context, AlarmManager alarmManager, BroadcastDispatcher broadcastDispatcher, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope) {
        Optional optional;
        if (context.getResources().getBoolean(2131034121)) {
            optional = Optional.of(new HealthManager(context, alarmManager, broadcastDispatcher, coroutineDispatcher, coroutineScope));
        } else {
            optional = Optional.empty();
        }
        Preconditions.checkNotNullFromProvides(optional);
        return optional;
    }
}
