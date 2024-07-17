package com.google.android.material.snackbar;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class SnackbarContentLayout extends LinearLayout {
    public TextView messageView;

    public SnackbarContentLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(2131363665);
        Button button = (Button) findViewById(2131363664);
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        boolean z = true;
        if (getOrientation() != 1) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131165775);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131165774);
            Layout layout = this.messageView.getLayout();
            if (layout == null || layout.getLineCount() <= 1) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            if (getOrientation() != 0) {
                setOrientation(0);
            } else {
                z = false;
            }
            if (this.messageView.getPaddingTop() != dimensionPixelSize || this.messageView.getPaddingBottom() != dimensionPixelSize) {
                TextView textView = this.messageView;
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (textView.isPaddingRelative()) {
                    textView.setPaddingRelative(textView.getPaddingStart(), dimensionPixelSize, textView.getPaddingEnd(), dimensionPixelSize);
                } else {
                    textView.setPadding(textView.getPaddingLeft(), dimensionPixelSize, textView.getPaddingRight(), dimensionPixelSize);
                }
            } else if (!z) {
                return;
            }
            super.onMeasure(i, i2);
        }
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
