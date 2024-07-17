package com.google.android.systemui.elmyra.actions;

import android.content.Context;
import com.android.systemui.shade.ShadeController;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SettingsAction_Factory implements Provider {
    public static SettingsAction newInstance(Context context, Executor executor, ShadeController shadeController, LaunchOpa launchOpa) {
        return new SettingsAction(context, executor, shadeController, launchOpa);
    }
}
