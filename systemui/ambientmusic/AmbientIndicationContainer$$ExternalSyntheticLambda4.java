package com.google.android.systemui.ambientmusic;

import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class AmbientIndicationContainer$$ExternalSyntheticLambda4 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AmbientIndicationContainer f$0;

    public /* synthetic */ AmbientIndicationContainer$$ExternalSyntheticLambda4(AmbientIndicationContainer ambientIndicationContainer, int i) {
        this.$r8$classId = i;
        this.f$0 = ambientIndicationContainer;
    }

    public final void onClick(View view) {
        int i = this.$r8$classId;
        AmbientIndicationContainer ambientIndicationContainer = this.f$0;
        switch (i) {
            case 0:
                int i2 = AmbientIndicationContainer.$r8$clinit;
                ambientIndicationContainer.onTextClick();
                return;
            default:
                if (ambientIndicationContainer.mFavoritingIntent != null) {
                    ambientIndicationContainer.mPowerInteractor.wakeUpIfDozing(4, "AMBIENT_MUSIC_CLICK");
                    AmbientIndicationContainer.sendBroadcastWithoutDismissingKeyguard(ambientIndicationContainer.mFavoritingIntent);
                    return;
                }
                ambientIndicationContainer.onTextClick();
                return;
        }
    }
}
