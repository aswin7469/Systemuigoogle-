package com.google.android.systemui.smartspace;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class InterceptingViewPager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ InterceptingViewPager f$0;

    public /* synthetic */ InterceptingViewPager$$ExternalSyntheticLambda1(InterceptingViewPager interceptingViewPager, int i) {
        this.$r8$classId = i;
        this.f$0 = interceptingViewPager;
    }

    public final void run() {
        int i = this.$r8$classId;
        InterceptingViewPager interceptingViewPager = this.f$0;
        switch (i) {
            case 0:
                interceptingViewPager.mHasPerformedLongPress = true;
                if (interceptingViewPager.performLongClick()) {
                    interceptingViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                    return;
                }
                return;
            default:
                interceptingViewPager.mHasPerformedLongPress = true;
                if (interceptingViewPager.performLongClick()) {
                    interceptingViewPager.getParent().requestDisallowInterceptTouchEvent(true);
                    return;
                }
                return;
        }
    }
}
