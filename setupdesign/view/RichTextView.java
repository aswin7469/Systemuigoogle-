package com.google.android.setupdesign.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Annotation;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.compose.ui.text.input.RecordingInputConnection$$ExternalSyntheticOutline0;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.setupcompat.util.BuildCompatUtils;
import com.google.android.setupdesign.accessibility.LinkAccessibilityHelper;
import com.google.android.setupdesign.span.BoldLinkSpan;
import com.google.android.setupdesign.span.LinkSpan;
import com.google.android.setupdesign.view.TouchableMovementMethod;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class RichTextView extends AppCompatTextView implements LinkSpan.OnLinkClickListener {
    static Typeface spanTypeface;
    public LinkAccessibilityHelper accessibilityHelper;

    public RichTextView(Context context) {
        super(context);
        init$9$1();
    }

    public static void setSpanTypeface(Typeface typeface) {
        spanTypeface = typeface;
    }

    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        LinkAccessibilityHelper linkAccessibilityHelper = this.accessibilityHelper;
        if (linkAccessibilityHelper != null) {
            AccessibilityDelegateCompat accessibilityDelegateCompat = linkAccessibilityHelper.delegate;
            if ((accessibilityDelegateCompat instanceof ExploreByTouchHelper) && ((ExploreByTouchHelper) accessibilityDelegateCompat).dispatchHoverEvent(motionEvent)) {
                return true;
            }
        }
        return super.dispatchHoverEvent(motionEvent);
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        for (Drawable drawable : getCompoundDrawablesRelative()) {
            if (drawable != null && drawable.setState(drawableState)) {
                invalidateDrawable(drawable);
            }
        }
    }

    public final void init$9$1() {
        if (!isInEditMode()) {
            LinkAccessibilityHelper linkAccessibilityHelper = new LinkAccessibilityHelper(new AccessibilityDelegateCompat());
            this.accessibilityHelper = linkAccessibilityHelper;
            ViewCompat.setAccessibilityDelegate(this, linkAccessibilityHelper);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        MovementMethod movementMethod = getMovementMethod();
        if (movementMethod instanceof TouchableMovementMethod) {
            TouchableMovementMethod touchableMovementMethod = (TouchableMovementMethod) movementMethod;
            if (((TouchableMovementMethod.TouchableLinkMovementMethod) touchableMovementMethod).lastEvent == motionEvent) {
                return ((TouchableMovementMethod.TouchableLinkMovementMethod) touchableMovementMethod).lastEventResult;
            }
        }
        return onTouchEvent;
    }

    /* JADX WARNING: type inference failed for: r12v3, types: [com.google.android.setupdesign.view.TouchableMovementMethod$TouchableLinkMovementMethod, android.text.method.LinkMovementMethod, android.text.method.MovementMethod] */
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        boolean z;
        Object obj;
        TypefaceSpan typefaceSpan;
        Context context = getContext();
        if (charSequence instanceof Spanned) {
            SpannableString spannableString = new SpannableString(charSequence);
            for (Annotation annotation : (Annotation[]) spannableString.getSpans(0, spannableString.length(), Annotation.class)) {
                String key = annotation.getKey();
                if ("textAppearance".equals(key)) {
                    int identifier = context.getResources().getIdentifier(annotation.getValue(), "style", context.getPackageName());
                    if (identifier == 0) {
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("Cannot find resource: ", identifier, "RichTextView");
                    }
                    Object[] objArr = {new TextAppearanceSpan(context, identifier)};
                    int spanStart = spannableString.getSpanStart(annotation);
                    int spanEnd = spannableString.getSpanEnd(annotation);
                    spannableString.removeSpan(annotation);
                    spannableString.setSpan(objArr[0], spanStart, spanEnd, 0);
                } else if ("link".equals(key)) {
                    if (BuildCompatUtils.isAtLeastU()) {
                        annotation.getValue();
                        obj = new BoldLinkSpan(context);
                    } else {
                        annotation.getValue();
                        obj = new ClickableSpan();
                    }
                    if (spanTypeface != null) {
                        typefaceSpan = new TypefaceSpan(spanTypeface);
                    } else {
                        typefaceSpan = new TypefaceSpan("sans-serif-medium");
                    }
                    Object[] objArr2 = {obj, typefaceSpan};
                    int spanStart2 = spannableString.getSpanStart(annotation);
                    int spanEnd2 = spannableString.getSpanEnd(annotation);
                    spannableString.removeSpan(annotation);
                    for (int i = 0; i < 2; i++) {
                        spannableString.setSpan(objArr2[i], spanStart2, spanEnd2, 0);
                    }
                }
            }
            charSequence = spannableString;
        }
        super.setText(charSequence, bufferType);
        if (!(charSequence instanceof Spanned) || ((ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class)).length <= 0) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            ? linkMovementMethod = new LinkMovementMethod();
            linkMovementMethod.lastEventResult = false;
            setMovementMethod(linkMovementMethod);
        } else {
            setMovementMethod((MovementMethod) null);
        }
        setFocusable(z);
        setRevealOnFocusHint(false);
        setFocusableInTouchMode(z);
    }

    public RichTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init$9$1();
    }
}
