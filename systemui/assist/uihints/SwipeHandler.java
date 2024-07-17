package com.google.android.systemui.assist.uihints;

import android.hardware.input.InputManagerGlobal;
import android.os.Handler;
import android.os.Looper;
import android.view.InputDevice;
import android.view.MotionEvent;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SwipeHandler {
    public final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public static void injectMotionEvent(int i, int i2, long j, float f, float f2, float f3) {
        int i3;
        int i4 = i;
        int[] deviceIds = InputDevice.getDeviceIds();
        int length = deviceIds.length;
        int i5 = 0;
        while (true) {
            if (i5 >= length) {
                i3 = 0;
                break;
            }
            int i6 = deviceIds[i5];
            if (InputDevice.getDevice(i6).supportsSource(i4)) {
                i3 = i6;
                break;
            }
            i5++;
        }
        MotionEvent obtain = MotionEvent.obtain(j, j, i2, f, f2, f3, 1.0f, 0, 1.0f, 1.0f, i3, 0);
        obtain.setSource(i4);
        InputManagerGlobal.getInstance().injectInputEvent(obtain, 0);
    }
}
