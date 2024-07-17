package com.google.android.systemui.assist.uihints;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.MathUtils;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.assist.ui.DisplayUtils;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.assist.ui.PathSpecCornerPathRenderer;
import com.android.systemui.assist.ui.PerimeterPathGuide;
import com.android.systemui.navigationbar.NavigationBar;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationBarTransitions;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class AssistantInvocationLightsView extends View implements NavigationBarTransitions.DarkIntensityListener {
    public final ArrayList mAssistInvocationLights;
    public final int mColorBlue;
    public final int mColorGreen;
    public final int mColorRed;
    public final int mColorYellow;
    public final int mDarkColor;
    public final PerimeterPathGuide mGuide;
    public final int mLightColor;
    public NavigationBarControllerImpl mNavigationBarController;
    public final Paint mPaint;
    public final Path mPath;
    public boolean mRegistered;
    public final int[] mScreenLocation;
    public boolean mUseNavBarColor;
    public final int mViewHeight;

    public AssistantInvocationLightsView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void hide() {
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
        setVisibility(8);
        Iterator it = this.mAssistInvocationLights.iterator();
        while (it.hasNext()) {
            ((EdgeLight) it.next()).setEndpoints(0.0f, 0.0f);
        }
        if (this.mRegistered && (navigationBarControllerImpl = this.mNavigationBarController) != null && (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) != null) {
            defaultNavigationBar.mNavigationBarTransitions.mDarkIntensityListeners.remove(this);
            this.mRegistered = false;
        }
    }

    public final void onDarkIntensity(float f) {
        updateDarkness(f);
    }

    public final void onDraw(Canvas canvas) {
        getLocationOnScreen(this.mScreenLocation);
        int[] iArr = this.mScreenLocation;
        canvas.translate((float) (-iArr[0]), (float) (-iArr[1]));
        if (this.mUseNavBarColor) {
            Iterator it = this.mAssistInvocationLights.iterator();
            while (it.hasNext()) {
                renderLight(canvas, (EdgeLight) it.next());
            }
            return;
        }
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        renderLight(canvas, (EdgeLight) this.mAssistInvocationLights.get(0));
        renderLight(canvas, (EdgeLight) this.mAssistInvocationLights.get(3));
        this.mPaint.setStrokeCap(Paint.Cap.BUTT);
        renderLight(canvas, (EdgeLight) this.mAssistInvocationLights.get(1));
        renderLight(canvas, (EdgeLight) this.mAssistInvocationLights.get(2));
    }

    public final void onFinishInflate() {
        getLayoutParams().height = this.mViewHeight;
        requestLayout();
    }

    public final void onInvocationProgress(float f) {
        NavigationBarControllerImpl navigationBarControllerImpl;
        NavigationBar defaultNavigationBar;
        if (f <= 1.0f) {
            if (f == 0.0f) {
                setVisibility(8);
            } else {
                if (!(this.mRegistered || (navigationBarControllerImpl = this.mNavigationBarController) == null || (defaultNavigationBar = navigationBarControllerImpl.getDefaultNavigationBar()) == null)) {
                    NavigationBarTransitions navigationBarTransitions = defaultNavigationBar.mNavigationBarTransitions;
                    navigationBarTransitions.mDarkIntensityListeners.add(this);
                    updateDarkness(navigationBarTransitions.mLightTransitionsController.mDarkIntensity);
                    this.mRegistered = true;
                }
                PerimeterPathGuide.RegionAttributes[] regionAttributesArr = this.mGuide.mRegions;
                float f2 = regionAttributesArr[7].normalizedLength;
                float f3 = (f2 - (0.6f * f2)) / 2.0f;
                float lerp = MathUtils.lerp(0.0f, regionAttributesArr[0].normalizedLength / 4.0f, f);
                float f4 = 1.0f - f;
                float f5 = ((-f2) + f3) * f4;
                float m = AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f3, f4, this.mGuide.mRegions[0].normalizedLength);
                float f6 = f5 + lerp;
                setLight(0, f5, f6);
                float f7 = 2.0f * lerp;
                setLight(1, f6, f5 + f7);
                float f8 = m - lerp;
                setLight(2, m - f7, f8);
                setLight(3, f8, m);
                setVisibility(0);
            }
            invalidate();
        } else {
            PerimeterPathGuide.RegionAttributes[] regionAttributesArr2 = this.mGuide.mRegions;
            float f9 = regionAttributesArr2[0].normalizedLength / 4.0f;
            float lerp2 = MathUtils.lerp((regionAttributesArr2[7].normalizedLength * 0.6f) / 2.0f, f9, 1.0f - (f - 1.0f));
            setLight(0, f9 - lerp2, f9);
            float f10 = 2.0f * f9;
            setLight(1, f9, f10);
            float f11 = f9 * 3.0f;
            setLight(2, f10, f11);
            setLight(3, f11, lerp2 + f11);
            setVisibility(0);
        }
        invalidate();
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mGuide.setRotation(getContext().getDisplay().getRotation());
    }

    public final void renderLight(Canvas canvas, EdgeLight edgeLight) {
        float f = edgeLight.mLength;
        if (f > 0.0f) {
            PerimeterPathGuide perimeterPathGuide = this.mGuide;
            Path path = this.mPath;
            float f2 = edgeLight.mStart;
            perimeterPathGuide.strokeSegment(path, f2, f + f2);
            this.mPaint.setColor(edgeLight.mColor);
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    public final void setLight(int i, float f, float f2) {
        if (i < 0 || i >= 4) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("invalid invocation light index: ", "InvocationLightsView", i);
        }
        ((EdgeLight) this.mAssistInvocationLights.get(i)).setEndpoints(f, f2);
    }

    public final void updateDarkness(float f) {
        if (this.mUseNavBarColor) {
            int intValue = ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(this.mLightColor), Integer.valueOf(this.mDarkColor))).intValue();
            Iterator it = this.mAssistInvocationLights.iterator();
            boolean z = true;
            while (it.hasNext()) {
                z &= ((EdgeLight) it.next()).setColor(intValue);
            }
            if (z) {
                invalidate();
            }
        }
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public AssistantInvocationLightsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3;
        int i4;
        this.mAssistInvocationLights = new ArrayList();
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mPath = new Path();
        this.mScreenLocation = new int[2];
        this.mRegistered = false;
        this.mUseNavBarColor = true;
        Display display = context.getDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getRealMetrics(displayMetrics);
        int ceil = (int) Math.ceil((double) (displayMetrics.density * 3.0f));
        paint.setStrokeWidth((float) ceil);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setAntiAlias(true);
        Display display2 = context.getDisplay();
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        display2.getRealMetrics(displayMetrics2);
        int rotation = display2.getRotation();
        if (rotation == 0 || rotation == 2) {
            i3 = displayMetrics2.widthPixels;
        } else {
            i3 = displayMetrics2.heightPixels;
        }
        int i5 = i3;
        Display display3 = context.getDisplay();
        DisplayMetrics displayMetrics3 = new DisplayMetrics();
        display3.getRealMetrics(displayMetrics3);
        int rotation2 = display3.getRotation();
        if (rotation2 == 0 || rotation2 == 2) {
            i4 = displayMetrics3.heightPixels;
        } else {
            i4 = displayMetrics3.widthPixels;
        }
        this.mGuide = new PerimeterPathGuide(context, new PathSpecCornerPathRenderer(context), ceil / 2, i5, i4);
        int max = Math.max(DisplayUtils.getCornerRadiusBottom(context), DisplayUtils.getCornerRadiusTop(context));
        Display display4 = context.getDisplay();
        DisplayMetrics displayMetrics4 = new DisplayMetrics();
        display4.getRealMetrics(displayMetrics4);
        this.mViewHeight = Math.max(max, (int) Math.ceil((double) (3.0f * displayMetrics4.density)));
        int themeAttr = Utils.getThemeAttr(2130968957, this.mContext);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, Utils.getThemeAttr(2130969463, this.mContext));
        ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(this.mContext, themeAttr);
        this.mLightColor = Utils.getColorAttrDefaultColor(contextThemeWrapper, 2130969921, 0);
        this.mDarkColor = Utils.getColorAttrDefaultColor(contextThemeWrapper2, 2130969921, 0);
        for (int i6 = 0; i6 < 4; i6++) {
            this.mAssistInvocationLights.add(new EdgeLight(0));
        }
        Resources resources = context.getResources();
        this.mColorRed = resources.getColor(2131099896);
        this.mColorYellow = resources.getColor(2131099897);
        this.mColorBlue = resources.getColor(2131099894);
        this.mColorGreen = resources.getColor(2131099895);
    }
}
