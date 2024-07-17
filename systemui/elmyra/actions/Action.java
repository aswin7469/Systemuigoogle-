package com.google.android.systemui.elmyra.actions;

import com.google.android.systemui.elmyra.ElmyraService;
import com.google.android.systemui.elmyra.feedback.FeedbackEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class Action {
    public final List mFeedbackEffects;
    public final Executor mHandler;
    public ElmyraService.AnonymousClass1 mListener;

    public Action(Executor executor, List list) {
        ArrayList arrayList = new ArrayList();
        this.mFeedbackEffects = arrayList;
        this.mHandler = executor;
        if (list != null) {
            arrayList.addAll(list);
        }
    }

    public abstract boolean isAvailable();

    public final void notifyListener() {
        ElmyraService.AnonymousClass1 r0 = this.mListener;
        Executor executor = this.mHandler;
        if (r0 != null) {
            executor.execute(new Action$$ExternalSyntheticLambda0(this, 0));
        }
        if (!isAvailable()) {
            executor.execute(new Action$$ExternalSyntheticLambda0(this, 1));
        }
    }

    public abstract void onTrigger(GestureSensor.DetectionProperties detectionProperties);

    public String toString() {
        return getClass().getSimpleName();
    }

    public void triggerFeedbackEffects(GestureSensor.DetectionProperties detectionProperties) {
        if (isAvailable()) {
            int i = 0;
            while (true) {
                ArrayList arrayList = (ArrayList) this.mFeedbackEffects;
                if (i < arrayList.size()) {
                    ((FeedbackEffect) arrayList.get(i)).onResolve(detectionProperties);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public void updateFeedbackEffects(int i, float f) {
        int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        int i3 = 0;
        List list = this.mFeedbackEffects;
        if (i2 == 0 || i == 0) {
            while (i3 < ((ArrayList) list).size()) {
                ((FeedbackEffect) ((ArrayList) list).get(i3)).onRelease();
                i3++;
            }
        } else if (isAvailable()) {
            while (i3 < ((ArrayList) list).size()) {
                ((FeedbackEffect) ((ArrayList) list).get(i3)).onProgress(i, f);
                i3++;
            }
        }
    }

    public void onProgress(int i, float f) {
    }
}
