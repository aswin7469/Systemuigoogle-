package com.google.android.systemui.dreams.complication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DreamWeatherComplicationContainerView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final GestureDetector gestureDetector;

    public DreamWeatherComplicationContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        this.gestureDetector = new GestureDetector(context, new DreamWeatherComplicationContainerView$gestureListener$1(this));
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent != null) {
            this.gestureDetector.onTouchEvent(motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
