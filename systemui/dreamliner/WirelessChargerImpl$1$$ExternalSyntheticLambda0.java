package com.google.android.systemui.dreamliner;

import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class WirelessChargerImpl$1$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int f$0;

    public /* synthetic */ WirelessChargerImpl$1$$ExternalSyntheticLambda0(int i) {
        this.f$0 = i;
    }

    public final void accept(Object obj) {
        int i = this.f$0;
        Iterator it = ((WirelessChargerCommander$fanLevelEventListener$1) obj).this$0.wirelessChargerFanLevelChangedCallback.iterator();
        while (it.hasNext()) {
            ((DockObserver$$ExternalSyntheticLambda2) it.next()).onFanLevelChanged(i);
        }
    }
}
