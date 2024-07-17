package com.google.android.material.internal;

import com.google.android.material.chip.ChipGroup;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CheckableGroup {
    public final Map checkables = new HashMap();
    public final Set checkedIds = new HashSet();
    public ChipGroup.AnonymousClass1 onCheckedStateChangeListener;
    public boolean selectionRequired;
    public boolean singleSelection;

    public final boolean checkInternal(MaterialCheckable materialCheckable) {
        int i;
        int id = materialCheckable.getId();
        Set set = this.checkedIds;
        if (set.contains(Integer.valueOf(id))) {
            return false;
        }
        Map map = this.checkables;
        if (!this.singleSelection || set.isEmpty()) {
            i = -1;
        } else {
            i = ((Integer) ((HashSet) set).iterator().next()).intValue();
        }
        MaterialCheckable materialCheckable2 = (MaterialCheckable) map.get(Integer.valueOf(i));
        if (materialCheckable2 != null) {
            uncheckInternal(materialCheckable2, false);
        }
        boolean add = set.add(Integer.valueOf(id));
        if (!materialCheckable.isChecked()) {
            materialCheckable.setChecked(true);
        }
        return add;
    }

    public final void onCheckedStateChanged() {
        ChipGroup.AnonymousClass1 r0 = this.onCheckedStateChangeListener;
        if (r0 != null) {
            new HashSet(this.checkedIds);
            int i = ChipGroup.$r8$clinit;
            ChipGroup.this.getClass();
        }
    }

    public final boolean uncheckInternal(MaterialCheckable materialCheckable, boolean z) {
        int id = materialCheckable.getId();
        Set set = this.checkedIds;
        if (!set.contains(Integer.valueOf(id))) {
            return false;
        }
        if (!z || ((HashSet) set).size() != 1 || !set.contains(Integer.valueOf(id))) {
            boolean remove = set.remove(Integer.valueOf(id));
            if (materialCheckable.isChecked()) {
                materialCheckable.setChecked(false);
            }
            return remove;
        }
        materialCheckable.setChecked(true);
        return false;
    }
}
