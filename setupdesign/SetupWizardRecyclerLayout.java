package com.google.android.setupdesign;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupdesign.template.RecyclerMixin;
import com.google.android.setupdesign.template.RequireScrollMixin;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class SetupWizardRecyclerLayout extends SetupWizardLayout {
    public RecyclerMixin recyclerMixin;

    public SetupWizardRecyclerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.recyclerMixin.parseAttributes(attributeSet);
            registerMixin(RecyclerMixin.class, this.recyclerMixin);
            RecyclerView recyclerView = this.recyclerMixin.recyclerView;
            ((RequireScrollMixin) getMixin(RequireScrollMixin.class)).getClass();
        }
    }

    public ViewGroup findContainer(int i) {
        if (i == 0) {
            i = 2131363755;
        }
        return super.findContainer(i);
    }

    public final View findManagedViewById(int i) {
        View findViewById;
        View view = this.recyclerMixin.header;
        if (view == null || (findViewById = view.findViewById(i)) == null) {
            return super.findViewById(i);
        }
        return findViewById;
    }

    public View onInflateTemplate(LayoutInflater layoutInflater, int i) {
        if (i == 0) {
            i = 2131559133;
        }
        return super.onInflateTemplate(layoutInflater, i);
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        RecyclerMixin recyclerMixin2 = this.recyclerMixin;
        if (recyclerMixin2.divider == null) {
            recyclerMixin2.updateDivider$1();
        }
    }

    public void onTemplateInflated() {
        View findViewById = findViewById(2131363755);
        if (findViewById instanceof RecyclerView) {
            this.recyclerMixin = new RecyclerMixin(this, (RecyclerView) findViewById);
            return;
        }
        throw new IllegalStateException("SetupWizardRecyclerLayout should use a template with recycler view");
    }
}
