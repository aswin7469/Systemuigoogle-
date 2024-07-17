package com.google.android.setupcompat.template;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.Button;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import java.util.HashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class FooterButtonStyleUtils {
    public static final HashMap defaultTextColor = new HashMap();

    public static void updateButtonTextDisabledColorWithPartnerConfig(Context context, Button button, PartnerConfig partnerConfig) {
        if (PartnerConfigHelper.get(context).isPartnerConfigAvailable(partnerConfig)) {
            int color = PartnerConfigHelper.get(context).getColor(context, partnerConfig);
            if (color != 0) {
                button.setTextColor(ColorStateList.valueOf(color));
                return;
            }
            return;
        }
        HashMap hashMap = defaultTextColor;
        if (hashMap.containsKey(Integer.valueOf(button.getId()))) {
            button.setTextColor((ColorStateList) hashMap.get(Integer.valueOf(button.getId())));
            return;
        }
        throw new IllegalStateException("There is no saved default color for button");
    }
}
