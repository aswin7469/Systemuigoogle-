package com.google.android.setupdesign;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DividerItemDecoration extends RecyclerView.ItemDecoration {
    public Drawable divider;
    public int dividerCondition;
    public int dividerHeight;
    public int dividerIntrinsicHeight;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface DividedViewHolder {
        boolean isDividerAllowedAbove();

        boolean isDividerAllowedBelow();
    }

    public final void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (shouldDrawDividerBelow$1(view, recyclerView)) {
            int i = this.dividerHeight;
            if (i == 0) {
                i = this.dividerIntrinsicHeight;
            }
            rect.bottom = i;
        }
    }

    public final void onDraw(Canvas canvas, RecyclerView recyclerView) {
        if (this.divider != null) {
            int childCount = recyclerView.getChildCount();
            int width = recyclerView.getWidth();
            int i = this.dividerHeight;
            if (i == 0) {
                i = this.dividerIntrinsicHeight;
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = recyclerView.getChildAt(i2);
                if (shouldDrawDividerBelow$1(childAt, recyclerView)) {
                    WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
                    int height = childAt.getHeight() + ((int) childAt.getY());
                    this.divider.setBounds(0, height, width, height + i);
                    this.divider.draw(canvas);
                }
            }
        }
    }

    public final boolean shouldDrawDividerBelow$1(View view, RecyclerView recyclerView) {
        RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
        int layoutPosition = childViewHolder.getLayoutPosition();
        int itemCount = recyclerView.mAdapter.getItemCount() - 1;
        if (!(childViewHolder instanceof DividedViewHolder) || ((DividedViewHolder) childViewHolder).isDividerAllowedBelow()) {
            if (this.dividerCondition == 0) {
                return true;
            }
        } else if (this.dividerCondition == 1 || layoutPosition == itemCount) {
            return false;
        }
        if (layoutPosition < itemCount) {
            RecyclerView.ViewHolder findViewHolderForPosition = recyclerView.findViewHolderForPosition(layoutPosition + 1, false);
            if (!(findViewHolderForPosition instanceof DividedViewHolder) || ((DividedViewHolder) findViewHolderForPosition).isDividerAllowedAbove()) {
                return true;
            }
            return false;
        }
        return true;
    }
}
