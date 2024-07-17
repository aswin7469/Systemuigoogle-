package com.google.android.systemui.dreamliner;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DockInfo {
    public int accessoryType;
    public String manufacturer;
    public String model;
    public String serialNumber;

    public final String toString() {
        return this.manufacturer + ", " + this.model + ", " + this.serialNumber + ", " + this.accessoryType;
    }
}
