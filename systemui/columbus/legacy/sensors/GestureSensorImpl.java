package com.google.android.systemui.columbus.legacy.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.google.android.systemui.columbus.ColumbusEvent;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class GestureSensorImpl extends GestureSensor {
    public final Sensor accelerometer;
    public final Sensor gyroscope;
    public final Handler handler;
    public boolean isListening;
    public final long samplingIntervalNs;
    public final GestureSensorEventListener sensorEventListener = new GestureSensorEventListener();
    public final SensorManager sensorManager;
    public final TapRT tap;
    public final UiEventLogger uiEventLogger;

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.sensors.TapRT, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r14v5, types: [java.lang.Object, com.google.android.systemui.columbus.legacy.sensors.TfClassifier] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x00f2, code lost:
        if (r13.equals("Pixel 4") != false) goto L_0x0100;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GestureSensorImpl(android.content.Context r12, android.os.Handler r13, com.android.internal.logging.UiEventLogger r14) {
        /*
            r11 = this;
            r11.<init>()
            r11.uiEventLogger = r14
            r11.handler = r13
            java.lang.String r13 = "sensor"
            java.lang.Object r13 = r12.getSystemService(r13)
            android.hardware.SensorManager r13 = (android.hardware.SensorManager) r13
            r11.sensorManager = r13
            r14 = 1
            android.hardware.Sensor r0 = r13.getDefaultSensor(r14)
            r11.accelerometer = r0
            r0 = 4
            android.hardware.Sensor r13 = r13.getDefaultSensor(r0)
            r11.gyroscope = r13
            com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener r13 = new com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener
            r13.<init>()
            r11.sensorEventListener = r13
            java.lang.String r13 = android.os.Build.MODEL
            r1 = 2400000(0x249f00, double:1.1857576E-317)
            r11.samplingIntervalNs = r1
            com.google.android.systemui.columbus.legacy.sensors.TapRT r1 = new com.google.android.systemui.columbus.legacy.sensors.TapRT
            android.content.res.AssetManager r12 = r12.getAssets()
            java.lang.String r2 = "tflite file loaded: "
            r1.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r1.mFeatureVector = r3
            r3 = 0
            r1.mClassifier = r3
            java.util.ArrayDeque r3 = new java.util.ArrayDeque
            r3.<init>()
            r1.mAccXs = r3
            java.util.ArrayDeque r3 = new java.util.ArrayDeque
            r3.<init>()
            r1.mAccYs = r3
            java.util.ArrayDeque r3 = new java.util.ArrayDeque
            r3.<init>()
            r1.mAccZs = r3
            java.util.ArrayDeque r3 = new java.util.ArrayDeque
            r3.<init>()
            r1.mGyroXs = r3
            java.util.ArrayDeque r3 = new java.util.ArrayDeque
            r3.<init>()
            r1.mGyroYs = r3
            java.util.ArrayDeque r3 = new java.util.ArrayDeque
            r3.<init>()
            r1.mGyroZs = r3
            r3 = 0
            r1.mGotAcc = r3
            r1.mGotGyro = r3
            r4 = 0
            r1.mSyncTime = r4
            com.google.android.systemui.columbus.legacy.sensors.Resample3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Resample3C
            r4.<init>()
            r1.mResampleAcc = r4
            com.google.android.systemui.columbus.legacy.sensors.Resample3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Resample3C
            r4.<init>()
            r1.mResampleGyro = r4
            com.google.android.systemui.columbus.legacy.sensors.Slope3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Slope3C
            r4.<init>()
            r1.mSlopeAcc = r4
            com.google.android.systemui.columbus.legacy.sensors.Slope3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Slope3C
            r4.<init>()
            r1.mSlopeGyro = r4
            com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Lowpass3C
            r4.<init>()
            r1.mLowpassAcc = r4
            com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Lowpass3C
            r4.<init>()
            r1.mLowpassGyro = r4
            com.google.android.systemui.columbus.legacy.sensors.Highpass3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Highpass3C
            r4.<init>()
            r1.mHighpassAcc = r4
            com.google.android.systemui.columbus.legacy.sensors.Highpass3C r4 = new com.google.android.systemui.columbus.legacy.sensors.Highpass3C
            r4.<init>()
            r1.mHighpassGyro = r4
            com.google.android.systemui.columbus.legacy.sensors.PeakDetector r4 = new com.google.android.systemui.columbus.legacy.sensors.PeakDetector
            r4.<init>()
            r1.mPeakDetector = r4
            com.google.android.systemui.columbus.legacy.sensors.PeakDetector r4 = new com.google.android.systemui.columbus.legacy.sensors.PeakDetector
            r4.<init>()
            r1.mValleyDetector = r4
            java.util.ArrayDeque r4 = new java.util.ArrayDeque
            r4.<init>()
            r1.mTimestampsBackTap = r4
            r1.mWasPeakApproaching = r14
            int r4 = r13.hashCode()
            r5 = 3
            r6 = 2
            switch(r4) {
                case -870267800: goto L_0x00f5;
                case 1105847546: goto L_0x00ec;
                case 1105847547: goto L_0x00e2;
                case 1905086331: goto L_0x00d8;
                case 1905116122: goto L_0x00ce;
                default: goto L_0x00cd;
            }
        L_0x00cd:
            goto L_0x00ff
        L_0x00ce:
            java.lang.String r14 = "Pixel 4 XL"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x00ff
            r14 = r6
            goto L_0x0100
        L_0x00d8:
            java.lang.String r14 = "Pixel 3 XL"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x00ff
            r14 = r3
            goto L_0x0100
        L_0x00e2:
            java.lang.String r14 = "Pixel 5"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x00ff
            r14 = r0
            goto L_0x0100
        L_0x00ec:
            java.lang.String r3 = "Pixel 4"
            boolean r13 = r13.equals(r3)
            if (r13 == 0) goto L_0x00ff
            goto L_0x0100
        L_0x00f5:
            java.lang.String r14 = "Pixel 4a (5G)"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x00ff
            r14 = r5
            goto L_0x0100
        L_0x00ff:
            r14 = -1
        L_0x0100:
            if (r14 == 0) goto L_0x0114
            if (r14 == r6) goto L_0x0111
            if (r14 == r5) goto L_0x010e
            if (r14 == r0) goto L_0x010b
            java.lang.String r13 = "tap7cls_flame.tflite"
            goto L_0x0116
        L_0x010b:
            java.lang.String r13 = "tap7cls_redfin.tflite"
            goto L_0x0116
        L_0x010e:
            java.lang.String r13 = "tap7cls_bramble.tflite"
            goto L_0x0116
        L_0x0111:
            java.lang.String r13 = "tap7cls_coral.tflite"
            goto L_0x0116
        L_0x0114:
            java.lang.String r13 = "tap7cls_crosshatch.tflite"
        L_0x0116:
            java.lang.String r14 = "TapRT loaded "
            java.lang.String r14 = r14.concat(r13)
            java.lang.String r0 = "Columbus"
            android.util.Log.d(r0, r14)
            com.google.android.systemui.columbus.legacy.sensors.TfClassifier r14 = new com.google.android.systemui.columbus.legacy.sensors.TfClassifier
            r14.<init>()
            android.content.res.AssetFileDescriptor r12 = r12.openFd(r13)     // Catch:{ Exception -> 0x0154 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0154 }
            java.io.FileDescriptor r4 = r12.getFileDescriptor()     // Catch:{ Exception -> 0x0154 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0154 }
            java.nio.channels.FileChannel r5 = r3.getChannel()     // Catch:{ Exception -> 0x0154 }
            long r7 = r12.getStartOffset()     // Catch:{ Exception -> 0x0154 }
            long r9 = r12.getDeclaredLength()     // Catch:{ Exception -> 0x0154 }
            java.nio.channels.FileChannel$MapMode r6 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ Exception -> 0x0154 }
            java.nio.MappedByteBuffer r12 = r5.map(r6, r7, r9)     // Catch:{ Exception -> 0x0154 }
            org.tensorflow.lite.Interpreter r3 = new org.tensorflow.lite.Interpreter     // Catch:{ Exception -> 0x0154 }
            r3.<init>(r12)     // Catch:{ Exception -> 0x0154 }
            r14.mInterpreter = r3     // Catch:{ Exception -> 0x0154 }
            java.lang.String r12 = r2.concat(r13)     // Catch:{ Exception -> 0x0154 }
            android.util.Log.d(r0, r12)     // Catch:{ Exception -> 0x0154 }
            goto L_0x0173
        L_0x0154:
            r12 = move-exception
            java.lang.String r2 = "load tflite file error: "
            java.lang.String r13 = r2.concat(r13)
            android.util.Log.d(r0, r13)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r2 = "tflite file:"
            r13.<init>(r2)
            java.lang.String r12 = r12.toString()
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            android.util.Log.e(r0, r12)
        L_0x0173:
            r1.mClassifier = r14
            r12 = 153600000(0x927c000, double:7.5888483E-316)
            r1.mSizeWindowNs = r12
            r12 = 50
            r1.mSizeFeatureWindow = r12
            r12 = 300(0x12c, float:4.2E-43)
            r1.mNumberFeature = r12
            com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r12 = r1.mLowpassAcc
            r12.setPara()
            com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r12 = r1.mLowpassGyro
            r12.setPara()
            com.google.android.systemui.columbus.legacy.sensors.Highpass3C r12 = r1.mHighpassAcc
            r12.setPara()
            com.google.android.systemui.columbus.legacy.sensors.Highpass3C r12 = r1.mHighpassGyro
            r12.setPara()
            r11.tap = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.<init>(android.content.Context, android.os.Handler, com.android.internal.logging.UiEventLogger):void");
    }

    public final boolean isListening() {
        return this.isListening;
    }

    public final void startListening() {
        this.sensorEventListener.setListening(true);
        this.tap.mLowpassAcc.setPara();
        this.tap.mLowpassGyro.setPara();
        this.tap.mHighpassAcc.setPara();
        this.tap.mHighpassGyro.setPara();
        TapRT tapRT = this.tap;
        PeakDetector peakDetector = tapRT.mPeakDetector;
        peakDetector.mMinNoiseTolerate = 0.03f;
        peakDetector.mWindowSize = 64;
        PeakDetector peakDetector2 = tapRT.mValleyDetector;
        peakDetector2.mMinNoiseTolerate = 0.015f;
        peakDetector2.mWindowSize = 64;
        tapRT.reset$com$google$android$systemui$columbus$legacy$sensors$EventIMURT();
        int i = tapRT.mNumberFeature;
        tapRT.mFeatureVector = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            tapRT.mFeatureVector.add(Float.valueOf(0.0f));
        }
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_HIGH_POWER_ACTIVE);
    }

    public final void stopListening() {
        this.sensorEventListener.setListening(false);
        this.uiEventLogger.log(ColumbusEvent.COLUMBUS_MODE_INACTIVE);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class GestureSensorEventListener implements SensorEventListener {
        public GestureSensorEventListener() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x006f, code lost:
            if (r2.mGotAcc == false) goto L_0x0448;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0048, code lost:
            if (r2.mGotGyro == false) goto L_0x0448;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onSensorChanged(android.hardware.SensorEvent r23) {
            /*
                r22 = this;
                r0 = r23
                if (r0 == 0) goto L_0x04c8
                r1 = r22
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl r1 = com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.this
                com.google.android.systemui.columbus.legacy.sensors.TapRT r2 = r1.tap
                android.hardware.Sensor r3 = r0.sensor
                int r3 = r3.getType()
                float[] r4 = r0.values
                r5 = 0
                r12 = r4[r5]
                r13 = 1
                r14 = r4[r13]
                r15 = 2
                r4 = r4[r15]
                long r10 = r0.timestamp
                long r6 = r1.samplingIntervalNs
                r8 = 6
                r2.mResult = r8
                java.util.Deque r9 = r2.mTimestampsBackTap
                r16 = 0
                if (r3 != r13) goto L_0x004c
                r2.mGotAcc = r13
                r22 = r9
                long r8 = r2.mSyncTime
                int r8 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
                if (r8 != 0) goto L_0x0046
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r8 = r2.mResampleAcc
                r8.mRawLastX = r12
                r8.mRawLastT = r10
                r8.mResampledThisX = r12
                r8.mResampledLastT = r10
                r8.mInterval = r6
                r8.mRawLastY = r14
                r8.mRawLastZ = r4
                r8.mResampledThisY = r14
                r8.mResampledThisZ = r4
            L_0x0046:
                boolean r6 = r2.mGotGyro
                if (r6 != 0) goto L_0x0073
                goto L_0x0448
            L_0x004c:
                r22 = r9
                r8 = 4
                if (r3 != r8) goto L_0x0073
                r2.mGotGyro = r13
                long r8 = r2.mSyncTime
                int r8 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
                if (r8 != 0) goto L_0x006d
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r8 = r2.mResampleGyro
                r8.mRawLastX = r12
                r8.mRawLastT = r10
                r8.mResampledThisX = r12
                r8.mResampledLastT = r10
                r8.mInterval = r6
                r8.mRawLastY = r14
                r8.mRawLastZ = r4
                r8.mResampledThisY = r14
                r8.mResampledThisZ = r4
            L_0x006d:
                boolean r6 = r2.mGotAcc
                if (r6 != 0) goto L_0x0073
                goto L_0x0448
            L_0x0073:
                long r6 = r2.mSyncTime
                int r6 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
                if (r6 != 0) goto L_0x010c
                r2.mSyncTime = r10
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r3 = r2.mResampleAcc
                r3.mResampledLastT = r10
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r4 = r2.mResampleGyro
                r4.mResampledLastT = r10
                com.google.android.systemui.columbus.legacy.sensors.Slope3C r4 = r2.mSlopeAcc
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r3 = r3.getResults()
                com.google.android.systemui.columbus.legacy.sensors.Point3f r3 = r3.mPoint
                r4.getClass()
                float r6 = r3.x
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r7 = r4.mSlopeX
                r7.mRawLastX = r6
                float r6 = r3.y
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r7 = r4.mSlopeY
                r7.mRawLastX = r6
                float r3 = r3.z
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r4 = r4.mSlopeZ
                r4.mRawLastX = r3
                com.google.android.systemui.columbus.legacy.sensors.Slope3C r3 = r2.mSlopeGyro
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r4 = r2.mResampleGyro
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r4 = r4.getResults()
                com.google.android.systemui.columbus.legacy.sensors.Point3f r4 = r4.mPoint
                r3.getClass()
                float r6 = r4.x
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r7 = r3.mSlopeX
                r7.mRawLastX = r6
                float r6 = r4.y
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r7 = r3.mSlopeY
                r7.mRawLastX = r6
                float r4 = r4.z
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r3 = r3.mSlopeZ
                r3.mRawLastX = r4
                com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r3 = r2.mLowpassAcc
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r4 = r3.mLowpassX
                r6 = 0
                r4.mLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r4 = r3.mLowpassY
                r4.mLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r3 = r3.mLowpassZ
                r3.mLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r3 = r2.mLowpassGyro
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r4 = r3.mLowpassX
                r4.mLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r4 = r3.mLowpassY
                r4.mLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r3 = r3.mLowpassZ
                r3.mLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Highpass3C r3 = r2.mHighpassAcc
                r3.getClass()
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r4 = r3.mHighpassX
                r4.mLastX = r6
                r4.mLastY = r6
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r4 = r3.mHighpassY
                r4.mLastX = r6
                r4.mLastY = r6
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r3 = r3.mHighpassZ
                r3.mLastX = r6
                r3.mLastY = r6
                com.google.android.systemui.columbus.legacy.sensors.Highpass3C r2 = r2.mHighpassGyro
                r2.getClass()
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r3 = r2.mHighpassX
                r3.mLastX = r6
                r3.mLastY = r6
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r3 = r2.mHighpassY
                r3.mLastX = r6
                r3.mLastY = r6
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r2 = r2.mHighpassZ
                r2.mLastX = r6
                r2.mLastY = r6
                goto L_0x0448
            L_0x010c:
                long r8 = r2.mSizeWindowNs
                com.google.android.systemui.columbus.legacy.sensors.PeakDetector r7 = r2.mValleyDetector
                com.google.android.systemui.columbus.legacy.sensors.PeakDetector r6 = r2.mPeakDetector
                r16 = 1242725376(0x4a127c00, float:2400000.0)
                if (r3 != r13) goto L_0x0208
            L_0x0117:
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r3 = r2.mResampleAcc
                r15 = r6
                r6 = r3
                r3 = r7
                r7 = r12
                r18 = r8
                r8 = r14
                r9 = r4
                r20 = r10
                boolean r6 = r6.update(r7, r8, r9, r10)
                if (r6 == 0) goto L_0x0448
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r6 = r2.mResampleAcc
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r6 = r6.getResults()
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r7 = r2.mResampleAcc
                long r7 = r7.mInterval
                float r7 = (float) r7
                float r7 = r16 / r7
                com.google.android.systemui.columbus.legacy.sensors.Slope3C r8 = r2.mSlopeAcc
                com.google.android.systemui.columbus.legacy.sensors.Point3f r6 = r6.mPoint
                r8.getClass()
                float r9 = r6.x
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r10 = r8.mSlopeX
                float r9 = r9 * r7
                float r11 = r10.mRawLastX
                float r11 = r9 - r11
                r10.mRawLastX = r9
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r9 = r8.mSlopeY
                float r10 = r6.y
                float r10 = r10 * r7
                float r13 = r9.mRawLastX
                float r13 = r10 - r13
                r9.mRawLastX = r10
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r8 = r8.mSlopeZ
                float r6 = r6.z
                float r6 = r6 * r7
                float r7 = r8.mRawLastX
                float r7 = r6 - r7
                r8.mRawLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r6 = r2.mLowpassAcc
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r8 = r6.mLowpassX
                float r8 = r8.update(r11)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r9 = r6.mLowpassY
                float r9 = r9.update(r13)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r6 = r6.mLowpassZ
                float r6 = r6.update(r7)
                com.google.android.systemui.columbus.legacy.sensors.Highpass3C r7 = r2.mHighpassAcc
                r7.getClass()
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r10 = r7.mHighpassX
                float r8 = r10.update(r8)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r10 = r7.mHighpassY
                float r9 = r10.update(r9)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r7 = r7.mHighpassZ
                float r6 = r7.update(r6)
                java.util.Deque r7 = r2.mAccXs
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r7.add(r8)
                java.util.Deque r7 = r2.mAccYs
                java.lang.Float r8 = java.lang.Float.valueOf(r9)
                r7.add(r8)
                java.util.Deque r7 = r2.mAccZs
                java.lang.Float r6 = java.lang.Float.valueOf(r6)
                r7.add(r6)
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r6 = r2.mResampleAcc
                long r6 = r6.mInterval
                long r8 = r18 / r6
                int r6 = (int) r8
            L_0x01ab:
                java.util.Deque r7 = r2.mAccXs
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                int r7 = r7.size()
                if (r7 <= r6) goto L_0x01cb
                java.util.Deque r7 = r2.mAccXs
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                r7.removeFirst()
                java.util.Deque r7 = r2.mAccYs
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                r7.removeFirst()
                java.util.Deque r7 = r2.mAccZs
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                r7.removeFirst()
                goto L_0x01ab
            L_0x01cb:
                java.util.Deque r6 = r2.mAccZs
                java.util.ArrayDeque r6 = (java.util.ArrayDeque) r6
                java.lang.Object r6 = r6.getLast()
                java.lang.Float r6 = (java.lang.Float) r6
                float r6 = r6.floatValue()
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r7 = r2.mResampleAcc
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r7 = r7.getResults()
                long r7 = r7.mT
                r15.update(r6, r7)
                java.util.Deque r6 = r2.mAccZs
                java.util.ArrayDeque r6 = (java.util.ArrayDeque) r6
                java.lang.Object r6 = r6.getLast()
                java.lang.Float r6 = (java.lang.Float) r6
                float r6 = r6.floatValue()
                float r6 = -r6
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r7 = r2.mResampleAcc
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r7 = r7.getResults()
                long r7 = r7.mT
                r3.update(r6, r7)
                r7 = r3
                r6 = r15
                r8 = r18
                r10 = r20
                r13 = 1
                r15 = 2
                goto L_0x0117
            L_0x0208:
                r15 = r6
                r13 = r7
                r18 = r8
                r20 = r10
                r6 = 4
                if (r3 != r6) goto L_0x0448
            L_0x0211:
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r6 = r2.mResampleGyro
                r7 = r12
                r8 = r14
                r3 = r22
                r9 = r4
                r10 = r20
                boolean r6 = r6.update(r7, r8, r9, r10)
                if (r6 == 0) goto L_0x0439
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r6 = r2.mResampleGyro
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r6 = r6.getResults()
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r7 = r2.mResampleGyro
                long r7 = r7.mInterval
                float r7 = (float) r7
                float r7 = r16 / r7
                com.google.android.systemui.columbus.legacy.sensors.Slope3C r8 = r2.mSlopeGyro
                com.google.android.systemui.columbus.legacy.sensors.Point3f r6 = r6.mPoint
                r8.getClass()
                float r9 = r6.x
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r10 = r8.mSlopeX
                float r9 = r9 * r7
                float r11 = r10.mRawLastX
                float r11 = r9 - r11
                r10.mRawLastX = r9
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r9 = r8.mSlopeY
                float r10 = r6.y
                float r10 = r10 * r7
                float r5 = r9.mRawLastX
                float r5 = r10 - r5
                r9.mRawLastX = r10
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r8 = r8.mSlopeZ
                float r6 = r6.z
                float r6 = r6 * r7
                float r7 = r8.mRawLastX
                float r7 = r6 - r7
                r8.mRawLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r6 = r2.mLowpassGyro
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r8 = r6.mLowpassX
                float r8 = r8.update(r11)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r9 = r6.mLowpassY
                float r5 = r9.update(r5)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r6 = r6.mLowpassZ
                float r6 = r6.update(r7)
                com.google.android.systemui.columbus.legacy.sensors.Highpass3C r7 = r2.mHighpassGyro
                r7.getClass()
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r9 = r7.mHighpassX
                float r8 = r9.update(r8)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r9 = r7.mHighpassY
                float r5 = r9.update(r5)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r7 = r7.mHighpassZ
                float r6 = r7.update(r6)
                java.util.Deque r7 = r2.mGyroXs
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r7.add(r8)
                java.util.Deque r7 = r2.mGyroYs
                java.lang.Float r5 = java.lang.Float.valueOf(r5)
                r7.add(r5)
                java.util.Deque r5 = r2.mGyroZs
                java.lang.Float r6 = java.lang.Float.valueOf(r6)
                r5.add(r6)
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r5 = r2.mResampleGyro
                long r5 = r5.mInterval
                long r8 = r18 / r5
                int r5 = (int) r8
            L_0x02a2:
                java.util.Deque r6 = r2.mGyroXs
                java.util.ArrayDeque r6 = (java.util.ArrayDeque) r6
                int r6 = r6.size()
                if (r6 <= r5) goto L_0x02c2
                java.util.Deque r6 = r2.mGyroXs
                java.util.ArrayDeque r6 = (java.util.ArrayDeque) r6
                r6.removeFirst()
                java.util.Deque r6 = r2.mGyroYs
                java.util.ArrayDeque r6 = (java.util.ArrayDeque) r6
                r6.removeFirst()
                java.util.Deque r6 = r2.mGyroZs
                java.util.ArrayDeque r6 = (java.util.ArrayDeque) r6
                r6.removeFirst()
                goto L_0x02a2
            L_0x02c2:
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r5 = r2.mResampleAcc
                long r6 = r5.mInterval
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r5 = r5.getResults()
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r8 = r2.mResampleGyro
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r8 = r8.getResults()
                long r9 = r5.mT
                r11 = r4
                long r4 = r8.mT
                long r9 = r9 - r4
                long r9 = r9 / r6
                int r4 = (int) r9
                int r5 = r15.mPeakId
                r6 = 0
                int r5 = java.lang.Math.max(r6, r5)
                int r7 = r13.mPeakId
                int r7 = java.lang.Math.max(r6, r7)
                float r6 = r15.mAmplitude
                float r8 = r13.mAmplitude
                int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r6 <= 0) goto L_0x02ee
                goto L_0x02ef
            L_0x02ee:
                r5 = r7
            L_0x02ef:
                r6 = 12
                if (r5 <= r6) goto L_0x02f6
                r7 = 1
                r2.mWasPeakApproaching = r7
            L_0x02f6:
                int r7 = r5 + -6
                int r4 = r7 - r4
                java.util.Deque r8 = r2.mAccZs
                java.util.ArrayDeque r8 = (java.util.ArrayDeque) r8
                int r8 = r8.size()
                if (r7 < 0) goto L_0x0433
                if (r4 < 0) goto L_0x0433
                int r9 = r2.mSizeFeatureWindow
                int r10 = r7 + r9
                if (r10 > r8) goto L_0x0433
                int r10 = r4 + r9
                if (r10 <= r8) goto L_0x0312
                goto L_0x0433
            L_0x0312:
                boolean r8 = r2.mWasPeakApproaching
                if (r8 == 0) goto L_0x0433
                if (r5 > r6) goto L_0x0433
                r5 = 0
                r2.mWasPeakApproaching = r5
                java.util.Deque r6 = r2.mAccXs
                r2.addToFeatureVector(r6, r7, r5)
                java.util.Deque r5 = r2.mAccYs
                r2.addToFeatureVector(r5, r7, r9)
                java.util.Deque r5 = r2.mAccZs
                int r6 = r9 * 2
                r2.addToFeatureVector(r5, r7, r6)
                java.util.Deque r5 = r2.mGyroXs
                int r6 = r9 * 3
                r2.addToFeatureVector(r5, r4, r6)
                java.util.Deque r5 = r2.mGyroYs
                int r6 = r9 * 4
                r2.addToFeatureVector(r5, r4, r6)
                java.util.Deque r5 = r2.mGyroZs
                int r9 = r9 * 5
                r2.addToFeatureVector(r5, r4, r9)
                java.util.ArrayList r4 = new java.util.ArrayList
                java.util.ArrayList r5 = r2.mFeatureVector
                r6 = 100
                r7 = 150(0x96, float:2.1E-43)
                java.util.List r5 = r5.subList(r6, r7)
                r4.<init>(r5)
                java.util.ArrayList r4 = r2.mFeatureVector
                int r5 = r4.size()
                r6 = 2
                int r5 = r5 / r6
            L_0x0358:
                int r6 = r4.size()
                if (r5 >= r6) goto L_0x0375
                java.lang.Object r6 = r4.get(r5)
                java.lang.Float r6 = (java.lang.Float) r6
                float r6 = r6.floatValue()
                r7 = 1092616192(0x41200000, float:10.0)
                float r6 = r6 * r7
                java.lang.Float r6 = java.lang.Float.valueOf(r6)
                r4.set(r5, r6)
                int r5 = r5 + 1
                goto L_0x0358
            L_0x0375:
                r2.mFeatureVector = r4
                com.google.android.systemui.columbus.legacy.sensors.TfClassifier r5 = r2.mClassifier
                org.tensorflow.lite.Interpreter r5 = r5.mInterpreter
                if (r5 != 0) goto L_0x0385
                java.util.ArrayList r4 = new java.util.ArrayList
                r4.<init>()
                r9 = 0
                goto L_0x03fe
            L_0x0385:
                int r6 = r4.size()
                r7 = 1
                int[] r6 = new int[]{r7, r6, r7, r7}
                java.lang.Class r7 = java.lang.Float.TYPE
                java.lang.Object r6 = java.lang.reflect.Array.newInstance(r7, r6)
                float[][][][] r6 = (float[][][][]) r6
                r7 = 0
            L_0x0397:
                int r8 = r4.size()
                if (r7 >= r8) goto L_0x03b3
                java.lang.Object r8 = r4.get(r7)
                java.lang.Float r8 = (java.lang.Float) r8
                float r8 = r8.floatValue()
                r9 = 0
                r10 = r6[r9]
                r10 = r10[r7]
                r10 = r10[r9]
                r10[r9] = r8
                int r7 = r7 + 1
                goto L_0x0397
            L_0x03b3:
                java.lang.Object[] r4 = new java.lang.Object[]{r6}
                java.util.HashMap r6 = new java.util.HashMap
                r6.<init>()
                r7 = 7
                r8 = 1
                int[] r9 = new int[]{r8, r7}
                java.lang.Class r8 = java.lang.Float.TYPE
                java.lang.Object r8 = java.lang.reflect.Array.newInstance(r8, r9)
                float[][] r8 = (float[][]) r8
                r9 = 0
                java.lang.Integer r10 = java.lang.Integer.valueOf(r9)
                r6.put(r10, r8)
                r5.runForMultipleInputsOutputs(r4, r6)
                java.lang.Integer r4 = java.lang.Integer.valueOf(r9)
                java.lang.Object r4 = r6.get(r4)
                float[][] r4 = (float[][]) r4
                java.util.ArrayList r5 = new java.util.ArrayList
                r5.<init>()
                java.util.ArrayList r6 = new java.util.ArrayList
                r6.<init>()
                r8 = r9
            L_0x03ea:
                if (r8 >= r7) goto L_0x03fa
                r10 = r4[r9]
                r10 = r10[r8]
                java.lang.Float r10 = java.lang.Float.valueOf(r10)
                r6.add(r10)
                int r8 = r8 + 1
                goto L_0x03ea
            L_0x03fa:
                r5.add(r6)
                r4 = r5
            L_0x03fe:
                boolean r5 = r4.isEmpty()
                if (r5 != 0) goto L_0x0433
                java.lang.Object r4 = r4.get(r9)
                java.util.ArrayList r4 = (java.util.ArrayList) r4
                r5 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
                r6 = 0
                r7 = 0
            L_0x040f:
                int r8 = r4.size()
                if (r6 >= r8) goto L_0x0431
                java.lang.Object r8 = r4.get(r6)
                java.lang.Float r8 = (java.lang.Float) r8
                float r8 = r8.floatValue()
                int r8 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r8 >= 0) goto L_0x042e
                java.lang.Object r5 = r4.get(r6)
                java.lang.Float r5 = (java.lang.Float) r5
                float r5 = r5.floatValue()
                r7 = r6
            L_0x042e:
                int r6 = r6 + 1
                goto L_0x040f
            L_0x0431:
                r2.mResult = r7
            L_0x0433:
                r22 = r3
                r4 = r11
                r5 = 0
                goto L_0x0211
            L_0x0439:
                int r2 = r2.mResult
                r4 = 1
                if (r2 != r4) goto L_0x0448
                java.lang.Long r2 = java.lang.Long.valueOf(r20)
                r9 = r3
                java.util.ArrayDeque r9 = (java.util.ArrayDeque) r9
                r9.addLast(r2)
            L_0x0448:
                com.google.android.systemui.columbus.legacy.sensors.TapRT r2 = r1.tap
                long r3 = r0.timestamp
                java.util.Deque r0 = r2.mTimestampsBackTap
                r2 = r0
                java.util.ArrayDeque r2 = (java.util.ArrayDeque) r2
                java.util.Iterator r2 = r2.iterator()
            L_0x0455:
                boolean r5 = r2.hasNext()
                if (r5 == 0) goto L_0x0472
                java.lang.Object r5 = r2.next()
                java.lang.Long r5 = (java.lang.Long) r5
                long r5 = r5.longValue()
                long r5 = r3 - r5
                r7 = 500000000(0x1dcd6500, double:2.47032823E-315)
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r5 <= 0) goto L_0x0455
                r2.remove()
                goto L_0x0455
            L_0x0472:
                boolean r2 = r0.isEmpty()
                if (r2 == 0) goto L_0x047b
                r0 = 1
                r6 = 0
                goto L_0x04ac
            L_0x047b:
                r2 = r0
                java.util.ArrayDeque r2 = (java.util.ArrayDeque) r2
                java.util.Iterator r3 = r2.iterator()
            L_0x0482:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x04aa
                java.lang.Object r4 = r2.getLast()
                java.lang.Long r4 = (java.lang.Long) r4
                long r4 = r4.longValue()
                java.lang.Object r6 = r3.next()
                java.lang.Long r6 = (java.lang.Long) r6
                long r6 = r6.longValue()
                long r4 = r4 - r6
                r6 = 100000000(0x5f5e100, double:4.94065646E-316)
                int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r4 <= 0) goto L_0x0482
                r0.clear()
                r0 = 1
                r6 = 2
                goto L_0x04ac
            L_0x04aa:
                r0 = 1
                r6 = 1
            L_0x04ac:
                if (r6 == r0) goto L_0x04bd
                r2 = 2
                if (r6 == r2) goto L_0x04b2
                goto L_0x04c8
            L_0x04b2:
                android.os.Handler r2 = r1.handler
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1 r3 = new com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1
                r3.<init>(r1, r0)
                r2.post(r3)
                goto L_0x04c8
            L_0x04bd:
                android.os.Handler r0 = r1.handler
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1 r2 = new com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1
                r3 = 0
                r2.<init>(r1, r3)
                r0.post(r2)
            L_0x04c8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.GestureSensorEventListener.onSensorChanged(android.hardware.SensorEvent):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
            r5 = r4.this$0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void setListening(boolean r5) {
            /*
                r4 = this;
                r0 = 0
                if (r5 == 0) goto L_0x0029
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl r5 = com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.this
                android.hardware.Sensor r1 = r5.accelerometer
                if (r1 == 0) goto L_0x0029
                android.hardware.Sensor r2 = r5.gyroscope
                if (r2 == 0) goto L_0x0029
                android.hardware.SensorManager r2 = r5.sensorManager
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener r3 = r5.sensorEventListener
                android.os.Handler r5 = r5.handler
                r2.registerListener(r3, r1, r0, r5)
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl r5 = com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.this
                android.hardware.SensorManager r1 = r5.sensorManager
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener r2 = r5.sensorEventListener
                android.hardware.Sensor r3 = r5.gyroscope
                android.os.Handler r5 = r5.handler
                r1.registerListener(r2, r3, r0, r5)
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl r4 = com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.this
                r5 = 1
                r4.isListening = r5
                goto L_0x0036
            L_0x0029:
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl r5 = com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.this
                android.hardware.SensorManager r1 = r5.sensorManager
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener r5 = r5.sensorEventListener
                r1.unregisterListener(r5)
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl r4 = com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.this
                r4.isListening = r0
            L_0x0036:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl.GestureSensorEventListener.setListening(boolean):void");
        }

        public final void onAccuracyChanged(Sensor sensor, int i) {
        }
    }
}
