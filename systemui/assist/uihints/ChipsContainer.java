package com.google.android.systemui.assist.uihints;

import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ChipsContainer extends LinearLayout implements TranscriptionController.TranscriptionSpaceView {
    public final int CHIP_MARGIN;
    public final int START_DELTA;
    public ValueAnimator mAnimator;
    public int mAvailableWidth;
    public final List mChipViews;
    public List mChips;
    public boolean mDarkBackground;

    public ChipsContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    public final ListenableFuture hide(boolean z) {
        if (this.mAnimator.isRunning()) {
            this.mAnimator.cancel();
        }
        removeAllViews();
        setVisibility(8);
        setTranslationY(0.0f);
        return ImmediateFuture.NULL;
    }

    public final void onFontSizeChanged() {
        float dimension = this.mContext.getResources().getDimension(2131165345);
        for (ChipView chipView : this.mChipViews) {
            chipView.mLabelView.setTextSize(0, dimension);
        }
        requestLayout();
    }

    public final void onMeasure(int i, int i2) {
        Display defaultDisplay = DisplayUtils.getDefaultDisplay(getContext());
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        int i3 = point.x;
        if (i3 != this.mAvailableWidth) {
            this.mAvailableWidth = i3;
            setChipsInternal();
        }
        super.onMeasure(i, i2);
    }

    public final void setChipsInternal() {
        ChipView chipView;
        Drawable drawable;
        int i;
        int i2 = this.mAvailableWidth;
        int i3 = 0;
        for (Bundle bundle : this.mChips) {
            if (i3 < ((ArrayList) this.mChipViews).size()) {
                chipView = (ChipView) ((ArrayList) this.mChipViews).get(i3);
            } else {
                chipView = (ChipView) LayoutInflater.from(getContext()).inflate(2131558464, this, false);
                this.mChipViews.add(chipView);
            }
            chipView.getClass();
            Icon icon = (Icon) bundle.getParcelable("icon");
            String string = bundle.getString("label");
            if (icon == null && (string == null || string.length() == 0)) {
                Log.w("ChipView", "Neither icon nor label provided; ignoring chip");
            } else {
                if (icon == null) {
                    chipView.mIconView.setVisibility(8);
                    chipView.mSpaceView.setVisibility(8);
                    chipView.mLabelView.setText(string);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) chipView.mLabelView.getLayoutParams();
                    int i4 = layoutParams.rightMargin;
                    layoutParams.setMargins(i4, layoutParams.topMargin, i4, layoutParams.bottomMargin);
                } else if (string == null || string.length() == 0) {
                    chipView.mLabelView.setVisibility(8);
                    chipView.mSpaceView.setVisibility(8);
                    chipView.mIconView.setImageIcon(icon);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) chipView.mIconView.getLayoutParams();
                    int i5 = layoutParams2.leftMargin;
                    layoutParams2.setMargins(i5, layoutParams2.topMargin, i5, layoutParams2.bottomMargin);
                } else {
                    chipView.mIconView.setImageIcon(icon);
                    chipView.mLabelView.setText(string);
                }
                if (bundle.getParcelable("tap_action") == null) {
                    Log.w("ChipView", "No tap action provided; ignoring chip");
                } else {
                    chipView.setOnClickListener(new ChipView$$ExternalSyntheticLambda0((PendingIntent) bundle.getParcelable("tap_action")));
                    boolean z = this.mDarkBackground;
                    LinearLayout linearLayout = chipView.mChip;
                    if (z) {
                        drawable = chipView.BACKGROUND_DARK;
                    } else {
                        drawable = chipView.BACKGROUND_LIGHT;
                    }
                    linearLayout.setBackground(drawable);
                    TextView textView = chipView.mLabelView;
                    if (z) {
                        i = chipView.TEXT_COLOR_DARK;
                    } else {
                        i = chipView.TEXT_COLOR_LIGHT;
                    }
                    textView.setTextColor(i);
                    chipView.measure(0, 0);
                    int measuredWidth = (this.CHIP_MARGIN * 2) + chipView.getMeasuredWidth();
                    if (measuredWidth < i2) {
                        if (chipView.getParent() == null) {
                            chipView.setVisibility(0);
                            addView(chipView);
                        }
                        i2 -= measuredWidth;
                        i3++;
                    }
                }
            }
        }
        if (i3 < ((ArrayList) this.mChipViews).size()) {
            while (i3 < ((ArrayList) this.mChipViews).size()) {
                ((ChipView) ((ArrayList) this.mChipViews).get(i3)).setVisibility(8);
                i3++;
            }
        }
        requestLayout();
    }

    public final void setHasDarkBackground(boolean z) {
        Drawable drawable;
        int i;
        if (this.mDarkBackground != z) {
            this.mDarkBackground = z;
            for (int i2 = 0; i2 < getChildCount(); i2++) {
                ChipView chipView = (ChipView) getChildAt(i2);
                LinearLayout linearLayout = chipView.mChip;
                if (z) {
                    drawable = chipView.BACKGROUND_DARK;
                } else {
                    drawable = chipView.BACKGROUND_LIGHT;
                }
                linearLayout.setBackground(drawable);
                TextView textView = chipView.mLabelView;
                if (z) {
                    i = chipView.TEXT_COLOR_DARK;
                } else {
                    i = chipView.TEXT_COLOR_LIGHT;
                }
                textView.setTextColor(i);
            }
        }
    }

    public ChipsContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChipsContainer(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ChipsContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mChips = new ArrayList();
        this.mChipViews = new ArrayList();
        this.mAnimator = new ValueAnimator();
        this.CHIP_MARGIN = (int) getResources().getDimension(2131165342);
        this.START_DELTA = (int) getResources().getDimension(2131165351);
    }
}
