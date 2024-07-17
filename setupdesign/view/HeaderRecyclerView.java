package com.google.android.setupdesign.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class HeaderRecyclerView extends RecyclerView {
    public View header;
    public int headerRes;
    public boolean shouldHandleActionUp = false;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class HeaderAdapter extends RecyclerView.Adapter {
        public final RecyclerView.Adapter adapter;
        public View header;
        public final AnonymousClass1 observer;

        public HeaderAdapter(RecyclerView.Adapter adapter2) {
            AnonymousClass1 r0 = new RecyclerView.AdapterDataObserver() {
                public final void onChanged() {
                    HeaderAdapter.this.notifyDataSetChanged();
                }

                public final void onItemRangeChanged(int i, int i2) {
                    HeaderAdapter headerAdapter = HeaderAdapter.this;
                    if (headerAdapter.header != null) {
                        i++;
                    }
                    headerAdapter.mObservable.notifyItemRangeChanged(i, i2);
                }

                public final void onItemRangeInserted(int i, int i2) {
                    HeaderAdapter headerAdapter = HeaderAdapter.this;
                    if (headerAdapter.header != null) {
                        i++;
                    }
                    headerAdapter.mObservable.notifyItemRangeInserted(i, i2);
                }

                public final void onItemRangeMoved(int i, int i2) {
                    HeaderAdapter headerAdapter = HeaderAdapter.this;
                    if (headerAdapter.header != null) {
                        i++;
                        i2++;
                    }
                    headerAdapter.notifyItemMoved(i, i2);
                }

                public final void onItemRangeRemoved(int i, int i2) {
                    HeaderAdapter headerAdapter = HeaderAdapter.this;
                    if (headerAdapter.header != null) {
                        i++;
                    }
                    headerAdapter.mObservable.notifyItemRangeRemoved(i, i2);
                }
            };
            this.adapter = adapter2;
            adapter2.registerAdapterDataObserver(r0);
            setHasStableIds(adapter2.mHasStableIds);
        }

        public final int getItemCount() {
            int itemCount = this.adapter.getItemCount();
            if (this.header != null) {
                return itemCount + 1;
            }
            return itemCount;
        }

        public final long getItemId(int i) {
            if (this.header != null) {
                i--;
            }
            if (i < 0) {
                return Long.MAX_VALUE;
            }
            return this.adapter.getItemId(i);
        }

        public final int getItemViewType(int i) {
            if (this.header != null) {
                i--;
            }
            if (i < 0) {
                return Integer.MAX_VALUE;
            }
            return this.adapter.getItemViewType(i);
        }

        public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            View view = this.header;
            if (view != null) {
                i--;
            }
            if (!(viewHolder instanceof HeaderViewHolder)) {
                this.adapter.onBindViewHolder(viewHolder, i);
            } else if (view != null) {
                if (view.getParent() != null) {
                    ((ViewGroup) this.header.getParent()).removeView(this.header);
                }
                ((FrameLayout) viewHolder.itemView).addView(this.header);
            } else {
                throw new IllegalStateException("HeaderViewHolder cannot find mHeader");
            }
        }

        public final RecyclerView.ViewHolder onCreateViewHolder(RecyclerView recyclerView, int i) {
            if (i != Integer.MAX_VALUE) {
                return this.adapter.onCreateViewHolder(recyclerView, i);
            }
            FrameLayout frameLayout = new FrameLayout(recyclerView.getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
            return new RecyclerView.ViewHolder(frameLayout);
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class HeaderViewHolder extends RecyclerView.ViewHolder {
    }

    public HeaderRecyclerView(Context context) {
        super(context);
        init$1((AttributeSet) null, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0085, code lost:
        if (r1 != false) goto L_0x0087;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.shouldHandleActionUp
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0010
            int r0 = r6.getAction()
            if (r0 != r2) goto L_0x0010
            r5.shouldHandleActionUp = r1
            goto L_0x0087
        L_0x0010:
            int r0 = r6.getAction()
            if (r0 != 0) goto L_0x0088
            int r0 = r6.getKeyCode()
            r3 = 19
            r4 = 2
            if (r0 == r3) goto L_0x0059
            r3 = 20
            if (r0 == r3) goto L_0x0024
            goto L_0x0083
        L_0x0024:
            android.view.View r0 = r5.findFocus()
            if (r0 != 0) goto L_0x002b
            goto L_0x0083
        L_0x002b:
            int[] r3 = new int[r4]
            int[] r4 = new int[r4]
            r0.getLocationInWindow(r3)
            r5.getLocationInWindow(r4)
            r3 = r3[r2]
            int r0 = r0.getMeasuredHeight()
            int r0 = r0 + r3
            r3 = r4[r2]
            int r4 = r5.getMeasuredHeight()
            int r4 = r4 + r3
            int r0 = r0 - r4
            if (r0 <= 0) goto L_0x0083
            int r3 = r5.getMeasuredHeight()
            float r3 = (float) r3
            r4 = 1060320051(0x3f333333, float:0.7)
            float r3 = r3 * r4
            int r3 = (int) r3
            int r0 = java.lang.Math.min(r3, r0)
            r5.smoothScrollBy$1(r1, r0, r1)
        L_0x0057:
            r1 = r2
            goto L_0x0083
        L_0x0059:
            android.view.View r0 = r5.findFocus()
            if (r0 != 0) goto L_0x0060
            goto L_0x0083
        L_0x0060:
            int[] r3 = new int[r4]
            int[] r4 = new int[r4]
            r0.getLocationInWindow(r3)
            r5.getLocationInWindow(r4)
            r0 = r3[r2]
            r3 = r4[r2]
            int r0 = r0 - r3
            if (r0 >= 0) goto L_0x0083
            int r3 = r5.getMeasuredHeight()
            float r3 = (float) r3
            r4 = -1087163597(0xffffffffbf333333, float:-0.7)
            float r3 = r3 * r4
            int r3 = (int) r3
            int r0 = java.lang.Math.max(r3, r0)
            r5.smoothScrollBy$1(r1, r0, r1)
            goto L_0x0057
        L_0x0083:
            r5.shouldHandleActionUp = r1
            if (r1 == 0) goto L_0x0088
        L_0x0087:
            return r2
        L_0x0088:
            boolean r5 = super.dispatchKeyEvent(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.view.HeaderRecyclerView.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    public final void init$1(AttributeSet attributeSet, int i) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SudHeaderRecyclerView, i, 0);
            this.headerRes = obtainStyledAttributes.getResourceId(0, 0);
            obtainStyledAttributes.recycle();
        }
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        int i;
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (this.header != null) {
            i = 1;
        } else {
            i = 0;
        }
        accessibilityEvent.setItemCount(accessibilityEvent.getItemCount() - i);
        accessibilityEvent.setFromIndex(Math.max(accessibilityEvent.getFromIndex() - i, 0));
        accessibilityEvent.setToIndex(Math.max(accessibilityEvent.getToIndex() - i, 0));
    }

    public final void setAdapter(RecyclerView.Adapter adapter) {
        if (!(this.header == null || adapter == null)) {
            HeaderAdapter headerAdapter = new HeaderAdapter(adapter);
            headerAdapter.header = this.header;
            adapter = headerAdapter;
        }
        super.setAdapter(adapter);
    }

    public final void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (layoutManager != null && this.header == null && this.headerRes != 0) {
            this.header = LayoutInflater.from(getContext()).inflate(this.headerRes, this, false);
        }
    }

    public HeaderRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init$1(attributeSet, 0);
    }

    public HeaderRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init$1(attributeSet, i);
    }
}
