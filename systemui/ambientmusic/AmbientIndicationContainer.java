package com.google.android.systemui.ambientmusic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MathUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.data.ViewNode;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.AutoReinflateContainer;
import com.android.systemui.Dependency;
import com.android.systemui.doze.DozeReceiver;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.util.wakelock.DelayedWakeLock;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.systemui.util.wakelock.WakeLockLogger;
import java.util.Objects;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class AmbientIndicationContainer extends AutoReinflateContainer implements DozeReceiver, StatusBarStateController.StateListener, NotificationMediaManager.MediaListener {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ActivityStarter mActivityStarter;
    public Drawable mAmbientIconOverride;
    public ConstraintLayout mAmbientIndicationContainer;
    public int mAmbientIndicationIconSize;
    public Drawable mAmbientMusicAnimation;
    public Drawable mAmbientMusicNoteIcon;
    public int mAmbientMusicNoteIconIconSize;
    public CharSequence mAmbientMusicText;
    public boolean mAmbientSkipUnlock;
    public int mBottomMarginPx;
    public boolean mDozing;
    public PendingIntent mFavoritingIntent;
    public final Handler mHandler;
    public final Rect mIconBounds = new Rect();
    public String mIconDescription;
    public int mIconOverride = -1;
    public ImageView mIconView;
    public int mIndicationTextMode;
    public boolean mInflated;
    public KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public int mMediaPlaybackState;
    public PendingIntent mOpenIntent;
    public PowerInteractor mPowerInteractor;
    public Drawable mReverseChargingAnimation;
    public CharSequence mReverseChargingMessage;
    public ShadeViewController mShadeViewController;
    public int mStatusBarState;
    public int mTextColor;
    public ValueAnimator mTextColorAnimator;
    public TextView mTextView;
    public final WakeLock mWakeLock;
    public CharSequence mWirelessChargingMessage;

    public static void $r8$lambda$DFan0h9JQgIimo3ogLWaY_C9MMU(AmbientIndicationContainer ambientIndicationContainer) {
        ambientIndicationContainer.mTextView = (TextView) ambientIndicationContainer.findViewById(2131361965);
        ambientIndicationContainer.mIconView = (ImageView) ambientIndicationContainer.findViewById(2131361963);
        ambientIndicationContainer.mAmbientIndicationContainer = (ConstraintLayout) ambientIndicationContainer.findViewById(2131361961);
        ConstraintSet constraintSet = new ConstraintSet();
        if (ambientIndicationContainer.mKeyguardUpdateMonitor.mAuthController.isUdfpsSupported()) {
            constraintSet.load(2132213760, ambientIndicationContainer.mContext);
        } else {
            constraintSet.load(2132213761, ambientIndicationContainer.mContext);
        }
        constraintSet.applyTo(ambientIndicationContainer.mAmbientIndicationContainer);
        ambientIndicationContainer.mAmbientMusicAnimation = null;
        ambientIndicationContainer.mAmbientMusicNoteIcon = null;
        ambientIndicationContainer.mReverseChargingAnimation = null;
        ambientIndicationContainer.mTextColor = ambientIndicationContainer.mTextView.getCurrentTextColor();
        ambientIndicationContainer.mAmbientIndicationIconSize = ambientIndicationContainer.getResources().getDimensionPixelSize(2131165331);
        ambientIndicationContainer.mAmbientMusicNoteIconIconSize = ambientIndicationContainer.getResources().getDimensionPixelSize(2131165333);
        ambientIndicationContainer.mTextView.setEnabled(!ambientIndicationContainer.mDozing);
        ambientIndicationContainer.updateColors();
        ambientIndicationContainer.updatePill();
        ambientIndicationContainer.mTextView.setOnClickListener(new AmbientIndicationContainer$$ExternalSyntheticLambda4(ambientIndicationContainer, 0));
        ambientIndicationContainer.mIconView.setOnClickListener(new AmbientIndicationContainer$$ExternalSyntheticLambda4(ambientIndicationContainer, 1));
        ambientIndicationContainer.mInflated = true;
        ambientIndicationContainer.updateBottomSpacing();
    }

    public AmbientIndicationContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        this.mWakeLock = createWakeLock(this.mContext, handler);
    }

    public static void sendBroadcastWithoutDismissingKeyguard(PendingIntent pendingIntent) {
        if (!pendingIntent.isActivity()) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                Log.w("AmbientIndication", "Sending intent failed: " + e);
            }
        }
    }

    public WakeLock createWakeLock(Context context, Handler handler) {
        return new DelayedWakeLock(handler, WakeLock.createPartial(context, (WakeLockLogger) null, "AmbientIndication", 20000));
    }

    public final void dozeTimeTick() {
        updatePill();
    }

    public final void initializeView(ShadeSurface shadeSurface, PowerInteractor powerInteractor, KeyguardUpdateMonitor keyguardUpdateMonitor, ActivityStarter activityStarter) {
        this.mShadeViewController = shadeSurface;
        this.mPowerInteractor = powerInteractor;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mActivityStarter = activityStarter;
        this.mInflateListeners.add(new AmbientIndicationContainer$$ExternalSyntheticLambda2(this));
        getChildAt(0);
        $r8$lambda$DFan0h9JQgIimo3ogLWaY_C9MMU(this);
        addOnLayoutChangeListener(new AmbientIndicationContainer$$ExternalSyntheticLambda3(this));
    }

    public final void onAttachedToWindow() {
        int i;
        PlaybackState playbackState;
        super.onAttachedToWindow();
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).addCallback(this);
        NotificationMediaManager notificationMediaManager = (NotificationMediaManager) Dependency.sDependency.getDependencyInner(NotificationMediaManager.class);
        notificationMediaManager.mMediaListeners.add(this);
        MediaMetadata mediaMetadata = notificationMediaManager.mMediaMetadata;
        MediaController mediaController = notificationMediaManager.mMediaController;
        if (mediaController == null || (playbackState = mediaController.getPlaybackState()) == null) {
            i = 0;
        } else {
            i = playbackState.getState();
        }
        onPrimaryMetadataOrStateChanged(mediaMetadata, i);
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        updateBottomSpacing();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).removeCallback(this);
        ((NotificationMediaManager) Dependency.sDependency.getDependencyInner(NotificationMediaManager.class)).mMediaListeners.remove(this);
        this.mMediaPlaybackState = 0;
    }

    public final void onDozingChanged(boolean z) {
        this.mDozing = z;
        if (this.mStatusBarState == 1) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setEnabled(!z);
            updateColors();
        }
    }

    public final void onPrimaryMetadataOrStateChanged(MediaMetadata mediaMetadata, int i) {
        if (this.mMediaPlaybackState != i) {
            this.mMediaPlaybackState = i;
            if (NotificationMediaManager.isPlayingState(i)) {
                setAmbientMusic((CharSequence) null, (PendingIntent) null, (PendingIntent) null, 0, false, (String) null);
            }
        }
    }

    public final void onStateChanged(int i) {
        this.mStatusBarState = i;
        if (i == 1) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public final void onTextClick() {
        if (this.mOpenIntent != null) {
            this.mPowerInteractor.wakeUpIfDozing(4, "AMBIENT_MUSIC_CLICK");
            if (this.mAmbientSkipUnlock) {
                sendBroadcastWithoutDismissingKeyguard(this.mOpenIntent);
            } else {
                this.mActivityStarter.startPendingIntentDismissingKeyguard(this.mOpenIntent);
            }
        }
    }

    public final void setAmbientMusic(CharSequence charSequence, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, boolean z, String str) {
        Drawable drawable;
        if (!Objects.equals(this.mAmbientMusicText, charSequence) || !Objects.equals(this.mOpenIntent, pendingIntent) || !Objects.equals(this.mFavoritingIntent, pendingIntent2) || this.mIconOverride != i || !Objects.equals(this.mIconDescription, str) || this.mAmbientSkipUnlock != z) {
            this.mAmbientMusicText = charSequence;
            this.mOpenIntent = pendingIntent;
            this.mFavoritingIntent = pendingIntent2;
            this.mAmbientSkipUnlock = z;
            this.mIconOverride = i;
            this.mIconDescription = str;
            Context context = this.mContext;
            switch (i) {
                case 1:
                    drawable = context.getDrawable(2131232942);
                    break;
                case 3:
                    drawable = context.getDrawable(2131232940);
                    break;
                case 4:
                    drawable = context.getDrawable(2131232500);
                    break;
                case 5:
                    drawable = context.getDrawable(2131232668);
                    break;
                case 6:
                    drawable = context.getDrawable(2131232669);
                    break;
                case ViewNode.WIDTH_FIELD_NUMBER:
                    drawable = context.getDrawable(2131232662);
                    break;
                case 8:
                    drawable = context.getDrawable(2131232670);
                    break;
                default:
                    drawable = null;
                    break;
            }
            this.mAmbientIconOverride = drawable;
            updatePill();
        }
    }

    public final void updateBottomSpacing() {
        boolean z;
        if (this.mInflated) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131165332);
            if (this.mBottomMarginPx != dimensionPixelSize) {
                this.mBottomMarginPx = dimensionPixelSize;
                ((ViewGroup.MarginLayoutParams) getLayoutParams()).bottomMargin = this.mBottomMarginPx;
            }
            int i = 0;
            if (this.mTextView.getVisibility() == 0) {
                z = true;
            } else {
                z = false;
            }
            ShadeViewController shadeViewController = this.mShadeViewController;
            int top = getTop();
            NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) shadeViewController;
            if (z) {
                i = notificationPanelViewController.mNotificationStackScrollLayoutController.mView.getBottom() - top;
            }
            if (notificationPanelViewController.mAmbientIndicationBottomPadding != i) {
                notificationPanelViewController.mAmbientIndicationBottomPadding = i;
                notificationPanelViewController.updateMaxDisplayedNotifications(true);
            }
        }
    }

    public final void updateColors() {
        int i;
        ValueAnimator valueAnimator = this.mTextColorAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mTextColorAnimator.cancel();
        }
        int defaultColor = this.mTextView.getTextColors().getDefaultColor();
        if (this.mDozing) {
            i = -1;
        } else {
            i = this.mTextColor;
        }
        if (defaultColor == i) {
            this.mTextView.setTextColor(i);
            this.mIconView.setImageTintList(ColorStateList.valueOf(i));
            return;
        }
        ValueAnimator ofArgb = ValueAnimator.ofArgb(new int[]{defaultColor, i});
        this.mTextColorAnimator = ofArgb;
        ofArgb.setInterpolator(Interpolators.LINEAR_OUT_SLOW_IN);
        this.mTextColorAnimator.setDuration(500);
        this.mTextColorAnimator.addUpdateListener(new AmbientIndicationContainer$$ExternalSyntheticLambda0(this));
        this.mTextColorAnimator.addListener(new AnimatorListenerAdapter(this, 1) {
            public final /* synthetic */ AmbientIndicationContainer this$0;

            {
                this.this$0 = r1;
            }

            public final void onAnimationEnd(Animator animator) {
                switch (0) {
                    case 0:
                        this.this$0.mWakeLock.release("AmbientIndication");
                        this.this$0.mTextView.animate().setListener((Animator.AnimatorListener) null);
                        return;
                    default:
                        this.this$0.mTextColorAnimator = null;
                        return;
                }
            }
        });
        this.mTextColorAnimator.start();
    }

    public final void updatePill() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        CharSequence charSequence;
        Drawable drawable;
        boolean z5;
        int i;
        int i2;
        int i3;
        Drawable drawable2;
        TextView textView = this.mTextView;
        if (textView != null) {
            int i4 = this.mIndicationTextMode;
            this.mIndicationTextMode = 1;
            CharSequence charSequence2 = this.mAmbientMusicText;
            if (textView.getVisibility() == 0) {
                z = true;
            } else {
                z = false;
            }
            CharSequence charSequence3 = this.mAmbientMusicText;
            if (charSequence3 == null || charSequence3.length() != 0) {
                z2 = false;
            } else {
                z2 = true;
            }
            TextView textView2 = this.mTextView;
            if (this.mOpenIntent != null) {
                z3 = true;
            } else {
                z3 = false;
            }
            textView2.setClickable(z3);
            ImageView imageView = this.mIconView;
            if (this.mFavoritingIntent == null && this.mOpenIntent == null) {
                z4 = false;
            } else {
                z4 = true;
            }
            imageView.setClickable(z4);
            if (TextUtils.isEmpty(this.mIconDescription)) {
                charSequence = charSequence2;
            } else {
                charSequence = this.mIconDescription;
            }
            Drawable drawable3 = null;
            if (!TextUtils.isEmpty(this.mReverseChargingMessage)) {
                this.mIndicationTextMode = 2;
                charSequence2 = this.mReverseChargingMessage;
                if (this.mReverseChargingAnimation == null) {
                    this.mReverseChargingAnimation = this.mContext.getDrawable(2130772561);
                }
                Drawable drawable4 = this.mReverseChargingAnimation;
                this.mTextView.setClickable(false);
                this.mIconView.setClickable(false);
                charSequence = null;
                drawable3 = drawable4;
                z2 = false;
            } else if (!TextUtils.isEmpty(this.mWirelessChargingMessage)) {
                this.mIndicationTextMode = 3;
                charSequence2 = this.mWirelessChargingMessage;
                this.mTextView.setClickable(false);
                this.mIconView.setClickable(false);
                z2 = false;
                charSequence = null;
            } else if ((!TextUtils.isEmpty(charSequence2) || z2) && (drawable3 = this.mAmbientIconOverride) == null) {
                if (z) {
                    if (this.mAmbientMusicNoteIcon == null) {
                        this.mAmbientMusicNoteIcon = this.mContext.getDrawable(2131232941);
                    }
                    drawable2 = this.mAmbientMusicNoteIcon;
                } else {
                    if (this.mAmbientMusicAnimation == null) {
                        this.mAmbientMusicAnimation = this.mContext.getDrawable(2130772484);
                    }
                    drawable2 = this.mAmbientMusicAnimation;
                }
                drawable3 = drawable2;
            }
            this.mTextView.setText(charSequence2);
            this.mTextView.setContentDescription(charSequence2);
            this.mIconView.setContentDescription(charSequence);
            if (drawable3 != null) {
                this.mIconBounds.set(0, 0, drawable3.getIntrinsicWidth(), drawable3.getIntrinsicHeight());
                Rect rect = this.mIconBounds;
                if (drawable3 == this.mAmbientMusicNoteIcon) {
                    i2 = this.mAmbientMusicNoteIconIconSize;
                } else {
                    i2 = this.mAmbientIndicationIconSize;
                }
                MathUtils.fitRect(rect, i2);
                drawable = new DrawableWrapper(drawable3) {
                    public final int getIntrinsicHeight() {
                        return AmbientIndicationContainer.this.mIconBounds.height();
                    }

                    public final int getIntrinsicWidth() {
                        return AmbientIndicationContainer.this.mIconBounds.width();
                    }
                };
                if (!TextUtils.isEmpty(charSequence2)) {
                    i3 = (int) (getResources().getDisplayMetrics().density * 24.0f);
                } else {
                    i3 = 0;
                }
                TextView textView3 = this.mTextView;
                textView3.setPaddingRelative(textView3.getPaddingStart(), this.mTextView.getPaddingTop(), i3, this.mTextView.getPaddingBottom());
            } else {
                TextView textView4 = this.mTextView;
                textView4.setPaddingRelative(textView4.getPaddingStart(), this.mTextView.getPaddingTop(), 0, this.mTextView.getPaddingBottom());
                drawable = drawable3;
            }
            this.mIconView.setImageDrawable(drawable);
            if (!TextUtils.isEmpty(charSequence2) || z2) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (z5) {
                i = 0;
            } else {
                i = 8;
            }
            this.mTextView.setVisibility(i);
            if (drawable3 == null) {
                this.mIconView.setVisibility(8);
            } else {
                this.mIconView.setVisibility(i);
            }
            if (!z5) {
                this.mTextView.animate().cancel();
                if (drawable3 != null && (drawable3 instanceof AnimatedVectorDrawable)) {
                    ((AnimatedVectorDrawable) drawable3).reset();
                }
                this.mHandler.post(this.mWakeLock.wrap(new AmbientIndicationContainer$$ExternalSyntheticLambda1(1)));
            } else if (!z) {
                this.mWakeLock.acquire("AmbientIndication");
                if (drawable3 != null && (drawable3 instanceof AnimatedVectorDrawable)) {
                    ((AnimatedVectorDrawable) drawable3).start();
                }
                TextView textView5 = this.mTextView;
                textView5.setTranslationY((float) (textView5.getHeight() / 2));
                this.mTextView.setAlpha(0.0f);
                this.mTextView.animate().alpha(1.0f).translationY(0.0f).setStartDelay(150).setDuration(100).setListener(new AnimatorListenerAdapter(this, 0) {
                    public final /* synthetic */ AmbientIndicationContainer this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final void onAnimationEnd(Animator animator) {
                        switch (0) {
                            case 0:
                                this.this$0.mWakeLock.release("AmbientIndication");
                                this.this$0.mTextView.animate().setListener((Animator.AnimatorListener) null);
                                return;
                            default:
                                this.this$0.mTextColorAnimator = null;
                                return;
                        }
                    }
                }).setInterpolator(Interpolators.DECELERATE_QUINT).start();
            } else if (i4 == this.mIndicationTextMode) {
                this.mHandler.post(this.mWakeLock.wrap(new AmbientIndicationContainer$$ExternalSyntheticLambda1(0)));
            } else if (drawable3 != null && (drawable3 instanceof AnimatedVectorDrawable)) {
                this.mWakeLock.acquire("AmbientIndication");
                ((AnimatedVectorDrawable) drawable3).start();
                this.mWakeLock.release("AmbientIndication");
            }
            updateBottomSpacing();
        }
    }
}
