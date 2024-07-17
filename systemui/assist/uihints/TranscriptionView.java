package com.google.android.systemui.assist.uihints;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.text.SpannableStringBuilder;
import android.text.style.ReplacementSpan;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.TextView;
import androidx.core.math.MathUtils;
import com.android.systemui.assist.DeviceConfigHelper;
import com.google.android.systemui.assist.uihints.TranscriptionController;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class TranscriptionView extends TextView implements TranscriptionController.TranscriptionSpaceView {
    public static final PathInterpolator INTERPOLATOR_SCROLL = new PathInterpolator(0.17f, 0.17f, 0.67f, 1.0f);
    public final float BUMPER_DISTANCE_END_PX;
    public final float BUMPER_DISTANCE_START_PX;
    public final float FADE_DISTANCE_END_PX;
    public final float FADE_DISTANCE_START_PX;
    public final int TEXT_COLOR_DARK;
    public final int TEXT_COLOR_LIGHT;
    public boolean mCardVisible;
    public DeviceConfigHelper mDeviceConfigHelper;
    public int mDisplayWidthPx;
    public boolean mHasDarkBackground;
    public SettableFuture mHideFuture;
    public final Matrix mMatrix;
    public int mRequestedTextColor;
    public float[] mStops;
    public ValueAnimator mTranscriptionAnimation;
    public TranscriptionAnimator mTranscriptionAnimator;
    public final SpannableStringBuilder mTranscriptionBuilder;
    public AnimatorSet mVisibilityAnimators;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class TranscriptionAnimator implements ValueAnimator.AnimatorUpdateListener {
        public float mDistance;
        public final List mSpans = new ArrayList();
        public float mStartX;

        public TranscriptionAnimator() {
        }

        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (Math.abs(floatValue - this.mStartX) < Math.abs(this.mDistance)) {
                TranscriptionView.this.setX(floatValue);
                TranscriptionView transcriptionView = TranscriptionView.this;
                PathInterpolator pathInterpolator = TranscriptionView.INTERPOLATOR_SCROLL;
                transcriptionView.updateColor();
            }
            ((ArrayList) this.mSpans).forEach(new TranscriptionView$TranscriptionAnimator$$ExternalSyntheticLambda0(valueAnimator));
            TranscriptionView.this.invalidate();
        }
    }

    public TranscriptionView(Context context) {
        this(context, (AttributeSet) null);
    }

    public static float interpolate(long j, long j2, float f) {
        return (((float) (j2 - j)) * f) + ((float) j);
    }

    public long getAdaptiveDuration(float f, float f2) {
        this.mDeviceConfigHelper.getClass();
        long j = DeviceConfigHelper.getLong(4, "assist_transcription_duration_per_px_regular");
        this.mDeviceConfigHelper.getClass();
        float interpolate = interpolate(j, DeviceConfigHelper.getLong(3, "assist_transcription_duration_per_px_fast"), f / f2);
        this.mDeviceConfigHelper.getClass();
        long j2 = DeviceConfigHelper.getLong(400, "assist_transcription_max_duration");
        this.mDeviceConfigHelper.getClass();
        return Math.min(j2, Math.max(DeviceConfigHelper.getLong(20, "assist_transcription_min_duration"), (long) (f * interpolate)));
    }

    public final float getFullyVisibleDistance(float f) {
        int i = this.mDisplayWidthPx;
        float f2 = this.BUMPER_DISTANCE_END_PX;
        float f3 = this.FADE_DISTANCE_END_PX;
        if (f < ((float) i) - (((this.BUMPER_DISTANCE_START_PX + f2) + f3) + this.FADE_DISTANCE_START_PX)) {
            return (((float) i) - f) / 2.0f;
        }
        return ((((float) i) - f) - f3) - f2;
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object, com.google.common.util.concurrent.SettableFuture] */
    public final ListenableFuture hide(boolean z) {
        SettableFuture settableFuture = this.mHideFuture;
        if (settableFuture != null && !settableFuture.isDone()) {
            return this.mHideFuture;
        }
        this.mHideFuture = new Object();
        final TranscriptionView$$ExternalSyntheticLambda0 transcriptionView$$ExternalSyntheticLambda0 = new TranscriptionView$$ExternalSyntheticLambda0(this);
        if (!z) {
            if (this.mVisibilityAnimators.isRunning()) {
                this.mVisibilityAnimators.end();
            } else {
                transcriptionView$$ExternalSyntheticLambda0.run();
            }
            return ImmediateFuture.NULL;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        this.mVisibilityAnimators = animatorSet;
        animatorSet.play(ObjectAnimator.ofFloat(this, View.ALPHA, new float[]{getAlpha(), 0.0f}).setDuration(400));
        if (!this.mCardVisible) {
            this.mVisibilityAnimators.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, new float[]{getTranslationY(), (float) (getHeight() * -1)}).setDuration(700));
        }
        this.mVisibilityAnimators.addListener(new AnimatorListenerAdapter() {
            public final void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                transcriptionView$$ExternalSyntheticLambda0.run();
            }
        });
        this.mVisibilityAnimators.start();
        return this.mHideFuture;
    }

    public void initializeDeviceConfigHelper(DeviceConfigHelper deviceConfigHelper) {
        this.mDeviceConfigHelper = deviceConfigHelper;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        String spannableStringBuilder = this.mTranscriptionBuilder.toString();
        setTranscription("");
        this.mTranscriptionAnimator = new TranscriptionAnimator();
        setTranscription(spannableStringBuilder);
    }

    public final void onFontSizeChanged() {
        setTextSize(0, this.mContext.getResources().getDimension(2131167746));
    }

    public final void setCardVisible(boolean z) {
        this.mCardVisible = z;
    }

    public final void setHasDarkBackground(boolean z) {
        if (z != this.mHasDarkBackground) {
            this.mHasDarkBackground = z;
            updateColor();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionSpan} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setTranscription(java.lang.String r15) {
        /*
            r14 = this;
            r0 = 0
            r14.setVisibility(r0)
            r14.updateDisplayWidth()
            android.animation.ValueAnimator r1 = r14.mTranscriptionAnimation
            r2 = 1
            if (r1 == 0) goto L_0x0014
            boolean r1 = r1.isRunning()
            if (r1 == 0) goto L_0x0014
            r1 = r2
            goto L_0x0015
        L_0x0014:
            r1 = r0
        L_0x0015:
            if (r1 == 0) goto L_0x001c
            android.animation.ValueAnimator r3 = r14.mTranscriptionAnimation
            r3.cancel()
        L_0x001c:
            android.text.SpannableStringBuilder r3 = r14.mTranscriptionBuilder
            java.lang.String r3 = r3.toString()
            boolean r3 = r3.isEmpty()
            android.text.SpannableStringBuilder r4 = r14.mTranscriptionBuilder
            java.lang.String r4 = r4.toString()
            if (r4 == 0) goto L_0x00a4
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L_0x0036
            goto L_0x00a4
        L_0x0036:
            if (r15 == 0) goto L_0x00a4
            boolean r5 = r15.isEmpty()
            if (r5 == 0) goto L_0x0040
            goto L_0x00a4
        L_0x0040:
            java.lang.String r5 = r4.toLowerCase()
            java.lang.String r6 = r15.toLowerCase()
            int r7 = r5.length()
            int r8 = r6.length()
            int[] r7 = new int[]{r7, r8}
            java.lang.Class r8 = java.lang.Integer.TYPE
            java.lang.Object r7 = java.lang.reflect.Array.newInstance(r8, r7)
            r13 = r7
            int[][] r13 = (int[][]) r13
            r7 = r0
        L_0x005e:
            int r8 = r5.length()
            if (r7 >= r8) goto L_0x0094
            char r8 = r5.charAt(r7)
            r9 = r0
        L_0x0069:
            int r10 = r6.length()
            if (r9 >= r10) goto L_0x0092
            char r10 = r6.charAt(r9)
            if (r8 != r10) goto L_0x0090
            r10 = 32
            if (r8 != r10) goto L_0x007b
            r10 = r0
            goto L_0x007c
        L_0x007b:
            r10 = r2
        L_0x007c:
            if (r7 == 0) goto L_0x008a
            if (r9 != 0) goto L_0x0081
            goto L_0x008a
        L_0x0081:
            int r11 = r7 + -1
            r11 = r13[r11]
            int r12 = r9 + -1
            r11 = r11[r12]
            goto L_0x008b
        L_0x008a:
            r11 = r0
        L_0x008b:
            r12 = r13[r7]
            int r11 = r11 + r10
            r12[r9] = r11
        L_0x0090:
            int r9 = r9 + r2
            goto L_0x0069
        L_0x0092:
            int r7 = r7 + r2
            goto L_0x005e
        L_0x0094:
            int r10 = r4.length()
            int r12 = r15.length()
            r9 = 0
            r11 = 0
            r8 = r15
            com.google.android.systemui.assist.uihints.StringUtils$StringStabilityInfo r15 = com.google.android.systemui.assist.uihints.StringUtils.getRightMostStabilityInfoLeaf(r8, r9, r10, r11, r12, r13)
            goto L_0x00aa
        L_0x00a4:
            com.google.android.systemui.assist.uihints.StringUtils$StringStabilityInfo r4 = new com.google.android.systemui.assist.uihints.StringUtils$StringStabilityInfo
            r4.<init>(r15)
            r15 = r4
        L_0x00aa:
            android.text.SpannableStringBuilder r4 = r14.mTranscriptionBuilder
            r4.clear()
            android.text.SpannableStringBuilder r4 = r14.mTranscriptionBuilder
            java.lang.String r5 = r15.stable
            r4.append(r5)
            android.text.SpannableStringBuilder r4 = r14.mTranscriptionBuilder
            java.lang.String r5 = r15.unstable
            r4.append(r5)
            android.text.TextPaint r4 = r14.getPaint()
            android.text.SpannableStringBuilder r6 = r14.mTranscriptionBuilder
            java.lang.String r6 = r6.toString()
            float r4 = r4.measureText(r6)
            double r6 = (double) r4
            double r6 = java.lang.Math.ceil(r6)
            int r4 = (int) r6
            android.view.ViewGroup$LayoutParams r6 = r14.getLayoutParams()
            if (r6 == 0) goto L_0x00dc
            r6.width = r4
            r14.setLayoutParams(r6)
        L_0x00dc:
            r14.updateColor()
            r6 = 0
            java.lang.String r15 = r15.stable
            if (r3 != 0) goto L_0x01ab
            boolean r3 = r15.isEmpty()
            if (r3 == 0) goto L_0x00ec
            goto L_0x01ab
        L_0x00ec:
            int r3 = r15.length()
            if (r1 == 0) goto L_0x0125
            java.lang.String r1 = " "
            boolean r4 = r15.endsWith(r1)
            if (r4 != 0) goto L_0x0125
            boolean r1 = r5.startsWith(r1)
            if (r1 != 0) goto L_0x0125
            java.lang.String r1 = "\\s+"
            java.lang.String[] r15 = r15.split(r1)
            int r1 = r15.length
            if (r1 <= 0) goto L_0x0112
            int r1 = r15.length
            int r1 = r1 - r2
            r15 = r15[r1]
            int r15 = r15.length()
            int r3 = r3 - r15
        L_0x0112:
            com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionAnimator r15 = r14.mTranscriptionAnimator
            java.util.List r15 = r15.mSpans
            boolean r1 = r15.isEmpty()
            if (r1 != 0) goto L_0x0125
            java.util.ArrayList r15 = (java.util.ArrayList) r15
            java.lang.Object r15 = androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0.m(r15, r2)
            r6 = r15
            com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionSpan r6 = (com.google.android.systemui.assist.uihints.TranscriptionView.TranscriptionSpan) r6
        L_0x0125:
            r14.setUpSpans(r3, r6)
            com.google.android.systemui.assist.uihints.TranscriptionView$TranscriptionAnimator r15 = r14.mTranscriptionAnimator
            com.google.android.systemui.assist.uihints.TranscriptionView r1 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            android.text.TextPaint r1 = r1.getPaint()
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            android.text.SpannableStringBuilder r3 = r3.mTranscriptionBuilder
            java.lang.String r3 = r3.toString()
            float r1 = r1.measureText(r3)
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            float r3 = r3.getX()
            r15.mStartX = r3
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            float r3 = r3.getFullyVisibleDistance(r1)
            float r4 = r15.mStartX
            float r3 = r3 - r4
            r15.mDistance = r3
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            r3.updateColor()
            com.google.android.systemui.assist.uihints.TranscriptionView r3 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            float r4 = r15.mDistance
            float r4 = java.lang.Math.abs(r4)
            com.google.android.systemui.assist.uihints.TranscriptionView r5 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            int r5 = r5.mDisplayWidthPx
            float r5 = (float) r5
            long r3 = r3.getAdaptiveDuration(r4, r5)
            com.google.android.systemui.assist.uihints.TranscriptionView r5 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            int r6 = r5.mDisplayWidthPx
            float r6 = (float) r6
            float r5 = r5.getX()
            float r6 = r6 - r5
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 <= 0) goto L_0x0184
            com.google.android.systemui.assist.uihints.TranscriptionView r1 = com.google.android.systemui.assist.uihints.TranscriptionView.this
            com.android.systemui.assist.DeviceConfigHelper r1 = r1.mDeviceConfigHelper
            r1.getClass()
            r5 = 50
            java.lang.String r1 = "assist_transcription_fade_in_duration"
            long r5 = com.android.systemui.assist.DeviceConfigHelper.getLong(r5, r1)
            long r5 = r5 + r3
            goto L_0x0185
        L_0x0184:
            r5 = r3
        L_0x0185:
            float r1 = r15.mDistance
            float r7 = (float) r5
            float r3 = (float) r3
            float r7 = r7 / r3
            float r7 = r7 * r1
            float r1 = r15.mStartX
            float r7 = r7 + r1
            r3 = 2
            float[] r3 = new float[r3]
            r3[r0] = r1
            r3[r2] = r7
            android.animation.ValueAnimator r0 = android.animation.ValueAnimator.ofFloat(r3)
            android.animation.ValueAnimator r0 = r0.setDuration(r5)
            android.view.animation.PathInterpolator r1 = INTERPOLATOR_SCROLL
            r0.setInterpolator(r1)
            r0.addUpdateListener(r15)
            r14.mTranscriptionAnimation = r0
            r0.start()
            goto L_0x01c2
        L_0x01ab:
            int r15 = r15.length()
            int r0 = r5.length()
            int r0 = r0 + r15
            r14.setUpSpans(r0, r6)
            float r15 = (float) r4
            float r15 = r14.getFullyVisibleDistance(r15)
            r14.setX(r15)
            r14.updateColor()
        L_0x01c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.TranscriptionView.setTranscription(java.lang.String):void");
    }

    public final void setUpSpans(int i, TranscriptionSpan transcriptionSpan) {
        TranscriptionSpan transcriptionSpan2;
        this.mTranscriptionAnimator.mSpans.clear();
        String spannableStringBuilder = this.mTranscriptionBuilder.toString();
        String substring = spannableStringBuilder.substring(i);
        if (substring.length() > 0) {
            int indexOf = spannableStringBuilder.indexOf(substring, i);
            int length = substring.length() + indexOf;
            if (transcriptionSpan == null) {
                transcriptionSpan2 = new TranscriptionSpan();
            } else {
                transcriptionSpan2 = new TranscriptionSpan(transcriptionSpan);
            }
            this.mTranscriptionBuilder.setSpan(transcriptionSpan2, indexOf, length, 33);
            this.mTranscriptionAnimator.mSpans.add(transcriptionSpan2);
        }
        setText(this.mTranscriptionBuilder, TextView.BufferType.SPANNABLE);
        updateColor();
    }

    public final void updateColor() {
        int i = this.mRequestedTextColor;
        if (i == 0) {
            if (this.mHasDarkBackground) {
                i = this.TEXT_COLOR_DARK;
            } else {
                i = this.TEXT_COLOR_LIGHT;
            }
        }
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, (float) this.mDisplayWidthPx, 0.0f, new int[]{0, i, i, 0}, this.mStops, Shader.TileMode.CLAMP);
        this.mMatrix.setTranslate(-getTranslationX(), 0.0f);
        linearGradient.setLocalMatrix(this.mMatrix);
        getPaint().setShader(linearGradient);
        invalidate();
    }

    public final void updateDisplayWidth() {
        Display defaultDisplay = DisplayUtils.getDefaultDisplay(this.mContext);
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        int i = point.x;
        this.mDisplayWidthPx = i;
        float f = this.BUMPER_DISTANCE_START_PX;
        float f2 = (float) i;
        this.mStops = new float[]{f / f2, (f + this.FADE_DISTANCE_START_PX) / f2, ((f2 - this.FADE_DISTANCE_END_PX) - this.BUMPER_DISTANCE_END_PX) / f2, 1.0f};
        updateColor();
    }

    public TranscriptionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class TranscriptionSpan extends ReplacementSpan {
        public float mCurrentFraction = 0.0f;
        public final float mStartFraction = 0.0f;

        public TranscriptionSpan() {
        }

        public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            float f2 = this.mStartFraction;
            float f3 = 1.0f;
            if (f2 != 1.0f) {
                f3 = MathUtils.clamp((((1.0f - f2) / 1.0f) * this.mCurrentFraction) + f2, 0.0f, 1.0f);
            }
            Paint paint2 = paint;
            paint2.setAlpha((int) Math.ceil((double) (f3 * 255.0f)));
            canvas.drawText(charSequence, i, i2, f, (float) i4, paint2);
        }

        public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            return (int) Math.ceil((double) TranscriptionView.this.getPaint().measureText(charSequence, 0, charSequence.length()));
        }

        public TranscriptionSpan(TranscriptionSpan transcriptionSpan) {
            this.mStartFraction = MathUtils.clamp(transcriptionSpan.mCurrentFraction, 0.0f, 1.0f);
        }
    }

    public TranscriptionView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARNING: type inference failed for: r2v6, types: [com.android.systemui.assist.DeviceConfigHelper, java.lang.Object] */
    public TranscriptionView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTranscriptionBuilder = new SpannableStringBuilder();
        this.mVisibilityAnimators = new AnimatorSet();
        this.mHideFuture = null;
        this.mHasDarkBackground = false;
        this.mCardVisible = false;
        this.mRequestedTextColor = 0;
        this.mMatrix = new Matrix();
        this.mDisplayWidthPx = 0;
        this.mTranscriptionAnimator = new TranscriptionAnimator();
        initializeDeviceConfigHelper(new Object());
        this.BUMPER_DISTANCE_START_PX = context.getResources().getDimension(2131167854) + context.getResources().getDimension(2131167852);
        this.BUMPER_DISTANCE_END_PX = context.getResources().getDimension(2131165996) + context.getResources().getDimension(2131165994);
        this.FADE_DISTANCE_START_PX = context.getResources().getDimension(2131167853);
        this.FADE_DISTANCE_END_PX = context.getResources().getDimension(2131165995) / 2.0f;
        this.TEXT_COLOR_DARK = context.getResources().getColor(2131100852);
        this.TEXT_COLOR_LIGHT = context.getResources().getColor(2131100853);
        updateDisplayWidth();
        setHasDarkBackground(!this.mHasDarkBackground);
    }
}
