package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class HighlightView extends FrameLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Paint backgroundPaint;
    public final float highlightCornerRadius;
    public final Paint highlightPaint;
    public float highlightProgress = 0.0f;
    public final ArrayList highlights = new ArrayList();
    public final List listeners = new ArrayList();
    public float pathChangeProgress = 0.0f;
    public final Rect tmpRect = new Rect();
    public final RectF tmpRectF = new RectF();

    static {
        new FloatProperty(0, "highlightProgress") {
            public final Object get(Object obj) {
                int i = 1;
                switch (i) {
                    case 0:
                        HighlightView highlightView = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                return Float.valueOf(highlightView.highlightProgress);
                            default:
                                return Float.valueOf(highlightView.pathChangeProgress);
                        }
                    default:
                        HighlightView highlightView2 = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                return Float.valueOf(highlightView2.highlightProgress);
                            default:
                                return Float.valueOf(highlightView2.pathChangeProgress);
                        }
                }
            }

            public final void setValue(Object obj, float f) {
                int i = 1;
                switch (i) {
                    case 0:
                        HighlightView highlightView = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                highlightView.highlightProgress = f;
                                highlightView.invalidate();
                                return;
                            default:
                                highlightView.pathChangeProgress = f;
                                highlightView.invalidate();
                                return;
                        }
                    default:
                        HighlightView highlightView2 = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                highlightView2.highlightProgress = f;
                                highlightView2.invalidate();
                                return;
                            default:
                                highlightView2.pathChangeProgress = f;
                                highlightView2.invalidate();
                                return;
                        }
                }
            }
        };
        new FloatProperty(1, "pathChangeProgress") {
            public final Object get(Object obj) {
                int i = 1;
                switch (i) {
                    case 0:
                        HighlightView highlightView = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                return Float.valueOf(highlightView.highlightProgress);
                            default:
                                return Float.valueOf(highlightView.pathChangeProgress);
                        }
                    default:
                        HighlightView highlightView2 = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                return Float.valueOf(highlightView2.highlightProgress);
                            default:
                                return Float.valueOf(highlightView2.pathChangeProgress);
                        }
                }
            }

            public final void setValue(Object obj, float f) {
                int i = 1;
                switch (i) {
                    case 0:
                        HighlightView highlightView = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                highlightView.highlightProgress = f;
                                highlightView.invalidate();
                                return;
                            default:
                                highlightView.pathChangeProgress = f;
                                highlightView.invalidate();
                                return;
                        }
                    default:
                        HighlightView highlightView2 = (HighlightView) obj;
                        switch (i) {
                            case 0:
                                highlightView2.highlightProgress = f;
                                highlightView2.invalidate();
                                return;
                            default:
                                highlightView2.pathChangeProgress = f;
                                highlightView2.invalidate();
                                return;
                        }
                }
            }
        };
        new PathInterpolator(0.71f, 0.0f, 0.13f, 1.0f);
    }

    public HighlightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        Paint paint = new Paint();
        this.backgroundPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(context.getColor(2131099811));
        float dimensionPixelSize = (float) context.getResources().getDimensionPixelSize(2131167407);
        this.highlightCornerRadius = dimensionPixelSize;
        Paint paint2 = new Paint();
        Paint paint3 = new Paint();
        this.highlightPaint = paint3;
        paint3.setColor(context.getColor(2131099812));
        paint2.setBlendMode(BlendMode.PLUS);
        paint2.setColor(paint3.getColor());
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeWidth(dimensionPixelSize * 2.0f);
        setWillNotDraw(false);
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return super.dispatchHoverEvent(motionEvent);
    }

    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setOnTouchListener(new HighlightView$$ExternalSyntheticLambda0(this));
        setAlpha(0.0f);
        animate().alpha(0.2f);
    }

    public final void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        if (getHeight() != 0) {
            getDrawingRect(this.tmpRect);
            this.tmpRectF.set(this.tmpRect);
            canvas.drawRoundRect(this.tmpRectF, 0.0f, 0.0f, this.backgroundPaint);
            float f2 = this.highlightProgress * 1.1f;
            for (int i = 0; i < this.highlights.size(); i++) {
                float height = (f2 - (((RectF) this.highlights.get(i)).top / ((float) getHeight()))) * 10.0f;
                if (height < 0.0f) {
                    f = 0.0f;
                } else {
                    f = Math.min(1.0f, height);
                }
                this.highlightPaint.setAlpha(Math.round(f * 255.0f));
                float f3 = this.highlightCornerRadius;
                canvas.drawRoundRect((RectF) this.highlights.get(i), f3, f3, this.highlightPaint);
            }
        }
    }

    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
    }
}
