package com.google.android.material.navigation;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.NavigationMenuView;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class NavigationView extends ScrimInsetsFrameLayout {
    public static final int[] CHECKED_STATE_SET = {16842912};
    public static final int[] DISABLED_STATE_SET = {-16842910};
    public final boolean bottomInsetScrimEnabled = true;
    public final int drawerLayoutCornerSize = 0;
    public final int layoutGravity = 0;
    public final int maxWidth;
    public final NavigationMenu menu;
    public final SupportMenuInflater menuInflater;
    public final AnonymousClass2 onGlobalLayoutListener;
    public final NavigationMenuPresenter presenter;
    public final RectF shapeClipBounds = new RectF();
    public Path shapeClipPath;
    public final int[] tmpLocation = new int[2];
    public final boolean topInsetScrimEnabled = true;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public Bundle menuState;

        /* renamed from: com.google.android.material.navigation.NavigationView$SavedState$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuState);
        }
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [com.google.android.material.internal.NavigationMenu, androidx.appcompat.view.menu.MenuBuilder, android.view.Menu] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0210  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0215  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0235  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x02b2  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02bb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NavigationView(android.content.Context r17, android.util.AttributeSet r18) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = 2130969657(0x7f040439, float:1.7548002E38)
            r9 = 2132018526(0x7f14055e, float:1.9675361E38)
            r1 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            com.google.android.material.internal.NavigationMenuPresenter r10 = new com.google.android.material.internal.NavigationMenuPresenter
            r10.<init>()
            r0.presenter = r10
            r11 = 2
            int[] r1 = new int[r11]
            r0.tmpLocation = r1
            r12 = 1
            r0.topInsetScrimEnabled = r12
            r0.bottomInsetScrimEnabled = r12
            r13 = 0
            r0.layoutGravity = r13
            r0.drawerLayoutCornerSize = r13
            android.graphics.RectF r1 = new android.graphics.RectF
            r1.<init>()
            r0.shapeClipBounds = r1
            android.content.Context r14 = r16.getContext()
            com.google.android.material.internal.NavigationMenu r15 = new com.google.android.material.internal.NavigationMenu
            r15.<init>(r14)
            r0.menu = r15
            int[] r3 = com.google.android.material.R$styleable.NavigationView
            r5 = 2132018526(0x7f14055e, float:1.9675361E38)
            int[] r6 = new int[r13]
            r1 = r14
            r2 = r18
            r4 = r8
            androidx.appcompat.widget.TintTypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r1, r2, r3, r4, r5, r6)
            android.content.res.TypedArray r2 = r1.mWrapped
            boolean r3 = r2.hasValue(r12)
            if (r3 == 0) goto L_0x005b
            android.graphics.drawable.Drawable r3 = r1.getDrawable(r12)
            java.util.WeakHashMap r4 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.setBackground(r0, r3)
        L_0x005b:
            r3 = 7
            int r3 = r2.getDimensionPixelSize(r3, r13)
            r0.drawerLayoutCornerSize = r3
            int r3 = r2.getInt(r13, r13)
            r0.layoutGravity = r3
            android.graphics.drawable.Drawable r3 = r16.getBackground()
            if (r3 == 0) goto L_0x0076
            android.graphics.drawable.Drawable r3 = r16.getBackground()
            boolean r3 = r3 instanceof android.graphics.drawable.ColorDrawable
            if (r3 == 0) goto L_0x00a0
        L_0x0076:
            com.google.android.material.shape.ShapeAppearanceModel$Builder r3 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r14, (android.util.AttributeSet) r7, (int) r8, (int) r9)
            com.google.android.material.shape.ShapeAppearanceModel r3 = r3.build()
            android.graphics.drawable.Drawable r4 = r16.getBackground()
            com.google.android.material.shape.MaterialShapeDrawable r5 = new com.google.android.material.shape.MaterialShapeDrawable
            r5.<init>((com.google.android.material.shape.ShapeAppearanceModel) r3)
            boolean r3 = r4 instanceof android.graphics.drawable.ColorDrawable
            if (r3 == 0) goto L_0x0098
            android.graphics.drawable.ColorDrawable r4 = (android.graphics.drawable.ColorDrawable) r4
            int r3 = r4.getColor()
            android.content.res.ColorStateList r3 = android.content.res.ColorStateList.valueOf(r3)
            r5.setFillColor(r3)
        L_0x0098:
            r5.initializeElevationOverlay(r14)
            java.util.WeakHashMap r3 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.setBackground(r0, r5)
        L_0x00a0:
            r3 = 8
            boolean r4 = r2.hasValue(r3)
            if (r4 == 0) goto L_0x00b0
            int r3 = r2.getDimensionPixelSize(r3, r13)
            float r3 = (float) r3
            r0.setElevation(r3)
        L_0x00b0:
            boolean r3 = r2.getBoolean(r11, r13)
            r0.setFitsSystemWindows(r3)
            r3 = 3
            int r3 = r2.getDimensionPixelSize(r3, r13)
            r0.maxWidth = r3
            r3 = 30
            boolean r4 = r2.hasValue(r3)
            r5 = 0
            if (r4 == 0) goto L_0x00cc
            android.content.res.ColorStateList r3 = r1.getColorStateList(r3)
            goto L_0x00cd
        L_0x00cc:
            r3 = r5
        L_0x00cd:
            r4 = 33
            boolean r6 = r2.hasValue(r4)
            if (r6 == 0) goto L_0x00da
            int r4 = r2.getResourceId(r4, r13)
            goto L_0x00db
        L_0x00da:
            r4 = r13
        L_0x00db:
            r6 = 16842808(0x1010038, float:2.3693715E-38)
            if (r4 != 0) goto L_0x00e6
            if (r3 != 0) goto L_0x00e6
            android.content.res.ColorStateList r3 = r0.createDefaultColorStateList(r6)
        L_0x00e6:
            r7 = 14
            boolean r8 = r2.hasValue(r7)
            if (r8 == 0) goto L_0x00f3
            android.content.res.ColorStateList r6 = r1.getColorStateList(r7)
            goto L_0x00f7
        L_0x00f3:
            android.content.res.ColorStateList r6 = r0.createDefaultColorStateList(r6)
        L_0x00f7:
            r7 = 24
            boolean r8 = r2.hasValue(r7)
            if (r8 == 0) goto L_0x0104
            int r7 = r2.getResourceId(r7, r13)
            goto L_0x0105
        L_0x0104:
            r7 = r13
        L_0x0105:
            r8 = 13
            boolean r9 = r2.hasValue(r8)
            if (r9 == 0) goto L_0x011c
            int r8 = r2.getDimensionPixelSize(r8, r13)
            int r9 = r10.itemIconSize
            if (r9 == r8) goto L_0x011c
            r10.itemIconSize = r8
            r10.hasCustomItemIconSize = r12
            r10.updateMenuView(r13)
        L_0x011c:
            r8 = 25
            boolean r9 = r2.hasValue(r8)
            if (r9 == 0) goto L_0x0129
            android.content.res.ColorStateList r8 = r1.getColorStateList(r8)
            goto L_0x012a
        L_0x0129:
            r8 = r5
        L_0x012a:
            if (r7 != 0) goto L_0x0135
            if (r8 != 0) goto L_0x0135
            r8 = 16842806(0x1010036, float:2.369371E-38)
            android.content.res.ColorStateList r8 = r0.createDefaultColorStateList(r8)
        L_0x0135:
            r9 = 10
            android.graphics.drawable.Drawable r9 = r1.getDrawable(r9)
            if (r9 != 0) goto L_0x0173
            r11 = 17
            boolean r11 = r2.hasValue(r11)
            if (r11 != 0) goto L_0x014d
            r11 = 18
            boolean r11 = r2.hasValue(r11)
            if (r11 == 0) goto L_0x0173
        L_0x014d:
            android.content.Context r9 = r16.getContext()
            r11 = 19
            android.content.res.ColorStateList r9 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r9, (androidx.appcompat.widget.TintTypedArray) r1, (int) r11)
            android.graphics.drawable.Drawable r9 = r0.createDefaultItemDrawable(r1, r9)
            r11 = 16
            android.content.res.ColorStateList r11 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r14, (androidx.appcompat.widget.TintTypedArray) r1, (int) r11)
            if (r11 == 0) goto L_0x0173
            android.graphics.drawable.Drawable r12 = r0.createDefaultItemDrawable(r1, r5)
            android.graphics.drawable.RippleDrawable r13 = new android.graphics.drawable.RippleDrawable
            r13.<init>(r11, r5, r12)
            r10.itemForeground = r13
            r5 = 0
            r10.updateMenuView(r5)
            goto L_0x0174
        L_0x0173:
            r5 = r13
        L_0x0174:
            r11 = 11
            boolean r12 = r2.hasValue(r11)
            if (r12 == 0) goto L_0x0185
            int r11 = r2.getDimensionPixelSize(r11, r5)
            r10.itemHorizontalPadding = r11
            r10.updateMenuView(r5)
        L_0x0185:
            r11 = 26
            boolean r12 = r2.hasValue(r11)
            if (r12 == 0) goto L_0x0196
            int r11 = r2.getDimensionPixelSize(r11, r5)
            r10.itemVerticalPadding = r11
            r10.updateMenuView(r5)
        L_0x0196:
            r11 = 6
            int r11 = r2.getDimensionPixelSize(r11, r5)
            r10.dividerInsetStart = r11
            r10.updateMenuView(r5)
            r11 = 5
            int r11 = r2.getDimensionPixelSize(r11, r5)
            r10.dividerInsetEnd = r11
            r10.updateMenuView(r5)
            r11 = 32
            int r11 = r2.getDimensionPixelSize(r11, r5)
            r10.subheaderInsetStart = r11
            r10.updateMenuView(r5)
            r11 = 31
            int r11 = r2.getDimensionPixelSize(r11, r5)
            r10.subheaderInsetStart = r11
            r10.updateMenuView(r5)
            boolean r5 = r0.topInsetScrimEnabled
            r11 = 34
            boolean r5 = r2.getBoolean(r11, r5)
            r0.topInsetScrimEnabled = r5
            boolean r5 = r0.bottomInsetScrimEnabled
            r11 = 4
            boolean r5 = r2.getBoolean(r11, r5)
            r0.bottomInsetScrimEnabled = r5
            r5 = 12
            r11 = 0
            int r5 = r2.getDimensionPixelSize(r5, r11)
            r12 = 15
            r13 = 1
            int r12 = r2.getInt(r12, r13)
            r10.itemMaxLines = r12
            r10.updateMenuView(r11)
            com.google.android.material.navigation.NavigationView$1 r11 = new com.google.android.material.navigation.NavigationView$1
            r11.<init>()
            r15.mCallback = r11
            r10.id = r13
            r10.initForMenu(r14, r15)
            if (r4 == 0) goto L_0x01fb
            r10.subheaderTextAppearance = r4
            r4 = 0
            r10.updateMenuView(r4)
            goto L_0x01fc
        L_0x01fb:
            r4 = 0
        L_0x01fc:
            r10.subheaderColor = r3
            r10.updateMenuView(r4)
            r10.iconTintList = r6
            r10.updateMenuView(r4)
            int r3 = r16.getOverScrollMode()
            r10.overScrollMode = r3
            com.google.android.material.internal.NavigationMenuView r4 = r10.menuView
            if (r4 == 0) goto L_0x0213
            r4.setOverScrollMode(r3)
        L_0x0213:
            if (r7 == 0) goto L_0x021c
            r10.textAppearance = r7
            r3 = 0
            r10.updateMenuView(r3)
            goto L_0x021d
        L_0x021c:
            r3 = 0
        L_0x021d:
            r10.textColor = r8
            r10.updateMenuView(r3)
            r10.itemBackground = r9
            r10.updateMenuView(r3)
            r10.itemIconPadding = r5
            r10.updateMenuView(r3)
            android.content.Context r4 = r15.mContext
            r15.addMenuPresenter(r10, r4)
            com.google.android.material.internal.NavigationMenuView r4 = r10.menuView
            if (r4 != 0) goto L_0x027a
            android.view.LayoutInflater r4 = r10.layoutInflater
            r5 = 2131558567(0x7f0d00a7, float:1.8742453E38)
            android.view.View r4 = r4.inflate(r5, r0, r3)
            com.google.android.material.internal.NavigationMenuView r4 = (com.google.android.material.internal.NavigationMenuView) r4
            r10.menuView = r4
            com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuViewAccessibilityDelegate r3 = new com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuViewAccessibilityDelegate
            com.google.android.material.internal.NavigationMenuView r5 = r10.menuView
            r3.<init>(r5)
            r4.mAccessibilityDelegate = r3
            androidx.core.view.ViewCompat.setAccessibilityDelegate(r4, r3)
            com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuAdapter r3 = r10.adapter
            if (r3 != 0) goto L_0x0259
            com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuAdapter r3 = new com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuAdapter
            r3.<init>()
            r10.adapter = r3
        L_0x0259:
            int r3 = r10.overScrollMode
            r4 = -1
            if (r3 == r4) goto L_0x0263
            com.google.android.material.internal.NavigationMenuView r4 = r10.menuView
            r4.setOverScrollMode(r3)
        L_0x0263:
            android.view.LayoutInflater r3 = r10.layoutInflater
            r4 = 2131558564(0x7f0d00a4, float:1.8742447E38)
            com.google.android.material.internal.NavigationMenuView r5 = r10.menuView
            r6 = 0
            android.view.View r3 = r3.inflate(r4, r5, r6)
            android.widget.LinearLayout r3 = (android.widget.LinearLayout) r3
            r10.headerLayout = r3
            com.google.android.material.internal.NavigationMenuView r3 = r10.menuView
            com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuAdapter r4 = r10.adapter
            r3.setAdapter(r4)
        L_0x027a:
            com.google.android.material.internal.NavigationMenuView r3 = r10.menuView
            r0.addView(r3)
            r3 = 27
            boolean r4 = r2.hasValue(r3)
            if (r4 == 0) goto L_0x02b2
            r4 = 0
            int r3 = r2.getResourceId(r3, r4)
            com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuAdapter r4 = r10.adapter
            if (r4 == 0) goto L_0x0293
            r5 = 1
            r4.updateSuspended = r5
        L_0x0293:
            androidx.appcompat.view.SupportMenuInflater r4 = r0.menuInflater
            if (r4 != 0) goto L_0x02a2
            androidx.appcompat.view.SupportMenuInflater r4 = new androidx.appcompat.view.SupportMenuInflater
            android.content.Context r5 = r16.getContext()
            r4.<init>(r5)
            r0.menuInflater = r4
        L_0x02a2:
            androidx.appcompat.view.SupportMenuInflater r4 = r0.menuInflater
            r4.inflate(r3, r15)
            com.google.android.material.internal.NavigationMenuPresenter$NavigationMenuAdapter r3 = r10.adapter
            r4 = 0
            if (r3 == 0) goto L_0x02ae
            r3.updateSuspended = r4
        L_0x02ae:
            r10.updateMenuView(r4)
            goto L_0x02b3
        L_0x02b2:
            r4 = 0
        L_0x02b3:
            r3 = 9
            boolean r5 = r2.hasValue(r3)
            if (r5 == 0) goto L_0x02d5
            int r2 = r2.getResourceId(r3, r4)
            android.view.LayoutInflater r3 = r10.layoutInflater
            android.widget.LinearLayout r5 = r10.headerLayout
            android.view.View r2 = r3.inflate(r2, r5, r4)
            android.widget.LinearLayout r3 = r10.headerLayout
            r3.addView(r2)
            com.google.android.material.internal.NavigationMenuView r2 = r10.menuView
            int r3 = r2.getPaddingBottom()
            r2.setPadding(r4, r4, r4, r3)
        L_0x02d5:
            r1.recycle()
            com.google.android.material.navigation.NavigationView$2 r1 = new com.google.android.material.navigation.NavigationView$2
            r1.<init>()
            r0.onGlobalLayoutListener = r1
            android.view.ViewTreeObserver r1 = r16.getViewTreeObserver()
            com.google.android.material.navigation.NavigationView$2 r0 = r0.onGlobalLayoutListener
            r1.addOnGlobalLayoutListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final ColorStateList createDefaultColorStateList(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = ContextCompat.getColorStateList(typedValue.resourceId, getContext());
        if (!getContext().getTheme().resolveAttribute(2130968878, typedValue, true)) {
            return null;
        }
        int i2 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i2, defaultColor});
    }

    public final Drawable createDefaultItemDrawable(TintTypedArray tintTypedArray, ColorStateList colorStateList) {
        TypedArray typedArray = tintTypedArray.mWrapped;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(getContext(), typedArray.getResourceId(17, 0), typedArray.getResourceId(18, 0), (CornerSize) new AbsoluteCornerSize((float) 0)).build());
        materialShapeDrawable.setFillColor(colorStateList);
        return new InsetDrawable(materialShapeDrawable, typedArray.getDimensionPixelSize(22, 0), typedArray.getDimensionPixelSize(23, 0), typedArray.getDimensionPixelSize(21, 0), typedArray.getDimensionPixelSize(20, 0));
    }

    public final void dispatchDraw(Canvas canvas) {
        if (this.shapeClipPath == null) {
            super.dispatchDraw(canvas);
            return;
        }
        int save = canvas.save();
        canvas.clipPath(this.shapeClipPath);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    public final void onInsetsChanged(WindowInsetsCompat windowInsetsCompat) {
        int i;
        NavigationMenuPresenter navigationMenuPresenter = this.presenter;
        navigationMenuPresenter.getClass();
        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
        if (navigationMenuPresenter.paddingTopDefault != systemWindowInsetTop) {
            navigationMenuPresenter.paddingTopDefault = systemWindowInsetTop;
            if (navigationMenuPresenter.headerLayout.getChildCount() != 0 || !navigationMenuPresenter.isBehindStatusBar) {
                i = 0;
            } else {
                i = navigationMenuPresenter.paddingTopDefault;
            }
            NavigationMenuView navigationMenuView = navigationMenuPresenter.menuView;
            navigationMenuView.setPadding(0, i, 0, navigationMenuView.getPaddingBottom());
        }
        NavigationMenuView navigationMenuView2 = navigationMenuPresenter.menuView;
        navigationMenuView2.setPadding(0, navigationMenuView2.getPaddingTop(), 0, windowInsetsCompat.getSystemWindowInsetBottom());
        ViewCompat.dispatchApplyWindowInsets(navigationMenuPresenter.headerLayout, windowInsetsCompat);
    }

    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i), this.maxWidth), 1073741824);
        } else if (mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        this.menu.restorePresenterStates(savedState.menuState);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, androidx.customview.view.AbsSavedState, com.google.android.material.navigation.NavigationView$SavedState] */
    public final Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        absSavedState.menuState = bundle;
        this.menu.savePresenterStates(bundle);
        return absSavedState;
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!(getParent() instanceof DrawerLayout) || this.drawerLayoutCornerSize <= 0 || !(getBackground() instanceof MaterialShapeDrawable)) {
            this.shapeClipPath = null;
            this.shapeClipBounds.setEmpty();
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) getBackground();
        ShapeAppearanceModel.Builder builder = materialShapeDrawable.drawableState.shapeAppearanceModel.toBuilder();
        int i5 = this.layoutGravity;
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (Gravity.getAbsoluteGravity(i5, ViewCompat.Api17Impl.getLayoutDirection(this)) == 3) {
            int i6 = this.drawerLayoutCornerSize;
            builder.topRightCornerSize = new AbsoluteCornerSize((float) i6);
            builder.bottomRightCornerSize = new AbsoluteCornerSize((float) i6);
        } else {
            int i7 = this.drawerLayoutCornerSize;
            builder.topLeftCornerSize = new AbsoluteCornerSize((float) i7);
            builder.bottomLeftCornerSize = new AbsoluteCornerSize((float) i7);
        }
        materialShapeDrawable.setShapeAppearanceModel(builder.build());
        if (this.shapeClipPath == null) {
            this.shapeClipPath = new Path();
        }
        this.shapeClipPath.reset();
        this.shapeClipBounds.set(0.0f, 0.0f, (float) i, (float) i2);
        ShapeAppearancePathProvider shapeAppearancePathProvider = ShapeAppearancePathProvider.Lazy.INSTANCE;
        MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable.drawableState;
        shapeAppearancePathProvider.calculatePath(materialShapeDrawableState.shapeAppearanceModel, materialShapeDrawableState.interpolation, this.shapeClipBounds, (MaterialShapeDrawable.AnonymousClass1) null, this.shapeClipPath);
        invalidate();
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public final void setOverScrollMode(int i) {
        super.setOverScrollMode(i);
        NavigationMenuPresenter navigationMenuPresenter = this.presenter;
        if (navigationMenuPresenter != null) {
            navigationMenuPresenter.overScrollMode = i;
            NavigationMenuView navigationMenuView = navigationMenuPresenter.menuView;
            if (navigationMenuView != null) {
                navigationMenuView.setOverScrollMode(i);
            }
        }
    }
}
