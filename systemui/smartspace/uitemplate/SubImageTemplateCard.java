package com.google.android.systemui.smartspace.uitemplate;

import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.uitemplatedata.SubImageTemplateData;
import android.app.smartspace.uitemplatedata.TapAction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.BcSmartSpaceUtil;
import com.google.android.systemui.smartspace.BcSmartspaceCardSecondary;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggerUtil;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class SubImageTemplateCard extends BcSmartspaceCardSecondary {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Handler mHandler;
    public final Map mIconDrawableCache;
    public final int mImageHeight;
    public ImageView mImageView;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class DrawableWrapper {
        public final ContentResolver mContentResolver;
        public Drawable mDrawable;
        public final int mHeightInPx;
        public final Icon.OnDrawableLoadedListener mListener;
        public final Uri mUri;

        public DrawableWrapper(Uri uri, ContentResolver contentResolver, int i, SubImageTemplateCard$$ExternalSyntheticLambda0 subImageTemplateCard$$ExternalSyntheticLambda0) {
            this.mUri = uri;
            this.mHeightInPx = i;
            this.mContentResolver = contentResolver;
            this.mListener = subImageTemplateCard$$ExternalSyntheticLambda0;
        }
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class LoadUriTask extends AsyncTask {
        public final Object doInBackground(Object[] objArr) {
            DrawableWrapper[] drawableWrapperArr = (DrawableWrapper[]) objArr;
            Drawable drawable = null;
            if (drawableWrapperArr.length <= 0) {
                return null;
            }
            DrawableWrapper drawableWrapper = drawableWrapperArr[0];
            try {
                InputStream openInputStream = drawableWrapper.mContentResolver.openInputStream(drawableWrapper.mUri);
                int i = drawableWrapper.mHeightInPx;
                int i2 = SubImageTemplateCard.$r8$clinit;
                try {
                    drawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource((Resources) null, openInputStream), new SubImageTemplateCard$$ExternalSyntheticLambda3(i));
                } catch (IOException e) {
                    Log.e("SubImageTemplateCard", "Unable to decode stream: " + e);
                }
                drawableWrapper.mDrawable = drawable;
            } catch (Exception e2) {
                Log.w("SubImageTemplateCard", "open uri:" + drawableWrapper.mUri + " got exception:" + e2);
            }
            return drawableWrapper;
        }

        public final void onPostExecute(Object obj) {
            DrawableWrapper drawableWrapper = (DrawableWrapper) obj;
            drawableWrapper.mListener.onDrawableLoaded(drawableWrapper.mDrawable);
        }
    }

    public SubImageTemplateCard(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mImageView = (ImageView) findViewById(2131362735);
    }

    public final void resetUi() {
        Map map = this.mIconDrawableCache;
        if (map != null) {
            map.clear();
        }
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.getLayoutParams().width = -2;
            this.mImageView.setImageDrawable((Drawable) null);
            this.mImageView.setBackgroundTintList((ColorStateList) null);
        }
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        String str;
        int i;
        TreeMap treeMap;
        TapAction tapAction;
        String sb;
        SubImageTemplateData templateData = smartspaceTarget.getTemplateData();
        if (!BcSmartspaceCardLoggerUtil.containsValidTemplateType(templateData) || templateData.getSubImages() == null || templateData.getSubImages().isEmpty()) {
            Log.w("SubImageTemplateCard", "SubImageTemplateData is null or has no SubImage or invalid template type");
            return false;
        }
        List subImages = templateData.getSubImages();
        TapAction subImageAction = templateData.getSubImageAction();
        if (this.mImageView == null) {
            Log.w("SubImageTemplateCard", "No image view can be updated. Skipping background update...");
        } else if (!(subImageAction == null || subImageAction.getExtras() == null)) {
            Bundle extras = subImageAction.getExtras();
            String string = extras.getString("imageDimensionRatio", "");
            if (!TextUtils.isEmpty(string)) {
                this.mImageView.getLayoutParams().width = 0;
                ((ConstraintLayout.LayoutParams) this.mImageView.getLayoutParams()).dimensionRatio = string;
            }
            if (extras.getBoolean("shouldShowBackground", false)) {
                this.mImageView.setBackgroundTintList(ColorStateList.valueOf(getContext().getColor(2131100718)));
            }
        }
        int i2 = 200;
        if (!(subImageAction == null || subImageAction.getExtras() == null)) {
            i2 = subImageAction.getExtras().getInt("GifFrameDurationMillis", 200);
        }
        int i3 = i2;
        ContentResolver contentResolver = getContext().getApplicationContext().getContentResolver();
        TreeMap treeMap2 = new TreeMap();
        WeakReference weakReference = new WeakReference(this.mImageView);
        String str2 = this.mPrevSmartspaceTargetId;
        int i4 = 0;
        while (i4 < subImages.size()) {
            android.app.smartspace.uitemplatedata.Icon icon = (android.app.smartspace.uitemplatedata.Icon) subImages.get(i4);
            if (icon == null || icon.getIcon() == null) {
                i = i4;
                str = str2;
                tapAction = subImageAction;
                treeMap = treeMap2;
            } else {
                Icon icon2 = icon.getIcon();
                StringBuilder sb2 = new StringBuilder(icon2.getType());
                switch (icon2.getType()) {
                    case 1:
                    case 5:
                        sb2.append(icon2.getBitmap().hashCode());
                        sb = sb2.toString();
                        break;
                    case 2:
                        sb2.append(icon2.getResPackage());
                        sb2.append(String.format("0x%08x", new Object[]{Integer.valueOf(icon2.getResId())}));
                        sb = sb2.toString();
                        break;
                    case 3:
                        sb2.append(Arrays.hashCode(icon2.getDataBytes()));
                        sb = sb2.toString();
                        break;
                    case 4:
                    case 6:
                        sb2.append(icon2.getUriString());
                        sb = sb2.toString();
                        break;
                    default:
                        sb = sb2.toString();
                        break;
                }
                String str3 = sb;
                tapAction = subImageAction;
                SubImageTemplateCard$$ExternalSyntheticLambda0 subImageTemplateCard$$ExternalSyntheticLambda0 = r0;
                TreeMap treeMap3 = treeMap2;
                treeMap = treeMap2;
                String str4 = str3;
                Icon icon3 = icon2;
                i = i4;
                str = str2;
                SubImageTemplateCard$$ExternalSyntheticLambda0 subImageTemplateCard$$ExternalSyntheticLambda02 = new SubImageTemplateCard$$ExternalSyntheticLambda0(this, str2, str3, treeMap3, i4, subImages, i3, weakReference);
                if (this.mIconDrawableCache.containsKey(str4) && this.mIconDrawableCache.get(str4) != null) {
                    subImageTemplateCard$$ExternalSyntheticLambda0.onDrawableLoaded((Drawable) this.mIconDrawableCache.get(str4));
                } else if (icon3.getType() == 4) {
                    new AsyncTask().execute(new DrawableWrapper[]{new DrawableWrapper(icon3.getUri(), contentResolver, this.mImageHeight, subImageTemplateCard$$ExternalSyntheticLambda0)});
                } else {
                    icon3.loadDrawableAsync(getContext(), subImageTemplateCard$$ExternalSyntheticLambda0, this.mHandler);
                }
            }
            i4 = i + 1;
            subImageAction = tapAction;
            treeMap2 = treeMap;
            str2 = str;
        }
        TapAction tapAction2 = subImageAction;
        if (tapAction2 == null) {
            return true;
        }
        BcSmartSpaceUtil.setOnClickListener((View) this, smartspaceTarget, tapAction2, smartspaceEventNotifier, "SubImageTemplateCard", bcSmartspaceCardLoggingInfo, 0);
        return true;
    }

    public SubImageTemplateCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIconDrawableCache = new HashMap();
        this.mHandler = new Handler();
        this.mImageHeight = getResources().getDimensionPixelOffset(2131165912);
    }

    public final void setTextColor(int i) {
    }
}
