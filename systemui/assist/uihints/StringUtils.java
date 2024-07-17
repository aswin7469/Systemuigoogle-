package com.google.android.systemui.assist.uihints;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class StringUtils {
    public static StringStabilityInfo getRightMostStabilityInfoLeaf(String str, int i, int i2, int i3, int i4, int[][] iArr) {
        int i5;
        int i6 = -1;
        int i7 = 0;
        int i8 = -1;
        while (i < i2) {
            for (int i9 = i3; i9 < i4; i9++) {
                int i10 = iArr[i][i9];
                if (i10 > i7) {
                    i6 = i;
                    i8 = i9;
                    i7 = i10;
                }
            }
            i++;
        }
        if (i7 == 0) {
            return new StringStabilityInfo(i3 - 1, str);
        }
        int i11 = i6 + 1;
        if (i11 == i2 || (i5 = i8 + 1) == i4) {
            return new StringStabilityInfo(i8, str);
        }
        return getRightMostStabilityInfoLeaf(str, i11, i2, i5, i4, iArr);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class StringStabilityInfo {
        public final String stable;
        public final String unstable;

        public StringStabilityInfo(String str) {
            this.stable = "";
            this.unstable = str == null ? "" : str;
        }

        public StringStabilityInfo(int i, String str) {
            if (i >= str.length()) {
                this.stable = str;
                this.unstable = "";
                return;
            }
            int i2 = i + 1;
            this.stable = str.substring(0, i2);
            this.unstable = str.substring(i2);
        }
    }
}
