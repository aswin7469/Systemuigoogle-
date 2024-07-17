package com.google.android.setupcompat.logging;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.setupcompat.internal.Preconditions;
import com.google.android.setupcompat.internal.Validations;
import java.util.Arrays;
import java.util.regex.Pattern;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class MetricKey implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Object();
    public static final Pattern METRIC_KEY_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]+");
    public final String name;
    public final String screenName;

    /* renamed from: com.google.android.setupcompat.logging.MetricKey$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass1 implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return new MetricKey(parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new MetricKey[i];
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, android.os.Parcelable$Creator] */
    static {
        Pattern.compile("^([a-z]+[.])+[A-Z][a-zA-Z0-9]+");
        Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]+");
    }

    public MetricKey(String str, String str2) {
        this.name = str;
        this.screenName = str2;
    }

    public static Bundle fromMetricKey(MetricKey metricKey) {
        Preconditions.checkNotNull(metricKey, "MetricKey cannot be null.");
        Bundle bundle = new Bundle();
        bundle.putInt("MetricKey_version", 1);
        bundle.putString("MetricKey_name", metricKey.name);
        bundle.putString("MetricKey_screenName", metricKey.screenName);
        return bundle;
    }

    public static MetricKey get(String str, Activity activity) {
        String className = activity.getComponentName().getClassName();
        Validations.assertLengthInRange(str, 5, 30, "MetricKey.name");
        Preconditions.checkArgument("Invalid MetricKey, only alpha numeric characters are allowed.", METRIC_KEY_PATTERN.matcher(str).matches());
        return new MetricKey(str, className);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MetricKey)) {
            return false;
        }
        MetricKey metricKey = (MetricKey) obj;
        String str = this.name;
        String str2 = metricKey.name;
        if (str == str2 || (str != null && str.equals(str2))) {
            String str3 = this.screenName;
            String str4 = metricKey.screenName;
            if (str3 == str4) {
                return true;
            }
            if (str3 != null && str3.equals(str4)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, this.screenName});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.screenName);
    }
}
