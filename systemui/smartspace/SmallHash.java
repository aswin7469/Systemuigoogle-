package com.google.android.systemui.smartspace;

import java.util.Objects;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SmallHash {
    public static int hash(String str) {
        return Math.abs(Math.floorMod(Objects.hashCode(str), 8192));
    }
}
