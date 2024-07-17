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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
