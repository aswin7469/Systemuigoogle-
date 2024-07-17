package com.google.android.material.chip;

import android.widget.CompoundButton;
import com.google.android.material.internal.CheckableGroup;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class Chip$$ExternalSyntheticLambda0 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ Chip f$0;

    public /* synthetic */ Chip$$ExternalSyntheticLambda0(Chip chip) {
        this.f$0 = chip;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Chip chip = this.f$0;
        CheckableGroup.AnonymousClass1 r0 = chip.onCheckedChangeListenerInternal;
        if (r0 != null) {
            CheckableGroup checkableGroup = CheckableGroup.this;
            if (!z ? checkableGroup.uncheckInternal(chip, checkableGroup.selectionRequired) : checkableGroup.checkInternal(chip)) {
                checkableGroup.onCheckedStateChanged();
            }
        }
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = chip.onCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(compoundButton, z);
        }
    }
}
