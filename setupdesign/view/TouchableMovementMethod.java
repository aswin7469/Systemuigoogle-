package com.google.android.setupdesign.view;

import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public interface TouchableMovementMethod {

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class TouchableLinkMovementMethod extends LinkMovementMethod implements TouchableMovementMethod {
        public MotionEvent lastEvent;
        public boolean lastEventResult;

        public final boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            boolean z;
            this.lastEvent = motionEvent;
            boolean onTouchEvent = super.onTouchEvent(textView, spannable, motionEvent);
            if (motionEvent.getAction() == 0) {
                if (Selection.getSelectionStart(spannable) != -1) {
                    z = true;
                } else {
                    z = false;
                }
                this.lastEventResult = z;
            } else {
                this.lastEventResult = onTouchEvent;
            }
            return onTouchEvent;
        }
    }
}
