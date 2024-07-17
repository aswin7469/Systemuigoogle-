package com.google.android.setupdesign.items;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.setupdesign.R$styleable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class SwitchItem extends Item implements CompoundButton.OnCheckedChangeListener {
    public boolean checked = false;

    public SwitchItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SudSwitchItem);
        this.checked = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
    }

    public int getDefaultLayoutResource() {
        return 2131559108;
    }

    public void onBindView(View view) {
        super.onBindView(view);
        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(2131363729);
        switchCompat.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        switchCompat.setChecked(this.checked);
        switchCompat.setOnCheckedChangeListener(this);
        switchCompat.setEnabled(this.enabled);
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.checked = z;
    }
}
