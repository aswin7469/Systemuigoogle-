package com.google.android.systemui.columbus.legacy.actions;

import android.content.Context;
import android.media.AudioManager;
import android.os.SystemClock;
import android.view.KeyEvent;
import com.android.internal.logging.UiEventLogger;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ManageMedia extends UserAction {
    public final AudioManager audioManager;
    public final String tag = "Columbus/ManageMedia";
    public final UiEventLogger uiEventLogger;

    public ManageMedia(Context context, AudioManager audioManager2, UiEventLogger uiEventLogger2) {
        super(context, (Set) null);
        this.audioManager = audioManager2;
        this.uiEventLogger = uiEventLogger2;
        setAvailable(true);
    }

    public final boolean availableOnLockscreen() {
        return true;
    }

    public final boolean availableOnScreenOff() {
        return true;
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        boolean z;
        AudioManager audioManager2 = this.audioManager;
        if (audioManager2.isMusicActive() || audioManager2.isMusicActiveRemotely()) {
            z = true;
        } else {
            z = false;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        audioManager2.dispatchMediaKeyEvent(new KeyEvent(uptimeMillis, uptimeMillis, 0, 85, 0));
        audioManager2.dispatchMediaKeyEvent(new KeyEvent(uptimeMillis, uptimeMillis, 1, 85, 0));
        UiEventLogger uiEventLogger2 = this.uiEventLogger;
        if (z) {
            uiEventLogger2.log(ColumbusEvent.COLUMBUS_INVOKED_PAUSE_MEDIA);
        } else {
            uiEventLogger2.log(ColumbusEvent.COLUMBUS_INVOKED_PLAY_MEDIA);
        }
    }
}
