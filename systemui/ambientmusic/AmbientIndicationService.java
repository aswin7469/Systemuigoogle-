package com.google.android.systemui.ambientmusic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class AmbientIndicationService extends BroadcastReceiver {
    public final AlarmManager mAlarmManager;
    public final AmbientIndicationContainer mAmbientIndicationContainer;
    public final KeyguardUpdateMonitorCallback mCallback = new KeyguardUpdateMonitorCallback() {
        public final void onUserSwitchComplete(int i) {
            AmbientIndicationService.this.onUserSwitched();
        }
    };
    public final Context mContext;
    public final AlarmManager.OnAlarmListener mHideIndicationListener;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public boolean mStarted;

    public AmbientIndicationService(Context context, AmbientIndicationContainer ambientIndicationContainer, AlarmManager alarmManager, SelectedUserInteractor selectedUserInteractor) {
        this.mContext = context;
        this.mAmbientIndicationContainer = ambientIndicationContainer;
        this.mAlarmManager = alarmManager;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mHideIndicationListener = new AmbientIndicationService$$ExternalSyntheticLambda0(ambientIndicationContainer);
    }

    public int getCurrentUser() {
        return this.mSelectedUserInteractor.getSelectedUserId();
    }

    public boolean isForCurrentUser() {
        if (getSendingUserId() == getCurrentUser() || getSendingUserId() == -1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onReceive(android.content.Context r22, android.content.Intent r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r23
            boolean r2 = r21.isForCurrentUser()
            java.lang.String r3 = "AmbientIndication"
            if (r2 != 0) goto L_0x0012
            java.lang.String r0 = "Suppressing ambient, not for this user."
            android.util.Log.i(r3, r0)
            return
        L_0x0012:
            java.lang.String r2 = "com.google.android.ambientindication.extra.VERSION"
            r4 = 0
            int r2 = r1.getIntExtra(r2, r4)
            r5 = 1
            if (r2 == r5) goto L_0x0033
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "AmbientIndicationApi.EXTRA_VERSION is 1, but received an intent with version "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r1 = ", dropping intent."
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.e(r3, r0)
            return
        L_0x0033:
            java.lang.String r2 = r23.getAction()
            int r6 = r2.hashCode()
            r7 = -1032272569(0xffffffffc278c547, float:-62.192654)
            if (r6 == r7) goto L_0x0050
            r7 = -1031945470(0xffffffffc27dc302, float:-63.440437)
            if (r6 == r7) goto L_0x0046
            goto L_0x005a
        L_0x0046:
            java.lang.String r6 = "com.google.android.ambientindication.action.AMBIENT_INDICATION_SHOW"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x005a
            r2 = r4
            goto L_0x005b
        L_0x0050:
            java.lang.String r6 = "com.google.android.ambientindication.action.AMBIENT_INDICATION_HIDE"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x005a
            r2 = r5
            goto L_0x005b
        L_0x005a:
            r2 = -1
        L_0x005b:
            if (r2 == 0) goto L_0x0078
            if (r2 == r5) goto L_0x0060
            goto L_0x00d3
        L_0x0060:
            android.app.AlarmManager r1 = r0.mAlarmManager
            android.app.AlarmManager$OnAlarmListener r2 = r0.mHideIndicationListener
            r1.cancel(r2)
            com.google.android.systemui.ambientmusic.AmbientIndicationContainer r4 = r0.mAmbientIndicationContainer
            r7 = 0
            r8 = 0
            r5 = 0
            r6 = 0
            r9 = 0
            r10 = 0
            r4.setAmbientMusic(r5, r6, r7, r8, r9, r10)
            java.lang.String r0 = "Hiding ambient indication."
            android.util.Log.i(r3, r0)
            goto L_0x00d3
        L_0x0078:
            java.lang.String r2 = "com.google.android.ambientindication.extra.TEXT"
            java.lang.CharSequence r6 = r1.getCharSequenceExtra(r2)
            java.lang.String r2 = "com.google.android.ambientindication.extra.OPEN_INTENT"
            android.os.Parcelable r2 = r1.getParcelableExtra(r2)
            r7 = r2
            android.app.PendingIntent r7 = (android.app.PendingIntent) r7
            java.lang.String r2 = "com.google.android.ambientindication.extra.FAVORITING_INTENT"
            android.os.Parcelable r2 = r1.getParcelableExtra(r2)
            r8 = r2
            android.app.PendingIntent r8 = (android.app.PendingIntent) r8
            java.lang.String r2 = "com.google.android.ambientindication.extra.TTL_MILLIS"
            r9 = 180000(0x2bf20, double:8.8932E-319)
            long r11 = r1.getLongExtra(r2, r9)
            r13 = 0
            long r11 = java.lang.Math.max(r11, r13)
            long r12 = java.lang.Math.min(r11, r9)
            java.lang.String r2 = "com.google.android.ambientindication.extra.SKIP_UNLOCK"
            boolean r10 = r1.getBooleanExtra(r2, r4)
            java.lang.String r2 = "com.google.android.ambientindication.extra.ICON_OVERRIDE"
            int r9 = r1.getIntExtra(r2, r4)
            java.lang.String r2 = "com.google.android.ambientindication.extra.ICON_DESCRIPTION"
            java.lang.String r11 = r1.getStringExtra(r2)
            com.google.android.systemui.ambientmusic.AmbientIndicationContainer r5 = r0.mAmbientIndicationContainer
            r5.setAmbientMusic(r6, r7, r8, r9, r10, r11)
            android.app.AlarmManager r14 = r0.mAlarmManager
            long r1 = android.os.SystemClock.elapsedRealtime()
            long r16 = r1 + r12
            android.app.AlarmManager$OnAlarmListener r0 = r0.mHideIndicationListener
            r15 = 2
            java.lang.String r18 = "AmbientIndication"
            r20 = 0
            r19 = r0
            r14.setExact(r15, r16, r18, r19, r20)
            java.lang.String r0 = "Showing ambient indication."
            android.util.Log.i(r3, r0)
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.ambientmusic.AmbientIndicationService.onReceive(android.content.Context, android.content.Intent):void");
    }

    public void onUserSwitched() {
        this.mAmbientIndicationContainer.setAmbientMusic((CharSequence) null, (PendingIntent) null, (PendingIntent) null, 0, false, (String) null);
    }
}
