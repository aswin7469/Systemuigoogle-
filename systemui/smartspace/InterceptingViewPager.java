package com.google.android.systemui.smartspace;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.viewpager.widget.ViewPager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class InterceptingViewPager extends ViewPager {
    public boolean mHasPerformedLongPress;
    public boolean mHasPostedLongPress;
    public final Runnable mLongPressCallback = new InterceptingViewPager$$ExternalSyntheticLambda2(this);
    public final InterceptingViewPager$$ExternalSyntheticLambda0 mSuperOnIntercept = new InterceptingViewPager$$ExternalSyntheticLambda0(this, 1);
    public final InterceptingViewPager$$ExternalSyntheticLambda0 mSuperOnTouch = new InterceptingViewPager$$ExternalSyntheticLambda0(this, 0);

    public InterceptingViewPager(Context context) {
        super(context);
    }

    public final void cancelScheduledLongPress() {
        if (this.mHasPostedLongPress) {
            this.mHasPostedLongPress = false;
            removeCallbacks(this.mLongPressCallback);
        }
    }

    public final boolean handleTouchOverride(MotionEvent motionEvent, InterceptingViewPager$$ExternalSyntheticLambda0 interceptingViewPager$$ExternalSyntheticLambda0) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mHasPerformedLongPress = false;
            if (isLongClickable()) {
                cancelScheduledLongPress();
                this.mHasPostedLongPress = true;
                postDelayed(this.mLongPressCallback, (long) ViewConfiguration.getLongPressTimeout());
            }
        } else if (action == 1 || action == 3) {
            cancelScheduledLongPress();
        }
        if (this.mHasPerformedLongPress) {
            cancelScheduledLongPress();
            return true;
        } else if (!interceptingViewPager$$ExternalSyntheticLambda0.delegateEvent(motionEvent)) {
            return false;
        } else {
            cancelScheduledLongPress();
            return true;
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return handleTouchOverride(motionEvent, this.mSuperOnIntercept);
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return handleTouchOverride(motionEvent, this.mSuperOnTouch);
    }

    public InterceptingViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
