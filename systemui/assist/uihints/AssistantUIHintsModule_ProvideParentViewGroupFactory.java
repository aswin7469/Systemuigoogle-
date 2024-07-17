package com.google.android.systemui.assist.uihints;

import android.view.ViewGroup;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class AssistantUIHintsModule_ProvideParentViewGroupFactory implements Provider {
    public static ViewGroup provideParentViewGroup(Object obj) {
        AssistUIView assistUIView = ((OverlayUiHost) obj).mRoot;
        Preconditions.checkNotNullFromProvides(assistUIView);
        return assistUIView;
    }
}
