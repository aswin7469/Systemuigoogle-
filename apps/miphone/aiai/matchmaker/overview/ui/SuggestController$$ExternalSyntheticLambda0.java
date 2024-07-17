package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.os.Bundle;
import android.util.Log;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ParserParcelables$ParsedViewHierarchy;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class SuggestController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SuggestController f$0;

    public /* synthetic */ SuggestController$$ExternalSyntheticLambda0(SuggestController suggestController) {
        this.f$0 = suggestController;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext, java.lang.Object] */
    public final void run() {
        SuggestController suggestController = this.f$0;
        suggestController.getClass();
        try {
            Log.i("AiAiSuggestUi", "Connecting to system intelligence module.");
            ? obj = new Object();
            obj.interactionType = SuggestParcelables$InteractionType.SETUP;
            ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = suggestController.wrapper;
            BundleUtils bundleUtils = suggestController.bundleUtils;
            String packageName = suggestController.uiContext.getPackageName();
            bundleUtils.getClass();
            contentSuggestionsServiceWrapperImpl.suggestContentSelections(-1, BundleUtils.createSelectionsRequest(packageName, "", -1, -1, obj, (Bundle) null, (ParserParcelables$ParsedViewHierarchy) null), new SuggestController$$ExternalSyntheticLambda2(suggestController));
        } catch (RuntimeException e) {
            LogUtils.e("Error while connecting to system intelligence module.", e);
        }
    }
}
