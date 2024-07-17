package com.google.android.systemui.power;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.service.dreams.IDreamManager;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.policy.KeyguardStateController;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SevereLowBatteryDialog {
    public final Context mContext;
    public final Handler mHandler = new Handler(Looper.getMainLooper());
    public final IDreamManager mIDreamManager;
    public final KeyguardStateController mKeyguardStateController;
    SystemUIDialog mSevereBatteryDialog;
    public final UiEventLogger mUiEventLogger;

    public SevereLowBatteryDialog(Context context, UiEventLogger uiEventLogger, KeyguardStateController keyguardStateController, IDreamManager iDreamManager) {
        this.mContext = context;
        this.mUiEventLogger = uiEventLogger;
        this.mKeyguardStateController = keyguardStateController;
        this.mIDreamManager = iDreamManager;
    }
}
