package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ButtonItem extends AbstractItem implements View.OnClickListener {
    public Button button;
    public final boolean enabled = true;
    public final CharSequence text;
    public final int theme = 2132017774;

    public ButtonItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudButtonItem);
        this.enabled = obtainStyledAttributes.getBoolean(1, true);
        this.text = obtainStyledAttributes.getText(3);
        this.theme = obtainStyledAttributes.getResourceId(0, 2132017774);
        obtainStyledAttributes.recycle();
    }

    public final int getCount() {
        return 0;
    }

    public final int getLayoutResource() {
        return 0;
    }

    public final boolean isEnabled() {
        return this.enabled;
    }

    public final void onBindView(View view) {
        throw new UnsupportedOperationException("Cannot bind to ButtonItem's view");
    }

    public final void onClick(View view) {
    }
}
