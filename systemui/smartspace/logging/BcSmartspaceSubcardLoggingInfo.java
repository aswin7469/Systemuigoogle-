package com.google.android.systemui.smartspace.logging;

import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BcSmartspaceSubcardLoggingInfo {
    public int mClickedSubcardIndex;
    public List mSubcards;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BcSmartspaceSubcardLoggingInfo)) {
            return false;
        }
        BcSmartspaceSubcardLoggingInfo bcSmartspaceSubcardLoggingInfo = (BcSmartspaceSubcardLoggingInfo) obj;
        if (this.mClickedSubcardIndex != bcSmartspaceSubcardLoggingInfo.mClickedSubcardIndex || !Objects.equals(this.mSubcards, bcSmartspaceSubcardLoggingInfo.mSubcards)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.mSubcards, Integer.valueOf(this.mClickedSubcardIndex)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BcSmartspaceSubcardLoggingInfo{mSubcards=");
        sb.append(this.mSubcards);
        sb.append(", mClickedSubcardIndex=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.mClickedSubcardIndex, '}');
    }
}
