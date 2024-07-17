package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.google.android.setupdesign.R$styleable;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class AbstractItemHierarchy {
    public final ArrayList observers = new ArrayList();

    public AbstractItemHierarchy(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudAbstractItem);
        obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
    }

    public final void notifyItemRangeChanged(int i) {
        int i2;
        if (i < 0) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("notifyItemRangeChanged: Invalid position=", "AbstractItemHierarchy", i);
            return;
        }
        Iterator it = this.observers.iterator();
        while (it.hasNext()) {
            ItemGroup itemGroup = (ItemGroup) it.next();
            ArrayList arrayList = (ArrayList) itemGroup.children;
            int size = arrayList.size();
            int i3 = 0;
            while (true) {
                i2 = -1;
                if (i3 >= size) {
                    i3 = -1;
                    break;
                } else if (arrayList.get(i3) == this) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 != -1) {
                int size2 = arrayList.size();
                int i4 = -1;
                while (i4 < 0 && i3 < size2) {
                    i4 = itemGroup.hierarchyStart.get(i3, -1);
                    i3++;
                }
                if (i4 < 0) {
                    i2 = itemGroup.count;
                } else {
                    i2 = i4;
                }
            }
            if (i2 >= 0) {
                itemGroup.notifyItemRangeChanged(i2 + i);
            } else {
                Log.e("ItemGroup", "Unexpected child change " + this);
            }
        }
    }
}
