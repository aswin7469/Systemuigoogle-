package com.google.android.systemui.columbus.legacy;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings;
import android.util.Log;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusStructuredDataManager {
    public final Set allowPackageList;
    public final ContentResolver contentResolver;
    public final Object lock = new Object();
    public JSONArray packageStats;
    public final UserTracker userTracker;
    public final ColumbusStructuredDataManager$userTrackerCallback$1 userTrackerCallback;

    public ColumbusStructuredDataManager(Context context, UserTracker userTracker2, Executor executor) {
        this.userTracker = userTracker2;
        this.contentResolver = context.getContentResolver();
        String[] stringArray = context.getResources().getStringArray(2130903088);
        this.allowPackageList = SetsKt.setOf(Arrays.copyOf(stringArray, stringArray.length));
        ColumbusStructuredDataManager$userTrackerCallback$1 columbusStructuredDataManager$userTrackerCallback$1 = new ColumbusStructuredDataManager$userTrackerCallback$1(this);
        this.userTrackerCallback = columbusStructuredDataManager$userTrackerCallback$1;
        ColumbusStructuredDataManager$broadcastReceiver$1 columbusStructuredDataManager$broadcastReceiver$1 = new ColumbusStructuredDataManager$broadcastReceiver$1(this);
        this.packageStats = fetchPackageStats();
        ((UserTrackerImpl) userTracker2).addCallback(columbusStructuredDataManager$userTrackerCallback$1, executor);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(columbusStructuredDataManager$broadcastReceiver$1, intentFilter);
    }

    public static JSONObject makeJSONObject$default(ColumbusStructuredDataManager columbusStructuredDataManager, String str, int i, long j, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            j = 0;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("packageName", str);
        jSONObject.put("shownCount", i);
        jSONObject.put("lastDeny", j);
        return jSONObject;
    }

    public final JSONArray fetchPackageStats() {
        JSONArray jSONArray;
        synchronized (this.lock) {
            String stringForUser = Settings.Secure.getStringForUser(this.contentResolver, "columbus_package_stats", ((UserTrackerImpl) this.userTracker).getUserId());
            if (stringForUser == null) {
                stringForUser = "[]";
            }
            try {
                jSONArray = new JSONArray(stringForUser);
            } catch (JSONException e) {
                Log.e("Columbus/StructuredData", "Failed to parse package counts", e);
                jSONArray = new JSONArray();
            }
        }
        return jSONArray;
    }

    public final long getLastDenyTimestamp(String str) {
        synchronized (this.lock) {
            int length = this.packageStats.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = this.packageStats.getJSONObject(i);
                if (Intrinsics.areEqual(str, jSONObject.getString("packageName"))) {
                    long j = jSONObject.getLong("lastDeny");
                    return j;
                }
            }
            return 0;
        }
    }

    public final int getPackageShownCount(String str) {
        synchronized (this.lock) {
            int length = this.packageStats.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = this.packageStats.getJSONObject(i);
                if (Intrinsics.areEqual(str, jSONObject.getString("packageName"))) {
                    int i2 = jSONObject.getInt("shownCount");
                    return i2;
                }
            }
            return 0;
        }
    }

    public final void storePackageStats() {
        synchronized (this.lock) {
            Settings.Secure.putStringForUser(this.contentResolver, "columbus_package_stats", this.packageStats.toString(), ((UserTrackerImpl) this.userTracker).getUserId());
        }
    }
}
