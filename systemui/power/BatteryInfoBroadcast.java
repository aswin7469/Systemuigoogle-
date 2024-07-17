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
import android.os.PowerManager;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticOutline0;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.power.EnhancedEstimates;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryInfoBroadcast {
    public int mBatteryChargingStatus = 1;
    public int mBatteryLevel = -1;
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
                            this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                            return;
                        }
                        return;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true);
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
                            this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                            return;
                        }
                        return;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true);
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
                            this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                            return;
                        }
                        return;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true);
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
                            this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                            return;
                        }
                        return;
                    case 2:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mDeviceNameObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.batteryStatusChanged"));
                        return;
                    default:
                        MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("mRemainingTimeObserver: ", z, "BatteryInfoBroadcast");
                        this.this$0.sendBatteryChangeIntent(new Intent("PNW.batteryStatusChanged"), true);
                        return;
                }
            }
        };
        this.mRemainingTimeObserver = r5;
        this.mMetadataListener = new BluetoothAdapter.OnMetadataChangedListener() {
            public final void onMetadataChanged(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                ExifInterface$$ExternalSyntheticOutline0.m("onMetadataChanged: ", i, "BatteryInfoBroadcast");
                BatteryInfoBroadcast.this.sendBroadcast(BatteryInfoBroadcast.createIntentForSI("PNW.bluetoothStatusChanged"));
            }
        };
        this.mContext = context;
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        this.mPowerManager = powerManager;
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

    public final void registerObserver(Uri uri, AnonymousClass1 r4, String str) {
        try {
            this.mContext.getContentResolver().registerContentObserver(uri, false, r4, -1);
        } catch (Exception e) {
            Log.e("BatteryInfoBroadcast", "failed to register observer for ".concat(str), e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0074 A[SYNTHETIC, Splitter:B:28:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a0 A[SYNTHETIC, Splitter:B:40:0x00a0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void sendBatteryChangeIntent(android.content.Intent r13, boolean r14) {
        /*
            r12 = this;
            java.lang.String r0 = r13.getAction()
            java.lang.String r1 = "BatteryInfoBroadcast"
            if (r0 != 0) goto L_0x000e
            java.lang.String r12 = "sendBatteryIntent() with invalid intent"
            android.util.Log.w(r1, r12)
            return
        L_0x000e:
            java.lang.String r0 = r13.getAction()
            java.lang.String r2 = "PNW.batteryStatusChanged"
            android.content.Intent r2 = createIntentForSI(r2)
            java.lang.String r3 = "battery_save"
            boolean r4 = r12.mIsPowerSaveMode
            android.content.Intent r2 = r2.putExtra(r3, r4)
            java.lang.String r3 = "android.intent.action.BATTERY_CHANGED"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x002d
            java.lang.String r3 = "battery_changed_intent"
            r2.putExtra(r3, r13)
        L_0x002d:
            int r13 = r12.mBatteryPlugged
            boolean r13 = com.android.settingslib.fuelgauge.BatteryStatus.isPluggedIn(r13)
            android.content.Context r3 = r12.mContext
            r4 = -1
            if (r13 == 0) goto L_0x00a9
            java.lang.String r13 = "BatteryUsageStats.close() failed"
            com.google.android.systemui.power.BatteryInfoBroadcast$$ExternalSyntheticLambda1 r14 = new com.google.android.systemui.power.BatteryInfoBroadcast$$ExternalSyntheticLambda1
            r14.<init>(r12)
            com.google.common.util.concurrent.ListenableFutureTask r6 = new com.google.common.util.concurrent.ListenableFutureTask
            r6.<init>(r14)
            java.util.concurrent.Executor r14 = r12.mExecutor
            r14.execute(r6)
            r14 = 0
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ Exception -> 0x0069, all -> 0x0067 }
            r8 = 2
            java.lang.Object r6 = r6.get(r8, r7)     // Catch:{ Exception -> 0x0069, all -> 0x0067 }
            android.os.BatteryUsageStats r6 = (android.os.BatteryUsageStats) r6     // Catch:{ Exception -> 0x0069, all -> 0x0067 }
            long r7 = r6.getChargeTimeRemainingMs()     // Catch:{ Exception -> 0x0065 }
            r6.close()     // Catch:{ Exception -> 0x005d }
            goto L_0x007d
        L_0x005d:
            r14 = move-exception
            android.util.Log.e(r1, r13, r14)
            goto L_0x007d
        L_0x0062:
            r12 = move-exception
            r14 = r6
            goto L_0x009e
        L_0x0065:
            r14 = move-exception
            goto L_0x006d
        L_0x0067:
            r12 = move-exception
            goto L_0x009e
        L_0x0069:
            r6 = move-exception
            r11 = r6
            r6 = r14
            r14 = r11
        L_0x006d:
            java.lang.String r7 = "getBatteryUsageStats timeout"
            android.util.Log.e(r1, r7, r14)     // Catch:{ all -> 0x0062 }
            if (r6 == 0) goto L_0x007c
            r6.close()     // Catch:{ Exception -> 0x0078 }
            goto L_0x007c
        L_0x0078:
            r14 = move-exception
            android.util.Log.e(r1, r13, r14)
        L_0x007c:
            r7 = r4
        L_0x007d:
            int r13 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x009c
            android.content.ContentResolver r13 = r3.getContentResolver()
            java.lang.String r14 = "time_to_full_millis"
            long r4 = android.provider.Settings.Global.getLong(r13, r14, r4)
            int r13 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r13 != 0) goto L_0x0090
            goto L_0x009c
        L_0x0090:
            android.content.ContentResolver r13 = r3.getContentResolver()
            android.provider.Settings.Global.putLong(r13, r14, r7)
            java.lang.String r13 = "time_to_full"
            r2.putExtra(r13, r7)
        L_0x009c:
            r4 = r7
            goto L_0x00de
        L_0x009e:
            if (r14 == 0) goto L_0x00a8
            r14.close()     // Catch:{ Exception -> 0x00a4 }
            goto L_0x00a8
        L_0x00a4:
            r14 = move-exception
            android.util.Log.e(r1, r13, r14)
        L_0x00a8:
            throw r12
        L_0x00a9:
            com.android.systemui.power.EnhancedEstimates r13 = r12.mEnhancedEstimates
            if (r13 == 0) goto L_0x00de
            com.google.android.systemui.power.EnhancedEstimatesGoogleImpl r13 = (com.google.android.systemui.power.EnhancedEstimatesGoogleImpl) r13
            com.android.settingslib.fuelgauge.Estimate r13 = r13.getEstimate()
            long r6 = r13.estimateMillis
            if (r14 == 0) goto L_0x00c3
            long r8 = r12.mRemainingTimeMillis
            int r14 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r14 != 0) goto L_0x00c3
            java.lang.String r12 = "sendBatteryIntent() ignore from the same remaining time"
            android.util.Log.w(r1, r12)
            return
        L_0x00c3:
            r12.mRemainingTimeMillis = r6
            java.lang.String r14 = "remaining_time"
            r2.putExtra(r14, r6)
            android.content.ContentResolver r14 = r3.getContentResolver()
            java.lang.String r8 = "remaining_time_millis"
            long r9 = r12.mRemainingTimeMillis
            android.provider.Settings.Global.putLong(r14, r8, r9)
            r8 = 0
            int r14 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r14 <= 0) goto L_0x00de
            com.android.settingslib.fuelgauge.Estimate.storeCachedEstimate(r3, r13)
        L_0x00de:
            boolean r13 = r12.mIsPowerSaveMode
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)
            long r6 = r12.mRemainingTimeMillis
            java.lang.Long r14 = java.lang.Long.valueOf(r6)
            java.lang.Long r3 = java.lang.Long.valueOf(r4)
            java.lang.Object[] r13 = new java.lang.Object[]{r0, r13, r14, r3}
            java.lang.String r14 = "onReceive: %s, saverMode: %b, remainingTime: %d, timeToFull: %d"
            java.lang.String r13 = java.lang.String.format(r14, r13)
            android.util.Log.d(r1, r13)
            r12.sendBroadcast(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.BatteryInfoBroadcast.sendBatteryChangeIntent(android.content.Intent, boolean):void");
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
