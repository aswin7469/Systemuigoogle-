package com.google.android.setupdesign.template;

import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.template.Mixin;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class IllustrationProgressMixin implements Mixin {

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public enum ProgressConfig {
        ;
        
        private final PartnerConfig config;

        /* access modifiers changed from: public */
        ProgressConfig(PartnerConfig partnerConfig) {
            if (partnerConfig.getResourceType() == PartnerConfig.ResourceType.ILLUSTRATION) {
                this.config = partnerConfig;
                return;
            }
            throw new IllegalArgumentException("Illustration progress only allow illustration resource");
        }
    }
}
