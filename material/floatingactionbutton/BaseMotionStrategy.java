package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BaseMotionStrategy {
    public final Context context;
    public MotionSpec defaultMotionSpec;
    public final ExtendedFloatingActionButton fab;
    public final ArrayList listeners = new ArrayList();
    public MotionSpec motionSpec;
    public final AnimatorTracker tracker;

    public BaseMotionStrategy(ExtendedFloatingActionButton extendedFloatingActionButton, AnimatorTracker animatorTracker) {
        this.fab = extendedFloatingActionButton;
        this.context = extendedFloatingActionButton.getContext();
        this.tracker = animatorTracker;
    }

    public AnimatorSet createAnimator() {
        MotionSpec motionSpec2 = this.motionSpec;
        if (motionSpec2 == null) {
            if (this.defaultMotionSpec == null) {
                this.defaultMotionSpec = MotionSpec.createFromResource(getDefaultMotionSpecResource(), this.context);
            }
            motionSpec2 = this.defaultMotionSpec;
            motionSpec2.getClass();
        }
        return createAnimator(motionSpec2);
    }

    public abstract int getDefaultMotionSpecResource();

    public void onAnimationCancel() {
        this.tracker.currentAnimator = null;
    }

    public abstract void onAnimationEnd();

    public abstract void onAnimationStart(Animator animator);

    public abstract void performNow();

    public abstract boolean shouldCancel();

    public final AnimatorSet createAnimator(MotionSpec motionSpec2) {
        ArrayList arrayList = new ArrayList();
        boolean hasPropertyValues = motionSpec2.hasPropertyValues("opacity");
        ExtendedFloatingActionButton extendedFloatingActionButton = this.fab;
        if (hasPropertyValues) {
            arrayList.add(motionSpec2.getAnimator("opacity", extendedFloatingActionButton, View.ALPHA));
        }
        if (motionSpec2.hasPropertyValues("scale")) {
            arrayList.add(motionSpec2.getAnimator("scale", extendedFloatingActionButton, View.SCALE_Y));
            arrayList.add(motionSpec2.getAnimator("scale", extendedFloatingActionButton, View.SCALE_X));
        }
        if (motionSpec2.hasPropertyValues("width")) {
            arrayList.add(motionSpec2.getAnimator("width", extendedFloatingActionButton, ExtendedFloatingActionButton.WIDTH));
        }
        if (motionSpec2.hasPropertyValues("height")) {
            arrayList.add(motionSpec2.getAnimator("height", extendedFloatingActionButton, ExtendedFloatingActionButton.HEIGHT));
        }
        if (motionSpec2.hasPropertyValues("paddingStart")) {
            arrayList.add(motionSpec2.getAnimator("paddingStart", extendedFloatingActionButton, ExtendedFloatingActionButton.PADDING_START));
        }
        if (motionSpec2.hasPropertyValues("paddingEnd")) {
            arrayList.add(motionSpec2.getAnimator("paddingEnd", extendedFloatingActionButton, ExtendedFloatingActionButton.PADDING_END));
        }
        if (motionSpec2.hasPropertyValues("labelOpacity")) {
            arrayList.add(motionSpec2.getAnimator("labelOpacity", extendedFloatingActionButton, new Property() {
                {
                    Class<Float> cls = Float.class;
                }

                public final Object get(Object obj) {
                    ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) obj;
                    return Float.valueOf(AnimationUtils.lerp(0.0f, 1.0f, (((float) Color.alpha(extendedFloatingActionButton.getCurrentTextColor())) / 255.0f) / ((float) Color.alpha(extendedFloatingActionButton.originalTextCsl.getColorForState(extendedFloatingActionButton.getDrawableState(), BaseMotionStrategy.this.fab.originalTextCsl.getDefaultColor())))));
                }

                public final void set(Object obj, Object obj2) {
                    ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) obj;
                    Float f = (Float) obj2;
                    int colorForState = extendedFloatingActionButton.originalTextCsl.getColorForState(extendedFloatingActionButton.getDrawableState(), BaseMotionStrategy.this.fab.originalTextCsl.getDefaultColor());
                    ColorStateList valueOf = ColorStateList.valueOf(Color.argb((int) (AnimationUtils.lerp(0.0f, ((float) Color.alpha(colorForState)) / 255.0f, f.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
                    if (f.floatValue() == 1.0f) {
                        extendedFloatingActionButton.silentlyUpdateTextColor(extendedFloatingActionButton.originalTextCsl);
                    } else {
                        extendedFloatingActionButton.silentlyUpdateTextColor(valueOf);
                    }
                }
            }));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }
}
