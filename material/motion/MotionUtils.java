package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.TypedValue;
import android.view.animation.AnimationUtils;
import android.view.animation.PathInterpolator;
import androidx.core.graphics.PathParser;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class MotionUtils {
    public static float getLegacyControlPoint(int i, String[] strArr) {
        float parseFloat = Float.parseFloat(strArr[i]);
        if (parseFloat >= 0.0f && parseFloat <= 1.0f) {
            return parseFloat;
        }
        throw new IllegalArgumentException("Motion easing control point value must be between 0 and 1; instead got: " + parseFloat);
    }

    public static boolean isLegacyEasingType(String str, String str2) {
        if (!str.startsWith(str2.concat("(")) || !str.endsWith(")")) {
            return false;
        }
        return true;
    }

    public static int resolveThemeDuration(Context context, int i, int i2) {
        TypedValue resolve = MaterialAttributes.resolve(i, context);
        if (resolve == null || resolve.type != 16) {
            return i2;
        }
        return resolve.data;
    }

    public static TimeInterpolator resolveThemeInterpolator(Context context, int i, FastOutSlowInInterpolator fastOutSlowInInterpolator) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(i, typedValue, true)) {
            return fastOutSlowInInterpolator;
        }
        if (typedValue.type == 3) {
            String valueOf = String.valueOf(typedValue.string);
            if (!isLegacyEasingType(valueOf, "cubic-bezier") && !isLegacyEasingType(valueOf, "path")) {
                return AnimationUtils.loadInterpolator(context, typedValue.resourceId);
            }
            if (isLegacyEasingType(valueOf, "cubic-bezier")) {
                String[] split = valueOf.substring(13, valueOf.length() - 1).split(",");
                if (split.length == 4) {
                    return new PathInterpolator(getLegacyControlPoint(0, split), getLegacyControlPoint(1, split), getLegacyControlPoint(2, split), getLegacyControlPoint(3, split));
                }
                throw new IllegalArgumentException("Motion easing theme attribute must have 4 control points if using bezier curve format; instead got: " + split.length);
            } else if (isLegacyEasingType(valueOf, "path")) {
                return new PathInterpolator(PathParser.createPathFromPathData(valueOf.substring(5, valueOf.length() - 1)));
            } else {
                throw new IllegalArgumentException("Invalid motion easing type: ".concat(valueOf));
            }
        } else {
            throw new IllegalArgumentException("Motion easing theme attribute must be an @interpolator resource for ?attr/motionEasing*Interpolator attributes or a string for ?attr/motionEasing* attributes.");
        }
    }
}
