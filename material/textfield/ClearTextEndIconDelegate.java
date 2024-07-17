package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.animation.AnimationUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ClearTextEndIconDelegate extends EndIconDelegate {
    public EditText editText;
    public AnimatorSet iconInAnim;
    public ValueAnimator iconOutAnim;
    public final ClearTextEndIconDelegate$$ExternalSyntheticLambda1 onFocusChangeListener = new ClearTextEndIconDelegate$$ExternalSyntheticLambda1(this);
    public final ClearTextEndIconDelegate$$ExternalSyntheticLambda0 onIconClickListener = new ClearTextEndIconDelegate$$ExternalSyntheticLambda0(this);

    public ClearTextEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
    }

    public final void afterEditTextChanged() {
        if (this.endLayout.suffixText == null) {
            animateIcon(shouldBeVisible());
        }
    }

    public final void animateIcon(boolean z) {
        boolean z2;
        if (this.endLayout.isEndIconVisible() == z) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && !this.iconInAnim.isRunning()) {
            this.iconOutAnim.cancel();
            this.iconInAnim.start();
            if (z2) {
                this.iconInAnim.end();
            }
        } else if (!z) {
            this.iconInAnim.cancel();
            this.iconOutAnim.start();
            if (z2) {
                this.iconOutAnim.end();
            }
        }
    }

    public final int getIconContentDescriptionResId() {
        return 2131952194;
    }

    public final int getIconDrawableResId() {
        return 2131233299;
    }

    public final View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    public final View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    public final View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    public final void onEditTextAttached(EditText editText2) {
        this.editText = editText2;
        this.textInputLayout.endLayout.setEndIconVisible(shouldBeVisible());
    }

    public final void onSuffixVisibilityChanged(boolean z) {
        if (this.endLayout.suffixText != null) {
            animateIcon(z);
        }
    }

    public final void setUp() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.8f, 1.0f});
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        ofFloat.setDuration(150);
        ofFloat.addUpdateListener(new ClearTextEndIconDelegate$$ExternalSyntheticLambda2(this, 1));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        TimeInterpolator timeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration(100);
        ofFloat2.addUpdateListener(new ClearTextEndIconDelegate$$ExternalSyntheticLambda2(this, 0));
        AnimatorSet animatorSet = new AnimatorSet();
        this.iconInAnim = animatorSet;
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        this.iconInAnim.addListener(new AnimatorListenerAdapter(this, 0) {
            public final /* synthetic */ ClearTextEndIconDelegate this$0;

            {
                this.this$0 = r1;
            }

            public final void onAnimationEnd(Animator animator) {
                switch (1) {
                    case 1:
                        this.this$0.endLayout.setEndIconVisible(false);
                        return;
                    default:
                        super.onAnimationEnd(animator);
                        return;
                }
            }

            public final void onAnimationStart(Animator animator) {
                switch (1) {
                    case 0:
                        this.this$0.endLayout.setEndIconVisible(true);
                        return;
                    default:
                        super.onAnimationStart(animator);
                        return;
                }
            }
        });
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat3.setInterpolator(timeInterpolator);
        ofFloat3.setDuration(100);
        ofFloat3.addUpdateListener(new ClearTextEndIconDelegate$$ExternalSyntheticLambda2(this, 0));
        this.iconOutAnim = ofFloat3;
        ofFloat3.addListener(new AnimatorListenerAdapter(this, 1) {
            public final /* synthetic */ ClearTextEndIconDelegate this$0;

            {
                this.this$0 = r1;
            }

            public final void onAnimationEnd(Animator animator) {
                switch (1) {
                    case 1:
                        this.this$0.endLayout.setEndIconVisible(false);
                        return;
                    default:
                        super.onAnimationEnd(animator);
                        return;
                }
            }

            public final void onAnimationStart(Animator animator) {
                switch (1) {
                    case 0:
                        this.this$0.endLayout.setEndIconVisible(true);
                        return;
                    default:
                        super.onAnimationStart(animator);
                        return;
                }
            }
        });
    }

    public final boolean shouldBeVisible() {
        EditText editText2 = this.editText;
        if (editText2 == null || ((!editText2.hasFocus() && !this.endIconView.hasFocus()) || this.editText.getText().length() <= 0)) {
            return false;
        }
        return true;
    }

    public final void tearDown() {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            editText2.post(new ClearTextEndIconDelegate$$ExternalSyntheticLambda3(this));
        }
    }
}
