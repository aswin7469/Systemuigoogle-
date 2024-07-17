package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    public final int clockHandPadding;
    public final ClockHandView clockHandView;
    public final int clockSize;
    public float currentHandRotation;
    public final int[] gradientColors;
    public final float[] gradientPositions;
    public final int minimumHeight;
    public final int minimumWidth;
    public final RectF scratch;
    public final ColorStateList textColor;
    public final SparseArray textViewPool;
    public final Rect textViewRect;
    public final AnonymousClass2 valueAccessibilityDelegate;
    public final String[] values;

    public ClockFaceView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void findIntersectingTextView() {
        RadialGradient radialGradient;
        RectF rectF = this.clockHandView.selectorBox;
        for (int i = 0; i < this.textViewPool.size(); i++) {
            TextView textView = (TextView) this.textViewPool.get(i);
            if (textView != null) {
                textView.getDrawingRect(this.textViewRect);
                offsetDescendantRectToMyCoords(textView, this.textViewRect);
                textView.setSelected(rectF.contains((float) this.textViewRect.centerX(), (float) this.textViewRect.centerY()));
                this.scratch.set(this.textViewRect);
                this.scratch.offset((float) textView.getPaddingLeft(), (float) textView.getPaddingTop());
                if (!RectF.intersects(rectF, this.scratch)) {
                    radialGradient = null;
                } else {
                    radialGradient = new RadialGradient(rectF.centerX() - this.scratch.left, rectF.centerY() - this.scratch.top, 0.5f * rectF.width(), this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP);
                }
                textView.getPaint().setShader(radialGradient);
                textView.invalidate();
            }
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, this.values.length, 1).mInfo);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        findIntersectingTextView();
    }

    public final void onMeasure(int i, int i2) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int max = (int) (((float) this.clockSize) / Math.max(Math.max(((float) this.minimumHeight) / ((float) displayMetrics.heightPixels), ((float) this.minimumWidth) / ((float) displayMetrics.widthPixels)), 1.0f));
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(max, 1073741824);
        setMeasuredDimension(max, max);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130969545);
    }

    public ClockFaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        SparseArray sparseArray = new SparseArray();
        this.textViewPool = sparseArray;
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClockFaceView, i, 2132018881);
        Resources resources = getResources();
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, 1);
        this.textColor = colorStateList;
        LayoutInflater.from(context).inflate(2131558756, this, true);
        ClockHandView clockHandView2 = (ClockHandView) findViewById(2131362975);
        this.clockHandView = clockHandView2;
        this.clockHandPadding = resources.getDimensionPixelSize(2131166660);
        int colorForState = colorStateList.getColorForState(new int[]{16842913}, colorStateList.getDefaultColor());
        this.gradientColors = new int[]{colorForState, colorForState, colorStateList.getDefaultColor()};
        clockHandView2.listeners.add(this);
        int defaultColor = ContextCompat.getColorStateList(2131100427, context).getDefaultColor();
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, 0);
        setBackgroundColor(colorStateList2 != null ? colorStateList2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public final boolean onPreDraw() {
                if (!ClockFaceView.this.isShown()) {
                    return true;
                }
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ClockFaceView clockFaceView = ClockFaceView.this;
                int height = ((ClockFaceView.this.getHeight() / 2) - clockFaceView.clockHandView.selectorRadius) - clockFaceView.clockHandPadding;
                if (height != clockFaceView.radius) {
                    clockFaceView.radius = height;
                    clockFaceView.updateLayoutParams$1();
                    ClockHandView clockHandView = clockFaceView.clockHandView;
                    clockHandView.circleRadius = clockFaceView.radius;
                    clockHandView.invalidate();
                }
                return true;
            }
        });
        setFocusable(true);
        obtainStyledAttributes.recycle();
        this.valueAccessibilityDelegate = new AccessibilityDelegateCompat() {
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                View.AccessibilityDelegate accessibilityDelegate = this.mOriginalDelegate;
                AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                int intValue = ((Integer) view.getTag(2131362990)).intValue();
                if (intValue > 0) {
                    accessibilityNodeInfo.setTraversalAfter((View) ClockFaceView.this.textViewPool.get(intValue - 1));
                }
                accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(view.isSelected(), 0, 1, intValue, 1));
                accessibilityNodeInfoCompat.setClickable(true);
                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
            }

            public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                if (i != 16) {
                    return super.performAccessibilityAction(view, i, bundle);
                }
                long uptimeMillis = SystemClock.uptimeMillis();
                float x = view.getX() + (((float) view.getWidth()) / 2.0f);
                float height = (((float) view.getHeight()) / 2.0f) + view.getY();
                ClockFaceView clockFaceView = ClockFaceView.this;
                clockFaceView.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, x, height, 0));
                clockFaceView.clockHandView.onTouchEvent(MotionEvent.obtain(uptimeMillis, uptimeMillis, 1, x, height, 0));
                return true;
            }
        };
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        this.values = strArr;
        LayoutInflater from = LayoutInflater.from(getContext());
        int size = sparseArray.size();
        for (int i2 = 0; i2 < Math.max(this.values.length, size); i2++) {
            TextView textView = (TextView) this.textViewPool.get(i2);
            if (i2 >= this.values.length) {
                removeView(textView);
                this.textViewPool.remove(i2);
            } else {
                if (textView == null) {
                    textView = (TextView) from.inflate(2131558755, this, false);
                    this.textViewPool.put(i2, textView);
                    addView(textView);
                }
                textView.setVisibility(0);
                textView.setText(this.values[i2]);
                textView.setTag(2131362990, Integer.valueOf(i2));
                ViewCompat.setAccessibilityDelegate(textView, this.valueAccessibilityDelegate);
                textView.setTextColor(this.textColor);
            }
        }
        this.minimumHeight = resources.getDimensionPixelSize(2131166690);
        this.minimumWidth = resources.getDimensionPixelSize(2131166691);
        this.clockSize = resources.getDimensionPixelSize(2131166667);
    }
}
