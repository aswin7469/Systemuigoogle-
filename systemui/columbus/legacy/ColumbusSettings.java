package com.google.android.systemui.columbus.legacy;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusContentObserver;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusSettings {
    public static final Uri COLUMBUS_ACTION_URI;
    public static final Uri COLUMBUS_AP_SENSOR_URI;
    public static final Uri COLUMBUS_ENABLED_URI;
    public static final Uri COLUMBUS_LAUNCH_APP_SHORTCUT_URI;
    public static final Uri COLUMBUS_LAUNCH_APP_URI;
    public static final Uri COLUMBUS_LOW_SENSITIVITY_URI;
    public static final Uri COLUMBUS_SILENCE_ALERTS_URI;
    public static final Set MONITORED_URIS;
    public final String backupPackage;
    public final Function1 callback = new ColumbusSettings$callback$1(this);
    public final ContentResolver contentResolver;
    public final Set listeners = new LinkedHashSet();
    public final UserTracker userTracker;

    static {
        Uri uriFor = Settings.Secure.getUriFor("columbus_enabled");
        COLUMBUS_ENABLED_URI = uriFor;
        Uri uriFor2 = Settings.Secure.getUriFor("columbus_ap_sensor");
        COLUMBUS_AP_SENSOR_URI = uriFor2;
        Uri uriFor3 = Settings.Secure.getUriFor("columbus_action");
        COLUMBUS_ACTION_URI = uriFor3;
        Uri uriFor4 = Settings.Secure.getUriFor("columbus_launch_app");
        COLUMBUS_LAUNCH_APP_URI = uriFor4;
        Uri uriFor5 = Settings.Secure.getUriFor("columbus_launch_app_shortcut");
        COLUMBUS_LAUNCH_APP_SHORTCUT_URI = uriFor5;
        Uri uriFor6 = Settings.Secure.getUriFor("columbus_low_sensitivity");
        COLUMBUS_LOW_SENSITIVITY_URI = uriFor6;
        Uri uriFor7 = Settings.Secure.getUriFor("columbus_silence_alerts");
        COLUMBUS_SILENCE_ALERTS_URI = uriFor7;
        MONITORED_URIS = SetsKt.setOf(uriFor, uriFor2, uriFor3, uriFor4, uriFor5, uriFor6, uriFor7);
    }

    public ColumbusSettings(Context context, UserTracker userTracker2, ColumbusContentObserver.Factory factory) {
        this.userTracker = userTracker2;
        this.backupPackage = context.getBasePackageName();
        this.contentResolver = context.getContentResolver();
        Iterable<Uri> iterable = MONITORED_URIS;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable));
        for (Uri uri : iterable) {
            Intrinsics.checkNotNull(uri);
            Function1 function1 = this.callback;
            factory.getClass();
            arrayList.add(new ColumbusContentObserver(factory.contentResolver, uri, function1, factory.userTracker, factory.executor, factory.handler));
        }
        for (ColumbusContentObserver columbusContentObserver : CollectionsKt.toSet(arrayList)) {
            ((UserTrackerImpl) columbusContentObserver.userTracker).addCallback(columbusContentObserver.userTrackerCallback, columbusContentObserver.executor);
            columbusContentObserver.contentResolver.contentResolver.unregisterContentObserver(columbusContentObserver);
            ContentResolverWrapper contentResolverWrapper = columbusContentObserver.contentResolver;
            contentResolverWrapper.contentResolver.registerContentObserver(columbusContentObserver.settingsUri, false, columbusContentObserver, ((UserTrackerImpl) columbusContentObserver.userTracker).getUserId());
        }
    }

    public final boolean isColumbusEnabled() {
        if (Settings.Secure.getIntForUser(this.contentResolver, "columbus_enabled", 0, ((UserTrackerImpl) this.userTracker).getUserId()) != 0) {
            return true;
        }
        return false;
    }

    public final void registerColumbusSettingsChangeListener(ColumbusSettingsChangeListener columbusSettingsChangeListener) {
        this.listeners.add(columbusSettingsChangeListener);
    }

    public final String selectedAction() {
        String stringForUser = Settings.Secure.getStringForUser(this.contentResolver, "columbus_action", ((UserTrackerImpl) this.userTracker).getUserId());
        if (stringForUser == null) {
            return "";
        }
        return stringForUser;
    }

    public final String selectedApp() {
        String stringForUser = Settings.Secure.getStringForUser(this.contentResolver, "columbus_launch_app", ((UserTrackerImpl) this.userTracker).getUserId());
        if (stringForUser == null) {
            return "";
        }
        return stringForUser;
    }

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public interface ColumbusSettingsChangeListener {
        void onAlertSilenceEnabledChange(boolean z) {
        }

        void onColumbusEnabledChange(boolean z) {
        }

        void onLowSensitivityChange(boolean z) {
        }

        void onSelectedActionChange(String str) {
        }

        void onSelectedAppChange(String str) {
        }

        void onSelectedAppShortcutChange(String str) {
        }
    }
}
