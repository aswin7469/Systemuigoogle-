package com.google.android.systemui.qs.launcher;

import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class TileState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Object();
    public CharSequence mContentDescription;
    public Icon mIcon;
    public boolean mIsTransient;
    public CharSequence mLabel;
    public boolean mShowChevron;
    public int mState = 1;
    public CharSequence mStateDescription;
    public CharSequence mSubtitle;
    public boolean mSupportClick;
    public boolean mSupportLongClick;
    public CharSequence mTileSpec;
    public boolean mUnSupported;

    /* renamed from: com.google.android.systemui.qs.launcher.TileState$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass1 implements Parcelable.Creator {
        /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, com.google.android.systemui.qs.launcher.TileState] */
        public final Object createFromParcel(Parcel parcel) {
            ? obj = new Object();
            obj.mState = 1;
            obj.mIcon = (Icon) parcel.readTypedObject(Icon.CREATOR);
            obj.mState = parcel.readInt();
            obj.mIsTransient = parcel.readBoolean();
            obj.mSupportClick = parcel.readBoolean();
            obj.mSupportLongClick = parcel.readBoolean();
            obj.mShowChevron = parcel.readBoolean();
            obj.mUnSupported = parcel.readBoolean();
            Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
            obj.mLabel = (CharSequence) creator.createFromParcel(parcel);
            obj.mSubtitle = (CharSequence) creator.createFromParcel(parcel);
            obj.mContentDescription = (CharSequence) creator.createFromParcel(parcel);
            obj.mStateDescription = (CharSequence) creator.createFromParcel(parcel);
            obj.mTileSpec = (CharSequence) creator.createFromParcel(parcel);
            return obj;
        }

        public final Object[] newArray(int i) {
            return new TileState[i];
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedObject(this.mIcon, i);
        parcel.writeInt(this.mState);
        parcel.writeBoolean(this.mIsTransient);
        parcel.writeBoolean(this.mSupportClick);
        parcel.writeBoolean(this.mSupportLongClick);
        parcel.writeBoolean(this.mShowChevron);
        parcel.writeBoolean(this.mUnSupported);
        TextUtils.writeToParcel(this.mLabel, parcel, i);
        TextUtils.writeToParcel(this.mSubtitle, parcel, i);
        TextUtils.writeToParcel(this.mContentDescription, parcel, i);
        TextUtils.writeToParcel(this.mStateDescription, parcel, i);
        TextUtils.writeToParcel(this.mTileSpec, parcel, i);
    }
}
