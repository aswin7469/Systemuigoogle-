package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.app.contentsuggestions.ContentClassification;
import android.app.contentsuggestions.ContentSuggestionsManager;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda0 implements ContentSuggestionsManager.ClassificationsCallback {
    public final /* synthetic */ ContentSuggestionsServiceWrapperImpl.BundleCallbackWrapper f$0;

    public /* synthetic */ ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda0(ContentSuggestionsServiceWrapperImpl.BundleCallbackWrapper bundleCallbackWrapper) {
        this.f$0 = bundleCallbackWrapper;
    }

    public final void onContentClassificationsAvailable(int i, List list) {
        this.f$0.onResult(((ContentClassification) list.get(0)).getExtras());
    }
}
