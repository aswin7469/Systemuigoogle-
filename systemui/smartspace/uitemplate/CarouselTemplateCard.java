package com.google.android.systemui.smartspace.uitemplate;

import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.uitemplatedata.CarouselTemplateData;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.BcSmartSpaceUtil;
import com.google.android.systemui.smartspace.BcSmartspaceCardSecondary;
import com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import java.util.List;
import java.util.Locale;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class CarouselTemplateCard extends BcSmartspaceCardSecondary {
    public static final /* synthetic */ int $r8$clinit = 0;

    public CarouselTemplateCard(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        ConstraintLayout constraintLayout;
        ConstraintLayout constraintLayout2;
        super.onFinishInflate();
        ConstraintLayout[] constraintLayoutArr = new ConstraintLayout[4];
        for (int i = 0; i < 4; i++) {
            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewGroup.inflate(getContext(), 2131559029, (ViewGroup) null);
            constraintLayout3.setId(View.generateViewId());
            constraintLayoutArr[i] = constraintLayout3;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(0);
            ConstraintLayout constraintLayout4 = constraintLayoutArr[i2];
            if (i2 > 0) {
                constraintLayout = constraintLayoutArr[i2 - 1];
            } else {
                constraintLayout = null;
            }
            if (i2 < 3) {
                constraintLayout2 = constraintLayoutArr[i2 + 1];
            } else {
                constraintLayout2 = null;
            }
            if (i2 == 0) {
                layoutParams.startToStart = 0;
                layoutParams.horizontalChainStyle = 1;
            } else {
                layoutParams.startToEnd = constraintLayout.getId();
            }
            if (i2 == 3) {
                layoutParams.endToEnd = 0;
            } else {
                layoutParams.endToStart = constraintLayout2.getId();
            }
            layoutParams.topToTop = 0;
            layoutParams.bottomToBottom = 0;
            addView(constraintLayout4, layoutParams);
        }
    }

    public final void resetUi() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            BcSmartspaceTemplateDataUtils.updateVisibility(childAt.findViewById(2131363955), 8);
            BcSmartspaceTemplateDataUtils.updateVisibility(childAt.findViewById(2131362691), 8);
            BcSmartspaceTemplateDataUtils.updateVisibility(childAt.findViewById(2131362920), 8);
        }
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [java.util.function.Predicate, java.lang.Object] */
    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        int i;
        int i2;
        CarouselTemplateData templateData = smartspaceTarget.getTemplateData();
        if (!BcSmartspaceCardLoggerUtil.containsValidTemplateType(templateData) || templateData.getCarouselItems() == null) {
            Log.w("CarouselTemplateCard", "CarouselTemplateData is null or has no CarouselItem or invalid template type");
            return false;
        }
        List carouselItems = templateData.getCarouselItems();
        int intExact = Math.toIntExact(carouselItems.stream().filter(new Object()).count());
        if (intExact < 4) {
            Locale locale = Locale.US;
            int i3 = 4 - intExact;
            Log.w("CarouselTemplateCard", "Hiding " + i3 + " incomplete column(s).");
            int i4 = 3 - i3;
            for (int i5 = 0; i5 < 4; i5++) {
                View childAt = getChildAt(i5);
                if (i5 <= i4) {
                    i2 = 0;
                } else {
                    i2 = 8;
                }
                BcSmartspaceTemplateDataUtils.updateVisibility(childAt, i2);
            }
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((ConstraintLayout) getChildAt(0)).getLayoutParams();
            if (i3 == 0) {
                i = 1;
            } else {
                i = 0;
            }
            layoutParams.horizontalChainStyle = i;
        }
        for (int i6 = 0; i6 < intExact; i6++) {
            TextView textView = (TextView) getChildAt(i6).findViewById(2131363955);
            ImageView imageView = (ImageView) getChildAt(i6).findViewById(2131362691);
            TextView textView2 = (TextView) getChildAt(i6).findViewById(2131362920);
            BcSmartspaceTemplateDataUtils.setText(textView, ((CarouselTemplateData.CarouselItem) carouselItems.get(i6)).getUpperText());
            BcSmartspaceTemplateDataUtils.updateVisibility(textView, 0);
            BcSmartspaceTemplateDataUtils.setIcon(imageView, ((CarouselTemplateData.CarouselItem) carouselItems.get(i6)).getImage());
            BcSmartspaceTemplateDataUtils.updateVisibility(imageView, 0);
            BcSmartspaceTemplateDataUtils.setText(textView2, ((CarouselTemplateData.CarouselItem) carouselItems.get(i6)).getLowerText());
            BcSmartspaceTemplateDataUtils.updateVisibility(textView2, 0);
        }
        if (templateData.getCarouselAction() != null) {
            BcSmartSpaceUtil.setOnClickListener((View) this, smartspaceTarget, templateData.getCarouselAction(), smartspaceEventNotifier, "CarouselTemplateCard", bcSmartspaceCardLoggingInfo);
        }
        for (CarouselTemplateData.CarouselItem carouselItem : templateData.getCarouselItems()) {
            if (carouselItem.getTapAction() != null) {
                BcSmartSpaceUtil.setOnClickListener((View) this, smartspaceTarget, carouselItem.getTapAction(), smartspaceEventNotifier, "CarouselTemplateCard", bcSmartspaceCardLoggingInfo);
            }
        }
        return true;
    }

    public final void setTextColor(int i) {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            ((TextView) getChildAt(i2).findViewById(2131363955)).setTextColor(i);
            ((TextView) getChildAt(i2).findViewById(2131362920)).setTextColor(i);
        }
    }

    public CarouselTemplateCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
