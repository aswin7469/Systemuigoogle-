package com.google.android.material.internal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.ImageButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class CheckableImageButton extends AppCompatImageButton implements Checkable {
    public static final int[] DRAWABLE_STATE_CHECKED = {16842912};
    public boolean checkable;
    public boolean checked;
    public boolean pressable;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new Object();
        public boolean checked;

        /* renamed from: com.google.android.material.internal.CheckableImageButton$SavedState$1  reason: invalid class name */
        /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
        public final class AnonymousClass1 implements Parcelable.ClassLoaderCreator {
            public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public final Object[] newArray(int i) {
                return new SavedState[i];
            }

            public final Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.checked = parcel.readInt() != 1 ? false : true;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.checked ? 1 : 0);
        }
    }

    public CheckableImageButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public final boolean isChecked() {
        return this.checked;
    }

    public final int[] onCreateDrawableState(int i) {
        if (this.checked) {
            return ImageButton.mergeDrawableStates(super.onCreateDrawableState(i + 1), DRAWABLE_STATE_CHECKED);
        }
        return super.onCreateDrawableState(i);
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        setChecked(savedState.checked);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.os.Parcelable, androidx.customview.view.AbsSavedState, com.google.android.material.internal.CheckableImageButton$SavedState] */
    public final Parcelable onSaveInstanceState() {
        ? absSavedState = new AbsSavedState(super.onSaveInstanceState());
        absSavedState.checked = this.checked;
        return absSavedState;
    }

    public final void setChecked(boolean z) {
        if (this.checkable && this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            sendAccessibilityEvent(2048);
        }
    }

    public final void setPressed(boolean z) {
        if (this.pressable) {
            super.setPressed(z);
        }
    }

    public final void toggle() {
        setChecked(!this.checked);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 2130969302);
    }

    public CheckableImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.checkable = true;
        this.pressable = true;
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                super.onInitializeAccessibilityEvent(view, accessibilityEvent);
                accessibilityEvent.setChecked(CheckableImageButton.this.checked);
            }

            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                View.AccessibilityDelegate accessibilityDelegate = this.mOriginalDelegate;
                AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                CheckableImageButton checkableImageButton = CheckableImageButton.this;
                accessibilityNodeInfo.setCheckable(checkableImageButton.checkable);
                accessibilityNodeInfo.setChecked(checkableImageButton.checked);
            }
        });
    }
}
