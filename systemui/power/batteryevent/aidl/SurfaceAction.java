package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public enum SurfaceAction implements Parcelable {
    UNKNOWN(0);
    
    public static final CREATOR CREATOR = null;
    private final String actionName;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class CREATOR implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            String str;
            SurfaceAction surfaceAction = null;
            if (parcel != null) {
                str = parcel.readString();
            } else {
                str = null;
            }
            if (str == null) {
                Log.w("SurfaceAction", "null parameter for createFromParcel");
                return SurfaceAction.UNKNOWN;
            }
            SurfaceAction[] values = SurfaceAction.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                SurfaceAction surfaceAction2 = values[i];
                if (Intrinsics.areEqual(surfaceAction2.getActionName(), str)) {
                    surfaceAction = surfaceAction2;
                    break;
                }
                i++;
            }
            if (surfaceAction == null) {
                return SurfaceAction.UNKNOWN;
            }
            return surfaceAction;
        }

        public final Object[] newArray(int i) {
            return new SurfaceAction[i];
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.systemui.power.batteryevent.aidl.SurfaceAction$CREATOR, java.lang.Object] */
    static {
        SurfaceAction[] surfaceActionArr;
        EnumEntriesKt.enumEntries(surfaceActionArr);
        CREATOR = new Object();
    }

    /* access modifiers changed from: public */
    SurfaceAction(int i) {
        this.actionName = r2;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getActionName() {
        return this.actionName;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.actionName);
    }
}
