package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.os.Bundle;
import android.util.Log;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ErrorCode;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$SetupInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class SuggestController$$ExternalSyntheticLambda2 implements ContentSuggestionsServiceWrapper$BundleCallback {
    public final /* synthetic */ SuggestController f$0;

    public /* synthetic */ SuggestController$$ExternalSyntheticLambda2(SuggestController suggestController) {
        this.f$0 = suggestController;
    }

    public final void onResult(Bundle bundle) {
        SuggestController suggestController = this.f$0;
        suggestController.getClass();
        try {
            suggestController.bundleUtils.getClass();
            SuggestParcelables$SetupInfo suggestParcelables$SetupInfo = BundleUtils.extractContents(bundle).setupInfo;
            if (suggestParcelables$SetupInfo == null) {
                LogUtils.e("System intelligence is unavailable.", (Throwable) null);
                return;
            }
            int i = Utils.$r8$clinit;
            if (suggestParcelables$SetupInfo.errorCode == SuggestParcelables$ErrorCode.ERROR_CODE_SUCCESS) {
                Log.i("AiAiSuggestUi", "Successfully connected to system intelligence: ");
                return;
            }
            LogUtils.e("Unable to connect to system intelligence: " + suggestParcelables$SetupInfo.errorMesssage, (Throwable) null);
        } catch (RuntimeException e) {
            LogUtils.e("Unable to connect to system intelligence module.", e);
        }
    }
}
