package com.google.android.material.circularreveal;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import com.google.android.material.circularreveal.CircularRevealHelper;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface CircularRevealWidget extends CircularRevealHelper.Delegate {

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CircularRevealEvaluator implements TypeEvaluator {
        public static final CircularRevealEvaluator CIRCULAR_REVEAL = new CircularRevealEvaluator();
        public final RevealInfo revealInfo = new Object();

        public final Object evaluate(float f, Object obj, Object obj2) {
            RevealInfo revealInfo2 = (RevealInfo) obj;
            RevealInfo revealInfo3 = (RevealInfo) obj2;
            RevealInfo revealInfo4 = this.revealInfo;
            float f2 = revealInfo2.centerX;
            float f3 = 1.0f - f;
            float f4 = (revealInfo3.centerX * f) + (f2 * f3);
            float f5 = revealInfo2.centerY;
            float f6 = revealInfo3.centerY * f;
            float f7 = revealInfo2.radius;
            float f8 = f * revealInfo3.radius;
            revealInfo4.centerX = f4;
            revealInfo4.centerY = f6 + (f5 * f3);
            revealInfo4.radius = f8 + (f3 * f7);
            return revealInfo4;
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CircularRevealProperty extends Property {
        public static final CircularRevealProperty CIRCULAR_REVEAL = new Property(RevealInfo.class, "circularReveal");

        public final Object get(Object obj) {
            return ((CircularRevealWidget) obj).getRevealInfo();
        }

        public final void set(Object obj, Object obj2) {
            ((CircularRevealWidget) obj).setRevealInfo((RevealInfo) obj2);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class CircularRevealScrimColorProperty extends Property {
        public static final CircularRevealScrimColorProperty CIRCULAR_REVEAL_SCRIM_COLOR = new Property(Integer.class, "circularRevealScrimColor");

        public final Object get(Object obj) {
            return Integer.valueOf(((CircularRevealWidget) obj).getCircularRevealScrimColor());
        }

        public final void set(Object obj, Object obj2) {
            ((CircularRevealWidget) obj).setCircularRevealScrimColor(((Integer) obj2).intValue());
        }
    }

    void buildCircularRevealCache();

    void destroyCircularRevealCache();

    int getCircularRevealScrimColor();

    RevealInfo getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(RevealInfo revealInfo);

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class RevealInfo {
        public float centerX;
        public float centerY;
        public float radius;

        public RevealInfo(float f, float f2, float f3) {
            this.centerX = f;
            this.centerY = f2;
            this.radius = f3;
        }

        public RevealInfo(RevealInfo revealInfo) {
            this(revealInfo.centerX, revealInfo.centerY, revealInfo.radius);
        }
    }
}
