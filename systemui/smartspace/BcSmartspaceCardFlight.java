package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BcSmartspaceCardFlight extends BcSmartspaceCardSecondary {
    public ImageView mQrCodeView;

    public BcSmartspaceCardFlight(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mQrCodeView = (ImageView) findViewById(2131362581);
    }

    public final void resetUi() {
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mQrCodeView, 8);
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        Bundle bundle;
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        if (baseAction == null) {
            bundle = null;
        } else {
            bundle = baseAction.getExtras();
        }
        if (bundle == null || !bundle.containsKey("qrCodeBitmap")) {
            return false;
        }
        Bitmap bitmap = (Bitmap) bundle.get("qrCodeBitmap");
        ImageView imageView = this.mQrCodeView;
        if (imageView == null) {
            Log.w("BcSmartspaceCardFlight", "No flight QR code view to update");
        } else {
            imageView.setImageBitmap(bitmap);
        }
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mQrCodeView, 0);
        return true;
    }

    public BcSmartspaceCardFlight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void setTextColor(int i) {
    }
}
