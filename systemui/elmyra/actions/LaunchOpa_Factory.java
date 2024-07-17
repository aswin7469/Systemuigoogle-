package com.google.android.systemui.elmyra.actions;

import android.app.KeyguardManager;
import android.content.Context;
import com.android.systemui.assist.AssistManager;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.tuner.TunerService;
import com.google.android.systemui.elmyra.feedback.AssistInvocationEffect;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class LaunchOpa_Factory implements Provider {
    public static LaunchOpa newInstance(Context context, Executor executor, ShadeController shadeController, AssistManager assistManager, KeyguardManager keyguardManager, TunerService tunerService, AssistInvocationEffect assistInvocationEffect) {
        return new LaunchOpa(context, executor, shadeController, assistManager, keyguardManager, tunerService, assistInvocationEffect);
    }
}
