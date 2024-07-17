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

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NgaMessageHandler {
    public static final boolean VERBOSE;
    public final AssistantPresenceHandler mAssistantPresenceHandler;
    public final Set mAudioInfoListeners;
    public final Set mCardInfoListeners;
    public final Set mChipsInfoListeners;
    public final Set mClearListeners;
    public final Set mConfigInfoListeners;
    public final Set mEdgeLightsInfoListeners;
    public final Set mGoBackListeners;
    public final Set mGreetingInfoListeners;
    public final Handler mHandler;
    public boolean mIsGestureNav;
    public final Set mKeepAliveListeners;
    public final Set mKeyboardInfoListeners;
    public final Set mNavBarVisibilityListeners;
    public final NgaUiController mNgaUiController;
    public final Set mStartActivityInfoListeners;
    public final Set mSwipeListeners;
    public final Set mTakeScreenshotListeners;
    public final Set mTranscriptionInfoListeners;
    public final Set mWarmingListeners;
    public final Set mZerostateInfoListeners;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface AudioInfoListener {
        void onAudioInfo(float f, float f2);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface CardInfoListener {
        void onCardInfo(int i, boolean z, boolean z2, boolean z3);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ChipsInfoListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ClearListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class ConfigInfo {
        public final PendingIntent configurationCallback;
        public final boolean ngaIsAssistant;
        public final PendingIntent onColorChanged;
        public final PendingIntent onKeyboardShowingChange;
        public final PendingIntent onTaskChange;
        public final PendingIntent onTouchInside;
        public final PendingIntent onTouchOutside;
        public final boolean sysUiIsNgaUi;

        public ConfigInfo(Bundle bundle) {
            boolean z;
            boolean z2 = bundle.getBoolean("is_available");
            boolean z3 = bundle.getBoolean("nga_is_assistant", z2);
            this.ngaIsAssistant = z3;
            if (!z2 || !z3) {
                z = false;
            } else {
                z = true;
            }
            this.sysUiIsNgaUi = z;
            this.onColorChanged = (PendingIntent) bundle.getParcelable("color_changed");
            this.onTouchOutside = (PendingIntent) bundle.getParcelable("touch_outside");
            this.onTouchInside = (PendingIntent) bundle.getParcelable("touch_inside");
            this.onTaskChange = (PendingIntent) bundle.getParcelable("task_stack_changed");
            this.onKeyboardShowingChange = (PendingIntent) bundle.getParcelable("keyboard_showing_changed");
            this.configurationCallback = (PendingIntent) bundle.getParcelable("configuration");
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ConfigInfoListener {
        void onConfigInfo(ConfigInfo configInfo);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface EdgeLightsInfoListener {
        void onEdgeLightsInfo(String str, boolean z);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface GreetingInfoListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface KeepAliveListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface KeyboardInfoListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface NavBarVisibilityListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface TranscriptionInfoListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class WarmingRequest {
        public final PendingIntent onWarm;
        public final float threshold;

        public WarmingRequest(PendingIntent pendingIntent, float f) {
            this.onWarm = pendingIntent;
            this.threshold = MathUtils.clamp(f, 0.0f, 1.0f);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ZerostateInfoListener {
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

    public NgaMessageHandler(NgaUiController ngaUiController, AssistantPresenceHandler assistantPresenceHandler, NavigationModeController navigationModeController, Set set, Set set2, Set set3, Set set4, Set set5, Set set6, Set set7, Set set8, Set set9, Set set10, Set set11, Set set12, Set set13, Set set14, Set set15, Set set16, Set set17, Handler handler) {
        this.mNgaUiController = ngaUiController;
        this.mAssistantPresenceHandler = assistantPresenceHandler;
        this.mKeepAliveListeners = set;
        this.mAudioInfoListeners = set2;
        this.mCardInfoListeners = set3;
        this.mConfigInfoListeners = set4;
        this.mEdgeLightsInfoListeners = set5;
        this.mTranscriptionInfoListeners = set6;
        this.mGreetingInfoListeners = set7;
        this.mChipsInfoListeners = set8;
        this.mClearListeners = set9;
        this.mStartActivityInfoListeners = set10;
        this.mKeyboardInfoListeners = set11;
        this.mZerostateInfoListeners = set12;
        this.mGoBackListeners = set13;
        this.mSwipeListeners = set14;
        this.mTakeScreenshotListeners = set15;
        this.mWarmingListeners = set16;
        this.mNavBarVisibilityListeners = set17;
        this.mHandler = handler;
        NavigationModeController navigationModeController2 = navigationModeController;
        this.mIsGestureNav = QuickStepContract.isGesturalMode(navigationModeController.addListener(new NgaMessageHandler$$ExternalSyntheticLambda0(this)));
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:263:0x0661, code lost:
        r12.refresh$2();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:266:0x066a, code lost:
        if (r14 != false) goto L_0x0682;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x066c, code lost:
        r0 = com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Invalid action \"", r7, "\" for state:\n  NGA is Assistant = ", r4, "\n  SysUI is NGA UI = ");
        r0.append(r8);
        android.util.Log.w(r31, r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:268:0x0682, code lost:
        r36.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x0685, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0124 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void processBundle(android.os.Bundle r35, java.lang.Runnable r36) {
        /*
            r34 = this;
            r0 = r34
            r1 = r35
            android.os.Looper r2 = android.os.Looper.myLooper()
            android.os.Handler r3 = r0.mHandler
            android.os.Looper r4 = r3.getLooper()
            if (r2 == r4) goto L_0x001b
            com.google.android.systemui.assist.uihints.NgaMessageHandler$$ExternalSyntheticLambda1 r2 = new com.google.android.systemui.assist.uihints.NgaMessageHandler$$ExternalSyntheticLambda1
            r4 = r36
            r2.<init>(r0, r1, r4)
            r3.post(r2)
            return
        L_0x001b:
            r4 = r36
            boolean r2 = VERBOSE
            java.lang.String r3 = "chips"
            java.lang.String r5 = "text"
            java.lang.String r6 = "audio_info"
            java.lang.String r7 = "action"
            if (r2 == 0) goto L_0x0096
            java.lang.Object r2 = r1.get(r7)
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0034
            goto L_0x0096
        L_0x0034:
            java.util.Set r2 = r35.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x003c:
            boolean r8 = r2.hasNext()
            if (r8 == 0) goto L_0x0096
            java.lang.Object r8 = r2.next()
            java.lang.String r8 = (java.lang.String) r8
            boolean r9 = r5.equals(r8)
            if (r9 == 0) goto L_0x0056
            java.lang.String r8 = r1.getString(r8)
            r8.getClass()
            goto L_0x003c
        L_0x0056:
            boolean r9 = r3.equals(r8)
            if (r9 == 0) goto L_0x008e
            java.util.ArrayList r8 = r1.getParcelableArrayList(r3)
            if (r8 == 0) goto L_0x003c
            java.util.Iterator r8 = r8.iterator()
        L_0x0066:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x003c
            java.lang.Object r9 = r8.next()
            android.os.Bundle r9 = (android.os.Bundle) r9
            java.util.Set r10 = r9.keySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x007a:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0066
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r9.get(r11)
            java.util.Objects.toString(r11)
            goto L_0x007a
        L_0x008e:
            java.lang.Object r8 = r1.get(r8)
            java.util.Objects.toString(r8)
            goto L_0x003c
        L_0x0096:
            java.lang.String r2 = ""
            java.lang.String r7 = r1.getString(r7, r2)
            boolean r8 = r7.isEmpty()
            java.lang.String r9 = "NgaMessageHandler"
            if (r8 == 0) goto L_0x00aa
            java.lang.String r0 = "No action specified, ignoring"
            android.util.Log.w(r9, r0)
            return
        L_0x00aa:
            com.google.android.systemui.assist.uihints.AssistantPresenceHandler r8 = r0.mAssistantPresenceHandler
            boolean r10 = r8.mNgaIsAssistant
            boolean r8 = r8.mSysUiIsNgaUi
            int r11 = r7.hashCode()
            r12 = -1354792126(0xffffffffaf3f8342, float:-1.7417981E-10)
            r15 = 1
            if (r11 == r12) goto L_0x00ca
            r12 = -711214028(0xffffffffd59bbc34, float:-2.14040786E13)
            if (r11 == r12) goto L_0x00c0
            goto L_0x00d4
        L_0x00c0:
            java.lang.String r11 = "gesture_nav_bar_visible"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x00d4
            r11 = r15
            goto L_0x00d5
        L_0x00ca:
            java.lang.String r11 = "config"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x00d4
            r11 = 0
            goto L_0x00d5
        L_0x00d4:
            r11 = -1
        L_0x00d5:
            com.google.android.systemui.assist.uihints.NgaUiController r12 = r0.mNgaUiController
            if (r11 == 0) goto L_0x0103
            if (r11 == r15) goto L_0x00dd
            r14 = 0
            goto L_0x0122
        L_0x00dd:
            boolean r11 = r0.mIsGestureNav
            if (r11 == 0) goto L_0x0121
            java.lang.String r11 = "visible"
            boolean r11 = r1.getBoolean(r11, r15)
            java.util.Set r13 = r0.mNavBarVisibilityListeners
            java.util.Iterator r13 = r13.iterator()
        L_0x00ed:
            boolean r16 = r13.hasNext()
            if (r16 == 0) goto L_0x0121
            java.lang.Object r16 = r13.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$NavBarVisibilityListener r16 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.NavBarVisibilityListener) r16
            r14 = r16
            com.google.android.systemui.assist.uihints.NavBarFadeController r14 = (com.google.android.systemui.assist.uihints.NavBarFadeController) r14
            r14.ngaVisible = r11
            r14.update()
            goto L_0x00ed
        L_0x0103:
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ConfigInfo r11 = new com.google.android.systemui.assist.uihints.NgaMessageHandler$ConfigInfo
            r11.<init>(r1)
            java.util.Set r13 = r0.mConfigInfoListeners
            java.util.Iterator r13 = r13.iterator()
        L_0x010e:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x011e
            java.lang.Object r14 = r13.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ConfigInfoListener r14 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.ConfigInfoListener) r14
            r14.onConfigInfo(r11)
            goto L_0x010e
        L_0x011e:
            r12.refresh$2()
        L_0x0121:
            r14 = r15
        L_0x0122:
            if (r14 != 0) goto L_0x0667
            if (r10 == 0) goto L_0x0667
            int r11 = r7.hashCode()
            r13 = 2
            switch(r11) {
                case 3046160: goto L_0x0161;
                case 109854522: goto L_0x0157;
                case 192184798: goto L_0x014d;
                case 371207756: goto L_0x0143;
                case 777739294: goto L_0x0139;
                case 1124416317: goto L_0x012f;
                default: goto L_0x012e;
            }
        L_0x012e:
            goto L_0x016b
        L_0x012f:
            java.lang.String r11 = "warming"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x016b
            r11 = 5
            goto L_0x016c
        L_0x0139:
            java.lang.String r11 = "take_screenshot"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x016b
            r11 = 4
            goto L_0x016c
        L_0x0143:
            java.lang.String r11 = "start_activity"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x016b
            r11 = r15
            goto L_0x016c
        L_0x014d:
            java.lang.String r11 = "go_back"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x016b
            r11 = r13
            goto L_0x016c
        L_0x0157:
            java.lang.String r11 = "swipe"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x016b
            r11 = 3
            goto L_0x016c
        L_0x0161:
            java.lang.String r11 = "card"
            boolean r11 = r7.equals(r11)
            if (r11 == 0) goto L_0x016b
            r11 = 0
            goto L_0x016c
        L_0x016b:
            r11 = -1
        L_0x016c:
            if (r11 == 0) goto L_0x034d
            java.lang.String r14 = "intent"
            if (r11 == r15) goto L_0x0310
            if (r11 == r13) goto L_0x02a4
            r13 = 3
            if (r11 == r13) goto L_0x01ec
            r13 = 4
            if (r11 == r13) goto L_0x01bd
            r13 = 5
            if (r11 == r13) goto L_0x0189
            r31 = r9
            r4 = r10
            r27 = r13
            r15 = 0
            r28 = 4
            r29 = 3
            goto L_0x038e
        L_0x0189:
            android.os.Parcelable r11 = r1.getParcelable(r14)
            android.app.PendingIntent r11 = (android.app.PendingIntent) r11
            java.lang.String r13 = "threshold"
            r14 = 1036831949(0x3dcccccd, float:0.1)
            float r13 = r1.getFloat(r13, r14)
            com.google.android.systemui.assist.uihints.NgaMessageHandler$WarmingRequest r14 = new com.google.android.systemui.assist.uihints.NgaMessageHandler$WarmingRequest
            r14.<init>(r11, r13)
            java.util.Set r11 = r0.mWarmingListeners
            java.util.Iterator r11 = r11.iterator()
        L_0x01a3:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x01b2
            java.lang.Object r13 = r11.next()
            com.google.android.systemui.assist.uihints.AssistantWarmer r13 = (com.google.android.systemui.assist.uihints.AssistantWarmer) r13
            r13.request = r14
            goto L_0x01a3
        L_0x01b2:
            r31 = r9
            r4 = r10
        L_0x01b5:
            r27 = 5
        L_0x01b7:
            r28 = 4
            r29 = 3
            goto L_0x038d
        L_0x01bd:
            java.lang.String r11 = "on_finish"
            android.os.Parcelable r11 = r1.getParcelable(r11)
            android.app.PendingIntent r11 = (android.app.PendingIntent) r11
            java.util.Set r13 = r0.mTakeScreenshotListeners
            java.util.Iterator r13 = r13.iterator()
        L_0x01cb:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x01b2
            java.lang.Object r14 = r13.next()
            com.google.android.systemui.assist.uihints.TakeScreenshotHandler r14 = (com.google.android.systemui.assist.uihints.TakeScreenshotHandler) r14
            com.android.internal.util.ScreenshotHelper r15 = r14.mScreenshotHelper
            android.os.Handler r4 = r14.mHandler
            r16 = r13
            com.google.android.systemui.assist.uihints.TakeScreenshotHandler$$ExternalSyntheticLambda0 r13 = new com.google.android.systemui.assist.uihints.TakeScreenshotHandler$$ExternalSyntheticLambda0
            r13.<init>(r14, r11)
            r14 = 5
            r15.takeScreenshot(r14, r4, r13)
            r4 = r36
            r13 = r16
            r15 = 1
            goto L_0x01cb
        L_0x01ec:
            r14 = 5
            java.lang.String r4 = "swipe_action"
            android.os.Parcelable r4 = r1.getParcelable(r4)
            android.os.Bundle r4 = (android.os.Bundle) r4
            if (r4 != 0) goto L_0x0202
            java.lang.String r4 = "Missing swipe action argument, ignoring request"
            android.util.Log.e(r9, r4)
        L_0x01fc:
            r31 = r9
            r4 = r10
            r27 = r14
            goto L_0x01b7
        L_0x0202:
            java.util.Set r11 = r0.mSwipeListeners
            java.util.Iterator r11 = r11.iterator()
        L_0x0208:
            boolean r13 = r11.hasNext()
            if (r13 == 0) goto L_0x01fc
            java.lang.Object r13 = r11.next()
            com.google.android.systemui.assist.uihints.SwipeHandler r13 = (com.google.android.systemui.assist.uihints.SwipeHandler) r13
            r13.getClass()
            java.lang.String r15 = "start_x"
            r14 = 0
            float r23 = r4.getFloat(r15, r14)
            java.lang.String r15 = "start_y"
            float r24 = r4.getFloat(r15, r14)
            java.lang.String r15 = "end_x"
            float r25 = r4.getFloat(r15, r14)
            java.lang.String r15 = "end_y"
            float r14 = r4.getFloat(r15, r14)
            java.lang.String r15 = "duration_ms"
            r27 = r11
            r11 = 1000(0x3e8, float:1.401E-42)
            int r15 = r4.getInt(r15, r11)
            r31 = r9
            int r9 = r15 * 60
            int r9 = r9 / r11
            java.lang.String r11 = "num_motion_events"
            int r9 = r4.getInt(r11, r9)
            java.lang.String r11 = "SwipeHandler"
            r28 = r4
            r4 = 1
            if (r9 < r4) goto L_0x0250
            r4 = 600(0x258, float:8.41E-43)
            if (r9 <= r4) goto L_0x0252
        L_0x0250:
            r4 = r10
            goto L_0x0295
        L_0x0252:
            if (r15 < 0) goto L_0x0258
            r4 = 10000(0x2710, float:1.4013E-41)
            if (r15 <= r4) goto L_0x025a
        L_0x0258:
            r4 = r10
            goto L_0x028f
        L_0x025a:
            long r32 = android.os.SystemClock.uptimeMillis()
            r22 = 1065353216(0x3f800000, float:1.0)
            r16 = 4098(0x1002, float:5.743E-42)
            r17 = 0
            r18 = r32
            r20 = r23
            r21 = r24
            com.google.android.systemui.assist.uihints.SwipeHandler.injectMotionEvent(r16, r17, r18, r20, r21, r22)
            r4 = r10
            long r10 = (long) r15
            long r17 = r32 + r10
            int r9 = r15 / r9
            com.google.android.systemui.assist.uihints.SwipeHandler$1 r10 = new com.google.android.systemui.assist.uihints.SwipeHandler$1
            r11 = r15
            r15 = r10
            r16 = r13
            r19 = r32
            r21 = r11
            r22 = r23
            r23 = r25
            r25 = r14
            r26 = r9
            r15.<init>(r17, r19, r21, r22, r23, r24, r25, r26)
            android.os.Handler r11 = r13.mUiHandler
            long r13 = (long) r9
            r11.postDelayed(r10, r13)
            goto L_0x029a
        L_0x028f:
            java.lang.String r9 = "Invalid swipe duration requested"
            android.util.Log.e(r11, r9)
            goto L_0x029a
        L_0x0295:
            java.lang.String r9 = "Invalid number of motion events requested"
            android.util.Log.e(r11, r9)
        L_0x029a:
            r10 = r4
            r11 = r27
            r4 = r28
            r9 = r31
            r14 = 5
            goto L_0x0208
        L_0x02a4:
            r31 = r9
            r4 = r10
            java.util.Set r9 = r0.mGoBackListeners
            java.util.Iterator r9 = r9.iterator()
        L_0x02ad:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x01b5
            java.lang.Object r10 = r9.next()
            com.google.android.systemui.assist.uihints.GoBackHandler r10 = (com.google.android.systemui.assist.uihints.GoBackHandler) r10
            r10.getClass()
            long r19 = android.os.SystemClock.uptimeMillis()
            android.hardware.input.InputManagerGlobal r10 = android.hardware.input.InputManagerGlobal.getInstance()
            android.view.KeyEvent r11 = new android.view.KeyEvent
            r27 = 72
            r28 = 257(0x101, float:3.6E-43)
            r22 = 4
            r23 = 0
            r24 = 0
            r25 = -1
            r26 = 0
            r16 = r11
            r17 = r19
            r13 = 0
            r21 = r13
            r16.<init>(r17, r19, r21, r22, r23, r24, r25, r26, r27, r28)
            r14 = 0
            r10.injectInputEvent(r11, r14)
            long r17 = android.os.SystemClock.uptimeMillis()
            android.hardware.input.InputManagerGlobal r10 = android.hardware.input.InputManagerGlobal.getInstance()
            android.view.KeyEvent r11 = new android.view.KeyEvent
            r25 = 72
            r26 = 257(0x101, float:3.6E-43)
            r20 = 4
            r21 = 0
            r22 = 0
            r23 = -1
            r24 = 0
            r15 = r14
            r27 = 5
            r28 = 4
            r29 = 3
            r14 = r11
            r13 = r15
            r30 = 1
            r15 = r17
            r19 = r30
            r14.<init>(r15, r17, r19, r20, r21, r22, r23, r24, r25, r26)
            r10.injectInputEvent(r11, r13)
            goto L_0x02ad
        L_0x0310:
            r31 = r9
            r4 = r10
            r30 = r15
            r13 = 0
            r27 = 5
            r28 = 4
            r29 = 3
            android.os.Parcelable r9 = r1.getParcelable(r14)
            android.content.Intent r9 = (android.content.Intent) r9
            java.lang.String r10 = "dismiss_shade"
            boolean r10 = r1.getBoolean(r10)
            java.util.Set r11 = r0.mStartActivityInfoListeners
            java.util.Iterator r11 = r11.iterator()
        L_0x032e:
            boolean r14 = r11.hasNext()
            if (r14 == 0) goto L_0x038d
            java.lang.Object r14 = r11.next()
            com.google.android.systemui.assist.uihints.AssistantUIHintsModule$1 r14 = (com.google.android.systemui.assist.uihints.AssistantUIHintsModule$1) r14
            if (r9 != 0) goto L_0x0347
            r14.getClass()
            java.lang.String r14 = "ActivityStarter"
            java.lang.String r15 = "Null intent; cannot start activity"
            android.util.Log.e(r14, r15)
            goto L_0x032e
        L_0x0347:
            com.android.systemui.plugins.ActivityStarter r14 = r14.val$activityStarter
            r14.startActivity(r9, r10)
            goto L_0x032e
        L_0x034d:
            r31 = r9
            r4 = r10
            r30 = r15
            r13 = 0
            r27 = 5
            r28 = 4
            r29 = 3
            java.lang.String r9 = "is_visible"
            boolean r9 = r1.getBoolean(r9)
            java.lang.String r10 = "sysui_color"
            r11 = 0
            int r10 = r1.getInt(r10, r11)
            java.lang.String r11 = "animate_transition"
            r14 = r30
            boolean r11 = r1.getBoolean(r11, r14)
            java.lang.String r15 = "card_forces_scrim"
            boolean r15 = r1.getBoolean(r15)
            java.util.Set r14 = r0.mCardInfoListeners
            java.util.Iterator r14 = r14.iterator()
        L_0x037a:
            boolean r16 = r14.hasNext()
            if (r16 == 0) goto L_0x038d
            java.lang.Object r16 = r14.next()
            r13 = r16
            com.google.android.systemui.assist.uihints.NgaMessageHandler$CardInfoListener r13 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.CardInfoListener) r13
            r13.onCardInfo(r10, r9, r11, r15)
            r13 = 0
            goto L_0x037a
        L_0x038d:
            r15 = 1
        L_0x038e:
            if (r15 != 0) goto L_0x0665
            if (r8 == 0) goto L_0x0665
            java.util.Set r9 = r0.mKeepAliveListeners
            java.util.Iterator r9 = r9.iterator()
        L_0x0398:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x03b3
            java.lang.Object r10 = r9.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$KeepAliveListener r10 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.KeepAliveListener) r10
            com.google.android.systemui.assist.uihints.TimeoutManager r10 = (com.google.android.systemui.assist.uihints.TimeoutManager) r10
            android.os.Handler r11 = r10.mHandler
            com.google.android.systemui.assist.uihints.TimeoutManager$$ExternalSyntheticLambda0 r10 = r10.mOnTimeout
            r11.removeCallbacks(r10)
            long r13 = com.google.android.systemui.assist.uihints.TimeoutManager.SESSION_TIMEOUT_MS
            r11.postDelayed(r10, r13)
            goto L_0x0398
        L_0x03b3:
            int r9 = r7.hashCode()
            switch(r9) {
                case -2051025175: goto L_0x0424;
                case -2040419289: goto L_0x0419;
                case -1160605116: goto L_0x040e;
                case -241763182: goto L_0x0403;
                case -207201236: goto L_0x03f8;
                case 94631335: goto L_0x03ef;
                case 94746189: goto L_0x03e5;
                case 205422649: goto L_0x03da;
                case 771587807: goto L_0x03d0;
                case 1549039479: goto L_0x03c7;
                case 1642639251: goto L_0x03bc;
                default: goto L_0x03ba;
            }
        L_0x03ba:
            goto L_0x042e
        L_0x03bc:
            java.lang.String r6 = "keep_alive"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 0
            goto L_0x042f
        L_0x03c7:
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 1
            goto L_0x042f
        L_0x03d0:
            java.lang.String r6 = "edge_lights"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 2
            goto L_0x042f
        L_0x03da:
            java.lang.String r6 = "greeting"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = r28
            goto L_0x042f
        L_0x03e5:
            java.lang.String r6 = "clear"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 6
            goto L_0x042f
        L_0x03ef:
            boolean r6 = r7.equals(r3)
            if (r6 == 0) goto L_0x042e
            r13 = r27
            goto L_0x042f
        L_0x03f8:
            java.lang.String r6 = "hide_zerostate"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 10
            goto L_0x042f
        L_0x0403:
            java.lang.String r6 = "transcription"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = r29
            goto L_0x042f
        L_0x040e:
            java.lang.String r6 = "hide_keyboard"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 8
            goto L_0x042f
        L_0x0419:
            java.lang.String r6 = "show_zerostate"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 9
            goto L_0x042f
        L_0x0424:
            java.lang.String r6 = "show_keyboard"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x042e
            r13 = 7
            goto L_0x042f
        L_0x042e:
            r13 = -1
        L_0x042f:
            com.google.android.systemui.assist.uihints.TranscriptionController$State r6 = com.google.android.systemui.assist.uihints.TranscriptionController.State.TRANSCRIPTION
            com.google.android.systemui.assist.uihints.TranscriptionController$State r9 = com.google.android.systemui.assist.uihints.TranscriptionController.State.GREETING
            com.google.android.systemui.assist.uihints.TranscriptionController$State r10 = com.google.android.systemui.assist.uihints.TranscriptionController.State.NONE
            java.util.Set r15 = r0.mKeyboardInfoListeners
            java.util.Set r11 = r0.mZerostateInfoListeners
            java.lang.String r14 = "tap_action"
            switch(r13) {
                case 0: goto L_0x0661;
                case 1: goto L_0x063f;
                case 2: goto L_0x061d;
                case 3: goto L_0x05dd;
                case 4: goto L_0x0575;
                case 5: goto L_0x04f3;
                case 6: goto L_0x04d0;
                case 7: goto L_0x04a6;
                case 8: goto L_0x0488;
                case 9: goto L_0x045e;
                case 10: goto L_0x0440;
                default: goto L_0x043e;
            }
        L_0x043e:
            goto L_0x066c
        L_0x0440:
            java.util.Iterator r0 = r11.iterator()
        L_0x0444:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0661
            java.lang.Object r1 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ZerostateInfoListener r1 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.ZerostateInfoListener) r1
            com.google.android.systemui.assist.uihints.IconController r1 = (com.google.android.systemui.assist.uihints.IconController) r1
            r2 = 0
            r1.mZerostateIconRequested = r2
            r3 = 0
            r1.mOnZerostateIconTap = r3
            com.google.android.systemui.assist.uihints.ZeroStateIconView r3 = r1.mZeroStateIcon
            r1.maybeUpdateIconVisibility(r3, r2)
            goto L_0x0444
        L_0x045e:
            android.os.Parcelable r0 = r1.getParcelable(r14)
            android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            java.util.Iterator r1 = r11.iterator()
        L_0x0468:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0661
            java.lang.Object r2 = r1.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ZerostateInfoListener r2 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.ZerostateInfoListener) r2
            com.google.android.systemui.assist.uihints.IconController r2 = (com.google.android.systemui.assist.uihints.IconController) r2
            r2.getClass()
            if (r0 == 0) goto L_0x047d
            r15 = 1
            goto L_0x047e
        L_0x047d:
            r15 = 0
        L_0x047e:
            r2.mZerostateIconRequested = r15
            r2.mOnZerostateIconTap = r0
            com.google.android.systemui.assist.uihints.ZeroStateIconView r3 = r2.mZeroStateIcon
            r2.maybeUpdateIconVisibility(r3, r15)
            goto L_0x0468
        L_0x0488:
            java.util.Iterator r0 = r15.iterator()
        L_0x048c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0661
            java.lang.Object r1 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$KeyboardInfoListener r1 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.KeyboardInfoListener) r1
            com.google.android.systemui.assist.uihints.IconController r1 = (com.google.android.systemui.assist.uihints.IconController) r1
            r2 = 0
            r1.mKeyboardIconRequested = r2
            r3 = 0
            r1.mOnKeyboardIconTap = r3
            com.google.android.systemui.assist.uihints.KeyboardIconView r3 = r1.mKeyboardIcon
            r1.maybeUpdateIconVisibility(r3, r2)
            goto L_0x048c
        L_0x04a6:
            android.os.Parcelable r0 = r1.getParcelable(r14)
            android.app.PendingIntent r0 = (android.app.PendingIntent) r0
            java.util.Iterator r1 = r15.iterator()
        L_0x04b0:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0661
            java.lang.Object r2 = r1.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$KeyboardInfoListener r2 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.KeyboardInfoListener) r2
            com.google.android.systemui.assist.uihints.IconController r2 = (com.google.android.systemui.assist.uihints.IconController) r2
            r2.getClass()
            if (r0 == 0) goto L_0x04c5
            r15 = 1
            goto L_0x04c6
        L_0x04c5:
            r15 = 0
        L_0x04c6:
            r2.mKeyboardIconRequested = r15
            r2.mOnKeyboardIconTap = r0
            com.google.android.systemui.assist.uihints.KeyboardIconView r3 = r2.mKeyboardIcon
            r2.maybeUpdateIconVisibility(r3, r15)
            goto L_0x04b0
        L_0x04d0:
            java.lang.String r2 = "show_animation"
            r3 = 1
            boolean r1 = r1.getBoolean(r2, r3)
            java.util.Set r0 = r0.mClearListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x04dd:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0661
            java.lang.Object r2 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ClearListener r2 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.ClearListener) r2
            com.google.android.systemui.assist.uihints.TranscriptionController r2 = (com.google.android.systemui.assist.uihints.TranscriptionController) r2
            r3 = 0
            r2.setQueuedState(r10, r1, r3)
            r2.maybeSetState()
            goto L_0x04dd
        L_0x04f3:
            java.util.ArrayList r1 = r1.getParcelableArrayList(r3)
            java.util.Set r0 = r0.mChipsInfoListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x04fd:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0661
            java.lang.Object r2 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$ChipsInfoListener r2 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.ChipsInfoListener) r2
            com.google.android.systemui.assist.uihints.TranscriptionController r2 = (com.google.android.systemui.assist.uihints.TranscriptionController) r2
            r2.getClass()
            if (r1 == 0) goto L_0x0516
            int r3 = r1.size()
            if (r3 != 0) goto L_0x0518
        L_0x0516:
            r3 = 0
            goto L_0x0566
        L_0x0518:
            com.google.android.systemui.assist.uihints.FlingVelocityWrapper r3 = r2.mFlingVelocity
            boolean r4 = r3.mGuarded
            if (r4 == 0) goto L_0x0523
            java.util.Optional r3 = java.util.Optional.empty()
            goto L_0x0530
        L_0x0523:
            r4 = 1
            r3.mGuarded = r4
            float r3 = r3.mVelocity
            java.lang.Float r3 = java.lang.Float.valueOf(r3)
            java.util.Optional r3 = java.util.Optional.of(r3)
        L_0x0530:
            com.google.android.systemui.assist.uihints.TranscriptionController$State r4 = r2.mCurrentState
            com.google.android.systemui.assist.uihints.TranscriptionController$State r5 = com.google.android.systemui.assist.uihints.TranscriptionController.State.CHIPS
            if (r4 != r10) goto L_0x0547
            boolean r4 = r3.isPresent()
            if (r4 == 0) goto L_0x0547
            com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda3 r4 = new com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda3
            r7 = 1
            r4.<init>(r2, r1, r3, r7)
            r3 = 0
            r2.setQueuedState(r5, r3, r4)
            goto L_0x0561
        L_0x0547:
            r3 = 0
            r7 = 1
            com.google.android.systemui.assist.uihints.TranscriptionController$State r4 = r2.mCurrentState
            if (r4 == r9) goto L_0x0559
            if (r4 != r6) goto L_0x0550
            goto L_0x0559
        L_0x0550:
            com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda4 r4 = new com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda4
            r4.<init>(r2, r1, r7)
            r2.setQueuedState(r5, r3, r4)
            goto L_0x0561
        L_0x0559:
            com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda4 r4 = new com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda4
            r4.<init>(r2, r1, r3)
            r2.setQueuedState(r5, r3, r4)
        L_0x0561:
            r2.maybeSetState()
            r4 = 0
            goto L_0x04fd
        L_0x0566:
            java.lang.String r4 = "TranscriptionController"
            java.lang.String r5 = "Null or empty chip list received; clearing transcription space"
            android.util.Log.e(r4, r5)
            r4 = 0
            r2.setQueuedState(r10, r3, r4)
            r2.maybeSetState()
            goto L_0x04fd
        L_0x0575:
            java.lang.String r2 = r1.getString(r5)
            android.os.Parcelable r1 = r1.getParcelable(r14)
            android.app.PendingIntent r1 = (android.app.PendingIntent) r1
            java.util.Set r0 = r0.mGreetingInfoListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x0585:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0661
            java.lang.Object r3 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$GreetingInfoListener r3 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.GreetingInfoListener) r3
            com.google.android.systemui.assist.uihints.TranscriptionController r3 = (com.google.android.systemui.assist.uihints.TranscriptionController) r3
            r3.getClass()
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L_0x05da
            r3.mOnGreetingTap = r1
            com.google.android.systemui.assist.uihints.FlingVelocityWrapper r4 = r3.mFlingVelocity
            boolean r5 = r4.mGuarded
            if (r5 == 0) goto L_0x05aa
            java.util.Optional r4 = java.util.Optional.empty()
            r5 = 1
            goto L_0x05b7
        L_0x05aa:
            r5 = 1
            r4.mGuarded = r5
            float r4 = r4.mVelocity
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
            java.util.Optional r4 = java.util.Optional.of(r4)
        L_0x05b7:
            com.google.android.systemui.assist.uihints.TranscriptionController$State r6 = r3.mCurrentState
            if (r6 != r10) goto L_0x05cc
            boolean r6 = r4.isPresent()
            if (r6 == 0) goto L_0x05cc
            com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda3 r6 = new com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda3
            r7 = 0
            r6.<init>(r3, r2, r4, r7)
            r3.setQueuedState(r9, r7, r6)
            r6 = 1
            goto L_0x05d6
        L_0x05cc:
            r7 = 0
            com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda2 r4 = new com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda2
            r6 = 1
            r4.<init>(r3, r2, r6)
            r3.setQueuedState(r9, r7, r4)
        L_0x05d6:
            r3.maybeSetState()
            goto L_0x0585
        L_0x05da:
            r5 = 1
            r6 = 1
            goto L_0x0585
        L_0x05dd:
            java.lang.String r2 = r1.getString(r5)
            android.os.Parcelable r3 = r1.getParcelable(r14)
            android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            java.lang.String r4 = "text_color"
            int r1 = r1.getInt(r4)
            java.util.Set r0 = r0.mTranscriptionInfoListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x05f3:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0661
            java.lang.Object r4 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$TranscriptionInfoListener r4 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.TranscriptionInfoListener) r4
            com.google.android.systemui.assist.uihints.TranscriptionController r4 = (com.google.android.systemui.assist.uihints.TranscriptionController) r4
            r4.mOnTranscriptionTap = r3
            com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda2 r5 = new com.google.android.systemui.assist.uihints.TranscriptionController$$ExternalSyntheticLambda2
            r7 = 0
            r5.<init>(r4, r2, r7)
            r4.setQueuedState(r6, r7, r5)
            r4.maybeSetState()
            java.util.Map r4 = r4.mViewMap
            java.lang.Object r4 = r4.get(r6)
            com.google.android.systemui.assist.uihints.TranscriptionView r4 = (com.google.android.systemui.assist.uihints.TranscriptionView) r4
            r4.mRequestedTextColor = r1
            r4.updateColor()
            goto L_0x05f3
        L_0x061d:
            java.lang.String r3 = "state"
            java.lang.String r2 = r1.getString(r3, r2)
            java.lang.String r3 = "listening"
            boolean r1 = r1.getBoolean(r3)
            java.util.Set r0 = r0.mEdgeLightsInfoListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x062f:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0661
            java.lang.Object r3 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$EdgeLightsInfoListener r3 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.EdgeLightsInfoListener) r3
            r3.onEdgeLightsInfo(r2, r1)
            goto L_0x062f
        L_0x063f:
            java.lang.String r2 = "volume"
            float r2 = r1.getFloat(r2)
            java.lang.String r3 = "speech_confidence"
            float r1 = r1.getFloat(r3)
            java.util.Set r0 = r0.mAudioInfoListeners
            java.util.Iterator r0 = r0.iterator()
        L_0x0651:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0661
            java.lang.Object r3 = r0.next()
            com.google.android.systemui.assist.uihints.NgaMessageHandler$AudioInfoListener r3 = (com.google.android.systemui.assist.uihints.NgaMessageHandler.AudioInfoListener) r3
            r3.onAudioInfo(r2, r1)
            goto L_0x0651
        L_0x0661:
            r12.refresh$2()
            goto L_0x0682
        L_0x0665:
            r14 = r15
            goto L_0x066a
        L_0x0667:
            r31 = r9
            r4 = r10
        L_0x066a:
            if (r14 != 0) goto L_0x0682
        L_0x066c:
            java.lang.String r0 = "Invalid action \""
            java.lang.String r1 = "\" for state:\n  NGA is Assistant = "
            java.lang.String r2 = "\n  SysUI is NGA UI = "
            java.lang.StringBuilder r0 = com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m(r0, r7, r1, r4, r2)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            r1 = r31
            android.util.Log.w(r1, r0)
        L_0x0682:
            r36.run()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.NgaMessageHandler.processBundle(android.os.Bundle, java.lang.Runnable):void");
    }
}
