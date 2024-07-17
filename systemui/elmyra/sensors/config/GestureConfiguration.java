package com.google.android.systemui.elmyra.sensors.config;

import android.content.Context;
import android.provider.Settings;
import android.util.Range;
import com.android.systemui.DejankUtils;
import com.google.android.systemui.elmyra.UserContentObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GestureConfiguration {
    public static final Range SENSITIVITY_RANGE = Range.create(Float.valueOf(0.0f), Float.valueOf(1.0f));
    public final GestureConfiguration$$ExternalSyntheticLambda0 mAdjustmentCallback = new GestureConfiguration$$ExternalSyntheticLambda0(this, 0);
    public final List mAdjustments;
    public final Context mContext;
    public Listener mListener;
    public float mSensitivity;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface Listener {
        void onGestureConfigurationChanged(GestureConfiguration gestureConfiguration);
    }

    public GestureConfiguration(Context context, Set set) {
        this.mContext = context;
        ArrayList arrayList = new ArrayList(set);
        this.mAdjustments = arrayList;
        arrayList.forEach(new GestureConfiguration$$ExternalSyntheticLambda0(this, 1));
        new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_sensitivity"), new GestureConfiguration$$ExternalSyntheticLambda0(this, 2), true);
        Float f = (Float) DejankUtils.whitelistIpcs((Supplier) new GestureConfiguration$$ExternalSyntheticLambda3(this));
        this.mSensitivity = !SENSITIVITY_RANGE.contains(f) ? 0.5f : f.floatValue();
    }

    public final float getSensitivity() {
        float f = this.mSensitivity;
        int i = 0;
        while (true) {
            List list = this.mAdjustments;
            if (i >= ((ArrayList) list).size()) {
                return f;
            }
            ScreenStateAdjustment screenStateAdjustment = (ScreenStateAdjustment) ((ArrayList) list).get(i);
            if (!screenStateAdjustment.mPowerManager.isInteractive()) {
                f += screenStateAdjustment.mScreenOffAdjustment;
            }
            f = ((Float) SENSITIVITY_RANGE.clamp(Float.valueOf(f))).floatValue();
            i++;
        }
    }
}
