package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class BcSmartspaceCardGenericImage extends BcSmartspaceCardSecondary {
    public ImageView mImageView;

    public BcSmartspaceCardGenericImage(Context context) {
        super(context);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.mImageView = (ImageView) findViewById(2131362735);
    }

    public void resetUi() {
        this.mImageView.setImageBitmap((Bitmap) null);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mImageView.setImageBitmap(bitmap);
    }

    public boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        Bundle bundle;
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        if (baseAction == null) {
            bundle = null;
        } else {
            bundle = baseAction.getExtras();
        }
        if (bundle == null || !bundle.containsKey("imageBitmap")) {
            return false;
        }
        if (bundle.containsKey("imageScaleType")) {
            String string = bundle.getString("imageScaleType");
            try {
                this.mImageView.setScaleType(ImageView.ScaleType.valueOf(string));
            } catch (IllegalArgumentException unused) {
                Log.w("SmartspaceGenericImg", "Invalid imageScaleType value: " + string);
            }
        }
        String dimensionRatio = BcSmartSpaceUtil.getDimensionRatio(bundle);
        if (dimensionRatio != null) {
            ((ConstraintLayout.LayoutParams) this.mImageView.getLayoutParams()).dimensionRatio = dimensionRatio;
        }
        if (bundle.containsKey("imageLayoutWidth")) {
            ((ConstraintLayout.LayoutParams) this.mImageView.getLayoutParams()).width = bundle.getInt("imageLayoutWidth");
        }
        if (bundle.containsKey("imageLayoutHeight")) {
            ((ConstraintLayout.LayoutParams) this.mImageView.getLayoutParams()).height = bundle.getInt("imageLayoutHeight");
        }
        setImageBitmap((Bitmap) bundle.get("imageBitmap"));
        return true;
    }

    public BcSmartspaceCardGenericImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setTextColor(int i) {
    }
}
