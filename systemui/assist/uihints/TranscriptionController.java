package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.graphics.Rect;
import android.graphics.Region;
import android.view.ViewGroup;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.input.TouchActionRegion;
import com.google.android.systemui.assist.uihints.input.TouchInsideRegion;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TranscriptionController implements NgaMessageHandler.CardInfoListener, NgaMessageHandler.TranscriptionInfoListener, NgaMessageHandler.GreetingInfoListener, NgaMessageHandler.ChipsInfoListener, NgaMessageHandler.ClearListener, ConfigurationController.ConfigurationListener, TouchActionRegion, TouchInsideRegion {
    public State mCurrentState;
    public final TouchInsideHandler mDefaultOnTap;
    public final FlingVelocityWrapper mFlingVelocity;
    public boolean mHasAccurateBackground;
    public ListenableFuture mHideFuture;
    public TranscriptionSpaceListener mListener;
    public PendingIntent mOnGreetingTap;
    public PendingIntent mOnTranscriptionTap;
    public Runnable mQueuedCompletion;
    public State mQueuedState;
    public boolean mQueuedStateAnimates;
    public final Map mViewMap = new HashMap();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public enum State {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface TranscriptionSpaceListener {
    }

    public TranscriptionController(ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, FlingVelocityWrapper flingVelocityWrapper, ConfigurationController configurationController) {
        State state = State.NONE;
        this.mCurrentState = state;
        this.mHasAccurateBackground = false;
        this.mQueuedStateAnimates = false;
        this.mQueuedState = state;
        this.mDefaultOnTap = touchInsideHandler;
        this.mFlingVelocity = flingVelocityWrapper;
        this.mViewMap = new HashMap();
        TranscriptionView transcriptionView = (TranscriptionView) viewGroup.findViewById(2131363864);
        transcriptionView.setOnClickListener(new TranscriptionController$$ExternalSyntheticLambda1(this, 0));
        transcriptionView.setOnTouchListener(touchInsideHandler);
        this.mViewMap.put(State.TRANSCRIPTION, transcriptionView);
        GreetingView greetingView = (GreetingView) viewGroup.findViewById(2131362621);
        greetingView.setOnClickListener(new TranscriptionController$$ExternalSyntheticLambda1(this, 1));
        greetingView.setOnTouchListener(touchInsideHandler);
        this.mViewMap.put(State.GREETING, greetingView);
        this.mViewMap.put(State.CHIPS, (ChipsContainer) viewGroup.findViewById(2131362248));
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
    }

    public final Optional getTouchActionRegion() {
        int ordinal = this.mCurrentState.ordinal();
        if (ordinal == 0 ? this.mOnTranscriptionTap == null : ordinal == 1 ? this.mOnGreetingTap == null : ordinal != 2) {
            return Optional.empty();
        }
        return getTouchRegion();
    }

    public final Optional getTouchInsideRegion() {
        int ordinal = this.mCurrentState.ordinal();
        if (ordinal == 0 ? this.mOnTranscriptionTap == null : ordinal == 1 ? this.mOnGreetingTap == null : ordinal != 2) {
            return getTouchRegion();
        }
        return Optional.empty();
    }

    public final Optional getTouchRegion() {
        TranscriptionSpaceView transcriptionSpaceView = (TranscriptionSpaceView) this.mViewMap.get(this.mCurrentState);
        if (transcriptionSpaceView == null) {
            return Optional.empty();
        }
        Rect rect = new Rect();
        transcriptionSpaceView.getHitRect(rect);
        return Optional.of(new Region(rect));
    }

    public final void maybeSetState() {
        boolean z;
        State state = this.mCurrentState;
        State state2 = this.mQueuedState;
        if (state == state2) {
            Runnable runnable = this.mQueuedCompletion;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        boolean z2 = this.mHasAccurateBackground;
        State state3 = State.NONE;
        if (z2 || state2 == state3) {
            ListenableFuture listenableFuture = this.mHideFuture;
            if (listenableFuture == null || listenableFuture.isDone()) {
                State state4 = this.mQueuedState;
                TranscriptionSpaceListener transcriptionSpaceListener = this.mListener;
                if (transcriptionSpaceListener != null) {
                    ScrimController scrimController = (ScrimController) transcriptionSpaceListener;
                    if (state4 != state3) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (scrimController.mTranscriptionVisible != z) {
                        scrimController.mTranscriptionVisible = z;
                        scrimController.refresh$1();
                    }
                }
                if (state3.equals(this.mCurrentState)) {
                    this.mCurrentState = this.mQueuedState;
                    Runnable runnable2 = this.mQueuedCompletion;
                    if (runnable2 != null) {
                        runnable2.run();
                        return;
                    }
                    return;
                }
                ListenableFuture hide = ((TranscriptionSpaceView) this.mViewMap.get(this.mCurrentState)).hide(this.mQueuedStateAnimates);
                this.mHideFuture = hide;
                TranscriptionController$$ExternalSyntheticLambda0 transcriptionController$$ExternalSyntheticLambda0 = new TranscriptionController$$ExternalSyntheticLambda0(this);
                MoreExecutors.directExecutor();
                Futures.transform(hide, transcriptionController$$ExternalSyntheticLambda0);
            }
        }
    }

    public final void onCardInfo(int i, boolean z, boolean z2, boolean z3) {
        for (TranscriptionSpaceView cardVisible : this.mViewMap.values()) {
            cardVisible.setCardVisible(z);
        }
    }

    public final void onDensityOrFontScaleChanged() {
        for (TranscriptionSpaceView onFontSizeChanged : this.mViewMap.values()) {
            onFontSizeChanged.onFontSizeChanged();
        }
    }

    public final void setQueuedState(State state, boolean z, Runnable runnable) {
        this.mQueuedState = state;
        this.mQueuedStateAnimates = z;
        this.mQueuedCompletion = runnable;
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface TranscriptionSpaceView {
        void getHitRect(Rect rect);

        ListenableFuture hide(boolean z);

        void onFontSizeChanged();

        void setHasDarkBackground(boolean z);

        void setCardVisible(boolean z) {
        }
    }
}
