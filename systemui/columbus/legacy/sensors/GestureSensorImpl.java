package com.google.android.systemui.columbus.legacy.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import com.android.internal.logging.UiEventLogger;
import com.google.android.systemui.columbus.ColumbusEvent;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class GestureSensorEventListener implements SensorEventListener {
        public GestureSensorEventListener() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:105:0x04ca  */
        /* JADX WARNING: Removed duplicated region for block: B:108:0x04da  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x009d  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0120  */
        /* JADX WARNING: Removed duplicated region for block: B:91:0x0477  */
        /* JADX WARNING: Removed duplicated region for block: B:96:0x0494  */
        /* JADX WARNING: Removed duplicated region for block: B:97:0x0497  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onSensorChanged(android.hardware.SensorEvent r33) {
            /*
                r32 = this;
                r0 = r33
                if (r0 == 0) goto L_0x04e5
                r1 = r32
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
                java.util.Deque r8 = r2.mAccZs
                r15 = 4
                r16 = 0
                if (r3 != r13) goto L_0x0056
                r2.mGotAcc = r13
                r18 = r14
                long r13 = r2.mSyncTime
                int r13 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1))
                if (r13 != 0) goto L_0x004c
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r13 = r2.mResampleAcc
                r13.mRawLastX = r12
                r13.mRawLastT = r10
                r13.mResampledThisX = r12
                r13.mResampledLastT = r10
                r13.mInterval = r6
                r14 = r18
                r13.mRawLastY = r14
                r13.mRawLastZ = r4
                r13.mResampledThisY = r14
                r13.mResampledThisZ = r4
                goto L_0x004e
            L_0x004c:
                r14 = r18
            L_0x004e:
                boolean r6 = r2.mGotGyro
                if (r6 != 0) goto L_0x0081
                r0 = r1
                r4 = r5
                goto L_0x0462
            L_0x0056:
                if (r3 != r15) goto L_0x0081
                r13 = 1
                r2.mGotGyro = r13
                r18 = r6
                long r5 = r2.mSyncTime
                int r5 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
                if (r5 != 0) goto L_0x0079
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r5 = r2.mResampleGyro
                r5.mRawLastX = r12
                r5.mRawLastT = r10
                r5.mResampledThisX = r12
                r5.mResampledLastT = r10
                r6 = r18
                r5.mInterval = r6
                r5.mRawLastY = r14
                r5.mRawLastZ = r4
                r5.mResampledThisY = r14
                r5.mResampledThisZ = r4
            L_0x0079:
                boolean r5 = r2.mGotAcc
                if (r5 != 0) goto L_0x0081
                r0 = r1
            L_0x007e:
                r4 = 0
                goto L_0x0462
            L_0x0081:
                long r5 = r2.mSyncTime
                int r5 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
                com.google.android.systemui.columbus.legacy.sensors.Highpass3C r7 = r2.mHighpassAcc
                com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r6 = r2.mLowpassAcc
                com.google.android.systemui.columbus.legacy.sensors.Slope3C r13 = r2.mSlopeAcc
                com.google.android.systemui.columbus.legacy.sensors.Highpass3C r15 = r2.mHighpassGyro
                com.google.android.systemui.columbus.legacy.sensors.Lowpass3C r0 = r2.mLowpassGyro
                r18 = r1
                com.google.android.systemui.columbus.legacy.sensors.Slope3C r1 = r2.mSlopeGyro
                r19 = r9
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r9 = r2.mResampleGyro
                r20 = r4
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r4 = r2.mResampleAcc
                if (r5 != 0) goto L_0x0120
                r2.mSyncTime = r10
                r4.mResampledLastT = r10
                r9.mResampledLastT = r10
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r2 = r4.getResults()
                com.google.android.systemui.columbus.legacy.sensors.Point3f r2 = r2.mPoint
                r13.getClass()
                float r3 = r2.x
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r4 = r13.mSlopeX
                r4.mRawLastX = r3
                float r3 = r2.y
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r4 = r13.mSlopeY
                r4.mRawLastX = r3
                float r2 = r2.z
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r3 = r13.mSlopeZ
                r3.mRawLastX = r2
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r2 = r9.getResults()
                com.google.android.systemui.columbus.legacy.sensors.Point3f r2 = r2.mPoint
                r1.getClass()
                float r3 = r2.x
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r4 = r1.mSlopeX
                r4.mRawLastX = r3
                float r3 = r2.y
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r4 = r1.mSlopeY
                r4.mRawLastX = r3
                float r2 = r2.z
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r1 = r1.mSlopeZ
                r1.mRawLastX = r2
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r1 = r6.mLowpassX
                r2 = 0
                r1.mLastX = r2
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r1 = r6.mLowpassY
                r1.mLastX = r2
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r1 = r6.mLowpassZ
                r1.mLastX = r2
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r1 = r0.mLowpassX
                r1.mLastX = r2
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r1 = r0.mLowpassY
                r1.mLastX = r2
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r0 = r0.mLowpassZ
                r0.mLastX = r2
                r7.getClass()
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r0 = r7.mHighpassX
                r0.mLastX = r2
                r0.mLastY = r2
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r0 = r7.mHighpassY
                r0.mLastX = r2
                r0.mLastY = r2
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r0 = r7.mHighpassZ
                r0.mLastX = r2
                r0.mLastY = r2
                r15.getClass()
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r0 = r15.mHighpassX
                r0.mLastX = r2
                r0.mLastY = r2
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r0 = r15.mHighpassY
                r0.mLastX = r2
                r0.mLastY = r2
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r0 = r15.mHighpassZ
                r0.mLastX = r2
                r0.mLastY = r2
                r0 = r18
                goto L_0x007e
            L_0x0120:
                java.util.Deque r5 = r2.mAccYs
                r21 = r15
                java.util.Deque r15 = r2.mAccXs
                r23 = r0
                r22 = r1
                long r0 = r2.mSizeWindowNs
                r24 = r0
                com.google.android.systemui.columbus.legacy.sensors.PeakDetector r0 = r2.mValleyDetector
                com.google.android.systemui.columbus.legacy.sensors.PeakDetector r1 = r2.mPeakDetector
                r26 = 1242725376(0x4a127c00, float:2400000.0)
                r27 = r6
                r6 = 1
                if (r3 != r6) goto L_0x0212
            L_0x013a:
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r6 = r2.mResampleAcc
                r3 = r27
                r9 = r7
                r7 = r12
                r27 = r12
                r12 = r8
                r8 = r14
                r17 = r9
                r9 = r20
                r28 = r10
                boolean r6 = r6.update(r7, r8, r9, r10)
                if (r6 == 0) goto L_0x020f
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r6 = r4.getResults()
                long r7 = r4.mInterval
                float r7 = (float) r7
                float r7 = r26 / r7
                com.google.android.systemui.columbus.legacy.sensors.Point3f r6 = r6.mPoint
                r13.getClass()
                float r8 = r6.x
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r9 = r13.mSlopeX
                float r8 = r8 * r7
                float r10 = r9.mRawLastX
                float r10 = r8 - r10
                r9.mRawLastX = r8
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r8 = r13.mSlopeY
                float r9 = r6.y
                float r9 = r9 * r7
                float r11 = r8.mRawLastX
                float r11 = r9 - r11
                r8.mRawLastX = r9
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r8 = r13.mSlopeZ
                float r6 = r6.z
                float r6 = r6 * r7
                float r7 = r8.mRawLastX
                float r7 = r6 - r7
                r8.mRawLastX = r6
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r6 = r3.mLowpassX
                float r6 = r6.update(r10)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r8 = r3.mLowpassY
                float r8 = r8.update(r11)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r9 = r3.mLowpassZ
                float r7 = r9.update(r7)
                r17.getClass()
                r9 = r17
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r10 = r9.mHighpassX
                float r6 = r10.update(r6)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r10 = r9.mHighpassY
                float r8 = r10.update(r8)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r10 = r9.mHighpassZ
                float r7 = r10.update(r7)
                java.lang.Float r6 = java.lang.Float.valueOf(r6)
                r15.add(r6)
                java.lang.Float r6 = java.lang.Float.valueOf(r8)
                r5.add(r6)
                java.lang.Float r6 = java.lang.Float.valueOf(r7)
                r12.add(r6)
                long r6 = r4.mInterval
                long r6 = r24 / r6
                int r6 = (int) r6
            L_0x01c2:
                r7 = r15
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                int r8 = r7.size()
                if (r8 <= r6) goto L_0x01db
                r7.removeFirst()
                r7 = r5
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                r7.removeFirst()
                r8 = r12
                java.util.ArrayDeque r8 = (java.util.ArrayDeque) r8
                r8.removeFirst()
                goto L_0x01c2
            L_0x01db:
                r8 = r12
                java.util.ArrayDeque r8 = (java.util.ArrayDeque) r8
                java.lang.Object r6 = r8.getLast()
                java.lang.Float r6 = (java.lang.Float) r6
                float r6 = r6.floatValue()
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r7 = r4.getResults()
                long r10 = r7.mT
                r1.update(r6, r10)
                java.lang.Object r6 = r8.getLast()
                java.lang.Float r6 = (java.lang.Float) r6
                float r6 = r6.floatValue()
                float r6 = -r6
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r7 = r4.getResults()
                long r7 = r7.mT
                r0.update(r6, r7)
                r7 = r9
                r8 = r12
                r12 = r27
                r10 = r28
                r27 = r3
                goto L_0x013a
            L_0x020f:
                r4 = 0
                goto L_0x0460
            L_0x0212:
                r28 = r10
                r27 = r12
                r6 = 4
                r12 = r8
                if (r3 != r6) goto L_0x020f
            L_0x021a:
                com.google.android.systemui.columbus.legacy.sensors.Resample3C r6 = r2.mResampleGyro
                r7 = r27
                r8 = r14
                r13 = r9
                r3 = r19
                r9 = r20
                r10 = r28
                boolean r6 = r6.update(r7, r8, r9, r10)
                if (r6 == 0) goto L_0x044d
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r6 = r13.getResults()
                long r7 = r13.mInterval
                float r7 = (float) r7
                float r7 = r26 / r7
                com.google.android.systemui.columbus.legacy.sensors.Point3f r6 = r6.mPoint
                r22.getClass()
                float r8 = r6.x
                r9 = r22
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r10 = r9.mSlopeX
                float r8 = r8 * r7
                float r11 = r10.mRawLastX
                float r11 = r8 - r11
                r10.mRawLastX = r8
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r8 = r9.mSlopeY
                float r10 = r6.y
                float r10 = r10 * r7
                r19 = r14
                float r14 = r8.mRawLastX
                float r14 = r10 - r14
                r8.mRawLastX = r10
                com.google.android.systemui.columbus.legacy.sensors.Slope1C r8 = r9.mSlopeZ
                float r6 = r6.z
                float r6 = r6 * r7
                float r7 = r8.mRawLastX
                float r7 = r6 - r7
                r8.mRawLastX = r6
                r6 = r23
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r8 = r6.mLowpassX
                float r8 = r8.update(r11)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r10 = r6.mLowpassY
                float r10 = r10.update(r14)
                com.google.android.systemui.columbus.legacy.sensors.Lowpass1C r11 = r6.mLowpassZ
                float r7 = r11.update(r7)
                r21.getClass()
                r11 = r21
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r14 = r11.mHighpassX
                float r8 = r14.update(r8)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r14 = r11.mHighpassY
                float r10 = r14.update(r10)
                com.google.android.systemui.columbus.legacy.sensors.Highpass1C r14 = r11.mHighpassZ
                float r7 = r14.update(r7)
                java.util.Deque r14 = r2.mGyroXs
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r14.add(r8)
                java.util.Deque r8 = r2.mGyroYs
                java.lang.Float r10 = java.lang.Float.valueOf(r10)
                r8.add(r10)
                java.util.Deque r10 = r2.mGyroZs
                java.lang.Float r7 = java.lang.Float.valueOf(r7)
                r10.add(r7)
                r23 = r6
                long r6 = r13.mInterval
                long r6 = r24 / r6
                int r6 = (int) r6
            L_0x02ac:
                r7 = r14
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                r22 = r9
                int r9 = r7.size()
                if (r9 <= r6) goto L_0x02c9
                r7.removeFirst()
                r7 = r8
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                r7.removeFirst()
                r7 = r10
                java.util.ArrayDeque r7 = (java.util.ArrayDeque) r7
                r7.removeFirst()
                r9 = r22
                goto L_0x02ac
            L_0x02c9:
                long r6 = r4.mInterval
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r9 = r4.getResults()
                r21 = r4
                com.google.android.systemui.columbus.legacy.sensors.Sample3C r4 = r13.getResults()
                r30 = r10
                long r9 = r9.mT
                r31 = r3
                long r3 = r4.mT
                long r9 = r9 - r3
                long r9 = r9 / r6
                int r3 = (int) r9
                int r4 = r1.mPeakId
                r6 = 0
                int r4 = java.lang.Math.max(r6, r4)
                int r7 = r0.mPeakId
                int r7 = java.lang.Math.max(r6, r7)
                r6 = r13
                float r9 = r1.mAmplitude
                float r10 = r0.mAmplitude
                int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
                if (r9 <= 0) goto L_0x02f7
                goto L_0x02f8
            L_0x02f7:
                r4 = r7
            L_0x02f8:
                r7 = 12
                if (r4 <= r7) goto L_0x02ff
                r9 = 1
                r2.mWasPeakApproaching = r9
            L_0x02ff:
                int r9 = r4 + -6
                int r3 = r9 - r3
                r10 = r12
                java.util.ArrayDeque r10 = (java.util.ArrayDeque) r10
                int r10 = r10.size()
                if (r9 < 0) goto L_0x0440
                if (r3 < 0) goto L_0x0440
                int r13 = r2.mSizeFeatureWindow
                int r7 = r9 + r13
                if (r7 > r10) goto L_0x0440
                int r7 = r3 + r13
                if (r7 <= r10) goto L_0x031a
                goto L_0x0440
            L_0x031a:
                boolean r7 = r2.mWasPeakApproaching
                if (r7 == 0) goto L_0x0440
                r7 = 12
                if (r4 > r7) goto L_0x0440
                r4 = 0
                r2.mWasPeakApproaching = r4
                r2.addToFeatureVector(r15, r9, r4)
                r2.addToFeatureVector(r5, r9, r13)
                int r7 = r13 * 2
                r2.addToFeatureVector(r12, r9, r7)
                int r7 = r13 * 3
                r2.addToFeatureVector(r14, r3, r7)
                int r7 = r13 * 4
                r2.addToFeatureVector(r8, r3, r7)
                int r13 = r13 * 5
                r7 = r30
                r2.addToFeatureVector(r7, r3, r13)
                java.util.ArrayList r3 = new java.util.ArrayList
                java.util.ArrayList r7 = r2.mFeatureVector
                r8 = 100
                r9 = 150(0x96, float:2.1E-43)
                java.util.List r7 = r7.subList(r8, r9)
                r3.<init>(r7)
                java.util.ArrayList r3 = r2.mFeatureVector
                int r7 = r3.size()
                r8 = 2
                int r7 = r7 / r8
            L_0x0358:
                int r8 = r3.size()
                if (r7 >= r8) goto L_0x0375
                java.lang.Object r8 = r3.get(r7)
                java.lang.Float r8 = (java.lang.Float) r8
                float r8 = r8.floatValue()
                r9 = 1092616192(0x41200000, float:10.0)
                float r8 = r8 * r9
                java.lang.Float r8 = java.lang.Float.valueOf(r8)
                r3.set(r7, r8)
                int r7 = r7 + 1
                goto L_0x0358
            L_0x0375:
                r2.mFeatureVector = r3
                com.google.android.systemui.columbus.legacy.sensors.TfClassifier r7 = r2.mClassifier
                org.tensorflow.lite.Interpreter r7 = r7.mInterpreter
                if (r7 != 0) goto L_0x0386
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>()
                r4 = 0
                r9 = 4
                goto L_0x040a
            L_0x0386:
                int r8 = r3.size()
                r9 = 4
                int[] r10 = new int[r9]
                r13 = 3
                r14 = 1
                r10[r13] = r14
                r13 = 2
                r10[r13] = r14
                r10[r14] = r8
                r4 = 0
                r10[r4] = r14
                java.lang.Class r8 = java.lang.Float.TYPE
                java.lang.Object r8 = java.lang.reflect.Array.newInstance(r8, r10)
                float[][][][] r8 = (float[][][][]) r8
                r10 = r4
            L_0x03a2:
                int r13 = r3.size()
                if (r10 >= r13) goto L_0x03bd
                java.lang.Object r13 = r3.get(r10)
                java.lang.Float r13 = (java.lang.Float) r13
                float r13 = r13.floatValue()
                r14 = r8[r4]
                r14 = r14[r10]
                r14 = r14[r4]
                r14[r4] = r13
                int r10 = r10 + 1
                goto L_0x03a2
            L_0x03bd:
                java.lang.Object[] r3 = new java.lang.Object[]{r8}
                java.util.HashMap r8 = new java.util.HashMap
                r8.<init>()
                r10 = 2
                int[] r13 = new int[r10]
                r10 = 7
                r14 = 1
                r13[r14] = r10
                r13[r4] = r14
                java.lang.Class r14 = java.lang.Float.TYPE
                java.lang.Object r13 = java.lang.reflect.Array.newInstance(r14, r13)
                float[][] r13 = (float[][]) r13
                java.lang.Integer r14 = java.lang.Integer.valueOf(r4)
                r8.put(r14, r13)
                r7.runForMultipleInputsOutputs(r3, r8)
                java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
                java.lang.Object r3 = r8.get(r3)
                float[][] r3 = (float[][]) r3
                java.util.ArrayList r7 = new java.util.ArrayList
                r7.<init>()
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                r13 = r4
            L_0x03f6:
                if (r13 >= r10) goto L_0x0406
                r14 = r3[r4]
                r14 = r14[r13]
                java.lang.Float r14 = java.lang.Float.valueOf(r14)
                r8.add(r14)
                int r13 = r13 + 1
                goto L_0x03f6
            L_0x0406:
                r7.add(r8)
                r3 = r7
            L_0x040a:
                boolean r7 = r3.isEmpty()
                if (r7 != 0) goto L_0x0442
                java.lang.Object r3 = r3.get(r4)
                java.util.ArrayList r3 = (java.util.ArrayList) r3
                r7 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
                r8 = r4
                r10 = r8
            L_0x041b:
                int r13 = r3.size()
                if (r8 >= r13) goto L_0x043d
                java.lang.Object r13 = r3.get(r8)
                java.lang.Float r13 = (java.lang.Float) r13
                float r13 = r13.floatValue()
                int r13 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
                if (r13 >= 0) goto L_0x043a
                java.lang.Object r7 = r3.get(r8)
                java.lang.Float r7 = (java.lang.Float) r7
                float r7 = r7.floatValue()
                r10 = r8
            L_0x043a:
                int r8 = r8 + 1
                goto L_0x041b
            L_0x043d:
                r2.mResult = r10
                goto L_0x0442
            L_0x0440:
                r4 = 0
                r9 = 4
            L_0x0442:
                r9 = r6
                r14 = r19
                r4 = r21
                r19 = r31
                r21 = r11
                goto L_0x021a
            L_0x044d:
                r31 = r3
                r4 = 0
                int r0 = r2.mResult
                r1 = 1
                if (r0 != r1) goto L_0x0460
                java.lang.Long r0 = java.lang.Long.valueOf(r28)
                r9 = r31
                java.util.ArrayDeque r9 = (java.util.ArrayDeque) r9
                r9.addLast(r0)
            L_0x0460:
                r0 = r18
            L_0x0462:
                com.google.android.systemui.columbus.legacy.sensors.TapRT r1 = r0.tap
                r2 = r33
                long r2 = r2.timestamp
                java.util.Deque r1 = r1.mTimestampsBackTap
                r5 = r1
                java.util.ArrayDeque r5 = (java.util.ArrayDeque) r5
                java.util.Iterator r5 = r5.iterator()
            L_0x0471:
                boolean r6 = r5.hasNext()
                if (r6 == 0) goto L_0x048e
                java.lang.Object r6 = r5.next()
                java.lang.Long r6 = (java.lang.Long) r6
                long r6 = r6.longValue()
                long r6 = r2 - r6
                r8 = 500000000(0x1dcd6500, double:2.47032823E-315)
                int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r6 <= 0) goto L_0x0471
                r5.remove()
                goto L_0x0471
            L_0x048e:
                boolean r2 = r1.isEmpty()
                if (r2 == 0) goto L_0x0497
                r5 = r4
                r1 = 1
                goto L_0x04c8
            L_0x0497:
                r2 = r1
                java.util.ArrayDeque r2 = (java.util.ArrayDeque) r2
                java.util.Iterator r3 = r2.iterator()
            L_0x049e:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x04c6
                java.lang.Object r4 = r2.getLast()
                java.lang.Long r4 = (java.lang.Long) r4
                long r4 = r4.longValue()
                java.lang.Object r6 = r3.next()
                java.lang.Long r6 = (java.lang.Long) r6
                long r6 = r6.longValue()
                long r4 = r4 - r6
                r6 = 100000000(0x5f5e100, double:4.94065646E-316)
                int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r4 <= 0) goto L_0x049e
                r1.clear()
                r1 = 1
                r5 = 2
                goto L_0x04c8
            L_0x04c6:
                r1 = 1
                r5 = 1
            L_0x04c8:
                if (r5 == r1) goto L_0x04da
                r1 = 2
                if (r5 == r1) goto L_0x04ce
                goto L_0x04e5
            L_0x04ce:
                android.os.Handler r1 = r0.handler
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1 r2 = new com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1
                r3 = 1
                r2.<init>(r0, r3)
                r1.post(r2)
                goto L_0x04e5
            L_0x04da:
                android.os.Handler r1 = r0.handler
                com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1 r2 = new com.google.android.systemui.columbus.legacy.sensors.GestureSensorImpl$GestureSensorEventListener$onSensorChanged$1$1
                r3 = 0
                r2.<init>(r0, r3)
                r1.post(r2)
            L_0x04e5:
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
