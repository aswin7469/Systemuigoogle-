package com.google.android.systemui.volume.panel.component.clearcalling.data.repository;

import com.google.android.settingslib.dcservice.DcServiceClient;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ClearCallingRepository {
    public final DcServiceClient dcServiceClient;
    public final StateFlowImpl mutableClearCallingStateUpdate;

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        kotlin.jdk7.AutoCloseableKt.closeFinally(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ClearCallingRepository(com.google.android.settingslib.dcservice.DcServiceClientImpl r5) {
        /*
            r4 = this;
            r4.<init>()
            r4.dcServiceClient = r5
            java.lang.String r0 = "Query Clear Calling state through DC-Service"
            java.lang.String r1 = "DcServiceClientImpl"
            android.util.Log.i(r1, r0)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            r2 = 0
            android.content.ContentResolver r5 = r5.contentResolver     // Catch:{ Exception -> 0x003b }
            android.net.Uri r3 = com.google.android.settingslib.dcservice.DcServiceClientImpl.PROXY_AUTHORITY     // Catch:{ Exception -> 0x003b }
            android.content.ContentProviderClient r5 = r5.acquireUnstableContentProviderClient(r3)     // Catch:{ Exception -> 0x003b }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ Exception -> 0x003b }
            java.lang.String r3 = "method_get_clear_calling_state"
            android.os.Bundle r0 = r5.call(r3, r2, r0)     // Catch:{ all -> 0x0034 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x0034 }
            kotlin.jdk7.AutoCloseableKt.closeFinally(r5, r2)     // Catch:{ Exception -> 0x003b }
            java.lang.String r5 = "clear_calling_enable_state"
            boolean r5 = r0.getBoolean(r5)     // Catch:{ Exception -> 0x003b }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r5)     // Catch:{ Exception -> 0x003b }
            goto L_0x0040
        L_0x0034:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0036 }
        L_0x0036:
            r3 = move-exception
            kotlin.jdk7.AutoCloseableKt.closeFinally(r5, r0)     // Catch:{ Exception -> 0x003b }
            throw r3     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            java.lang.String r5 = "getClearCallingEnableState: error happens when calling DcService."
            android.util.Log.w(r1, r5)
        L_0x0040:
            kotlinx.coroutines.flow.StateFlowImpl r5 = kotlinx.coroutines.flow.StateFlowKt.MutableStateFlow(r2)
            r4.mutableClearCallingStateUpdate = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepository.<init>(com.google.android.settingslib.dcservice.DcServiceClientImpl):void");
    }
}
