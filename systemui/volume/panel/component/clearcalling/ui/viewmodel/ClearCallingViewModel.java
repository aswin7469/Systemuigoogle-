package com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel;

import android.content.Context;
import com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ClearCallingViewModel {
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow buttonViewModel;
    public final ClearCallingInteractor clearCallingInteractor;
    public final Context context;
    public final CoroutineScope coroutineScope;

    public ClearCallingViewModel(Context context2, ClearCallingInteractor clearCallingInteractor2, CoroutineScope coroutineScope2, CoroutineDispatcher coroutineDispatcher) {
        this.context = context2;
        this.clearCallingInteractor = clearCallingInteractor2;
        this.coroutineScope = coroutineScope2;
        this.backgroundDispatcher = coroutineDispatcher;
        this.buttonViewModel = FlowKt.stateIn(new ClearCallingViewModel$special$$inlined$map$1(new ReadonlyStateFlow(clearCallingInteractor2.clearCallingRepository.mutableClearCallingStateUpdate), this), coroutineScope2, SharingStarted.Companion.Eagerly, (Object) null);
    }

    public final void setIsClearCallingEnabled(boolean z) {
        BuildersKt.launch$default(this.coroutineScope, this.backgroundDispatcher, (CoroutineStart) null, new ClearCallingViewModel$setIsClearCallingEnabled$1(this, z, (Continuation) null), 2);
    }
}
