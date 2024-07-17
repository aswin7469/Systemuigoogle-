package com.google.android.systemui.columbus.legacy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.android.systemui.statusbar.phone.SystemUIDialog;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusTargetRequestDialog extends SystemUIDialog {
    public TextView mContent;
    public Button mNegativeButton;
    public Button mPositiveButton;
    public TextView mTitle;

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131558526);
        this.mTitle = (TextView) requireViewById(2131363868);
        this.mContent = (TextView) requireViewById(2131362309);
        this.mPositiveButton = (Button) requireViewById(2131363325);
        this.mNegativeButton = (Button) requireViewById(2131363188);
    }

    public final void setMessage(CharSequence charSequence) {
        this.mContent.setText(charSequence);
    }

    public final void setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        this.mNegativeButton.setText(i);
        this.mNegativeButton.setOnClickListener(new ColumbusTargetRequestDialog$$ExternalSyntheticLambda0(this, onClickListener, 0));
    }

    public final void setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        this.mPositiveButton.setText(i);
        this.mPositiveButton.setOnClickListener(new ColumbusTargetRequestDialog$$ExternalSyntheticLambda0(this, onClickListener, 1));
    }

    public final void setTitle(CharSequence charSequence) {
        getWindow().setTitle(charSequence);
        getWindow().getAttributes().setTitle(charSequence);
        this.mTitle.setText(charSequence);
    }
}
