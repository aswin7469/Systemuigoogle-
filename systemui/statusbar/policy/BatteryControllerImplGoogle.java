package com.google.android.systemui.statusbar.policy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserContentResolverProvider;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.google.android.systemui.power.PowerUtils;
import com.google.android.systemui.reversecharging.ReverseChargingController;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryControllerImplGoogle extends BatteryControllerImpl implements ReverseChargingController.ReverseChargingChangeCallback {
    public static final boolean DEBUG = Log.isLoggable("BatteryControllerGoogle", 3);
    public static final Uri IS_EBS_ENABLED_OBSERVABLE_URI = new Uri.Builder().scheme("content").authority("com.google.android.flipendo.api").appendPath("get_flipendo_state").build();
    protected final ContentObserver mContentObserver;
    public final UserContentResolverProvider mContentResolverProvider;
    public boolean mExtremeSaver;
    public String mName;
    public boolean mReverse;
    public final ReverseChargingController mReverseChargingController;
    public int mRtxLevel;

    public BatteryControllerImplGoogle(Context context, EnhancedEstimates enhancedEstimates, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher, DemoModeController demoModeController, DumpManager dumpManager, BatteryControllerLogger batteryControllerLogger, Handler handler, Handler handler2, UserContentResolverProvider userContentResolverProvider, ReverseChargingController reverseChargingController) {
        super(context, enhancedEstimates, powerManager, broadcastDispatcher, demoModeController, dumpManager, batteryControllerLogger, handler, handler2);
        this.mReverseChargingController = reverseChargingController;
        this.mContentResolverProvider = userContentResolverProvider;
        this.mContentObserver = new ContentObserver(handler2) {
            public final void onChange(boolean z, Uri uri) {
                if (BatteryControllerImplGoogle.DEBUG) {
                    Log.d("BatteryControllerGoogle", "Change in EBS value " + uri.toSafeString());
                }
                BatteryControllerImplGoogle batteryControllerImplGoogle = BatteryControllerImplGoogle.this;
                boolean isFlipendoEnabled = PowerUtils.isFlipendoEnabled(((UserTrackerImpl) batteryControllerImplGoogle.mContentResolverProvider).getUserContext().getContentResolver());
                if (isFlipendoEnabled != batteryControllerImplGoogle.mExtremeSaver) {
                    batteryControllerImplGoogle.mExtremeSaver = isFlipendoEnabled;
                    synchronized (batteryControllerImplGoogle.mChangeCallbacks) {
                        try {
                            int size = batteryControllerImplGoogle.mChangeCallbacks.size();
                            for (int i = 0; i < size; i++) {
                                ((BatteryController.BatteryStateChangeCallback) batteryControllerImplGoogle.mChangeCallbacks.get(i)).onExtremeBatterySaverChanged(batteryControllerImplGoogle.mExtremeSaver);
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            }
        };
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        super.dump(printWriter, strArr);
        printWriter.print("  mReverse=");
        printWriter.println(this.mReverse);
        printWriter.print("  mExtremeSaver=");
        printWriter.println(this.mExtremeSaver);
    }

    public final void init$10() {
        super.init$10();
        this.mReverse = false;
        this.mRtxLevel = -1;
        this.mName = null;
        this.mReverseChargingController.init(this);
        this.mReverseChargingController.addCallback((ReverseChargingController.ReverseChargingChangeCallback) this);
        try {
            ContentResolver contentResolver = ((UserTrackerImpl) this.mContentResolverProvider).getUserContext().getContentResolver();
            Uri uri = IS_EBS_ENABLED_OBSERVABLE_URI;
            contentResolver.registerContentObserver(uri, false, this.mContentObserver, -1);
            this.mContentObserver.onChange(false, uri);
        } catch (Exception e) {
            Log.w("BatteryControllerGoogle", "Couldn't register to observe provider", e);
        }
    }

    public final boolean isReverseOn() {
        return this.mReverse;
    }

    public final boolean isReverseSupported() {
        return this.mReverseChargingController.isReverseSupported();
    }

    public final void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        this.mReverseChargingController.handleIntentForReverseCharging(intent);
    }

    public final void onReverseChargingChanged(int i, String str, boolean z) {
        this.mReverse = z;
        this.mRtxLevel = i;
        this.mName = str;
        if (DEBUG) {
            StringBuilder m = ValidatingOffsetMapping$$ExternalSyntheticOutline0.m("onReverseChargingChanged(): rtx=", z ? 1 : 0, " level=", i, " name=");
            m.append(str);
            m.append(" this=");
            m.append(this);
            Log.d("BatteryControllerGoogle", m.toString());
        }
        synchronized (this.mChangeCallbacks) {
            try {
                ArrayList arrayList = new ArrayList(this.mChangeCallbacks);
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((BatteryController.BatteryStateChangeCallback) arrayList.get(i2)).onReverseChanged(this.mRtxLevel, this.mName, this.mReverse);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setReverseState(boolean z) {
        ReverseChargingController reverseChargingController = this.mReverseChargingController;
        if (reverseChargingController.isReverseSupported()) {
            if (ReverseChargingController.DEBUG) {
                ExifInterface$$ExternalSyntheticOutline0.m("setReverseState(): rtx=", z ? 1 : 0, "ReverseChargingControl");
            }
            reverseChargingController.mStopReverseAtAcUnplug = false;
            reverseChargingController.setReverseStateInternal(2, z);
        }
    }

    public final void addCallback(BatteryController.BatteryStateChangeCallback batteryStateChangeCallback) {
        super.addCallback(batteryStateChangeCallback);
        batteryStateChangeCallback.onReverseChanged(this.mRtxLevel, this.mName, this.mReverse);
        batteryStateChangeCallback.onExtremeBatterySaverChanged(this.mExtremeSaver);
    }
}
