package com.google.android.systemui.ambientmusic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class AmbientIndicationService$$ExternalSyntheticLambda0 implements AlarmManager.OnAlarmListener {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ AmbientIndicationService$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    public final void onAlarm() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                AmbientIndicationInteractor ambientIndicationInteractor = (AmbientIndicationInteractor) obj;
                ambientIndicationInteractor.ambientIndicationRepository.ambientMusic.setValue((Object) null);
                ((KeyguardRepositoryImpl) ambientIndicationInteractor.keyguardInteractor.repository).ambientIndicationVisible.setValue(Boolean.FALSE);
                return;
            default:
                ((AmbientIndicationContainer) obj).setAmbientMusic((CharSequence) null, (PendingIntent) null, (PendingIntent) null, 0, false, (String) null);
                return;
        }
    }
}
