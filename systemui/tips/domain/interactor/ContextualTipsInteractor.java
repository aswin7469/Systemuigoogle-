package com.google.android.systemui.tips.domain.interactor;

import android.content.Context;
import com.android.systemui.CoreStartable;
import com.android.systemui.assist.domain.interactor.AssistInteractor;
import com.android.systemui.globalactions.domain.interactor.GlobalActionsInteractor;
import com.google.android.systemui.tips.data.repository.ContextualTipsRepository;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ContextualTipsInteractor implements CoreStartable {
    public final Context applicationContext;
    public final CoroutineScope applicationScope;
    public final AssistInteractor assistInteractor;
    public final GlobalActionsInteractor globalActionsInteractor;
    public final CoroutineDispatcher mainDispatcher;
    public final ContextualTipsRepository repository;

    public ContextualTipsInteractor(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, ContextualTipsRepository contextualTipsRepository, GlobalActionsInteractor globalActionsInteractor2, AssistInteractor assistInteractor2) {
    }

    public final void start() {
    }
}
