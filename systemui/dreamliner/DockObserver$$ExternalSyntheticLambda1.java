package com.google.android.systemui.dreamliner;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DockObserver$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ ArrayList f$0;

    public /* synthetic */ DockObserver$$ExternalSyntheticLambda1(ArrayList arrayList) {
        this.f$0 = arrayList;
    }

    public final void accept(Object obj) {
        ArrayList arrayList = this.f$0;
        Bundle bundle = new Bundle();
        bundle.putByteArray("wpc_digest", (byte[]) obj);
        arrayList.add(bundle);
    }
}
