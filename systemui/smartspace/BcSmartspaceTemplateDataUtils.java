package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceUtils;
import android.app.smartspace.uitemplatedata.Icon;
import android.app.smartspace.uitemplatedata.Text;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BcSmartspaceTemplateDataUtils {
    public static void offsetTextViewForIcon(TextView textView, DoubleShadowIconDrawable doubleShadowIconDrawable, boolean z) {
        int i;
        if (doubleShadowIconDrawable == null) {
            textView.setTranslationX(0.0f);
            return;
        }
        if (z) {
            i = 1;
        } else {
            i = -1;
        }
        textView.setTranslationX((float) (i * doubleShadowIconDrawable.mIconInsetSize));
    }

    public static void setIcon(ImageView imageView, Icon icon) {
        if (imageView == null) {
            Log.w("BcSmartspaceTemplateDataUtils", "Cannot set. The image view is null");
            return;
        }
        if (icon == null) {
            Log.w("BcSmartspaceTemplateDataUtils", "Cannot set. The given icon is null");
            updateVisibility(imageView, 8);
        }
        imageView.setImageIcon(icon.getIcon());
        if (icon.getContentDescription() != null) {
            imageView.setContentDescription(icon.getContentDescription());
        }
    }

    public static void setText(TextView textView, Text text) {
        if (textView == null) {
            Log.w("BcSmartspaceTemplateDataUtils", "Cannot set. The text view is null");
        } else if (SmartspaceUtils.isEmpty(text)) {
            Log.w("BcSmartspaceTemplateDataUtils", "Cannot set. The given text is empty");
            updateVisibility(textView, 8);
        } else {
            textView.setText(text.getText());
            textView.setEllipsize(text.getTruncateAtType());
            textView.setMaxLines(text.getMaxLines());
        }
    }

    public static void updateVisibility(View view, int i) {
        if (view != null && view.getVisibility() != i) {
            view.setVisibility(i);
        }
    }
}
