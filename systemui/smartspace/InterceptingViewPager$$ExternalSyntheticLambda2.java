package com.google.android.systemui.smartspace;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class InterceptingViewPager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ InterceptingViewPager f$0;

    public /* synthetic */ InterceptingViewPager$$ExternalSyntheticLambda2(InterceptingViewPager interceptingViewPager) {
        this.f$0 = interceptingViewPager;
    }

    public final void run() {
        InterceptingViewPager interceptingViewPager = this.f$0;
        interceptingViewPager.mHasPerformedLongPress = true;
        if (interceptingViewPager.performLongClick()) {
            interceptingViewPager.getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
}
