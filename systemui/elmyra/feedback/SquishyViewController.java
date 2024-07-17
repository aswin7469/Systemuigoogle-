package com.google.android.systemui.elmyra.feedback;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.IRotationWatcher;
import android.view.IWindowManager;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SquishyViewController implements FeedbackEffect {
    public static final Interpolator SQUISH_TRANSLATION_MAP = new PathInterpolator(0.4f, 0.0f, 0.6f, 1.0f);
    public AnimatorSet mAnimatorSet;
    public float mLastPressure;
    public final List mLeftViews = new ArrayList();
    public float mPressure;
    public final List mRightViews = new ArrayList();
    public final AnonymousClass1 mRotationWatcher;
    public int mScreenRotation;
    public final float mSquishTranslationMax;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SpringInterpolator implements Interpolator {
        public final float mBounce;

        public SpringInterpolator(float f) {
            this.mBounce = f;
        }

        public final float getInterpolation(float f) {
            return (float) ((-(Math.cos((double) (f * this.mBounce)) * Math.exp((double) (-(f / 0.31f))))) + 1.0d);
        }
    }

    public SquishyViewController(Context context) {
        AnonymousClass1 r0 = new IRotationWatcher.Stub() {
            public final void onRotationChanged(int i) {
                SquishyViewController.this.mScreenRotation = i;
            }
        };
        this.mSquishTranslationMax = TypedValue.applyDimension(1, 8.0f, context.getResources().getDisplayMetrics());
        try {
            this.mScreenRotation = IWindowManager.Stub.asInterface(ServiceManager.getService("window")).watchRotation(r0, context.getDisplay().getDisplayId());
        } catch (RemoteException e) {
            Log.e("SquishyViewController", "Couldn't get screen rotation or set watcher", e);
            this.mScreenRotation = 0;
        }
    }

    public static AnimatorSet createSpringbackAnimatorSet(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, new float[]{view.getTranslationX(), 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{view.getTranslationY(), 0.0f});
        ofFloat.setDuration(250);
        ofFloat2.setDuration(250);
        float max = Math.max(Math.abs(view.getTranslationX()) / 8.0f, Math.abs(view.getTranslationY()) / 8.0f) * 3.1f;
        ofFloat.setInterpolator(new SpringInterpolator(max));
        ofFloat2.setInterpolator(new SpringInterpolator(max));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setStartDelay(50);
        return animatorSet;
    }

    public final void onProgress(int i, float f) {
        float min = Math.min(f, 1.0f) / 1.0f;
        if (min != 0.0f) {
            this.mPressure = (this.mLastPressure * 0.0f) + (1.0f * min);
        } else {
            this.mPressure = min;
        }
        AnimatorSet animatorSet = this.mAnimatorSet;
        if (animatorSet == null || !animatorSet.isRunning()) {
            if (min - this.mLastPressure < -0.1f) {
                AnimatorSet animatorSet2 = new AnimatorSet();
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    ArrayList arrayList = (ArrayList) this.mLeftViews;
                    if (i3 >= arrayList.size()) {
                        break;
                    }
                    animatorSet2.play(createSpringbackAnimatorSet((View) arrayList.get(i3)));
                    i3++;
                }
                while (true) {
                    ArrayList arrayList2 = (ArrayList) this.mRightViews;
                    if (i2 >= arrayList2.size()) {
                        break;
                    }
                    animatorSet2.play(createSpringbackAnimatorSet((View) arrayList2.get(i2)));
                    i2++;
                }
                this.mAnimatorSet = animatorSet2;
                animatorSet2.start();
            } else {
                translateViews(((PathInterpolator) SQUISH_TRANSLATION_MAP).getInterpolation(this.mPressure) * this.mSquishTranslationMax);
            }
        }
        this.mLastPressure = this.mPressure;
    }

    public final void onRelease() {
        onProgress(0, 0.0f);
    }

    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        onProgress(0, 0.0f);
    }

    public final void setViewTranslation(View view, float f) {
        if (view.isAttachedToWindow()) {
            if (view.getLayoutDirection() == 1) {
                f *= -1.0f;
            }
            int i = this.mScreenRotation;
            if (i != 0) {
                if (i == 1) {
                    view.setTranslationX(0.0f);
                    view.setTranslationY(-f);
                    return;
                } else if (i != 2) {
                    if (i == 3) {
                        view.setTranslationX(0.0f);
                        view.setTranslationY(f);
                        return;
                    }
                    return;
                }
            }
            view.setTranslationX(f);
            view.setTranslationY(0.0f);
        }
    }

    public final void translateViews(float f) {
        int i = 0;
        int i2 = 0;
        while (true) {
            List list = this.mLeftViews;
            if (i2 >= ((ArrayList) list).size()) {
                break;
            }
            setViewTranslation((View) ((ArrayList) list).get(i2), f);
            i2++;
        }
        while (true) {
            List list2 = this.mRightViews;
            if (i < ((ArrayList) list2).size()) {
                setViewTranslation((View) ((ArrayList) list2).get(i), -f);
                i++;
            } else {
                return;
            }
        }
    }
}
