package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.ProgressBar;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.WeakHashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BaseProgressIndicator extends ProgressBar {
    public AnimatorDurationScaleProvider animatorDurationScaleProvider;
    public final AnonymousClass1 delayedHide = new Runnable(this, 1) {
        public final /* synthetic */ BaseProgressIndicator this$0;

        {
            this.this$0 = r1;
        }

        public final void run() {
            switch (1) {
                case 0:
                    BaseProgressIndicator baseProgressIndicator = this.this$0;
                    if (baseProgressIndicator.minHideDelay > 0) {
                        SystemClock.uptimeMillis();
                    }
                    baseProgressIndicator.setVisibility(0);
                    return;
                default:
                    BaseProgressIndicator.access$100(this.this$0);
                    this.this$0.getClass();
                    return;
            }
        }
    };
    public final AnonymousClass1 delayedShow = new Runnable(this, 0) {
        public final /* synthetic */ BaseProgressIndicator this$0;

        {
            this.this$0 = r1;
        }

        public final void run() {
            switch (1) {
                case 0:
                    BaseProgressIndicator baseProgressIndicator = this.this$0;
                    if (baseProgressIndicator.minHideDelay > 0) {
                        SystemClock.uptimeMillis();
                    }
                    baseProgressIndicator.setVisibility(0);
                    return;
                default:
                    BaseProgressIndicator.access$100(this.this$0);
                    this.this$0.getClass();
                    return;
            }
        }
    };
    public final AnonymousClass3 hideAnimationCallback = new Animatable2Compat.AnimationCallback(this, 1) {
        public final /* synthetic */ BaseProgressIndicator this$0;

        {
            this.this$0 = r1;
        }

        public final void onAnimationEnd(Drawable drawable) {
            int i = 1;
            BaseProgressIndicator baseProgressIndicator = this.this$0;
            switch (i) {
                case 0:
                    baseProgressIndicator.setIndeterminate(false);
                    baseProgressIndicator.setProgressCompat(baseProgressIndicator.storedProgress, baseProgressIndicator.storedProgressAnimated);
                    return;
                default:
                    if (!baseProgressIndicator.isIndeterminateModeChangeRequested) {
                        baseProgressIndicator.setVisibility(baseProgressIndicator.visibilityAfterHide);
                        return;
                    }
                    return;
            }
        }
    };
    public boolean isIndeterminateModeChangeRequested = false;
    public final boolean isParentDoneInitializing;
    public final int minHideDelay;
    public final BaseProgressIndicatorSpec spec;
    public int storedProgress;
    public boolean storedProgressAnimated;
    public final AnonymousClass3 switchIndeterminateModeCallback = new Animatable2Compat.AnimationCallback(this, 0) {
        public final /* synthetic */ BaseProgressIndicator this$0;

        {
            this.this$0 = r1;
        }

        public final void onAnimationEnd(Drawable drawable) {
            int i = 1;
            BaseProgressIndicator baseProgressIndicator = this.this$0;
            switch (i) {
                case 0:
                    baseProgressIndicator.setIndeterminate(false);
                    baseProgressIndicator.setProgressCompat(baseProgressIndicator.storedProgress, baseProgressIndicator.storedProgressAnimated);
                    return;
                default:
                    if (!baseProgressIndicator.isIndeterminateModeChangeRequested) {
                        baseProgressIndicator.setVisibility(baseProgressIndicator.visibilityAfterHide);
                        return;
                    }
                    return;
            }
        }
    };
    public final int visibilityAfterHide = 4;

    /* JADX WARNING: type inference failed for: r9v4, types: [com.google.android.material.progressindicator.AnimatorDurationScaleProvider, java.lang.Object] */
    public BaseProgressIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, 2132018851), attributeSet, i);
        Context context2 = getContext();
        this.spec = createSpec(context2, attributeSet);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.BaseProgressIndicator, i, i2, new int[0]);
        obtainStyledAttributes.getInt(5, -1);
        this.minHideDelay = Math.min(obtainStyledAttributes.getInt(3, -1), 1000);
        obtainStyledAttributes.recycle();
        this.animatorDurationScaleProvider = new Object();
        this.isParentDoneInitializing = true;
    }

    public static void access$100(BaseProgressIndicator baseProgressIndicator) {
        ((DrawableWithAnimatedVisibilityChange) baseProgressIndicator.getCurrentDrawable()).setVisible(false, false, true);
        if (((DeterminateDrawable) super.getProgressDrawable()) != null && ((DeterminateDrawable) super.getProgressDrawable()).isVisible()) {
            return;
        }
        if (((IndeterminateDrawable) super.getIndeterminateDrawable()) == null || !((IndeterminateDrawable) super.getIndeterminateDrawable()).isVisible()) {
            baseProgressIndicator.setVisibility(4);
        }
    }

    public abstract BaseProgressIndicatorSpec createSpec(Context context, AttributeSet attributeSet);

    public final Drawable getCurrentDrawable() {
        if (isIndeterminate()) {
            return (IndeterminateDrawable) super.getIndeterminateDrawable();
        }
        return (DeterminateDrawable) super.getProgressDrawable();
    }

    public final Drawable getIndeterminateDrawable() {
        return (IndeterminateDrawable) super.getIndeterminateDrawable();
    }

    public final Drawable getProgressDrawable() {
        return (DeterminateDrawable) super.getProgressDrawable();
    }

    public final void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!(((DeterminateDrawable) super.getProgressDrawable()) == null || ((IndeterminateDrawable) super.getIndeterminateDrawable()) == null)) {
            ((IndeterminateDrawable) super.getIndeterminateDrawable()).animatorDelegate.registerAnimatorsCompleteCallback(this.switchIndeterminateModeCallback);
        }
        if (((DeterminateDrawable) super.getProgressDrawable()) != null) {
            ((DeterminateDrawable) super.getProgressDrawable()).registerAnimationCallback(this.hideAnimationCallback);
        }
        if (((IndeterminateDrawable) super.getIndeterminateDrawable()) != null) {
            ((IndeterminateDrawable) super.getIndeterminateDrawable()).registerAnimationCallback(this.hideAnimationCallback);
        }
        if (visibleToUser()) {
            if (this.minHideDelay > 0) {
                SystemClock.uptimeMillis();
            }
            setVisibility(0);
        }
    }

    public final void onDetachedFromWindow() {
        removeCallbacks(this.delayedHide);
        removeCallbacks(this.delayedShow);
        ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).setVisible(false, false, false);
        if (((IndeterminateDrawable) super.getIndeterminateDrawable()) != null) {
            ((IndeterminateDrawable) super.getIndeterminateDrawable()).unregisterAnimationCallback(this.hideAnimationCallback);
            ((IndeterminateDrawable) super.getIndeterminateDrawable()).animatorDelegate.unregisterAnimatorsCompleteCallback();
        }
        if (((DeterminateDrawable) super.getProgressDrawable()) != null) {
            ((DeterminateDrawable) super.getProgressDrawable()).unregisterAnimationCallback(this.hideAnimationCallback);
        }
        super.onDetachedFromWindow();
    }

    public final synchronized void onDraw(Canvas canvas) {
        try {
            int save = canvas.save();
            if (getPaddingLeft() == 0) {
                if (getPaddingTop() != 0) {
                }
                if (!(getPaddingRight() == 0 && getPaddingBottom() == 0)) {
                    canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
                }
                getCurrentDrawable().draw(canvas);
                canvas.restoreToCount(save);
            }
            canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
            canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
            getCurrentDrawable().draw(canvas);
            canvas.restoreToCount(save);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void onMeasure(int i, int i2) {
        int i3;
        int i4;
        try {
            DrawingDelegate drawingDelegate = null;
            if (isIndeterminate()) {
                if (((IndeterminateDrawable) super.getIndeterminateDrawable()) != null) {
                    drawingDelegate = ((IndeterminateDrawable) super.getIndeterminateDrawable()).drawingDelegate;
                }
            } else if (((DeterminateDrawable) super.getProgressDrawable()) != null) {
                drawingDelegate = ((DeterminateDrawable) super.getProgressDrawable()).drawingDelegate;
            }
            if (drawingDelegate != null) {
                if (drawingDelegate.getPreferredWidth() < 0) {
                    i3 = ProgressBar.getDefaultSize(getSuggestedMinimumWidth(), i);
                } else {
                    i3 = drawingDelegate.getPreferredWidth() + getPaddingLeft() + getPaddingRight();
                }
                if (drawingDelegate.getPreferredHeight() < 0) {
                    i4 = ProgressBar.getDefaultSize(getSuggestedMinimumHeight(), i2);
                } else {
                    i4 = drawingDelegate.getPreferredHeight() + getPaddingTop() + getPaddingBottom();
                }
                setMeasuredDimension(i3, i4);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void onVisibilityChanged(View view, int i) {
        boolean z;
        super.onVisibilityChanged(view, i);
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.isParentDoneInitializing) {
            ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).setVisible(visibleToUser(), false, z);
        }
    }

    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.isParentDoneInitializing) {
            ((DrawableWithAnimatedVisibilityChange) getCurrentDrawable()).setVisible(visibleToUser(), false, false);
        }
    }

    public void setAnimatorDurationScaleProvider(AnimatorDurationScaleProvider animatorDurationScaleProvider2) {
        this.animatorDurationScaleProvider = animatorDurationScaleProvider2;
        if (((DeterminateDrawable) super.getProgressDrawable()) != null) {
            ((DeterminateDrawable) super.getProgressDrawable()).animatorDurationScaleProvider = animatorDurationScaleProvider2;
        }
        if (((IndeterminateDrawable) super.getIndeterminateDrawable()) != null) {
            ((IndeterminateDrawable) super.getIndeterminateDrawable()).animatorDurationScaleProvider = animatorDurationScaleProvider2;
        }
    }

    public final synchronized void setIndeterminate(boolean z) {
        try {
            if (z != isIndeterminate()) {
                DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange = (DrawableWithAnimatedVisibilityChange) getCurrentDrawable();
                if (drawableWithAnimatedVisibilityChange != null) {
                    drawableWithAnimatedVisibilityChange.setVisible(false, false, false);
                }
                super.setIndeterminate(z);
                DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange2 = (DrawableWithAnimatedVisibilityChange) getCurrentDrawable();
                if (drawableWithAnimatedVisibilityChange2 != null) {
                    drawableWithAnimatedVisibilityChange2.setVisible(visibleToUser(), false, false);
                }
                if ((drawableWithAnimatedVisibilityChange2 instanceof IndeterminateDrawable) && visibleToUser()) {
                    ((IndeterminateDrawable) drawableWithAnimatedVisibilityChange2).animatorDelegate.startAnimator();
                }
                this.isIndeterminateModeChangeRequested = false;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void setIndeterminateDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable((Drawable) null);
        } else if (drawable instanceof IndeterminateDrawable) {
            ((DrawableWithAnimatedVisibilityChange) drawable).setVisible(false, false, false);
            super.setIndeterminateDrawable(drawable);
        } else {
            throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
        }
    }

    public final synchronized void setProgress(int i) {
        if (!isIndeterminate()) {
            setProgressCompat(i, false);
        }
    }

    public void setProgressCompat(int i, boolean z) {
        if (!isIndeterminate()) {
            super.setProgress(i);
            if (((DeterminateDrawable) super.getProgressDrawable()) != null && !z) {
                ((DeterminateDrawable) super.getProgressDrawable()).jumpToCurrentState();
            }
        } else if (((DeterminateDrawable) super.getProgressDrawable()) != null) {
            this.storedProgress = i;
            this.storedProgressAnimated = z;
            this.isIndeterminateModeChangeRequested = true;
            if (((IndeterminateDrawable) super.getIndeterminateDrawable()).isVisible()) {
                AnimatorDurationScaleProvider animatorDurationScaleProvider2 = this.animatorDurationScaleProvider;
                ContentResolver contentResolver = getContext().getContentResolver();
                animatorDurationScaleProvider2.getClass();
                if (Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) != 0.0f) {
                    ((IndeterminateDrawable) super.getIndeterminateDrawable()).animatorDelegate.requestCancelAnimatorAfterCurrentCycle();
                    return;
                }
            }
            this.switchIndeterminateModeCallback.onAnimationEnd((IndeterminateDrawable) super.getIndeterminateDrawable());
        }
    }

    public final void setProgressDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setProgressDrawable((Drawable) null);
        } else if (drawable instanceof DeterminateDrawable) {
            DeterminateDrawable determinateDrawable = (DeterminateDrawable) drawable;
            determinateDrawable.setVisible(false, false, false);
            super.setProgressDrawable(determinateDrawable);
            determinateDrawable.setLevel((int) ((((float) getProgress()) / ((float) getMax())) * 10000.0f));
        } else {
            throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
        }
    }

    public final boolean visibleToUser() {
        WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
        if (ViewCompat.Api19Impl.isAttachedToWindow(this) && getWindowVisibility() == 0) {
            View view = this;
            while (true) {
                if (view.getVisibility() != 0) {
                    break;
                }
                ViewParent parent = view.getParent();
                if (parent == null) {
                    if (getWindowVisibility() == 0) {
                        return true;
                    }
                } else if (!(parent instanceof View)) {
                    break;
                } else {
                    view = (View) parent;
                }
            }
        }
        return false;
    }

    /* renamed from: getIndeterminateDrawable  reason: collision with other method in class */
    public final IndeterminateDrawable m825getIndeterminateDrawable() {
        return (IndeterminateDrawable) super.getIndeterminateDrawable();
    }

    /* renamed from: getProgressDrawable  reason: collision with other method in class */
    public final DeterminateDrawable m826getProgressDrawable() {
        return (DeterminateDrawable) super.getProgressDrawable();
    }
}
