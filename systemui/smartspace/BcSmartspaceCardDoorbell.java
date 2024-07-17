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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class BcSmartspaceCardDoorbell extends BcSmartspaceCardGenericImage {
    public static final /* synthetic */ int $r8$clinit = 0;
    public int mGifFrameDurationInMs;
    public final LatencyInstrumentContext mLatencyInstrumentContext;
    public ImageView mLoadingIcon;
    public ViewGroup mLoadingScreenView;
    public String mPreviousTargetId;
    public ProgressBar mProgressBar;
    public final Map mUriToDrawable;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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

        public DrawableWithUri(Uri uri, ContentResolver contentResolver, int i, float f, WeakReference weakReference, WeakReference weakReference2) {
            super(new ColorDrawable(0));
            this.mRoundedCornersRadius = f;
            this.mUri = uri;
            this.mHeightInPx = i;
            this.mContentResolver = contentResolver;
            this.mImageViewWeakReference = weakReference;
            this.mLoadingScreenWeakReference = weakReference2;
        }

        public final void draw(Canvas canvas) {
            int save = canvas.save();
            canvas.clipPath(this.mClipPath);
            super.draw(canvas);
            canvas.restoreToCount(save);
        }

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

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
                    drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource((Resources) null, openInputStream), new BcSmartspaceCardDoorbell$$ExternalSyntheticLambda7(i));
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
        this.mLoadingScreenView = (ViewGroup) findViewById(2131362915);
        this.mProgressBar = (ProgressBar) findViewById(2131362745);
        this.mLoadingIcon = (ImageView) findViewById(2131362916);
    }

    public final void resetUi() {
        super.resetUi();
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mImageView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoadingScreenView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mProgressBar, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mLoadingIcon, 8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:72:0x0297  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x02dc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean setSmartspaceActions(android.app.smartspace.SmartspaceTarget r15, com.android.systemui.plugins.BcSmartspaceDataPlugin.SmartspaceEventNotifier r16, com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo r17) {
        /*
            r14 = this;
            r8 = r14
            android.content.Context r0 = r14.getContext()
            java.lang.String r0 = r0.getPackageName()
            java.lang.String r1 = "com.android.systemui"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 != 0) goto L_0x0013
            return r1
        L_0x0013:
            android.app.smartspace.SmartspaceAction r0 = r15.getBaseAction()
            if (r0 != 0) goto L_0x001b
            r0 = 0
            goto L_0x001f
        L_0x001b:
            android.os.Bundle r0 = r0.getExtras()
        L_0x001f:
            java.util.List r2 = r15.getIconGrid()
            java.util.stream.Stream r2 = r2.stream()
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2 r3 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2
            r4 = 1
            r3.<init>(r4)
            java.util.stream.Stream r2 = r2.filter(r3)
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4 r3 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4
            r4 = 0
            r3.<init>(r4)
            java.util.stream.Stream r2 = r2.map(r3)
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4 r3 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda4
            r4 = 1
            r3.<init>(r4)
            java.util.stream.Stream r2 = r2.map(r3)
            java.util.stream.Collector r3 = java.util.stream.Collectors.toList()
            java.lang.Object r2 = r2.collect(r3)
            java.util.List r2 = (java.util.List) r2
            boolean r3 = r2.isEmpty()
            java.lang.String r9 = "BcSmartspaceCardBell"
            r4 = 2131165920(0x7f0702e0, float:1.794607E38)
            r5 = 2131165912(0x7f0702d8, float:1.7946055E38)
            r10 = 1
            if (r3 != 0) goto L_0x0126
            if (r0 == 0) goto L_0x006e
            java.lang.String r3 = "frameDurationMs"
            boolean r6 = r0.containsKey(r3)
            if (r6 == 0) goto L_0x006e
            int r0 = r0.getInt(r3)
            r8.mGifFrameDurationInMs = r0
        L_0x006e:
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$LatencyInstrumentContext r0 = r8.mLatencyInstrumentContext
            java.util.stream.Stream r3 = r2.stream()
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0 r6 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda0
            r6.<init>(r14)
            java.util.stream.Stream r3 = r3.filter(r6)
            java.util.stream.Collector r6 = java.util.stream.Collectors.toSet()
            java.lang.Object r3 = r3.collect(r6)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Set r6 = r0.mUriSet
            if (r3 == 0) goto L_0x0094
            boolean r7 = r3.isEmpty()
            if (r7 != 0) goto L_0x0094
            r6.addAll(r3)
        L_0x0094:
            boolean r3 = r6.isEmpty()
            if (r3 == 0) goto L_0x009b
            goto L_0x00a2
        L_0x009b:
            com.android.internal.util.LatencyTracker r0 = r0.mLatencyTracker
            r3 = 22
            r0.onActionStart(r3)
        L_0x00a2:
            r14.maybeResetImageView(r15)
            android.widget.ImageView r0 = r8.mImageView
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r0, r1)
            android.content.Context r0 = r14.getContext()
            android.content.Context r0 = r0.getApplicationContext()
            android.content.ContentResolver r3 = r0.getContentResolver()
            android.content.res.Resources r0 = r14.getResources()
            int r5 = r0.getDimensionPixelSize(r5)
            android.content.res.Resources r0 = r14.getResources()
            float r4 = r0.getDimension(r4)
            java.lang.ref.WeakReference r6 = new java.lang.ref.WeakReference
            android.widget.ImageView r0 = r8.mImageView
            r6.<init>(r0)
            java.lang.ref.WeakReference r7 = new java.lang.ref.WeakReference
            android.view.ViewGroup r0 = r8.mLoadingScreenView
            r7.<init>(r0)
            java.util.stream.Stream r11 = r2.stream()
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda1 r12 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda1
            r13 = 0
            r0 = r12
            r1 = r14
            r2 = r3
            r3 = r5
            r5 = r6
            r6 = r7
            r7 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            java.util.stream.Stream r0 = r11.map(r12)
            com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2 r1 = new com.google.android.systemui.smartspace.BcSmartspaceCardDoorbell$$ExternalSyntheticLambda2
            r2 = 0
            r1.<init>(r2)
            java.util.stream.Stream r0 = r0.filter(r1)
            java.util.stream.Collector r1 = java.util.stream.Collectors.toList()
            java.lang.Object r0 = r0.collect(r1)
            java.util.List r0 = (java.util.List) r0
            android.graphics.drawable.AnimationDrawable r1 = new android.graphics.drawable.AnimationDrawable
            r1.<init>()
            java.util.Iterator r0 = r0.iterator()
        L_0x0106:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0118
            java.lang.Object r2 = r0.next()
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            int r3 = r8.mGifFrameDurationInMs
            r1.addFrame(r2, r3)
            goto L_0x0106
        L_0x0118:
            android.widget.ImageView r0 = r8.mImageView
            r0.setImageDrawable(r1)
            r1.start()
            java.lang.String r0 = "imageUri is set"
            android.util.Log.d(r9, r0)
            return r10
        L_0x0126:
            if (r0 == 0) goto L_0x0180
            java.lang.String r2 = "imageBitmap"
            boolean r3 = r0.containsKey(r2)
            if (r3 == 0) goto L_0x0180
            java.lang.Object r0 = r0.get(r2)
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            r14.maybeResetImageView(r15)
            android.widget.ImageView r2 = r8.mImageView
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r2, r1)
            if (r0 == 0) goto L_0x017f
            int r1 = r0.getHeight()
            if (r1 == 0) goto L_0x0161
            android.content.res.Resources r1 = r14.getResources()
            float r1 = r1.getDimension(r5)
            int r1 = (int) r1
            int r2 = r0.getWidth()
            float r2 = (float) r2
            int r3 = r0.getHeight()
            float r3 = (float) r3
            float r2 = r2 / r3
            float r3 = (float) r1
            float r3 = r3 * r2
            int r2 = (int) r3
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r0, r2, r1, r10)
        L_0x0161:
            android.content.res.Resources r1 = r14.getResources()
            androidx.core.graphics.drawable.RoundedBitmapDrawable21 r2 = new androidx.core.graphics.drawable.RoundedBitmapDrawable21
            r2.<init>(r1, r0)
            android.content.res.Resources r0 = r14.getResources()
            float r0 = r0.getDimension(r4)
            r2.setCornerRadius(r0)
            android.widget.ImageView r0 = r8.mImageView
            r0.setImageDrawable(r2)
            java.lang.String r0 = "imageBitmap is set"
            android.util.Log.d(r9, r0)
        L_0x017f:
            return r10
        L_0x0180:
            if (r0 == 0) goto L_0x02e1
            java.lang.String r2 = "loadingScreenState"
            boolean r3 = r0.containsKey(r2)
            if (r3 == 0) goto L_0x02e1
            int r2 = r0.getInt(r2)
            java.lang.String r3 = com.google.android.systemui.smartspace.BcSmartSpaceUtil.getDimensionRatio(r0)
            if (r3 != 0) goto L_0x0195
            return r1
        L_0x0195:
            r14.maybeResetImageView(r15)
            android.widget.ImageView r4 = r8.mImageView
            r5 = 8
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r4, r5)
            android.view.ViewGroup r4 = r8.mLoadingScreenView
            android.view.ViewGroup$LayoutParams r4 = r4.getLayoutParams()
            androidx.constraintlayout.widget.ConstraintLayout$LayoutParams r4 = (androidx.constraintlayout.widget.ConstraintLayout.LayoutParams) r4
            r4.dimensionRatio = r3
            android.content.Context r3 = r14.getContext()
            r4 = 2131100718(0x7f06042e, float:1.7813825E38)
            int r3 = r3.getColor(r4)
            android.view.ViewGroup r4 = r8.mLoadingScreenView
            android.content.res.ColorStateList r3 = android.content.res.ColorStateList.valueOf(r3)
            r4.setBackgroundTintList(r3)
            android.view.ViewGroup r3 = r8.mLoadingScreenView
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r3, r1)
            android.widget.ProgressBar r3 = r8.mProgressBar
            java.lang.String r4 = "progressBarWidth"
            boolean r6 = r0.containsKey(r4)
            if (r6 == 0) goto L_0x01e7
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.content.Context r6 = r14.getContext()
            int r4 = r0.getInt(r4)
            android.content.res.Resources r6 = r6.getResources()
            android.util.DisplayMetrics r6 = r6.getDisplayMetrics()
            float r6 = r6.density
            float r4 = (float) r4
            float r6 = r6 * r4
            int r4 = (int) r6
            r3.width = r4
        L_0x01e7:
            android.widget.ProgressBar r3 = r8.mProgressBar
            java.lang.String r4 = "progressBarHeight"
            boolean r6 = r0.containsKey(r4)
            if (r6 == 0) goto L_0x020c
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.content.Context r6 = r14.getContext()
            int r4 = r0.getInt(r4)
            android.content.res.Resources r6 = r6.getResources()
            android.util.DisplayMetrics r6 = r6.getDisplayMetrics()
            float r6 = r6.density
            float r4 = (float) r4
            float r6 = r6 * r4
            int r4 = (int) r6
            r3.height = r4
        L_0x020c:
            android.content.Context r3 = r14.getContext()
            r4 = 2131100719(0x7f06042f, float:1.7813827E38)
            int r3 = r3.getColor(r4)
            android.widget.ProgressBar r6 = r8.mProgressBar
            android.content.res.ColorStateList r3 = android.content.res.ColorStateList.valueOf(r3)
            r6.setIndeterminateTintList(r3)
            android.widget.ProgressBar r3 = r8.mProgressBar
            r6 = 4
            if (r2 != r10) goto L_0x0226
            goto L_0x0230
        L_0x0226:
            if (r2 != r6) goto L_0x0232
            java.lang.String r7 = "progressBarVisible"
            boolean r7 = r0.getBoolean(r7, r10)
            if (r7 == 0) goto L_0x0232
        L_0x0230:
            r7 = r1
            goto L_0x0233
        L_0x0232:
            r7 = r5
        L_0x0233:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r3, r7)
            r3 = 2
            if (r2 != r3) goto L_0x024b
            android.widget.ImageView r2 = r8.mLoadingIcon
            android.content.Context r3 = r14.getContext()
            r4 = 2131233705(0x7f080ba9, float:1.8083555E38)
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r4)
            r2.setImageDrawable(r3)
        L_0x0249:
            r2 = r10
            goto L_0x028d
        L_0x024b:
            r3 = 3
            if (r2 != r3) goto L_0x025f
            android.widget.ImageView r2 = r8.mLoadingIcon
            android.content.Context r3 = r14.getContext()
            r4 = 2131233706(0x7f080baa, float:1.8083557E38)
            android.graphics.drawable.Drawable r3 = r3.getDrawable(r4)
            r2.setImageDrawable(r3)
            goto L_0x0249
        L_0x025f:
            if (r2 == r6) goto L_0x0263
        L_0x0261:
            r2 = r1
            goto L_0x028d
        L_0x0263:
            java.lang.String r2 = "loadingScreenIcon"
            boolean r3 = r0.containsKey(r2)
            if (r3 != 0) goto L_0x026c
            goto L_0x0261
        L_0x026c:
            java.lang.Object r2 = r0.get(r2)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            android.widget.ImageView r3 = r8.mLoadingIcon
            r3.setImageBitmap(r2)
            java.lang.String r2 = "tintLoadingIcon"
            boolean r2 = r0.getBoolean(r2, r1)
            if (r2 == 0) goto L_0x0249
            android.content.Context r2 = r14.getContext()
            int r2 = r2.getColor(r4)
            android.widget.ImageView r3 = r8.mLoadingIcon
            r3.setColorFilter(r2)
            goto L_0x0249
        L_0x028d:
            android.widget.ImageView r3 = r8.mLoadingIcon
            java.lang.String r4 = "loadingIconWidth"
            boolean r6 = r0.containsKey(r4)
            if (r6 == 0) goto L_0x02b2
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.content.Context r6 = r14.getContext()
            int r4 = r0.getInt(r4)
            android.content.res.Resources r6 = r6.getResources()
            android.util.DisplayMetrics r6 = r6.getDisplayMetrics()
            float r6 = r6.density
            float r4 = (float) r4
            float r6 = r6 * r4
            int r4 = (int) r6
            r3.width = r4
        L_0x02b2:
            android.widget.ImageView r3 = r8.mLoadingIcon
            java.lang.String r4 = "loadingIconHeight"
            boolean r6 = r0.containsKey(r4)
            if (r6 == 0) goto L_0x02d7
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.content.Context r6 = r14.getContext()
            int r0 = r0.getInt(r4)
            android.content.res.Resources r4 = r6.getResources()
            android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
            float r4 = r4.density
            float r0 = (float) r0
            float r4 = r4 * r0
            int r0 = (int) r4
            r3.height = r0
        L_0x02d7:
            android.widget.ImageView r0 = r8.mLoadingIcon
            if (r2 == 0) goto L_0x02dc
            goto L_0x02dd
        L_0x02dc:
            r1 = r5
        L_0x02dd:
            com.google.android.systemui.smartspace.BcSmartspaceTemplateDataUtils.updateVisibility(r0, r1)
            return r10
        L_0x02e1:
            return r1
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
