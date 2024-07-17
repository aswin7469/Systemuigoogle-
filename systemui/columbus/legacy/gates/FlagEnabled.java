package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
