package com.google.android.setupdesign.items;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class RecyclerItemAdapter extends RecyclerView.Adapter implements ItemHierarchy$Observer {
    public final boolean applyPartnerHeavyThemeResource;
    public final AbstractItemHierarchy itemHierarchy;
    public final boolean useFullDynamicColor;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    class PatchedLayerDrawable extends LayerDrawable {
        public final boolean getPadding(Rect rect) {
            if (!super.getPadding(rect) || (rect.left == 0 && rect.top == 0 && rect.right == 0 && rect.bottom == 0)) {
                return false;
            }
            return true;
        }
    }

    public RecyclerItemAdapter(AbstractItemHierarchy abstractItemHierarchy, boolean z, boolean z2) {
        this.applyPartnerHeavyThemeResource = z;
        this.useFullDynamicColor = z2;
        this.itemHierarchy = abstractItemHierarchy;
        abstractItemHierarchy.observers.add(this);
    }

    public final int getItemCount() {
        return this.itemHierarchy.getCount();
    }

    public final long getItemId(int i) {
        int i2;
        AbstractItem itemAt = this.itemHierarchy.getItemAt(i);
        if (!(itemAt instanceof AbstractItem) || (i2 = itemAt.id) <= 0) {
            return -1;
        }
        return (long) i2;
    }

    public final int getItemViewType(int i) {
        return this.itemHierarchy.getItemAt(i).getLayoutResource();
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
        AbstractItem itemAt = this.itemHierarchy.getItemAt(i);
        boolean isEnabled = itemAt.isEnabled();
        itemViewHolder.isEnabled = isEnabled;
        View view = itemViewHolder.itemView;
        view.setClickable(isEnabled);
        view.setEnabled(isEnabled);
        view.setFocusable(isEnabled);
        itemViewHolder.item = itemAt;
        itemAt.onBindView(view);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.setupdesign.items.ItemViewHolder, androidx.recyclerview.widget.RecyclerView$ViewHolder] */
    public final RecyclerView.ViewHolder onCreateViewHolder(int i, RecyclerView recyclerView) {
        Drawable drawable;
        View inflate = LayoutInflater.from(recyclerView.getContext()).inflate(i, recyclerView, false);
        final ? viewHolder = new RecyclerView.ViewHolder(inflate);
        if (!"noBackground".equals(inflate.getTag())) {
            TypedArray obtainStyledAttributes = recyclerView.getContext().obtainStyledAttributes(R$styleable.SudRecyclerItemAdapter);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(1);
            if (drawable2 == null) {
                drawable2 = obtainStyledAttributes.getDrawable(2);
                drawable = null;
            } else {
                Drawable background = inflate.getBackground();
                if (background == null) {
                    if (!this.applyPartnerHeavyThemeResource || this.useFullDynamicColor) {
                        drawable = obtainStyledAttributes.getDrawable(0);
                    } else {
                        background = new ColorDrawable(PartnerConfigHelper.get(inflate.getContext()).getColor(inflate.getContext(), PartnerConfig.CONFIG_LAYOUT_BACKGROUND_COLOR));
                    }
                }
                drawable = background;
            }
            if (drawable2 == null || drawable == null) {
                Log.e("RecyclerItemAdapter", "Cannot resolve required attributes. selectableItemBackground=" + drawable2 + " background=" + drawable);
            } else {
                inflate.setBackgroundDrawable(new LayerDrawable(new Drawable[]{drawable, drawable2}));
            }
            obtainStyledAttributes.recycle();
        }
        inflate.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AbstractItem abstractItem = viewHolder.item;
                RecyclerItemAdapter.this.getClass();
            }
        });
        return viewHolder;
    }

    public final void onItemRangeChanged(AbstractItemHierarchy abstractItemHierarchy, int i) {
        this.mObservable.notifyItemRangeChanged(i, 1, (Object) null);
    }

    public final void onItemRangeInserted(AbstractItemHierarchy abstractItemHierarchy, int i, int i2) {
        notifyItemRangeInserted(i, i2);
    }
}
