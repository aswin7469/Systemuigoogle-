package com.google.android.systemui.keyguard;

import android.text.TextUtils;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.TrustGrantFlags;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.common.shared.model.TintedIcon;
import com.android.systemui.temporarydisplay.ViewPriority;
import com.android.systemui.temporarydisplay.chipbar.ChipbarInfo;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ActiveUnlockChipbarManager$keyguardUpdateMonitorCallback$1 extends KeyguardUpdateMonitorCallback {
    public final /* synthetic */ ActiveUnlockChipbarManager this$0;

    public ActiveUnlockChipbarManager$keyguardUpdateMonitorCallback$1(ActiveUnlockChipbarManager activeUnlockChipbarManager) {
        this.this$0 = activeUnlockChipbarManager;
    }

    public final void onTrustGrantedForCurrentUser(boolean z, boolean z2, TrustGrantFlags trustGrantFlags, String str) {
        if (!TextUtils.isEmpty(str) && z2) {
            ActiveUnlockChipbarManager activeUnlockChipbarManager = this.this$0;
            if (activeUnlockChipbarManager.globalSettings.getInt(0, "chip_all_watch_unlocks") != 0 || z) {
                Intrinsics.checkNotNull(str);
                TintedIcon tintedIcon = new TintedIcon(new Icon.Resource(2131233098, (ContentDescription) null), 17957019);
                Text.Loaded loaded = new Text.Loaded(str);
                int i = activeUnlockChipbarManager.globalSettings.getInt(1500, "chip_duration");
                ViewPriority viewPriority = ViewPriority.NORMAL;
                activeUnlockChipbarManager.chipbarCoordinator.displayView(new ChipbarInfo(tintedIcon, loaded, "Unlock Chip", "UNLOCK_CHIP", i, "active_unlock", activeUnlockChipbarManager.sessionTracker.getSessionId(1)));
            }
        }
    }
}
