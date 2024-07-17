package com.google.android.setupdesign.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import com.google.android.setupcompat.internal.TemplateLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.template.Mixin;
import com.google.android.setupdesign.R$styleable;
import com.google.android.setupdesign.items.ItemAdapter;
import com.google.android.setupdesign.items.ItemGroup;
import com.google.android.setupdesign.items.ItemInflater;
import com.google.android.setupdesign.util.PartnerStyleHelper;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ListMixin implements Mixin {
    public Drawable defaultDivider;
    public Drawable divider;
    public final int dividerInsetEnd;
    public final int dividerInsetStart;
    public ListView listView;
    public final TemplateLayout templateLayout;

    /* JADX INFO: finally extract failed */
    public ListMixin(TemplateLayout templateLayout2, AttributeSet attributeSet) {
        this.templateLayout = templateLayout2;
        Context context = templateLayout2.getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudListMixin, 0, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            ItemInflater itemInflater = new ItemInflater(context);
            XmlResourceParser xml = itemInflater.resources.getXml(resourceId);
            try {
                Object inflate = itemInflater.inflate(xml);
                xml.close();
                ItemAdapter itemAdapter = new ItemAdapter((ItemGroup) inflate);
                ListView listViewInternal = getListViewInternal();
                if (listViewInternal != null) {
                    listViewInternal.setAdapter(itemAdapter);
                }
            } catch (Throwable th) {
                xml.close();
                throw th;
            }
        }
        boolean z = obtainStyledAttributes.getBoolean(4, true);
        if (PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout2)) {
            PartnerConfigHelper partnerConfigHelper = PartnerConfigHelper.get(context);
            PartnerConfig partnerConfig = PartnerConfig.CONFIG_ITEMS_DIVIDER_SHOWN;
            if (partnerConfigHelper.isPartnerConfigAvailable(partnerConfig)) {
                z = PartnerConfigHelper.get(context).getBoolean(context, partnerConfig, true);
            }
        }
        if (z) {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(1, -1);
            if (dimensionPixelSize != -1) {
                this.dividerInsetStart = dimensionPixelSize;
                this.dividerInsetEnd = 0;
                updateDivider();
            } else {
                int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(3, 0);
                int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(2, 0);
                if (PartnerStyleHelper.shouldApplyPartnerResource((View) templateLayout2)) {
                    PartnerConfigHelper partnerConfigHelper2 = PartnerConfigHelper.get(context);
                    PartnerConfig partnerConfig2 = PartnerConfig.CONFIG_LAYOUT_MARGIN_START;
                    dimensionPixelSize2 = partnerConfigHelper2.isPartnerConfigAvailable(partnerConfig2) ? (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig2, 0.0f) : dimensionPixelSize2;
                    PartnerConfigHelper partnerConfigHelper3 = PartnerConfigHelper.get(context);
                    PartnerConfig partnerConfig3 = PartnerConfig.CONFIG_LAYOUT_MARGIN_END;
                    if (partnerConfigHelper3.isPartnerConfigAvailable(partnerConfig3)) {
                        dimensionPixelSize3 = (int) PartnerConfigHelper.get(context).getDimension(context, partnerConfig3, 0.0f);
                    }
                }
                this.dividerInsetStart = dimensionPixelSize2;
                this.dividerInsetEnd = dimensionPixelSize3;
                updateDivider();
            }
        } else {
            getListViewInternal().setDivider((Drawable) null);
        }
        obtainStyledAttributes.recycle();
    }

    public final ListView getListViewInternal() {
        if (this.listView == null) {
            View findManagedViewById = this.templateLayout.findManagedViewById(16908298);
            if (findManagedViewById instanceof ListView) {
                this.listView = (ListView) findManagedViewById;
            }
        }
        return this.listView;
    }

    public final void updateDivider() {
        InsetDrawable insetDrawable;
        ListView listViewInternal = getListViewInternal();
        if (listViewInternal != null) {
            TemplateLayout templateLayout2 = this.templateLayout;
            if (templateLayout2.isLayoutDirectionResolved()) {
                if (this.defaultDivider == null) {
                    this.defaultDivider = listViewInternal.getDivider();
                }
                Drawable drawable = this.defaultDivider;
                if (drawable != null) {
                    int i = this.dividerInsetStart;
                    int i2 = this.dividerInsetEnd;
                    if (templateLayout2.getLayoutDirection() == 1) {
                        insetDrawable = new InsetDrawable(drawable, i2, 0, i, 0);
                    } else {
                        insetDrawable = new InsetDrawable(drawable, i, 0, i2, 0);
                    }
                    this.divider = insetDrawable;
                    listViewInternal.setDivider(insetDrawable);
                }
            }
        }
    }
}
