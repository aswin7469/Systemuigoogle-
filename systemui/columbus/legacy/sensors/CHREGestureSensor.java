package com.google.android.systemui.columbus.legacy.sensors;

import android.content.Context;
import android.hardware.google.pixel.vendor.PixelAtoms$DoubleTapNanoappEventReported$Type;
import android.hardware.location.ContextHubClient;
import android.hardware.location.ContextHubInfo;
import android.hardware.location.ContextHubManager;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.StatsEvent;
import android.util.StatsLog;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.RingBuffer;
import com.android.systemui.Dumpable;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import com.google.android.systemui.columbus.legacy.sensors.config.GestureConfiguration;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$GestureDetected;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$NanoappEvent;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$NanoappEvents;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$RecognizerStart;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$ScreenStateUpdate;
import com.google.protobuf.nano.MessageNano;
import java.io.PrintWriter;
import java.util.List;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CHREGestureSensor extends GestureSensor implements Dumpable {
    public final Handler bgHandler;
    public final Context context;
    public ContextHubClient contextHubClient;
    public final CHREGestureSensor$contextHubClientCallback$1 contextHubClientCallback = new CHREGestureSensor$contextHubClientCallback$1(this);
    public final FeatureVectorDumper featureVectorDumper = new FeatureVectorDumper();
    public final GestureConfiguration gestureConfiguration;
    public boolean isAwake;
    public boolean isDozing;
    public boolean isInitialized;
    public boolean isListening;
    public boolean screenOn;
    public boolean screenStateUpdated;
    public final StatusBarStateController statusBarStateController;
    public final CHREGestureSensor$statusBarStateListener$1 statusBarStateListener = new CHREGestureSensor$statusBarStateListener$1(this);
    public final UiEventLogger uiEventLogger;
    public final WakefulnessLifecycle wakefulnessLifecycle;
    public final CHREGestureSensor$wakefulnessLifecycleObserver$1 wakefulnessLifecycleObserver = new CHREGestureSensor$wakefulnessLifecycleObserver$1(this);

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class FeatureVector implements Dumpable {
        public final int gesture;
        public final long timestamp = SystemClock.elapsedRealtime();
        public final float[] vector;

        public FeatureVector(ColumbusProto$GestureDetected columbusProto$GestureDetected) {
            this.vector = columbusProto$GestureDetected.featureVector;
            this.gesture = columbusProto$GestureDetected.gestureType;
        }

        public final void dump(PrintWriter printWriter, String[] strArr) {
            printWriter.println("      Gesture: " + this.gesture + " Time: " + (this.timestamp - SystemClock.elapsedRealtime()));
            float[] fArr = this.vector;
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            int i = 0;
            for (float f : fArr) {
                i++;
                if (i > 1) {
                    sb.append(", ");
                }
                sb.append(String.valueOf(f));
            }
            sb.append(" ]");
            printWriter.println("      ".concat(sb.toString()));
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class FeatureVectorDumper implements Dumpable {
        public final RingBuffer featureVectors = new RingBuffer(FeatureVector.class, 10);
        public FeatureVector lastSingleTapFeatureVector;

        public final void dump(PrintWriter printWriter, String[] strArr) {
            printWriter.println("    Feature Vectors:");
            for (Object obj : this.featureVectors.toArray()) {
                ((FeatureVector) obj).dump(printWriter, strArr);
            }
        }
    }

    public CHREGestureSensor(Context context2, UiEventLogger uiEventLogger2, GestureConfiguration gestureConfiguration2, StatusBarStateController statusBarStateController2, WakefulnessLifecycle wakefulnessLifecycle2, Handler handler) {
        boolean z;
        this.context = context2;
        this.uiEventLogger = uiEventLogger2;
        this.gestureConfiguration = gestureConfiguration2;
        this.statusBarStateController = statusBarStateController2;
        this.wakefulnessLifecycle = wakefulnessLifecycle2;
        this.bgHandler = handler;
        boolean isDozing2 = statusBarStateController2.isDozing();
        this.isDozing = isDozing2;
        boolean z2 = false;
        if (wakefulnessLifecycle2.mWakefulness == 2) {
            z = true;
        } else {
            z = false;
        }
        this.isAwake = z;
        if (z && !isDozing2) {
            z2 = true;
        }
        this.screenOn = z2;
        this.screenStateUpdated = true;
    }

    public static final void access$handleGestureDetection(CHREGestureSensor cHREGestureSensor, ColumbusProto$GestureDetected columbusProto$GestureDetected) {
        boolean z;
        cHREGestureSensor.getClass();
        int i = columbusProto$GestureDetected.gestureType;
        int i2 = 0;
        if (i == 2) {
            z = true;
        } else {
            z = false;
        }
        if (i == 1) {
            i2 = 1;
        } else if (i == 2) {
            i2 = 2;
        }
        GestureSensor.DetectionProperties detectionProperties = new GestureSensor.DetectionProperties(z);
        GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1 = cHREGestureSensor.listener;
        if (gestureController$gestureSensorListener$1 != null) {
            gestureController$gestureSensorListener$1.onGestureDetected(i2, detectionProperties);
        }
        FeatureVectorDumper featureVectorDumper2 = cHREGestureSensor.featureVectorDumper;
        featureVectorDumper2.getClass();
        int i3 = columbusProto$GestureDetected.gestureType;
        if (i3 == 1) {
            FeatureVector featureVector = featureVectorDumper2.lastSingleTapFeatureVector;
            featureVectorDumper2.lastSingleTapFeatureVector = null;
            if (featureVector == null) {
                Log.w("Columbus/GestureSensor", "Received double tap without single taps, event will not appear in sysdump");
                return;
            }
            RingBuffer ringBuffer = featureVectorDumper2.featureVectors;
            ringBuffer.append(featureVector);
            ringBuffer.append(new FeatureVector(columbusProto$GestureDetected));
        } else if (i3 == 2) {
            featureVectorDumper2.lastSingleTapFeatureVector = new FeatureVector(columbusProto$GestureDetected);
        }
    }

    public static final void access$handleNanoappEvents(CHREGestureSensor cHREGestureSensor, ColumbusProto$NanoappEvents columbusProto$NanoappEvents) {
        int i;
        cHREGestureSensor.getClass();
        for (ColumbusProto$NanoappEvent columbusProto$NanoappEvent : columbusProto$NanoappEvents.batchedEvents) {
            StatsEvent.Builder writeLong = StatsEvent.newBuilder().setAtomId(100051).writeLong(columbusProto$NanoappEvent.timestamp);
            switch (columbusProto$NanoappEvent.type) {
                case 1:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.GATE_START.getNumber();
                    break;
                case 2:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.GATE_STOP.getNumber();
                    break;
                case 3:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.HIGH_IMU_ODR_START.getNumber();
                    break;
                case 4:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.HIGH_IMU_ODR_STOP.getNumber();
                    break;
                case 5:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.ML_PREDICTION_START.getNumber();
                    break;
                case 6:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.ML_PREDICTION_STOP.getNumber();
                    break;
                case ViewNode.WIDTH_FIELD_NUMBER:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.SINGLE_TAP.getNumber();
                    break;
                case 8:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.DOUBLE_TAP.getNumber();
                    break;
                default:
                    i = PixelAtoms$DoubleTapNanoappEventReported$Type.UNKNOWN.getNumber();
                    break;
            }
            StatsLog.write(writeLong.writeInt(i).build());
        }
    }

    public static void sendMessageToNanoApp$default(CHREGestureSensor cHREGestureSensor, int i, byte[] bArr, Function0 function0, int i2) {
        if ((i2 & 4) != 0) {
            function0 = null;
        }
        Function0 function02 = function0;
        cHREGestureSensor.initializeContextHubClientIfNull();
        if (cHREGestureSensor.contextHubClient == null) {
            Log.w("Columbus/GestureSensor", "ContextHubClient null");
        } else {
            cHREGestureSensor.bgHandler.post(new CHREGestureSensor$sendMessageToNanoApp$1(cHREGestureSensor, i, bArr, (Function0) null, function02));
        }
    }

    public final void close() {
        Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor close");
        if (this.isInitialized) {
            this.gestureConfiguration.listener = null;
            this.statusBarStateController.removeCallback(this.statusBarStateListener);
            this.wakefulnessLifecycle.removeObserver(this.wakefulnessLifecycleObserver);
            ContextHubClient contextHubClient2 = this.contextHubClient;
            if (contextHubClient2 != null) {
                contextHubClient2.close();
            }
            this.contextHubClient = null;
            this.isInitialized = false;
        }
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        this.featureVectorDumper.dump(printWriter, strArr);
    }

    public final void initializeContextHubClientIfNull() {
        List list;
        if (this.contextHubClient == null) {
            ContextHubManager contextHubManager = (ContextHubManager) this.context.getSystemService("contexthub");
            if (contextHubManager != null) {
                list = contextHubManager.getContextHubs();
            } else {
                list = null;
            }
            if (list == null || list.size() == 0) {
                Log.e("Columbus/GestureSensor", "No context hubs found");
            } else if (list != null) {
                this.contextHubClient = contextHubManager.createClient((ContextHubInfo) list.get(0), this.contextHubClientCallback);
            }
        }
    }

    public final boolean isListening() {
        return this.isListening;
    }

    public final void sendScreenState() {
        int i;
        ColumbusProto$ScreenStateUpdate columbusProto$ScreenStateUpdate = new ColumbusProto$ScreenStateUpdate();
        if (this.screenOn) {
            i = 1;
        } else {
            i = 2;
        }
        columbusProto$ScreenStateUpdate.screenState = i;
        byte[] byteArray = MessageNano.toByteArray(columbusProto$ScreenStateUpdate);
        CHREGestureSensor$sendScreenState$1 cHREGestureSensor$sendScreenState$1 = new CHREGestureSensor$sendScreenState$1(this);
        CHREGestureSensor$sendScreenState$2 cHREGestureSensor$sendScreenState$2 = new CHREGestureSensor$sendScreenState$2(this);
        initializeContextHubClientIfNull();
        if (this.contextHubClient == null) {
            Log.w("Columbus/GestureSensor", "ContextHubClient null");
        } else {
            this.bgHandler.post(new CHREGestureSensor$sendMessageToNanoApp$1(this, 400, byteArray, cHREGestureSensor$sendScreenState$2, cHREGestureSensor$sendScreenState$1));
        }
    }

    public final void startListening() {
        if (!this.isInitialized) {
            Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor initialize");
            this.isInitialized = true;
            this.gestureConfiguration.listener = new CHREGestureSensor$initialize$1(this);
            this.statusBarStateController.addCallback(this.statusBarStateListener);
            this.wakefulnessLifecycle.addObserver(this.wakefulnessLifecycleObserver);
            initializeContextHubClientIfNull();
        }
        Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor startListening");
        this.isListening = true;
        startRecognizer();
        sendScreenState();
    }

    public final void startRecognizer() {
        ColumbusProto$RecognizerStart columbusProto$RecognizerStart = new ColumbusProto$RecognizerStart(0);
        columbusProto$RecognizerStart.sensitivity = this.gestureConfiguration.sensitivity;
        sendMessageToNanoApp$default(this, 100, MessageNano.toByteArray(columbusProto$RecognizerStart), new CHREGestureSensor$startRecognizer$1(this), 8);
    }

    public final void stopListening() {
        Log.i("Columbus/GestureSensor", "Legacy CHREGestureSensor stopListening");
        sendMessageToNanoApp$default(this, 101, new byte[0], new CHREGestureSensor$stopListening$1(this), 8);
        this.isListening = false;
    }

    public final void updateScreenState() {
        boolean z;
        if (!this.isAwake || this.isDozing) {
            z = false;
        } else {
            z = true;
        }
        if (this.screenOn != z || !this.screenStateUpdated) {
            this.screenOn = z;
            if (this.isListening) {
                sendScreenState();
            }
        }
    }
}
