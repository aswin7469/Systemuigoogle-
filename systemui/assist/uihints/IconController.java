package com.google.android.systemui.assist.uihints;

import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class IconController implements ConfigurationController.ConfigurationListener {
    public boolean mHasAccurateLuma;
    public final KeyboardIconView mKeyboardIcon;
    public final ZeroStateIconView mZeroStateIcon;

    public IconController(ViewGroup viewGroup, ConfigurationController configurationController) {
        KeyboardIconView keyboardIconView = (KeyboardIconView) viewGroup.findViewById(2131362785);
        this.mKeyboardIcon = keyboardIconView;
        keyboardIconView.setOnClickListener(new IconController$$ExternalSyntheticLambda0(this));
        ZeroStateIconView zeroStateIconView = (ZeroStateIconView) viewGroup.findViewById(2131364163);
        this.mZeroStateIcon = zeroStateIconView;
        zeroStateIconView.setOnClickListener(new IconController$$ExternalSyntheticLambda0(this));
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
    }

    public final void maybeUpdateIconVisibility(View view, boolean z) {
        boolean z2;
        boolean z3 = true;
        int i = 0;
        if (view.getVisibility() == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z || !this.mHasAccurateLuma) {
            z3 = false;
        }
        if (z2 != z3) {
            if (!z3) {
                i = 8;
            }
            view.setVisibility(i);
        }
    }

    public final void onDensityOrFontScaleChanged() {
        KeyboardIconView keyboardIconView = this.mKeyboardIcon;
        keyboardIconView.mKeyboardIcon.setImageDrawable(keyboardIconView.getContext().getDrawable(2131232754));
        ZeroStateIconView zeroStateIconView = this.mZeroStateIcon;
        zeroStateIconView.mZeroStateIcon.setImageDrawable(zeroStateIconView.getContext().getDrawable(2131232683));
    }
}
