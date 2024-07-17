package com.google.android.systemui.elmyra;

import android.content.Context;
import android.metrics.LogMaker;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.Dumpable;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.feedback.FeedbackEffect;
import com.google.android.systemui.elmyra.gates.Gate;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ElmyraService implements Dumpable {
    public final AnonymousClass1 mActionListener = new Object() {
        public final void onGateChanged(Gate gate) {
            ElmyraService.this.updateSensorListener$1();
        }

        public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
            int i;
            long j;
            ElmyraEvent elmyraEvent;
            ElmyraService elmyraService = ElmyraService.this;
            elmyraService.mWakeLock.acquire(2000);
            boolean isInteractive = elmyraService.mPowerManager.isInteractive();
            if (detectionProperties != null && detectionProperties.mHostSuspended) {
                i = 3;
            } else if (!isInteractive) {
                i = 2;
            } else {
                i = 1;
            }
            LogMaker subtype = new LogMaker(999).setType(4).setSubtype(i);
            if (isInteractive) {
                j = SystemClock.uptimeMillis() - elmyraService.mLastPrimedGesture;
            } else {
                j = 0;
            }
            LogMaker latency = subtype.setLatency(j);
            elmyraService.mLastPrimedGesture = 0;
            Action updateActiveAction = elmyraService.updateActiveAction();
            if (updateActiveAction != null) {
                Log.i("Elmyra/ElmyraService", "Triggering " + updateActiveAction);
                updateActiveAction.onTrigger(detectionProperties);
                int i2 = 0;
                while (true) {
                    List list = elmyraService.mFeedbackEffects;
                    if (i2 >= ((ArrayList) list).size()) {
                        break;
                    }
                    ((FeedbackEffect) ((ArrayList) list).get(i2)).onResolve(detectionProperties);
                    i2++;
                }
                latency.setPackageName(updateActiveAction.getClass().getName());
            }
            if (detectionProperties != null && detectionProperties.mHostSuspended) {
                elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_AP_SUSPENDED;
            } else if (!isInteractive) {
                elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_OFF;
            } else {
                elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_ON;
            }
            elmyraService.mUiEventLogger.log(elmyraEvent);
            elmyraService.mLogger.write(latency);
        }

        public final void onGestureProgress(float f, int i) {
            ElmyraService elmyraService = ElmyraService.this;
            Action updateActiveAction = elmyraService.updateActiveAction();
            if (updateActiveAction != null) {
                updateActiveAction.onProgress(i, f);
                int i2 = 0;
                while (true) {
                    List list = elmyraService.mFeedbackEffects;
                    if (i2 >= ((ArrayList) list).size()) {
                        break;
                    }
                    ((FeedbackEffect) ((ArrayList) list).get(i2)).onProgress(i, f);
                    i2++;
                }
            }
            if (i != elmyraService.mLastStage) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MetricsLogger metricsLogger = elmyraService.mLogger;
                UiEventLogger uiEventLogger = elmyraService.mUiEventLogger;
                if (i == 2) {
                    uiEventLogger.log(ElmyraEvent.ELMYRA_PRIMED);
                    metricsLogger.action(998);
                    elmyraService.mLastPrimedGesture = uptimeMillis;
                } else if (i == 0 && elmyraService.mLastPrimedGesture != 0) {
                    uiEventLogger.log(ElmyraEvent.ELMYRA_RELEASED);
                    metricsLogger.write(new LogMaker(997).setType(4).setLatency(uptimeMillis - elmyraService.mLastPrimedGesture));
                }
                elmyraService.mLastStage = i;
            }
        }
    };
    public final List mActions;
    public final List mFeedbackEffects;
    public final AnonymousClass1 mGateListener = new Object() {
        public final void onGateChanged(Gate gate) {
            ElmyraService.this.updateSensorListener$1();
        }

        public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
            int i;
            long j;
            ElmyraEvent elmyraEvent;
            ElmyraService elmyraService = ElmyraService.this;
            elmyraService.mWakeLock.acquire(2000);
            boolean isInteractive = elmyraService.mPowerManager.isInteractive();
            if (detectionProperties != null && detectionProperties.mHostSuspended) {
                i = 3;
            } else if (!isInteractive) {
                i = 2;
            } else {
                i = 1;
            }
            LogMaker subtype = new LogMaker(999).setType(4).setSubtype(i);
            if (isInteractive) {
                j = SystemClock.uptimeMillis() - elmyraService.mLastPrimedGesture;
            } else {
                j = 0;
            }
            LogMaker latency = subtype.setLatency(j);
            elmyraService.mLastPrimedGesture = 0;
            Action updateActiveAction = elmyraService.updateActiveAction();
            if (updateActiveAction != null) {
                Log.i("Elmyra/ElmyraService", "Triggering " + updateActiveAction);
                updateActiveAction.onTrigger(detectionProperties);
                int i2 = 0;
                while (true) {
                    List list = elmyraService.mFeedbackEffects;
                    if (i2 >= ((ArrayList) list).size()) {
                        break;
                    }
                    ((FeedbackEffect) ((ArrayList) list).get(i2)).onResolve(detectionProperties);
                    i2++;
                }
                latency.setPackageName(updateActiveAction.getClass().getName());
            }
            if (detectionProperties != null && detectionProperties.mHostSuspended) {
                elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_AP_SUSPENDED;
            } else if (!isInteractive) {
                elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_OFF;
            } else {
                elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_ON;
            }
            elmyraService.mUiEventLogger.log(elmyraEvent);
            elmyraService.mLogger.write(latency);
        }

        public final void onGestureProgress(float f, int i) {
            ElmyraService elmyraService = ElmyraService.this;
            Action updateActiveAction = elmyraService.updateActiveAction();
            if (updateActiveAction != null) {
                updateActiveAction.onProgress(i, f);
                int i2 = 0;
                while (true) {
                    List list = elmyraService.mFeedbackEffects;
                    if (i2 >= ((ArrayList) list).size()) {
                        break;
                    }
                    ((FeedbackEffect) ((ArrayList) list).get(i2)).onProgress(i, f);
                    i2++;
                }
            }
            if (i != elmyraService.mLastStage) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MetricsLogger metricsLogger = elmyraService.mLogger;
                UiEventLogger uiEventLogger = elmyraService.mUiEventLogger;
                if (i == 2) {
                    uiEventLogger.log(ElmyraEvent.ELMYRA_PRIMED);
                    metricsLogger.action(998);
                    elmyraService.mLastPrimedGesture = uptimeMillis;
                } else if (i == 0 && elmyraService.mLastPrimedGesture != 0) {
                    uiEventLogger.log(ElmyraEvent.ELMYRA_RELEASED);
                    metricsLogger.write(new LogMaker(997).setType(4).setLatency(uptimeMillis - elmyraService.mLastPrimedGesture));
                }
                elmyraService.mLastStage = i;
            }
        }
    };
    public final List mGates;
    public final AnonymousClass1 mGestureListener;
    public final GestureSensor mGestureSensor;
    public Action mLastActiveAction;
    public long mLastPrimedGesture;
    public int mLastStage;
    public final MetricsLogger mLogger;
    public final PowerManager mPowerManager;
    public final UiEventLogger mUiEventLogger;
    public final PowerManager.WakeLock mWakeLock;

    public ElmyraService(Context context, ServiceConfigurationGoogle serviceConfigurationGoogle, UiEventLogger uiEventLogger) {
        AnonymousClass1 r0 = new Object() {
            public final void onGateChanged(Gate gate) {
                ElmyraService.this.updateSensorListener$1();
            }

            public final void onGestureDetected(GestureSensor.DetectionProperties detectionProperties) {
                int i;
                long j;
                ElmyraEvent elmyraEvent;
                ElmyraService elmyraService = ElmyraService.this;
                elmyraService.mWakeLock.acquire(2000);
                boolean isInteractive = elmyraService.mPowerManager.isInteractive();
                if (detectionProperties != null && detectionProperties.mHostSuspended) {
                    i = 3;
                } else if (!isInteractive) {
                    i = 2;
                } else {
                    i = 1;
                }
                LogMaker subtype = new LogMaker(999).setType(4).setSubtype(i);
                if (isInteractive) {
                    j = SystemClock.uptimeMillis() - elmyraService.mLastPrimedGesture;
                } else {
                    j = 0;
                }
                LogMaker latency = subtype.setLatency(j);
                elmyraService.mLastPrimedGesture = 0;
                Action updateActiveAction = elmyraService.updateActiveAction();
                if (updateActiveAction != null) {
                    Log.i("Elmyra/ElmyraService", "Triggering " + updateActiveAction);
                    updateActiveAction.onTrigger(detectionProperties);
                    int i2 = 0;
                    while (true) {
                        List list = elmyraService.mFeedbackEffects;
                        if (i2 >= ((ArrayList) list).size()) {
                            break;
                        }
                        ((FeedbackEffect) ((ArrayList) list).get(i2)).onResolve(detectionProperties);
                        i2++;
                    }
                    latency.setPackageName(updateActiveAction.getClass().getName());
                }
                if (detectionProperties != null && detectionProperties.mHostSuspended) {
                    elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_AP_SUSPENDED;
                } else if (!isInteractive) {
                    elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_OFF;
                } else {
                    elmyraEvent = ElmyraEvent.ELMYRA_TRIGGERED_SCREEN_ON;
                }
                elmyraService.mUiEventLogger.log(elmyraEvent);
                elmyraService.mLogger.write(latency);
            }

            public final void onGestureProgress(float f, int i) {
                ElmyraService elmyraService = ElmyraService.this;
                Action updateActiveAction = elmyraService.updateActiveAction();
                if (updateActiveAction != null) {
                    updateActiveAction.onProgress(i, f);
                    int i2 = 0;
                    while (true) {
                        List list = elmyraService.mFeedbackEffects;
                        if (i2 >= ((ArrayList) list).size()) {
                            break;
                        }
                        ((FeedbackEffect) ((ArrayList) list).get(i2)).onProgress(i, f);
                        i2++;
                    }
                }
                if (i != elmyraService.mLastStage) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MetricsLogger metricsLogger = elmyraService.mLogger;
                    UiEventLogger uiEventLogger = elmyraService.mUiEventLogger;
                    if (i == 2) {
                        uiEventLogger.log(ElmyraEvent.ELMYRA_PRIMED);
                        metricsLogger.action(998);
                        elmyraService.mLastPrimedGesture = uptimeMillis;
                    } else if (i == 0 && elmyraService.mLastPrimedGesture != 0) {
                        uiEventLogger.log(ElmyraEvent.ELMYRA_RELEASED);
                        metricsLogger.write(new LogMaker(997).setType(4).setLatency(uptimeMillis - elmyraService.mLastPrimedGesture));
                    }
                    elmyraService.mLastStage = i;
                }
            }
        };
        this.mUiEventLogger = uiEventLogger;
        this.mLogger = new MetricsLogger();
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mPowerManager = powerManager;
        this.mWakeLock = powerManager.newWakeLock(1, "Elmyra/ElmyraService");
        ArrayList arrayList = new ArrayList(serviceConfigurationGoogle.mActions);
        this.mActions = arrayList;
        arrayList.forEach(new ElmyraService$$ExternalSyntheticLambda0(this, 0));
        this.mFeedbackEffects = new ArrayList(serviceConfigurationGoogle.mFeedbackEffects);
        ArrayList arrayList2 = new ArrayList(serviceConfigurationGoogle.mGates);
        this.mGates = arrayList2;
        arrayList2.forEach(new ElmyraService$$ExternalSyntheticLambda0(this, 1));
        GestureSensor gestureSensor = serviceConfigurationGoogle.mGestureSensor;
        this.mGestureSensor = gestureSensor;
        if (gestureSensor != null) {
            gestureSensor.setGestureListener(r0);
        }
        updateSensorListener$1();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        String str;
        String str2;
        printWriter.println("ElmyraService state:");
        printWriter.println("  Gates:");
        int i = 0;
        int i2 = 0;
        while (true) {
            List list = this.mGates;
            str = "O ";
            if (i2 >= ((ArrayList) list).size()) {
                break;
            }
            printWriter.print("    ");
            if (((Gate) ((ArrayList) list).get(i2)).mActive) {
                if (((Gate) ((ArrayList) list).get(i2)).isBlocking()) {
                    str = "X ";
                }
                printWriter.print(str);
            } else {
                printWriter.print("- ");
            }
            printWriter.println(((Gate) ((ArrayList) list).get(i2)).toString());
            i2++;
        }
        printWriter.println("  Actions:");
        int i3 = 0;
        while (true) {
            List list2 = this.mActions;
            if (i3 >= ((ArrayList) list2).size()) {
                break;
            }
            printWriter.print("    ");
            if (((Action) ((ArrayList) list2).get(i3)).isAvailable()) {
                str2 = str;
            } else {
                str2 = "X ";
            }
            printWriter.print(str2);
            printWriter.println(((Action) ((ArrayList) list2).get(i3)).toString());
            i3++;
        }
        printWriter.println("  Active: " + this.mLastActiveAction);
        printWriter.println("  Feedback Effects:");
        while (true) {
            List list3 = this.mFeedbackEffects;
            if (i >= ((ArrayList) list3).size()) {
                break;
            }
            printWriter.print("    ");
            printWriter.println(((FeedbackEffect) ((ArrayList) list3).get(i)).toString());
            i++;
        }
        StringBuilder sb = new StringBuilder("  Gesture Sensor: ");
        GestureSensor gestureSensor = this.mGestureSensor;
        sb.append(gestureSensor.toString());
        printWriter.println(sb.toString());
        if (gestureSensor instanceof Dumpable) {
            ((Dumpable) gestureSensor).dump(printWriter, strArr);
        }
    }

    public final void stopListening$2() {
        GestureSensor gestureSensor = this.mGestureSensor;
        if (gestureSensor != null && gestureSensor.isListening()) {
            gestureSensor.stopListening();
            int i = 0;
            while (true) {
                List list = this.mFeedbackEffects;
                if (i >= list.size()) {
                    break;
                }
                ((FeedbackEffect) list.get(i)).onRelease();
                i++;
            }
            Action updateActiveAction = updateActiveAction();
            if (updateActiveAction != null) {
                updateActiveAction.onProgress(0, 0.0f);
            }
        }
    }

    public final Action updateActiveAction() {
        Action action;
        int i = 0;
        while (true) {
            List list = this.mActions;
            if (i >= list.size()) {
                action = null;
                break;
            } else if (((Action) list.get(i)).isAvailable()) {
                action = (Action) list.get(i);
                break;
            } else {
                i++;
            }
        }
        Action action2 = this.mLastActiveAction;
        if (!(action2 == null || action == action2)) {
            Log.i("Elmyra/ElmyraService", "Switching action from " + this.mLastActiveAction + " to " + action);
            this.mLastActiveAction.onProgress(0, 0.0f);
        }
        this.mLastActiveAction = action;
        return action;
    }

    public final void updateSensorListener$1() {
        Gate gate;
        Action updateActiveAction = updateActiveAction();
        List list = this.mGates;
        int i = 0;
        if (updateActiveAction == null) {
            Log.i("Elmyra/ElmyraService", "No available actions");
            while (i < list.size()) {
                ((Gate) list.get(i)).deactivate();
                i++;
            }
            stopListening$2();
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((Gate) list.get(i2)).activate();
        }
        while (true) {
            if (i >= list.size()) {
                gate = null;
                break;
            } else if (((Gate) list.get(i)).isBlocking()) {
                gate = (Gate) list.get(i);
                break;
            } else {
                i++;
            }
        }
        if (gate != null) {
            Log.i("Elmyra/ElmyraService", "Gated by " + gate);
            stopListening$2();
            return;
        }
        Log.i("Elmyra/ElmyraService", "Unblocked; current action: " + updateActiveAction);
        GestureSensor gestureSensor = this.mGestureSensor;
        if (gestureSensor != null && !gestureSensor.isListening()) {
            gestureSensor.startListening();
        }
    }
}
