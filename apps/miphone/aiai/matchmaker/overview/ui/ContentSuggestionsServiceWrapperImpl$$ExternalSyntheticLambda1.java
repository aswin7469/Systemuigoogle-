package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.os.Bundle;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;
import java.util.function.Supplier;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ContentSuggestionsServiceWrapperImpl f$0;
    public final /* synthetic */ Supplier f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1(ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl, Supplier supplier, String str, FeedbackParcelables$FeedbackBatch feedbackParcelables$FeedbackBatch) {
        this.f$0 = contentSuggestionsServiceWrapperImpl;
        this.f$1 = supplier;
        this.f$2 = str;
    }

    public final void run() {
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = this.f$0;
        Supplier supplier = this.f$1;
        String str = this.f$2;
        contentSuggestionsServiceWrapperImpl.getClass();
        try {
            contentSuggestionsServiceWrapperImpl.contentSuggestionsManager.notifyInteraction(str, (Bundle) supplier.get());
        } catch (Throwable th) {
            LogUtils.e("Failed to notifyInteraction", th);
        }
    }
}
