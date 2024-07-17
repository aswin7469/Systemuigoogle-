package com.google.android.setupdesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.setupdesign.template.ListMixin;
import com.google.android.setupdesign.template.RequireScrollMixin;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class SetupWizardListLayout extends SetupWizardLayout {
    public final ListMixin listMixin;

    public SetupWizardListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            ListMixin listMixin2 = new ListMixin(this, attributeSet);
            this.listMixin = listMixin2;
            registerMixin(ListMixin.class, listMixin2);
            this.listMixin.getListViewInternal();
            ((RequireScrollMixin) getMixin(RequireScrollMixin.class)).getClass();
        }
    }

    public final ViewGroup findContainer(int i) {
        if (i == 0) {
            i = 16908298;
        }
        return super.findContainer(i);
    }

    public final View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        if (i == 0) {
            i = 2131559112;
        }
        return super.onInflateTemplate(layoutInflater, i);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        ListMixin listMixin2 = this.listMixin;
        if (listMixin2.divider == null) {
            listMixin2.updateDivider();
        }
    }
}
