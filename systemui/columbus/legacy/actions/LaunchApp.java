package com.google.android.systemui.columbus.legacy.actions;

import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.IActivityManager;
import android.app.IApplicationThread;
import android.app.ProfilerInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserManager;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.util.Log;
import com.android.app.tracing.TraceUtils;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import com.google.android.systemui.columbus.legacy.gates.KeyguardVisibility;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__IteratorsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LaunchApp extends UserAction {
    public final ActivityStarter activityStarter;
    public final Set allowCertList;
    public final Set allowPackageList;
    public final Map availableApps;
    public final Map availableShortcuts;
    public final Handler bgHandler;
    public final LaunchApp$broadcastReceiver$1 broadcastReceiver;
    public ComponentName currentApp;
    public String currentShortcut;
    public final Set denyPackageList;
    public final LaunchApp$deviceConfigPropertiesChangedListener$1 deviceConfigPropertiesChangedListener;
    public final LaunchApp$gateListener$1 gateListener;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final LaunchApp$keyguardUpdateMonitorCallback$1 keyguardUpdateMonitorCallback;
    public final KeyguardVisibility keyguardVisibility;
    public final LauncherApps launcherApps;
    public final Handler mainHandler;
    public final MessageDigest messageDigest;
    public final LaunchApp$onDismissKeyguardAction$1 onDismissKeyguardAction;
    public final LaunchApp$settingsListener$1 settingsListener = new LaunchApp$settingsListener$1(this);
    public final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public final String tag = "Columbus/LaunchApp";
    public final UiEventLogger uiEventLogger;
    public final UserManager userManager;
    public final UserTracker userTracker;

    public LaunchApp(Context context, LauncherApps launcherApps2, ActivityStarter activityStarter2, StatusBarKeyguardViewManager statusBarKeyguardViewManager2, IActivityManager iActivityManager, UserManager userManager2, ColumbusSettings columbusSettings, KeyguardVisibility keyguardVisibility2, KeyguardUpdateMonitor keyguardUpdateMonitor2, Handler handler, Handler handler2, Executor executor, UiEventLogger uiEventLogger2, UserTracker userTracker2) {
        super(context, (Set) null);
        this.launcherApps = launcherApps2;
        this.activityStarter = activityStarter2;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager2;
        this.userManager = userManager2;
        this.keyguardVisibility = keyguardVisibility2;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor2;
        this.mainHandler = handler;
        this.bgHandler = handler2;
        this.uiEventLogger = uiEventLogger2;
        this.userTracker = userTracker2;
        LaunchApp$userSwitchCallback$1 launchApp$userSwitchCallback$1 = new LaunchApp$userSwitchCallback$1(this);
        this.broadcastReceiver = new LaunchApp$broadcastReceiver$1(this);
        this.gateListener = new LaunchApp$gateListener$1(this);
        this.keyguardUpdateMonitorCallback = new LaunchApp$keyguardUpdateMonitorCallback$1(this, context);
        LaunchApp$deviceConfigPropertiesChangedListener$1 launchApp$deviceConfigPropertiesChangedListener$1 = new LaunchApp$deviceConfigPropertiesChangedListener$1(this);
        this.onDismissKeyguardAction = new LaunchApp$onDismissKeyguardAction$1(this);
        this.messageDigest = MessageDigest.getInstance("SHA-256");
        String[] stringArray = context.getResources().getStringArray(2130903088);
        this.allowPackageList = SetsKt__SetsKt.setOf(Arrays.copyOf(stringArray, stringArray.length));
        String[] stringArray2 = context.getResources().getStringArray(2130903087);
        this.allowCertList = SetsKt__SetsKt.setOf(Arrays.copyOf(stringArray2, stringArray2.length));
        this.denyPackageList = new LinkedHashSet();
        this.availableApps = new LinkedHashMap();
        this.availableShortcuts = new LinkedHashMap();
        String str = "";
        this.currentShortcut = str;
        DeviceConfig.addOnPropertiesChangedListener("systemui", executor, launchApp$deviceConfigPropertiesChangedListener$1);
        updateDenyList(DeviceConfig.getString("systemui", "systemui_google_columbus_secure_deny_list", (String) null));
        try {
            iActivityManager.registerUserSwitchObserver(launchApp$userSwitchCallback$1, "Columbus/LaunchApp");
        } catch (RemoteException e) {
            Log.e("Columbus/LaunchApp", "Failed to register user switch observer", e);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addDataScheme("package");
        context.registerReceiver(this.broadcastReceiver, intentFilter);
        context.registerReceiver(this.broadcastReceiver, new IntentFilter("android.intent.action.BOOT_COMPLETED"));
        updateAvailableAppsAndShortcutsAsync();
        columbusSettings.registerColumbusSettingsChangeListener(this.settingsListener);
        this.currentApp = ComponentName.unflattenFromString(columbusSettings.selectedApp());
        String stringForUser = Settings.Secure.getStringForUser(columbusSettings.contentResolver, "columbus_launch_app_shortcut", ((UserTrackerImpl) columbusSettings.userTracker).getUserId());
        this.currentShortcut = stringForUser != null ? stringForUser : str;
        this.keyguardVisibility.registerListener(this.gateListener);
        updateAvailable$4();
    }

    public static final void access$addShortcutsForApp(LaunchApp launchApp, LauncherActivityInfo launcherActivityInfo, List list) {
        launchApp.getClass();
        if (list != null) {
            ArrayList<ShortcutInfo> arrayList = new ArrayList<>();
            for (Object next : list) {
                if (Intrinsics.areEqual(((ShortcutInfo) next).getPackage(), launcherActivityInfo.getComponentName().getPackageName())) {
                    arrayList.add(next);
                }
            }
            for (ShortcutInfo shortcutInfo : arrayList) {
                Map map = launchApp.availableShortcuts;
                map.putIfAbsent(shortcutInfo.getPackage(), new LinkedHashMap());
                Object obj = map.get(shortcutInfo.getPackage());
                Intrinsics.checkNotNull(obj);
                ((Map) obj).put(shortcutInfo.getId(), shortcutInfo);
            }
        }
    }

    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }

    public final void onTrigger(GestureSensor.DetectionProperties detectionProperties) {
        Intent intent;
        String str;
        String str2;
        Signature[] signatureArr;
        String str3;
        KeyguardVisibility keyguardVisibility2 = this.keyguardVisibility;
        boolean isBlocking = keyguardVisibility2.isBlocking();
        Lazy lazy = keyguardVisibility2.keyguardStateController;
        Context context = this.context;
        if (isBlocking && (!((KeyguardStateController) lazy.get()).isUnlocked())) {
            ComponentName componentName = this.currentApp;
            if (componentName != null) {
                str2 = componentName.getPackageName();
            } else {
                str2 = null;
            }
            if (!CollectionsKt___CollectionsKt.contains(this.denyPackageList, str2) && str2 != null && this.allowPackageList.contains(str2)) {
                SigningInfo signingInfo = context.getPackageManager().getPackageInfo(str2, 134217728).signingInfo;
                if (signingInfo != null) {
                    if (signingInfo.hasMultipleSigners()) {
                        signatureArr = signingInfo.getApkContentsSigners();
                    } else {
                        signatureArr = signingInfo.getSigningCertificateHistory();
                    }
                    Intrinsics.checkNotNull(signatureArr);
                    ArrayList arrayList = new ArrayList(signatureArr.length);
                    for (Signature byteArray : signatureArr) {
                        arrayList.add(new String(this.messageDigest.digest(byteArray.toByteArray()), Charsets.UTF_16));
                    }
                    if (!arrayList.isEmpty()) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            if (this.allowCertList.contains((String) it.next())) {
                                Intent intent2 = new Intent("android.media.action.STILL_IMAGE_CAMERA_SECURE");
                                ComponentName componentName2 = this.currentApp;
                                if (componentName2 != null) {
                                    str3 = componentName2.getPackageName();
                                } else {
                                    str3 = null;
                                }
                                Intent putExtra = intent2.setPackage(str3).putExtra("systemui_google_quick_tap_is_source", true);
                                if (putExtra.resolveActivity(context.getPackageManager()) == null) {
                                    putExtra = null;
                                }
                                intent = putExtra;
                            }
                        }
                    }
                } else {
                    throw new IllegalStateException("Required value was null.".toString());
                }
            }
        }
        intent = null;
        if (intent != null) {
            ActivityOptions makeBasic = ActivityOptions.makeBasic();
            makeBasic.setDisallowEnterPictureInPictureWhileLaunching(true);
            makeBasic.setRotationAnimationHint(3);
            try {
                ActivityTaskManager.getService().startActivityAsUser((IApplicationThread) null, context.getBasePackageName(), context.getAttributionTag(), intent, intent.resolveTypeIfNeeded(context.getContentResolver()), (IBinder) null, (String) null, 0, 268435456, (ProfilerInfo) null, makeBasic.toBundle(), ((UserTrackerImpl) this.userTracker).getUserId());
                UiEventLogger uiEventLogger2 = this.uiEventLogger;
                ColumbusEvent columbusEvent = ColumbusEvent.COLUMBUS_INVOKED_LAUNCH_APP_SECURE;
                ComponentName componentName3 = this.currentApp;
                if (componentName3 != null) {
                    str = componentName3.getPackageName();
                } else {
                    str = null;
                }
                uiEventLogger2.log(columbusEvent, 0, str);
                return;
            } catch (RemoteException e) {
                Log.e("Columbus/LaunchApp", "Unable to start secure activity for", e);
            }
        }
        if (((KeyguardStateControllerImpl) ((KeyguardStateController) lazy.get())).mShowing && (!((KeyguardStateController) lazy.get()).isUnlocked())) {
            this.keyguardUpdateMonitor.registerCallback(this.keyguardUpdateMonitorCallback);
        }
        this.activityStarter.dismissKeyguardThenExecute(this.onDismissKeyguardAction, (Runnable) null, true);
    }

    public final String toString() {
        if (usingShortcut()) {
            ComponentName componentName = this.currentApp;
            String str = this.currentShortcut;
            return "Launch " + componentName + " shortcut " + str;
        }
        ComponentName componentName2 = this.currentApp;
        return "Launch " + componentName2;
    }

    public final void updateAvailable$4() {
        String str;
        if (usingShortcut()) {
            ComponentName componentName = this.currentApp;
            if (componentName != null) {
                str = componentName.getPackageName();
            } else {
                str = null;
            }
            Map map = (Map) this.availableShortcuts.get(str);
            boolean z = false;
            if (map != null && map.containsKey(this.currentShortcut)) {
                z = true;
            }
            setAvailable(z);
            return;
        }
        setAvailable(this.availableApps.containsKey(this.currentApp));
    }

    public final void updateAvailableAppsAndShortcutsAsync() {
        int i = TraceUtils.$r8$clinit;
        this.bgHandler.post(new LaunchApp$updateAvailableAppsAndShortcutsAsync$$inlined$traceRunnable$1(this));
    }

    public final void updateDenyList(String str) {
        Set set = this.denyPackageList;
        set.clear();
        if (str != null) {
            List<String> split$default = StringsKt__StringsKt.split$default(str, new String[]{","}, 0, 6);
            ArrayList arrayList = new ArrayList(CollectionsKt__IteratorsJVMKt.collectionSizeOrDefault(split$default));
            for (String trim : split$default) {
                arrayList.add(StringsKt__StringsKt.trim(trim).toString());
            }
            set.addAll(arrayList);
        }
    }

    public final boolean usingShortcut() {
        String str;
        if (this.currentShortcut.length() > 0) {
            String str2 = this.currentShortcut;
            ComponentName componentName = this.currentApp;
            if (componentName != null) {
                str = componentName.flattenToString();
            } else {
                str = null;
            }
            if (!Intrinsics.areEqual(str2, str)) {
                return true;
            }
        }
        return false;
    }
}
