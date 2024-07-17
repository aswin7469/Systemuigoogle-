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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0085 A[Catch:{ Exception -> 0x009d }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x009f A[Catch:{ Exception -> 0x009d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JNIGestureSensor(android.content.Context r10, com.google.android.systemui.elmyra.sensors.config.GestureConfiguration r11, com.android.keyguard.KeyguardUpdateMonitor r12) {
        /*
            r9 = this;
            java.lang.String r0 = "touch_2_sensitivity"
            java.lang.String r1 = "Elmyra/JNIGestureSensor"
            r9.<init>()
            com.google.android.systemui.elmyra.sensors.JNIGestureSensor$1 r2 = new com.google.android.systemui.elmyra.sensors.JNIGestureSensor$1
            r2.<init>()
            r9.mKeyguardUpdateMonitorCallback = r2
            r9.mContext = r10
            com.google.android.systemui.elmyra.sensors.AssistGestureController r3 = new com.google.android.systemui.elmyra.sensors.AssistGestureController
            r4 = 0
            r3.<init>(r10, r9, r11, r4)
            r9.mController = r3
            android.content.res.Resources r3 = r10.getResources()
            r5 = 2131952481(0x7f130361, float:1.9541406E38)
            java.lang.String r3 = r3.getString(r5)
            r9.mSensorStringType = r3
            r9.mGestureConfiguration = r11
            com.google.android.systemui.elmyra.sensors.JNIGestureSensor$$ExternalSyntheticLambda0 r3 = new com.google.android.systemui.elmyra.sensors.JNIGestureSensor$$ExternalSyntheticLambda0
            r3.<init>(r9)
            r11.mListener = r3
            r12.registerCallback(r2)
            byte[] r10 = getChassisAsset(r10)
            if (r10 == 0) goto L_0x00c1
            int r11 = r10.length
            if (r11 == 0) goto L_0x00c1
            r11 = 0
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis r12 = new com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Chassis     // Catch:{ Exception -> 0x009d }
            r12.<init>()     // Catch:{ Exception -> 0x009d }
            com.google.protobuf.nano.MessageNano.mergeFrom(r12, r10)     // Catch:{ Exception -> 0x009d }
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Sensor[] r2 = r12.sensors     // Catch:{ Exception -> 0x009d }
            int r2 = r2.length     // Catch:{ Exception -> 0x009d }
            r9.mSensorCount = r2     // Catch:{ Exception -> 0x009d }
            r2 = r11
        L_0x0049:
            int r3 = r9.mSensorCount     // Catch:{ Exception -> 0x009d }
            if (r2 >= r3) goto L_0x00b6
            java.lang.String r3 = "Elmyra/SensorCalibration"
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            java.lang.String r6 = "/persist/sensors/elmyra/calibration.%d"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            r7[r11] = r8     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            r5.<init>(r6)     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            com.google.android.systemui.elmyra.sensors.config.SensorCalibration r6 = new com.google.android.systemui.elmyra.sensors.config.SensorCalibration     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            r6.<init>(r5)     // Catch:{ FileNotFoundException -> 0x006b, SecurityException -> 0x0069 }
            goto L_0x0079
        L_0x0069:
            r5 = move-exception
            goto L_0x006d
        L_0x006b:
            r5 = move-exception
            goto L_0x0073
        L_0x006d:
            java.lang.String r6 = "Could not open calibration file"
            android.util.Log.e(r3, r6, r5)     // Catch:{ Exception -> 0x009d }
            goto L_0x0078
        L_0x0073:
            java.lang.String r6 = "Could not find calibration file"
            android.util.Log.e(r3, r6, r5)     // Catch:{ Exception -> 0x009d }
        L_0x0078:
            r6 = r4
        L_0x0079:
            if (r6 == 0) goto L_0x009f
            java.util.Map r3 = r6.mProperties     // Catch:{ Exception -> 0x009d }
            android.util.ArrayMap r3 = (android.util.ArrayMap) r3     // Catch:{ Exception -> 0x009d }
            boolean r3 = r3.containsKey(r0)     // Catch:{ Exception -> 0x009d }
            if (r3 == 0) goto L_0x009f
            com.google.android.systemui.elmyra.proto.nano.ChassisProtos$Sensor[] r3 = r12.sensors     // Catch:{ Exception -> 0x009d }
            r3 = r3[r2]     // Catch:{ Exception -> 0x009d }
            java.util.Map r5 = r6.mProperties     // Catch:{ Exception -> 0x009d }
            android.util.ArrayMap r5 = (android.util.ArrayMap) r5     // Catch:{ Exception -> 0x009d }
            java.lang.Object r5 = r5.get(r0)     // Catch:{ Exception -> 0x009d }
            java.lang.Float r5 = (java.lang.Float) r5     // Catch:{ Exception -> 0x009d }
            float r5 = r5.floatValue()     // Catch:{ Exception -> 0x009d }
            r6 = 1065353216(0x3f800000, float:1.0)
            float r6 = r6 / r5
            r3.sensitivity = r6     // Catch:{ Exception -> 0x009d }
            goto L_0x00b3
        L_0x009d:
            r10 = move-exception
            goto L_0x00ba
        L_0x009f:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009d }
            r3.<init>()     // Catch:{ Exception -> 0x009d }
            java.lang.String r5 = "Error reading calibration for sensor "
            r3.append(r5)     // Catch:{ Exception -> 0x009d }
            r3.append(r2)     // Catch:{ Exception -> 0x009d }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x009d }
            android.util.Log.w(r1, r3)     // Catch:{ Exception -> 0x009d }
        L_0x00b3:
            int r2 = r2 + 1
            goto L_0x0049
        L_0x00b6:
            r9.createNativeService(r10)     // Catch:{ Exception -> 0x009d }
            goto L_0x00c1
        L_0x00ba:
            java.lang.String r12 = "Error reading chassis file"
            android.util.Log.e(r1, r12, r10)
            r9.mSensorCount = r11
        L_0x00c1:
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
