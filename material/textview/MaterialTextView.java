package com.google.android.material.textview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R$styleable;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        TypedValue resolve = MaterialAttributes.resolve(2130970195, context);
        if (resolve == null || resolve.type != 18 || resolve.data != 0) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
            Context context2 = getContext();
            int[] iArr = {1, 2};
            int i2 = -1;
            for (int i3 = 0; i3 < 2 && i2 < 0; i3++) {
                i2 = MaterialResources.getDimensionPixelSize(context2, obtainStyledAttributes, iArr[i3], -1);
            }
            obtainStyledAttributes.recycle();
            if (i2 >= 0) {
                TextViewCompat.setLineHeight(i2, this);
            }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MaterialTextView(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 16842884, 0), attributeSet, 16842884);
        Context context2 = getContext();
        TypedValue resolve = MaterialAttributes.resolve(2130970195, context2);
        if (resolve == null || resolve.type != 18 || resolve.data != 0) {
            Resources.Theme theme = context2.getTheme();
            int[] iArr = R$styleable.MaterialTextView;
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
            int[] iArr2 = {1, 2};
            int i2 = -1;
            for (int i3 = 0; i3 < 2 && i2 < 0; i3++) {
                i2 = MaterialResources.getDimensionPixelSize(context2, obtainStyledAttributes, iArr2[i3], -1);
            }
            obtainStyledAttributes.recycle();
            if (i2 == -1) {
                TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
                int resourceId = obtainStyledAttributes2.getResourceId(0, -1);
                obtainStyledAttributes2.recycle();
                if (resourceId != -1) {
                    TypedArray obtainStyledAttributes3 = theme.obtainStyledAttributes(resourceId, R$styleable.MaterialTextAppearance);
                    Context context3 = getContext();
                    int[] iArr3 = {1, 2};
                    int i4 = -1;
                    for (int i5 = 0; i5 < 2 && i4 < 0; i5++) {
                        i4 = MaterialResources.getDimensionPixelSize(context3, obtainStyledAttributes3, iArr3[i5], -1);
                    }
                    obtainStyledAttributes3.recycle();
                    if (i4 >= 0) {
                        TextViewCompat.setLineHeight(i4, this);
                    }
                }
            }
        }
    }
}
