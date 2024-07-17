package com.google.android.systemui.smartspace;

import android.app.AlarmManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Handler;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyguardZenAlarmViewController {
    public final Drawable alarmImage;
    public final AlarmManager alarmManager;
    public final CoroutineScope applicationScope;
    public final Context context;
    public final BcSmartspaceDataPlugin datePlugin;
    public final Drawable dndImage;
    public final Handler handler;
    public final KeyguardZenAlarmViewController$nextAlarmCallback$1 nextAlarmCallback = new KeyguardZenAlarmViewController$nextAlarmCallback$1(this);
    public final NextClockAlarmController nextClockAlarmController;
    public final KeyguardZenAlarmViewController$showNextAlarm$1 showNextAlarm = new KeyguardZenAlarmViewController$showNextAlarm$1(this);
    public final Set smartspaceViews = new LinkedHashSet();
    public final KeyguardZenAlarmViewController$zenModeCallback$1 zenModeCallback = new KeyguardZenAlarmViewController$zenModeCallback$1(this);
    public final ZenModeController zenModeController;

    public KeyguardZenAlarmViewController(Context context2, BcSmartspaceDataPlugin bcSmartspaceDataPlugin, ZenModeController zenModeController2, AlarmManager alarmManager2, NextClockAlarmController nextClockAlarmController2, Handler handler2, CoroutineScope coroutineScope) {
        this.context = context2;
        this.datePlugin = bcSmartspaceDataPlugin;
        this.zenModeController = zenModeController2;
        this.alarmManager = alarmManager2;
        this.nextClockAlarmController = nextClockAlarmController2;
        this.handler = handler2;
        this.applicationScope = coroutineScope;
        Drawable drawable = ((InsetDrawable) context2.getResources().getDrawable(2131233557, (Resources.Theme) null)).getDrawable();
        if (drawable != null) {
            this.dndImage = drawable;
            this.alarmImage = context2.getResources().getDrawable(2131232426, (Resources.Theme) null);
            return;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public final Job showAlarm() {
        return BuildersKt.launch$default(this.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new KeyguardZenAlarmViewController$showAlarm$1(this, (Continuation) null), 3);
    }

    public final void updateDnd() {
        if (((ZenModeControllerImpl) this.zenModeController).mZenMode != 0) {
            String string = this.context.getResources().getString(2131951812);
            for (BcSmartspaceDataPlugin.SmartspaceView dnd : this.smartspaceViews) {
                dnd.setDnd(this.dndImage, string);
            }
            return;
        }
        for (BcSmartspaceDataPlugin.SmartspaceView dnd2 : this.smartspaceViews) {
            dnd2.setDnd((Drawable) null, (String) null);
        }
    }

    public static /* synthetic */ void getSmartspaceViews$annotations() {
    }
}
