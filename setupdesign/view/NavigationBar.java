package com.google.android.setupdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class NavigationBar extends LinearLayout implements View.OnClickListener {
    public NavigationBar(Context context) {
        super(getThemedContext(context));
        init();
    }

    public static Context getThemedContext(Context context) {
        int i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{2130970104, 16842800, 16842801});
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId == 0) {
            float[] fArr = new float[3];
            float[] fArr2 = new float[3];
            Color.colorToHSV(obtainStyledAttributes.getColor(1, 0), fArr);
            Color.colorToHSV(obtainStyledAttributes.getColor(2, 0), fArr2);
            if (fArr[2] > fArr2[2]) {
                i = 2132017866;
            } else {
                i = 2132017867;
            }
            resourceId = i;
        }
        obtainStyledAttributes.recycle();
        return new ContextThemeWrapper(context, resourceId);
    }

    public final void init() {
        if (!isInEditMode()) {
            View.inflate(getContext(), 2131559118, this);
            Button button = (Button) findViewById(2131363752);
            Button button2 = (Button) findViewById(2131363750);
            Button button3 = (Button) findViewById(2131363751);
        }
    }

    public NavigationBar(Context context, AttributeSet attributeSet) {
        super(getThemedContext(context), attributeSet);
        init();
    }

    public NavigationBar(Context context, AttributeSet attributeSet, int i) {
        super(getThemedContext(context), attributeSet, i);
        init();
    }

    public final void onClick(View view) {
    }
}
