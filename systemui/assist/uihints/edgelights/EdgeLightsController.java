package com.google.android.systemui.assist.uihints.edgelights;

import android.content.Context;
import android.view.ViewGroup;
import com.android.systemui.assist.AssistLogger;
import com.google.android.systemui.assist.uihints.NgaMessageHandler;
import com.google.android.systemui.assist.uihints.NgaUiController$$ExternalSyntheticLambda2;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class EdgeLightsController implements NgaMessageHandler.AudioInfoListener, NgaMessageHandler.EdgeLightsInfoListener {
    public final AssistLogger mAssistLogger;
    public final Context mContext;
    public final EdgeLightsView mEdgeLightsView;
    public NgaUiController$$ExternalSyntheticLambda2 mThrottler;

    public EdgeLightsController(Context context, ViewGroup viewGroup, AssistLogger assistLogger) {
        this.mContext = context;
        this.mAssistLogger = assistLogger;
        this.mEdgeLightsView = (EdgeLightsView) viewGroup.findViewById(2131362483);
    }

    public final void onAudioInfo(float f, float f2) {
        this.mEdgeLightsView.mMode.onAudioLevelUpdate(f2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: com.google.android.systemui.assist.uihints.edgelights.mode.FulfillBottom} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: com.google.android.systemui.assist.uihints.edgelights.mode.FulfillBottom} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: com.google.android.systemui.assist.uihints.edgelights.mode.FulfillBottom} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: com.google.android.systemui.assist.uihints.edgelights.mode.FullListening} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: com.google.android.systemui.assist.uihints.edgelights.mode.FullListening} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: com.google.android.systemui.assist.uihints.edgelights.mode.FulfillPerimeter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: com.google.android.systemui.assist.uihints.edgelights.EdgeLightsView$Mode} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onEdgeLightsInfo(java.lang.String r11, boolean r12) {
        /*
            r10 = this;
            int r0 = r11.hashCode()
            r1 = 2
            r2 = 4
            r3 = 3
            java.lang.String r4 = "FULL_LISTENING"
            r5 = 0
            r6 = 1
            switch(r0) {
                case -1911007510: goto L_0x0035;
                case 2193567: goto L_0x002b;
                case 429932431: goto L_0x0021;
                case 1387022046: goto L_0x0017;
                case 1971150571: goto L_0x000f;
                default: goto L_0x000e;
            }
        L_0x000e:
            goto L_0x003f
        L_0x000f:
            boolean r0 = r11.equals(r4)
            if (r0 == 0) goto L_0x003f
            r0 = r5
            goto L_0x0040
        L_0x0017:
            java.lang.String r0 = "FULFILL_PERIMETER"
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = r3
            goto L_0x0040
        L_0x0021:
            java.lang.String r0 = "HALF_LISTENING"
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = r6
            goto L_0x0040
        L_0x002b:
            java.lang.String r0 = "GONE"
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = r2
            goto L_0x0040
        L_0x0035:
            java.lang.String r0 = "FULFILL_BOTTOM"
            boolean r0 = r11.equals(r0)
            if (r0 == 0) goto L_0x003f
            r0 = r1
            goto L_0x0040
        L_0x003f:
            r0 = -1
        L_0x0040:
            java.lang.String r7 = "EdgeLightsController"
            android.content.Context r8 = r10.mContext
            r9 = 0
            if (r0 == 0) goto L_0x006f
            if (r0 == r6) goto L_0x0064
            if (r0 == r1) goto L_0x005d
            if (r0 == r3) goto L_0x0057
            if (r0 == r2) goto L_0x0051
            r12 = r9
            goto L_0x0074
        L_0x0051:
            com.google.android.systemui.assist.uihints.edgelights.mode.Gone r12 = new com.google.android.systemui.assist.uihints.edgelights.mode.Gone
            r12.<init>()
            goto L_0x0074
        L_0x0057:
            com.google.android.systemui.assist.uihints.edgelights.mode.FulfillPerimeter r12 = new com.google.android.systemui.assist.uihints.edgelights.mode.FulfillPerimeter
            r12.<init>(r8)
            goto L_0x0074
        L_0x005d:
            com.google.android.systemui.assist.uihints.edgelights.mode.FulfillBottom r0 = new com.google.android.systemui.assist.uihints.edgelights.mode.FulfillBottom
            r0.<init>(r8, r12)
            r12 = r0
            goto L_0x0074
        L_0x0064:
            java.lang.String r12 = "Rendering full instead of half listening for now."
            android.util.Log.i(r7, r12)
            com.google.android.systemui.assist.uihints.edgelights.mode.FullListening r12 = new com.google.android.systemui.assist.uihints.edgelights.mode.FullListening
            r12.<init>(r8, r6)
            goto L_0x0074
        L_0x006f:
            com.google.android.systemui.assist.uihints.edgelights.mode.FullListening r12 = new com.google.android.systemui.assist.uihints.edgelights.mode.FullListening
            r12.<init>(r8, r5)
        L_0x0074:
            if (r12 != 0) goto L_0x0080
            java.lang.String r10 = "Invalid edge lights mode: "
            java.lang.String r10 = r10.concat(r11)
            android.util.Log.e(r7, r10)
            return
        L_0x0080:
            com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController$$ExternalSyntheticLambda0 r0 = new com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController$$ExternalSyntheticLambda0
            r0.<init>(r10, r12)
            com.google.android.systemui.assist.uihints.NgaUiController$$ExternalSyntheticLambda2 r10 = r10.mThrottler
            if (r10 != 0) goto L_0x008d
            r0.run()
            goto L_0x00b7
        L_0x008d:
            java.lang.Object r10 = r10.f$0
            com.google.android.systemui.assist.uihints.NgaUiController r10 = (com.google.android.systemui.assist.uihints.NgaUiController) r10
            android.animation.ValueAnimator r12 = r10.mInvocationAnimator
            if (r12 == 0) goto L_0x009e
            boolean r12 = r12.isStarted()
            if (r12 == 0) goto L_0x009e
            r10.mPendingEdgeLightsModeChange = r0
            goto L_0x00b7
        L_0x009e:
            boolean r12 = r10.mShowingAssistUi
            if (r12 != 0) goto L_0x00b2
            boolean r11 = r4.equals(r11)
            if (r11 == 0) goto L_0x00b2
            r10.mInvocationInProgress = r6
            r11 = 1065353216(0x3f800000, float:1.0)
            r10.onInvocationProgress(r5, r11)
            r10.mPendingEdgeLightsModeChange = r0
            goto L_0x00b7
        L_0x00b2:
            r10.mPendingEdgeLightsModeChange = r9
            r0.run()
        L_0x00b7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.assist.uihints.edgelights.EdgeLightsController.onEdgeLightsInfo(java.lang.String, boolean):void");
    }
}
