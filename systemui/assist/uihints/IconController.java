package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.input.TouchActionRegion;
import java.util.Optional;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class IconController implements NgaMessageHandler.KeyboardInfoListener, NgaMessageHandler.ZerostateInfoListener, ConfigurationController.ConfigurationListener, TouchActionRegion {
    public boolean mHasAccurateLuma;
    public final KeyboardIconView mKeyboardIcon;
    public boolean mKeyboardIconRequested;
    public PendingIntent mOnKeyboardIconTap;
    public PendingIntent mOnZerostateIconTap;
    public final ZeroStateIconView mZeroStateIcon;
    public boolean mZerostateIconRequested;

    public IconController(ViewGroup viewGroup, ConfigurationController configurationController) {
        KeyboardIconView keyboardIconView = (KeyboardIconView) viewGroup.findViewById(2131362764);
        this.mKeyboardIcon = keyboardIconView;
        keyboardIconView.setOnClickListener(new IconController$$ExternalSyntheticLambda0(this, 0));
        ZeroStateIconView zeroStateIconView = (ZeroStateIconView) viewGroup.findViewById(2131364125);
        this.mZeroStateIcon = zeroStateIconView;
        zeroStateIconView.setOnClickListener(new IconController$$ExternalSyntheticLambda0(this, 1));
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
    }

    public final Optional getTouchActionRegion() {
        Region region = new Region();
        KeyboardIconView keyboardIconView = this.mKeyboardIcon;
        if (keyboardIconView.getVisibility() == 0) {
            Rect rect = new Rect();
            keyboardIconView.getHitRect(rect);
            region.union(rect);
        }
        ZeroStateIconView zeroStateIconView = this.mZeroStateIcon;
        if (zeroStateIconView.getVisibility() == 0) {
            Rect rect2 = new Rect();
            zeroStateIconView.getHitRect(rect2);
            region.union(rect2);
        }
        if (region.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(region);
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
        keyboardIconView.mKeyboardIcon.setImageDrawable(keyboardIconView.getContext().getDrawable(2131232735));
        ZeroStateIconView zeroStateIconView = this.mZeroStateIcon;
        zeroStateIconView.mZeroStateIcon.setImageDrawable(zeroStateIconView.getContext().getDrawable(2131232666));
    }
}
