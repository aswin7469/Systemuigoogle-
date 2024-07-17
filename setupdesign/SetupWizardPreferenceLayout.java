package com.google.android.setupdesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupdesign.template.RecyclerMixin;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class SetupWizardPreferenceLayout extends SetupWizardRecyclerLayout {
    public SetupWizardPreferenceLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final ViewGroup findContainer(int i) {
        if (i == 0) {
            i = 2131363735;
        }
        return super.findContainer(i);
    }

    public final View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        if (i == 0) {
            i = 2131559128;
        }
        return super.onInflateTemplate(layoutInflater, i);
    }

    public final void onTemplateInflated() {
        this.recyclerMixin = new RecyclerMixin(this, (RecyclerView) LayoutInflater.from(getContext()).inflate(2131559125, this, false));
    }
}
