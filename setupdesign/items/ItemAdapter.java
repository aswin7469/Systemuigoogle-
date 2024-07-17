package com.google.android.setupdesign.items;

import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ItemAdapter extends BaseAdapter implements ItemHierarchy$Observer {
    public final AbstractItemHierarchy itemHierarchy;
    public final ViewTypes viewTypes = new ViewTypes();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ViewTypes {
        public int nextPosition = 0;
        public final SparseIntArray positionMap = new SparseIntArray();
    }

    public ItemAdapter(ItemGroup itemGroup) {
        this.itemHierarchy = itemGroup;
        itemGroup.observers.add(this);
        refreshViewTypes();
    }

    public final int getCount() {
        return this.itemHierarchy.getCount();
    }

    public final Object getItem(int i) {
        return this.itemHierarchy.getItemAt(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final int getItemViewType(int i) {
        return this.viewTypes.positionMap.get(this.itemHierarchy.getItemAt(i).getLayoutResource());
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        AbstractItem itemAt = this.itemHierarchy.getItemAt(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(itemAt.getLayoutResource(), viewGroup, false);
        }
        itemAt.onBindView(view);
        return view;
    }

    public final int getViewTypeCount() {
        return this.viewTypes.positionMap.size();
    }

    public final boolean isEnabled(int i) {
        return this.itemHierarchy.getItemAt(i).isEnabled();
    }

    public final void onItemRangeChanged(AbstractItemHierarchy abstractItemHierarchy, int i) {
        refreshViewTypes();
        notifyDataSetChanged();
    }

    public final void onItemRangeInserted(AbstractItemHierarchy abstractItemHierarchy, int i, int i2) {
        refreshViewTypes();
        notifyDataSetChanged();
    }

    public final void refreshViewTypes() {
        for (int i = 0; i < this.itemHierarchy.getCount(); i++) {
            AbstractItem itemAt = this.itemHierarchy.getItemAt(i);
            ViewTypes viewTypes2 = this.viewTypes;
            int layoutResource = itemAt.getLayoutResource();
            SparseIntArray sparseIntArray = viewTypes2.positionMap;
            if (sparseIntArray.indexOfKey(layoutResource) < 0) {
                sparseIntArray.put(layoutResource, viewTypes2.nextPosition);
                viewTypes2.nextPosition++;
            }
            sparseIntArray.get(layoutResource);
        }
    }
}
