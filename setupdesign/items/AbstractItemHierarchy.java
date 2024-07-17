package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.compose.ui.text.input.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.google.android.setupdesign.R$styleable;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class AbstractItemHierarchy {
    public final int id = -1;
    public final ArrayList observers = new ArrayList();

    public AbstractItemHierarchy(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudAbstractItem);
        this.id = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
    }

    public abstract int getCount();

    public abstract AbstractItem getItemAt(int i);

    public final void notifyItemRangeChanged(int i, int i2) {
        if (i < 0) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("notifyItemRangeChanged: Invalid position=", i, "AbstractItemHierarchy");
            return;
        }
        Iterator it = this.observers.iterator();
        while (it.hasNext()) {
            ((ItemHierarchy$Observer) it.next()).onItemRangeChanged(this, i);
        }
    }

    public final void notifyItemRangeInserted(int i, int i2) {
        if (i < 0) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("notifyItemRangeInserted: Invalid position=", i, "AbstractItemHierarchy");
        } else if (i2 < 0) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("notifyItemRangeInserted: Invalid itemCount=", i2, "AbstractItemHierarchy");
        } else {
            Iterator it = this.observers.iterator();
            while (it.hasNext()) {
                ((ItemHierarchy$Observer) it.next()).onItemRangeInserted(this, i, i2);
            }
        }
    }
}
