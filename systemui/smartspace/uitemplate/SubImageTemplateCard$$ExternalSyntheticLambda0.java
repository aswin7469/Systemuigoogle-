package com.google.android.systemui.smartspace.uitemplate;

import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class SubImageTemplateCard$$ExternalSyntheticLambda0 implements Icon.OnDrawableLoadedListener {
    public final /* synthetic */ SubImageTemplateCard f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Map f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ List f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ WeakReference f$7;

    public /* synthetic */ SubImageTemplateCard$$ExternalSyntheticLambda0(SubImageTemplateCard subImageTemplateCard, String str, String str2, Map map, int i, List list, int i2, WeakReference weakReference) {
        this.f$0 = subImageTemplateCard;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = map;
        this.f$4 = i;
        this.f$5 = list;
        this.f$6 = i2;
        this.f$7 = weakReference;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.util.function.Predicate, java.lang.Object] */
    public final void onDrawableLoaded(Drawable drawable) {
        SubImageTemplateCard subImageTemplateCard = this.f$0;
        String str = this.f$1;
        String str2 = this.f$2;
        Map map = this.f$3;
        int i = this.f$4;
        List list = this.f$5;
        int i2 = this.f$6;
        WeakReference weakReference = this.f$7;
        int i3 = SubImageTemplateCard.$r8$clinit;
        if (!str.equals(subImageTemplateCard.mPrevSmartspaceTargetId)) {
            Log.d("SubImageTemplateCard", "SmartspaceTarget has changed. Skip the loaded result...");
            return;
        }
        subImageTemplateCard.mIconDrawableCache.put(str2, drawable);
        map.put(Integer.valueOf(i), drawable);
        if (map.size() == list.size()) {
            AnimationDrawable animationDrawable = new AnimationDrawable();
            List list2 = (List) map.values().stream().filter(new Object()).collect(Collectors.toList());
            if (list2.isEmpty()) {
                Log.w("SubImageTemplateCard", "All images are failed to load. Reset imageView");
                ImageView imageView = subImageTemplateCard.mImageView;
                if (imageView != null) {
                    imageView.getLayoutParams().width = -2;
                    subImageTemplateCard.mImageView.setImageDrawable((Drawable) null);
                    subImageTemplateCard.mImageView.setBackgroundTintList((ColorStateList) null);
                    return;
                }
                return;
            }
            list2.forEach(new SubImageTemplateCard$$ExternalSyntheticLambda2(animationDrawable, i2));
            ImageView imageView2 = (ImageView) weakReference.get();
            imageView2.setImageDrawable(animationDrawable);
            int intrinsicWidth = animationDrawable.getIntrinsicWidth();
            if (imageView2.getLayoutParams().width != intrinsicWidth) {
                Log.d("SubImageTemplateCard", "imageView requestLayout");
                imageView2.getLayoutParams().width = intrinsicWidth;
                imageView2.requestLayout();
            }
            animationDrawable.start();
        }
    }
}
