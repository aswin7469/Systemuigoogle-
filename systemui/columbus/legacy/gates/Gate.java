package com.google.android.systemui.columbus.legacy.gates;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class Gate {
    public boolean active;
    public final ContextScope coroutineScope;
    public boolean isBlocked;
    public final Set listeners = new LinkedHashSet();
    public final CoroutineDispatcher mainDispatcher;
    public final CoroutineDispatcher mainPostDispatcher;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Listener {
        void onGateChanged(Gate gate);
    }

    public Gate() {
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        MainCoroutineDispatcher mainCoroutineDispatcher = MainDispatcherLoader.dispatcher;
        HandlerContext handlerContext = ((HandlerContext) mainCoroutineDispatcher).immediate;
        this.mainDispatcher = handlerContext;
        this.mainPostDispatcher = mainCoroutineDispatcher;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(handlerContext);
    }

    public final boolean isActive() {
        return ((Boolean) BuildersKt.runBlocking(this.mainDispatcher, new Gate$isActive$1(this, (Continuation) null))).booleanValue();
    }

    public final boolean isBlocking() {
        return ((Boolean) BuildersKt.runBlocking(this.mainDispatcher, new Gate$isBlocking$1(this, (Continuation) null))).booleanValue();
    }

    public abstract void onActivate();

    public abstract void onDeactivate();

    public final void registerListener(Listener listener) {
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new Gate$registerListener$1(listener, this, (Continuation) null), 3);
    }

    public final void setBlocking(boolean z) {
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new Gate$setBlocking$1(this, z, (Continuation) null), 3);
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public final void unregisterListener(Listener listener) {
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new Gate$unregisterListener$1(listener, this, (Continuation) null), 3);
    }
}
