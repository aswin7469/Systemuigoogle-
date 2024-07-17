package com.google.android.systemui.volume.panel.component.shared.availabilitycriteria;

import com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GoogleComponentAvailabilityCriteria implements ComponentAvailabilityCriteria {
    public final Flow isAvailable() {
        return new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
    }
}
