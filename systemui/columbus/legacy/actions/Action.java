package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class Action {
    public final Context context;
    public final Set feedbackEffects;
    public final Handler handler = new Handler(Looper.getMainLooper());
    public boolean isAvailable = true;
    public final Set listeners = new LinkedHashSet();

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Listener {
        void onActionAvailabilityChanged(Action action);
    }

    public Action(Context context2, Set set) {
        this.context = context2;
        this.feedbackEffects = set;
    }

    public abstract String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig();

    public void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        updateFeedbackEffects(i, detectionProperties);
        if (i == 1) {
            Log.i(getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig(), "Triggering");
            onTrigger(detectionProperties);
        }
    }

    public abstract void onTrigger(GestureSensor.DetectionProperties detectionProperties);

    public final void setAvailable(boolean z) {
        Handler handler2;
        if (this.isAvailable != z) {
            this.isAvailable = z;
            Iterator it = this.listeners.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                handler2 = this.handler;
                if (!hasNext) {
                    break;
                }
                handler2.post(new Action$setAvailable$1$1((Listener) it.next(), this));
            }
            if (!this.isAvailable) {
                handler2.post(new Action$setAvailable$2(this));
            }
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void updateFeedbackEffects(int i, GestureSensor.DetectionProperties detectionProperties) {
        Set<FeedbackEffect> set = this.feedbackEffects;
        if (set != null) {
            for (FeedbackEffect onGestureDetected : set) {
                onGestureDetected.onGestureDetected(i, detectionProperties);
            }
        }
    }
}
