package com.google.android.setupdesign.items;

import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupdesign.DividerItemDecoration;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ItemViewHolder extends RecyclerView.ViewHolder implements DividerItemDecoration.DividedViewHolder {
    public boolean isEnabled;
    public AbstractItem item;

    public final boolean isDividerAllowedAbove() {
        return this.isEnabled;
    }

    public final boolean isDividerAllowedBelow() {
        return this.isEnabled;
    }
}
