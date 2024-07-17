package com.google.android.setupdesign.util;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class PartnerStyleHelper {
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getLayoutGravity(android.content.Context r4) {
        /*
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r0 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r4)
            com.google.android.setupcompat.partnerconfig.PartnerConfig r1 = com.google.android.setupcompat.partnerconfig.PartnerConfig.CONFIG_LAYOUT_GRAVITY
            java.lang.String r4 = r0.getString(r4, r1)
            r0 = 0
            if (r4 != 0) goto L_0x000e
            return r0
        L_0x000e:
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r4 = r4.toLowerCase(r1)
            int r1 = r4.hashCode()
            r2 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            r3 = 1
            if (r1 == r2) goto L_0x002e
            r2 = 109757538(0x68ac462, float:5.219839E-35)
            if (r1 == r2) goto L_0x0024
            goto L_0x0038
        L_0x0024:
            java.lang.String r1 = "start"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0038
            r4 = r3
            goto L_0x0039
        L_0x002e:
            java.lang.String r1 = "center"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0038
            r4 = r0
            goto L_0x0039
        L_0x0038:
            r4 = -1
        L_0x0039:
            if (r4 == 0) goto L_0x0042
            if (r4 == r3) goto L_0x003e
            return r0
        L_0x003e:
            r4 = 8388611(0x800003, float:1.1754948E-38)
            return r4
        L_0x0042:
            r4 = 17
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.util.PartnerStyleHelper.getLayoutGravity(android.content.Context):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.google.android.setupcompat.internal.TemplateLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.google.android.setupcompat.internal.TemplateLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: android.app.Activity} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.google.android.setupcompat.internal.TemplateLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.google.android.setupcompat.internal.TemplateLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.google.android.setupcompat.internal.TemplateLayout} */
    /* JADX WARNING: type inference failed for: r2v0, types: [android.app.Activity] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0064 A[ADDED_TO_REGION] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean shouldApplyPartnerResource(android.view.View r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r4 instanceof com.google.android.setupcompat.PartnerCustomizationLayout
            if (r1 == 0) goto L_0x000f
            com.google.android.setupcompat.PartnerCustomizationLayout r4 = (com.google.android.setupcompat.PartnerCustomizationLayout) r4
            boolean r4 = r4.shouldApplyPartnerResource()
            return r4
        L_0x000f:
            android.content.Context r4 = r4.getContext()
            com.google.android.setupcompat.partnerconfig.PartnerConfigHelper r1 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.get(r4)
            boolean r1 = r1.isAvailable()
            if (r1 != 0) goto L_0x001e
            goto L_0x0067
        L_0x001e:
            r1 = 0
            com.google.android.setupcompat.util.Logger r2 = com.google.android.setupcompat.PartnerCustomizationLayout.LOG     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0042 }
            android.app.Activity r2 = com.google.android.setupcompat.partnerconfig.PartnerConfigHelper.lookupActivityFromContext(r4)     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0042 }
            if (r2 == 0) goto L_0x0043
            r3 = 2131363747(0x7f0a07a3, float:1.8347312E38)
            android.view.View r3 = r2.findViewById(r3)     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0041 }
            if (r3 == 0) goto L_0x0036
            android.view.ViewParent r1 = r3.getParent()     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0041 }
            com.google.android.setupcompat.internal.TemplateLayout r1 = (com.google.android.setupcompat.internal.TemplateLayout) r1     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0041 }
        L_0x0036:
            boolean r3 = r1 instanceof com.google.android.setupcompat.PartnerCustomizationLayout     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0041 }
            if (r3 == 0) goto L_0x0043
            com.google.android.setupcompat.PartnerCustomizationLayout r1 = (com.google.android.setupcompat.PartnerCustomizationLayout) r1     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0041 }
            boolean r0 = r1.shouldApplyPartnerResource()     // Catch:{ ClassCastException | IllegalArgumentException -> 0x0041 }
            goto L_0x0067
        L_0x0041:
            r1 = r2
        L_0x0042:
            r2 = r1
        L_0x0043:
            if (r2 == 0) goto L_0x004e
            android.content.Intent r1 = r2.getIntent()
            boolean r1 = com.google.android.setupcompat.util.WizardManagerHelper.isAnySetupWizard(r1)
            goto L_0x004f
        L_0x004e:
            r1 = r0
        L_0x004f:
            r2 = 2130970028(0x7f0405ac, float:1.7548755E38)
            int[] r2 = new int[]{r2}
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r2)
            r2 = 1
            boolean r3 = r4.getBoolean(r0, r2)
            r4.recycle()
            if (r1 != 0) goto L_0x0066
            if (r3 == 0) goto L_0x0067
        L_0x0066:
            r0 = r2
        L_0x0067:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.util.PartnerStyleHelper.shouldApplyPartnerResource(android.view.View):boolean");
    }
}
