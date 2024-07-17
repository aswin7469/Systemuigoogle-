package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FlagEnabled extends Gate {
    public final CoroutineDispatcher bgDispatcher;
    public boolean columbusEnabled;
    public final ColumbusSettings columbusSettings;
    public final FlagEnabled$settingsChangeListener$1 settingsChangeListener = new FlagEnabled$settingsChangeListener$1(this);

    public FlagEnabled(ColumbusSettings columbusSettings2, CoroutineDispatcher coroutineDispatcher) {
        this.columbusSettings = columbusSettings2;
        this.bgDispatcher = coroutineDispatcher;
    }

    public final void onActivate() {
        this.columbusSettings.registerColumbusSettingsChangeListener(this.settingsChangeListener);
        BuildersKt.launch$default(this.coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new FlagEnabled$onActivate$1(this, (Continuation) null), 3);
    }

    public final void onDeactivate() {
        this.columbusSettings.listeners.remove(this.settingsChangeListener);
    }

    public final String toString() {
        String gate = super.toString();
        Object runBlocking = BuildersKt.runBlocking(this.mainDispatcher, new FlagEnabled$toString$1(this, (Continuation) null));
        return gate + runBlocking;
    }
}
