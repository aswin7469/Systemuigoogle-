package com.google.android.systemui.controls;

import android.content.ComponentName;
import com.android.systemui.controls.controller.ControlsControllerImpl;
import com.android.systemui.controls.controller.ControlsTileResourceConfiguration;
import com.android.systemui.controls.dagger.ControlsComponent;
import com.android.systemui.controls.ui.SelectedItem;
import dagger.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GoogleControlsTileResourceConfigurationImpl implements ControlsTileResourceConfiguration {
    public final Lazy controlsComponent;

    public GoogleControlsTileResourceConfigurationImpl(Lazy lazy) {
        this.controlsComponent = lazy;
    }

    public final ComponentName getComponentName() {
        Lazy lazy = this.controlsComponent;
        if (!((ControlsComponent) lazy.get()).featureEnabled) {
            return new ComponentName("", "");
        }
        SelectedItem preferredSelection = ((ControlsControllerImpl) ((ControlsComponent) lazy.get()).controlsController.get()).getPreferredSelection();
        if (preferredSelection instanceof SelectedItem.StructureItem) {
            return preferredSelection.getComponentName();
        }
        if (preferredSelection instanceof SelectedItem.PanelItem) {
            return preferredSelection.getComponentName();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String getPackageName() {
        String packageName = getComponentName().getPackageName();
        if (Intrinsics.areEqual(packageName, "com.google.android.apps.chromecast.app")) {
            return packageName;
        }
        return null;
    }

    public final int getTileImageId() {
        if (Intrinsics.areEqual(getComponentName().getPackageName(), "com.google.android.apps.chromecast.app")) {
            return 2131232426;
        }
        return 2131232336;
    }

    public final int getTileTitleId() {
        if (Intrinsics.areEqual(getComponentName().getPackageName(), "com.google.android.apps.chromecast.app")) {
            return 2131952677;
        }
        return 2131953568;
    }
}
