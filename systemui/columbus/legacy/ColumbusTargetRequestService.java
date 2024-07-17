package com.google.android.systemui.columbus.legacy;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Trace;
import android.util.Log;
import androidx.compose.foundation.text.HeightInLinesModifierKt$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.ColumbusContext;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusTargetRequestService extends Service {
    public static final long PACKAGE_DENY_COOLDOWN_MS = TimeUnit.DAYS.toMillis(5);
    public final Set allowCertList;
    public final Set allowPackageList;
    public final ColumbusContext columbusContext;
    public final ColumbusSettings columbusSettings;
    public final ColumbusStructuredDataManager columbusStructuredDataManager;
    public LauncherApps launcherApps;
    public final Handler mainHandler;
    public final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
    public final Messenger messenger;
    public final Context sysUIContext;
    public final UiEventLogger uiEventLogger;
    public final UserTracker userTracker;

    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class IncomingMessageHandler extends Handler {
        public static final /* synthetic */ int $r8$clinit = 0;

        public IncomingMessageHandler(Looper looper) {
            super(looper);
        }

        public static void replyToMessenger(Messenger messenger, int i, int i2) {
            Object obj;
            if (messenger != null) {
                Message what = Message.obtain().setWhat(i2);
                what.arg1 = i;
                try {
                    messenger.send(what);
                    obj = Unit.INSTANCE;
                } catch (Throwable th) {
                    obj = new Result.Failure(th);
                }
                Throwable r2 = Result.m1756exceptionOrNullimpl(obj);
                if (r2 != null) {
                    Log.e("Columbus/TargetRequest", HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("Could not send response ", i2, i, " for request "), r2);
                }
            }
        }

        public final LauncherActivityInfo getAppInfoForPackage(String str) {
            List<LauncherActivityInfo> activityList;
            boolean isEnabled;
            PendingIntent pendingIntent;
            boolean z;
            String concat = "getAppInfoForPackage pkg=".concat(str);
            ColumbusTargetRequestService columbusTargetRequestService = ColumbusTargetRequestService.this;
            boolean isEnabled2 = Trace.isEnabled();
            if (isEnabled2) {
                TraceUtilsKt.beginSlice(concat);
            }
            try {
                LauncherApps launcherApps = columbusTargetRequestService.launcherApps;
                T t = null;
                if (launcherApps != null && (activityList = launcherApps.getActivityList(str, ((UserTrackerImpl) columbusTargetRequestService.userTracker).getUserHandle())) != null) {
                    Iterator<T> it = activityList.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            T next = it.next();
                            LauncherActivityInfo launcherActivityInfo = (LauncherActivityInfo) next;
                            try {
                                String str2 = "getMainActivityLaunchIntent component=" + launcherActivityInfo.getComponentName();
                                isEnabled = Trace.isEnabled();
                                if (isEnabled) {
                                    TraceUtilsKt.beginSlice(str2);
                                }
                                LauncherApps launcherApps2 = columbusTargetRequestService.launcherApps;
                                if (launcherApps2 != null) {
                                    pendingIntent = launcherApps2.getMainActivityLaunchIntent(launcherActivityInfo.getComponentName(), (Bundle) null, ((UserTrackerImpl) columbusTargetRequestService.userTracker).getUserHandle());
                                } else {
                                    pendingIntent = null;
                                }
                                if (pendingIntent != null) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (isEnabled) {
                                    TraceUtilsKt.endSlice();
                                }
                                if (z) {
                                    t = next;
                                    break;
                                }
                            } catch (RuntimeException unused) {
                            } catch (Throwable th) {
                                if (isEnabled) {
                                    TraceUtilsKt.endSlice();
                                }
                                throw th;
                            }
                        }
                    }
                    t = (LauncherActivityInfo) t;
                }
                return t;
            } finally {
                if (isEnabled2) {
                    TraceUtilsKt.endSlice();
                }
            }
        }

        public final void handleMessage(Message message) {
            String str;
            String[] packagesForUid = ColumbusTargetRequestService.this.getPackageManager().getPackagesForUid(message.sendingUid);
            if (packagesForUid != null) {
                str = packagesForUid[0];
            } else {
                str = null;
            }
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    RecordingInputConnection$$ExternalSyntheticOutline0.m("Invalid request type: ", "Columbus/TargetRequest", i);
                } else if (str == null || packageIsNotAllowed(str) || ColumbusTargetRequestService.this.columbusStructuredDataManager.getPackageShownCount(str) >= 3 || getAppInfoForPackage(str) == null) {
                    replyToMessenger(message.replyTo, message.what, 2);
                } else if (packageIsTarget(str)) {
                    replyToMessenger(message.replyTo, message.what, 0);
                } else if (packageNeedsToCoolDown(str)) {
                    replyToMessenger(message.replyTo, message.what, 3);
                } else {
                    replyToMessenger(message.replyTo, message.what, 1);
                }
            } else if (str == null || packageIsNotAllowed(str)) {
                replyToMessenger(message.replyTo, message.what, 1);
                Log.d("Columbus/TargetRequest", "Unsupported caller: " + str);
            } else if (packageIsTarget(str)) {
                replyToMessenger(message.replyTo, message.what, 0);
                Log.d("Columbus/TargetRequest", "Caller already target: ".concat(str));
            } else if (packageNeedsToCoolDown(str)) {
                replyToMessenger(message.replyTo, message.what, 2);
                Log.d("Columbus/TargetRequest", "Caller throttled: ".concat(str));
            } else if (ColumbusTargetRequestService.this.columbusStructuredDataManager.getPackageShownCount(str) >= 3) {
                replyToMessenger(message.replyTo, message.what, 3);
                Log.d("Columbus/TargetRequest", "Caller already shown max times: ".concat(str));
            } else {
                LauncherActivityInfo appInfoForPackage = getAppInfoForPackage(str);
                if (appInfoForPackage == null) {
                    replyToMessenger(message.replyTo, message.what, 4);
                    Log.d("Columbus/TargetRequest", "Caller not launchable: ".concat(str));
                    return;
                }
                Messenger messenger = message.replyTo;
                int i2 = message.what;
                ColumbusTargetRequestService columbusTargetRequestService = ColumbusTargetRequestService.this;
                columbusTargetRequestService.mainHandler.post(new ColumbusTargetRequestService$IncomingMessageHandler$displayDialog$1(columbusTargetRequestService, appInfoForPackage, this, messenger, i2));
            }
        }

        public final boolean packageIsNotAllowed(String str) {
            Signature[] signatureArr;
            if (!ColumbusTargetRequestService.this.allowPackageList.contains(str)) {
                return true;
            }
            SigningInfo signingInfo = ColumbusTargetRequestService.this.sysUIContext.getPackageManager().getPackageInfo(str, 134217728).signingInfo;
            if (signingInfo != null) {
                if (signingInfo.hasMultipleSigners()) {
                    signatureArr = signingInfo.getApkContentsSigners();
                } else {
                    signatureArr = signingInfo.getSigningCertificateHistory();
                }
                Intrinsics.checkNotNull(signatureArr);
                ColumbusTargetRequestService columbusTargetRequestService = ColumbusTargetRequestService.this;
                ArrayList arrayList = new ArrayList(signatureArr.length);
                boolean z = false;
                for (Signature byteArray : signatureArr) {
                    arrayList.add(new String(columbusTargetRequestService.messageDigest.digest(byteArray.toByteArray()), Charsets.UTF_16));
                }
                ColumbusTargetRequestService columbusTargetRequestService2 = ColumbusTargetRequestService.this;
                if (!arrayList.isEmpty()) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (columbusTargetRequestService2.allowCertList.contains((String) it.next())) {
                            z = true;
                            break;
                        }
                    }
                }
                return !z;
            }
            throw new IllegalStateException("Required value was null.".toString());
        }

        public final boolean packageIsTarget(String str) {
            String str2;
            boolean isColumbusEnabled = ColumbusTargetRequestService.this.columbusSettings.isColumbusEnabled();
            boolean areEqual = Intrinsics.areEqual("launch", ColumbusTargetRequestService.this.columbusSettings.selectedAction());
            ComponentName unflattenFromString = ComponentName.unflattenFromString(ColumbusTargetRequestService.this.columbusSettings.selectedApp());
            if (unflattenFromString != null) {
                str2 = unflattenFromString.getPackageName();
            } else {
                str2 = null;
            }
            boolean areEqual2 = Intrinsics.areEqual(str, str2);
            if (!isColumbusEnabled || !areEqual || !areEqual2) {
                return false;
            }
            return true;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:?, code lost:
            r1 = java.lang.System.currentTimeMillis();
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean packageNeedsToCoolDown(java.lang.String r4) {
            /*
                r3 = this;
                com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r3 = com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService.this
                com.google.android.systemui.columbus.legacy.ColumbusStructuredDataManager r3 = r3.columbusStructuredDataManager
                java.lang.Object r0 = r3.lock
                monitor-enter(r0)
                long r1 = android.os.SystemClock.currentNetworkTimeMillis()     // Catch:{ DateTimeException -> 0x000c }
                goto L_0x0010
            L_0x000c:
                long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0020 }
            L_0x0010:
                long r3 = r3.getLastDenyTimestamp(r4)     // Catch:{ all -> 0x0020 }
                long r1 = r1 - r3
                monitor-exit(r0)
                long r3 = com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService.PACKAGE_DENY_COOLDOWN_MS
                int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
                if (r3 >= 0) goto L_0x001e
                r3 = 1
                goto L_0x001f
            L_0x001e:
                r3 = 0
            L_0x001f:
                return r3
            L_0x0020:
                r3 = move-exception
                monitor-exit(r0)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService.IncomingMessageHandler.packageNeedsToCoolDown(java.lang.String):boolean");
        }
    }

    public ColumbusTargetRequestService(Context context, UserTracker userTracker2, ColumbusSettings columbusSettings2, ColumbusStructuredDataManager columbusStructuredDataManager2, UiEventLogger uiEventLogger2, Handler handler, Looper looper) {
        this.sysUIContext = context;
        this.userTracker = userTracker2;
        this.columbusSettings = columbusSettings2;
        this.columbusStructuredDataManager = columbusStructuredDataManager2;
        this.uiEventLogger = uiEventLogger2;
        this.mainHandler = handler;
        this.columbusContext = new ColumbusContext(context);
        this.messenger = new Messenger(new IncomingMessageHandler(looper));
        String[] stringArray = context.getResources().getStringArray(2130903088);
        this.allowPackageList = SetsKt.setOf(Arrays.copyOf(stringArray, stringArray.length));
        String[] stringArray2 = context.getResources().getStringArray(2130903087);
        this.allowCertList = SetsKt.setOf(Arrays.copyOf(stringArray2, stringArray2.length));
    }

    public final IBinder onBind(Intent intent) {
        if (this.columbusContext.packageManager.hasSystemFeature("com.google.android.feature.QUICK_TAP")) {
            return this.messenger.getBinder();
        }
        return null;
    }

    public final void onCreate() {
        LauncherApps launcherApps2;
        super.onCreate();
        Object systemService = getSystemService("launcherapps");
        if (systemService instanceof LauncherApps) {
            launcherApps2 = (LauncherApps) systemService;
        } else {
            launcherApps2 = null;
        }
        this.launcherApps = launcherApps2;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }
}
