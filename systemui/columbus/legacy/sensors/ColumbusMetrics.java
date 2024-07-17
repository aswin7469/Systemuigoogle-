package com.google.android.systemui.columbus.legacy.sensors;

import android.frameworks.stats.IStats;
import android.util.Log;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class ColumbusMetrics {
    public static final boolean DEBUG = Log.isLoggable("Columbus/Metrics", 3);
    public static final String ISTATS_INSTANCE_NAME = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(IStats.DESCRIPTOR, "/default");
}
