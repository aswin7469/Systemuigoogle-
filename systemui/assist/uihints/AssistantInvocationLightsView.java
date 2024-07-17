package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.MathUtils;
import com.android.systemui.assist.ui.CornerPathRenderer;
import com.android.systemui.assist.ui.InvocationLightsView;
import com.android.systemui.assist.ui.PathSpecCornerPathRenderer;
import com.android.systemui.assist.ui.PerimeterPathGuide;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class AssistantInvocationLightsView extends InvocationLightsView {
    public final int mColorBlue;
    public final int mColorGreen;
    public final int mColorRed;
    public final int mColorYellow;

    public AssistantInvocationLightsView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final CornerPathRenderer createCornerPathRenderer(Context context) {
        return new PathSpecCornerPathRenderer(context);
    }

    public final void onInvocationProgress(float f) {
        if (f <= 1.0f) {
            super.onInvocationProgress(f);
        } else {
            PerimeterPathGuide.RegionAttributes[] regionAttributesArr = this.mGuide.mRegions;
            float f2 = regionAttributesArr[0].normalizedLength / 4.0f;
            float lerp = MathUtils.lerp((regionAttributesArr[7].normalizedLength * 0.6f) / 2.0f, f2, 1.0f - (f - 1.0f));
            setLight(0, f2 - lerp, f2);
            float f3 = 2.0f * f2;
            setLight(1, f2, f3);
            float f4 = f2 * 3.0f;
            setLight(2, f3, f4);
            setLight(3, f4, lerp + f4);
            setVisibility(0);
        }
        invalidate();
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Resources resources = context.getResources();
        this.mColorRed = resources.getColor(2131099881);
        this.mColorYellow = resources.getColor(2131099882);
        this.mColorBlue = resources.getColor(2131099879);
        this.mColorGreen = resources.getColor(2131099880);
    }
}
