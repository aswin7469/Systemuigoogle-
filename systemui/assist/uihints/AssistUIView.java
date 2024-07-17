package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class AssistUIView extends FrameLayout {
    public TouchOutsideHandler mTouchOutside;

    public AssistUIView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        TouchOutsideHandler touchOutsideHandler;
        if (motionEvent.getAction() != 4 || (touchOutsideHandler = this.mTouchOutside) == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        PendingIntent pendingIntent = touchOutsideHandler.mTouchOutside;
        if (pendingIntent == null) {
            return false;
        }
        try {
            pendingIntent.send();
            return false;
        } catch (PendingIntent.CanceledException unused) {
            Log.w("TouchOutsideHandler", "Touch outside PendingIntent canceled");
            return false;
        }
    }

    public AssistUIView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AssistUIView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AssistUIView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setClipChildren(false);
    }
}
