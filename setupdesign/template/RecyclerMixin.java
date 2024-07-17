package com.google.android.setupdesign.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.DividerItemDecoration;
import com.google.android.setupdesign.GlifLayout;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.items.AbstractItemHierarchy;
import com.google.android.setupdesign.items.ItemInflater;
import com.google.android.setupdesign.items.RecyclerItemAdapter;
import com.google.android.setupdesign.util.PartnerStyleHelper;
import com.google.android.setupdesign.view.HeaderRecyclerView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class RecyclerMixin implements Mixin {
    public Drawable defaultDivider;
    public Drawable divider;
    public final DividerItemDecoration dividerDecoration;
    public int dividerInsetEnd;
    public int dividerInsetStart;
    public final View header;
    public final boolean isDividerDisplay = true;
    public final RecyclerView recyclerView;
    public final TemplateLayout templateLayout;

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, androidx.recyclerview.widget.RecyclerView$ItemDecoration, com.google.android.setupdesign.DividerItemDecoration] */
    public RecyclerMixin(TemplateLayout templateLayout2, RecyclerView recyclerView2) {
        boolean z = true;
        this.templateLayout = templateLayout2;
        Context context = templateLayout2.getContext();
        ? obj = new Object();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R$styleable.SudDividerItemDecoration);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        int i = obtainStyledAttributes.getInt(2, 0);
        obtainStyledAttributes.recycle();
        if (drawable != null) {
            obj.dividerIntrinsicHeight = drawable.getIntrinsicHeight();
        } else {
            obj.dividerIntrinsicHeight = 0;
        }
        obj.divider = drawable;
        obj.dividerHeight = dimensionPixelSize;
        obj.dividerCondition = i;
        this.dividerDecoration = obj;
        this.recyclerView = recyclerView2;
        templateLayout2.getContext();
        recyclerView2.setLayoutManager(new LinearLayoutManager(1));
        if (recyclerView2 instanceof HeaderRecyclerView) {
            this.header = ((HeaderRecyclerView) recyclerView2).header;
        }
        Context context2 = templateLayout2.getContext();
        TypedValue typedValue = new TypedValue();
        context2.getTheme().resolveAttribute(2130970057, typedValue, true);
        z = typedValue.data == 0 ? false : z;
        if (PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout2)) {
            PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(recyclerView2.getContext());
            PartnerConfig partnerConfig = PartnerConfig.CONFIG_ITEMS_DIVIDER_SHOWN;
            if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                z = PartnerConfigHelper.get(recyclerView2.getContext()).getBoolean(recyclerView2.getContext(), partnerConfig, z);
            }
        }
        this.isDividerDisplay = z;
        if (z) {
            recyclerView2.addItemDecoration(obj);
        }
    }

    /* JADX INFO: finally extract failed */
    public final void parseAttributes(AttributeSet attributeSet) {
        boolean z;
        boolean z2;
        TemplateLayout templateLayout2 = this.templateLayout;
        Context context = templateLayout2.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudRecyclerMixin, 0, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            ItemInflater itemInflater = new ItemInflater(context);
            XmlResourceParser xml = itemInflater.resources.getXml(resourceId);
            try {
                Object inflate = itemInflater.inflate(xml);
                xml.close();
                AbstractItemHierarchy abstractItemHierarchy = (AbstractItemHierarchy) inflate;
                if (templateLayout2 instanceof GlifLayout) {
                    GlifLayout glifLayout = (GlifLayout) templateLayout2;
                    z = glifLayout.shouldApplyPartnerHeavyThemeResource();
                    z2 = glifLayout.useFullDynamicColor();
                } else {
                    z2 = false;
                    z = false;
                }
                RecyclerItemAdapter recyclerItemAdapter = new RecyclerItemAdapter(abstractItemHierarchy, z, z2);
                recyclerItemAdapter.setHasStableIds(obtainStyledAttributes.getBoolean(4, false));
                this.recyclerView.setAdapter(recyclerItemAdapter);
            } catch (Throwable th) {
                xml.close();
                throw th;
            }
        }
        if (!this.isDividerDisplay) {
            obtainStyledAttributes.recycle();
            return;
        }
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize != -1) {
            this.dividerInsetStart = dimensionPixelSize;
            this.dividerInsetEnd = 0;
            updateDivider$1();
        } else {
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(3, 0);
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(2, 0);
            if (PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout2)) {
                PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
                PartnerConfig partnerConfig = PartnerConfig.CONFIG_LAYOUT_MARGIN_START;
                if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                    dimensionPixelSize2 = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig, 0.0f);
                }
                PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context);
                PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_LAYOUT_MARGIN_END;
                if (partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig2)) {
                    dimensionPixelSize3 = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f);
                }
            }
            this.dividerInsetStart = dimensionPixelSize2;
            this.dividerInsetEnd = dimensionPixelSize3;
            updateDivider$1();
        }
        obtainStyledAttributes.recycle();
    }

    public final void updateDivider$1() {
        InsetDrawable insetDrawable;
        TemplateLayout templateLayout2 = this.templateLayout;
        if (templateLayout2.isLayoutDirectionResolved()) {
            Drawable drawable = this.defaultDivider;
            DividerItemDecoration dividerItemDecoration = this.dividerDecoration;
            if (drawable == null) {
                this.defaultDivider = dividerItemDecoration.divider;
            }
            Drawable drawable2 = this.defaultDivider;
            int i = this.dividerInsetStart;
            int i2 = this.dividerInsetEnd;
            if (templateLayout2.getLayoutDirection() == 1) {
                insetDrawable = new InsetDrawable(drawable2, i2, 0, i, 0);
            } else {
                insetDrawable = new InsetDrawable(drawable2, i, 0, i2, 0);
            }
            this.divider = insetDrawable;
            dividerItemDecoration.getClass();
            dividerItemDecoration.dividerIntrinsicHeight = insetDrawable.getIntrinsicHeight();
            dividerItemDecoration.divider = insetDrawable;
        }
    }
}
