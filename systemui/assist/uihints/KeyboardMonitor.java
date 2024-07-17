package com.google.android.systemui.assist.uihints;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.android.systemui.statusbar.CommandQueue;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import java.util.Optional;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class KeyboardMonitor implements CommandQueue.Callbacks, NgaMessageHandler.ConfigInfoListener {
    public final Context mContext;
    public PendingIntent mOnKeyboardShowingChanged;
    public boolean mShowing;

    public KeyboardMonitor(Context context, Optional optional) {
        this.mContext = context;
        optional.ifPresent(new KeyboardMonitor$$ExternalSyntheticLambda0(this));
    }

    public final void onConfigInfo(NgaMessageHandler.ConfigInfo configInfo) {
        PendingIntent pendingIntent = this.mOnKeyboardShowingChanged;
        PendingIntent pendingIntent2 = configInfo.onKeyboardShowingChange;
        if (pendingIntent != pendingIntent2) {
            this.mOnKeyboardShowingChanged = pendingIntent2;
            trySendKeyboardShowing();
        }
    }

    public final void setImeWindowStatus(int i, IBinder iBinder, int i2, int i3, boolean z) {
        boolean z2;
        boolean z3 = this.mShowing;
        if ((i2 & 2) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.mShowing = z2;
        if (z2 != z3) {
            trySendKeyboardShowing();
        }
    }

    public final void trySendKeyboardShowing() {
        if (this.mOnKeyboardShowingChanged != null) {
            Intent intent = new Intent();
            intent.putExtra("is_keyboard_showing", this.mShowing);
            try {
                this.mOnKeyboardShowingChanged.send(this.mContext, 0, intent);
            } catch (PendingIntent.CanceledException e) {
                Log.e("KeyboardMonitor", "onKeyboardShowingChanged pending intent cancelled", e);
            }
        }
    }
}
