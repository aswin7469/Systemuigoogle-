package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
class SelectionListeningTextView extends TextView {
    public SelectionListeningTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        setTextIsSelectable(true);
    }

    public final void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
    }
}
