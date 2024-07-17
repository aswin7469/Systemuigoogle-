package com.google.android.material.slider;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.widget.SeekBar;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.slider.BaseSlider;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class RangeSlider extends BaseSlider {
    public float minSeparation;
    public int separationUnit;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class RangeSliderState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public float minSeparation;
        public int separationUnit;

        /* renamed from: com.google.android.material.slider.RangeSlider$RangeSliderState$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass1 implements Parcelable.Creator {
            public final Object createFromParcel(Parcel parcel) {
                return new RangeSliderState(parcel);
            }

            public final Object[] newArray(int i) {
                return new RangeSliderState[i];
            }
        }

        public RangeSliderState(BaseSlider.SliderState sliderState) {
            super(sliderState);
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.minSeparation);
            parcel.writeInt(this.separationUnit);
        }

        public RangeSliderState(Parcel parcel) {
            super(parcel.readParcelable(RangeSliderState.class.getClassLoader()));
            this.minSeparation = parcel.readFloat();
            this.separationUnit = parcel.readInt();
        }
    }

    public RangeSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        int[] iArr = R$styleable.RangeSlider;
        ThemeEnforcement.checkCompatibleTheme(context, attributeSet, 2130969926, 2132018853);
        ThemeEnforcement.checkTextAppearance(context, attributeSet, iArr, 2130969926, 2132018853, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 2130969926, 2132018853);
        if (obtainStyledAttributes.hasValue(1)) {
            TypedArray obtainTypedArray = obtainStyledAttributes.getResources().obtainTypedArray(obtainStyledAttributes.getResourceId(1, 0));
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < obtainTypedArray.length(); i++) {
                arrayList.add(Float.valueOf(obtainTypedArray.getFloat(i, -1.0f)));
            }
            setValuesInternal(new ArrayList(arrayList));
        }
        this.minSeparation = obtainStyledAttributes.getDimension(0, 0.0f);
        obtainStyledAttributes.recycle();
    }

    public final CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final float getMinSeparation() {
        return this.minSeparation;
    }

    public final float getValueFrom() {
        return this.valueFrom;
    }

    public final float getValueTo() {
        return this.valueTo;
    }

    public final List getValues() {
        return super.getValues();
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        RangeSliderState rangeSliderState = (RangeSliderState) parcelable;
        super.onRestoreInstanceState(rangeSliderState.getSuperState());
        this.minSeparation = rangeSliderState.minSeparation;
        int i = rangeSliderState.separationUnit;
        this.separationUnit = i;
        this.separationUnit = i;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public final Parcelable onSaveInstanceState() {
        RangeSliderState rangeSliderState = new RangeSliderState((BaseSlider.SliderState) super.onSaveInstanceState());
        rangeSliderState.minSeparation = this.minSeparation;
        rangeSliderState.separationUnit = this.separationUnit;
        return rangeSliderState;
    }

    public final void setValues(Float... fArr) {
        super.setValues(fArr);
    }
}
