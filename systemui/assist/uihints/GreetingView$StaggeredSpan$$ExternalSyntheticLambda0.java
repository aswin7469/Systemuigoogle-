package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import com.google.android.systemui.assist.uihints.GreetingView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class GreetingView$StaggeredSpan$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ GreetingView.StaggeredSpan f$0;

    public /* synthetic */ GreetingView$StaggeredSpan$$ExternalSyntheticLambda0(GreetingView.StaggeredSpan staggeredSpan, int i) {
        this.$r8$classId = i;
        this.f$0 = staggeredSpan;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.$r8$classId;
        GreetingView.StaggeredSpan staggeredSpan = this.f$0;
        switch (i) {
            case 0:
                staggeredSpan.mShift = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * ((float) GreetingView.this.START_DELTA));
                return;
            default:
                staggeredSpan.getClass();
                staggeredSpan.mAlpha = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * GreetingView.this.mMaxAlpha);
                return;
        }
    }
}
