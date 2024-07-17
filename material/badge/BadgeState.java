package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BadgeState {
    public final float badgeRadius;
    public final float badgeWidePadding;
    public final float badgeWithTextRadius;
    public final State currentState = new State();
    public final State overridingState;

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class State implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Object();
        public Integer additionalHorizontalOffset;
        public Integer additionalVerticalOffset;
        public int alpha = 255;
        public Integer backgroundColor;
        public Integer badgeGravity;
        public int badgeResId;
        public Integer badgeTextColor;
        public int contentDescriptionExceedsMaxBadgeNumberRes;
        public CharSequence contentDescriptionNumberless;
        public int contentDescriptionQuantityStrings;
        public Integer horizontalOffsetWithText;
        public Integer horizontalOffsetWithoutText;
        public Boolean isVisible = Boolean.TRUE;
        public int maxCharacterCount = -2;
        public int number = -2;
        public Locale numberLocale;
        public Integer verticalOffsetWithText;
        public Integer verticalOffsetWithoutText;

        /* renamed from: com.google.android.material.badge.BadgeState$State$1  reason: invalid class name */
        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class AnonymousClass1 implements Parcelable.Creator {
            /* JADX WARNING: type inference failed for: r1v1, types: [java.lang.Object, com.google.android.material.badge.BadgeState$State] */
            public final Object createFromParcel(Parcel parcel) {
                ? obj = new Object();
                obj.alpha = 255;
                obj.number = -2;
                obj.maxCharacterCount = -2;
                obj.isVisible = Boolean.TRUE;
                obj.badgeResId = parcel.readInt();
                obj.backgroundColor = (Integer) parcel.readSerializable();
                obj.badgeTextColor = (Integer) parcel.readSerializable();
                obj.alpha = parcel.readInt();
                obj.number = parcel.readInt();
                obj.maxCharacterCount = parcel.readInt();
                obj.contentDescriptionNumberless = parcel.readString();
                obj.contentDescriptionQuantityStrings = parcel.readInt();
                obj.badgeGravity = (Integer) parcel.readSerializable();
                obj.horizontalOffsetWithoutText = (Integer) parcel.readSerializable();
                obj.verticalOffsetWithoutText = (Integer) parcel.readSerializable();
                obj.horizontalOffsetWithText = (Integer) parcel.readSerializable();
                obj.verticalOffsetWithText = (Integer) parcel.readSerializable();
                obj.additionalHorizontalOffset = (Integer) parcel.readSerializable();
                obj.additionalVerticalOffset = (Integer) parcel.readSerializable();
                obj.isVisible = (Boolean) parcel.readSerializable();
                obj.numberLocale = (Locale) parcel.readSerializable();
                return obj;
            }

            public final Object[] newArray(int i) {
                return new State[i];
            }
        }

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            String str;
            parcel.writeInt(this.badgeResId);
            parcel.writeSerializable(this.backgroundColor);
            parcel.writeSerializable(this.badgeTextColor);
            parcel.writeInt(this.alpha);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            CharSequence charSequence = this.contentDescriptionNumberless;
            if (charSequence == null) {
                str = null;
            } else {
                str = charSequence.toString();
            }
            parcel.writeString(str);
            parcel.writeInt(this.contentDescriptionQuantityStrings);
            parcel.writeSerializable(this.badgeGravity);
            parcel.writeSerializable(this.horizontalOffsetWithoutText);
            parcel.writeSerializable(this.verticalOffsetWithoutText);
            parcel.writeSerializable(this.horizontalOffsetWithText);
            parcel.writeSerializable(this.verticalOffsetWithText);
            parcel.writeSerializable(this.additionalHorizontalOffset);
            parcel.writeSerializable(this.additionalVerticalOffset);
            parcel.writeSerializable(this.isVisible);
            parcel.writeSerializable(this.numberLocale);
        }
    }

    public BadgeState(Context context, State state) {
        AttributeSet attributeSet;
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int next;
        Context context2 = context;
        State state2 = state;
        int i10 = state2.badgeResId;
        int i11 = 0;
        if (i10 != 0) {
            try {
                XmlResourceParser xml = context.getResources().getXml(i10);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                } else if (TextUtils.equals(xml.getName(), "badge")) {
                    AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                    i = asAttributeSet.getStyleAttribute();
                    attributeSet = asAttributeSet;
                } else {
                    throw new XmlPullParserException("Must have a <" + "badge" + "> start tag");
                }
            } catch (IOException | XmlPullParserException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(i10));
                notFoundException.initCause(e);
                throw notFoundException;
            }
        } else {
            attributeSet = null;
            i = 0;
        }
        if (i == 0) {
            i2 = 2132018775;
        } else {
            i2 = i;
        }
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R$styleable.Badge, 2130968684, i2, new int[0]);
        Resources resources = context.getResources();
        this.badgeRadius = (float) obtainStyledAttributes.getDimensionPixelSize(2, resources.getDimensionPixelSize(2131166727));
        this.badgeWidePadding = (float) obtainStyledAttributes.getDimensionPixelSize(4, resources.getDimensionPixelSize(2131166726));
        this.badgeWithTextRadius = (float) obtainStyledAttributes.getDimensionPixelSize(5, resources.getDimensionPixelSize(2131166732));
        State state3 = this.currentState;
        int i12 = state2.alpha;
        state3.alpha = i12 == -2 ? 255 : i12;
        CharSequence charSequence = state2.contentDescriptionNumberless;
        state3.contentDescriptionNumberless = charSequence == null ? context2.getString(2131953249) : charSequence;
        State state4 = this.currentState;
        int i13 = state2.contentDescriptionQuantityStrings;
        state4.contentDescriptionQuantityStrings = i13 == 0 ? 2131820547 : i13;
        int i14 = state2.contentDescriptionExceedsMaxBadgeNumberRes;
        state4.contentDescriptionExceedsMaxBadgeNumberRes = i14 == 0 ? 2131953251 : i14;
        Boolean bool = state2.isVisible;
        if (bool == null || bool.booleanValue()) {
            z = true;
        } else {
            z = false;
        }
        state4.isVisible = Boolean.valueOf(z);
        State state5 = this.currentState;
        int i15 = state2.maxCharacterCount;
        state5.maxCharacterCount = i15 == -2 ? obtainStyledAttributes.getInt(8, 4) : i15;
        int i16 = state2.number;
        if (i16 != -2) {
            this.currentState.number = i16;
        } else if (obtainStyledAttributes.hasValue(9)) {
            this.currentState.number = obtainStyledAttributes.getInt(9, 0);
        } else {
            this.currentState.number = -1;
        }
        State state6 = this.currentState;
        Integer num = state2.backgroundColor;
        if (num == null) {
            i3 = MaterialResources.getColorStateList(context2, obtainStyledAttributes, 0).getDefaultColor();
        } else {
            i3 = num.intValue();
        }
        state6.backgroundColor = Integer.valueOf(i3);
        Integer num2 = state2.badgeTextColor;
        if (num2 != null) {
            this.currentState.badgeTextColor = num2;
        } else if (obtainStyledAttributes.hasValue(3)) {
            this.currentState.badgeTextColor = Integer.valueOf(MaterialResources.getColorStateList(context2, obtainStyledAttributes, 3).getDefaultColor());
        } else {
            TypedArray obtainStyledAttributes2 = context2.obtainStyledAttributes(2132018069, R$styleable.TextAppearance);
            obtainStyledAttributes2.getDimension(0, 0.0f);
            ColorStateList colorStateList = MaterialResources.getColorStateList(context2, obtainStyledAttributes2, 3);
            MaterialResources.getColorStateList(context2, obtainStyledAttributes2, 4);
            MaterialResources.getColorStateList(context2, obtainStyledAttributes2, 5);
            obtainStyledAttributes2.getInt(2, 0);
            obtainStyledAttributes2.getInt(1, 1);
            int i17 = !obtainStyledAttributes2.hasValue(12) ? 10 : 12;
            obtainStyledAttributes2.getResourceId(i17, 0);
            obtainStyledAttributes2.getString(i17);
            obtainStyledAttributes2.getBoolean(14, false);
            MaterialResources.getColorStateList(context2, obtainStyledAttributes2, 6);
            obtainStyledAttributes2.getFloat(7, 0.0f);
            obtainStyledAttributes2.getFloat(8, 0.0f);
            obtainStyledAttributes2.getFloat(9, 0.0f);
            obtainStyledAttributes2.recycle();
            TypedArray obtainStyledAttributes3 = context2.obtainStyledAttributes(2132018069, R$styleable.MaterialTextAppearance);
            obtainStyledAttributes3.hasValue(0);
            obtainStyledAttributes3.getFloat(0, 0.0f);
            obtainStyledAttributes3.recycle();
            this.currentState.badgeTextColor = Integer.valueOf(colorStateList.getDefaultColor());
        }
        State state7 = this.currentState;
        Integer num3 = state2.badgeGravity;
        if (num3 == null) {
            i4 = obtainStyledAttributes.getInt(1, 8388661);
        } else {
            i4 = num3.intValue();
        }
        state7.badgeGravity = Integer.valueOf(i4);
        State state8 = this.currentState;
        Integer num4 = state2.horizontalOffsetWithoutText;
        if (num4 == null) {
            i5 = obtainStyledAttributes.getDimensionPixelOffset(6, 0);
        } else {
            i5 = num4.intValue();
        }
        state8.horizontalOffsetWithoutText = Integer.valueOf(i5);
        State state9 = this.currentState;
        if (state2.horizontalOffsetWithoutText == null) {
            i6 = obtainStyledAttributes.getDimensionPixelOffset(10, 0);
        } else {
            i6 = state2.verticalOffsetWithoutText.intValue();
        }
        state9.verticalOffsetWithoutText = Integer.valueOf(i6);
        State state10 = this.currentState;
        Integer num5 = state2.horizontalOffsetWithText;
        if (num5 == null) {
            i7 = obtainStyledAttributes.getDimensionPixelOffset(7, state10.horizontalOffsetWithoutText.intValue());
        } else {
            i7 = num5.intValue();
        }
        state10.horizontalOffsetWithText = Integer.valueOf(i7);
        State state11 = this.currentState;
        Integer num6 = state2.verticalOffsetWithText;
        if (num6 == null) {
            i8 = obtainStyledAttributes.getDimensionPixelOffset(11, state11.verticalOffsetWithoutText.intValue());
        } else {
            i8 = num6.intValue();
        }
        state11.verticalOffsetWithText = Integer.valueOf(i8);
        State state12 = this.currentState;
        Integer num7 = state2.additionalHorizontalOffset;
        if (num7 == null) {
            i9 = 0;
        } else {
            i9 = num7.intValue();
        }
        state12.additionalHorizontalOffset = Integer.valueOf(i9);
        State state13 = this.currentState;
        Integer num8 = state2.additionalVerticalOffset;
        state13.additionalVerticalOffset = Integer.valueOf(num8 != null ? num8.intValue() : i11);
        obtainStyledAttributes.recycle();
        Locale locale = state2.numberLocale;
        if (locale == null) {
            this.currentState.numberLocale = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            this.currentState.numberLocale = locale;
        }
        this.overridingState = state2;
    }
}
