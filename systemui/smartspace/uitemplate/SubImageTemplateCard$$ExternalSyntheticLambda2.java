package com.google.android.systemui.smartspace.uitemplate;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
