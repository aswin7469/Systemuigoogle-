package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public enum SurfaceType implements Parcelable {
    UNKNOWN(0),
    NOTIFICATION(2);
    
    public static final CREATOR CREATOR = null;
    private final String typeName;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CREATOR implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            String str;
            SurfaceType surfaceType = null;
            if (parcel != null) {
                str = parcel.readString();
            } else {
                str = null;
            }
            if (str == null) {
                Log.w("SurfaceType", "null parameter for createFromParcel");
                return SurfaceType.UNKNOWN;
            }
            SurfaceType[] values = SurfaceType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                SurfaceType surfaceType2 = values[i];
                if (Intrinsics.areEqual(surfaceType2.getTypeName(), str)) {
                    surfaceType = surfaceType2;
                    break;
                }
                i++;
            }
            if (surfaceType == null) {
                return SurfaceType.UNKNOWN;
            }
            return surfaceType;
        }

        public final Object[] newArray(int i) {
            return new SurfaceType[i];
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.systemui.power.batteryevent.aidl.SurfaceType$CREATOR, java.lang.Object] */
    static {
        SurfaceType[] surfaceTypeArr;
        EnumEntriesKt.enumEntries(surfaceTypeArr);
        CREATOR = new Object();
    }

    /* access modifiers changed from: public */
    SurfaceType(int i) {
        this.typeName = r2;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getTypeName() {
        return this.typeName;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.typeName);
    }
}
