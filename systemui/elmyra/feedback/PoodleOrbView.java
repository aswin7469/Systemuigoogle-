package com.google.android.systemui.elmyra.feedback;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class PoodleOrbView extends FrameLayout implements Animator.AnimatorListener, FeedbackEffect {
    public final ArrayList mAnimations = new ArrayList();
    public View mBackground;
    public View mBlue;
    public int mFeedbackHeight;
    public View mGreen;
    public View mRed;
    public int mState = 0;
    public View mYellow;

    public PoodleOrbView(Context context) {
        super(context);
    }

    public final ObjectAnimator[] createDotAnimator(View view, float f, Path path) {
        View view2 = view;
        Keyframe[] keyframeArr = {Keyframe.ofFloat(0.0f, view.getScaleX()), Keyframe.ofFloat(0.75f, view.getScaleX()), Keyframe.ofFloat(0.95f, 0.3f), Keyframe.ofFloat(1.0f, 0.0f)};
        Keyframe[] keyframeArr2 = {Keyframe.ofFloat(0.0f, 1.0f), Keyframe.ofFloat(0.75f, 1.0f), Keyframe.ofFloat(0.95f, 0.25f), Keyframe.ofFloat(1.0f, 0.0f)};
        float[] approximate = path.approximate(0.5f);
        Keyframe[] keyframeArr3 = new Keyframe[(approximate.length / 3)];
        Keyframe[] keyframeArr4 = new Keyframe[(approximate.length / 3)];
        int i = 0;
        int i2 = 0;
        while (i < approximate.length) {
            float f2 = (approximate[i] * 0.25f) + 0.75f;
            int i3 = i + 2;
            keyframeArr3[i2] = Keyframe.ofFloat(f2, approximate[i + 1]);
            i += 3;
            keyframeArr4[i2] = Keyframe.ofFloat(f2, approximate[i3]);
            i2++;
        }
        Keyframe[][] keyframeArr5 = {keyframeArr3, keyframeArr4};
        Keyframe[] keyframeArr6 = new Keyframe[(keyframeArr5[0].length + 2)];
        keyframeArr6[0] = Keyframe.ofFloat(0.0f, view.getTranslationX());
        keyframeArr6[1] = Keyframe.ofFloat(0.75f, view.getTranslationX());
        Keyframe[] keyframeArr7 = keyframeArr5[0];
        System.arraycopy(keyframeArr7, 0, keyframeArr6, 2, keyframeArr7.length);
        Keyframe[] keyframeArr8 = new Keyframe[(keyframeArr5[1].length + 3)];
        keyframeArr8[0] = Keyframe.ofFloat(0.0f, view.getTranslationY());
        keyframeArr8[1] = Keyframe.ofFloat(f, view.getTranslationY());
        keyframeArr8[2] = Keyframe.ofFloat(0.75f, view.getTranslationY() - ((float) this.mFeedbackHeight));
        Keyframe[] keyframeArr9 = keyframeArr5[1];
        System.arraycopy(keyframeArr9, 0, keyframeArr8, 3, keyframeArr9.length);
        keyframeArr8[2].setInterpolator(new OvershootInterpolator());
        ObjectAnimator[] objectAnimatorArr = {ObjectAnimator.ofPropertyValuesHolder(view2, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.SCALE_X, keyframeArr)}), ObjectAnimator.ofPropertyValuesHolder(view2, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.SCALE_Y, keyframeArr)}), ObjectAnimator.ofPropertyValuesHolder(view2, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, keyframeArr6)}), ObjectAnimator.ofPropertyValuesHolder(view2, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y, keyframeArr8)}), ObjectAnimator.ofPropertyValuesHolder(view2, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.ALPHA, keyframeArr2)})};
        for (int i4 = 0; i4 < 5; i4++) {
            objectAnimatorArr[i4].setDuration(1000);
        }
        return objectAnimatorArr;
    }

    public final void onAnimationEnd(Animator animator) {
        this.mState = 0;
        onProgress(0, 0.0f);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mBackground = findViewById(2131362490);
        this.mBlue = findViewById(2131362491);
        this.mGreen = findViewById(2131362492);
        this.mRed = findViewById(2131362493);
        this.mYellow = findViewById(2131362495);
        this.mFeedbackHeight = getResources().getDimensionPixelSize(2131167110);
        this.mBackground.setScaleX(0.0f);
        this.mBackground.setScaleY(0.0f);
        View view = this.mBlue;
        view.setTranslationY(view.getTranslationY() + ((float) this.mFeedbackHeight));
        View view2 = this.mGreen;
        view2.setTranslationY(view2.getTranslationY() + ((float) this.mFeedbackHeight));
        View view3 = this.mRed;
        view3.setTranslationY(view3.getTranslationY() + ((float) this.mFeedbackHeight));
        View view4 = this.mYellow;
        view4.setTranslationY(view4.getTranslationY() + ((float) this.mFeedbackHeight));
        ArrayList arrayList = this.mAnimations;
        View view5 = this.mBackground;
        Keyframe[] keyframeArr = {Keyframe.ofFloat(0.0f, 0.0f), Keyframe.ofFloat(0.375f, 1.2f), Keyframe.ofFloat(0.75f, 1.2f), Keyframe.ofFloat(0.95f, 0.2f), Keyframe.ofFloat(1.0f, 0.0f)};
        keyframeArr[1].setInterpolator(new OvershootInterpolator());
        ObjectAnimator[] objectAnimatorArr = {ObjectAnimator.ofPropertyValuesHolder(view5, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.SCALE_X, keyframeArr)}), ObjectAnimator.ofPropertyValuesHolder(view5, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.SCALE_Y, keyframeArr)}), ObjectAnimator.ofPropertyValuesHolder(view5, new PropertyValuesHolder[]{PropertyValuesHolder.ofKeyframe(View.TRANSLATION_Y, new Keyframe[]{Keyframe.ofFloat(0.0f, view5.getTranslationY()), Keyframe.ofFloat(0.375f, px(27.5f)), Keyframe.ofFloat(0.75f, px(27.5f)), Keyframe.ofFloat(0.95f, px(21.75f))})})};
        for (int i = 0; i < 3; i++) {
            objectAnimatorArr[i].setDuration(1000);
        }
        arrayList.addAll(Arrays.asList(objectAnimatorArr));
        ((ValueAnimator) this.mAnimations.get(0)).addListener(this);
        Path path = new Path();
        path.moveTo(this.mBlue.getTranslationX(), this.mBlue.getTranslationY() - ((float) this.mFeedbackHeight));
        path.cubicTo(px(-32.5f), px(-27.5f), px(15.0f), px(-33.75f), px(-2.5f), px(-20.0f));
        this.mAnimations.addAll(Arrays.asList(createDotAnimator(this.mBlue, 0.0f, path)));
        Path path2 = new Path();
        path2.moveTo(this.mRed.getTranslationX(), this.mRed.getTranslationY() - ((float) this.mFeedbackHeight));
        path2.cubicTo(px(-25.0f), px(-17.5f), px(-20.0f), px(-27.5f), px(2.5f), px(-20.0f));
        this.mAnimations.addAll(Arrays.asList(createDotAnimator(this.mRed, 0.05f, path2)));
        Path path3 = new Path();
        path3.moveTo(this.mYellow.getTranslationX(), this.mYellow.getTranslationY() - ((float) this.mFeedbackHeight));
        path3.cubicTo(px(21.25f), px(-33.75f), px(15.0f), px(-27.5f), px(0.0f), px(-20.0f));
        this.mAnimations.addAll(Arrays.asList(createDotAnimator(this.mYellow, 0.1f, path3)));
        Path path4 = new Path();
        path4.moveTo(this.mGreen.getTranslationX(), this.mGreen.getTranslationY() - ((float) this.mFeedbackHeight));
        path4.cubicTo(px(-27.5f), px(-20.0f), px(35.0f), px(-30.0f), px(0.0f), px(-20.0f));
        this.mAnimations.addAll(Arrays.asList(createDotAnimator(this.mGreen, 0.2f, path4)));
    }

    public final void onProgress(int i, float f) {
        if (this.mState != 3) {
            float f2 = (0.75f * f) + 0.0f;
            Iterator it = this.mAnimations.iterator();
            while (it.hasNext()) {
                ValueAnimator valueAnimator = (ValueAnimator) it.next();
                valueAnimator.cancel();
                valueAnimator.setCurrentFraction(f2);
            }
            if (f == 0.0f) {
                this.mState = 0;
            } else if (f == 1.0f) {
                this.mState = 2;
            } else {
                this.mState = 1;
            }
        }
    }

    public final void onRelease() {
        int i = this.mState;
        if (i == 2 || i == 1) {
            Iterator it = this.mAnimations.iterator();
            while (it.hasNext()) {
                ((ValueAnimator) it.next()).reverse();
            }
            this.mState = 0;
        }
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        Iterator it = this.mAnimations.iterator();
        while (it.hasNext()) {
            ((ValueAnimator) it.next()).start();
        }
        this.mState = 3;
    }

    public final float px(float f) {
        return TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    public PoodleOrbView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PoodleOrbView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public PoodleOrbView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public final void onAnimationCancel(Animator animator) {
    }

    public final void onAnimationRepeat(Animator animator) {
    }

    public final void onAnimationStart(Animator animator) {
    }
}
