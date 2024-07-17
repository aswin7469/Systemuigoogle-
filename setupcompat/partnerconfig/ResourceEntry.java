package com.google.android.setupcompat.partnerconfig;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ResourceEntry {
    static final String KEY_FALLBACK_CONFIG = "fallbackConfig";
    static final String KEY_PACKAGE_NAME = "packageName";
    static final String KEY_RESOURCE_ID = "resourceId";
    static final String KEY_RESOURCE_NAME = "resourceName";
    public final String packageName;
    public final int resourceId;
    public final String resourceName;
    public final Resources resources;

    public ResourceEntry(String str, String str2, int i, Resources resources2) {
        this.packageName = str;
        this.resourceName = str2;
        this.resourceId = i;
        this.resources = resources2;
    }

    public static ResourceEntry fromBundle(Context context, Bundle bundle) {
        if (bundle.containsKey(KEY_PACKAGE_NAME) && bundle.containsKey(KEY_RESOURCE_NAME) && bundle.containsKey(KEY_RESOURCE_ID)) {
            String string = bundle.getString(KEY_PACKAGE_NAME);
            String string2 = bundle.getString(KEY_RESOURCE_NAME);
            int i = bundle.getInt(KEY_RESOURCE_ID);
            try {
                PackageManager packageManager = context.getPackageManager();
                return new ResourceEntry(string, string2, i, packageManager.getResourcesForApplication(packageManager.getApplicationInfo(string, 512)));
            } catch (PackageManager.NameNotFoundException unused) {
                Bundle bundle2 = bundle.getBundle("fallbackConfig");
                if (bundle2 != null) {
                    Log.w("ResourceEntry", string + " not found, " + string2 + " fallback to default value");
                    return fromBundle(context, bundle2);
                }
            }
        }
        return null;
    }
}
