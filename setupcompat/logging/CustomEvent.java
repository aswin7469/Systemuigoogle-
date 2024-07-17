package com.google.android.setupcompat.logging;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import com.google.android.setupcompat.internal.Preconditions;
import com.google.android.setupcompat.internal.Validations;
import java.util.Arrays;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class CustomEvent implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Object();
    static final int MAX_STR_LENGTH = 50;
    static final int MIN_BUNDLE_KEY_LENGTH = 3;
    public final MetricKey metricKey;
    public final PersistableBundle persistableBundle;
    public final PersistableBundle piiValues;
    public final long timestampMillis;

    /* renamed from: com.google.android.setupcompat.logging.CustomEvent$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return new CustomEvent(parcel.readLong(), (MetricKey) parcel.readParcelable(MetricKey.class.getClassLoader()), parcel.readPersistableBundle(MetricKey.class.getClassLoader()), parcel.readPersistableBundle(MetricKey.class.getClassLoader()));
        }

        public final Object[] newArray(int i) {
            return new CustomEvent[i];
        }
    }

    public CustomEvent(long j, MetricKey metricKey2, PersistableBundle persistableBundle2, PersistableBundle persistableBundle3) {
        boolean z;
        boolean z2;
        if (j >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument("Timestamp cannot be negative.", z);
        Preconditions.checkNotNull(metricKey2, "MetricKey cannot be null.");
        Preconditions.checkNotNull(persistableBundle2, "Bundle cannot be null.");
        Preconditions.checkArgument("Bundle cannot be empty.", !persistableBundle2.isEmpty());
        Preconditions.checkNotNull(persistableBundle3, "piiValues cannot be null.");
        for (String str : persistableBundle2.keySet()) {
            Validations.assertLengthInRange(str, 3, MAX_STR_LENGTH, "bundle key");
            Object obj = persistableBundle2.get(str);
            if (obj instanceof String) {
                if (((String) obj).length() <= MAX_STR_LENGTH) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                Preconditions.checkArgument("Maximum length of string value for key='" + str + "' cannot exceed 50.", z2);
            }
        }
        this.timestampMillis = j;
        this.metricKey = metricKey2;
        this.persistableBundle = new PersistableBundle(persistableBundle2);
        this.piiValues = new PersistableBundle(persistableBundle3);
    }

    public final int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        if (com.google.android.setupcompat.internal.PersistableBundles.toMap(r1).equals(com.google.android.setupcompat.internal.PersistableBundles.toMap(r3)) != false) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 1
            if (r7 != r8) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r8 instanceof com.google.android.setupcompat.logging.CustomEvent
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            com.google.android.setupcompat.logging.CustomEvent r8 = (com.google.android.setupcompat.logging.CustomEvent) r8
            long r3 = r7.timestampMillis
            long r5 = r8.timestampMillis
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x004e
            com.google.android.setupcompat.logging.MetricKey r1 = r7.metricKey
            com.google.android.setupcompat.logging.MetricKey r3 = r8.metricKey
            if (r1 == r3) goto L_0x0022
            if (r1 == 0) goto L_0x004e
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x004e
        L_0x0022:
            android.os.PersistableBundle r1 = r7.persistableBundle
            android.os.PersistableBundle r3 = r8.persistableBundle
            if (r1 == r3) goto L_0x0037
            android.util.ArrayMap r1 = com.google.android.setupcompat.internal.PersistableBundles.toMap(r1)
            android.util.ArrayMap r3 = com.google.android.setupcompat.internal.PersistableBundles.toMap(r3)
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x004e
            goto L_0x0039
        L_0x0037:
            com.google.android.setupcompat.util.Logger r1 = com.google.android.setupcompat.internal.PersistableBundles.LOG
        L_0x0039:
            android.os.PersistableBundle r7 = r7.piiValues
            android.os.PersistableBundle r8 = r8.piiValues
            if (r7 == r8) goto L_0x004f
            android.util.ArrayMap r7 = com.google.android.setupcompat.internal.PersistableBundles.toMap(r7)
            android.util.ArrayMap r8 = com.google.android.setupcompat.internal.PersistableBundles.toMap(r8)
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r0 = r2
        L_0x004f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.logging.CustomEvent.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.timestampMillis), this.metricKey, this.persistableBundle, this.piiValues});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.timestampMillis);
        parcel.writeParcelable(this.metricKey, i);
        parcel.writePersistableBundle(this.persistableBundle);
        parcel.writePersistableBundle(this.piiValues);
    }
}
