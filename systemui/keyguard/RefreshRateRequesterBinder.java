package com.google.android.systemui.keyguard;

import android.content.res.Resources;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.systemui.CoreStartable;
import dagger.Lazy;
import java.io.PrintWriter;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class RefreshRateRequesterBinder implements CoreStartable {
    public final Lazy interactor;
    public final Resources resources;
    public final CoroutineScope scope;

    public RefreshRateRequesterBinder(Resources resources2, Lazy lazy, CoroutineScope coroutineScope) {
        this.resources = resources2;
        this.interactor = lazy;
        this.scope = coroutineScope;
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("enabled: ", this.resources.getBoolean(2131034174), printWriter);
    }

    public final void start() {
        if (this.resources.getBoolean(2131034174)) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new RefreshRateRequesterBinder$start$1(this, (Continuation) null), 3);
        }
    }
}
