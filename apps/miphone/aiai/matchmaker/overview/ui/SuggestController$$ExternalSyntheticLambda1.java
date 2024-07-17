package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import java.util.function.Supplier;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class SuggestController$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ SuggestController f$0;
    public final /* synthetic */ FeedbackParcelables$FeedbackBatch f$1;

    public /* synthetic */ SuggestController$$ExternalSyntheticLambda1(SuggestController suggestController, FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch) {
        this.f$0 = suggestController;
        this.f$1 = feedbackParcelables$FeedbackBatch;
    }

    public final Object get() {
        SuggestController suggestController = this.f$0;
        FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch = this.f$1;
        suggestController.bundleUtils.getClass();
        return BundleUtils.createFeedbackRequest(feedbackParcelables$FeedbackBatch);
    }
}
