package com.google.android.systemui.autorotate;

import android.app.StatsManager;
import android.frameworks.stats.VendorAtomValue$1$$ExternalSyntheticOutline0;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.android.internal.util.FrameworkStatsLog;
import com.google.android.systemui.autorotate.proto.nano.AutorotateProto$DeviceRotatedSensorData;
import com.google.android.systemui.autorotate.proto.nano.AutorotateProto$DeviceRotatedSensorHeader;
import com.google.android.systemui.autorotate.proto.nano.AutorotateProto$DeviceRotatedSensorSample;
import com.google.protobuf.nano.MessageNano;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DataLogger {
    public Queue mDataQueue;
    public long mLastPullTimeNanos;
    public StatsManager mStatsManager;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class StatsPullAtomCallbackImpl implements StatsManager.StatsPullAtomCallback {
        public StatsPullAtomCallbackImpl() {
        }

        public final int onPullAtom(int i, List list) {
            int i2;
            int i3 = i;
            if (i3 == 10097) {
                DataLogger dataLogger = DataLogger.this;
                dataLogger.getClass();
                Log.d("Autorotate-DataLogger", "Received pull request from statsd.");
                dataLogger.mLastPullTimeNanos = SystemClock.elapsedRealtimeNanos();
                Pair pair = (Pair) ((LinkedList) dataLogger.mDataQueue).poll();
                SensorData[] sensorDataArr = (SensorData[]) pair.first;
                int intValue = ((Integer) pair.second).intValue();
                if (!(sensorDataArr == null || sensorDataArr.length == 0 || sensorDataArr[0] == null)) {
                    AutorotateProto$DeviceRotatedSensorData autorotateProto$DeviceRotatedSensorData = new AutorotateProto$DeviceRotatedSensorData();
                    AutorotateProto$DeviceRotatedSensorHeader autorotateProto$DeviceRotatedSensorHeader = new AutorotateProto$DeviceRotatedSensorHeader();
                    autorotateProto$DeviceRotatedSensorHeader.timestampBase = sensorDataArr[0].mTimestampMillis;
                    autorotateProto$DeviceRotatedSensorData.header = autorotateProto$DeviceRotatedSensorHeader;
                    AutorotateProto$DeviceRotatedSensorSample[] autorotateProto$DeviceRotatedSensorSampleArr = new AutorotateProto$DeviceRotatedSensorSample[sensorDataArr.length];
                    int i4 = 0;
                    while (true) {
                        i2 = 2;
                        if (i4 >= sensorDataArr.length) {
                            break;
                        }
                        AutorotateProto$DeviceRotatedSensorSample autorotateProto$DeviceRotatedSensorSample = new AutorotateProto$DeviceRotatedSensorSample();
                        SensorData sensorData = sensorDataArr[i4];
                        autorotateProto$DeviceRotatedSensorSample.timestampOffset = (int) (sensorData.mTimestampMillis - autorotateProto$DeviceRotatedSensorHeader.timestampBase);
                        int i5 = sensorData.mSensorIdentifier;
                        if (i5 != 4) {
                            i2 = i5;
                        }
                        autorotateProto$DeviceRotatedSensorSample.sensorType = i2;
                        autorotateProto$DeviceRotatedSensorSample.xValue = sensorData.mValueX;
                        autorotateProto$DeviceRotatedSensorSample.yValue = sensorData.mValueY;
                        autorotateProto$DeviceRotatedSensorSample.zValue = sensorData.mValueZ;
                        autorotateProto$DeviceRotatedSensorSampleArr[i4] = autorotateProto$DeviceRotatedSensorSample;
                        i4++;
                    }
                    autorotateProto$DeviceRotatedSensorData.sample = autorotateProto$DeviceRotatedSensorSampleArr;
                    byte[] byteArray = MessageNano.toByteArray(autorotateProto$DeviceRotatedSensorData);
                    int i6 = 1;
                    if (intValue != 0) {
                        if (intValue != 1) {
                            i6 = 3;
                            if (intValue != 2) {
                                if (intValue != 3) {
                                    i2 = 0;
                                } else {
                                    i2 = 4;
                                }
                            }
                        }
                        list.add(FrameworkStatsLog.buildStatsEvent(10097, byteArray, i2));
                    }
                    i2 = i6;
                    list.add(FrameworkStatsLog.buildStatsEvent(10097, byteArray, i2));
                }
                return 0;
            }
            throw new UnsupportedOperationException(VendorAtomValue$1$$ExternalSyntheticOutline0.m(i3, "Unknown tagId: "));
        }
    }
}
