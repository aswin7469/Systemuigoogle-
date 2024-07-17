package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.content.Context;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SuggestController {
    public final BundleUtils bundleUtils = new Object();
    public final Context uiContext;
    public final ContentSuggestionsServiceWrapperImpl wrapper;

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils, java.lang.Object] */
    public SuggestController(Context context, Context context2, Executor executor, Executor executor2) {
        this.uiContext = context2;
        this.wrapper = new ContentSuggestionsServiceWrapperImpl(context, executor, executor2);
    }

    public void reportMetricsToService(String str, FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch, SuggestListener suggestListener) {
        List list = feedbackParcelables$FeedbackBatch.feedback;
        int i = Utils.$r8$clinit;
        list.getClass();
        if (!list.isEmpty()) {
            SuggestController$$ExternalSyntheticLambda1 suggestController$$ExternalSyntheticLambda1 = new SuggestController$$ExternalSyntheticLambda1(this, feedbackParcelables$FeedbackBatch);
            ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = this.wrapper;
            contentSuggestionsServiceWrapperImpl.getClass();
            contentSuggestionsServiceWrapperImpl.loggingExecutor.execute(new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1(contentSuggestionsServiceWrapperImpl, suggestController$$ExternalSyntheticLambda1, str, feedbackParcelables$FeedbackBatch));
        }
    }
}
