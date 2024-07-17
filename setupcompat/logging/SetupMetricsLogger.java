package com.google.android.setupcompat.logging;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import com.google.android.setupcompat.internal.PersistableBundles;
import com.google.android.setupcompat.internal.Preconditions;
import com.google.android.setupcompat.internal.SetupCompatServiceInvoker;
import com.google.android.setupcompat.util.Logger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SetupMetricsLogger {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        StringBuilder sb = new StringBuilder("[");
        sb.append("SetupMetricsLogger");
        sb.append("] ");
    }

    public static void logCustomEvent(Context context, CustomEvent customEvent) {
        Preconditions.checkNotNull(context, "Context cannot be null.");
        SetupCompatServiceInvoker setupCompatServiceInvoker = SetupCompatServiceInvoker.get(context);
        Bundle bundle = new Bundle();
        Parcelable.Creator creator = CustomEvent.CREATOR;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("CustomEvent_version", 1);
        bundle2.putLong("CustomEvent_timestamp", customEvent.timestampMillis);
        bundle2.putBundle("CustomEvent_metricKey", MetricKey.fromMetricKey(customEvent.metricKey));
        PersistableBundle persistableBundle = new PersistableBundle(customEvent.persistableBundle);
        Logger logger = PersistableBundles.LOG;
        Bundle bundle3 = new Bundle();
        bundle3.putAll(persistableBundle);
        bundle2.putBundle("CustomEvent_bundleValues", bundle3);
        PersistableBundle persistableBundle2 = customEvent.piiValues;
        Bundle bundle4 = new Bundle();
        bundle4.putAll(persistableBundle2);
        bundle2.putBundle("CustomEvent_pii_bundleValues", bundle4);
        bundle.putParcelable("CustomEvent_bundle", bundle2);
        setupCompatServiceInvoker.logMetricEvent(1, bundle);
    }

    public static void setInstanceForTesting(SetupMetricsLogger setupMetricsLogger) {
    }
}
