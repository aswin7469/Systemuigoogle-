package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

@Deprecated
/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    public final MotionTiming collapseTiming = new MotionTiming(0);
    public final MotionTiming expandTiming = new MotionTiming(75);

    public FabTransformationScrimBehavior() {
    }

    public final boolean layoutDependsOn(View view, View view2) {
        return view2 instanceof FloatingActionButton;
    }

    public final AnimatorSet onCreateExpandedStateChangeAnimation(View view, final View view2, final boolean z, boolean z2) {
        MotionTiming motionTiming;
        ObjectAnimator objectAnimator;
        ArrayList arrayList = new ArrayList();
        new ArrayList();
        if (z) {
            motionTiming = this.expandTiming;
        } else {
            motionTiming = this.collapseTiming;
        }
        if (z) {
            if (!z2) {
                view2.setAlpha(0.0f);
            }
            objectAnimator = ObjectAnimator.ofFloat(view2, View.ALPHA, new float[]{1.0f});
        } else {
            objectAnimator = ObjectAnimator.ofFloat(view2, View.ALPHA, new float[]{0.0f});
        }
        motionTiming.apply(objectAnimator);
        arrayList.add(objectAnimator);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public final void onAnimationEnd(Animator animator) {
                if (!z) {
                    view2.setVisibility(4);
                }
            }

            public final void onAnimationStart(Animator animator) {
                if (z) {
                    view2.setVisibility(0);
                }
            }
        });
        return animatorSet;
    }

    public final boolean onTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    public FabTransformationScrimBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
