package com.google.android.material.timepicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.ViewCompat;
import com.android.app.viewcapture.data.ViewNode;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import java.util.HashMap;
import java.util.WeakHashMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
class TimePickerView extends ConstraintLayout {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final AnonymousClass1 selectionListener;
    public final MaterialButtonToggleGroup toggle;

    public TimePickerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateToggleConstraints();
    }

    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && i == 0) {
            updateToggleConstraints();
        }
    }

    public final void updateToggleConstraints() {
        char c;
        ConstraintSet.Constraint constraint;
        if (this.toggle.getVisibility() == 0) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone((ConstraintLayout) this);
            WeakHashMap weakHashMap = ViewCompat.sViewPropertyAnimatorMap;
            if (getLayoutDirection() == 0) {
                c = 2;
            } else {
                c = 1;
            }
            HashMap hashMap = constraintSet.mConstraints;
            if (hashMap.containsKey(2131362973) && (constraint = (ConstraintSet.Constraint) hashMap.get(2131362973)) != null) {
                ConstraintSet.Layout layout = constraint.layout;
                switch (c) {
                    case 1:
                        layout.leftToRight = -1;
                        layout.leftToLeft = -1;
                        layout.leftMargin = -1;
                        layout.goneLeftMargin = Integer.MIN_VALUE;
                        break;
                    case 2:
                        layout.rightToRight = -1;
                        layout.rightToLeft = -1;
                        layout.rightMargin = -1;
                        layout.goneRightMargin = Integer.MIN_VALUE;
                        break;
                    case 3:
                        layout.topToBottom = -1;
                        layout.topToTop = -1;
                        layout.topMargin = 0;
                        layout.goneTopMargin = Integer.MIN_VALUE;
                        break;
                    case 4:
                        layout.bottomToTop = -1;
                        layout.bottomToBottom = -1;
                        layout.bottomMargin = 0;
                        layout.goneBottomMargin = Integer.MIN_VALUE;
                        break;
                    case 5:
                        layout.baselineToBaseline = -1;
                        layout.baselineToTop = -1;
                        layout.baselineToBottom = -1;
                        layout.baselineMargin = 0;
                        layout.goneBaselineMargin = Integer.MIN_VALUE;
                        break;
                    case 6:
                        layout.startToEnd = -1;
                        layout.startToStart = -1;
                        layout.startMargin = 0;
                        layout.goneStartMargin = Integer.MIN_VALUE;
                        break;
                    case ViewNode.WIDTH_FIELD_NUMBER:
                        layout.endToStart = -1;
                        layout.endToEnd = -1;
                        layout.endMargin = 0;
                        layout.goneEndMargin = Integer.MIN_VALUE;
                        break;
                    case 8:
                        layout.circleAngle = -1.0f;
                        layout.circleRadius = -1;
                        layout.circleConstraint = -1;
                        break;
                    default:
                        throw new IllegalArgumentException("unknown constraint");
                }
            }
            constraintSet.applyTo(this);
        }
    }

    public TimePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AnonymousClass1 r5 = new View.OnClickListener() {
            public final void onClick(View view) {
                TimePickerView timePickerView = TimePickerView.this;
                int i = TimePickerView.$r8$clinit;
                timePickerView.getClass();
            }
        };
        LayoutInflater.from(context).inflate(2131558761, this);
        ClockFaceView clockFaceView = (ClockFaceView) findViewById(2131362974);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(2131362978);
        this.toggle = materialButtonToggleGroup;
        materialButtonToggleGroup.onButtonCheckedListeners.add(new TimePickerView$$ExternalSyntheticLambda0(this));
        Chip chip = (Chip) findViewById(2131362983);
        Chip chip2 = (Chip) findViewById(2131362980);
        ClockHandView clockHandView = (ClockHandView) findViewById(2131362975);
        final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            public final boolean onDoubleTap(MotionEvent motionEvent) {
                TimePickerView timePickerView = TimePickerView.this;
                int i = TimePickerView.$r8$clinit;
                timePickerView.getClass();
                return false;
            }
        });
        AnonymousClass3 r3 = new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (((Checkable) view).isChecked()) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
                return false;
            }
        };
        chip.setOnTouchListener(r3);
        chip2.setOnTouchListener(r3);
        chip.setTag(2131363589, 12);
        chip2.setTag(2131363589, 10);
        chip.setOnClickListener(r5);
        chip2.setOnClickListener(r5);
        chip.accessibilityClassName = "android.view.View";
        chip2.accessibilityClassName = "android.view.View";
    }
}
