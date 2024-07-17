package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.math.MathUtils;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.shared.system.QuickStepContract;
import java.util.Locale;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class NgaMessageHandler {
    public static final boolean VERBOSE;
    public final AssistantPresenceHandler mAssistantPresenceHandler;
    public final Set mConfigInfoListeners;
    public final Set mGoBackListeners;
    public final Handler mHandler;
    public boolean mIsGestureNav;
    public final Set mNavBarVisibilityListeners;
    public final NgaUiController mNgaUiController;
    public final Set mSwipeListeners;
    public final Set mWarmingListeners;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class ConfigInfo {
        public final PendingIntent configurationCallback;
        public final boolean ngaIsAssistant;

        public ConfigInfo(Bundle bundle) {
            this.ngaIsAssistant = bundle.getBoolean("nga_is_assistant");
            this.configurationCallback = (PendingIntent) bundle.getParcelable("configuration");
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface ConfigInfoListener {
        void onConfigInfo(ConfigInfo configInfo);
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class WarmingRequest {
        public final PendingIntent onWarm;
        public final float threshold;

        public WarmingRequest(PendingIntent pendingIntent, float f) {
            this.onWarm = pendingIntent;
            this.threshold = MathUtils.clamp(f);
        }
    }

    static {
        boolean z;
        String str = Build.TYPE;
        Locale locale = Locale.ROOT;
        if (str.toLowerCase(locale).contains("debug") || str.toLowerCase(locale).equals("eng")) {
            z = true;
        } else {
            z = false;
        }
        VERBOSE = z;
    }

    public NgaMessageHandler(NgaUiController ngaUiController, AssistantPresenceHandler assistantPresenceHandler, NavigationModeController navigationModeController, Set set, Set set2, Set set3, Set set4, Set set5, Handler handler) {
        this.mNgaUiController = ngaUiController;
        this.mAssistantPresenceHandler = assistantPresenceHandler;
        this.mConfigInfoListeners = set;
        this.mGoBackListeners = set2;
        this.mSwipeListeners = set3;
        this.mWarmingListeners = set4;
        this.mNavBarVisibilityListeners = set5;
        this.mHandler = handler;
        this.mIsGestureNav = QuickStepContract.isGesturalMode(navigationModeController.addListener(new NgaMessageHandler$$ExternalSyntheticLambda0(this)));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:84:0x022a, code lost:
        if (r6 != false) goto L_0x0245;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00cd A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void processBundle(android.os.Bundle r31, java.lang.Runnable r32) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            android.os.Looper r2 = android.os.Looper.myLooper()
            android.os.Handler r3 = r0.mHandler
            android.os.Looper r4 = r3.getLooper()
            if (r2 == r4) goto L_0x001b
            com.google.android.systemui.assist.uihints.NgaMessageHandler$$ExternalSyntheticLambda1 r2 = new com.google.android.systemui.assist.uihints.NgaMessageHandler$$ExternalSyntheticLambda1
            r4 = r32
            r2.<init>(r0, r1, r4)
            r3.post(r2)
            return
        L_0x001b:
            r4 = r32
            boolean r2 = VERBOSE
            if (r2 != 0) goto L_0x0022
            goto L_0x003e
        L_0x0022:
            java.util.Set r2 = r31.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x002a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x003e
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r1.get(r3)
            java.util.Objects.toString(r3)
            goto L_0x002a
        L_0x003e:
            java.lang.String r2 = "action"
            java.lang.String r3 = ""
            java.lang.String r2 = r1.getString(r2, r3)
            boolean r3 = r2.isEmpty()
            java.lang.String r5 = "NgaMessageHandler"
            if (r3 == 0) goto L_0x0054
            java.lang.String r0 = "No action specified, ignoring"
            android.util.Log.w(r5, r0)
            return
        L_0x0054:
            com.google.android.systemui.assist.uihints.AssistantPresenceHandler r3 = r0.mAssistantPresenceHandler
            boolean r3 = r3.mNgaIsAssistant
            int r6 = r2.hashCode()
            r7 = -1354792126(0xffffffffaf3f8342, float:-1.7417981E-10)
            r8 = -1
            r15 = 1
            r29 = 0
            if (r6 == r7) goto L_0x0075
            r7 = -711214028(0xffffffffd59bbc34, float:-2.14040786E13)
            if (r6 == r7) goto L_0x006b
            goto L_0x0080
        L_0x006b:
            java.lang.String r6 = "gesture_nav_bar_visible"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x0080
            r6 = r15
            goto L_0x0081
        L_0x0075:
            java.lang.String r6 = "config"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x0080
            r6 = r29
            goto L_0x0081
        L_0x0080:
            r6 = r8
        L_0x0081:
            if (r6 == 0) goto L_0x00aa
            if (r6 == r15) goto L_0x0088
            r6 = r29
            goto L_0x00cb
        L_0x0088:
            boolean r6 = r0.mIsGestureNav
            if (r6 == 0) goto L_0x00ca
            java.lang.String r6 = "visible"
            boolean r6 = r1.getBoolean(r6, r15)
            java.util.Set r7 = r0.mNavBarVisibilityListeners
            java.util.Iterator r7 = r7.iterator()
        L_0x0098:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x00ca
            java.lang.Object r9 = r7.next()
            com.google.android.systemui.assist.uihints.NavBarFadeController r9 = (com.google.android.systemui.assist.uihints.NavBarFadeController) r9
            r9.ngaVisible = r6
            r9.update()
            goto L_0x0098
        L_0x00aa:
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ConfigInfo r6 = new com.google.android.systemui.assist.uihints.NgaMessageHandler$ConfigInfo
            r6.<init>(r1)
            java.util.Set r7 = r0.mConfigInfoListeners
            java.util.Iterator r7 = r7.iterator()
        L_0x00b5:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x00c5
            java.lang.Object r9 = r7.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ConfigInfoListener r9 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.ConfigInfoListener) r9
            r9.onConfigInfo(r6)
            goto L_0x00b5
        L_0x00c5:
            com.google.android.systemui.assist.uihints.NgaUiController r6 = r0.mNgaUiController
            r6.refresh$1()
        L_0x00ca:
            r6 = r15
        L_0x00cb:
            if (r6 != 0) goto L_0x022a
            if (r3 == 0) goto L_0x022a
            int r6 = r2.hashCode()
            r7 = 109854522(0x68c3f3a, float:5.275505E-35)
            r9 = 2
            if (r6 == r7) goto L_0x00f9
            r7 = 192184798(0xb7481de, float:4.7090392E-32)
            if (r6 == r7) goto L_0x00ee
            r7 = 1124416317(0x43053b3d, float:133.2314)
            if (r6 == r7) goto L_0x00e4
            goto L_0x0102
        L_0x00e4:
            java.lang.String r6 = "warming"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x0102
            r8 = r9
            goto L_0x0102
        L_0x00ee:
            java.lang.String r6 = "go_back"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x0102
            r8 = r29
            goto L_0x0102
        L_0x00f9:
            java.lang.String r6 = "swipe"
            boolean r6 = r2.equals(r6)
            if (r6 == 0) goto L_0x0102
            r8 = r15
        L_0x0102:
            if (r8 == 0) goto L_0x01cc
            if (r8 == r15) goto L_0x0135
            if (r8 == r9) goto L_0x010a
            goto L_0x022c
        L_0x010a:
            java.lang.String r2 = "intent"
            android.os.Parcelable r2 = r1.getParcelable(r2)
            android.app.PendingIntent r2 = (android.app.PendingIntent) r2
            java.lang.String r3 = "threshold"
            r5 = 1036831949(0x3dcccccd, float:0.1)
            float r1 = r1.getFloat(r3, r5)
            com.google.android.systemui.assist.uihints.NgaMessageHandler$WarmingRequest r3 = new com.google.android.systemui.assist.uihints.NgaMessageHandler$WarmingRequest
            r3.<init>(r2, r1)
            java.util.Set r0 = r0.mWarmingListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x0126:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0245
            java.lang.Object r1 = r0.next()
            com.google.android.systemui.assist.uihints.AssistantWarmer r1 = (com.google.android.systemui.assist.uihints.AssistantWarmer) r1
            r1.request = r3
            goto L_0x0126
        L_0x0135:
            java.lang.String r2 = "swipe_action"
            android.os.Parcelable r1 = r1.getParcelable(r2)
            android.os.Bundle r1 = (android.os.Bundle) r1
            if (r1 != 0) goto L_0x0146
            java.lang.String r0 = "Missing swipe action argument, ignoring request"
            android.util.Log.e(r5, r0)
            goto L_0x0245
        L_0x0146:
            java.util.Set r0 = r0.mSwipeListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x014c:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0245
            java.lang.Object r2 = r0.next()
            com.google.android.systemui.assist.uihints.SwipeHandler r2 = (com.google.android.systemui.assist.uihints.SwipeHandler) r2
            r2.getClass()
            java.lang.String r3 = "start_x"
            r5 = 0
            float r23 = r1.getFloat(r3, r5)
            java.lang.String r3 = "start_y"
            float r25 = r1.getFloat(r3, r5)
            java.lang.String r3 = "end_x"
            float r24 = r1.getFloat(r3, r5)
            java.lang.String r3 = "end_y"
            float r26 = r1.getFloat(r3, r5)
            java.lang.String r3 = "duration_ms"
            r5 = 1000(0x3e8, float:1.401E-42)
            int r3 = r1.getInt(r3, r5)
            int r6 = r3 * 60
            int r6 = r6 / r5
            java.lang.String r5 = "num_motion_events"
            int r5 = r1.getInt(r5, r6)
            java.lang.String r6 = "SwipeHandler"
            if (r5 < r15) goto L_0x01c6
            r7 = 600(0x258, float:8.41E-43)
            if (r5 <= r7) goto L_0x018e
            goto L_0x01c6
        L_0x018e:
            if (r3 < 0) goto L_0x01c0
            r7 = 10000(0x2710, float:1.4013E-41)
            if (r3 <= r7) goto L_0x0195
            goto L_0x01c0
        L_0x0195:
            long r20 = android.os.SystemClock.uptimeMillis()
            r12 = 1065353216(0x3f800000, float:1.0)
            r6 = 4098(0x1002, float:5.743E-42)
            r7 = 0
            r8 = r20
            r10 = r23
            r11 = r25
            com.google.android.systemui.assist.uihints.SwipeHandler.injectMotionEvent(r6, r7, r8, r10, r11, r12)
            long r6 = (long) r3
            long r18 = r20 + r6
            int r5 = r3 / r5
            com.google.android.systemui.assist.uihints.SwipeHandler$1 r6 = new com.google.android.systemui.assist.uihints.SwipeHandler$1
            r16 = r6
            r17 = r2
            r22 = r3
            r27 = r5
            r16.<init>(r18, r20, r22, r23, r24, r25, r26, r27)
            android.os.Handler r2 = r2.mUiHandler
            long r7 = (long) r5
            r2.postDelayed(r6, r7)
            goto L_0x014c
        L_0x01c0:
            java.lang.String r2 = "Invalid swipe duration requested"
            android.util.Log.e(r6, r2)
            goto L_0x014c
        L_0x01c6:
            java.lang.String r2 = "Invalid number of motion events requested"
            android.util.Log.e(r6, r2)
            goto L_0x014c
        L_0x01cc:
            java.util.Set r0 = r0.mGoBackListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x01d2:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0245
            java.lang.Object r1 = r0.next()
            com.google.android.systemui.assist.uihints.GoBackHandler r1 = (com.google.android.systemui.assist.uihints.GoBackHandler) r1
            r1.getClass()
            long r19 = android.os.SystemClock.uptimeMillis()
            android.hardware.input.InputManagerGlobal r1 = android.hardware.input.InputManagerGlobal.getInstance()
            android.view.KeyEvent r2 = new android.view.KeyEvent
            r27 = 72
            r28 = 257(0x101, float:3.6E-43)
            r22 = 4
            r23 = 0
            r24 = 0
            r25 = -1
            r26 = 0
            r16 = r2
            r17 = r19
            r21 = r29
            r16.<init>(r17, r19, r21, r22, r23, r24, r25, r26, r27, r28)
            r3 = 0
            r1.injectInputEvent(r2, r3)
            long r12 = android.os.SystemClock.uptimeMillis()
            android.hardware.input.InputManagerGlobal r1 = android.hardware.input.InputManagerGlobal.getInstance()
            android.view.KeyEvent r2 = new android.view.KeyEvent
            r20 = 72
            r21 = 257(0x101, float:3.6E-43)
            r5 = 4
            r16 = 0
            r17 = 0
            r18 = -1
            r19 = 0
            r9 = r2
            r10 = r12
            r14 = r15
            r6 = r15
            r15 = r5
            r9.<init>(r10, r12, r14, r15, r16, r17, r18, r19, r20, r21)
            r1.injectInputEvent(r2, r3)
            r15 = r6
            goto L_0x01d2
        L_0x022a:
            if (r6 != 0) goto L_0x0245
        L_0x022c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid action \""
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = "\" for state:\n  NGA is Assistant = "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r5, r0)
        L_0x0245:
            r32.run()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.NgaMessageHandler.processBundle(android.os.Bundle, java.lang.Runnable):void");
    }
}
