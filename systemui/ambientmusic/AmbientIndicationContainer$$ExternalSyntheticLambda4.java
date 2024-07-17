package com.google.android.systemui.ambientmusic;

import android.view.View;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
