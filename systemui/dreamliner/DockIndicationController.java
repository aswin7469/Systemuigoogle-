package com.google.android.systemui.dreamliner;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.ui.view.WindowRootView;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.KeyguardIndicationTextView;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DockIndicationController implements StatusBarStateController.StateListener, View.OnClickListener, View.OnAttachStateChangeListener, ConfigurationController.ConfigurationListener {
    static final String ACTION_ASSISTANT_POODLE = "com.google.android.systemui.dreamliner.ASSISTANT_POODLE";
    public static final long KEYGUARD_INDICATION_TIMEOUT_MILLIS;
    public static final long PROMO_SHOWING_TIME_MILLIS;
    public final AccessibilityManager mAccessibilityManager;
    public LinearLayout mAmbientIndicationContainer;
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    public final DockIndicationController$$ExternalSyntheticLambda0 mDisableLiveRegionRunnable;
    FrameLayout mDockPromo;
    ImageView mDockedTopIcon;
    public boolean mDocking;
    public boolean mDozing;
    public final Animation mHidePromoAnimation;
    public final DockIndicationController$$ExternalSyntheticLambda0 mHidePromoRunnable;
    boolean mIconViewsValidated;
    public final KeyguardIndicationController mKeyguardIndicationController;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public TextView mPromoText;
    public boolean mShowPromo;
    public final Animation mShowPromoAnimation;
    public int mShowPromoTimes;
    public int mStatusBarState;
    public boolean mTopIconShowing;
    public KeyguardIndicationTextView mTopIndicationView;
    public boolean mViewAttached = true;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        PROMO_SHOWING_TIME_MILLIS = timeUnit.toMillis(2);
        KEYGUARD_INDICATION_TIMEOUT_MILLIS = timeUnit.toMillis(15);
    }

    public DockIndicationController(Context context, BroadcastSender broadcastSender, KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle, SysuiStatusBarStateController sysuiStatusBarStateController, NotificationShadeWindowController notificationShadeWindowController) {
        this.mContext = context;
        this.mBroadcastSender = broadcastSender;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mKeyguardIndicationController = keyguardIndicationControllerGoogle;
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).addCallback((StatusBarStateController.StateListener) this);
        this.mHidePromoRunnable = new DockIndicationController$$ExternalSyntheticLambda0(this, 0);
        this.mDisableLiveRegionRunnable = new DockIndicationController$$ExternalSyntheticLambda0(this, 1);
        Animation loadAnimation = AnimationUtils.loadAnimation(context, 2130772502);
        this.mShowPromoAnimation = loadAnimation;
        loadAnimation.setAnimationListener(new PhotoAnimationListener(this, 0) {
            public final /* synthetic */ DockIndicationController this$0;

            {
                this.this$0 = r1;
            }

            public final void onAnimationEnd(Animation animation) {
                switch (1) {
                    case 0:
                        DockIndicationController dockIndicationController = this.this$0;
                        FrameLayout frameLayout = dockIndicationController.mDockPromo;
                        DockIndicationController$$ExternalSyntheticLambda0 dockIndicationController$$ExternalSyntheticLambda0 = dockIndicationController.mHidePromoRunnable;
                        long j = DockIndicationController.PROMO_SHOWING_TIME_MILLIS;
                        AccessibilityManager accessibilityManager = dockIndicationController.mAccessibilityManager;
                        if (accessibilityManager != null) {
                            j = (long) accessibilityManager.getRecommendedTimeoutMillis(Math.toIntExact(j), 2);
                        }
                        frameLayout.postDelayed(dockIndicationController$$ExternalSyntheticLambda0, j);
                        return;
                    default:
                        DockIndicationController dockIndicationController2 = this.this$0;
                        if (dockIndicationController2.mShowPromoTimes < 3) {
                            dockIndicationController2.showPromoInner();
                            return;
                        }
                        dockIndicationController2.mKeyguardIndicationController.setVisible(true);
                        this.this$0.mDockPromo.setVisibility(8);
                        return;
                }
            }
        });
        Animation loadAnimation2 = AnimationUtils.loadAnimation(context, 2130772503);
        this.mHidePromoAnimation = loadAnimation2;
        loadAnimation2.setAnimationListener(new PhotoAnimationListener(this, 1) {
            public final /* synthetic */ DockIndicationController this$0;

            {
                this.this$0 = r1;
            }

            public final void onAnimationEnd(Animation animation) {
                switch (1) {
                    case 0:
                        DockIndicationController dockIndicationController = this.this$0;
                        FrameLayout frameLayout = dockIndicationController.mDockPromo;
                        DockIndicationController$$ExternalSyntheticLambda0 dockIndicationController$$ExternalSyntheticLambda0 = dockIndicationController.mHidePromoRunnable;
                        long j = DockIndicationController.PROMO_SHOWING_TIME_MILLIS;
                        AccessibilityManager accessibilityManager = dockIndicationController.mAccessibilityManager;
                        if (accessibilityManager != null) {
                            j = (long) accessibilityManager.getRecommendedTimeoutMillis(Math.toIntExact(j), 2);
                        }
                        frameLayout.postDelayed(dockIndicationController$$ExternalSyntheticLambda0, j);
                        return;
                    default:
                        DockIndicationController dockIndicationController2 = this.this$0;
                        if (dockIndicationController2.mShowPromoTimes < 3) {
                            dockIndicationController2.showPromoInner();
                            return;
                        }
                        dockIndicationController2.mKeyguardIndicationController.setVisible(true);
                        this.this$0.mDockPromo.setVisibility(8);
                        return;
                }
            }
        });
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    public void initializeIconViews() {
        if (this.mViewAttached) {
            WindowRootView windowRootView = ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).mWindowRootView;
            ImageView imageView = (ImageView) windowRootView.findViewById(2131362443);
            this.mDockedTopIcon = imageView;
            imageView.setImageResource(2131232447);
            this.mDockedTopIcon.setContentDescription(this.mContext.getString(2131951680));
            this.mDockedTopIcon.setTooltipText(this.mContext.getString(2131951680));
            this.mDockedTopIcon.setOnClickListener(this);
            this.mDockPromo = (FrameLayout) windowRootView.findViewById(2131362440);
            TextView textView = (TextView) windowRootView.findViewById(2131363275);
            this.mPromoText = textView;
            textView.setAutoSizeTextTypeUniformWithConfiguration(10, 16, 1, 2);
            windowRootView.findViewById(2131361961).addOnAttachStateChangeListener(this);
            this.mTopIndicationView = (KeyguardIndicationTextView) windowRootView.findViewById(2131362787);
            this.mAmbientIndicationContainer = (LinearLayout) windowRootView.findViewById(2131361964);
            this.mIconViewsValidated = true;
            return;
        }
        throw new IllegalStateException("Cannot init icon views when view isn't attached");
    }

    public final void onClick(View view) {
        if (view.getId() == 2131362443) {
            Intent intent = new Intent(ACTION_ASSISTANT_POODLE);
            intent.addFlags(1073741824);
            try {
                this.mBroadcastSender.sendBroadcastAsUser(intent, UserHandle.ALL);
            } catch (SecurityException e) {
                Log.w("DLIndicator", "Cannot send event for intent= " + intent, e);
            }
        }
    }

    public final void onDozingChanged(boolean z) {
        this.mDozing = z;
        updateVisibility$8();
        updateLiveRegionIfNeeded();
        if (!this.mDozing) {
            this.mShowPromo = false;
        } else {
            showPromoInner();
        }
    }

    public final void onLocaleListChanged() {
        if (!this.mIconViewsValidated && this.mViewAttached) {
            initializeIconViews();
        }
        this.mPromoText.setText(this.mContext.getResources().getString(2131952453));
    }

    public final void onStateChanged(int i) {
        this.mStatusBarState = i;
        updateVisibility$8();
    }

    public final void onViewAttachedToWindow(View view) {
        this.mViewAttached = true;
        updateVisibility$8();
    }

    public final void onViewDetachedFromWindow(View view) {
        this.mViewAttached = false;
        view.removeOnAttachStateChangeListener(this);
        this.mIconViewsValidated = false;
        this.mDockedTopIcon = null;
    }

    public final void showPromoInner() {
        if (this.mDozing && this.mDocking && this.mShowPromo) {
            this.mKeyguardIndicationController.setVisible(false);
            this.mDockPromo.setVisibility(0);
            this.mDockPromo.startAnimation(this.mShowPromoAnimation);
            this.mShowPromoTimes++;
        }
    }

    public final void updateLiveRegionIfNeeded() {
        int accessibilityLiveRegion = this.mTopIndicationView.getAccessibilityLiveRegion();
        if (this.mDozing && this.mDocking) {
            this.mTopIndicationView.removeCallbacks(this.mDisableLiveRegionRunnable);
            KeyguardIndicationTextView keyguardIndicationTextView = this.mTopIndicationView;
            DockIndicationController$$ExternalSyntheticLambda0 dockIndicationController$$ExternalSyntheticLambda0 = this.mDisableLiveRegionRunnable;
            long j = KEYGUARD_INDICATION_TIMEOUT_MILLIS;
            AccessibilityManager accessibilityManager = this.mAccessibilityManager;
            if (accessibilityManager != null) {
                j = (long) accessibilityManager.getRecommendedTimeoutMillis(Math.toIntExact(j), 2);
            }
            keyguardIndicationTextView.postDelayed(dockIndicationController$$ExternalSyntheticLambda0, j);
        } else if (accessibilityLiveRegion != 1) {
            this.mTopIndicationView.setAccessibilityLiveRegion(1);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void updateVisibility$8() {
        /*
            r5 = this;
            boolean r0 = r5.mViewAttached
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r5.mIconViewsValidated
            if (r0 != 0) goto L_0x000c
            r5.initializeIconViews()
        L_0x000c:
            boolean r0 = r5.mDozing
            r1 = 1
            r2 = 8
            r3 = 0
            if (r0 == 0) goto L_0x002a
            boolean r0 = r5.mDocking
            if (r0 != 0) goto L_0x0019
            goto L_0x002a
        L_0x0019:
            boolean r0 = r5.mTopIconShowing
            if (r0 != 0) goto L_0x0024
            android.widget.ImageView r0 = r5.mDockedTopIcon
            r0.setVisibility(r2)
        L_0x0022:
            r1 = r3
            goto L_0x0043
        L_0x0024:
            android.widget.ImageView r0 = r5.mDockedTopIcon
            r0.setVisibility(r3)
            goto L_0x0043
        L_0x002a:
            android.widget.FrameLayout r0 = r5.mDockPromo
            r0.setVisibility(r2)
            android.widget.ImageView r0 = r5.mDockedTopIcon
            r0.setVisibility(r2)
            int r0 = r5.mStatusBarState
            if (r0 == r1) goto L_0x003d
            r2 = 2
            if (r0 != r2) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r1 = r3
        L_0x003d:
            com.android.systemui.statusbar.KeyguardIndicationController r0 = r5.mKeyguardIndicationController
            r0.setVisible(r1)
            goto L_0x0022
        L_0x0043:
            android.widget.LinearLayout r0 = r5.mAmbientIndicationContainer
            if (r0 != 0) goto L_0x0048
            goto L_0x007c
        L_0x0048:
            if (r1 == 0) goto L_0x0055
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131165330(0x7f070092, float:1.7944874E38)
            int r3 = r0.getDimensionPixelSize(r1)
        L_0x0055:
            android.widget.LinearLayout r0 = r5.mAmbientIndicationContainer
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            boolean r0 = r0 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r0 == 0) goto L_0x007c
            android.widget.LinearLayout r0 = r5.mAmbientIndicationContainer
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0
            if (r0 == 0) goto L_0x007c
            int r1 = r0.topMargin
            if (r1 != r3) goto L_0x006e
            goto L_0x007c
        L_0x006e:
            int r1 = r0.leftMargin
            int r2 = r0.rightMargin
            int r4 = r0.bottomMargin
            r0.setMargins(r1, r3, r2, r4)
            android.widget.LinearLayout r5 = r5.mAmbientIndicationContainer
            r5.requestLayout()
        L_0x007c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.dreamliner.DockIndicationController.updateVisibility$8():void");
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class PhotoAnimationListener implements Animation.AnimationListener {
        public final void onAnimationRepeat(Animation animation) {
        }

        public final void onAnimationStart(Animation animation) {
        }
    }
}
