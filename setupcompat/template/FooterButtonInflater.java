package com.google.android.setupcompat.template;

import android.content.Context;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class FooterButtonInflater {
    public final Context context;

    public FooterButtonInflater(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0011 A[Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.setupcompat.template.FooterButton inflate(org.xmlpull.v1.XmlPullParser r5) {
        /*
            r4 = this;
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)
        L_0x0004:
            int r1 = r5.next()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r2 = 2
            if (r1 == r2) goto L_0x000f
            r3 = 1
            if (r1 == r3) goto L_0x000f
            goto L_0x0004
        L_0x000f:
            if (r1 != r2) goto L_0x0044
            java.lang.String r1 = r5.getName()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.String r2 = "FooterButton"
            boolean r1 = r1.equals(r2)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            if (r1 == 0) goto L_0x0029
            com.google.android.setupcompat.template.FooterButton r1 = new com.google.android.setupcompat.template.FooterButton     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            android.content.Context r4 = r4.context     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r1.<init>((android.content.Context) r4, (android.util.AttributeSet) r0)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            return r1
        L_0x0025:
            r4 = move-exception
            goto L_0x005f
        L_0x0027:
            r4 = move-exception
            goto L_0x0081
        L_0x0029:
            android.view.InflateException r4 = new android.view.InflateException     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r0.<init>()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.String r1 = r5.getPositionDescription()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r0.append(r1)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.String r1 = ": not a FooterButton"
            r0.append(r1)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.String r0 = r0.toString()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r4.<init>(r0)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            throw r4     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
        L_0x0044:
            android.view.InflateException r4 = new android.view.InflateException     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r0.<init>()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.String r1 = r5.getPositionDescription()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r0.append(r1)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.String r1 = ": No start tag found!"
            r0.append(r1)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            java.lang.String r0 = r0.toString()     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            r4.<init>(r0)     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
            throw r4     // Catch:{ XmlPullParserException -> 0x0027, IOException -> 0x0025 }
        L_0x005f:
            android.view.InflateException r0 = new android.view.InflateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r5 = r5.getPositionDescription()
            r1.append(r5)
            java.lang.String r5 = ": "
            r1.append(r5)
            java.lang.String r5 = r4.getMessage()
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5, r4)
            throw r0
        L_0x0081:
            android.view.InflateException r5 = new android.view.InflateException
            java.lang.String r0 = r4.getMessage()
            r5.<init>(r0, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.template.FooterButtonInflater.inflate(org.xmlpull.v1.XmlPullParser):com.google.android.setupcompat.template.FooterButton");
    }
}
