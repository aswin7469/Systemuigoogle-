package com.google.android.systemui.power;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SevereLowBatteryNotification {
    public final Context context;
    public final Lazy notificationManager$delegate = LazyKt__LazyJVMKt.lazy(new SevereLowBatteryNotification$notificationManager$2(this));
    public final UiEventLogger uiEventLogger;

    public SevereLowBatteryNotification(Context context2, UiEventLogger uiEventLogger2) {
        this.context = context2;
        this.uiEventLogger = uiEventLogger2;
    }
}
