package com.google.android.systemui.power;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.util.KeyValueListParser;
import android.util.Log;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.settingslib.utils.PowerUtil;
import com.android.systemui.power.EnhancedEstimates;
import java.time.Duration;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class EnhancedEstimatesGoogleImpl implements EnhancedEstimates {
    public final Context mContext;
    public final KeyValueListParser mParser = new KeyValueListParser(',');

    public EnhancedEstimatesGoogleImpl(Context context) {
        this.mContext = context;
    }

    public final Estimate getEstimate() {
        Cursor query;
        long j;
        try {
            query = this.mContext.getContentResolver().query(new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("time_remaining").build(), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    boolean z = true;
                    if (query.getColumnIndex("is_based_on_usage") != -1) {
                        if (query.getInt(query.getColumnIndex("is_based_on_usage")) == 0) {
                            z = false;
                        }
                    }
                    boolean z2 = z;
                    int columnIndex = query.getColumnIndex("average_battery_life");
                    if (columnIndex != -1) {
                        long j2 = query.getLong(columnIndex);
                        if (j2 != -1) {
                            long millis = Duration.ofMinutes(15).toMillis();
                            if (Duration.ofMillis(j2).compareTo(Duration.ofDays(1)) >= 0) {
                                millis = Duration.ofHours(1).toMillis();
                            }
                            j = PowerUtil.roundTimeToNearestThreshold(j2, millis);
                            Estimate estimate = new Estimate(query.getLong(query.getColumnIndex("battery_estimate")), j, z2);
                            query.close();
                            return estimate;
                        }
                    }
                    j = -1;
                    Estimate estimate2 = new Estimate(query.getLong(query.getColumnIndex("battery_estimate")), j, z2);
                    query.close();
                    return estimate2;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            Log.d("EnhancedEstimates", "Something went wrong when getting an estimate from Turbo", e);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return new Estimate(-1, -1, false);
        throw th;
    }

    public final boolean isHybridNotificationEnabled() {
        try {
            if (!this.mContext.getPackageManager().getPackageInfo("com.google.android.apps.turbo", 512).applicationInfo.enabled) {
                return false;
            }
            updateFlags();
            return this.mParser.getBoolean("hybrid_enabled", true);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final void updateFlags() {
        try {
            this.mParser.setString(Settings.Global.getString(this.mContext.getContentResolver(), "hybrid_sysui_battery_warning_flags"));
        } catch (IllegalArgumentException unused) {
            Log.e("EnhancedEstimates", "Bad hybrid sysui warning flags");
        }
    }
}
