package com.google.android.systemui.smartspace.uitemplate;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class SubImageTemplateCard$$ExternalSyntheticLambda2 implements Consumer {
    public final /* synthetic */ AnimationDrawable f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ SubImageTemplateCard$$ExternalSyntheticLambda2(AnimationDrawable animationDrawable, int i) {
        this.f$0 = animationDrawable;
        this.f$1 = i;
    }

    public final void accept(Object obj) {
        AnimationDrawable animationDrawable = this.f$0;
        int i = this.f$1;
        int i2 = SubImageTemplateCard.$r8$clinit;
        animationDrawable.addFrame((Drawable) obj, i);
    }
}
