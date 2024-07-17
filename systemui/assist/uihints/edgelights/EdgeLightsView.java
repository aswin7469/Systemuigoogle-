package com.google.android.systemui.assist.uihints.edgelights;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.metrics.LogMaker;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.assist.ui.EdgeLight;
import com.android.systemui.assist.ui.PathSpecCornerPathRenderer;
import com.android.systemui.assist.ui.PerimeterPathGuide;
import com.google.android.systemui.assist.uihints.DisplayUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class EdgeLightsView extends View {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final List mAssistInvocationLights;
    public EdgeLight[] mAssistLights;
    public final Set mListeners;
    public Mode mMode;
    public final Paint mPaint;
    public final Path mPath;
    public final PerimeterPathGuide mPerimeterPathGuide;
    public final int[] mScreenLocation;

    public EdgeLightsView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.util.function.Consumer, java.lang.Object] */
    public final void commitModeTransition(Mode mode) {
        mode.start(this, this.mPerimeterPathGuide, this.mMode);
        this.mMode = mode;
        this.mListeners.forEach(new EdgeLightsView$$ExternalSyntheticLambda0(this, 0));
        ((ArrayList) this.mAssistInvocationLights).forEach(new Object());
        invalidate();
    }

    public final EdgeLight[] getAssistLights() {
        if (Looper.getMainLooper().isCurrentThread()) {
            return this.mAssistLights;
        }
        throw new IllegalStateException("Must be called from main thread");
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mPerimeterPathGuide.setRotation(getContext().getDisplay().getRotation());
        this.mMode.onConfigurationChanged();
    }

    public final void onDraw(Canvas canvas) {
        getLocationOnScreen(this.mScreenLocation);
        int[] iArr = this.mScreenLocation;
        canvas.translate((float) (-iArr[0]), (float) (-iArr[1]));
        EdgeLight[] edgeLightArr = this.mAssistLights;
        if (edgeLightArr.length != 0) {
            this.mPaint.setStrokeCap(Paint.Cap.ROUND);
            renderLight(canvas, edgeLightArr[0]);
            if (edgeLightArr.length > 1) {
                renderLight(canvas, edgeLightArr[edgeLightArr.length - 1]);
            }
            if (edgeLightArr.length > 2) {
                this.mPaint.setStrokeCap(Paint.Cap.BUTT);
                for (int i = 1; i < edgeLightArr.length - 1; i++) {
                    renderLight(canvas, edgeLightArr[i]);
                }
            }
        }
        List list = this.mAssistInvocationLights;
        if (!list.isEmpty()) {
            this.mPaint.setStrokeCap(Paint.Cap.ROUND);
            ArrayList arrayList = (ArrayList) list;
            renderLight(canvas, (EdgeLight) arrayList.get(0));
            if (arrayList.size() > 1) {
                renderLight(canvas, (EdgeLight) CascadingMenuPopup$$ExternalSyntheticOutline0.m(arrayList, 1));
            }
            if (arrayList.size() > 2) {
                this.mPaint.setStrokeCap(Paint.Cap.BUTT);
                for (EdgeLight renderLight : list.subList(1, arrayList.size() - 1)) {
                    renderLight(canvas, renderLight);
                }
            }
        }
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mPerimeterPathGuide.setRotation(getContext().getDisplay().getRotation());
    }

    public final void renderLight(Canvas canvas, EdgeLight edgeLight) {
        PerimeterPathGuide perimeterPathGuide = this.mPerimeterPathGuide;
        Path path = this.mPath;
        float f = edgeLight.mStart;
        perimeterPathGuide.strokeSegment(path, f, edgeLight.mLength + f);
        this.mPaint.setColor(edgeLight.mColor);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    public final void setAssistLights(EdgeLight[] edgeLightArr) {
        post(new EdgeLightsView$$ExternalSyntheticLambda2(this, edgeLightArr));
    }

    public final void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        if (visibility == 8) {
            this.mPerimeterPathGuide.setRotation(getContext().getDisplay().getRotation());
        }
    }

    public EdgeLightsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EdgeLightsView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    /* JADX WARNING: type inference failed for: r9v1, types: [com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView$Mode, java.lang.Object] */
    public EdgeLightsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3;
        int i4;
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mAssistLights = new EdgeLight[0];
        ArrayList arrayList = new ArrayList();
        this.mAssistInvocationLights = arrayList;
        this.mPath = new Path();
        this.mListeners = new HashSet();
        this.mScreenLocation = new int[2];
        Display defaultDisplay = DisplayUtils.getDefaultDisplay(context);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        int ceil = (int) Math.ceil((double) (3.0f * displayMetrics.density));
        paint.setStrokeWidth((float) ceil);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setAntiAlias(true);
        PathSpecCornerPathRenderer pathSpecCornerPathRenderer = new PathSpecCornerPathRenderer(context);
        int i5 = ceil / 2;
        Display defaultDisplay2 = DisplayUtils.getDefaultDisplay(context);
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay2.getRealMetrics(displayMetrics2);
        int rotation = defaultDisplay2.getRotation();
        if (rotation == 0 || rotation == 2) {
            i3 = displayMetrics2.widthPixels;
        } else {
            i3 = displayMetrics2.heightPixels;
        }
        int i6 = i3;
        Display defaultDisplay3 = DisplayUtils.getDefaultDisplay(context);
        DisplayMetrics displayMetrics3 = new DisplayMetrics();
        defaultDisplay3.getRealMetrics(displayMetrics3);
        int rotation2 = defaultDisplay3.getRotation();
        if (rotation2 == 0 || rotation2 == 2) {
            i4 = displayMetrics3.heightPixels;
        } else {
            i4 = displayMetrics3.widthPixels;
        }
        this.mPerimeterPathGuide = new PerimeterPathGuide(context, pathSpecCornerPathRenderer, i5, i6, i4);
        ? obj = new Object();
        this.mMode = obj;
        commitModeTransition(obj);
        Resources resources = getResources();
        arrayList.add(new EdgeLight(resources.getColor(2131099879)));
        arrayList.add(new EdgeLight(resources.getColor(2131099881)));
        arrayList.add(new EdgeLight(resources.getColor(2131099882)));
        arrayList.add(new EdgeLight(resources.getColor(2131099880)));
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface Mode {
        int getSubType();

        void logState() {
            MetricsLogger.action(new LogMaker(1716).setType(6).setSubtype(getSubType()));
        }

        void onNewModeRequest(EdgeLightsView edgeLightsView, Mode mode);

        boolean preventsInvocations() {
            return false;
        }

        void start(EdgeLightsView edgeLightsView, PerimeterPathGuide perimeterPathGuide, Mode mode);

        void onAudioLevelUpdate(float f) {
        }

        void onConfigurationChanged() {
        }
    }
}
