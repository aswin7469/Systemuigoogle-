package com.google.android.systemui.smartspace;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public enum BcSmartspaceEvent {
    SMARTSPACE_CARD_RECEIVED(759),
    SMARTSPACE_CARD_CLICK(760),
    SMARTSPACE_CARD_SEEN(800);
    
    private final int mId;

    /* access modifiers changed from: public */
    BcSmartspaceEvent(int i) {
        this.mId = i;
    }

    public final int getId() {
        return this.mId;
    }
}
