package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

@Deprecated
/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class DescriptionItem extends Item {
    public DescriptionItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void onBindView(View view) {
        super.onBindView(view);
        TextView textView = (TextView) view.findViewById(2131363731);
    }
}
