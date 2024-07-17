package com.google.android.systemui.smartspace;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.viewpager.widget.ViewPager;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class InterceptingViewPager extends ViewPager {
    public boolean mHasPerformedLongPress;
    public boolean mHasPostedLongPress;
    public final InterceptingViewPager$$ExternalSyntheticLambda1 mLongPressCallback = new InterceptingViewPager$$ExternalSyntheticLambda1(this, 1);
    public final InterceptingViewPager$$ExternalSyntheticLambda0 mSuperOnIntercept = new InterceptingViewPager$$ExternalSyntheticLambda0(this, 3);
    public final InterceptingViewPager$$ExternalSyntheticLambda0 mSuperOnTouch = new InterceptingViewPager$$ExternalSyntheticLambda0(this, 2);

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
        boolean z;
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
        }
        int i = interceptingViewPager$$ExternalSyntheticLambda0.$r8$classId;
        InterceptingViewPager interceptingViewPager = interceptingViewPager$$ExternalSyntheticLambda0.f$0;
        switch (i) {
            case 0:
            case 2:
                z = super.onTouchEvent(motionEvent);
                break;
            default:
                z = super.onInterceptTouchEvent(motionEvent);
                break;
        }
        if (!z) {
            return false;
        }
        cancelScheduledLongPress();
        return true;
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
