package com.google.android.systemui.columbus.legacy.sensors;

import android.frameworks.stats.IStats;
import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class ColumbusMetrics {
    public static final boolean DEBUG = Log.isLoggable("Columbus/Metrics", 3);
    public static final String ISTATS_INSTANCE_NAME = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(IStats.DESCRIPTOR, "/default");
}
