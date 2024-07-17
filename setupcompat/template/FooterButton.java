package com.google.android.setupcompat.template;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.View;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.data.ViewNode;
import com.google.android.setupcompat.R$styleable;
import com.google.android.setupcompat.logging.CustomEvent;
import com.google.android.setupcompat.template.FooterBarMixin;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FooterButton implements View.OnClickListener {
    public FooterBarMixin.AnonymousClass1 buttonListener;
    public final int buttonType;
    public int clickCount = 0;
    public boolean enabled = true;
    public final View.OnClickListener onClickListener;
    public final CharSequence text;
    public final int theme;

    public FooterButton(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SucFooterButton);
        this.text = obtainStyledAttributes.getString(1);
        this.onClickListener = null;
        int i = obtainStyledAttributes.getInt(2, 0);
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("Not a ButtonType");
        }
        this.buttonType = i;
        this.theme = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
    }

    public final PersistableBundle getMetrics(String str) {
        String str2;
        PersistableBundle persistableBundle = new PersistableBundle();
        String concat = str.concat("_text");
        String charSequence = this.text.toString();
        Parcelable.Creator creator = CustomEvent.CREATOR;
        if (charSequence.length() > 50) {
            charSequence = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(charSequence.substring(0, 49), "…");
        }
        persistableBundle.putString(concat, charSequence);
        String concat2 = str.concat("_type");
        switch (this.buttonType) {
            case 1:
                str2 = "ADD_ANOTHER";
                break;
            case 2:
                str2 = "CANCEL";
                break;
            case 3:
                str2 = "CLEAR";
                break;
            case 4:
                str2 = "DONE";
                break;
            case 5:
                str2 = "NEXT";
                break;
            case 6:
                str2 = "OPT_IN";
                break;
            case ViewNode.WIDTH_FIELD_NUMBER:
                str2 = "SKIP";
                break;
            case 8:
                str2 = "STOP";
                break;
            default:
                str2 = "OTHER";
                break;
        }
        persistableBundle.putString(concat2, str2);
        persistableBundle.putInt(str.concat("_onClickCount"), this.clickCount);
        return persistableBundle;
    }

    public final void onClick(View view) {
        View.OnClickListener onClickListener2 = this.onClickListener;
        if (onClickListener2 != null) {
            this.clickCount++;
            onClickListener2.onClick(view);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r3.this$0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setEnabled(boolean r4) {
        /*
            r3 = this;
            r3.enabled = r4
            com.google.android.setupcompat.template.FooterBarMixin$1 r3 = r3.buttonListener
            if (r3 == 0) goto L_0x005a
            com.google.android.setupcompat.template.FooterBarMixin r0 = com.google.android.setupcompat.template.FooterBarMixin.this
            android.widget.LinearLayout r1 = r0.buttonContainer
            if (r1 == 0) goto L_0x005a
            int r3 = r0
            android.view.View r1 = r1.findViewById(r3)
            android.widget.Button r1 = (android.widget.Button) r1
            if (r1 == 0) goto L_0x005a
            r1.setEnabled(r4)
            boolean r4 = r0.applyPartnerResources
            if (r4 == 0) goto L_0x005a
            boolean r4 = r0.applyDynamicColor
            if (r4 != 0) goto L_0x005a
            int r4 = r0.primaryButtonId
            if (r3 == r4) goto L_0x002d
            boolean r2 = r0.isSecondaryButtonInPrimaryStyle
            if (r2 == 0) goto L_0x002a
            goto L_0x002d
        L_0x002a:
            com.google.android.setupcompat.partnerconfig.PartnerConfig r2 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_FOOTER_SECONDARY_BUTTON_TEXT_COLOR
            goto L_0x002f
        L_0x002d:
            com.google.android.setupcompat.partnerconfig.PartnerConfig r2 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_FOOTER_PRIMARY_BUTTON_TEXT_COLOR
        L_0x002f:
            if (r3 == r4) goto L_0x0039
            boolean r3 = r0.isSecondaryButtonInPrimaryStyle
            if (r3 == 0) goto L_0x0036
            goto L_0x0039
        L_0x0036:
            com.google.android.setupcompat.partnerconfig.PartnerConfig r3 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_FOOTER_SECONDARY_BUTTON_DISABLED_TEXT_COLOR
            goto L_0x003b
        L_0x0039:
            com.google.android.setupcompat.partnerconfig.PartnerConfig r3 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_FOOTER_PRIMARY_BUTTON_DISABLED_TEXT_COLOR
        L_0x003b:
            boolean r4 = r1.isEnabled()
            android.content.Context r0 = r0.context
            if (r4 == 0) goto L_0x0057
            java.util.HashMap r3 = com.google.android.setupcompat.template.FooterButtonStyleUtils.defaultTextColor
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r3 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r0)
            int r3 = r3.getColor(r0, r2)
            if (r3 == 0) goto L_0x005a
            android.content.res.ColorStateList r3 = android.content.res.ColorStateList.valueOf(r3)
            r1.setTextColor(r3)
            goto L_0x005a
        L_0x0057:
            com.google.android.setupcompat.template.FooterButtonStyleUtils.updateButtonTextDisabledColorWithPartnerConfig(r0, r1, r3)
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.template.FooterButton.setEnabled(boolean):void");
    }

    public FooterButton(CharSequence charSequence, View.OnClickListener onClickListener2) {
        this.text = charSequence;
        this.onClickListener = onClickListener2;
        this.buttonType = 0;
        this.theme = 0;
    }
}
