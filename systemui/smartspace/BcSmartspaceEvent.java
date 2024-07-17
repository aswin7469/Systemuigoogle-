package com.google.android.systemui.smartspace;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public enum BcSmartspaceEvent implements EventEnum {
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
