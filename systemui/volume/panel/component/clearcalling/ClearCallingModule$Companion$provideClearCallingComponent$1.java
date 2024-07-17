package com.google.android.systemui.volume.panel.component.clearcalling;

import com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
final /* synthetic */ class ClearCallingModule$Companion$provideClearCallingComponent$1 extends FunctionReferenceImpl implements Function1 {
    public final Object invoke(Object obj) {
        ((ClearCallingViewModel) this.receiver).setIsClearCallingEnabled(((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }
}
