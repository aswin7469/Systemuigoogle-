package com.google.android.systemui.columbus;

import android.os.IBinder;
import com.google.android.systemui.columbus.ColumbusServiceProxy;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
final class ColumbusServiceProxy$binder$1$registerServiceListener$1 extends Lambda implements Function1 {
    final /* synthetic */ IBinder $token;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColumbusServiceProxy$binder$1$registerServiceListener$1(IBinder iBinder) {
        super(1);
        this.$token = iBinder;
    }

    public final Object invoke(Object obj) {
        ColumbusServiceProxy.ColumbusServiceListener columbusServiceListener = (ColumbusServiceProxy.ColumbusServiceListener) obj;
        boolean z = false;
        if (Intrinsics.areEqual(this.$token, columbusServiceListener.token)) {
            IBinder iBinder = columbusServiceListener.token;
            if (iBinder != null) {
                iBinder.unlinkToDeath(columbusServiceListener, 0);
            }
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
