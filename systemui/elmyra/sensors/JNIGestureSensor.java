package com.google.android.systemui.elmyra.sensors;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import com.google.android.systemui.elmyra.sensors.config.GestureConfiguration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class JNIGestureSensor implements GestureSensor {
    private static final String DISABLE_SETTING = "com.google.android.systemui.elmyra.disable_jni";
    private static final int SENSOR_RATE = 20000;
    private static final String TAG = "Elmyra/JNIGestureSensor";
    private static boolean sLibraryLoaded;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final AssistGestureController mController;
    private final GestureConfiguration mGestureConfiguration;
    private boolean mIsListening;
    private final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    private long mNativeService;
    private int mSensorCount;
    private final String mSensorStringType;

    static {
        try {
            System.loadLibrary("elmyra");
            sLibraryLoaded = true;
        } catch (Throwable th) {
            Log.w(TAG, "Could not load JNI component: " + th);
            sLibraryLoaded = false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0084 A[Catch:{ Exception -> 0x009c }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009e A[Catch:{ Exception -> 0x009c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JNIGestureSensor(android.content.Context r9, com.google.android.systemui.elmyra.sensors.config.GestureConfiguration r10, com.android.keyguard.KeyguardUpdateMonitor r11) {
        /*
            r8 = this;
            java.lang.String r0 = "Elmyra/JNIGestureSensor"
            java.lang.String r1 = "touch_2_sensitivity"
            r8.<init>()
            com.google.android.systemui.elmyra.sensors.JNIGestureSensor$1 r2 = new com.google.android.systemui.elmyra.sensors.JNIGestureSensor$1
            r2.<init>()
            r8.mKeyguardUpdateMonitorCallback = r2
            r8.mContext = r9
            com.google.android.systemui.elmyra.sensors.AssistGestureController r3 = new com.google.android.systemui.elmyra.sensors.AssistGestureController
            r4 = 0
            r3.<init>(r9, r8, r10, r4)
            r8.mController = r3
            android.content.res.Resources r3 = r9.getResources()
            r5 = 2131952506(0x7f13037a, float:1.9541457E38)
            java.lang.String r3 = r3.getString(r5)
            r8.mSensorStringType = r3
            r8.mGestureConfiguration = r10
            com.google.android.systemui.elmyra.sensors.JNIGestureSensor$$ExternalSyntheticLambda0 r3 = new com.google.android.systemui.elmyra.sensors.JNIGestureSensor$$ExternalSyntheticLambda0
            r3.<init>(r8)
            r10.mListener = r3
            r11.registerCallback(r2)
            byte[] r9 = getChassisAsset(r9)
            if (r9 == 0) goto L_0x00c0
            int r10 = r9.length
            if (r10 == 0) goto L_0x00c0
            r10 = 0
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis r11 = new com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis     // Catch:{ Exception -> 0x009c }
            r11.<init>()     // Catch:{ Exception -> 0x009c }
            com.google.protobuf.nano.MessageNano.mergeFrom(r11, r9)     // Catch:{ Exception -> 0x009c }
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Sensor[] r2 = r11.sensors     // Catch:{ Exception -> 0x009c }
            int r2 = r2.length     // Catch:{ Exception -> 0x009c }
            r8.mSensorCount = r2     // Catch:{ Exception -> 0x009c }
            r2 = r10
        L_0x0049:
            int r3 = r8.mSensorCount     // Catch:{ Exception -> 0x009c }
            if (r2 >= r3) goto L_0x00b5
            java.lang.String r3 = "Elmyra/SensorCalibration"
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x006a, SecurityException -> 0x0068 }
            java.lang.String r6 = "/persist/sensors/elmyra/calibration.%d"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)     // Catch:{ FileNotFoundException -> 0x006a, SecurityException -> 0x0068 }
            java.lang.Object[] r7 = new java.lang.Object[]{r7}     // Catch:{ FileNotFoundException -> 0x006a, SecurityException -> 0x0068 }
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ FileNotFoundException -> 0x006a, SecurityException -> 0x0068 }
            r5.<init>(r6)     // Catch:{ FileNotFoundException -> 0x006a, SecurityException -> 0x0068 }
            com.google.android.systemui.elmyra.sensors.config.SensorCalibration r6 = new com.google.android.systemui.elmyra.sensors.config.SensorCalibration     // Catch:{ FileNotFoundException -> 0x006a, SecurityException -> 0x0068 }
            r6.<init>(r5)     // Catch:{ FileNotFoundException -> 0x006a, SecurityException -> 0x0068 }
            goto L_0x0078
        L_0x0068:
            r5 = move-exception
            goto L_0x006c
        L_0x006a:
            r5 = move-exception
            goto L_0x0072
        L_0x006c:
            java.lang.String r6 = "Could not open calibration file"
            android.util.Log.e(r3, r6, r5)     // Catch:{ Exception -> 0x009c }
            goto L_0x0077
        L_0x0072:
            java.lang.String r6 = "Could not find calibration file"
            android.util.Log.e(r3, r6, r5)     // Catch:{ Exception -> 0x009c }
        L_0x0077:
            r6 = r4
        L_0x0078:
            if (r6 == 0) goto L_0x009e
            java.util.Map r3 = r6.mProperties     // Catch:{ Exception -> 0x009c }
            android.util.ArrayMap r3 = (android.util.ArrayMap) r3     // Catch:{ Exception -> 0x009c }
            boolean r3 = r3.containsKey(r1)     // Catch:{ Exception -> 0x009c }
            if (r3 == 0) goto L_0x009e
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Sensor[] r3 = r11.sensors     // Catch:{ Exception -> 0x009c }
            r3 = r3[r2]     // Catch:{ Exception -> 0x009c }
            java.util.Map r5 = r6.mProperties     // Catch:{ Exception -> 0x009c }
            android.util.ArrayMap r5 = (android.util.ArrayMap) r5     // Catch:{ Exception -> 0x009c }
            java.lang.Object r5 = r5.get(r1)     // Catch:{ Exception -> 0x009c }
            java.lang.Float r5 = (java.lang.Float) r5     // Catch:{ Exception -> 0x009c }
            float r5 = r5.floatValue()     // Catch:{ Exception -> 0x009c }
            r6 = 1065353216(0x3f800000, float:1.0)
            float r6 = r6 / r5
            r3.sensitivity = r6     // Catch:{ Exception -> 0x009c }
            goto L_0x00b2
        L_0x009c:
            r9 = move-exception
            goto L_0x00b9
        L_0x009e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009c }
            r3.<init>()     // Catch:{ Exception -> 0x009c }
            java.lang.String r5 = "Error reading calibration for sensor "
            r3.append(r5)     // Catch:{ Exception -> 0x009c }
            r3.append(r2)     // Catch:{ Exception -> 0x009c }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x009c }
            android.util.Log.w(r0, r3)     // Catch:{ Exception -> 0x009c }
        L_0x00b2:
            int r2 = r2 + 1
            goto L_0x0049
        L_0x00b5:
            r8.createNativeService(r9)     // Catch:{ Exception -> 0x009c }
            goto L_0x00c0
        L_0x00b9:
            java.lang.String r11 = "Error reading chassis file"
            android.util.Log.e(r0, r11, r9)
            r8.mSensorCount = r10
        L_0x00c0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.elmyra.sensors.JNIGestureSensor.<init>(android.content.Context, com.google.android.systemui.elmyra.sensors.config.GestureConfiguration, com.android.keyguard.KeyguardUpdateMonitor):void");
    }

    private native boolean createNativeService(byte[] bArr);

    private native void destroyNativeService();

    private static byte[] getChassisAsset(Context context) {
        try {
            return readAllBytes(context.getResources().openRawResource(2131886101));
        } catch (Resources.NotFoundException | IOException e) {
            Log.e(TAG, "Could not load chassis resource", e);
            return null;
        }
    }

    public static boolean isAvailable(Context context) {
        byte[] chassisAsset;
        if (Settings.Secure.getInt(context.getContentResolver(), DISABLE_SETTING, 0) == 1 || !sLibraryLoaded || (chassisAsset = getChassisAsset(context)) == null || chassisAsset.length == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(GestureConfiguration gestureConfiguration) {
        updateConfiguration();
    }

    private void onGestureDetected() {
        this.mController.onGestureDetected((GestureSensor.DetectionProperties) null);
    }

    private void onGestureProgress(float f) {
        this.mController.onGestureProgress(f);
    }

    private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        int i = 1024;
        byte[] bArr = new byte[1024];
        int i2 = 0;
        while (true) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read > 0) {
                i2 += read;
            } else if (read < 0) {
                break;
            } else {
                i <<= 1;
                bArr = Arrays.copyOf(bArr, i);
            }
        }
        if (i == i2) {
            return bArr;
        }
        return Arrays.copyOf(bArr, i2);
    }

    private native boolean setGestureDetector(byte[] bArr);

    private native boolean startListeningNative(String str, int i);

    private native void stopListeningNative();

    public void finalize() throws Throwable {
        super.finalize();
        destroyNativeService();
    }

    public boolean isListening() {
        return this.mIsListening;
    }

    public void setGestureListener(GestureSensor.Listener listener) {
        this.mController.mGestureListener = listener;
    }

    public void startListening() {
        if (!this.mIsListening && startListeningNative(this.mSensorStringType, SENSOR_RATE)) {
            updateConfiguration();
            this.mIsListening = true;
        }
    }

    public void stopListening() {
        if (this.mIsListening) {
            stopListeningNative();
            this.mIsListening = false;
        }
    }

    private void updateConfiguration() {
    }
}
