package com.google.android.systemui.power;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BatteryInfoBroadcast {
    public int mBatteryChargingStatus = 1;
    public int mBatteryLevel = -1;
    public final BatteryManager mBatteryManager;
    public int mBatteryPlugged = 0;
    public int mBatteryStatus = 1;
    public final BroadcastSender mBroadcastSender;
    public final Context mContext;
    final ContentObserver mDeviceNameObserver;
    public final EnhancedEstimates mEnhancedEstimates;
    public final Executor mExecutor;
    public boolean mIsPowerSaveMode = false;
    final BluetoothAdapter.OnMetadataChangedListener mMetadataListener;
    public final PowerManager mPowerManager;
    public long mRemainingTimeMillis = -1;
    final ContentObserver mRemainingTimeObserver;
    public final SharedPreferences mSharedPreferences;
    public long mTimeToFullMillis = -1;
    final ContentObserver mTimeToFullObserver;
    public final UserTracker mUserTracker;
    final ContentObserver mWidgetEnableObserver;
    public boolean mWidgetEnabled = true;

    public BatteryInfoBroadcast(Context context, BroadcastSender broadcastSender, EnhancedEstimates enhancedEstimates, Executor executor, UserTracker userTracker) {
        AnonymousClass1 r2 = new ContentObserver(this, 0) {
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            {
                this.this$0 = r1;
            }

            public final void onChange(boolean z) {
                boolean z2;
                switch (3) {
                    case 0:
                        if (Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        return;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            return;
                        }
                        return;
                    case 2:
                        Utils$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        Utils$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        return;
                }
            }
        };
        this.mWidgetEnableObserver = r2;
        AnonymousClass1 r3 = new ContentObserver(this, 1) {
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            {
                this.this$0 = r1;
            }

            public final void onChange(boolean z) {
                boolean z2;
                switch (3) {
                    case 0:
                        if (Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        return;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            return;
                        }
                        return;
                    case 2:
                        Utils$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        Utils$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        return;
                }
            }
        };
        this.mTimeToFullObserver = r3;
        AnonymousClass1 r4 = new ContentObserver(this, 2) {
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            {
                this.this$0 = r1;
            }

            public final void onChange(boolean z) {
                boolean z2;
                switch (3) {
                    case 0:
                        if (Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        return;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            return;
                        }
                        return;
                    case 2:
                        Utils$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        Utils$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        return;
                }
            }
        };
        this.mDeviceNameObserver = r4;
        AnonymousClass1 r5 = new ContentObserver(this, 3) {
            public final /* synthetic */ BatteryInfoBroadcast this$0;

            {
                this.this$0 = r1;
            }

            public final void onChange(boolean z) {
                boolean z2;
                switch (3) {
                    case 0:
                        if (Settings.Secure.getIntForUser(this.this$0.mContext.getContentResolver(), "battery_widget_enabled", 1, ((UserTrackerImpl) this.this$0.mUserTracker).getUserId()) == 1) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            BatteryInfoBroadcast batteryInfoBroadcast = this.this$0;
                            if (!batteryInfoBroadcast.mWidgetEnabled) {
                                batteryInfoBroadcast.mWidgetEnabled = true;
                                batteryInfoBroadcast.mExecutor.execute(new BatteryInfoBroadcast$$ExternalSyntheticLambda0(batteryInfoBroadcast, new Intent("PNW.batteryStatusChanged")));
                            }
                        }
                        this.this$0.mWidgetEnabled = z2;
                        CachedBluetoothDevice$$ExternalSyntheticOutline0.m(new StringBuilder("mWidgetEnableObserver: "), this.this$0.mWidgetEnabled, "BatteryInfoBroadcast");
                        return;
                    case 1:
                        if (!z) {
                            this.this$0.sendBatteryChangeIntent(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"), false, true);
                            return;
                        }
                        return;
                    case 2:
                        Utils$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        Utils$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", "BatteryInfoBroadcast", z);
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true, false);
                        return;
                }
            }
        };
        this.mRemainingTimeObserver = r5;
        this.mMetadataListener = new BluetoothAdapter.OnMetadataChangedListener() {
            public final void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                ExifInterface$$ExternalSyntheticOutline0.m("onMetadataChanged: ", "BatteryInfoBroadcast", i);
                BatteryInfoBroadcast.this.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.bluetoothStatusChanged"));
            }
        };
        this.mContext = context;
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mPowerManager = powerManager;
        this.mBatteryManager = (BatteryManager) context.getSystemService(BatteryManager.class);
        this.mIsPowerSaveMode = powerManager.isPowerSaveMode();
        this.mBroadcastSender = broadcastSender;
        this.mEnhancedEstimates = executor == null ? null : enhancedEstimates;
        this.mExecutor = executor;
        this.mUserTracker = userTracker;
        r2.onChange(true);
        this.mSharedPreferences = context.getApplicationContext().getSharedPreferences("battery_info_shared_prefs", 0);
        registerObserver(Settings.Global.getUriFor("device_name"), r4, "device name");
        registerObserver(new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("time_remaining").build(), r5, "remaining time");
        registerObserver(Settings.Secure.getUriFor("battery_widget_enabled"), r2, "enabled widget");
        registerObserver(Settings.Global.getUriFor("time_to_full_millis"), r3, "time to full");
    }

    public static Intent createIntentForSI(String str) {
        return new Intent(str).setPackage("com.google.android.settings.intelligence");
    }

    public final void recordDateTime(String str) {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            Uri uri = DumpUtils.PROVIDER_URI;
            edit.putString(str, DumpUtils.toReadableDateTime(System.currentTimeMillis())).apply();
        }
    }

    public final void registerObserver(Uri uri, ContentObserver contentObserver, String str) {
        try {
            this.mContext.getContentResolver().registerContentObserver(uri, false, contentObserver, -1);
        } catch (Exception e) {
            Log.e("BatteryInfoBroadcast", "failed to register observer for ".concat(str), e);
        }
    }

    public final void sendBatteryChangeIntent(Intent intent, boolean z, boolean z2) {
        long j;
        if (intent == null || intent.getAction() == null) {
            Log.w("BatteryInfoBroadcast", "sendBatteryIntent() with invalid intent");
            return;
        }
        String action = intent.getAction();
        Intent putExtra = createIntentForSI("PNW.batteryStatusChanged").putExtra("battery_save", this.mIsPowerSaveMode);
        if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
            putExtra.putExtra("battery_changed_intent", intent);
        }
        boolean isPluggedIn = BatteryStatus.isPluggedIn(this.mBatteryPlugged);
        Context context = this.mContext;
        long j2 = -1;
        if (isPluggedIn) {
            try {
                j = this.mBatteryManager.computeChargeTimeRemaining();
            } catch (Exception e) {
                Log.w("BatteryInfoBroadcast", "computeChargeTimeRemaining failed.", e);
                j = -1;
            }
            Log.d("BatteryInfoBroadcast", "computeChargeTimeRemaining=" + j);
            if (!z2 || this.mTimeToFullMillis != j) {
                if (this.mTimeToFullMillis != j) {
                    if (j != 0) {
                        j2 = j;
                    }
                    this.mTimeToFullMillis = j2;
                    Settings.Global.putLong(context.getContentResolver(), "time_to_full_millis", this.mTimeToFullMillis);
                }
                putExtra.putExtra("time_to_full", this.mTimeToFullMillis);
                j2 = j;
            } else {
                Log.w("BatteryInfoBroadcast", "sendBroadcastIntent() ignore from the same timeToFull");
                return;
            }
        } else {
            EnhancedEstimates enhancedEstimates = this.mEnhancedEstimates;
            if (enhancedEstimates != null) {
                Estimate estimate = ((EnhancedEstimatesGoogleImpl) enhancedEstimates).getEstimate();
                long j3 = estimate.estimateMillis;
                if (!z || this.mRemainingTimeMillis != j3) {
                    this.mRemainingTimeMillis = j3;
                    putExtra.putExtra("remaining_time", j3);
                    Settings.Global.putLong(context.getContentResolver(), "remaining_time_millis", this.mRemainingTimeMillis);
                    if (j3 > 0) {
                        Estimate.storeCachedEstimate(context, estimate);
                    }
                } else {
                    Log.w("BatteryInfoBroadcast", "sendBatteryIntent() ignore from the same remaining time");
                    return;
                }
            }
        }
        Log.d("BatteryInfoBroadcast", String.format("onReceive: %s, saverMode: %b, remainingTime: %d, timeToFull: %d", new Object[]{action, Boolean.valueOf(this.mIsPowerSaveMode), Long.valueOf(this.mRemainingTimeMillis), Long.valueOf(j2)}));
        sendBroadcast(putExtra);
    }

    public final void sendBroadcast(Intent intent) {
        BroadcastSender broadcastSender = this.mBroadcastSender;
        if (broadcastSender != null && intent != null) {
            broadcastSender.sendBroadcastAsUser(intent, UserHandle.ALL);
        }
    }

    public final void sendPluggedInStateIntent(boolean z) {
        String str;
        if (z) {
            str = "com.android.settings.battery.action.ACTION_BATTERY_PLUGGING";
        } else {
            str = "com.android.settings.battery.action.ACTION_BATTERY_UNPLUGGING";
        }
        sendBroadcast(new Intent(str).setComponent(new ComponentName("com.android.settings", "com.android.settings.fuelgauge.batteryusage.BatteryUsageBroadcastReceiver")));
        if (!z) {
            Intent registerReceiver = this.mContext.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (BatteryStatus.isCharged(registerReceiver.getIntExtra("status", 1), BatteryStatus.getBatteryLevel(registerReceiver))) {
                recordDateTime("last_data_reset_time");
            }
        }
    }

    public final void writeString(PrintWriter printWriter, String str, String str2) {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str2, "");
            printWriter.println("\t\t" + str + ": " + string);
        }
    }
}
