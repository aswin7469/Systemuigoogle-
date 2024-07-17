package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import java.util.Locale;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BcSmartspaceCardWeatherForecast extends BcSmartspaceCardSecondary {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ItemUpdateFunction {
        void update(View view, int i);
    }

    public BcSmartspaceCardWeatherForecast(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        ConstraintLayout constraintLayout;
        ConstraintLayout constraintLayout2;
        super.onFinishInflate();
        ConstraintLayout[] constraintLayoutArr = new ConstraintLayout[4];
        for (int i = 0; i < 4; i++) {
            ConstraintLayout constraintLayout3 = (ConstraintLayout) ViewGroup.inflate(getContext(), 2131559028, (ViewGroup) null);
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

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        Bundle bundle;
        boolean z;
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        if (baseAction == null) {
            bundle = null;
        } else {
            bundle = baseAction.getExtras();
        }
        if (bundle == null) {
            return false;
        }
        if (bundle.containsKey("temperatureValues")) {
            String[] stringArray = bundle.getStringArray("temperatureValues");
            if (stringArray == null) {
                Log.w("BcSmartspaceCardWeatherForecast", "Temperature values array is null.");
            } else {
                updateFields(new BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda0(1, stringArray), stringArray.length, 2131363799, "temperature value");
            }
            z = true;
        } else {
            z = false;
        }
        if (bundle.containsKey("weatherIcons")) {
            Bitmap[] bitmapArr = (Bitmap[]) bundle.get("weatherIcons");
            if (bitmapArr == null) {
                Log.w("BcSmartspaceCardWeatherForecast", "Weather icons array is null.");
            } else {
                updateFields(new BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda0(2, bitmapArr), bitmapArr.length, 2131364061, "weather icon");
            }
            z = true;
        }
        if (!bundle.containsKey("timestamps")) {
            return z;
        }
        String[] stringArray2 = bundle.getStringArray("timestamps");
        if (stringArray2 == null) {
            Log.w("BcSmartspaceCardWeatherForecast", "Timestamps array is null.");
        } else {
            updateFields(new BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda0(0, stringArray2), stringArray2.length, 2131363832, "timestamp");
        }
        return true;
    }

    public final void setTextColor(int i) {
        updateFields(new BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda1(i, 0), 4, 2131363799, "temperature value");
        updateFields(new BcSmartspaceCardWeatherForecast$$ExternalSyntheticLambda1(i, 1), 4, 2131363832, "timestamp");
    }

    public final void updateFields(ItemUpdateFunction itemUpdateFunction, int i, int i2, String str) {
        int i3;
        int i4;
        if (getChildCount() < 4) {
            Log.w("BcSmartspaceCardWeatherForecast", String.format(Locale.US, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Missing %d ", str, " view(s) to update."), new Object[]{Integer.valueOf(4 - getChildCount())}));
            return;
        }
        int i5 = 0;
        if (i < 4) {
            int i6 = 4 - i;
            Log.w("BcSmartspaceCardWeatherForecast", String.format(Locale.US, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Missing %d ", str, "(s). Hiding incomplete columns."), new Object[]{Integer.valueOf(i6)}));
            if (getChildCount() < 4) {
                Log.w("BcSmartspaceCardWeatherForecast", "Missing " + (4 - getChildCount()) + " columns to update.");
            } else {
                int i7 = 3 - i6;
                for (int i8 = 0; i8 < 4; i8++) {
                    View childAt = getChildAt(i8);
                    if (i8 <= i7) {
                        i4 = 0;
                    } else {
                        i4 = 8;
                    }
                    BcSmartspaceTemplateDataUtils.updateVisibility(childAt, i4);
                }
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((ConstraintLayout) getChildAt(0)).getLayoutParams();
                if (i6 == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                layoutParams.horizontalChainStyle = i3;
            }
        }
        int min = Math.min(4, i);
        while (i5 < min) {
            View findViewById = getChildAt(i5).findViewById(i2);
            if (findViewById == null) {
                Log.w("BcSmartspaceCardWeatherForecast", String.format(Locale.US, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Missing ", str, " view to update at column: %d."), new Object[]{Integer.valueOf(i5 + 1)}));
                return;
            } else {
                itemUpdateFunction.update(findViewById, i5);
                i5++;
            }
        }
    }

    public BcSmartspaceCardWeatherForecast(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
