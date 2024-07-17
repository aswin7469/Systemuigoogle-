package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOp;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpStatus;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;
import java.util.ArrayList;
import java.util.function.Supplier;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ContentSuggestionsServiceClient$$ExternalSyntheticLambda2 implements Supplier {
    public final /* synthetic */ ContentSuggestionsServiceClient f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ FeedbackParcelables$ScreenshotOp f$2;
    public final /* synthetic */ FeedbackParcelables$ScreenshotOpStatus f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ ContentSuggestionsServiceClient$$ExternalSyntheticLambda2(ContentSuggestionsServiceClient contentSuggestionsServiceClient, String str, FeedbackParcelables$ScreenshotOp feedbackParcelables$ScreenshotOp, FeedbackParcelables$ScreenshotOpStatus feedbackParcelables$ScreenshotOpStatus, long j) {
        this.f$0 = contentSuggestionsServiceClient;
        this.f$1 = str;
        this.f$2 = feedbackParcelables$ScreenshotOp;
        this.f$3 = feedbackParcelables$ScreenshotOpStatus;
        this.f$4 = j;
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpFeedback] */
    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotFeedback] */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$Feedback] */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch, java.lang.Object] */
    public final Object get() {
        ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.f$0;
        String str = this.f$1;
        FeedbackParcelables$ScreenshotOp feedbackParcelables$ScreenshotOp = this.f$2;
        FeedbackParcelables$ScreenshotOpStatus feedbackParcelables$ScreenshotOpStatus = this.f$3;
        long j = this.f$4;
        contentSuggestionsServiceClient.getClass();
        ArrayList arrayList = new ArrayList();
        ? obj = new Object();
        obj.durationMs = j;
        obj.op = feedbackParcelables$ScreenshotOp;
        obj.status = feedbackParcelables$ScreenshotOpStatus;
        ? obj2 = new Object();
        obj2.screenshotId = str;
        obj2.screenshotFeedback = obj;
        ? obj3 = new Object();
        arrayList.add(obj3);
        obj3.feedback = obj2;
        ? obj4 = new Object();
        obj4.screenSessionId = (long) 0;
        obj4.overviewSessionId = str;
        int i = Utils.$r8$clinit;
        obj4.feedback = arrayList;
        contentSuggestionsServiceClient.bundleUtils.getClass();
        return BundleUtils.createFeedbackRequest(obj4);
    }
}
