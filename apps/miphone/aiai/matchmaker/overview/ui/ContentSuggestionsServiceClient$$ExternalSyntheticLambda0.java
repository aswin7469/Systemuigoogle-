package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;
import java.util.ArrayList;
import java.util.function.Supplier;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ContentSuggestionsServiceClient$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ ContentSuggestionsServiceClient f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ Intent f$4;

    public /* synthetic */ ContentSuggestionsServiceClient$$ExternalSyntheticLambda0(ContentSuggestionsServiceClient contentSuggestionsServiceClient, String str, String str2, boolean z, Intent intent) {
        this.f$0 = contentSuggestionsServiceClient;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = z;
        this.f$4 = intent;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotActionFeedback] */
    /* JADX WARNING: type inference failed for: r7v2, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotFeedback] */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$Feedback] */
    /* JADX WARNING: type inference failed for: r7v3, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v6, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$QuickShareInfo] */
    public final Object get() {
        ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.f$0;
        String str = this.f$1;
        String str2 = this.f$2;
        boolean z = this.f$3;
        Intent intent = this.f$4;
        contentSuggestionsServiceClient.getClass();
        ArrayList arrayList = new ArrayList();
        ? obj = new Object();
        obj.actionType = str2;
        obj.isSmartActions = z;
        if (!(!"QUICK_SHARE".equals(str2) || intent == null || intent.getComponent() == null)) {
            ? obj2 = new Object();
            Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
            if (uri != null) {
                obj2.contentUri = uri.toString();
            }
            ComponentName component = intent.getComponent();
            int i = Utils.$r8$clinit;
            component.getClass();
            obj2.targetPackageName = component.getPackageName();
            ComponentName component2 = intent.getComponent();
            component2.getClass();
            obj2.targetClassName = component2.getClassName();
            obj2.targetShortcutId = intent.getStringExtra("android.intent.extra.shortcut.ID");
            obj.quickShareInfo = obj2;
        }
        ? obj3 = new Object();
        obj3.screenshotId = str;
        obj3.screenshotFeedback = obj;
        ? obj4 = new Object();
        arrayList.add(obj4);
        obj4.feedback = obj3;
        ? obj5 = new Object();
        obj5.screenSessionId = (long) 0;
        obj5.overviewSessionId = str;
        int i2 = Utils.$r8$clinit;
        obj5.feedback = arrayList;
        contentSuggestionsServiceClient.bundleUtils.getClass();
        return BundleUtils.createFeedbackRequest(obj5);
    }
}
