package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.resources.MaterialResources;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class IndicatorViewController {
    public Animator captionAnimator;
    public FrameLayout captionArea;
    public int captionDisplayed;
    public int captionToShow;
    public final float captionTranslationYPx;
    public final Context context;
    public boolean errorEnabled;
    public CharSequence errorText;
    public int errorTextAppearance;
    public AppCompatTextView errorView;
    public CharSequence errorViewContentDescription;
    public ColorStateList errorViewTextColor;
    public CharSequence helperText;
    public boolean helperTextEnabled;
    public int helperTextTextAppearance;
    public AppCompatTextView helperTextView;
    public ColorStateList helperTextViewTextColor;
    public LinearLayout indicatorArea;
    public int indicatorsAdded;
    public final TextInputLayout textInputView;

    public IndicatorViewController(TextInputLayout textInputLayout) {
        Context context2 = textInputLayout.getContext();
        this.context = context2;
        this.textInputView = textInputLayout;
        this.captionTranslationYPx = (float) context2.getResources().getDimensionPixelSize(2131165759);
    }

    public final void addIndicator(int i, TextView textView) {
        if (this.indicatorArea == null && this.captionArea == null) {
            Context context2 = this.context;
            LinearLayout linearLayout = new LinearLayout(context2);
            this.indicatorArea = linearLayout;
            linearLayout.setOrientation(0);
            LinearLayout linearLayout2 = this.indicatorArea;
            TextInputLayout textInputLayout = this.textInputView;
            textInputLayout.addView(linearLayout2, -1, -2);
            this.captionArea = new FrameLayout(context2);
            this.indicatorArea.addView(this.captionArea, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (textInputLayout.editText != null) {
                adjustIndicatorPadding();
            }
        }
        if (i == 0 || i == 1) {
            this.captionArea.setVisibility(0);
            this.captionArea.addView(textView);
        } else {
            this.indicatorArea.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.indicatorArea.setVisibility(0);
        this.indicatorsAdded++;
    }

    public final void adjustIndicatorPadding() {
        EditText editText;
        if (this.indicatorArea != null && (editText = this.textInputView.editText) != null) {
            Context context2 = this.context;
            boolean isFontScaleAtLeast1_3 = MaterialResources.isFontScaleAtLeast1_3(context2);
            LinearLayout linearLayout = this.indicatorArea;
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            int paddingStart = ViewCompat.Api17Impl.getPaddingStart(editText);
            if (isFontScaleAtLeast1_3) {
                paddingStart = context2.getResources().getDimensionPixelSize(2131166641);
            }
            int dimensionPixelSize = context2.getResources().getDimensionPixelSize(2131166640);
            if (isFontScaleAtLeast1_3) {
                dimensionPixelSize = context2.getResources().getDimensionPixelSize(2131166642);
            }
            int paddingEnd = ViewCompat.Api17Impl.getPaddingEnd(editText);
            if (isFontScaleAtLeast1_3) {
                paddingEnd = context2.getResources().getDimensionPixelSize(2131166641);
            }
            ViewCompat.Api17Impl.setPaddingRelative(linearLayout, paddingStart, dimensionPixelSize, paddingEnd, 0);
        }
    }

    public final void cancelCaptionAnimator() {
        Animator animator = this.captionAnimator;
        if (animator != null) {
            animator.cancel();
        }
    }

    public final void createCaptionAnimators(List list, boolean z, TextView textView, int i, int i2, int i3) {
        float f;
        if (textView != null && z) {
            if (i == i3 || i == i2) {
                if (i3 == i) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.ALPHA, new float[]{f});
                ofFloat.setDuration(167);
                ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
                list.add(ofFloat);
                if (i3 == i) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, new float[]{-this.captionTranslationYPx, 0.0f});
                    ofFloat2.setDuration(217);
                    ofFloat2.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
                    list.add(ofFloat2);
                }
            }
        }
    }

    public final TextView getCaptionViewFromDisplayState(int i) {
        if (i == 1) {
            return this.errorView;
        }
        if (i != 2) {
            return null;
        }
        return this.helperTextView;
    }

    public final void hideError() {
        this.errorText = null;
        cancelCaptionAnimator();
        if (this.captionDisplayed == 1) {
            if (!this.helperTextEnabled || TextUtils.isEmpty(this.helperText)) {
                this.captionToShow = 0;
            } else {
                this.captionToShow = 2;
            }
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, ""));
    }

    public final void removeIndicator(int i, TextView textView) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.indicatorArea;
        if (linearLayout != null) {
            if ((i == 0 || i == 1) && (frameLayout = this.captionArea) != null) {
                frameLayout.removeView(textView);
            } else {
                linearLayout.removeView(textView);
            }
            int i2 = this.indicatorsAdded - 1;
            this.indicatorsAdded = i2;
            LinearLayout linearLayout2 = this.indicatorArea;
            if (i2 == 0) {
                linearLayout2.setVisibility(8);
            }
        }
    }

    public final boolean shouldAnimateCaptionView(TextView textView, CharSequence charSequence) {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        TextInputLayout textInputLayout = this.textInputView;
        if (!ViewCompat.Api19Impl.isLaidOut(textInputLayout) || !textInputLayout.isEnabled() || (this.captionToShow == this.captionDisplayed && textView != null && TextUtils.equals(textView.getText(), charSequence))) {
            return false;
        }
        return true;
    }

    public final void updateCaptionViewsVisibility(int i, int i2, boolean z) {
        TextView captionViewFromDisplayState;
        TextView captionViewFromDisplayState2;
        int i3 = i;
        int i4 = i2;
        boolean z2 = z;
        if (i3 != i4) {
            if (z2) {
                AnimatorSet animatorSet = new AnimatorSet();
                this.captionAnimator = animatorSet;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = arrayList;
                int i5 = i;
                int i6 = i2;
                createCaptionAnimators(arrayList2, this.helperTextEnabled, this.helperTextView, 2, i5, i6);
                createCaptionAnimators(arrayList2, this.errorEnabled, this.errorView, 1, i5, i6);
                AnimatorSetCompat.playTogether(animatorSet, arrayList);
                final TextView captionViewFromDisplayState3 = getCaptionViewFromDisplayState(i);
                final TextView captionViewFromDisplayState4 = getCaptionViewFromDisplayState(i4);
                final int i7 = i2;
                final int i8 = i;
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    public final void onAnimationEnd(Animator animator) {
                        AppCompatTextView appCompatTextView;
                        IndicatorViewController indicatorViewController = IndicatorViewController.this;
                        indicatorViewController.captionDisplayed = i7;
                        indicatorViewController.captionAnimator = null;
                        TextView textView = captionViewFromDisplayState3;
                        if (textView != null) {
                            textView.setVisibility(4);
                            if (i8 == 1 && (appCompatTextView = IndicatorViewController.this.errorView) != null) {
                                appCompatTextView.setText((CharSequence) null);
                            }
                        }
                        TextView textView2 = captionViewFromDisplayState4;
                        if (textView2 != null) {
                            textView2.setTranslationY(0.0f);
                            captionViewFromDisplayState4.setAlpha(1.0f);
                        }
                    }

                    public final void onAnimationStart(Animator animator) {
                        TextView textView = captionViewFromDisplayState4;
                        if (textView != null) {
                            textView.setVisibility(0);
                        }
                    }
                });
                animatorSet.start();
            } else if (i3 != i4) {
                if (!(i4 == 0 || (captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i4)) == null)) {
                    captionViewFromDisplayState2.setVisibility(0);
                    captionViewFromDisplayState2.setAlpha(1.0f);
                }
                if (!(i3 == 0 || (captionViewFromDisplayState = getCaptionViewFromDisplayState(i)) == null)) {
                    captionViewFromDisplayState.setVisibility(4);
                    if (i3 == 1) {
                        captionViewFromDisplayState.setText((CharSequence) null);
                    }
                }
                this.captionDisplayed = i4;
            }
            TextInputLayout textInputLayout = this.textInputView;
            textInputLayout.updateEditTextBackground();
            textInputLayout.updateLabelState(z2, false);
            textInputLayout.updateTextInputBoxState();
        }
    }
}
