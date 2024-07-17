package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class PromptView extends TextView {
    public final DecelerateInterpolator mDecelerateInterpolator;
    public boolean mEnabled;
    public String mHandleString;
    public boolean mHasDarkBackground;
    public final Configuration mLastConfig;
    public int mLastInvocationType;
    public final float mRiseDistance;
    public String mSqueezeString;
    public final int mTextColorDark;
    public final int mTextColorLight;

    public PromptView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        boolean z;
        super.onConfigurationChanged(configuration);
        this.mHandleString = getResources().getString(2131952628);
        this.mSqueezeString = getResources().getString(2131953873);
        int updateFrom = this.mLastConfig.updateFrom(configuration);
        boolean z2 = true;
        if ((updateFrom & 4096) != 0) {
            z = true;
        } else {
            z = false;
        }
        if ((updateFrom & 1073741824) == 0) {
            z2 = false;
        }
        if (z || z2) {
            setTextSize(0, this.mContext.getResources().getDimension(2131167746));
            updateViewHeight();
        }
    }

    public final void onFinishInflate() {
        updateViewHeight();
    }

    public final void updateViewHeight() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = (int) (this.mContext.getResources().getDimension(2131167746) + getResources().getDimension(2131165353) + this.mRiseDistance);
        }
        requestLayout();
    }

    public PromptView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PromptView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PromptView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDecelerateInterpolator = new DecelerateInterpolator(2.0f);
        this.mLastConfig = new Configuration();
        this.mHasDarkBackground = false;
        this.mEnabled = false;
        this.mLastInvocationType = 0;
        int color = getContext().getColor(2131100852);
        this.mTextColorDark = color;
        int color2 = getContext().getColor(2131100853);
        this.mTextColorLight = color2;
        this.mRiseDistance = getResources().getDimension(2131165352);
        this.mHandleString = getResources().getString(2131952628);
        this.mSqueezeString = getResources().getString(2131953873);
        boolean z = this.mHasDarkBackground;
        boolean z2 = !z;
        if (z2 != z) {
            setTextColor(!z2 ? color2 : color);
            this.mHasDarkBackground = z2;
        }
    }
}
