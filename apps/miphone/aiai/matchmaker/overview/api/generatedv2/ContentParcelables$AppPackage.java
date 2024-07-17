package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ContentParcelables$AppPackage {
    public final /* synthetic */ int $r8$classId;
    public String packageName;

    public ContentParcelables$AppPackage(int i, Bundle bundle) {
        this.$r8$classId = i;
        if (i != 1) {
            readFromBundle(bundle);
        } else {
            readFromBundle(bundle);
        }
    }

    public final void readFromBundle(Bundle bundle) {
        switch (this.$r8$classId) {
            case 0:
                if (bundle.containsKey("packageName")) {
                    this.packageName = bundle.getString("packageName");
                    return;
                }
                return;
            default:
                if (bundle.containsKey("deeplinkUri")) {
                    this.packageName = bundle.getString("deeplinkUri");
                    return;
                }
                return;
        }
    }
}
