package com.google.android.systemui.smartspace;

import android.view.MotionEvent;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class InterceptingViewPager$$ExternalSyntheticLambda0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ InterceptingViewPager f$0;

    public /* synthetic */ InterceptingViewPager$$ExternalSyntheticLambda0(InterceptingViewPager interceptingViewPager, int i) {
        this.$r8$classId = i;
        this.f$0 = interceptingViewPager;
    }

    public final boolean delegateEvent(MotionEvent motionEvent) {
        int i = this.$r8$classId;
        InterceptingViewPager interceptingViewPager = this.f$0;
        switch (i) {
            case 0:
                return InterceptingViewPager$$ExternalSyntheticLambda0.super.onTouchEvent(motionEvent);
            default:
                return InterceptingViewPager$$ExternalSyntheticLambda0.super.onInterceptTouchEvent(motionEvent);
        }
    }
}
