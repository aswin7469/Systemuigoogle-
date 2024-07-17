package com.google.android.systemui.autorotate;

import android.app.StatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.DeviceConfig;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.ConcurrentUtils;
import com.android.internal.util.FrameworkStatsLog;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.google.android.systemui.autorotate.DataLogger;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AutorotateDataService {
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final Context mContext;
    public Sensor mDebugSensor;
    public final DeviceConfigProxy mDeviceConfig;
    public int mLastPreIndication = -1;
    public LatencyTracker mLatencyTracker;
    public final DelayableExecutor mMainExecutor;
    public Sensor mPreindicationSensor;
    public boolean mRawSensorLoggingEnabled;
    public boolean mRotationPreindicationEnabled;
    public final AnonymousClass1 mScreenEventBroadcastReceiver = new BroadcastReceiver() {
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                AutorotateDataService.this.registerRequiredSensors();
            } else if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                AutorotateDataService autorotateDataService = AutorotateDataService.this;
                autorotateDataService.mSensorManager.unregisterListener(autorotateDataService.mSensorListener);
                autorotateDataService.mSensorDataLogger.mDataQueue.clear();
                AutorotateDataService.this.mLastPreIndication = -1;
            }
        }
    };
    public SensorData[] mSensorData = new SensorData[600];
    public int mSensorDataIndex = 0;
    public final DataLogger mSensorDataLogger;
    public final SensorListener mSensorListener;
    public final SensorManager mSensorManager;
    public boolean mServiceRunning = false;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SensorDataReadyRunnable implements Runnable {
        public final int mRotation;
        public final long mRotationTimestampMillis;

        public SensorDataReadyRunnable(int i, long j) {
            this.mRotation = i;
            this.mRotationTimestampMillis = j;
        }

        public final void run() {
            AutorotateDataService autorotateDataService = AutorotateDataService.this;
            DataLogger dataLogger = autorotateDataService.mSensorDataLogger;
            SensorData[] sensorDataArr = autorotateDataService.mSensorData;
            int i = this.mRotation;
            dataLogger.getClass();
            int i2 = 0;
            if (!(sensorDataArr == null || sensorDataArr.length == 0 || sensorDataArr[0] == null)) {
                if (SystemClock.elapsedRealtimeNanos() - dataLogger.mLastPullTimeNanos > 5000000000L) {
                    dataLogger.mDataQueue.clear();
                }
                dataLogger.mDataQueue.add(Pair.create(sensorDataArr, Integer.valueOf(i)));
            }
            DataLogger dataLogger2 = AutorotateDataService.this.mSensorDataLogger;
            long j = this.mRotationTimestampMillis;
            int i3 = this.mRotation;
            dataLogger2.getClass();
            if (i3 == 0) {
                i2 = 1;
            } else if (i3 == 1) {
                i2 = 2;
            } else if (i3 == 2) {
                i2 = 3;
            } else if (i3 == 3) {
                i2 = 4;
            }
            FrameworkStatsLog.write(333, j, i2, 3);
        }
    }

    public AutorotateDataService(Context context, SensorManager sensorManager, DataLogger dataLogger, BroadcastDispatcher broadcastDispatcher, DeviceConfigProxy deviceConfigProxy, DelayableExecutor delayableExecutor) {
        this.mContext = context;
        this.mMainExecutor = delayableExecutor;
        this.mSensorDataLogger = dataLogger;
        this.mSensorManager = sensorManager;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mDeviceConfig = deviceConfigProxy;
        this.mSensorListener = new SensorListener();
    }

    public final void readFlagsToControlSensorLogging() {
        Sensor sensor;
        Sensor sensor2;
        this.mRawSensorLoggingEnabled = DeviceConfig.getBoolean("window_manager", "log_raw_sensor_data", false);
        boolean z = DeviceConfig.getBoolean("window_manager", "log_rotation_preindication", false);
        this.mRotationPreindicationEnabled = z;
        boolean z2 = this.mRawSensorLoggingEnabled;
        SensorListener sensorListener = this.mSensorListener;
        SensorManager sensorManager = this.mSensorManager;
        BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
        AnonymousClass1 r6 = this.mScreenEventBroadcastReceiver;
        DataLogger dataLogger = this.mSensorDataLogger;
        if (z2 || z) {
            if (z2 || z) {
                if (!this.mServiceRunning) {
                    StatsManager statsManager = dataLogger.mStatsManager;
                    if (statsManager != null) {
                        statsManager.setPullAtomCallback(10097, (StatsManager.PullAtomMetadata) null, ConcurrentUtils.DIRECT_EXECUTOR, new DataLogger.StatsPullAtomCallbackImpl());
                        Log.d("Autorotate-DataLogger", "Registered to statsd for pull");
                    }
                    IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    broadcastDispatcher.getClass();
                    BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, r6, intentFilter, (Executor) null, (UserHandle) null, 0, (String) null, 56);
                    this.mServiceRunning = true;
                }
                registerRequiredSensors();
            }
            if (!this.mRawSensorLoggingEnabled && (sensor2 = this.mDebugSensor) != null) {
                sensorManager.unregisterListener(sensorListener, sensor2);
            }
            if (!this.mRotationPreindicationEnabled && (sensor = this.mPreindicationSensor) != null) {
                sensorManager.unregisterListener(sensorListener, sensor);
                return;
            }
            return;
        }
        if (this.mServiceRunning) {
            StatsManager statsManager2 = dataLogger.mStatsManager;
            if (statsManager2 != null) {
                statsManager2.clearPullAtomCallback(10097);
            }
            broadcastDispatcher.unregisterReceiver(r6);
            sensorManager.unregisterListener(sensorListener);
            dataLogger.mDataQueue.clear();
            this.mServiceRunning = false;
        }
        sensorManager.unregisterListener(sensorListener);
        dataLogger.mDataQueue.clear();
    }

    public final void registerRequiredSensors() {
        SensorManager sensorManager = this.mSensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(27);
        SensorListener sensorListener = this.mSensorListener;
        sensorManager.registerListener(sensorListener, defaultSensor, 1);
        if (this.mRawSensorLoggingEnabled) {
            Sensor defaultSensor2 = sensorManager.getDefaultSensor(65548);
            this.mDebugSensor = defaultSensor2;
            sensorManager.registerListener(sensorListener, defaultSensor2, 1);
        }
        if (this.mRotationPreindicationEnabled) {
            Sensor defaultSensor3 = sensorManager.getDefaultSensor(65553);
            this.mPreindicationSensor = defaultSensor3;
            sensorManager.registerListener(sensorListener, defaultSensor3, 1);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SensorListener implements SensorEventListener {
        public SensorListener() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.google.android.systemui.autorotate.SensorData[]} */
        /* JADX WARNING: type inference failed for: r2v3, types: [com.google.android.systemui.autorotate.SensorData, java.lang.Object] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onSensorChanged(android.hardware.SensorEvent r14) {
            /*
                r13 = this;
                android.hardware.Sensor r0 = r14.sensor
                int r0 = r0.getType()
                r1 = 27
                r2 = 10
                r3 = 600(0x258, float:8.41E-43)
                r4 = 3
                r5 = 1000000(0xf4240, double:4.940656E-318)
                r7 = 0
                if (r0 != r1) goto L_0x004a
                float[] r0 = r14.values
                r0 = r0[r7]
                int r0 = (int) r0
                if (r0 < 0) goto L_0x0049
                if (r0 <= r4) goto L_0x001d
                goto L_0x0049
            L_0x001d:
                com.google.android.systemui.autorotate.AutorotateDataService r1 = com.google.android.systemui.autorotate.AutorotateDataService.this
                com.google.android.systemui.autorotate.SensorData[] r3 = new com.google.android.systemui.autorotate.SensorData[r3]
                r1.mSensorData = r3
                r1.mSensorDataIndex = r7
                com.google.android.systemui.autorotate.AutorotateDataService$SensorDataReadyRunnable r3 = new com.google.android.systemui.autorotate.AutorotateDataService$SensorDataReadyRunnable
                long r7 = r14.timestamp
                long r7 = r7 / r5
                r3.<init>(r0, r7)
                java.util.concurrent.TimeUnit r14 = java.util.concurrent.TimeUnit.MILLISECONDS
                com.android.systemui.util.concurrency.DelayableExecutor r1 = r1.mMainExecutor
                com.android.systemui.util.concurrency.ExecutorImpl r1 = (com.android.systemui.util.concurrency.ExecutorImpl) r1
                r4 = 2300(0x8fc, double:1.1364E-320)
                r1.executeDelayed(r3, r4, r14)
                com.google.android.systemui.autorotate.AutorotateDataService r13 = com.google.android.systemui.autorotate.AutorotateDataService.this
                android.hardware.Sensor r14 = r13.mPreindicationSensor
                if (r14 == 0) goto L_0x00c0
                int r14 = r13.mLastPreIndication
                if (r0 != r14) goto L_0x00c0
                com.android.internal.util.LatencyTracker r13 = r13.mLatencyTracker
                r13.onActionEnd(r2)
                goto L_0x00c0
            L_0x0049:
                return
            L_0x004a:
                android.hardware.Sensor r0 = r14.sensor
                int r0 = r0.getType()
                r1 = 65548(0x1000c, float:9.1852E-41)
                r8 = 2
                r9 = 1
                if (r0 != r1) goto L_0x0085
                com.google.android.systemui.autorotate.AutorotateDataService r13 = com.google.android.systemui.autorotate.AutorotateDataService.this
                int r0 = r13.mSensorDataIndex
                if (r0 >= r3) goto L_0x0082
                com.google.android.systemui.autorotate.SensorData[] r1 = r13.mSensorData
                com.google.android.systemui.autorotate.SensorData r2 = new com.google.android.systemui.autorotate.SensorData
                float[] r3 = r14.values
                r7 = r3[r7]
                r10 = r3[r9]
                r8 = r3[r8]
                r3 = r3[r4]
                int r3 = (int) r3
                long r11 = r14.timestamp
                long r11 = r11 / r5
                r2.<init>()
                r2.mValueX = r7
                r2.mValueY = r10
                r2.mValueZ = r8
                r2.mSensorIdentifier = r3
                r2.mTimestampMillis = r11
                r1[r0] = r2
                int r0 = r0 + r9
                r13.mSensorDataIndex = r0
                goto L_0x00c0
            L_0x0082:
                r13.mSensorDataIndex = r7
                goto L_0x00c0
            L_0x0085:
                android.hardware.Sensor r0 = r14.sensor
                int r0 = r0.getType()
                r1 = 65553(0x10011, float:9.186E-41)
                if (r0 != r1) goto L_0x00c0
                com.google.android.systemui.autorotate.AutorotateDataService r0 = com.google.android.systemui.autorotate.AutorotateDataService.this
                android.hardware.Sensor r1 = r0.mPreindicationSensor
                if (r1 == 0) goto L_0x00c0
                float[] r1 = r14.values
                r1 = r1[r7]
                int r1 = (int) r1
                com.android.internal.util.LatencyTracker r0 = r0.mLatencyTracker
                r0.onActionStart(r2)
                com.google.android.systemui.autorotate.AutorotateDataService r13 = com.google.android.systemui.autorotate.AutorotateDataService.this
                r13.mLastPreIndication = r1
                long r2 = r14.timestamp
                long r2 = r2 / r5
                com.google.android.systemui.autorotate.DataLogger r13 = r13.mSensorDataLogger
                r13.getClass()
                if (r1 == 0) goto L_0x00ba
                if (r1 == r9) goto L_0x00b8
                if (r1 == r8) goto L_0x00bb
                if (r1 == r4) goto L_0x00b6
                r4 = r7
                goto L_0x00bb
            L_0x00b6:
                r4 = 4
                goto L_0x00bb
            L_0x00b8:
                r4 = r8
                goto L_0x00bb
            L_0x00ba:
                r4 = r9
            L_0x00bb:
                r13 = 333(0x14d, float:4.67E-43)
                com.android.internal.util.FrameworkStatsLog.write(r13, r2, r4, r9)
            L_0x00c0:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.autorotate.AutorotateDataService.SensorListener.onSensorChanged(android.hardware.SensorEvent):void");
        }

        public final void onAccuracyChanged(Sensor sensor, int i) {
        }
    }
}
