package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext;
import com.google.android.systemui.screenshot.ScreenshotNotificationSmartActionsProviderGoogle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class ContentSuggestionsServiceClient$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ContentSuggestionsServiceClient f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ InteractionContextParcelables$InteractionContext f$6;
    public final /* synthetic */ UserHandle f$7;
    public final /* synthetic */ Uri f$8;
    public final /* synthetic */ ContentSuggestionsServiceWrapper$BundleCallback f$9;

    public /* synthetic */ ContentSuggestionsServiceClient$$ExternalSyntheticLambda1(ContentSuggestionsServiceClient contentSuggestionsServiceClient, int i, Bundle bundle, String str, String str2, long j, InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext, UserHandle userHandle, Uri uri, ScreenshotNotificationSmartActionsProviderGoogle.AnonymousClass1 r11) {
        this.f$0 = contentSuggestionsServiceClient;
        this.f$1 = i;
        this.f$2 = bundle;
        this.f$3 = str;
        this.f$4 = str2;
        this.f$5 = j;
        this.f$6 = interactionContextParcelables$InteractionContext;
        this.f$7 = userHandle;
        this.f$8 = uri;
        this.f$9 = r11;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ParserParcelables$ParsedViewHierarchy} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r20 = this;
            r0 = r20
            com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient r1 = r0.f$0
            int r10 = r0.f$1
            android.os.Bundle r2 = r0.f$2
            java.lang.String r11 = r0.f$3
            java.lang.String r12 = r0.f$4
            long r13 = r0.f$5
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext r15 = r0.f$6
            android.os.UserHandle r9 = r0.f$7
            android.net.Uri r8 = r0.f$8
            com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback r7 = r0.f$9
            com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl r5 = r1.serviceWrapper
            r5.getClass()
            java.lang.String r0 = "CAPTURE_TIME_MS"
            long r3 = java.lang.System.currentTimeMillis()
            r2.putLong(r0, r3)
            android.app.contentsuggestions.ContentSuggestionsManager r0 = r5.contentSuggestionsManager     // Catch:{ all -> 0x002a }
            r0.provideContextImage(r10, r2)     // Catch:{ all -> 0x002a }
            goto L_0x0030
        L_0x002a:
            r0 = move-exception
            java.lang.String r2 = "Failed to provideContextImage"
            com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils.e(r2, r0)
        L_0x0030:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ParserParcelables$ParsedViewHierarchy r16 = new com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ParserParcelables$ParsedViewHierarchy
            r16.<init>()
            com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils r2 = r1.bundleUtils
            r2.getClass()
            r2 = r11
            r3 = r12
            r4 = r10
            r17 = r5
            r5 = r13
            r18 = r7
            r7 = r15
            r19 = r8
            r8 = r0
            r20 = r9
            r9 = r16
            android.os.Bundle r9 = com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils.createSelectionsRequest(r2, r3, r4, r5, r7, r8, r9)
            int r0 = r20.getIdentifier()
            android.os.UserManager r2 = r1.userManager
            boolean r0 = r2.isManagedProfile(r0)
            java.lang.String r2 = "IsManagedProfile"
            r9.putBoolean(r2, r0)
            java.lang.String r0 = "UserHandle"
            r2 = r20
            r9.putParcelable(r0, r2)
            com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$1 r8 = new com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$1
            r0 = r8
            r2 = r11
            r11 = r8
            r8 = r19
            r12 = r9
            r9 = r18
            r0.<init>(r2, r3, r4, r5, r7, r8, r9)
            r1 = r17
            r1.suggestContentSelections(r10, r12, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$$ExternalSyntheticLambda1.run():void");
    }
}
