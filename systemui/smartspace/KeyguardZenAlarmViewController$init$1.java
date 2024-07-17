package com.google.android.systemui.smartspace;

import android.view.View;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class KeyguardZenAlarmViewController$init$1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ KeyguardZenAlarmViewController this$0;

    public KeyguardZenAlarmViewController$init$1(KeyguardZenAlarmViewController keyguardZenAlarmViewController) {
        this.this$0 = keyguardZenAlarmViewController;
    }

    public final void onViewAttachedToWindow(View view) {
        this.this$0.smartspaceViews.add((BcSmartspaceDataPlugin.SmartspaceView) view);
        if (this.this$0.smartspaceViews.size() == 1) {
            KeyguardZenAlarmViewController keyguardZenAlarmViewController = this.this$0;
            ((ZenModeControllerImpl) keyguardZenAlarmViewController.zenModeController).addCallback(keyguardZenAlarmViewController.zenModeCallback);
            KeyguardZenAlarmViewController keyguardZenAlarmViewController2 = this.this$0;
            List list = keyguardZenAlarmViewController2.nextClockAlarmController.changeCallbacks;
            KeyguardZenAlarmViewController$nextAlarmCallback$1 keyguardZenAlarmViewController$nextAlarmCallback$1 = keyguardZenAlarmViewController2.nextAlarmCallback;
            list.add(keyguardZenAlarmViewController$nextAlarmCallback$1);
            KeyguardZenAlarmViewController keyguardZenAlarmViewController3 = keyguardZenAlarmViewController$nextAlarmCallback$1.this$0;
            keyguardZenAlarmViewController3.getClass();
            BuildersKt.launch$default(keyguardZenAlarmViewController3.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardZenAlarmViewController$updateNextAlarm$1(keyguardZenAlarmViewController3, (Continuation) null), 3);
        }
        KeyguardZenAlarmViewController keyguardZenAlarmViewController4 = this.this$0;
        keyguardZenAlarmViewController4.updateDnd();
        BuildersKt.launch$default(keyguardZenAlarmViewController4.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardZenAlarmViewController$updateNextAlarm$1(keyguardZenAlarmViewController4, (Continuation) null), 3);
    }

    public final void onViewDetachedFromWindow(View view) {
        this.this$0.smartspaceViews.remove((BcSmartspaceDataPlugin.SmartspaceView) view);
        if (this.this$0.smartspaceViews.isEmpty()) {
            KeyguardZenAlarmViewController keyguardZenAlarmViewController = this.this$0;
            ((ZenModeControllerImpl) keyguardZenAlarmViewController.zenModeController).removeCallback(keyguardZenAlarmViewController.zenModeCallback);
            KeyguardZenAlarmViewController keyguardZenAlarmViewController2 = this.this$0;
            keyguardZenAlarmViewController2.nextClockAlarmController.changeCallbacks.remove(keyguardZenAlarmViewController2.nextAlarmCallback);
        }
    }
}
