package com.google.android.material.tabs;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.Pools$SimplePool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

@ViewPager.DecorView
/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class TabLayout extends HorizontalScrollView {
    public static final Pools$SynchronizedPool tabPool = new Pools$SynchronizedPool(16);
    public AdapterChangeListener adapterChangeListener;
    public ViewPagerOnTabSelectedListener currentVpSelectedListener;
    public final boolean inlineLabel;
    public final int mode;
    public TabLayoutOnPageChangeListener pageChangeListener;
    public PagerAdapter pagerAdapter;
    public PagerAdapterObserver pagerAdapterObserver;
    public final int requestedTabMaxWidth;
    public final int requestedTabMinWidth;
    public ValueAnimator scrollAnimator;
    public final int scrollableTabMinWidth;
    public final ArrayList selectedListeners;
    public Tab selectedTab;
    public boolean setupViewPagerImplicitly;
    public final SlidingTabIndicator slidingTabIndicator;
    public final int tabBackgroundResId;
    public int tabGravity;
    public final ColorStateList tabIconTint;
    public final PorterDuff.Mode tabIconTintMode;
    public final int tabIndicatorAnimationDuration;
    public final boolean tabIndicatorFullWidth;
    public final int tabIndicatorGravity;
    public final TabIndicatorInterpolator tabIndicatorInterpolator;
    public int tabMaxWidth;
    public final int tabPaddingBottom;
    public final int tabPaddingEnd;
    public final int tabPaddingStart;
    public final int tabPaddingTop;
    public final ColorStateList tabRippleColorStateList;
    public final Drawable tabSelectedIndicator;
    public final int tabSelectedIndicatorColor;
    public final int tabTextAppearance;
    public final ColorStateList tabTextColors;
    public final float tabTextMultiLineSize;
    public final float tabTextSize;
    public final Pools$SimplePool tabViewPool;
    public final ArrayList tabs;
    public final boolean unboundedRipple;
    public ViewPager viewPager;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AdapterChangeListener implements ViewPager.OnAdapterChangeListener {
        public boolean autoRefresh;

        public AdapterChangeListener() {
        }

        public final void onAdapterChanged(ViewPager viewPager, PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.viewPager == viewPager) {
                tabLayout.setPagerAdapter(pagerAdapter2, this.autoRefresh);
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class PagerAdapterObserver extends DataSetObserver {
        public PagerAdapterObserver() {
        }

        public final void onChanged() {
            TabLayout.this.populateFromPagerAdapter();
        }

        public final void onInvalidated() {
            TabLayout.this.populateFromPagerAdapter();
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SlidingTabIndicator extends LinearLayout {
        public ValueAnimator indicatorAnimator;

        public SlidingTabIndicator(Context context) {
            super(context);
            setWillNotDraw(false);
        }

        public final void draw(Canvas canvas) {
            int i;
            int height = TabLayout.this.tabSelectedIndicator.getBounds().height();
            if (height < 0) {
                height = TabLayout.this.tabSelectedIndicator.getIntrinsicHeight();
            }
            int i2 = TabLayout.this.tabIndicatorGravity;
            if (i2 == 0) {
                i = getHeight() - height;
                height = getHeight();
            } else if (i2 != 1) {
                i = 0;
                if (i2 != 2) {
                    if (i2 != 3) {
                        height = 0;
                    } else {
                        height = getHeight();
                    }
                }
            } else {
                i = (getHeight() - height) / 2;
                height = (getHeight() + height) / 2;
            }
            if (TabLayout.this.tabSelectedIndicator.getBounds().width() > 0) {
                Rect bounds = TabLayout.this.tabSelectedIndicator.getBounds();
                TabLayout.this.tabSelectedIndicator.setBounds(bounds.left, i, bounds.right, height);
                TabLayout.this.tabSelectedIndicator.draw(canvas);
            }
            super.draw(canvas);
        }

        public final void jumpIndicatorToSelectedPosition() {
            View childAt = getChildAt(TabLayout.this.getSelectedTabPosition());
            TabLayout tabLayout = TabLayout.this;
            TabIndicatorInterpolator tabIndicatorInterpolator = tabLayout.tabIndicatorInterpolator;
            Drawable drawable = tabLayout.tabSelectedIndicator;
            tabIndicatorInterpolator.getClass();
            RectF calculateIndicatorWidthForTab = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout, childAt);
            drawable.setBounds((int) calculateIndicatorWidthForTab.left, drawable.getBounds().top, (int) calculateIndicatorWidthForTab.right, drawable.getBounds().bottom);
        }

        public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                jumpIndicatorToSelectedPosition();
            } else {
                updateOrRecreateIndicatorAnimation(TabLayout.this.getSelectedTabPosition(), -1, false);
            }
        }

        public final void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) == 1073741824) {
                TabLayout tabLayout = TabLayout.this;
                if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                    int childCount = getChildCount();
                    int i3 = 0;
                    for (int i4 = 0; i4 < childCount; i4++) {
                        View childAt = getChildAt(i4);
                        if (childAt.getVisibility() == 0) {
                            i3 = Math.max(i3, childAt.getMeasuredWidth());
                        }
                    }
                    if (i3 > 0) {
                        if (i3 * childCount <= getMeasuredWidth() - (((int) ViewUtils.dpToPx(16, getContext())) * 2)) {
                            boolean z = false;
                            for (int i5 = 0; i5 < childCount; i5++) {
                                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i5).getLayoutParams();
                                if (layoutParams.width != i3 || layoutParams.weight != 0.0f) {
                                    layoutParams.width = i3;
                                    layoutParams.weight = 0.0f;
                                    z = true;
                                }
                            }
                            if (!z) {
                                return;
                            }
                        } else {
                            TabLayout tabLayout2 = TabLayout.this;
                            tabLayout2.tabGravity = 0;
                            tabLayout2.updateTabViews(false);
                        }
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        public final void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
        }

        public final void tweenIndicatorPosition(View view, View view2, float f) {
            if (view == null || view.getWidth() <= 0) {
                Drawable drawable = TabLayout.this.tabSelectedIndicator;
                drawable.setBounds(-1, drawable.getBounds().top, -1, TabLayout.this.tabSelectedIndicator.getBounds().bottom);
            } else {
                TabLayout tabLayout = TabLayout.this;
                tabLayout.tabIndicatorInterpolator.updateIndicatorForOffset(tabLayout, view, view2, f, tabLayout.tabSelectedIndicator);
            }
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            ViewCompat.Api16Impl.postInvalidateOnAnimation(this);
        }

        public final void updateOrRecreateIndicatorAnimation(int i, int i2, boolean z) {
            final View childAt = getChildAt(TabLayout.this.getSelectedTabPosition());
            final View childAt2 = getChildAt(i);
            if (childAt2 == null) {
                jumpIndicatorToSelectedPosition();
                return;
            }
            AnonymousClass1 r1 = new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SlidingTabIndicator.this.tweenIndicatorPosition(childAt, childAt2, valueAnimator.getAnimatedFraction());
                }
            };
            if (z) {
                ValueAnimator valueAnimator = new ValueAnimator();
                this.indicatorAnimator = valueAnimator;
                valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                valueAnimator.setDuration((long) i2);
                valueAnimator.setFloatValues(new float[]{0.0f, 1.0f});
                valueAnimator.addUpdateListener(r1);
                valueAnimator.start();
                return;
            }
            this.indicatorAnimator.removeAllUpdateListeners();
            this.indicatorAnimator.addUpdateListener(r1);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class Tab {
        public CharSequence contentDesc;
        public View customView;
        public Drawable icon;
        public int id;
        public TabLayout parent;
        public int position;
        public CharSequence text;
        public TabView view;
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class TabLayoutOnPageChangeListener implements ViewPager.OnPageChangeListener {
        public int previousScrollState;
        public int scrollState;
        public final WeakReference tabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference(tabLayout);
        }

        public final void onPageScrollStateChanged(int i) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i;
        }

        public final void onPageScrolled(int i, float f, int i2) {
            boolean z;
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null) {
                int i3 = this.scrollState;
                boolean z2 = false;
                if (i3 != 2 || this.previousScrollState == 1) {
                    z = true;
                } else {
                    z = false;
                }
                if (!(i3 == 2 && this.previousScrollState == 0)) {
                    z2 = true;
                }
                tabLayout.setScrollPosition(f, i, z, z2);
            }
        }

        public final void onPageSelected(int i) {
            boolean z;
            Tab tab;
            TabLayout tabLayout = (TabLayout) this.tabLayoutRef.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i && i < tabLayout.tabs.size()) {
                int i2 = this.scrollState;
                if (i2 == 0 || (i2 == 2 && this.previousScrollState == 0)) {
                    z = true;
                } else {
                    z = false;
                }
                if (i < 0 || i >= tabLayout.tabs.size()) {
                    tab = null;
                } else {
                    tab = (Tab) tabLayout.tabs.get(i);
                }
                tabLayout.selectTab(tab, z);
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class TabView extends LinearLayout {
        public final Drawable baseBackgroundDrawable;
        public ImageView customIconView;
        public TextView customTextView;
        public View customView;
        public int defaultMaxLines = 2;
        public ImageView iconView;
        public Tab tab;
        public TextView textView;

        /* JADX WARNING: type inference failed for: r3v0, types: [android.graphics.drawable.RippleDrawable] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public TabView(android.content.Context r7) {
            /*
                r5 = this;
                com.google.android.material.tabs.TabLayout.this = r6
                r5.<init>(r7)
                r0 = 2
                r5.defaultMaxLines = r0
                int r0 = r6.tabBackgroundResId
                r1 = 0
                if (r0 == 0) goto L_0x0025
                android.graphics.drawable.Drawable r7 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r7)
                r5.baseBackgroundDrawable = r7
                if (r7 == 0) goto L_0x0027
                boolean r7 = r7.isStateful()
                if (r7 == 0) goto L_0x0027
                android.graphics.drawable.Drawable r7 = r5.baseBackgroundDrawable
                int[] r0 = r5.getDrawableState()
                r7.setState(r0)
                goto L_0x0027
            L_0x0025:
                r5.baseBackgroundDrawable = r1
            L_0x0027:
                android.graphics.drawable.GradientDrawable r7 = new android.graphics.drawable.GradientDrawable
                r7.<init>()
                r0 = 0
                r7.setColor(r0)
                android.content.res.ColorStateList r0 = r6.tabRippleColorStateList
                if (r0 == 0) goto L_0x0058
                android.graphics.drawable.GradientDrawable r0 = new android.graphics.drawable.GradientDrawable
                r0.<init>()
                r2 = 925353388(0x3727c5ac, float:1.0E-5)
                r0.setCornerRadius(r2)
                r2 = -1
                r0.setColor(r2)
                android.content.res.ColorStateList r2 = r6.tabRippleColorStateList
                android.content.res.ColorStateList r2 = com.google.android.material.ripple.RippleUtils.convertToRippleDrawableColor(r2)
                android.graphics.drawable.RippleDrawable r3 = new android.graphics.drawable.RippleDrawable
                boolean r4 = r6.unboundedRipple
                if (r4 == 0) goto L_0x0050
                r7 = r1
            L_0x0050:
                if (r4 == 0) goto L_0x0053
                goto L_0x0054
            L_0x0053:
                r1 = r0
            L_0x0054:
                r3.<init>(r2, r7, r1)
                r7 = r3
            L_0x0058:
                java.util.WeakHashMap r0 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
                androidx.core.view.ViewCompat.Api16Impl.setBackground(r5, r7)
                r6.invalidate()
                int r7 = r6.tabPaddingStart
                int r0 = r6.tabPaddingTop
                int r1 = r6.tabPaddingEnd
                int r2 = r6.tabPaddingBottom
                androidx.core.view.ViewCompat.Api17Impl.setPaddingRelative(r5, r7, r0, r1, r2)
                r7 = 17
                r5.setGravity(r7)
                boolean r6 = r6.inlineLabel
                r7 = 1
                r6 = r6 ^ r7
                r5.setOrientation(r6)
                r5.setClickable(r7)
                android.content.Context r6 = r5.getContext()
                r7 = 1002(0x3ea, float:1.404E-42)
                android.view.PointerIcon r6 = android.view.PointerIcon.getSystemIcon(r6, r7)
                androidx.core.view.ViewCompat.Api24Impl.setPointerIcon(r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.TabView.<init>(com.google.android.material.tabs.TabLayout, android.content.Context):void");
        }

        public final void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.baseBackgroundDrawable;
            if (drawable != null && drawable.isStateful() && this.baseBackgroundDrawable.setState(drawableState)) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(0, 1, this.tab.position, 1, false, isSelected()).mInfo);
            if (isSelected()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.mAction);
            }
            accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", getResources().getString(2131952704));
        }

        public final void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int i3 = TabLayout.this.tabMaxWidth;
            if (i3 > 0 && (mode == 0 || size > i3)) {
                i = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.textView != null) {
                float f = TabLayout.this.tabTextSize;
                int i4 = this.defaultMaxLines;
                ImageView imageView = this.iconView;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView2 = this.textView;
                    if (textView2 != null && textView2.getLineCount() > 1) {
                        f = TabLayout.this.tabTextMultiLineSize;
                    }
                } else {
                    i4 = 1;
                }
                float textSize = this.textView.getTextSize();
                int lineCount = this.textView.getLineCount();
                int maxLines = this.textView.getMaxLines();
                int i5 = (f > textSize ? 1 : (f == textSize ? 0 : -1));
                if (i5 != 0 || (maxLines >= 0 && i4 != maxLines)) {
                    if (TabLayout.this.mode == 1 && i5 > 0 && lineCount == 1) {
                        Layout layout = this.textView.getLayout();
                        if (layout != null) {
                            if ((f / layout.getPaint().getTextSize()) * layout.getLineWidth(0) > ((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    this.textView.setTextSize(0, f);
                    this.textView.setMaxLines(i4);
                    super.onMeasure(i, i2);
                }
            }
        }

        public final boolean performClick() {
            boolean performClick = super.performClick();
            if (this.tab == null) {
                return performClick;
            }
            if (!performClick) {
                playSoundEffect(0);
            }
            Tab tab2 = this.tab;
            TabLayout tabLayout = tab2.parent;
            if (tabLayout != null) {
                tabLayout.selectTab(tab2, true);
                return true;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public final void setSelected(boolean z) {
            isSelected();
            super.setSelected(z);
            TextView textView2 = this.textView;
            if (textView2 != null) {
                textView2.setSelected(z);
            }
            ImageView imageView = this.iconView;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.customView;
            if (view != null) {
                view.setSelected(z);
            }
        }

        public final void update() {
            View view;
            Tab tab2 = this.tab;
            if (tab2 != null) {
                view = tab2.customView;
            } else {
                view = null;
            }
            if (view != null) {
                ViewParent parent = view.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(view);
                    }
                    addView(view);
                }
                this.customView = view;
                TextView textView2 = this.textView;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                }
                ImageView imageView = this.iconView;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.iconView.setImageDrawable((Drawable) null);
                }
                TextView textView3 = (TextView) view.findViewById(16908308);
                this.customTextView = textView3;
                if (textView3 != null) {
                    this.defaultMaxLines = textView3.getMaxLines();
                }
                this.customIconView = (ImageView) view.findViewById(16908294);
            } else {
                View view2 = this.customView;
                if (view2 != null) {
                    removeView(view2);
                    this.customView = null;
                }
                this.customTextView = null;
                this.customIconView = null;
            }
            boolean z = false;
            if (this.customView == null) {
                if (this.iconView == null) {
                    ImageView imageView2 = (ImageView) LayoutInflater.from(getContext()).inflate(2131558560, this, false);
                    this.iconView = imageView2;
                    addView(imageView2, 0);
                }
                if (this.textView == null) {
                    TextView textView4 = (TextView) LayoutInflater.from(getContext()).inflate(2131558561, this, false);
                    this.textView = textView4;
                    addView(textView4);
                    this.defaultMaxLines = this.textView.getMaxLines();
                }
                this.textView.setTextAppearance(TabLayout.this.tabTextAppearance);
                ColorStateList colorStateList = TabLayout.this.tabTextColors;
                if (colorStateList != null) {
                    this.textView.setTextColor(colorStateList);
                }
                updateTextAndIcon(this.textView, this.iconView);
                final ImageView imageView3 = this.iconView;
                if (imageView3 != null) {
                    imageView3.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                            if (r1.getVisibility() == 0) {
                                TabView.this.getClass();
                            }
                        }
                    });
                }
                final TextView textView5 = this.textView;
                if (textView5 != null) {
                    textView5.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                            if (textView5.getVisibility() == 0) {
                                TabView.this.getClass();
                            }
                        }
                    });
                }
            } else {
                TextView textView6 = this.customTextView;
                if (!(textView6 == null && this.customIconView == null)) {
                    updateTextAndIcon(textView6, this.customIconView);
                }
            }
            if (tab2 != null && !TextUtils.isEmpty(tab2.contentDesc)) {
                setContentDescription(tab2.contentDesc);
            }
            if (tab2 != null) {
                TabLayout tabLayout = tab2.parent;
                if (tabLayout != null) {
                    int selectedTabPosition = tabLayout.getSelectedTabPosition();
                    if (selectedTabPosition != -1 && selectedTabPosition == tab2.position) {
                        z = true;
                    }
                } else {
                    throw new IllegalArgumentException("Tab not attached to a TabLayout");
                }
            }
            setSelected(z);
        }

        public final void updateTextAndIcon(TextView textView2, ImageView imageView) {
            Drawable drawable;
            CharSequence charSequence;
            int i;
            Drawable drawable2;
            Tab tab2 = this.tab;
            CharSequence charSequence2 = null;
            if (tab2 == null || (drawable2 = tab2.icon) == null) {
                drawable = null;
            } else {
                drawable = drawable2.mutate();
            }
            if (drawable != null) {
                drawable.setTintList(TabLayout.this.tabIconTint);
                PorterDuff.Mode mode = TabLayout.this.tabIconTintMode;
                if (mode != null) {
                    drawable.setTintMode(mode);
                }
            }
            Tab tab3 = this.tab;
            if (tab3 != null) {
                charSequence = tab3.text;
            } else {
                charSequence = null;
            }
            if (imageView != null) {
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable((Drawable) null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequence);
            if (textView2 != null) {
                if (z) {
                    textView2.setText(charSequence);
                    this.tab.getClass();
                    textView2.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView2.setVisibility(8);
                    textView2.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                if (!z || imageView.getVisibility() != 0) {
                    i = 0;
                } else {
                    i = (int) ViewUtils.dpToPx(8, getContext());
                }
                if (TabLayout.this.inlineLabel) {
                    if (i != marginLayoutParams.getMarginEnd()) {
                        marginLayoutParams.setMarginEnd(i);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (i != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = i;
                    marginLayoutParams.setMarginEnd(0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            Tab tab4 = this.tab;
            if (tab4 != null) {
                charSequence2 = tab4.contentDesc;
            }
            if (!z) {
                charSequence = charSequence2;
            }
            setTooltipText(charSequence);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ViewPagerOnTabSelectedListener {
        public final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager2) {
            this.viewPager = viewPager2;
        }
    }

    public TabLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void addTab(Tab tab, boolean z) {
        int size = this.tabs.size();
        if (tab.parent == this) {
            tab.position = size;
            this.tabs.add(size, tab);
            int size2 = this.tabs.size();
            for (int i = size + 1; i < size2; i++) {
                ((Tab) this.tabs.get(i)).position = i;
            }
            TabView tabView = tab.view;
            tabView.setSelected(false);
            tabView.setActivated(false);
            SlidingTabIndicator slidingTabIndicator2 = this.slidingTabIndicator;
            int i2 = tab.position;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            if (this.mode == 1 && this.tabGravity == 0) {
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            } else {
                layoutParams.width = -2;
                layoutParams.weight = 0.0f;
            }
            slidingTabIndicator2.addView(tabView, i2, layoutParams);
            if (z) {
                TabLayout tabLayout = tab.parent;
                if (tabLayout != null) {
                    tabLayout.selectTab(tab, true);
                    return;
                }
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    public final void addView(View view) {
        addViewInternal(view);
    }

    public final void addViewInternal(View view) {
        if (view instanceof TabItem) {
            TabItem tabItem = (TabItem) view;
            Tab newTab = newTab();
            CharSequence charSequence = tabItem.text;
            if (charSequence != null) {
                if (TextUtils.isEmpty(newTab.contentDesc) && !TextUtils.isEmpty(charSequence)) {
                    newTab.view.setContentDescription(charSequence);
                }
                newTab.text = charSequence;
                TabView tabView = newTab.view;
                if (tabView != null) {
                    tabView.update();
                }
            }
            Drawable drawable = tabItem.icon;
            if (drawable != null) {
                newTab.icon = drawable;
                TabLayout tabLayout = newTab.parent;
                if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                    tabLayout.updateTabViews(true);
                }
                TabView tabView2 = newTab.view;
                if (tabView2 != null) {
                    tabView2.update();
                }
            }
            int i = tabItem.customLayout;
            if (i != 0) {
                newTab.customView = LayoutInflater.from(newTab.view.getContext()).inflate(i, newTab.view, false);
                TabView tabView3 = newTab.view;
                if (tabView3 != null) {
                    tabView3.update();
                }
            }
            if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
                newTab.contentDesc = tabItem.getContentDescription();
                TabView tabView4 = newTab.view;
                if (tabView4 != null) {
                    tabView4.update();
                }
            }
            addTab(newTab, this.tabs.isEmpty());
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    public final void animateToTab(int i) {
        if (i != -1) {
            if (getWindowToken() != null) {
                WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                if (ViewCompat.Api19Impl.isLaidOut(this)) {
                    SlidingTabIndicator slidingTabIndicator2 = this.slidingTabIndicator;
                    int childCount = slidingTabIndicator2.getChildCount();
                    int i2 = 0;
                    while (i2 < childCount) {
                        if (slidingTabIndicator2.getChildAt(i2).getWidth() > 0) {
                            i2++;
                        }
                    }
                    int scrollX = getScrollX();
                    int calculateScrollXForTab = calculateScrollXForTab(i, 0.0f);
                    if (scrollX != calculateScrollXForTab) {
                        if (this.scrollAnimator == null) {
                            ValueAnimator valueAnimator = new ValueAnimator();
                            this.scrollAnimator = valueAnimator;
                            valueAnimator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
                            this.scrollAnimator.setDuration((long) this.tabIndicatorAnimationDuration);
                            this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
                                }
                            });
                        }
                        this.scrollAnimator.setIntValues(new int[]{scrollX, calculateScrollXForTab});
                        this.scrollAnimator.start();
                    }
                    SlidingTabIndicator slidingTabIndicator3 = this.slidingTabIndicator;
                    int i3 = this.tabIndicatorAnimationDuration;
                    ValueAnimator valueAnimator2 = slidingTabIndicator3.indicatorAnimator;
                    if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                        slidingTabIndicator3.indicatorAnimator.cancel();
                    }
                    slidingTabIndicator3.updateOrRecreateIndicatorAnimation(i, i3, true);
                    return;
                }
            }
            setScrollPosition(0.0f, i, true, true);
        }
    }

    public final int calculateScrollXForTab(int i, float f) {
        View childAt;
        View view;
        int i2 = this.mode;
        int i3 = 0;
        if ((i2 != 0 && i2 != 2) || (childAt = this.slidingTabIndicator.getChildAt(i)) == null) {
            return 0;
        }
        int i4 = i + 1;
        if (i4 < this.slidingTabIndicator.getChildCount()) {
            view = this.slidingTabIndicator.getChildAt(i4);
        } else {
            view = null;
        }
        int width = childAt.getWidth();
        if (view != null) {
            i3 = view.getWidth();
        }
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i5 = (int) (((float) (width + i3)) * 0.5f * f);
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (ViewCompat.Api17Impl.getLayoutDirection(this) == 0) {
            return left + i5;
        }
        return left - i5;
    }

    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public final int getSelectedTabPosition() {
        Tab tab = this.selectedTab;
        if (tab != null) {
            return tab.position;
        }
        return -1;
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.android.material.tabs.TabLayout$Tab, java.lang.Object] */
    public final Tab newTab() {
        TabView tabView;
        int i;
        Tab tab = (Tab) tabPool.acquire();
        Tab tab2 = tab;
        if (tab == null) {
            ? obj = new Object();
            obj.position = -1;
            obj.id = -1;
            tab2 = obj;
        }
        tab2.parent = this;
        Pools$SimplePool pools$SimplePool = this.tabViewPool;
        if (pools$SimplePool != null) {
            tabView = (TabView) pools$SimplePool.acquire();
        } else {
            tabView = null;
        }
        if (tabView == null) {
            tabView = new TabView(getContext());
        }
        if (tab2 != tabView.tab) {
            tabView.tab = tab2;
            tabView.update();
        }
        tabView.setFocusable(true);
        int i2 = this.requestedTabMinWidth;
        if (i2 == -1) {
            int i3 = this.mode;
            if (i3 == 0 || i3 == 2) {
                i = this.scrollableTabMinWidth;
            } else {
                i = 0;
            }
            i2 = i;
        }
        tabView.setMinimumWidth(i2);
        if (TextUtils.isEmpty(tab2.contentDesc)) {
            tabView.setContentDescription(tab2.text);
        } else {
            tabView.setContentDescription(tab2.contentDesc);
        }
        tab2.view = tabView;
        int i4 = tab2.id;
        if (i4 != -1) {
            tabView.setId(i4);
        }
        return tab2;
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent, true);
            }
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager((ViewPager) null, false);
            this.setupViewPagerImplicitly = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r1 = (com.google.android.material.tabs.TabLayout.TabView) r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onDraw(android.graphics.Canvas r8) {
        /*
            r7 = this;
            r0 = 0
        L_0x0001:
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r1 = r7.slidingTabIndicator
            int r1 = r1.getChildCount()
            if (r0 >= r1) goto L_0x0034
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r1 = r7.slidingTabIndicator
            android.view.View r1 = r1.getChildAt(r0)
            boolean r2 = r1 instanceof com.google.android.material.tabs.TabLayout.TabView
            if (r2 == 0) goto L_0x0031
            com.google.android.material.tabs.TabLayout$TabView r1 = (com.google.android.material.tabs.TabLayout.TabView) r1
            android.graphics.drawable.Drawable r2 = r1.baseBackgroundDrawable
            if (r2 == 0) goto L_0x0031
            int r3 = r1.getLeft()
            int r4 = r1.getTop()
            int r5 = r1.getRight()
            int r6 = r1.getBottom()
            r2.setBounds(r3, r4, r5, r6)
            android.graphics.drawable.Drawable r1 = r1.baseBackgroundDrawable
            r1.draw(r8)
        L_0x0031:
            int r0 = r0 + 1
            goto L_0x0001
        L_0x0034:
            super.onDraw(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onDraw(android.graphics.Canvas):void");
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, this.tabs.size(), 1).mInfo);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i = this.mode;
        if ((i == 0 || i == 2) && super.onInterceptTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    public final void onMeasure(int i, int i2) {
        int i3;
        Context context = getContext();
        int size = this.tabs.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size) {
                break;
            }
            Tab tab = (Tab) this.tabs.get(i4);
            if (tab == null || tab.icon == null || TextUtils.isEmpty(tab.text)) {
                i4++;
            } else if (!this.inlineLabel) {
                i3 = 72;
            }
        }
        i3 = 48;
        int round = Math.round(ViewUtils.dpToPx(i3, context));
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 != Integer.MIN_VALUE) {
            if (mode2 == 0) {
                i2 = View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + round, 1073741824);
            }
        } else if (getChildCount() == 1 && View.MeasureSpec.getSize(i2) >= round) {
            getChildAt(0).setMinimumHeight(round);
        }
        int size2 = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i) != 0) {
            int i5 = this.requestedTabMaxWidth;
            if (i5 <= 0) {
                i5 = (int) (((float) size2) - ViewUtils.dpToPx(56, getContext()));
            }
            this.tabMaxWidth = i5;
        }
        super.onMeasure(i, i2);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            int i6 = this.mode;
            if (i6 != 0) {
                if (i6 == 1) {
                    if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                        return;
                    }
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), HorizontalScrollView.getChildMeasureSpec(i2, getPaddingBottom() + getPaddingTop(), childAt.getLayoutParams().height));
                } else if (i6 != 2) {
                    return;
                }
            }
            if (childAt.getMeasuredWidth() >= getMeasuredWidth()) {
                return;
            }
            childAt.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), HorizontalScrollView.getChildMeasureSpec(i2, getPaddingBottom() + getPaddingTop(), childAt.getLayoutParams().height));
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        if (motionEvent.getActionMasked() != 8 || (i = this.mode) == 0 || i == 2) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.google.android.material.tabs.TabLayout$Tab} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void populateFromPagerAdapter() {
        /*
            r7 = this;
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r0 = r7.slidingTabIndicator
            int r0 = r0.getChildCount()
            r1 = 1
            int r0 = r0 - r1
        L_0x0008:
            r2 = 0
            r3 = 0
            if (r0 < 0) goto L_0x0032
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r4 = r7.slidingTabIndicator
            android.view.View r4 = r4.getChildAt(r0)
            com.google.android.material.tabs.TabLayout$TabView r4 = (com.google.android.material.tabs.TabLayout.TabView) r4
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r5 = r7.slidingTabIndicator
            r5.removeViewAt(r0)
            if (r4 == 0) goto L_0x002c
            com.google.android.material.tabs.TabLayout$Tab r5 = r4.tab
            if (r5 == 0) goto L_0x0024
            r4.tab = r2
            r4.update()
        L_0x0024:
            r4.setSelected(r3)
            androidx.core.util.Pools$SimplePool r2 = r7.tabViewPool
            r2.release(r4)
        L_0x002c:
            r7.requestLayout()
            int r0 = r0 + -1
            goto L_0x0008
        L_0x0032:
            java.util.ArrayList r0 = r7.tabs
            java.util.Iterator r0 = r0.iterator()
        L_0x0038:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x005e
            java.lang.Object r4 = r0.next()
            com.google.android.material.tabs.TabLayout$Tab r4 = (com.google.android.material.tabs.TabLayout.Tab) r4
            r0.remove()
            r4.parent = r2
            r4.view = r2
            r4.icon = r2
            r5 = -1
            r4.id = r5
            r4.text = r2
            r4.contentDesc = r2
            r4.position = r5
            r4.customView = r2
            androidx.core.util.Pools$SynchronizedPool r5 = tabPool
            r5.release(r4)
            goto L_0x0038
        L_0x005e:
            r7.selectedTab = r2
            androidx.viewpager.widget.PagerAdapter r0 = r7.pagerAdapter
            if (r0 == 0) goto L_0x00c3
            int r0 = r0.getCount()
            r4 = r3
        L_0x0069:
            if (r4 >= r0) goto L_0x0096
            com.google.android.material.tabs.TabLayout$Tab r5 = r7.newTab()
            androidx.viewpager.widget.PagerAdapter r6 = r7.pagerAdapter
            r6.getClass()
            java.lang.CharSequence r6 = r5.contentDesc
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L_0x0087
            boolean r6 = android.text.TextUtils.isEmpty(r2)
            if (r6 != 0) goto L_0x0087
            com.google.android.material.tabs.TabLayout$TabView r6 = r5.view
            r6.setContentDescription(r2)
        L_0x0087:
            r5.text = r2
            com.google.android.material.tabs.TabLayout$TabView r6 = r5.view
            if (r6 == 0) goto L_0x0090
            r6.update()
        L_0x0090:
            r7.addTab(r5, r3)
            int r4 = r4 + 1
            goto L_0x0069
        L_0x0096:
            androidx.viewpager.widget.ViewPager r3 = r7.viewPager
            if (r3 == 0) goto L_0x00c3
            if (r0 <= 0) goto L_0x00c3
            int r0 = r3.mCurItem
            int r3 = r7.getSelectedTabPosition()
            if (r0 == r3) goto L_0x00c3
            java.util.ArrayList r3 = r7.tabs
            int r3 = r3.size()
            if (r0 >= r3) goto L_0x00c3
            if (r0 < 0) goto L_0x00c0
            java.util.ArrayList r3 = r7.tabs
            int r3 = r3.size()
            if (r0 < r3) goto L_0x00b7
            goto L_0x00c0
        L_0x00b7:
            java.util.ArrayList r2 = r7.tabs
            java.lang.Object r0 = r2.get(r0)
            r2 = r0
            com.google.android.material.tabs.TabLayout$Tab r2 = (com.google.android.material.tabs.TabLayout.Tab) r2
        L_0x00c0:
            r7.selectTab(r2, r1)
        L_0x00c3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.populateFromPagerAdapter():void");
    }

    public final void selectTab(Tab tab, boolean z) {
        int i;
        Tab tab2 = this.selectedTab;
        if (tab2 != tab) {
            if (tab != null) {
                i = tab.position;
            } else {
                i = -1;
            }
            if (z) {
                if ((tab2 == null || tab2.position == -1) && i != -1) {
                    setScrollPosition(0.0f, i, true, true);
                } else {
                    animateToTab(i);
                }
                if (i != -1) {
                    setSelectedTabView(i);
                }
            }
            this.selectedTab = tab;
            if (tab2 != null) {
                for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
                    ((ViewPagerOnTabSelectedListener) this.selectedListeners.get(size)).getClass();
                }
            }
            if (tab != null) {
                for (int size2 = this.selectedListeners.size() - 1; size2 >= 0; size2--) {
                    ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = (ViewPagerOnTabSelectedListener) this.selectedListeners.get(size2);
                    viewPagerOnTabSelectedListener.getClass();
                    viewPagerOnTabSelectedListener.viewPager.setCurrentItem(tab.position);
                }
            }
        } else if (tab2 != null) {
            for (int size3 = this.selectedListeners.size() - 1; size3 >= 0; size3--) {
                ((ViewPagerOnTabSelectedListener) this.selectedListeners.get(size3)).getClass();
            }
            animateToTab(tab.position);
        }
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public final void setPagerAdapter(PagerAdapter pagerAdapter2, boolean z) {
        PagerAdapterObserver pagerAdapterObserver2;
        PagerAdapter pagerAdapter3 = this.pagerAdapter;
        if (!(pagerAdapter3 == null || (pagerAdapterObserver2 = this.pagerAdapterObserver) == null)) {
            pagerAdapter3.mObservable.unregisterObserver(pagerAdapterObserver2);
        }
        this.pagerAdapter = pagerAdapter2;
        if (z && pagerAdapter2 != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new PagerAdapterObserver();
            }
            pagerAdapter2.mObservable.registerObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    public final void setScrollPosition(float f, int i, boolean z, boolean z2) {
        int i2;
        int round = Math.round(((float) i) + f);
        if (round >= 0 && round < this.slidingTabIndicator.getChildCount()) {
            if (z2) {
                SlidingTabIndicator slidingTabIndicator2 = this.slidingTabIndicator;
                ValueAnimator valueAnimator = slidingTabIndicator2.indicatorAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    slidingTabIndicator2.indicatorAnimator.cancel();
                }
                slidingTabIndicator2.tweenIndicatorPosition(slidingTabIndicator2.getChildAt(i), slidingTabIndicator2.getChildAt(i + 1), f);
            }
            ValueAnimator valueAnimator2 = this.scrollAnimator;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.scrollAnimator.cancel();
            }
            if (i < 0) {
                i2 = 0;
            } else {
                i2 = calculateScrollXForTab(i, f);
            }
            scrollTo(i2, 0);
            if (z) {
                setSelectedTabView(round);
            }
        }
    }

    public final void setSelectedTabView(int i) {
        boolean z;
        int childCount = this.slidingTabIndicator.getChildCount();
        if (i < childCount) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.slidingTabIndicator.getChildAt(i2);
                boolean z2 = true;
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                childAt.setSelected(z);
                if (i2 != i) {
                    z2 = false;
                }
                childAt.setActivated(z2);
            }
        }
    }

    public final void setupWithViewPager(ViewPager viewPager2, boolean z) {
        List list;
        List list2;
        ViewPager viewPager3 = this.viewPager;
        if (viewPager3 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.pageChangeListener;
            if (!(tabLayoutOnPageChangeListener == null || (list2 = viewPager3.mOnPageChangeListeners) == null)) {
                list2.remove(tabLayoutOnPageChangeListener);
            }
            AdapterChangeListener adapterChangeListener2 = this.adapterChangeListener;
            if (!(adapterChangeListener2 == null || (list = this.viewPager.mAdapterChangeListeners) == null)) {
                list.remove(adapterChangeListener2);
            }
        }
        ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = this.currentVpSelectedListener;
        if (viewPagerOnTabSelectedListener != null) {
            this.selectedListeners.remove(viewPagerOnTabSelectedListener);
            this.currentVpSelectedListener = null;
        }
        if (viewPager2 != null) {
            this.viewPager = viewPager2;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener2 = this.pageChangeListener;
            tabLayoutOnPageChangeListener2.scrollState = 0;
            tabLayoutOnPageChangeListener2.previousScrollState = 0;
            if (viewPager2.mOnPageChangeListeners == null) {
                viewPager2.mOnPageChangeListeners = new ArrayList();
            }
            viewPager2.mOnPageChangeListeners.add(tabLayoutOnPageChangeListener2);
            ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener2 = new ViewPagerOnTabSelectedListener(viewPager2);
            this.currentVpSelectedListener = viewPagerOnTabSelectedListener2;
            if (!this.selectedListeners.contains(viewPagerOnTabSelectedListener2)) {
                this.selectedListeners.add(viewPagerOnTabSelectedListener2);
            }
            PagerAdapter pagerAdapter2 = viewPager2.mAdapter;
            if (pagerAdapter2 != null) {
                setPagerAdapter(pagerAdapter2, true);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new AdapterChangeListener();
            }
            AdapterChangeListener adapterChangeListener3 = this.adapterChangeListener;
            adapterChangeListener3.autoRefresh = true;
            if (viewPager2.mAdapterChangeListeners == null) {
                viewPager2.mAdapterChangeListeners = new ArrayList();
            }
            viewPager2.mAdapterChangeListeners.add(adapterChangeListener3);
            setScrollPosition(0.0f, viewPager2.mCurItem, true, true);
        } else {
            this.viewPager = null;
            setPagerAdapter((PagerAdapter) null, false);
        }
        this.setupViewPagerImplicitly = z;
    }

    public final boolean shouldDelayChildPressedState() {
        if (Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight()) > 0) {
            return true;
        }
        return false;
    }

    public final void updateTabViews(boolean z) {
        for (int i = 0; i < this.slidingTabIndicator.getChildCount(); i++) {
            View childAt = this.slidingTabIndicator.getChildAt(i);
            int i2 = this.requestedTabMinWidth;
            if (i2 == -1) {
                int i3 = this.mode;
                if (i3 == 0 || i3 == 2) {
                    i2 = this.scrollableTabMinWidth;
                } else {
                    i2 = 0;
                }
            }
            childAt.setMinimumWidth(i2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            if (this.mode == 1 && this.tabGravity == 0) {
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            } else {
                layoutParams.width = -2;
                layoutParams.weight = 0.0f;
            }
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130970158);
    }

    public final void addView(View view, int i) {
        addViewInternal(view);
    }

    /* renamed from: generateLayoutParams  reason: collision with other method in class */
    public final FrameLayout.LayoutParams m827generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r15v46, types: [com.google.android.material.tabs.TabIndicatorInterpolator, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r15v47, types: [com.google.android.material.tabs.TabIndicatorInterpolator, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r15v48, types: [com.google.android.material.tabs.TabIndicatorInterpolator, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x025e, code lost:
        if (r14 != 2) goto L_0x0270;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TabLayout(android.content.Context r13, android.util.AttributeSet r14, int r15) {
        /*
            r12 = this;
            r0 = 2132018529(0x7f140561, float:1.9675367E38)
            android.content.Context r13 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r13, r14, r15, r0)
            r12.<init>(r13, r14, r15)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            r12.tabs = r13
            r13 = 0
            r12.tabSelectedIndicatorColor = r13
            r0 = 2147483647(0x7fffffff, float:NaN)
            r12.tabMaxWidth = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r12.selectedListeners = r0
            androidx.core.util.Pools$SimplePool r0 = new androidx.core.util.Pools$SimplePool
            r1 = 12
            r0.<init>(r1)
            r12.tabViewPool = r0
            android.content.Context r0 = r12.getContext()
            r12.setHorizontalScrollBarEnabled(r13)
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r8 = new com.google.android.material.tabs.TabLayout$SlidingTabIndicator
            r8.<init>(r0)
            r12.slidingTabIndicator = r8
            android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
            r3 = -2
            r9 = -1
            r2.<init>(r3, r9)
            super.addView(r8, r13, r2)
            int[] r4 = com.google.android.material.R$styleable.TabLayout
            r10 = 23
            int[] r7 = new int[]{r10}
            r6 = 2132018529(0x7f140561, float:1.9675367E38)
            r2 = r0
            r3 = r14
            r5 = r15
            android.content.res.TypedArray r14 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r2, r3, r4, r5, r6, r7)
            android.graphics.drawable.Drawable r15 = r12.getBackground()
            boolean r15 = r15 instanceof android.graphics.drawable.ColorDrawable
            if (r15 == 0) goto L_0x0080
            android.graphics.drawable.Drawable r15 = r12.getBackground()
            android.graphics.drawable.ColorDrawable r15 = (android.graphics.drawable.ColorDrawable) r15
            com.google.android.material.shape.MaterialShapeDrawable r2 = new com.google.android.material.shape.MaterialShapeDrawable
            r2.<init>()
            int r15 = r15.getColor()
            android.content.res.ColorStateList r15 = android.content.res.ColorStateList.valueOf(r15)
            r2.setFillColor(r15)
            r2.initializeElevationOverlay(r0)
            java.util.WeakHashMap r15 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            float r15 = androidx.core.view.ViewCompat.Api21Impl.getElevation(r12)
            r2.setElevation(r15)
            androidx.core.view.ViewCompat.Api16Impl.setBackground(r12, r2)
        L_0x0080:
            r15 = 5
            android.graphics.drawable.Drawable r15 = com.google.android.material.resources.MaterialResources.getDrawable(r0, r14, r15)
            if (r15 != 0) goto L_0x008c
            android.graphics.drawable.GradientDrawable r15 = new android.graphics.drawable.GradientDrawable
            r15.<init>()
        L_0x008c:
            android.graphics.drawable.Drawable r15 = r15.mutate()
            r12.tabSelectedIndicator = r15
            int r2 = r12.tabSelectedIndicatorColor
            r3 = 0
            if (r2 == 0) goto L_0x009b
            r15.setTint(r2)
            goto L_0x009e
        L_0x009b:
            r15.setTintList(r3)
        L_0x009e:
            android.graphics.drawable.Drawable r15 = r12.tabSelectedIndicator
            int r15 = r15.getIntrinsicHeight()
            android.graphics.drawable.Drawable r2 = r12.tabSelectedIndicator
            android.graphics.Rect r2 = r2.getBounds()
            android.graphics.drawable.Drawable r4 = r12.tabSelectedIndicator
            int r5 = r2.left
            int r2 = r2.right
            r4.setBounds(r5, r13, r2, r15)
            r8.requestLayout()
            r15 = 8
            int r15 = r14.getColor(r15, r13)
            r12.tabSelectedIndicatorColor = r15
            android.graphics.drawable.Drawable r2 = r12.tabSelectedIndicator
            if (r15 == 0) goto L_0x00c6
            r2.setTint(r15)
            goto L_0x00c9
        L_0x00c6:
            r2.setTintList(r3)
        L_0x00c9:
            r12.updateTabViews(r13)
            r15 = 11
            int r15 = r14.getDimensionPixelSize(r15, r9)
            android.graphics.drawable.Drawable r2 = r12.tabSelectedIndicator
            android.graphics.Rect r2 = r2.getBounds()
            android.graphics.drawable.Drawable r4 = r12.tabSelectedIndicator
            int r5 = r2.left
            int r2 = r2.right
            r4.setBounds(r5, r13, r2, r15)
            r8.requestLayout()
            r15 = 10
            int r15 = r14.getInt(r15, r13)
            int r2 = r12.tabIndicatorGravity
            if (r2 == r15) goto L_0x00f5
            r12.tabIndicatorGravity = r15
            java.util.WeakHashMap r15 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.postInvalidateOnAnimation(r8)
        L_0x00f5:
            r15 = 7
            int r15 = r14.getInt(r15, r13)
            r2 = 2
            r4 = 1
            if (r15 == 0) goto L_0x0129
            if (r15 == r4) goto L_0x0121
            if (r15 != r2) goto L_0x010a
            com.google.android.material.tabs.FadeTabIndicatorInterpolator r15 = new com.google.android.material.tabs.FadeTabIndicatorInterpolator
            r15.<init>()
            r12.tabIndicatorInterpolator = r15
            goto L_0x0130
        L_0x010a:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r15)
            java.lang.String r14 = " is not a valid TabIndicatorAnimationMode"
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L_0x0121:
            com.google.android.material.tabs.ElasticTabIndicatorInterpolator r15 = new com.google.android.material.tabs.ElasticTabIndicatorInterpolator
            r15.<init>()
            r12.tabIndicatorInterpolator = r15
            goto L_0x0130
        L_0x0129:
            com.google.android.material.tabs.TabIndicatorInterpolator r15 = new com.google.android.material.tabs.TabIndicatorInterpolator
            r15.<init>()
            r12.tabIndicatorInterpolator = r15
        L_0x0130:
            r15 = 9
            boolean r15 = r14.getBoolean(r15, r4)
            r12.tabIndicatorFullWidth = r15
            r8.jumpIndicatorToSelectedPosition()
            java.util.WeakHashMap r15 = androidx.core.view.ViewCompat.sViewPropertyAnimatorMap
            androidx.core.view.ViewCompat.Api16Impl.postInvalidateOnAnimation(r8)
            r15 = 16
            int r15 = r14.getDimensionPixelSize(r15, r13)
            r12.tabPaddingBottom = r15
            r12.tabPaddingEnd = r15
            r12.tabPaddingTop = r15
            r12.tabPaddingStart = r15
            r5 = 19
            int r5 = r14.getDimensionPixelSize(r5, r15)
            r12.tabPaddingStart = r5
            r6 = 20
            int r6 = r14.getDimensionPixelSize(r6, r15)
            r12.tabPaddingTop = r6
            r6 = 18
            int r6 = r14.getDimensionPixelSize(r6, r15)
            r12.tabPaddingEnd = r6
            r6 = 17
            int r15 = r14.getDimensionPixelSize(r6, r15)
            r12.tabPaddingBottom = r15
            r15 = 2132017989(0x7f140345, float:1.9674272E38)
            int r15 = r14.getResourceId(r10, r15)
            r12.tabTextAppearance = r15
            int[] r6 = androidx.appcompat.R$styleable.TextAppearance
            android.content.res.TypedArray r15 = r0.obtainStyledAttributes(r15, r6)
            int r6 = r15.getDimensionPixelSize(r13, r13)     // Catch:{ all -> 0x0274 }
            float r6 = (float) r6     // Catch:{ all -> 0x0274 }
            r12.tabTextSize = r6     // Catch:{ all -> 0x0274 }
            r6 = 3
            android.content.res.ColorStateList r7 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r0, (android.content.res.TypedArray) r15, (int) r6)     // Catch:{ all -> 0x0274 }
            r12.tabTextColors = r7     // Catch:{ all -> 0x0274 }
            r15.recycle()
            r15 = 24
            boolean r7 = r14.hasValue(r15)
            if (r7 == 0) goto L_0x019c
            android.content.res.ColorStateList r15 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r0, (android.content.res.TypedArray) r14, (int) r15)
            r12.tabTextColors = r15
        L_0x019c:
            r15 = 22
            boolean r7 = r14.hasValue(r15)
            if (r7 == 0) goto L_0x01c1
            int r15 = r14.getColor(r15, r13)
            android.content.res.ColorStateList r7 = r12.tabTextColors
            int r7 = r7.getDefaultColor()
            int[] r10 = android.widget.HorizontalScrollView.SELECTED_STATE_SET
            int[] r11 = android.widget.HorizontalScrollView.EMPTY_STATE_SET
            int[][] r10 = new int[][]{r10, r11}
            int[] r15 = new int[]{r15, r7}
            android.content.res.ColorStateList r7 = new android.content.res.ColorStateList
            r7.<init>(r10, r15)
            r12.tabTextColors = r7
        L_0x01c1:
            android.content.res.ColorStateList r15 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r0, (android.content.res.TypedArray) r14, (int) r6)
            r12.tabIconTint = r15
            r15 = 4
            int r15 = r14.getInt(r15, r9)
            android.graphics.PorterDuff$Mode r15 = com.google.android.material.internal.ViewUtils.parseTintMode(r15, r3)
            r12.tabIconTintMode = r15
            r15 = 21
            android.content.res.ColorStateList r15 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r0, (android.content.res.TypedArray) r14, (int) r15)
            r12.tabRippleColorStateList = r15
            r15 = 6
            r0 = 300(0x12c, float:4.2E-43)
            int r15 = r14.getInt(r15, r0)
            r12.tabIndicatorAnimationDuration = r15
            r15 = 14
            int r15 = r14.getDimensionPixelSize(r15, r9)
            r12.requestedTabMinWidth = r15
            r15 = 13
            int r15 = r14.getDimensionPixelSize(r15, r9)
            r12.requestedTabMaxWidth = r15
            int r15 = r14.getResourceId(r13, r13)
            r12.tabBackgroundResId = r15
            int r15 = r14.getDimensionPixelSize(r4, r13)
            r0 = 15
            int r0 = r14.getInt(r0, r4)
            r12.mode = r0
            int r3 = r14.getInt(r2, r13)
            r12.tabGravity = r3
            boolean r1 = r14.getBoolean(r1, r13)
            r12.inlineLabel = r1
            r1 = 25
            boolean r1 = r14.getBoolean(r1, r13)
            r12.unboundedRipple = r1
            r14.recycle()
            android.content.res.Resources r14 = r12.getResources()
            r1 = 2131165758(0x7f07023e, float:1.7945742E38)
            int r1 = r14.getDimensionPixelSize(r1)
            float r1 = (float) r1
            r12.tabTextMultiLineSize = r1
            r1 = 2131165756(0x7f07023c, float:1.7945738E38)
            int r14 = r14.getDimensionPixelSize(r1)
            r12.scrollableTabMinWidth = r14
            if (r0 == 0) goto L_0x023a
            if (r0 != r2) goto L_0x0238
            goto L_0x023a
        L_0x0238:
            r14 = r13
            goto L_0x023f
        L_0x023a:
            int r15 = r15 - r5
            int r14 = java.lang.Math.max(r13, r15)
        L_0x023f:
            androidx.core.view.ViewCompat.Api17Impl.setPaddingRelative(r8, r14, r13, r13, r13)
            java.lang.String r13 = "TabLayout"
            if (r0 == 0) goto L_0x0258
            if (r0 == r4) goto L_0x024b
            if (r0 == r2) goto L_0x024b
            goto L_0x0270
        L_0x024b:
            int r14 = r12.tabGravity
            if (r14 != r2) goto L_0x0254
            java.lang.String r14 = "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead"
            android.util.Log.w(r13, r14)
        L_0x0254:
            r8.setGravity(r4)
            goto L_0x0270
        L_0x0258:
            int r14 = r12.tabGravity
            if (r14 == 0) goto L_0x0265
            if (r14 == r4) goto L_0x0261
            if (r14 == r2) goto L_0x026a
            goto L_0x0270
        L_0x0261:
            r8.setGravity(r4)
            goto L_0x0270
        L_0x0265:
            java.lang.String r14 = "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead"
            android.util.Log.w(r13, r14)
        L_0x026a:
            r13 = 8388611(0x800003, float:1.1754948E-38)
            r8.setGravity(r13)
        L_0x0270:
            r12.updateTabViews(r4)
            return
        L_0x0274:
            r12 = move-exception
            r15.recycle()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }
}
