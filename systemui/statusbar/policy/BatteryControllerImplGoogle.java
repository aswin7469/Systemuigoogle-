package com.google.android.systemui.statusbar.policy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerLogger;
import com.google.android.systemui.power.PowerUtils;
import com.google.android.systemui.reversecharging.ReverseChargingController;
import java.io.PrintWriter;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryControllerImplGoogle extends BatteryControllerImpl implements ReverseChargingController.ReverseChargingChangeCallback {
    public static final boolean DEBUG = Log.isLoggable("BatteryControllerGoogle", 3);
    public static final Uri IS_EBS_ENABLED_OBSERVABLE_URI = new Uri.Builder().scheme("content").authority("com.google.android.flipendo.api").appendPath("get_flipendo_state").build();
    protected final ContentObserver mContentObserver;
    public final UserTracker mContentResolverProvider;
    public boolean mExtremeSaver;
    public String mName;
    public boolean mReverse;
    public final ReverseChargingController mReverseChargingController;
    public int mRtxLevel;

    public BatteryControllerImplGoogle(Context context, EnhancedEstimates enhancedEstimates, PowerManager powerManager, BroadcastDispatcher broadcastDispatcher, DemoModeController demoModeController, DumpManager dumpManager, BatteryControllerLogger batteryControllerLogger, Handler handler, Handler handler2, UserTracker userTracker, ReverseChargingController reverseChargingController) {
        super(context, enhancedEstimates, powerManager, broadcastDispatcher, demoModeController, dumpManager, batteryControllerLogger, handler, handler2);
        this.mReverseChargingController = reverseChargingController;
        this.mContentResolverProvider = userTracker;
        this.mContentObserver = new ContentObserver(handler2) {
            public final void onChange(boolean z, Uri uri) {
                if (BatteryControllerImplGoogle.DEBUG) {
                    Log.d("BatteryControllerGoogle", "Change in EBS value " + uri.toSafeString());
                }
                BatteryControllerImplGoogle batteryControllerImplGoogle = BatteryControllerImplGoogle.this;
                boolean isFlipendoEnabled = PowerUtils.isFlipendoEnabled(((UserTrackerImpl) batteryControllerImplGoogle.mContentResolverProvider).getUserContext().getContentResolver());
                if (isFlipendoEnabled != batteryControllerImplGoogle.mExtremeSaver) {
                    batteryControllerImplGoogle.mExtremeSaver = isFlipendoEnabled;
                    batteryControllerImplGoogle.dispatchSafeChange(new BatteryControllerImplGoogle$$ExternalSyntheticLambda0(batteryControllerImplGoogle, 1));
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
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m("onReverseChargingChanged(): rtx=", z ? 1 : 0, " level=", i, " name=");
            m.append(str);
            m.append(" this=");
            m.append(this);
            Log.d("BatteryControllerGoogle", m.toString());
        }
        dispatchSafeChange(new BatteryControllerImplGoogle$$ExternalSyntheticLambda0(this, 0));
    }

    public final void setReverseState(boolean z) {
        ReverseChargingController reverseChargingController = this.mReverseChargingController;
        if (reverseChargingController.isReverseSupported()) {
            if (ReverseChargingController.DEBUG) {
                ExifInterface$$ExternalSyntheticOutline0.m("setReverseState(): rtx=", "ReverseChargingControl", z ? 1 : 0);
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