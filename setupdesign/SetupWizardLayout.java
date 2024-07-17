package com.google.android.setupdesign;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupdesign.template.ProgressBarMixin;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class SetupWizardLayout extends TemplateLayout {

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public boolean isProgressBarShown;

        /* renamed from: com.google.android.setupdesign.SetupWizardLayout$SavedState$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass1 implements Parcelable.Creator {
            /* JADX WARNING: type inference failed for: r1v1, types: [android.view.View$BaseSavedState, com.google.android.setupdesign.SetupWizardLayout$SavedState, java.lang.Object] */
            public final Object createFromParcel(Parcel parcel) {
                ? baseSavedState = new View.BaseSavedState(parcel);
                boolean z = false;
                baseSavedState.isProgressBarShown = false;
                if (parcel.readInt() != 0) {
                    z = true;
                }
                baseSavedState.isProgressBarShown = z;
                return baseSavedState;
            }

            public final Object[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.isProgressBarShown ? 1 : 0);
        }
    }

    /* JADX WARNING: type inference failed for: r7v6, types: [com.google.android.setupcompat.template.Mixin, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v9, types: [android.view.View] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SetupWizardLayout(android.content.Context r7, android.util.AttributeSet r8) {
        /*
            r6 = this;
            r6.<init>(r7, r8)
            boolean r7 = r6.isInEditMode()
            if (r7 == 0) goto L_0x000b
            goto L_0x018d
        L_0x000b:
            com.google.android.setupcompat.template.SystemNavBarMixin r7 = new com.google.android.setupcompat.template.SystemNavBarMixin
            r0 = 0
            r7.<init>(r6, r0)
            java.lang.Class<com.google.android.setupcompat.template.SystemNavBarMixin> r1 = com.google.android.setupcompat.template.SystemNavBarMixin.class
            r6.registerMixin(r1, r7)
            com.google.android.setupdesign.template.HeaderMixin r7 = new com.google.android.setupdesign.template.HeaderMixin
            r1 = 2130970092(0x7f0405ec, float:1.7548884E38)
            r7.<init>(r6, r8, r1)
            java.lang.Class<com.google.android.setupdesign.template.HeaderMixin> r2 = com.google.android.setupdesign.template.HeaderMixin.class
            r6.registerMixin(r2, r7)
            com.google.android.setupdesign.template.DescriptionMixin r7 = new com.google.android.setupdesign.template.DescriptionMixin
            r7.<init>(r6, r8, r1)
            java.lang.Class<com.google.android.setupdesign.template.DescriptionMixin> r2 = com.google.android.setupdesign.template.DescriptionMixin.class
            r6.registerMixin(r2, r7)
            com.google.android.setupdesign.template.ProgressBarMixin r7 = new com.google.android.setupdesign.template.ProgressBarMixin
            r2 = 0
            r7.<init>(r6, r0, r2)
            java.lang.Class<com.google.android.setupdesign.template.ProgressBarMixin> r3 = com.google.android.setupdesign.template.ProgressBarMixin.class
            r6.registerMixin(r3, r7)
            com.google.android.setupdesign.template.NavigationBarMixin r7 = new com.google.android.setupdesign.template.NavigationBarMixin
            r7.<init>()
            java.lang.Class<com.google.android.setupdesign.template.NavigationBarMixin> r3 = com.google.android.setupdesign.template.NavigationBarMixin.class
            r6.registerMixin(r3, r7)
            com.google.android.setupdesign.template.RequireScrollMixin r7 = new com.google.android.setupdesign.template.RequireScrollMixin
            r7.<init>()
            java.lang.Class<com.google.android.setupdesign.template.RequireScrollMixin> r3 = com.google.android.setupdesign.template.RequireScrollMixin.class
            r6.registerMixin(r3, r7)
            r7 = 2131363717(0x7f0a0785, float:1.834725E38)
            android.view.View r7 = r6.findManagedViewById(r7)
            boolean r3 = r7 instanceof android.widget.ScrollView
            if (r3 == 0) goto L_0x005a
            r0 = r7
            android.widget.ScrollView r0 = (android.widget.ScrollView) r0
        L_0x005a:
            if (r0 == 0) goto L_0x0076
            boolean r7 = r0 instanceof com.google.android.setupdesign.view.BottomScrollView
            if (r7 == 0) goto L_0x0063
            com.google.android.setupdesign.view.BottomScrollView r0 = (com.google.android.setupdesign.view.BottomScrollView) r0
            goto L_0x0076
        L_0x0063:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r3 = "Cannot set non-BottomScrollView. Found="
            r7.<init>(r3)
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            java.lang.String r0 = "ScrollViewDelegate"
            android.util.Log.w(r0, r7)
        L_0x0076:
            android.content.Context r7 = r6.getContext()
            int[] r0 = com.google.android.setupdesign.R$styleable.SudSetupWizardLayout
            android.content.res.TypedArray r7 = r7.obtainStyledAttributes(r8, r0, r1, r2)
            android.graphics.drawable.Drawable r8 = r7.getDrawable(r2)
            r0 = 2131363736(0x7f0a0798, float:1.834729E38)
            r1 = 1
            if (r8 == 0) goto L_0x0094
            android.view.View r2 = r6.findManagedViewById(r0)
            if (r2 == 0) goto L_0x00af
            r2.setBackgroundDrawable(r8)
            goto L_0x00af
        L_0x0094:
            android.graphics.drawable.Drawable r8 = r7.getDrawable(r1)
            if (r8 == 0) goto L_0x00af
            boolean r2 = r8 instanceof android.graphics.drawable.BitmapDrawable
            if (r2 == 0) goto L_0x00a6
            r2 = r8
            android.graphics.drawable.BitmapDrawable r2 = (android.graphics.drawable.BitmapDrawable) r2
            android.graphics.Shader$TileMode r3 = android.graphics.Shader.TileMode.REPEAT
            r2.setTileModeXY(r3, r3)
        L_0x00a6:
            android.view.View r2 = r6.findManagedViewById(r0)
            if (r2 == 0) goto L_0x00af
            r2.setBackgroundDrawable(r8)
        L_0x00af:
            r8 = 3
            android.graphics.drawable.Drawable r8 = r7.getDrawable(r8)
            if (r8 == 0) goto L_0x00ce
            android.view.View r2 = r6.findManagedViewById(r0)
            boolean r3 = r2 instanceof com.google.android.setupdesign.view.Illustration
            if (r3 == 0) goto L_0x0132
            com.google.android.setupdesign.view.Illustration r2 = (com.google.android.setupdesign.view.Illustration) r2
            android.graphics.drawable.Drawable r3 = r2.illustration
            if (r8 != r3) goto L_0x00c5
            goto L_0x0132
        L_0x00c5:
            r2.illustration = r8
            r2.invalidate()
            r2.requestLayout()
            goto L_0x0132
        L_0x00ce:
            r8 = 6
            android.graphics.drawable.Drawable r8 = r7.getDrawable(r8)
            r2 = 5
            android.graphics.drawable.Drawable r2 = r7.getDrawable(r2)
            if (r8 == 0) goto L_0x0132
            if (r2 == 0) goto L_0x0132
            android.view.View r3 = r6.findManagedViewById(r0)
            boolean r4 = r3 instanceof com.google.android.setupdesign.view.Illustration
            if (r4 == 0) goto L_0x0132
            com.google.android.setupdesign.view.Illustration r3 = (com.google.android.setupdesign.view.Illustration) r3
            android.content.Context r4 = r6.getContext()
            android.content.res.Resources r4 = r4.getResources()
            r5 = 2131034244(0x7f050084, float:1.7679E38)
            boolean r4 = r4.getBoolean(r5)
            if (r4 == 0) goto L_0x0122
            boolean r4 = r2 instanceof android.graphics.drawable.BitmapDrawable
            if (r4 == 0) goto L_0x0108
            r4 = r2
            android.graphics.drawable.BitmapDrawable r4 = (android.graphics.drawable.BitmapDrawable) r4
            android.graphics.Shader$TileMode r5 = android.graphics.Shader.TileMode.REPEAT
            r4.setTileModeX(r5)
            r5 = 48
            r4.setGravity(r5)
        L_0x0108:
            boolean r4 = r8 instanceof android.graphics.drawable.BitmapDrawable
            if (r4 == 0) goto L_0x0114
            r4 = r8
            android.graphics.drawable.BitmapDrawable r4 = (android.graphics.drawable.BitmapDrawable) r4
            r5 = 51
            r4.setGravity(r5)
        L_0x0114:
            android.graphics.drawable.LayerDrawable r4 = new android.graphics.drawable.LayerDrawable
            android.graphics.drawable.Drawable[] r8 = new android.graphics.drawable.Drawable[]{r2, r8}
            r4.<init>(r8)
            r4.setAutoMirrored(r1)
            r8 = r4
            goto L_0x0125
        L_0x0122:
            r8.setAutoMirrored(r1)
        L_0x0125:
            android.graphics.drawable.Drawable r2 = r3.illustration
            if (r8 != r2) goto L_0x012a
            goto L_0x0132
        L_0x012a:
            r3.illustration = r8
            r3.invalidate()
            r3.requestLayout()
        L_0x0132:
            r8 = 2
            r2 = -1
            int r8 = r7.getDimensionPixelSize(r8, r2)
            if (r8 != r2) goto L_0x0145
            android.content.res.Resources r8 = r6.getResources()
            r2 = 2131167591(0x7f070967, float:1.794946E38)
            int r8 = r8.getDimensionPixelSize(r2)
        L_0x0145:
            android.view.View r2 = r6.findManagedViewById(r0)
            if (r2 == 0) goto L_0x015a
            int r3 = r2.getPaddingLeft()
            int r4 = r2.getPaddingRight()
            int r5 = r2.getPaddingBottom()
            r2.setPadding(r3, r8, r4, r5)
        L_0x015a:
            r8 = 4
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r8 = r7.getFloat(r8, r2)
            int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r2 != 0) goto L_0x0178
            android.util.TypedValue r8 = new android.util.TypedValue
            r8.<init>()
            android.content.res.Resources r2 = r6.getResources()
            r3 = 2131167678(0x7f0709be, float:1.7949636E38)
            r2.getValue(r3, r8, r1)
            float r8 = r8.getFloat()
        L_0x0178:
            android.view.View r6 = r6.findManagedViewById(r0)
            boolean r0 = r6 instanceof com.google.android.setupdesign.view.Illustration
            if (r0 == 0) goto L_0x018a
            com.google.android.setupdesign.view.Illustration r6 = (com.google.android.setupdesign.view.Illustration) r6
            r6.aspectRatio = r8
            r6.invalidate()
            r6.requestLayout()
        L_0x018a:
            r7.recycle()
        L_0x018d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.SetupWizardLayout.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public ViewGroup findContainer(int i) {
        if (i == 0) {
            i = 2131363735;
        }
        return (ViewGroup) findViewById(i);
    }

    public View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        if (i == 0) {
            i = 2131559139;
        }
        return inflateTemplate(layoutInflater, 2132017889, i);
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            Log.w("SetupWizardLayout", "Ignoring restore instance state " + parcelable);
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        ((ProgressBarMixin) getMixin(ProgressBarMixin.class)).setShown(savedState.isProgressBarShown);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.view.View$BaseSavedState, android.os.Parcelable, com.google.android.setupdesign.SetupWizardLayout$SavedState] */
    public final Parcelable onSaveInstanceState() {
        int i;
        ? baseSavedState = new View.BaseSavedState(super.onSaveInstanceState());
        boolean z = false;
        baseSavedState.isProgressBarShown = false;
        ProgressBarMixin progressBarMixin = (ProgressBarMixin) getMixin(ProgressBarMixin.class);
        if (progressBarMixin.useBottomProgressBar) {
            i = 2131363722;
        } else {
            i = 2131363744;
        }
        View findManagedViewById = progressBarMixin.templateLayout.findManagedViewById(i);
        if (findManagedViewById != null && findManagedViewById.getVisibility() == 0) {
            z = true;
        }
        baseSavedState.isProgressBarShown = z;
        return baseSavedState;
    }
}
