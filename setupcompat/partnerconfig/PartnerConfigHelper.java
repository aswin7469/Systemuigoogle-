package com.google.android.setupcompat.partnerconfig;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import androidx.window.embedding.ActivityEmbeddingController;
import androidx.window.embedding.EmbeddingCompat;
import androidx.window.embedding.ExtensionEmbeddingBackend;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.util.BuildCompatUtils;
import java.util.EnumMap;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class PartnerConfigHelper {
    public static final String EMBEDDED_ACTIVITY_RESOURCE_SUFFIX = "_embedded_activity";
    static final String FORCE_TWO_PANE_SUFFIX = "_two_pane";
    public static final String GET_SUW_DEFAULT_THEME_STRING_METHOD = "suwDefaultThemeString";
    public static final String IS_DYNAMIC_COLOR_ENABLED_METHOD = "isDynamicColorEnabled";
    public static final String IS_EMBEDDED_ACTIVITY_ONE_PANE_ENABLED_METHOD = "isEmbeddedActivityOnePaneEnabled";
    public static final String IS_EXTENDED_PARTNER_CONFIG_ENABLED_METHOD = "isExtendedPartnerConfigEnabled";
    public static final String IS_FONT_WEIGHT_ENABLED_METHOD = "isFontWeightEnabled";
    public static final String IS_FORCE_TWO_PANE_ENABLED_METHOD = "isForceTwoPaneEnabled";
    public static final String IS_FULL_DYNAMIC_COLOR_ENABLED_METHOD = "isFullDynamicColorEnabled";
    public static final String IS_MATERIAL_YOU_STYLE_ENABLED_METHOD = "IsMaterialYouStyleEnabled";
    public static final String IS_NEUTRAL_BUTTON_STYLE_ENABLED_METHOD = "isNeutralButtonStyleEnabled";
    public static final String IS_SUW_DAY_NIGHT_ENABLED_METHOD = "isSuwDayNightEnabled";
    public static final String KEY_FALLBACK_CONFIG = "fallbackConfig";
    public static final String MATERIAL_YOU_RESOURCE_SUFFIX = "_material_you";
    public static final String SUW_GET_PARTNER_CONFIG_METHOD = "getOverlayConfig";
    public static final String SUW_PACKAGE_NAME = "com.google.android.setupwizard";
    public static Bundle applyDynamicColorBundle = null;
    public static Bundle applyEmbeddedActivityOnePaneBundle = null;
    public static Bundle applyExtendedPartnerConfigBundle = null;
    public static Bundle applyFontWeightBundle = null;
    public static Bundle applyForceTwoPaneBundle = null;
    public static Bundle applyFullDynamicColorBundle = null;
    public static Bundle applyMaterialYouConfigBundle = null;
    public static Bundle applyNeutralButtonStyleBundle = null;
    static Bundle applyTransitionBundle = null;
    public static AnonymousClass1 contentObserver = null;
    public static PartnerConfigHelper instance = null;
    public static boolean savedConfigEmbeddedActivityMode = false;
    public static int savedConfigUiMode = 0;
    public static int savedOrientation = 1;
    public static int savedScreenHeight;
    public static int savedScreenWidth;
    static Bundle suwDayNightEnabledBundle;
    public static Bundle suwDefaultThemeBundle;
    final EnumMap partnerResourceCache;
    Bundle resultBundle = null;

    /* renamed from: com.google.android.setupcompat.partnerconfig.PartnerConfigHelper$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 extends ContentObserver {
        public final void onChange(boolean z) {
            super.onChange(z);
            PartnerConfigHelper.resetInstance();
        }
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [com.google.android.setupcompat.partnerconfig.PartnerConfigHelper$1, android.database.ContentObserver] */
    public PartnerConfigHelper(Context context) {
        Object obj;
        EnumMap enumMap = new EnumMap(PartnerConfig.class);
        this.partnerResourceCache = enumMap;
        Bundle bundle = this.resultBundle;
        if (bundle == null || bundle.isEmpty()) {
            try {
                this.resultBundle = context.getContentResolver().call(getContentUri(), SUW_GET_PARTNER_CONFIG_METHOD, (String) null, (Bundle) null);
                enumMap.clear();
                StringBuilder sb = new StringBuilder("PartnerConfigsBundle=");
                Bundle bundle2 = this.resultBundle;
                if (bundle2 != null) {
                    obj = Integer.valueOf(bundle2.size());
                } else {
                    obj = "(null)";
                }
                sb.append(obj);
                Log.i("PartnerConfigHelper", sb.toString());
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "Fail to get config from suw provider");
            }
        }
        if (isSetupWizardDayNightEnabled(context)) {
            if (contentObserver != null) {
                try {
                    context.getContentResolver().unregisterContentObserver(contentObserver);
                    contentObserver = null;
                } catch (IllegalArgumentException | NullPointerException | SecurityException e) {
                    Log.w("PartnerConfigHelper", "Failed to unregister content observer: " + e);
                }
            }
            Uri contentUri = getContentUri();
            try {
                contentObserver = new ContentObserver((Handler) null);
                context.getContentResolver().registerContentObserver(contentUri, true, contentObserver);
            } catch (IllegalArgumentException | NullPointerException | SecurityException e2) {
                Log.w("PartnerConfigHelper", "Failed to register content observer for " + contentUri + ": " + e2);
            }
        }
    }

    public static synchronized PartnerConfigHelper get(Context context) {
        PartnerConfigHelper partnerConfigHelper;
        synchronized (PartnerConfigHelper.class) {
            try {
                if (!isValidInstance(context)) {
                    instance = new PartnerConfigHelper(context);
                }
                partnerConfigHelper = instance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return partnerConfigHelper;
    }

    public static Uri getContentUri() {
        return new Uri.Builder().scheme("content").authority("com.google.android.setupwizard.partner").build();
    }

    public static TypedValue getTypedValueFromResource(int i, Resources resources) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i, typedValue, true);
        if (typedValue.type == 5) {
            return typedValue;
        }
        throw new Resources.NotFoundException("Resource ID #0x" + Integer.toHexString(i) + " type #0x" + Integer.toHexString(typedValue.type) + " is not valid");
    }

    public static boolean isEmbeddedActivityOnePaneEnabled(Context context) {
        if (applyEmbeddedActivityOnePaneBundle == null) {
            try {
                applyEmbeddedActivityOnePaneBundle = context.getContentResolver().call(getContentUri(), IS_EMBEDDED_ACTIVITY_ONE_PANE_ENABLED_METHOD, (String) null, (Bundle) null);
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "SetupWizard one-pane support in embedded activity status unknown; return as false.");
                applyEmbeddedActivityOnePaneBundle = null;
                return false;
            }
        }
        Bundle bundle = applyEmbeddedActivityOnePaneBundle;
        if (bundle == null || !bundle.getBoolean(IS_EMBEDDED_ACTIVITY_ONE_PANE_ENABLED_METHOD, false)) {
            return false;
        }
        return true;
    }

    public static boolean isFontWeightEnabled(Context context) {
        if (applyFontWeightBundle == null) {
            try {
                applyFontWeightBundle = context.getContentResolver().call(getContentUri(), IS_FONT_WEIGHT_ENABLED_METHOD, (String) null, (Bundle) null);
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "Font weight supporting status unknown; return as false.");
                applyFontWeightBundle = null;
                return false;
            }
        }
        Bundle bundle = applyFontWeightBundle;
        if (bundle == null || !bundle.getBoolean(IS_FONT_WEIGHT_ENABLED_METHOD, true)) {
            return false;
        }
        return true;
    }

    public static boolean isForceTwoPaneEnabled(Context context) {
        Bundle bundle = applyForceTwoPaneBundle;
        if (bundle == null || bundle.isEmpty()) {
            try {
                applyForceTwoPaneBundle = context.getContentResolver().call(getContentUri(), IS_FORCE_TWO_PANE_ENABLED_METHOD, (String) null, (Bundle) null);
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "isForceTwoPaneEnabled status is unknown; return as false.");
            }
        }
        Bundle bundle2 = applyForceTwoPaneBundle;
        if (bundle2 == null || bundle2.isEmpty()) {
            return false;
        }
        return applyForceTwoPaneBundle.getBoolean(IS_FORCE_TWO_PANE_ENABLED_METHOD, false);
    }

    public static boolean isSetupWizardDayNightEnabled(Context context) {
        if (suwDayNightEnabledBundle == null) {
            try {
                suwDayNightEnabledBundle = context.getContentResolver().call(getContentUri(), IS_SUW_DAY_NIGHT_ENABLED_METHOD, (String) null, (Bundle) null);
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "SetupWizard DayNight supporting status unknown; return as false.");
                suwDayNightEnabledBundle = null;
                return false;
            }
        }
        Bundle bundle = suwDayNightEnabledBundle;
        if (bundle == null || !bundle.getBoolean(IS_SUW_DAY_NIGHT_ENABLED_METHOD, false)) {
            return false;
        }
        return true;
    }

    public static boolean isValidInstance(Context context) {
        boolean z;
        boolean z2;
        Configuration configuration = context.getResources().getConfiguration();
        boolean z3 = true;
        if (instance == null) {
            if (!isEmbeddedActivityOnePaneEnabled(context) || !BuildCompatUtils.isAtLeastU()) {
                z3 = false;
            }
            savedConfigEmbeddedActivityMode = z3;
            savedConfigUiMode = configuration.uiMode & 48;
            savedOrientation = configuration.orientation;
            savedScreenWidth = configuration.screenWidthDp;
            savedScreenHeight = configuration.screenHeightDp;
            return false;
        }
        if (!isSetupWizardDayNightEnabled(context) || (configuration.uiMode & 48) == savedConfigUiMode) {
            z = false;
        } else {
            z = true;
        }
        if (!isEmbeddedActivityOnePaneEnabled(context) || !BuildCompatUtils.isAtLeastU()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (!z && z2 == savedConfigEmbeddedActivityMode && configuration.orientation == savedOrientation && configuration.screenWidthDp == savedScreenWidth && configuration.screenHeightDp == savedScreenHeight) {
            return true;
        }
        savedConfigUiMode = configuration.uiMode & 48;
        savedOrientation = configuration.orientation;
        savedScreenHeight = configuration.screenHeightDp;
        savedScreenWidth = configuration.screenWidthDp;
        resetInstance();
        return false;
    }

    public static Activity lookupActivityFromContext(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return lookupActivityFromContext(((ContextWrapper) context).getBaseContext());
        }
        throw new IllegalArgumentException("Cannot find instance of Activity in parent tree");
    }

    public static synchronized void resetInstance() {
        synchronized (PartnerConfigHelper.class) {
            instance = null;
            suwDayNightEnabledBundle = null;
            applyExtendedPartnerConfigBundle = null;
            applyMaterialYouConfigBundle = null;
            applyDynamicColorBundle = null;
            applyFullDynamicColorBundle = null;
            applyNeutralButtonStyleBundle = null;
            applyEmbeddedActivityOnePaneBundle = null;
            suwDefaultThemeBundle = null;
            applyTransitionBundle = null;
            applyForceTwoPaneBundle = null;
        }
    }

    public static boolean shouldApplyExtendedPartnerConfig(Context context) {
        if (applyExtendedPartnerConfigBundle == null) {
            try {
                applyExtendedPartnerConfigBundle = context.getContentResolver().call(getContentUri(), IS_EXTENDED_PARTNER_CONFIG_ENABLED_METHOD, (String) null, (Bundle) null);
            } catch (IllegalArgumentException | SecurityException unused) {
                Log.w("PartnerConfigHelper", "SetupWizard extended partner configs supporting status unknown; return as false.");
                applyExtendedPartnerConfigBundle = null;
                return false;
            }
        }
        Bundle bundle = applyExtendedPartnerConfigBundle;
        if (bundle == null || !bundle.getBoolean(IS_EXTENDED_PARTNER_CONFIG_ENABLED_METHOD, false)) {
            return false;
        }
        return true;
    }

    public final boolean getBoolean(Context context, PartnerConfig partnerConfig, boolean z) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.BOOL) {
            throw new IllegalArgumentException("Not a bool resource");
        } else if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return ((Boolean) this.partnerResourceCache.get(partnerConfig)).booleanValue();
        } else {
            try {
                ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
                z = resourceEntryFromKey.resources.getBoolean(resourceEntryFromKey.resourceId);
                this.partnerResourceCache.put(partnerConfig, Boolean.valueOf(z));
                return z;
            } catch (Resources.NotFoundException | NullPointerException unused) {
                return z;
            }
        }
    }

    public final int getColor(Context context, PartnerConfig partnerConfig) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.COLOR) {
            throw new IllegalArgumentException("Not a color resource");
        } else if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return ((Integer) this.partnerResourceCache.get(partnerConfig)).intValue();
        } else {
            try {
                ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
                Resources resources = resourceEntryFromKey.resources;
                int i = resourceEntryFromKey.resourceId;
                TypedValue typedValue = new TypedValue();
                resources.getValue(i, typedValue, true);
                if (typedValue.type == 1 && typedValue.data == 0) {
                    return 0;
                }
                int color = resources.getColor(i, (Resources.Theme) null);
                this.partnerResourceCache.put(partnerConfig, Integer.valueOf(color));
                return color;
            } catch (NullPointerException unused) {
                return 0;
            }
        }
    }

    public final float getDimension(Context context, PartnerConfig partnerConfig, float f) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.DIMENSION) {
            throw new IllegalArgumentException("Not a dimension resource");
        } else if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return ((TypedValue) this.partnerResourceCache.get(partnerConfig)).getDimension(context.getResources().getDisplayMetrics());
        } else {
            try {
                ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
                Resources resources = resourceEntryFromKey.resources;
                int i = resourceEntryFromKey.resourceId;
                float dimension = resources.getDimension(i);
                this.partnerResourceCache.put(partnerConfig, getTypedValueFromResource(i, resources));
                return ((TypedValue) this.partnerResourceCache.get(partnerConfig)).getDimension(context.getResources().getDisplayMetrics());
            } catch (Resources.NotFoundException | NullPointerException unused) {
                return f;
            }
        }
    }

    public final Drawable getDrawable(Context context, PartnerConfig partnerConfig) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.DRAWABLE) {
            throw new IllegalArgumentException("Not a drawable resource");
        } else if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return (Drawable) this.partnerResourceCache.get(partnerConfig);
        } else {
            try {
                ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
                Resources resources = resourceEntryFromKey.resources;
                int i = resourceEntryFromKey.resourceId;
                TypedValue typedValue = new TypedValue();
                resources.getValue(i, typedValue, true);
                if (typedValue.type == 1 && typedValue.data == 0) {
                    return null;
                }
                Drawable drawable = resources.getDrawable(i, (Resources.Theme) null);
                this.partnerResourceCache.put(partnerConfig, drawable);
                return drawable;
            } catch (Resources.NotFoundException | NullPointerException unused) {
                return null;
            }
        }
    }

    public final float getFraction(Context context, PartnerConfig partnerConfig) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.FRACTION) {
            throw new IllegalArgumentException("Not a fraction resource");
        } else if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return ((Float) this.partnerResourceCache.get(partnerConfig)).floatValue();
        } else {
            float f = 0.0f;
            try {
                ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
                f = resourceEntryFromKey.resources.getFraction(resourceEntryFromKey.resourceId, 1, 1);
                this.partnerResourceCache.put(partnerConfig, Float.valueOf(f));
                return f;
            } catch (Resources.NotFoundException | NullPointerException unused) {
                return f;
            }
        }
    }

    public final int getInteger(Context context, PartnerConfig partnerConfig, int i) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.INTEGER) {
            throw new IllegalArgumentException("Not a integer resource");
        } else if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return ((Integer) this.partnerResourceCache.get(partnerConfig)).intValue();
        } else {
            try {
                ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
                i = resourceEntryFromKey.resources.getInteger(resourceEntryFromKey.resourceId);
                this.partnerResourceCache.put(partnerConfig, Integer.valueOf(i));
                return i;
            } catch (Resources.NotFoundException | NullPointerException unused) {
                return i;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fc, code lost:
        r1 = r3.getResourceTypeName(r9.resourceId);
        r4 = r9.resourceName.concat(MATERIAL_YOU_RESOURCE_SUFFIX);
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0139  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.setupcompat.partnerconfig.ResourceEntry getResourceEntryFromKey(android.content.Context r8, java.lang.String r9) {
        /*
            r7 = this;
            android.os.Bundle r0 = r7.resultBundle
            android.os.Bundle r0 = r0.getBundle(r9)
            android.os.Bundle r1 = r7.resultBundle
            java.lang.String r2 = "fallbackConfig"
            android.os.Bundle r1 = r1.getBundle(r2)
            if (r1 == 0) goto L_0x0017
            android.os.Bundle r9 = r1.getBundle(r9)
            r0.putBundle(r2, r9)
        L_0x0017:
            com.google.android.setupcompat.partnerconfig.ResourceEntry r9 = com.google.android.setupcompat.partnerconfig.ResourceEntry.fromBundle(r8, r0)
            boolean r0 = com.google.android.setupcompat.util.BuildCompatUtils.isAtLeastU()
            java.lang.String r1 = "com.google.android.setupwizard"
            java.lang.String r2 = "PartnerConfigHelper"
            if (r0 == 0) goto L_0x0072
            boolean r7 = r7.isActivityEmbedded(r8)
            if (r7 == 0) goto L_0x0072
            java.lang.String r7 = "use embedded activity resource:"
            android.content.res.Resources r0 = r9.resources     // Catch:{  }
            java.lang.String r3 = r9.packageName
            int r4 = r9.resourceId     // Catch:{  }
            java.lang.String r4 = r0.getResourceTypeName(r4)     // Catch:{  }
            java.lang.String r5 = r9.resourceName     // Catch:{  }
            java.lang.String r6 = "_embedded_activity"
            java.lang.String r5 = r5.concat(r6)     // Catch:{  }
            int r6 = r0.getIdentifier(r5, r4, r3)     // Catch:{  }
            if (r6 == 0) goto L_0x005c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{  }
            r1.<init>(r7)     // Catch:{  }
            r1.append(r5)     // Catch:{  }
            java.lang.String r7 = r1.toString()     // Catch:{  }
            android.util.Log.i(r2, r7)     // Catch:{  }
            com.google.android.setupcompat.partnerconfig.ResourceEntry r7 = new com.google.android.setupcompat.partnerconfig.ResourceEntry     // Catch:{  }
            r7.<init>(r3, r5, r6, r0)     // Catch:{  }
        L_0x0059:
            r9 = r7
            goto L_0x012d
        L_0x005c:
            android.content.pm.PackageManager r7 = r8.getPackageManager()     // Catch:{  }
            android.content.res.Resources r7 = r7.getResourcesForApplication(r1)     // Catch:{  }
            int r0 = r7.getIdentifier(r5, r4, r1)     // Catch:{  }
            if (r0 == 0) goto L_0x012d
            com.google.android.setupcompat.partnerconfig.ResourceEntry r2 = new com.google.android.setupcompat.partnerconfig.ResourceEntry     // Catch:{  }
            r2.<init>(r1, r5, r0, r7)     // Catch:{  }
        L_0x006f:
            r9 = r2
            goto L_0x012d
        L_0x0072:
            boolean r7 = com.google.android.setupcompat.util.BuildCompatUtils.isAtLeastU()
            if (r7 == 0) goto L_0x00c5
            boolean r7 = isForceTwoPaneEnabled(r8)
            if (r7 == 0) goto L_0x00c5
            java.lang.String r7 = "two pane resource="
            if (r8 != 0) goto L_0x0084
            goto L_0x012d
        L_0x0084:
            android.content.res.Resources r0 = r9.resources     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            java.lang.String r3 = r9.packageName
            int r4 = r9.resourceId     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            java.lang.String r4 = r0.getResourceTypeName(r4)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            java.lang.String r5 = r9.resourceName     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            java.lang.String r6 = "_two_pane"
            java.lang.String r5 = r5.concat(r6)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            int r6 = r0.getIdentifier(r5, r4, r3)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            if (r6 == 0) goto L_0x00b1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            r1.<init>(r7)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            r1.append(r5)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            java.lang.String r7 = r1.toString()     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            android.util.Log.i(r2, r7)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            com.google.android.setupcompat.partnerconfig.ResourceEntry r7 = new com.google.android.setupcompat.partnerconfig.ResourceEntry     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            r7.<init>(r3, r5, r6, r0)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            goto L_0x0059
        L_0x00b1:
            android.content.pm.PackageManager r7 = r8.getPackageManager()     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            android.content.res.Resources r7 = r7.getResourcesForApplication(r1)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            int r0 = r7.getIdentifier(r5, r4, r1)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            if (r0 == 0) goto L_0x012d
            com.google.android.setupcompat.partnerconfig.ResourceEntry r2 = new com.google.android.setupcompat.partnerconfig.ResourceEntry     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            r2.<init>(r1, r5, r0, r7)     // Catch:{ NameNotFoundException | NotFoundException -> 0x012d }
            goto L_0x006f
        L_0x00c5:
            android.os.Bundle r7 = applyMaterialYouConfigBundle
            java.lang.String r0 = "IsMaterialYouStyleEnabled"
            if (r7 == 0) goto L_0x00d1
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x00e5
        L_0x00d1:
            r7 = 0
            android.content.ContentResolver r3 = r8.getContentResolver()     // Catch:{ IllegalArgumentException | SecurityException -> 0x0126 }
            android.net.Uri r4 = getContentUri()     // Catch:{ IllegalArgumentException | SecurityException -> 0x0126 }
            android.os.Bundle r3 = r3.call(r4, r0, r7, r7)     // Catch:{ IllegalArgumentException | SecurityException -> 0x0126 }
            applyMaterialYouConfigBundle = r3     // Catch:{ IllegalArgumentException | SecurityException -> 0x0126 }
            if (r3 == 0) goto L_0x00e5
            r3.isEmpty()     // Catch:{ IllegalArgumentException | SecurityException -> 0x0126 }
        L_0x00e5:
            android.os.Bundle r7 = applyMaterialYouConfigBundle
            if (r7 == 0) goto L_0x012d
            r3 = 0
            boolean r7 = r7.getBoolean(r0, r3)
            if (r7 == 0) goto L_0x012d
            java.lang.String r7 = "use material you resource:"
            java.lang.String r0 = r9.packageName     // Catch:{  }
            android.content.res.Resources r3 = r9.resources
            boolean r1 = java.util.Objects.equals(r0, r1)     // Catch:{  }
            if (r1 == 0) goto L_0x012d
            int r1 = r9.resourceId     // Catch:{  }
            java.lang.String r1 = r3.getResourceTypeName(r1)     // Catch:{  }
            java.lang.String r4 = r9.resourceName     // Catch:{  }
            java.lang.String r5 = "_material_you"
            java.lang.String r4 = r4.concat(r5)     // Catch:{  }
            int r1 = r3.getIdentifier(r4, r1, r0)     // Catch:{  }
            if (r1 == 0) goto L_0x012d
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{  }
            r5.<init>(r7)     // Catch:{  }
            r5.append(r4)     // Catch:{  }
            java.lang.String r7 = r5.toString()     // Catch:{  }
            android.util.Log.i(r2, r7)     // Catch:{  }
            com.google.android.setupcompat.partnerconfig.ResourceEntry r7 = new com.google.android.setupcompat.partnerconfig.ResourceEntry     // Catch:{  }
            r7.<init>(r0, r4, r1, r3)     // Catch:{  }
            goto L_0x0059
        L_0x0126:
            java.lang.String r0 = "SetupWizard Material You configs supporting status unknown; return as false."
            android.util.Log.w(r2, r0)
            applyMaterialYouConfigBundle = r7
        L_0x012d:
            android.content.res.Resources r7 = r9.resources
            android.content.res.Configuration r0 = r7.getConfiguration()
            boolean r8 = isSetupWizardDayNightEnabled(r8)
            if (r8 != 0) goto L_0x014e
            int r8 = r0.uiMode
            r1 = r8 & 48
            r2 = 32
            if (r1 != r2) goto L_0x014e
            r8 = r8 & -49
            r8 = r8 | 16
            r0.uiMode = r8
            android.util.DisplayMetrics r8 = r7.getDisplayMetrics()
            r7.updateConfiguration(r0, r8)
        L_0x014e:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.getResourceEntryFromKey(android.content.Context, java.lang.String):com.google.android.setupcompat.partnerconfig.ResourceEntry");
    }

    public final String getString(Context context, PartnerConfig partnerConfig) {
        if (partnerConfig.getResourceType() != PartnerConfig.ResourceType.STRING) {
            throw new IllegalArgumentException("Not a string resource");
        } else if (this.partnerResourceCache.containsKey(partnerConfig)) {
            return (String) this.partnerResourceCache.get(partnerConfig);
        } else {
            String str = null;
            try {
                ResourceEntry resourceEntryFromKey = getResourceEntryFromKey(context, partnerConfig.getResourceName());
                str = resourceEntryFromKey.resources.getString(resourceEntryFromKey.resourceId);
                this.partnerResourceCache.put(partnerConfig, str);
                return str;
            } catch (NullPointerException unused) {
                return str;
            }
        }
    }

    public boolean isActivityEmbedded(Context context) {
        EmbeddingCompat embeddingCompat;
        try {
            Activity lookupActivityFromContext = lookupActivityFromContext(context);
            if (!isEmbeddedActivityOnePaneEnabled(context) || (embeddingCompat = ((ExtensionEmbeddingBackend) ActivityEmbeddingController.getInstance(lookupActivityFromContext).backend).embeddingExtension) == null || !embeddingCompat.embeddingExtension.isActivityEmbedded(lookupActivityFromContext)) {
                return false;
            }
            return true;
        } catch (IllegalArgumentException unused) {
            Log.w("PartnerConfigHelper", "Not a Activity instance in parent tree");
            return false;
        }
    }

    public final boolean isAvailable() {
        Bundle bundle = this.resultBundle;
        if (bundle == null || bundle.isEmpty()) {
            return false;
        }
        return true;
    }

    public final boolean isPartnerConfigAvailable(PartnerConfig partnerConfig) {
        if (!isAvailable() || !this.resultBundle.containsKey(partnerConfig.getResourceName())) {
            return false;
        }
        return true;
    }
}
