package com.google.android.setupdesign.util;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ThemeResolver {
    public static ThemeResolver defaultResolver;
    public final int defaultTheme;
    public final String oldestSupportedTheme;
    public final boolean useDayNight;

    public ThemeResolver(int i, String str, boolean z) {
        this.defaultTheme = i;
        this.oldestSupportedTheme = str;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getDayNightThemeRes(java.lang.String r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x008b
            int r1 = r2.hashCode()
            switch(r1) {
                case -2128555920: goto L_0x0068;
                case -1270463490: goto L_0x005d;
                case -1241052239: goto L_0x0053;
                case -353548558: goto L_0x0049;
                case 3175618: goto L_0x003f;
                case 115650329: goto L_0x0035;
                case 115650330: goto L_0x002b;
                case 115650331: goto L_0x0021;
                case 299066663: goto L_0x0016;
                case 767685465: goto L_0x000c;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0072
        L_0x000c:
            java.lang.String r1 = "glif_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 6
            goto L_0x0073
        L_0x0016:
            java.lang.String r1 = "material"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 9
            goto L_0x0073
        L_0x0021:
            java.lang.String r1 = "glif_v4"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 1
            goto L_0x0073
        L_0x002b:
            java.lang.String r1 = "glif_v3"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 3
            goto L_0x0073
        L_0x0035:
            java.lang.String r1 = "glif_v2"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 5
            goto L_0x0073
        L_0x003f:
            java.lang.String r1 = "glif"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 7
            goto L_0x0073
        L_0x0049:
            java.lang.String r1 = "glif_v4_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = r0
            goto L_0x0073
        L_0x0053:
            java.lang.String r1 = "glif_v3_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 2
            goto L_0x0073
        L_0x005d:
            java.lang.String r1 = "material_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 8
            goto L_0x0073
        L_0x0068:
            java.lang.String r1 = "glif_v2_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 4
            goto L_0x0073
        L_0x0072:
            r2 = -1
        L_0x0073:
            switch(r2) {
                case 0: goto L_0x0087;
                case 1: goto L_0x0087;
                case 2: goto L_0x0083;
                case 3: goto L_0x0083;
                case 4: goto L_0x007f;
                case 5: goto L_0x007f;
                case 6: goto L_0x007b;
                case 7: goto L_0x007b;
                case 8: goto L_0x0077;
                case 9: goto L_0x0077;
                default: goto L_0x0076;
            }
        L_0x0076:
            goto L_0x008b
        L_0x0077:
            r2 = 2132017889(0x7f1402e1, float:1.967407E38)
            return r2
        L_0x007b:
            r2 = 2132017877(0x7f1402d5, float:1.9674045E38)
            return r2
        L_0x007f:
            r2 = 2132017880(0x7f1402d8, float:1.967405E38)
            return r2
        L_0x0083:
            r2 = 2132017883(0x7f1402db, float:1.9674057E38)
            return r2
        L_0x0087:
            r2 = 2132017886(0x7f1402de, float:1.9674063E38)
            return r2
        L_0x008b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.util.ThemeResolver.getDayNightThemeRes(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getThemeRes(java.lang.String r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x009f
            int r1 = r2.hashCode()
            switch(r1) {
                case -2128555920: goto L_0x0068;
                case -1270463490: goto L_0x005d;
                case -1241052239: goto L_0x0053;
                case -353548558: goto L_0x0049;
                case 3175618: goto L_0x003f;
                case 115650329: goto L_0x0035;
                case 115650330: goto L_0x002b;
                case 115650331: goto L_0x0021;
                case 299066663: goto L_0x0016;
                case 767685465: goto L_0x000c;
                default: goto L_0x000a;
            }
        L_0x000a:
            goto L_0x0072
        L_0x000c:
            java.lang.String r1 = "glif_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 6
            goto L_0x0073
        L_0x0016:
            java.lang.String r1 = "material"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 9
            goto L_0x0073
        L_0x0021:
            java.lang.String r1 = "glif_v4"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 1
            goto L_0x0073
        L_0x002b:
            java.lang.String r1 = "glif_v3"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 3
            goto L_0x0073
        L_0x0035:
            java.lang.String r1 = "glif_v2"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 5
            goto L_0x0073
        L_0x003f:
            java.lang.String r1 = "glif"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 7
            goto L_0x0073
        L_0x0049:
            java.lang.String r1 = "glif_v4_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = r0
            goto L_0x0073
        L_0x0053:
            java.lang.String r1 = "glif_v3_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 2
            goto L_0x0073
        L_0x005d:
            java.lang.String r1 = "material_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 8
            goto L_0x0073
        L_0x0068:
            java.lang.String r1 = "glif_v2_light"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x0072
            r2 = 4
            goto L_0x0073
        L_0x0072:
            r2 = -1
        L_0x0073:
            switch(r2) {
                case 0: goto L_0x009b;
                case 1: goto L_0x0097;
                case 2: goto L_0x0093;
                case 3: goto L_0x008f;
                case 4: goto L_0x008b;
                case 5: goto L_0x0087;
                case 6: goto L_0x0083;
                case 7: goto L_0x007f;
                case 8: goto L_0x007b;
                case 9: goto L_0x0077;
                default: goto L_0x0076;
            }
        L_0x0076:
            goto L_0x009f
        L_0x0077:
            r2 = 2132017888(0x7f1402e0, float:1.9674067E38)
            return r2
        L_0x007b:
            r2 = 2132017890(0x7f1402e2, float:1.9674071E38)
            return r2
        L_0x007f:
            r2 = 2132017876(0x7f1402d4, float:1.9674043E38)
            return r2
        L_0x0083:
            r2 = 2132017878(0x7f1402d6, float:1.9674047E38)
            return r2
        L_0x0087:
            r2 = 2132017879(0x7f1402d7, float:1.9674049E38)
            return r2
        L_0x008b:
            r2 = 2132017881(0x7f1402d9, float:1.9674053E38)
            return r2
        L_0x008f:
            r2 = 2132017882(0x7f1402da, float:1.9674055E38)
            return r2
        L_0x0093:
            r2 = 2132017884(0x7f1402dc, float:1.967406E38)
            return r2
        L_0x0097:
            r2 = 2132017885(0x7f1402dd, float:1.9674061E38)
            return r2
        L_0x009b:
            r2 = 2132017887(0x7f1402df, float:1.9674065E38)
            return r2
        L_0x009f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.util.ThemeResolver.getThemeRes(java.lang.String):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getThemeVersion(java.lang.String r7) {
        /*
            r0 = -1
            if (r7 == 0) goto L_0x0082
            int r1 = r7.hashCode()
            r2 = 4
            r3 = 2
            r4 = 5
            r5 = 3
            r6 = 1
            switch(r1) {
                case -2128555920: goto L_0x006e;
                case -1270463490: goto L_0x0063;
                case -1241052239: goto L_0x0059;
                case -353548558: goto L_0x004f;
                case 3175618: goto L_0x0045;
                case 115650329: goto L_0x003b;
                case 115650330: goto L_0x0031;
                case 115650331: goto L_0x0027;
                case 299066663: goto L_0x001c;
                case 767685465: goto L_0x0011;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0078
        L_0x0011:
            java.lang.String r1 = "glif_light"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = 6
            goto L_0x0079
        L_0x001c:
            java.lang.String r1 = "material"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = 9
            goto L_0x0079
        L_0x0027:
            java.lang.String r1 = "glif_v4"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = r6
            goto L_0x0079
        L_0x0031:
            java.lang.String r1 = "glif_v3"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = r5
            goto L_0x0079
        L_0x003b:
            java.lang.String r1 = "glif_v2"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = r4
            goto L_0x0079
        L_0x0045:
            java.lang.String r1 = "glif"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = 7
            goto L_0x0079
        L_0x004f:
            java.lang.String r1 = "glif_v4_light"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = 0
            goto L_0x0079
        L_0x0059:
            java.lang.String r1 = "glif_v3_light"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = r3
            goto L_0x0079
        L_0x0063:
            java.lang.String r1 = "material_light"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = 8
            goto L_0x0079
        L_0x006e:
            java.lang.String r1 = "glif_v2_light"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0078
            r7 = r2
            goto L_0x0079
        L_0x0078:
            r7 = r0
        L_0x0079:
            switch(r7) {
                case 0: goto L_0x0081;
                case 1: goto L_0x0081;
                case 2: goto L_0x0080;
                case 3: goto L_0x0080;
                case 4: goto L_0x007f;
                case 5: goto L_0x007f;
                case 6: goto L_0x007e;
                case 7: goto L_0x007e;
                case 8: goto L_0x007d;
                case 9: goto L_0x007d;
                default: goto L_0x007c;
            }
        L_0x007c:
            goto L_0x0082
        L_0x007d:
            return r6
        L_0x007e:
            return r3
        L_0x007f:
            return r5
        L_0x0080:
            return r2
        L_0x0081:
            return r4
        L_0x0082:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.util.ThemeResolver.getThemeVersion(java.lang.String):int");
    }
}
