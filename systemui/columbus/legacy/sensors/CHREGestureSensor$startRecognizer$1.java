package com.google.android.systemui.columbus.legacy.sensors;

import com.google.android.systemui.columbus.ColumbusEvent;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class CHREGestureSensor$startRecognizer$1 extends Lambda implements Function0 {
    final /* synthetic */ CHREGestureSensor this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CHREGestureSensor$startRecognizer$1(CHREGestureSensor cHREGestureSensor) {
        super(0);
        this.this$0 = cHREGestureSensor;
    }

    public final Object invoke() {
        this.this$0.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_LOW_POWER_ACTIVE);
        return Unit.INSTANCE;
    }
}
