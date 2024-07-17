package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.Property;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.collection.SimpleArrayMap;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class MotionSpec {
    public final SimpleArrayMap propertyValues = new SimpleArrayMap();
    public final SimpleArrayMap timings = new SimpleArrayMap();

    public static MotionSpec createFromAttribute(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        return createFromResource(resourceId, context);
    }

    public static MotionSpec createFromResource(int i, Context context) {
        try {
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, i);
            if (loadAnimator instanceof AnimatorSet) {
                return createSpecFromAnimators(((AnimatorSet) loadAnimator).getChildAnimations());
            }
            if (loadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(loadAnimator);
            return createSpecFromAnimators(arrayList);
        } catch (Exception e) {
            Log.w("MotionSpec", "Can't load animation resource ID #0x" + Integer.toHexString(i), e);
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [java.lang.Object, com.google.android.material.animation.MotionTiming] */
    public static MotionSpec createSpecFromAnimators(List list) {
        MotionSpec motionSpec = new MotionSpec();
        int size = list.size();
        int i = 0;
        while (i < size) {
            Animator animator = (Animator) list.get(i);
            if (animator instanceof ObjectAnimator) {
                ObjectAnimator objectAnimator = (ObjectAnimator) animator;
                motionSpec.setPropertyValues(objectAnimator.getPropertyName(), objectAnimator.getValues());
                String propertyName = objectAnimator.getPropertyName();
                long startDelay = objectAnimator.getStartDelay();
                long duration = objectAnimator.getDuration();
                TimeInterpolator interpolator = objectAnimator.getInterpolator();
                if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                    interpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
                } else if (interpolator instanceof AccelerateInterpolator) {
                    interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
                } else if (interpolator instanceof DecelerateInterpolator) {
                    interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
                }
                ? obj = new Object();
                obj.repeatCount = 0;
                obj.repeatMode = 1;
                obj.delay = startDelay;
                obj.duration = duration;
                obj.interpolator = interpolator;
                obj.repeatCount = objectAnimator.getRepeatCount();
                obj.repeatMode = objectAnimator.getRepeatMode();
                motionSpec.timings.put(propertyName, obj);
                i++;
            } else {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
        }
        return motionSpec;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotionSpec)) {
            return false;
        }
        return this.timings.equals(((MotionSpec) obj).timings);
    }

    public final ObjectAnimator getAnimator(String str, ExtendedFloatingActionButton extendedFloatingActionButton, Property property) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(extendedFloatingActionButton, getPropertyValues(str));
        ofPropertyValuesHolder.setProperty(property);
        getTiming(str).apply(ofPropertyValuesHolder);
        return ofPropertyValuesHolder;
    }

    public final PropertyValuesHolder[] getPropertyValues(String str) {
        if (hasPropertyValues(str)) {
            PropertyValuesHolder[] propertyValuesHolderArr = (PropertyValuesHolder[]) this.propertyValues.get(str);
            PropertyValuesHolder[] propertyValuesHolderArr2 = new PropertyValuesHolder[propertyValuesHolderArr.length];
            for (int i = 0; i < propertyValuesHolderArr.length; i++) {
                propertyValuesHolderArr2[i] = propertyValuesHolderArr[i].clone();
            }
            return propertyValuesHolderArr2;
        }
        throw new IllegalArgumentException();
    }

    public final MotionTiming getTiming(String str) {
        SimpleArrayMap simpleArrayMap = this.timings;
        if (simpleArrayMap.get(str) != null) {
            return (MotionTiming) simpleArrayMap.get(str);
        }
        throw new IllegalArgumentException();
    }

    public final boolean hasPropertyValues(String str) {
        if (this.propertyValues.get(str) != null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.timings.hashCode();
    }

    public final void setPropertyValues(String str, PropertyValuesHolder[] propertyValuesHolderArr) {
        this.propertyValues.put(str, propertyValuesHolderArr);
    }

    public final String toString() {
        return "\n" + MotionSpec.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.timings + "}\n";
    }
}
