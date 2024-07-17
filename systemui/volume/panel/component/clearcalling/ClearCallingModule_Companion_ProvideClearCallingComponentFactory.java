package com.google.android.systemui.volume.panel.component.clearcalling;

import com.android.systemui.volume.panel.component.button.ui.composable.ToggleButtonComponent;
import com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel;
import javax.inject.Provider;
import kotlin.jvm.internal.FunctionReference;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ClearCallingModule_Companion_ProvideClearCallingComponentFactory implements Provider {
    public static ToggleButtonComponent provideClearCallingComponent(ClearCallingViewModel clearCallingViewModel) {
        return new ToggleButtonComponent(clearCallingViewModel.buttonViewModel, new FunctionReference(1, clearCallingViewModel, ClearCallingViewModel.class, "setIsClearCallingEnabled", "setIsClearCallingEnabled(Z)V", 0));
    }
}
