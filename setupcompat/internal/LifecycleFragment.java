package com.google.android.setupcompat.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import com.google.android.setupcompat.logging.CustomEvent;
import com.google.android.setupcompat.logging.MetricKey;
import com.google.android.setupcompat.logging.SetupMetricsLogger;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LifecycleFragment extends Fragment {
    public static final /* synthetic */ int $r8$clinit = 0;
    public long durationInNanos;
    public MetricKey metricKey;
    public long startInNanos;

    static {
        Class<LifecycleFragment> cls = LifecycleFragment.class;
    }

    public final void onAttach(Context context) {
        super.onAttach(context);
        this.metricKey = MetricKey.get("ScreenDuration", getActivity());
    }

    public final void onDetach() {
        boolean z;
        super.onDetach();
        Activity activity = getActivity();
        MetricKey metricKey2 = this.metricKey;
        long millis = TimeUnit.NANOSECONDS.toMillis(this.durationInNanos);
        int i = SetupMetricsLogger.$r8$clinit;
        Preconditions.checkNotNull(activity, "Context cannot be null.");
        Preconditions.checkNotNull(metricKey2, "Timer name cannot be null.");
        if (millis >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument("Duration cannot be negative.", z);
        SetupCompatServiceInvoker setupCompatServiceInvoker = SetupCompatServiceInvoker.get(activity);
        Bundle bundle = new Bundle();
        bundle.putParcelable("MetricKey_bundle", MetricKey.fromMetricKey(metricKey2));
        bundle.putLong("timeMillis", millis);
        setupCompatServiceInvoker.logMetricEvent(2, bundle);
    }

    public final void onPause() {
        super.onPause();
        this.durationInNanos = (ClockProvider.ticker.read() - this.startInNanos) + this.durationInNanos;
    }

    public final void onResume() {
        super.onResume();
        this.startInNanos = ClockProvider.ticker.read();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putLong("onScreenResume", System.nanoTime());
        Activity activity = getActivity();
        MetricKey metricKey2 = MetricKey.get("ScreenActivity", getActivity());
        Parcelable.Creator creator = CustomEvent.CREATOR;
        PersistableBundle persistableBundle2 = PersistableBundle.EMPTY;
        long millis = TimeUnit.NANOSECONDS.toMillis(ClockProvider.ticker.read());
        PersistableBundles.assertIsValid(persistableBundle);
        PersistableBundles.assertIsValid(persistableBundle2);
        SetupMetricsLogger.logCustomEvent(activity, new CustomEvent(millis, metricKey2, persistableBundle, persistableBundle2));
    }
}
