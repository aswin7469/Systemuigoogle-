package com.google.android.systemui.assist.uihints;

import android.graphics.Rect;
import android.graphics.Region;
import android.view.ViewGroup;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class TranscriptionController implements ConfigurationController.ConfigurationListener {
    public State mCurrentState;
    public final TouchInsideHandler mDefaultOnTap;
    public boolean mHasAccurateBackground;
    public ListenableFuture mHideFuture;
    public TranscriptionSpaceListener mListener;
    public Runnable mQueuedCompletion;
    public State mQueuedState;
    public boolean mQueuedStateAnimates;
    public final Map mViewMap = new HashMap();

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public enum State {
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface TranscriptionSpaceListener {
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface TranscriptionSpaceView {
        void getHitRect(Rect rect);

        ListenableFuture hide(boolean z);

        void onFontSizeChanged();

        void setHasDarkBackground(boolean z);
    }

    public TranscriptionController(ViewGroup viewGroup, TouchInsideHandler touchInsideHandler, ConfigurationController configurationController) {
        State state = State.NONE;
        this.mCurrentState = state;
        this.mHasAccurateBackground = false;
        this.mQueuedStateAnimates = false;
        this.mQueuedState = state;
        this.mDefaultOnTap = touchInsideHandler;
        this.mViewMap = new HashMap();
        TranscriptionView transcriptionView = (TranscriptionView) viewGroup.findViewById(2131363900);
        transcriptionView.setOnClickListener(new TranscriptionController$$ExternalSyntheticLambda1(this, 0));
        transcriptionView.setOnTouchListener(touchInsideHandler);
        this.mViewMap.put(State.TRANSCRIPTION, transcriptionView);
        GreetingView greetingView = (GreetingView) viewGroup.findViewById(2131362641);
        greetingView.setOnClickListener(new TranscriptionController$$ExternalSyntheticLambda1(this, 1));
        greetingView.setOnTouchListener(touchInsideHandler);
        this.mViewMap.put(State.GREETING, greetingView);
        this.mViewMap.put(State.CHIPS, (ChipsContainer) viewGroup.findViewById(2131362264));
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
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
                        scrimController.refresh();
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

    public final void onDensityOrFontScaleChanged() {
        for (TranscriptionSpaceView onFontSizeChanged : this.mViewMap.values()) {
            onFontSizeChanged.onFontSizeChanged();
        }
    }
}
