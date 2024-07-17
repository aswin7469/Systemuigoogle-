package com.google.android.systemui.dagger;

import android.app.NotificationManager;
import android.media.AudioManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.volume.CsdWarningDialog;
import com.android.systemui.volume.VolumeDialogImpl$$ExternalSyntheticLambda0;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$16 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$16(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final CsdWarningDialog create(int i, VolumeDialogImpl$$ExternalSyntheticLambda0 volumeDialogImpl$$ExternalSyntheticLambda0) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = switchingProvider.sysUIGoogleGlobalRootComponentImpl;
        return new CsdWarningDialog(i, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (NotificationManager) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideNotificationManagerProvider.get(), (DelayableExecutor) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).provideBackgroundDelayableExecutorProvider.get(), volumeDialogImpl$$ExternalSyntheticLambda0);
    }
}
