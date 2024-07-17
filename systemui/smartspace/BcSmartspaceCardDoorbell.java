package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTarget;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ImageDecoder;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.android.internal.util.LatencyTracker;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BcSmartspaceCardDoorbell extends BcSmartspaceCardGenericImage {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int mGifFrameDurationInMs;
    public final LatencyInstrumentContext mLatencyInstrumentContext;
    public ImageView mLoadingIcon;
    public ViewGroup mLoadingScreenView;
    public String mPreviousTargetId;
    public ProgressBar mProgressBar;
    public final Map mUriToDrawable;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class DrawableWithUri extends DrawableWrapper {
        public final Path mClipPath = new Path();
        public final ContentResolver mContentResolver;
        public Drawable mDrawable;
        public final int mHeightInPx;
        public final WeakReference mImageViewWeakReference;
        public final WeakReference mLoadingScreenWeakReference;
        public final float mRoundedCornersRadius;
        public final RectF mTempRect = new RectF();
        public final Uri mUri;

        public DrawableWithUri(float f, int i, ContentResolver contentResolver, Uri uri, WeakReference weakReference, WeakReference weakReference2) {
            super(new ColorDrawable(0));
            this.mRoundedCornersRadius = f;
            this.mUri = uri;
            this.mHeightInPx = i;
            this.mContentResolver = contentResolver;
            this.mImageViewWeakReference = weakReference;
            this.mLoadingScreenWeakReference = weakReference2;
        }

        /* renamed from: draw$com$android$launcher3$icons$RoundDrawableWrapper */
        public final void draw(Canvas canvas) {
            int save = canvas.save();
            canvas.clipPath(this.mClipPath);
            super.draw(canvas);
            canvas.restoreToCount(save);
        }

        /* renamed from: onBoundsChange$com$android$launcher3$icons$RoundDrawableWrapper */
        public final void onBoundsChange(Rect rect) {
            this.mTempRect.set(getBounds());
            this.mClipPath.reset();
            Path path = this.mClipPath;
            RectF rectF = this.mTempRect;
            float f = this.mRoundedCornersRadius;
            path.addRoundRect(rectF, f, f, Path.Direction.CCW);
            super.onBoundsChange(rect);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class LatencyInstrumentContext {
        public final LatencyTracker mLatencyTracker;
        public final Set mUriSet = new HashSet();

        public LatencyInstrumentContext(Context context) {
            this.mLatencyTracker = LatencyTracker.getInstance(context);
        }

        public final void cancelInstrument() {
            Set set = this.mUriSet;
            if (!set.isEmpty()) {
                this.mLatencyTracker.onActionCancel(22);
                set.clear();
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class LoadUriTask extends AsyncTask {
        public final LatencyInstrumentContext mInstrumentContext;

        public LoadUriTask(LatencyInstrumentContext latencyInstrumentContext) {
            this.mInstrumentContext = latencyInstrumentContext;
        }

        public final Object doInBackground(Object[] objArr) {
            DrawableWithUri[] drawableWithUriArr = (DrawableWithUri[]) objArr;
            Drawable drawable = null;
            if (drawableWithUriArr.length <= 0) {
                return null;
            }
            DrawableWithUri drawableWithUri = drawableWithUriArr[0];
            try {
                InputStream openInputStream = drawableWithUri.mContentResolver.openInputStream(drawableWithUri.mUri);
                int i = drawableWithUri.mHeightInPx;
                int i2 = BcSmartspaceCardDoorbell.$r8$clinit;
                try {
                    drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource((Resources) null, openInputStream), new BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4(i));
                } catch (IOException e) {
                    Log.e("BcSmartspaceCardBell", "Unable to decode stream: " + e);
                }
                drawableWithUri.mDrawable = drawable;
            } catch (Exception e2) {
                Log.w("BcSmartspaceCardBell", "open uri:" + drawableWithUri.mUri + " got exception:" + e2);
            }
            return drawableWithUri;
        }

        public final void onCancelled() {
            this.mInstrumentContext.cancelInstrument();
        }

        public final void onPostExecute(Object obj) {
            DrawableWithUri drawableWithUri = (DrawableWithUri) obj;
            if (drawableWithUri != null) {
                Drawable drawable = drawableWithUri.mDrawable;
                if (drawable != null) {
                    drawableWithUri.setDrawable(drawable);
                    ImageView imageView = (ImageView) drawableWithUri.mImageViewWeakReference.get();
                    int intrinsicWidth = drawableWithUri.mDrawable.getIntrinsicWidth();
                    if (imageView.getLayoutParams().width != intrinsicWidth) {
                        Log.d("BcSmartspaceCardBell", "imageView requestLayout " + drawableWithUri.mUri);
                        imageView.getLayoutParams().width = intrinsicWidth;
                        imageView.requestLayout();
                    }
                    LatencyInstrumentContext latencyInstrumentContext = this.mInstrumentContext;
                    Uri uri = drawableWithUri.mUri;
                    Set set = latencyInstrumentContext.mUriSet;
                    if (!set.isEmpty()) {
                        if (uri == null || !set.remove(uri)) {
                            latencyInstrumentContext.cancelInstrument();
                        } else if (set.isEmpty()) {
                            latencyInstrumentContext.mLatencyTracker.onActionEnd(22);
                        }
                    }
                } else {
                    BcSmartspaceTemplateDataUtils.updateVisibility((ImageView) drawableWithUri.mImageViewWeakReference.get(), 8);
                    this.mInstrumentContext.cancelInstrument();
                }
                BcSmartspaceTemplateDataUtils.updateVisibility((View) drawableWithUri.mLoadingScreenWeakReference.get(), 8);
            }
        }
    }

    public BcSmartspaceCardDoorbell(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void maybeResetImageView(SmartspaceTarget smartspaceTarget) {
        boolean z = !smartspaceTarget.getSmartspaceTargetId().equals(this.mPreviousTargetId);
        this.mPreviousTargetId = smartspaceTarget.getSmartspaceTargetId();
        if (z) {
            this.mImageView.getLayoutParams().width = -2;
            this.mImageView.setImageDrawable((Drawable) null);
            this.mUriToDrawable.clear();
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mLoadingScreenView = (ViewGroup) findViewById(2131362892);
        this.mProgressBar = (ProgressBar) findViewById(2131362725);
        this.mLoadingIcon = (ImageView) findViewById(2131362893);
    }

    public final void resetUi() {
        super.resetUi();
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mImageView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoadingScreenView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mProgressBar, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoadingIcon, 8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x02a1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02c6  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x02e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean setSmartspaceActions(android.app.smartspace.SmartspaceTarget r15, com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceEventNotifier r16, com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo r17) {
        /*
            r14 = this;
            r8 = r14
            android.content.Context r0 = r14.getContext()
            java.lang.String r0 = r0.getPackageName()
            java.lang.String r1 = "com.android.systemui"
            boolean r0 = r0.equals(r1)
            r9 = 0
            if (r0 != 0) goto L_0x0013
            return r9
        L_0x0013:
            android.app.smartspace.SmartspaceAction r0 = r15.getBaseAction()
            if (r0 != 0) goto L_0x001b
            r0 = 0
            goto L_0x001f
        L_0x001b:
            android.os.Bundle r0 = r0.getExtras()
        L_0x001f:
            java.util.List r1 = r15.getIconGrid()
            java.util.stream.Stream r1 = r1.stream()
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2 r2 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2
            r10 = 1
            r2.<init>(r10)
            java.util.stream.Stream r1 = r1.filter(r2)
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda3 r2 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda3
            r2.<init>(r9)
            java.util.stream.Stream r1 = r1.map(r2)
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda3 r2 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda3
            r2.<init>(r10)
            java.util.stream.Stream r1 = r1.map(r2)
            java.util.stream.Collector r2 = java.util.stream.Collectors.toList()
            java.lang.Object r1 = r1.collect(r2)
            java.util.List r1 = (java.util.List) r1
            boolean r2 = r1.isEmpty()
            r3 = 2131165888(0x7f0702c0, float:1.7946006E38)
            r4 = 2131165880(0x7f0702b8, float:1.794599E38)
            if (r2 != 0) goto L_0x0124
            if (r0 == 0) goto L_0x006b
            java.lang.String r2 = "frameDurationMs"
            boolean r2 = r0.containsKey(r2)
            if (r2 == 0) goto L_0x006b
            java.lang.String r2 = "frameDurationMs"
            int r0 = r0.getInt(r2)
            r8.mGifFrameDurationInMs = r0
        L_0x006b:
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$LatencyInstrumentContext r0 = r8.mLatencyInstrumentContext
            java.util.stream.Stream r2 = r1.stream()
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0 r5 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0
            r5.<init>(r14)
            java.util.stream.Stream r2 = r2.filter(r5)
            java.util.stream.Collector r5 = java.util.stream.Collectors.toSet()
            java.lang.Object r2 = r2.collect(r5)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Set r5 = r0.mUriSet
            if (r2 == 0) goto L_0x0091
            boolean r6 = r2.isEmpty()
            if (r6 != 0) goto L_0x0091
            r5.addAll(r2)
        L_0x0091:
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x0098
            goto L_0x009f
        L_0x0098:
            com.android.internal.util.LatencyTracker r0 = r0.mLatencyTracker
            r2 = 22
            r0.onActionStart(r2)
        L_0x009f:
            r14.maybeResetImageView(r15)
            android.widget.ImageView r0 = r8.mImageView
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r0, r9)
            android.content.Context r0 = r14.getContext()
            android.content.Context r0 = r0.getApplicationContext()
            android.content.ContentResolver r2 = r0.getContentResolver()
            android.content.res.Resources r0 = r14.getResources()
            int r4 = r0.getDimensionPixelSize(r4)
            android.content.res.Resources r0 = r14.getResources()
            float r5 = r0.getDimension(r3)
            java.lang.ref.WeakReference r6 = new java.lang.ref.WeakReference
            android.widget.ImageView r0 = r8.mImageView
            r6.<init>(r0)
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference
            android.view.ViewGroup r0 = r8.mLoadingScreenView
            r7.<init>(r0)
            java.util.stream.Stream r11 = r1.stream()
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda1 r12 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda1
            r13 = 0
            r0 = r12
            r1 = r14
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            java.util.stream.Stream r0 = r11.map(r12)
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2 r1 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2
            r1.<init>(r9)
            java.util.stream.Stream r0 = r0.filter(r1)
            java.util.stream.Collector r1 = java.util.stream.Collectors.toList()
            java.lang.Object r0 = r0.collect(r1)
            java.util.List r0 = (java.util.List) r0
            android.graphics.drawable.AnimationDrawable r1 = new android.graphics.drawable.AnimationDrawable
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0102:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0114
            java.lang.Object r2 = r0.next()
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            int r3 = r8.mGifFrameDurationInMs
            r1.addFrame(r2, r3)
            goto L_0x0102
        L_0x0114:
            android.widget.ImageView r0 = r8.mImageView
            r0.setImageDrawable(r1)
            r1.start()
            java.lang.String r0 = "BcSmartspaceCardBell"
            java.lang.String r1 = "imageUri is set"
            android.util.Log.d(r0, r1)
            return r10
        L_0x0124:
            if (r0 == 0) goto L_0x0182
            java.lang.String r1 = "imageBitmap"
            boolean r1 = r0.containsKey(r1)
            if (r1 == 0) goto L_0x0182
            java.lang.String r1 = "imageBitmap"
            java.lang.Object r0 = r0.get(r1)
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            r14.maybeResetImageView(r15)
            android.widget.ImageView r1 = r8.mImageView
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r1, r9)
            if (r0 == 0) goto L_0x0181
            int r1 = r0.getHeight()
            if (r1 == 0) goto L_0x0161
            android.content.res.Resources r1 = r14.getResources()
            float r1 = r1.getDimension(r4)
            int r1 = (int) r1
            int r2 = r0.getWidth()
            float r2 = (float) r2
            int r4 = r0.getHeight()
            float r4 = (float) r4
            float r2 = r2 / r4
            float r4 = (float) r1
            float r4 = r4 * r2
            int r2 = (int) r4
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r0, r2, r1, r10)
        L_0x0161:
            android.content.res.Resources r1 = r14.getResources()
            androidx.core.graphics.drawable.RoundedBitmapDrawable21 r2 = new androidx.core.graphics.drawable.RoundedBitmapDrawable21
            r2.<init>(r1, r0)
            android.content.res.Resources r0 = r14.getResources()
            float r0 = r0.getDimension(r3)
            r2.setCornerRadius(r0)
            android.widget.ImageView r0 = r8.mImageView
            r0.setImageDrawable(r2)
            java.lang.String r0 = "BcSmartspaceCardBell"
            java.lang.String r1 = "imageBitmap is set"
            android.util.Log.d(r0, r1)
        L_0x0181:
            return r10
        L_0x0182:
            if (r0 == 0) goto L_0x02eb
            java.lang.String r1 = "loadingScreenState"
            boolean r1 = r0.containsKey(r1)
            if (r1 == 0) goto L_0x02eb
            java.lang.String r1 = "loadingScreenState"
            int r1 = r0.getInt(r1)
            java.lang.String r2 = com.google.android.systemui.smartspace.BcSmartSpaceUtil.getDimensionRatio(r0)
            if (r2 != 0) goto L_0x0199
            return r9
        L_0x0199:
            r14.maybeResetImageView(r15)
            android.widget.ImageView r3 = r8.mImageView
            r4 = 8
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r3, r4)
            android.view.ViewGroup r3 = r8.mLoadingScreenView
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r3 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r3
            r3.dimensionRatio = r2
            android.content.Context r2 = r14.getContext()
            r3 = 2131100700(0x7f06041c, float:1.7813789E38)
            int r2 = r2.getColor(r3)
            android.view.ViewGroup r3 = r8.mLoadingScreenView
            android.content.res.ColorStateList r2 = android.content.res.ColorStateList.valueOf(r2)
            r3.setBackgroundTintList(r2)
            android.view.ViewGroup r2 = r8.mLoadingScreenView
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r2, r9)
            android.widget.ProgressBar r2 = r8.mProgressBar
            java.lang.String r3 = "progressBarWidth"
            boolean r5 = r0.containsKey(r3)
            if (r5 == 0) goto L_0x01eb
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            android.content.Context r5 = r14.getContext()
            int r3 = r0.getInt(r3)
            android.content.res.Resources r5 = r5.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            float r5 = r5.density
            float r3 = (float) r3
            float r5 = r5 * r3
            int r3 = (int) r5
            r2.width = r3
        L_0x01eb:
            android.widget.ProgressBar r2 = r8.mProgressBar
            java.lang.String r3 = "progressBarHeight"
            boolean r5 = r0.containsKey(r3)
            if (r5 == 0) goto L_0x0210
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            android.content.Context r5 = r14.getContext()
            int r3 = r0.getInt(r3)
            android.content.res.Resources r5 = r5.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            float r5 = r5.density
            float r3 = (float) r3
            float r5 = r5 * r3
            int r3 = (int) r5
            r2.height = r3
        L_0x0210:
            android.content.Context r2 = r14.getContext()
            r3 = 2131100701(0x7f06041d, float:1.781379E38)
            int r2 = r2.getColor(r3)
            android.widget.ProgressBar r5 = r8.mProgressBar
            android.content.res.ColorStateList r2 = android.content.res.ColorStateList.valueOf(r2)
            r5.setIndeterminateTintList(r2)
            android.widget.ProgressBar r2 = r8.mProgressBar
            r5 = 4
            if (r1 != r10) goto L_0x022a
            goto L_0x0234
        L_0x022a:
            if (r1 != r5) goto L_0x0236
            java.lang.String r6 = "progressBarVisible"
            boolean r6 = r0.getBoolean(r6, r10)
            if (r6 == 0) goto L_0x0236
        L_0x0234:
            r6 = r9
            goto L_0x0237
        L_0x0236:
            r6 = r4
        L_0x0237:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r2, r6)
            r2 = 2
            if (r1 != r2) goto L_0x0251
            android.widget.ImageView r1 = r8.mLoadingIcon
            android.content.Context r2 = r14.getContext()
            java.lang.Object r3 = androidx.core.content.ContextCompat.sLock
            r3 = 2131233647(0x7f080b6f, float:1.8083437E38)
            android.graphics.drawable.Drawable r2 = r2.getDrawable(r3)
            r1.setImageDrawable(r2)
        L_0x024f:
            r1 = r10
            goto L_0x0297
        L_0x0251:
            r2 = 3
            if (r1 != r2) goto L_0x0267
            android.widget.ImageView r1 = r8.mLoadingIcon
            android.content.Context r2 = r14.getContext()
            java.lang.Object r3 = androidx.core.content.ContextCompat.sLock
            r3 = 2131233648(0x7f080b70, float:1.808344E38)
            android.graphics.drawable.Drawable r2 = r2.getDrawable(r3)
            r1.setImageDrawable(r2)
            goto L_0x024f
        L_0x0267:
            if (r1 == r5) goto L_0x026b
        L_0x0269:
            r1 = r9
            goto L_0x0297
        L_0x026b:
            java.lang.String r1 = "loadingScreenIcon"
            boolean r1 = r0.containsKey(r1)
            if (r1 != 0) goto L_0x0274
            goto L_0x0269
        L_0x0274:
            java.lang.String r1 = "loadingScreenIcon"
            java.lang.Object r1 = r0.get(r1)
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            android.widget.ImageView r2 = r8.mLoadingIcon
            r2.setImageBitmap(r1)
            java.lang.String r1 = "tintLoadingIcon"
            boolean r1 = r0.getBoolean(r1, r9)
            if (r1 == 0) goto L_0x024f
            android.content.Context r1 = r14.getContext()
            int r1 = r1.getColor(r3)
            android.widget.ImageView r2 = r8.mLoadingIcon
            r2.setColorFilter(r1)
            goto L_0x024f
        L_0x0297:
            android.widget.ImageView r2 = r8.mLoadingIcon
            java.lang.String r3 = "loadingIconWidth"
            boolean r5 = r0.containsKey(r3)
            if (r5 == 0) goto L_0x02bc
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            android.content.Context r5 = r14.getContext()
            int r3 = r0.getInt(r3)
            android.content.res.Resources r5 = r5.getResources()
            android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
            float r5 = r5.density
            float r3 = (float) r3
            float r5 = r5 * r3
            int r3 = (int) r5
            r2.width = r3
        L_0x02bc:
            android.widget.ImageView r2 = r8.mLoadingIcon
            java.lang.String r3 = "loadingIconHeight"
            boolean r5 = r0.containsKey(r3)
            if (r5 == 0) goto L_0x02e1
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            android.content.Context r5 = r14.getContext()
            int r0 = r0.getInt(r3)
            android.content.res.Resources r3 = r5.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            float r3 = r3.density
            float r0 = (float) r0
            float r3 = r3 * r0
            int r0 = (int) r3
            r2.height = r0
        L_0x02e1:
            android.widget.ImageView r0 = r8.mLoadingIcon
            if (r1 == 0) goto L_0x02e6
            goto L_0x02e7
        L_0x02e6:
            r9 = r4
        L_0x02e7:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r0, r9)
            return r10
        L_0x02eb:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell.setSmartspaceActions(android.app.smartspace.SmartspaceTarget, com.android.systemui.plugins.BcSmartspaceDataPlugin$SmartspaceEventNotifier, com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo):boolean");
    }

    public BcSmartspaceCardDoorbell(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUriToDrawable = new HashMap();
        this.mGifFrameDurationInMs = 200;
        this.mLatencyInstrumentContext = new LatencyInstrumentContext(context);
    }
}
