package com.google.android.systemui.smartspace.uitemplate;

import android.app.smartspace.uitemplatedata.CarouselTemplateData;
import java.util.function.Predicate;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class CarouselTemplateCard$$ExternalSyntheticLambda0 implements Predicate {
    public final boolean test(Object obj) {
        CarouselTemplateData.CarouselItem carouselItem = (CarouselTemplateData.CarouselItem) obj;
        int i = CarouselTemplateCard.$r8$clinit;
        if (carouselItem.getImage() == null || carouselItem.getLowerText() == null || carouselItem.getUpperText() == null) {
            return false;
        }
        return true;
    }
}
