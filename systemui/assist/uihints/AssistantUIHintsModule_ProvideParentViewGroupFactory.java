package com.google.android.systemui.assist.uihints;

import android.view.ViewGroup;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class AssistantUIHintsModule_ProvideParentViewGroupFactory implements Provider {
    public static ViewGroup provideParentViewGroup(Object obj) {
        AssistUIView assistUIView = ((OverlayUiHost) obj).mRoot;
        Preconditions.checkNotNullFromProvides(assistUIView);
        return assistUIView;
    }
}
