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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
