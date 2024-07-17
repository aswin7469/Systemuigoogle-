package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class UrlIndicatorContainerView extends LinearLayout {
    public UrlIndicatorContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        LinearLayout linearLayout = (LinearLayout) LinearLayout.inflate(context, 2131559175, this);
        TextView textView = (TextView) linearLayout.findViewById(2131363960);
        ImageButton imageButton = (ImageButton) linearLayout.findViewById(2131363958);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
