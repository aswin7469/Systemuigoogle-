package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import com.google.android.setupdesign.items.ItemInflater;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ItemGroup extends AbstractItemHierarchy implements ItemInflater.ItemParent, ItemHierarchy$Observer {
    public final List children = new ArrayList();
    public int count = 0;
    public boolean dirty = false;
    public final SparseIntArray hierarchyStart = new SparseIntArray();

    public ItemGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void addChild(AbstractItemHierarchy abstractItemHierarchy) {
        this.dirty = true;
        this.children.add(abstractItemHierarchy);
        abstractItemHierarchy.observers.add(this);
        int count2 = abstractItemHierarchy.getCount();
        if (count2 > 0) {
            notifyItemRangeInserted(getChildPosition(abstractItemHierarchy), count2);
        }
    }

    public final int getChildPosition(AbstractItemHierarchy abstractItemHierarchy) {
        List list = this.children;
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (arrayList.get(i) == abstractItemHierarchy) {
                break;
            } else {
                i++;
            }
        }
        updateDataIfNeeded();
        if (i == -1) {
            return -1;
        }
        int size2 = ((ArrayList) list).size();
        int i2 = -1;
        while (i2 < 0 && i < size2) {
            i2 = this.hierarchyStart.get(i, -1);
            i++;
        }
        if (i2 >= 0) {
            return i2;
        }
        updateDataIfNeeded();
        return this.count;
    }

    public final int getCount() {
        updateDataIfNeeded();
        return this.count;
    }

    public final AbstractItem getItemAt(int i) {
        int keyAt;
        updateDataIfNeeded();
        if (i < 0 || i >= this.count) {
            throw new IndexOutOfBoundsException("size=" + this.count + "; index=" + i);
        }
        SparseIntArray sparseIntArray = this.hierarchyStart;
        int size = sparseIntArray.size() - 1;
        int i2 = 0;
        while (true) {
            if (i2 <= size) {
                int i3 = (i2 + size) >>> 1;
                int valueAt = sparseIntArray.valueAt(i3);
                if (valueAt >= i) {
                    if (valueAt <= i) {
                        keyAt = sparseIntArray.keyAt(i3);
                        break;
                    }
                    size = i3 - 1;
                } else {
                    i2 = i3 + 1;
                }
            } else {
                keyAt = sparseIntArray.keyAt(i2 - 1);
                break;
            }
        }
        if (keyAt >= 0) {
            return ((AbstractItemHierarchy) ((ArrayList) this.children).get(keyAt)).getItemAt(i - sparseIntArray.get(keyAt));
        }
        throw new IllegalStateException("Cannot have item start index < 0");
    }

    public final void onItemRangeChanged(AbstractItemHierarchy abstractItemHierarchy, int i) {
        int childPosition = getChildPosition(abstractItemHierarchy);
        if (childPosition >= 0) {
            notifyItemRangeChanged(childPosition + i, 1);
            return;
        }
        Log.e("ItemGroup", "Unexpected child change " + abstractItemHierarchy);
    }

    public final void onItemRangeInserted(AbstractItemHierarchy abstractItemHierarchy, int i, int i2) {
        this.dirty = true;
        int childPosition = getChildPosition(abstractItemHierarchy);
        if (childPosition >= 0) {
            notifyItemRangeInserted(childPosition + i, i2);
            return;
        }
        Log.e("ItemGroup", "Unexpected child insert " + abstractItemHierarchy);
    }

    public final void updateDataIfNeeded() {
        if (this.dirty) {
            this.count = 0;
            SparseIntArray sparseIntArray = this.hierarchyStart;
            sparseIntArray.clear();
            int i = 0;
            while (true) {
                List list = this.children;
                if (i < ((ArrayList) list).size()) {
                    AbstractItemHierarchy abstractItemHierarchy = (AbstractItemHierarchy) ((ArrayList) list).get(i);
                    if (abstractItemHierarchy.getCount() > 0) {
                        sparseIntArray.put(i, this.count);
                    }
                    this.count = abstractItemHierarchy.getCount() + this.count;
                    i++;
                } else {
                    this.dirty = false;
                    return;
                }
            }
        }
    }
}
