package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.app.contentsuggestions.ContentClassification;
import android.app.contentsuggestions.ContentSuggestionsManager;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda0 implements ContentSuggestionsManager.ClassificationsCallback {
    public final /* synthetic */ ContentSuggestionsServiceWrapperImpl.BundleCallbackWrapper f$0;

    public /* synthetic */ ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda0(ContentSuggestionsServiceWrapperImpl.BundleCallbackWrapper bundleCallbackWrapper) {
        this.f$0 = bundleCallbackWrapper;
    }

    public final void onContentClassificationsAvailable(int i, List list) {
        this.f$0.onResult(((ContentClassification) list.get(0)).getExtras());
    }
}
