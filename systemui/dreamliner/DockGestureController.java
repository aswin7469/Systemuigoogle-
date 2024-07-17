package com.google.android.systemui.dreamliner;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.animation.PhysicsAnimator;
import com.google.android.systemui.dreamliner.DockObserver;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class DockGestureController extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener, StatusBarStateController.StateListener, KeyguardStateController.Callback, ConfigurationController.ConfigurationListener {
    public static final long GEAR_VISIBLE_TIME_MILLIS;
    public static final long PREVIEW_DELAY_MILLIS;
    public final AccessibilityManager mAccessibilityManager;
    public final DockObserver.ProtectedBroadcastSender mBroadcastSender;
    public final Context mContext;
    public float mDiffX;
    public final DockIndicationController mDockIndicationController;
    public float mFirstTouchX;
    public float mFirstTouchY;
    public boolean mFromRight;
    GestureDetector mGestureDetector;
    public final DockGestureController$$ExternalSyntheticLambda0 mHideGearRunnable;
    public final AnonymousClass1 mKeyguardMonitorCallback = new KeyguardStateController.Callback() {
        public final void onKeyguardShowingChanged() {
            DockGestureController dockGestureController = DockGestureController.this;
            if (((KeyguardStateControllerImpl) dockGestureController.mKeyguardStateController).mOccluded) {
                dockGestureController.hidePhotoPreview(false);
            }
        }
    };
    public final KeyguardStateController mKeyguardStateController;
    public float mLastTouchX;
    public boolean mLaunchedPhoto;
    public final int mPhotoDiffThreshold;
    public boolean mPhotoEnabled;
    public final FrameLayout mPhotoPreview;
    public final TextView mPhotoPreviewText;
    public final PhysicsAnimator mPreviewTargetAnimator;
    public final ImageView mSettingsGear;
    public boolean mShouldConsumeTouch;
    public final DreamlinerA11yAction mShowPhotoFrameA11yAction;
    public final StatusBarStateController mStatusBarStateController;
    public PendingIntent mTapAction;
    public final PhysicsAnimator.SpringConfig mTargetSpringConfig = new PhysicsAnimator.SpringConfig(1500.0f, 1.0f);
    public final View mTouchDelegateView;
    public boolean mTriggerPhoto;
    public VelocityTracker mVelocityTracker;
    public float mVelocityX;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        GEAR_VISIBLE_TIME_MILLIS = timeUnit.toMillis(15);
        PREVIEW_DELAY_MILLIS = timeUnit.toMillis(1);
    }

    public DockGestureController(Context context, DockObserver.ProtectedBroadcastSender protectedBroadcastSender, ImageView imageView, FrameLayout frameLayout, View view, DockIndicationController dockIndicationController, StatusBarStateController statusBarStateController, KeyguardStateController keyguardStateController) {
        this.mDockIndicationController = dockIndicationController;
        this.mContext = context;
        this.mBroadcastSender = protectedBroadcastSender;
        this.mHideGearRunnable = new DockGestureController$$ExternalSyntheticLambda0(this, 0);
        this.mGestureDetector = new GestureDetector(context, this);
        this.mTouchDelegateView = view;
        this.mSettingsGear = imageView;
        this.mPhotoPreview = frameLayout;
        TextView textView = (TextView) frameLayout.findViewById(2131363301);
        this.mPhotoPreviewText = textView;
        textView.setText(context.getResources().getString(2131952473));
        imageView.setOnClickListener(new DockGestureController$$ExternalSyntheticLambda1(this));
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mPhotoDiffThreshold = context.getResources().getDimensionPixelSize(2131165829);
        this.mStatusBarStateController = statusBarStateController;
        this.mKeyguardStateController = keyguardStateController;
        Function1 function1 = PhysicsAnimator.instanceConstructor;
        this.mPreviewTargetAnimator = PhysicsAnimator.Companion.getInstance(frameLayout);
        this.mShowPhotoFrameA11yAction = new DreamlinerA11yAction(context.getString(2131952463), frameLayout.getRootView(), new DockGestureController$$ExternalSyntheticLambda0(this, 1));
    }

    public final void hideGear() {
        if (this.mSettingsGear.isVisibleToUser() || this.mSettingsGear.getVisibility() == 0) {
            this.mSettingsGear.removeCallbacks(this.mHideGearRunnable);
            this.mSettingsGear.animate().setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN).alpha(0.0f).withEndAction(new DockGestureController$$ExternalSyntheticLambda0(this, 6)).start();
        }
    }

    public final void hidePhotoPreview(boolean z) {
        if (this.mPhotoPreview.getVisibility() == 0) {
            if (z) {
                this.mPhotoPreview.post(new DockGestureController$$ExternalSyntheticLambda0(this, 3));
                return;
            }
            this.mPhotoPreview.setAlpha(0.0f);
            this.mPhotoPreview.setVisibility(4);
        }
    }

    public final boolean onDown(MotionEvent motionEvent) {
        sendProtectedBroadcast(new Intent("com.google.android.systemui.dreamliner.TOUCH_EVENT"));
        return false;
    }

    /* JADX WARNING: type inference failed for: r8v5, types: [java.lang.Object, java.util.function.Supplier] */
    public final void onDozingChanged(boolean z) {
        if (z) {
            this.mTouchDelegateView.setOnTouchListener(this);
            showGear();
            DreamlinerA11yAction dreamlinerA11yAction = this.mShowPhotoFrameA11yAction;
            int i = dreamlinerA11yAction.mActionId;
            String str = dreamlinerA11yAction.mActionLabel;
            if (i != -1) {
                Log.d("DreamlnierA11yAction", "enable action: " + str + " already enabled, skip enable");
                return;
            }
            Log.d("DreamlnierA11yAction", "enable action: " + str);
            DreamlinerA11yAction$$ExternalSyntheticLambda0 dreamlinerA11yAction$$ExternalSyntheticLambda0 = new DreamlinerA11yAction$$ExternalSyntheticLambda0(dreamlinerA11yAction);
            dreamlinerA11yAction.mActionWrapper.getClass();
            dreamlinerA11yAction.mActionId = ViewCompat.addAccessibilityAction(dreamlinerA11yAction.mViewForAction, str, dreamlinerA11yAction$$ExternalSyntheticLambda0);
            return;
        }
        this.mTouchDelegateView.setOnTouchListener((View.OnTouchListener) null);
        hideGear();
        boolean z2 = true;
        if (!this.mLaunchedPhoto) {
            hidePhotoPreview(true);
        } else {
            this.mPhotoPreview.postDelayed(new DockGestureController$$ExternalSyntheticLambda0(this, 4), PREVIEW_DELAY_MILLIS);
        }
        DreamlinerA11yAction dreamlinerA11yAction2 = this.mShowPhotoFrameA11yAction;
        int i2 = dreamlinerA11yAction2.mActionId;
        View view = dreamlinerA11yAction2.mViewForAction;
        String str2 = dreamlinerA11yAction2.mActionLabel;
        if (i2 == -1) {
            StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("disable action: ", str2, " already disable, skip disable(actionId:");
            m.append(dreamlinerA11yAction2.mActionId);
            m.append(", viewForAction==null:");
            if (view != null) {
                z2 = false;
            }
            m.append(z2);
            m.append(")");
            Log.d("DreamlnierA11yAction", m.toString());
            return;
        }
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("disable action: ", str2, "DreamlnierA11yAction");
        int i3 = dreamlinerA11yAction2.mActionId;
        dreamlinerA11yAction2.mActionWrapper.getClass();
        ViewCompat.removeAccessibilityAction(view, i3);
        dreamlinerA11yAction2.mActionId = -1;
        ((Runnable) Optional.ofNullable((Object) null).orElseGet(new Object())).run();
    }

    public final void onLocaleListChanged() {
        this.mPhotoPreviewText.setText(this.mContext.getResources().getString(2131952473));
    }

    public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        PendingIntent pendingIntent = this.mTapAction;
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.w("DLGestureController", "Tap action pending intent cancelled", e);
            }
        }
        showGear();
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x0130  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(android.view.View r10, android.view.MotionEvent r11) {
        /*
            r9 = this;
            android.view.VelocityTracker r10 = r9.mVelocityTracker
            if (r10 != 0) goto L_0x000a
            android.view.VelocityTracker r10 = android.view.VelocityTracker.obtain()
            r9.mVelocityTracker = r10
        L_0x000a:
            float r10 = r11.getX()
            int r0 = r11.getActionMasked()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x00c2
            if (r0 == r2) goto L_0x0065
            if (r0 == r1) goto L_0x001d
            goto L_0x013c
        L_0x001d:
            boolean r0 = r9.mFromRight
            if (r0 == 0) goto L_0x013c
            boolean r0 = r9.mPhotoEnabled
            if (r0 != 0) goto L_0x0027
            goto L_0x013c
        L_0x0027:
            float r0 = r11.getX()
            float r1 = r9.mLastTouchX
            float r3 = r0 - r1
            android.widget.FrameLayout r4 = r9.mPhotoPreview
            float r1 = r1 + r3
            r4.setTranslationX(r1)
            android.view.VelocityTracker r1 = r9.mVelocityTracker
            r1.addMovement(r11)
            float r1 = r9.mFirstTouchX
            float r1 = r1 - r0
            r9.mDiffX = r1
            float r0 = r9.mFirstTouchY
            float r1 = r11.getY()
            float r0 = r0 - r1
            float r1 = r9.mDiffX
            float r1 = java.lang.Math.abs(r1)
            float r0 = java.lang.Math.abs(r0)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x013c
            float r0 = r9.mDiffX
            float r0 = java.lang.Math.abs(r0)
            int r1 = r9.mPhotoDiffThreshold
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x013c
            r9.mTriggerPhoto = r2
            goto L_0x013c
        L_0x0065:
            android.view.VelocityTracker r0 = r9.mVelocityTracker
            r1 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r1)
            android.view.VelocityTracker r0 = r9.mVelocityTracker
            float r0 = r0.getXVelocity()
            r9.mVelocityX = r0
            float r0 = r9.mDiffX
            float r0 = java.lang.Math.signum(r0)
            float r1 = r9.mVelocityX
            float r1 = java.lang.Math.signum(r1)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0094
            float r0 = r9.mLastTouchX
            android.widget.FrameLayout r1 = r9.mPhotoPreview
            int r1 = r1.getRight()
            int r4 = r9.mPhotoDiffThreshold
            int r1 = r1 - r4
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L_0x0096
        L_0x0094:
            r9.mTriggerPhoto = r3
        L_0x0096:
            boolean r0 = r9.mTriggerPhoto
            if (r0 == 0) goto L_0x00bd
            android.widget.FrameLayout r0 = r9.mPhotoPreview
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x00bd
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.android.systemui.dreamliner.PHOTO_EVENT"
            r0.<init>(r1)
            r9.sendProtectedBroadcast(r0)
            android.widget.FrameLayout r0 = r9.mPhotoPreview
            com.google.android.systemui.dreamliner.DockGestureController$$ExternalSyntheticLambda0 r1 = new com.google.android.systemui.dreamliner.DockGestureController$$ExternalSyntheticLambda0
            r4 = 2
            r1.<init>(r9, r4)
            r0.post(r1)
            r9.mLaunchedPhoto = r2
            r9.mTriggerPhoto = r3
            goto L_0x013c
        L_0x00bd:
            r9.hidePhotoPreview(r2)
            goto L_0x013c
        L_0x00c2:
            android.view.VelocityTracker r0 = r9.mVelocityTracker
            r0.clear()
            r9.mFirstTouchX = r10
            float r0 = r11.getY()
            r9.mFirstTouchY = r0
            r9.mLaunchedPhoto = r3
            r9.mFromRight = r3
            com.google.android.systemui.dreamliner.DockIndicationController r0 = r9.mDockIndicationController
            if (r0 == 0) goto L_0x0116
            android.widget.ImageView r4 = r0.mDockedTopIcon
            if (r4 != 0) goto L_0x00dc
            goto L_0x0116
        L_0x00dc:
            int[] r1 = new int[r1]
            r4.getLocationOnScreen(r1)
            android.graphics.RectF r4 = new android.graphics.RectF
            r5 = r1[r3]
            float r6 = (float) r5
            r7 = r1[r2]
            float r7 = (float) r7
            android.widget.ImageView r8 = r0.mDockedTopIcon
            int r8 = r8.getWidth()
            int r8 = r8 + r5
            float r5 = (float) r8
            r1 = r1[r2]
            android.widget.ImageView r0 = r0.mDockedTopIcon
            int r0 = r0.getHeight()
            int r0 = r0 + r1
            float r0 = (float) r0
            r4.<init>(r6, r7, r5, r0)
            float r0 = r11.getRawX()
            float r1 = r11.getRawY()
            boolean r0 = r4.contains(r0, r1)
            java.lang.String r1 = "dockedTopIcon touched="
            java.lang.String r4 = "DLIndicator"
            com.android.settingslib.Utils$$ExternalSyntheticOutline0.m(r1, r4, r0)
            if (r0 != 0) goto L_0x0114
            goto L_0x0116
        L_0x0114:
            r0 = r3
            goto L_0x0117
        L_0x0116:
            r0 = r2
        L_0x0117:
            r9.mShouldConsumeTouch = r0
            if (r0 != 0) goto L_0x011c
            goto L_0x013c
        L_0x011c:
            android.widget.FrameLayout r0 = r9.mPhotoPreview
            int r0 = r0.getRight()
            int r1 = r9.mPhotoDiffThreshold
            int r0 = r0 - r1
            float r0 = (float) r0
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x013c
            r9.mFromRight = r2
            boolean r0 = r9.mPhotoEnabled
            if (r0 == 0) goto L_0x013c
            android.widget.FrameLayout r0 = r9.mPhotoPreview
            r0.setVisibility(r3)
            android.widget.FrameLayout r0 = r9.mPhotoPreview
            r1 = 100
            com.android.systemui.statusbar.CrossFadeHelper.fadeIn((android.view.View) r0, (long) r1, (int) r3)
        L_0x013c:
            r9.mLastTouchX = r10
            boolean r10 = r9.mShouldConsumeTouch
            if (r10 == 0) goto L_0x0147
            android.view.GestureDetector r10 = r9.mGestureDetector
            r10.onTouchEvent(r11)
        L_0x0147:
            boolean r9 = r9.mShouldConsumeTouch
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockGestureController.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void sendProtectedBroadcast(Intent intent) {
        try {
            this.mBroadcastSender.sendBroadcastAsUser(intent, UserHandle.ALL);
        } catch (SecurityException e) {
            Log.w("DLGestureController", "Cannot send event", e);
        }
    }

    public final void showGear() {
        long j;
        if (this.mTapAction == null) {
            if (!this.mSettingsGear.isVisibleToUser()) {
                this.mSettingsGear.setVisibility(0);
                this.mSettingsGear.animate().setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN).alpha(1.0f).start();
            }
            this.mSettingsGear.removeCallbacks(this.mHideGearRunnable);
            ImageView imageView = this.mSettingsGear;
            DockGestureController$$ExternalSyntheticLambda0 dockGestureController$$ExternalSyntheticLambda0 = this.mHideGearRunnable;
            AccessibilityManager accessibilityManager = this.mAccessibilityManager;
            if (accessibilityManager == null) {
                j = GEAR_VISIBLE_TIME_MILLIS;
            } else {
                j = (long) accessibilityManager.getRecommendedTimeoutMillis(Math.toIntExact(GEAR_VISIBLE_TIME_MILLIS), 5);
            }
            imageView.postDelayed(dockGestureController$$ExternalSyntheticLambda0, j);
        }
    }

    public final void stopMonitoring() {
        this.mStatusBarStateController.removeCallback(this);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).removeCallback(this.mKeyguardMonitorCallback);
        onDozingChanged(false);
        this.mSettingsGear.setVisibility(8);
    }
}
