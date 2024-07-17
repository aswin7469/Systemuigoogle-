package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SuggestParcelables$Stats {
    public long endTimestampMs;
    public long entityExtractionMs;
    public long ocrDetectionMs;
    public long ocrMs;
    public long startTimestampMs;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Stats, java.lang.Object] */
    public static SuggestParcelables$Stats create(Bundle bundle) {
        ? obj = new Object();
        if (bundle.containsKey("startTimestampMs")) {
            obj.startTimestampMs = bundle.getLong("startTimestampMs");
        }
        if (bundle.containsKey("endTimestampMs")) {
            obj.endTimestampMs = bundle.getLong("endTimestampMs");
        }
        if (bundle.containsKey("ocrMs")) {
            obj.ocrMs = bundle.getLong("ocrMs");
        }
        if (bundle.containsKey("ocrDetectionMs")) {
            obj.ocrDetectionMs = bundle.getLong("ocrDetectionMs");
        }
        if (bundle.containsKey("entityExtractionMs")) {
            obj.entityExtractionMs = bundle.getLong("entityExtractionMs");
        }
        return obj;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong("startTimestampMs", this.startTimestampMs);
        bundle.putLong("endTimestampMs", this.endTimestampMs);
        bundle.putLong("ocrMs", this.ocrMs);
        bundle.putLong("ocrDetectionMs", this.ocrDetectionMs);
        bundle.putLong("entityExtractionMs", this.entityExtractionMs);
        return bundle;
    }
}
