package com.google.android.systemui.dagger;

import com.android.systemui.CoreStartable;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CoroutineScopeCoreStartable implements CoreStartable {
    public final Set coroutineInitializers;
    public StandaloneCoroutine job;
    public final ContextScope scope = CoroutineScopeKt.MainScope();

    public CoroutineScopeCoreStartable(Set set) {
        this.coroutineInitializers = set;
    }

    public final void start() {
        if (this.job == null) {
            this.job = BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new CoroutineScopeCoreStartable$start$1(this, (Continuation) null), 3);
            return;
        }
        throw new IllegalStateException("Already started".toString());
    }
}
