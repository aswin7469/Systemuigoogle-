package com.google.android.systemui.columbus.legacy;

import android.content.DialogInterface;
import android.content.pm.LauncherActivityInfo;
import android.os.Messenger;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.google.android.systemui.columbus.ColumbusEvent;
import com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusTargetRequestService$IncomingMessageHandler$displayDialog$1 implements Runnable {
    public final /* synthetic */ LauncherActivityInfo $appInfo;
    public final /* synthetic */ Messenger $replyTo;
    public final /* synthetic */ int $requestCode;
    public final /* synthetic */ ColumbusTargetRequestService this$0;
    public final /* synthetic */ ColumbusTargetRequestService.IncomingMessageHandler this$1;

    public ColumbusTargetRequestService$IncomingMessageHandler$displayDialog$1(ColumbusTargetRequestService columbusTargetRequestService, LauncherActivityInfo launcherActivityInfo, ColumbusTargetRequestService.IncomingMessageHandler incomingMessageHandler, Messenger messenger, int i) {
        this.this$0 = columbusTargetRequestService;
        this.$appInfo = launcherActivityInfo;
        this.this$1 = incomingMessageHandler;
        this.$replyTo = messenger;
        this.$requestCode = i;
    }

    /* JADX WARNING: type inference failed for: r2v5, types: [android.app.AlertDialog, com.google.android.systemui.columbus.legacy.ColumbusTargetRequestDialog, com.android.systemui.statusbar.phone.SystemUIDialog] */
    public final void run() {
        int packageShownCount = this.this$0.columbusStructuredDataManager.getPackageShownCount(this.$appInfo.getComponentName().getPackageName());
        this.this$0.uiEventLogger.log(ColumbusEvent.COLUMBUS_RETARGET_DIALOG_SHOWN, 0, this.$appInfo.getComponentName().getPackageName());
        ? systemUIDialog = new SystemUIDialog(this.this$0.sysUIContext);
        systemUIDialog.show();
        LauncherActivityInfo launcherActivityInfo = this.$appInfo;
        ColumbusTargetRequestService columbusTargetRequestService = this.this$0;
        ColumbusTargetRequestService.IncomingMessageHandler incomingMessageHandler = this.this$1;
        Messenger messenger = this.$replyTo;
        int i = this.$requestCode;
        final int i2 = i;
        final int i3 = packageShownCount;
        final LauncherActivityInfo launcherActivityInfo2 = launcherActivityInfo;
        final Messenger messenger2 = messenger;
        final ColumbusTargetRequestService.IncomingMessageHandler incomingMessageHandler2 = incomingMessageHandler;
        int i4 = i;
        final ColumbusTargetRequestService columbusTargetRequestService2 = columbusTargetRequestService;
        AnonymousClass1 r3 = new DialogInterface.OnClickListener() {
            /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
                r3 = java.lang.System.currentTimeMillis();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
                r9.packageStats.put(com.google.android.systemui.columbus.legacy.ColumbusStructuredDataManager.makeJSONObject$default(r9, r2, 0, r4, 2));
                r9.storePackageStats();
             */
            /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
            /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x00cd */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void onClick(android.content.DialogInterface r9, int r10) {
                /*
                    r8 = this;
                    r9 = -2
                    r0 = 0
                    if (r10 == r9) goto L_0x00b6
                    r9 = -1
                    if (r10 == r9) goto L_0x0010
                    java.lang.String r8 = "Columbus/TargetRequest"
                    java.lang.String r9 = "Invalid dialog option: "
                    com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m(r9, r8, r10)
                    goto L_0x014b
                L_0x0010:
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r9 = r9
                    android.content.ContentResolver r9 = r9.getContentResolver()
                    java.lang.String r10 = "columbus_enabled"
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r1 = r9
                    com.android.systemui.settings.UserTracker r1 = r1.userTracker
                    com.android.systemui.settings.UserTrackerImpl r1 = (com.android.systemui.settings.UserTrackerImpl) r1
                    int r1 = r1.getUserId()
                    r2 = 1
                    android.provider.Settings.Secure.putIntForUser(r9, r10, r2, r1)
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r9 = r9
                    android.content.ContentResolver r9 = r9.getContentResolver()
                    java.lang.String r10 = "columbus_action"
                    java.lang.String r1 = "launch"
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r2 = r9
                    com.android.systemui.settings.UserTracker r2 = r2.userTracker
                    com.android.systemui.settings.UserTrackerImpl r2 = (com.android.systemui.settings.UserTrackerImpl) r2
                    int r2 = r2.getUserId()
                    android.provider.Settings.Secure.putStringForUser(r9, r10, r1, r2)
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r9 = r9
                    android.content.ContentResolver r9 = r9.getContentResolver()
                    java.lang.String r10 = "columbus_launch_app"
                    android.content.pm.LauncherActivityInfo r1 = r6
                    android.content.ComponentName r1 = r1.getComponentName()
                    java.lang.String r1 = r1.flattenToString()
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r2 = r9
                    com.android.systemui.settings.UserTracker r2 = r2.userTracker
                    com.android.systemui.settings.UserTrackerImpl r2 = (com.android.systemui.settings.UserTrackerImpl) r2
                    int r2 = r2.getUserId()
                    android.provider.Settings.Secure.putStringForUser(r9, r10, r1, r2)
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r9 = r9
                    android.content.ContentResolver r9 = r9.getContentResolver()
                    java.lang.String r10 = "columbus_launch_app_shortcut"
                    android.content.pm.LauncherActivityInfo r1 = r6
                    android.content.ComponentName r1 = r1.getComponentName()
                    java.lang.String r1 = r1.flattenToString()
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r2 = r9
                    com.android.systemui.settings.UserTracker r2 = r2.userTracker
                    com.android.systemui.settings.UserTrackerImpl r2 = (com.android.systemui.settings.UserTrackerImpl) r2
                    int r2 = r2.getUserId()
                    android.provider.Settings.Secure.putStringForUser(r9, r10, r1, r2)
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService$IncomingMessageHandler r9 = r8
                    android.os.Messenger r10 = r7
                    int r1 = r4
                    int r2 = com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService.IncomingMessageHandler.$r8$clinit
                    r9.getClass()
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService.IncomingMessageHandler.replyToMessenger(r10, r1, r0)
                    java.lang.String r9 = "Columbus/TargetRequest"
                    android.content.pm.LauncherActivityInfo r10 = r6
                    android.content.ComponentName r10 = r10.getComponentName()
                    java.lang.String r10 = r10.flattenToString()
                    java.lang.String r1 = "Target changed to "
                    androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0.m(r1, r10, r9)
                    int r9 = r5
                    if (r9 != 0) goto L_0x00a1
                    com.google.android.systemui.columbus.ColumbusEvent r9 = com.google.android.systemui.columbus.ColumbusEvent.COLUMBUS_RETARGET_APPROVED
                    goto L_0x00a3
                L_0x00a1:
                    com.google.android.systemui.columbus.ColumbusEvent r9 = com.google.android.systemui.columbus.ColumbusEvent.COLUMBUS_RETARGET_FOLLOW_ON_APPROVED
                L_0x00a3:
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r10 = r9
                    com.android.internal.logging.UiEventLogger r10 = r10.uiEventLogger
                    android.content.pm.LauncherActivityInfo r8 = r6
                    android.content.ComponentName r8 = r8.getComponentName()
                    java.lang.String r8 = r8.flattenToString()
                    r10.log(r9, r0, r8)
                    goto L_0x014b
                L_0x00b6:
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r9 = r9
                    com.google.android.systemui.columbus.legacy.ColumbusStructuredDataManager r9 = r9.columbusStructuredDataManager
                    android.content.pm.LauncherActivityInfo r10 = r6
                    android.content.ComponentName r10 = r10.getComponentName()
                    java.lang.String r2 = r10.getPackageName()
                    java.lang.Object r10 = r9.lock
                    monitor-enter(r10)
                    long r3 = android.os.SystemClock.currentNetworkTimeMillis()     // Catch:{ DateTimeException -> 0x00cd }
                L_0x00cb:
                    r4 = r3
                    goto L_0x00d2
                L_0x00cd:
                    long r3 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00fc }
                    goto L_0x00cb
                L_0x00d2:
                    org.json.JSONArray r1 = r9.packageStats     // Catch:{ all -> 0x00fc }
                    int r1 = r1.length()     // Catch:{ all -> 0x00fc }
                    r3 = r0
                L_0x00d9:
                    if (r3 >= r1) goto L_0x0101
                    org.json.JSONArray r6 = r9.packageStats     // Catch:{ all -> 0x00fc }
                    org.json.JSONObject r6 = r6.getJSONObject(r3)     // Catch:{ all -> 0x00fc }
                    java.lang.String r7 = "packageName"
                    java.lang.String r7 = r6.getString(r7)     // Catch:{ all -> 0x00fc }
                    boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r7)     // Catch:{ all -> 0x00fc }
                    if (r7 == 0) goto L_0x00fe
                    java.lang.String r1 = "lastDeny"
                    r6.put(r1, r4)     // Catch:{ all -> 0x00fc }
                    org.json.JSONArray r1 = r9.packageStats     // Catch:{ all -> 0x00fc }
                    r1.put(r3, r6)     // Catch:{ all -> 0x00fc }
                    r9.storePackageStats()     // Catch:{ all -> 0x00fc }
                    monitor-exit(r10)
                    goto L_0x0111
                L_0x00fc:
                    r8 = move-exception
                    goto L_0x014c
                L_0x00fe:
                    int r3 = r3 + 1
                    goto L_0x00d9
                L_0x0101:
                    org.json.JSONArray r7 = r9.packageStats     // Catch:{ all -> 0x00fc }
                    r3 = 0
                    r6 = 2
                    r1 = r9
                    org.json.JSONObject r1 = com.google.android.systemui.columbus.legacy.ColumbusStructuredDataManager.makeJSONObject$default(r1, r2, r3, r4, r6)     // Catch:{ all -> 0x00fc }
                    r7.put(r1)     // Catch:{ all -> 0x00fc }
                    r9.storePackageStats()     // Catch:{ all -> 0x00fc }
                    monitor-exit(r10)
                L_0x0111:
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService$IncomingMessageHandler r9 = r8
                    android.os.Messenger r10 = r7
                    int r1 = r4
                    int r2 = com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService.IncomingMessageHandler.$r8$clinit
                    r9.getClass()
                    r9 = 5
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService.IncomingMessageHandler.replyToMessenger(r10, r1, r9)
                    java.lang.String r9 = "Columbus/TargetRequest"
                    android.content.pm.LauncherActivityInfo r10 = r6
                    android.content.ComponentName r10 = r10.getComponentName()
                    java.lang.String r10 = r10.flattenToString()
                    java.lang.String r1 = "Target change denied by user: "
                    androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0.m(r1, r10, r9)
                    int r9 = r5
                    if (r9 != 0) goto L_0x0138
                    com.google.android.systemui.columbus.ColumbusEvent r9 = com.google.android.systemui.columbus.ColumbusEvent.COLUMBUS_RETARGET_NOT_APPROVED
                    goto L_0x013a
                L_0x0138:
                    com.google.android.systemui.columbus.ColumbusEvent r9 = com.google.android.systemui.columbus.ColumbusEvent.COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED
                L_0x013a:
                    com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService r10 = r9
                    com.android.internal.logging.UiEventLogger r10 = r10.uiEventLogger
                    android.content.pm.LauncherActivityInfo r8 = r6
                    android.content.ComponentName r8 = r8.getComponentName()
                    java.lang.String r8 = r8.flattenToString()
                    r10.log(r9, r0, r8)
                L_0x014b:
                    return
                L_0x014c:
                    monitor-exit(r10)
                    throw r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService$IncomingMessageHandler$displayDialog$1.AnonymousClass1.onClick(android.content.DialogInterface, int):void");
            }
        };
        final int i5 = i4;
        AnonymousClass2 r1 = r3;
        final ColumbusTargetRequestService columbusTargetRequestService3 = columbusTargetRequestService;
        AnonymousClass2 r32 = new DialogInterface.OnCancelListener() {
            public final void onCancel(DialogInterface dialogInterface) {
                ColumbusEvent columbusEvent;
                ColumbusTargetRequestService.IncomingMessageHandler incomingMessageHandler = incomingMessageHandler2;
                Messenger messenger = messenger2;
                int i = i5;
                int i2 = ColumbusTargetRequestService.IncomingMessageHandler.$r8$clinit;
                incomingMessageHandler.getClass();
                ColumbusTargetRequestService.IncomingMessageHandler.replyToMessenger(messenger, i, 6);
                FragmentManagerViewModel$$ExternalSyntheticOutline0.m("Target change dismissed by user: ", launcherActivityInfo2.getComponentName().flattenToString(), "Columbus/TargetRequest");
                if (i3 == 0) {
                    columbusEvent = ColumbusEvent.COLUMBUS_RETARGET_NOT_APPROVED;
                } else {
                    columbusEvent = ColumbusEvent.COLUMBUS_RETARGET_FOLLOW_ON_NOT_APPROVED;
                }
                columbusTargetRequestService3.uiEventLogger.log(columbusEvent, 0, launcherActivityInfo2.getComponentName().flattenToString());
            }
        };
        systemUIDialog.setTitle(systemUIDialog.getContext().getString(2131952241, new Object[]{launcherActivityInfo.getLabel()}));
        systemUIDialog.setMessage(systemUIDialog.getContext().getString(2131952240, new Object[]{launcherActivityInfo.getLabel()}));
        systemUIDialog.setPositiveButton(2131952238, r3);
        systemUIDialog.setNegativeButton(2131952239, r3);
        systemUIDialog.setOnCancelListener(r1);
        systemUIDialog.setCanceledOnTouchOutside(true);
        ColumbusTargetRequestService.IncomingMessageHandler incomingMessageHandler3 = this.this$1;
        String packageName = this.$appInfo.getComponentName().getPackageName();
        ColumbusStructuredDataManager columbusStructuredDataManager = ColumbusTargetRequestService.this.columbusStructuredDataManager;
        synchronized (columbusStructuredDataManager.lock) {
            int length = columbusStructuredDataManager.packageStats.length();
            for (int i6 = 0; i6 < length; i6++) {
                JSONObject jSONObject = columbusStructuredDataManager.packageStats.getJSONObject(i6);
                if (Intrinsics.areEqual(packageName, jSONObject.getString("packageName"))) {
                    jSONObject.put("shownCount", jSONObject.getInt("shownCount") + 1);
                    columbusStructuredDataManager.packageStats.put(i6, jSONObject);
                    columbusStructuredDataManager.storePackageStats();
                    return;
                }
            }
            columbusStructuredDataManager.packageStats.put(ColumbusStructuredDataManager.makeJSONObject$default(columbusStructuredDataManager, packageName, 1, 0, 4));
            columbusStructuredDataManager.storePackageStats();
        }
    }
}
