package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.util.LayoutStyler;
import com.google.android.setupdesign.util.PartnerStyleHelper;
import com.google.android.setupdesign.util.TextViewPartnerStyler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class Item extends AbstractItem {
    public final CharSequence contentDescription;
    public final boolean enabled = true;
    public final Drawable icon;
    public int iconGravity = 16;
    public final int iconTint = 0;
    public final int layoutRes;
    public final CharSequence summary;
    public final CharSequence title;
    public final boolean visible = true;

    public Item(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudItem);
        this.enabled = obtainStyledAttributes.getBoolean(1, true);
        this.icon = obtainStyledAttributes.getDrawable(0);
        this.title = obtainStyledAttributes.getText(4);
        this.summary = obtainStyledAttributes.getText(5);
        this.contentDescription = obtainStyledAttributes.getText(6);
        this.layoutRes = obtainStyledAttributes.getResourceId(2, getDefaultLayoutResource());
        this.visible = obtainStyledAttributes.getBoolean(3, true);
        this.iconTint = obtainStyledAttributes.getColor(8, 0);
        this.iconGravity = obtainStyledAttributes.getInt(7, 16);
        obtainStyledAttributes.recycle();
    }

    public final int getCount() {
        return this.visible ? 1 : 0;
    }

    public int getDefaultLayoutResource() {
        return 2131559104;
    }

    public final int getLayoutResource() {
        return this.layoutRes;
    }

    public CharSequence getSummary() {
        return this.summary;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void onBindView(View view) {
        float f;
        float f2;
        View view2 = view;
        ((TextView) view2.findViewById(2131363731)).setText(this.title);
        TextView textView = (TextView) view2.findViewById(2131363728);
        CharSequence summary2 = getSummary();
        if (summary2 == null || summary2.length() <= 0) {
            textView.setVisibility(8);
        } else {
            textView.setText(summary2);
            textView.setVisibility(0);
        }
        view2.setContentDescription(this.contentDescription);
        View findViewById = view2.findViewById(2131363727);
        Drawable drawable = this.icon;
        if (drawable != null) {
            ImageView imageView = (ImageView) view2.findViewById(2131363726);
            imageView.setImageDrawable((Drawable) null);
            imageView.setImageState(drawable.getState(), false);
            imageView.setImageLevel(drawable.getLevel());
            imageView.setImageDrawable(drawable);
            int i = this.iconTint;
            if (i != 0) {
                imageView.setColorFilter(i);
            } else {
                imageView.clearColorFilter();
            }
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                ((LinearLayout.LayoutParams) layoutParams).gravity = this.iconGravity;
            }
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(8);
        }
        view2.setId(this.id);
        if (!(this instanceof ExpandableSwitchItem) && view.getId() != 2131363738) {
            LayoutStyler.applyPartnerCustomizationLayoutPaddingStyle(view);
        }
        if (PartnerStyleHelper.shouldApplyPartnerHeavyThemeResource(view)) {
            TextView textView2 = (TextView) view2.findViewById(2131363731);
            if (PartnerStyleHelper.shouldApplyPartnerHeavyThemeResource(textView2)) {
                TextViewPartnerStyler.applyPartnerCustomizationStyle(textView2, new TextViewPartnerStyler.TextPartnerConfigs((PartnerConfig) null, (PartnerConfig) null, PartnerConfig.CONFIG_ITEMS_TITLE_TEXT_SIZE, PartnerConfig.CONFIG_ITEMS_TITLE_FONT_FAMILY, (PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, (PartnerConfig) null, PartnerStyleHelper.getLayoutGravity(textView2.getContext())));
            }
            TextView textView3 = (TextView) view2.findViewById(2131363728);
            if (textView3.getVisibility() == 8 && (view2 instanceof LinearLayout)) {
                ((LinearLayout) view2).setGravity(16);
            }
            if (PartnerStyleHelper.shouldApplyPartnerHeavyThemeResource(textView3)) {
                TextViewPartnerStyler.applyPartnerCustomizationStyle(textView3, new TextViewPartnerStyler.TextPartnerConfigs((PartnerConfig) null, (PartnerConfig) null, PartnerConfig.CONFIG_ITEMS_SUMMARY_TEXT_SIZE, PartnerConfig.CONFIG_ITEMS_SUMMARY_FONT_FAMILY, (PartnerConfig) null, (PartnerConfig) null, PartnerConfig.CONFIG_ITEMS_SUMMARY_MARGIN_TOP, (PartnerConfig) null, PartnerStyleHelper.getLayoutGravity(textView3.getContext())));
            }
            Context context = view.getContext();
            PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig = PartnerConfig.CONFIG_ITEMS_PADDING_TOP;
            if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                f = PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f);
            } else {
                f = (float) view.getPaddingTop();
            }
            PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_ITEMS_PADDING_BOTTOM;
            if (partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig2)) {
                f2 = PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f);
            } else {
                f2 = (float) view.getPaddingBottom();
            }
            if (!(f == ((float) view.getPaddingTop()) && f2 == ((float) view.getPaddingBottom()))) {
                view2.setPadding(view.getPaddingStart(), (int) f, view.getPaddingEnd(), (int) f2);
            }
            PartnerConfigHelper partnerConfigHelper3 = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_ITEMS_MIN_HEIGHT;
            if (partnerConfigHelper3.isPartnerConfigAvailable(partnerConfig3)) {
                view2.setMinimumHeight((int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig3, 0.0f));
            }
        }
    }
}
