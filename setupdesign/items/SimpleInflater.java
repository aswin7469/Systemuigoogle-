package com.google.android.setupdesign.items;

import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.InflateException;
import com.google.android.setupdesign.items.ItemInflater;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class SimpleInflater {
    public final Resources resources;

    public SimpleInflater(Resources resources2) {
        this.resources = resources2;
    }

    public final Object createItemFromTag(String str, AttributeSet attributeSet) {
        try {
            return onCreateItem(str, attributeSet);
        } catch (InflateException e) {
            throw e;
        } catch (Exception e2) {
            throw new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str, e2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0021 A[Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0011 A[Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object inflate(org.xmlpull.v1.XmlPullParser r5) {
        /*
            r4 = this;
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r5)
        L_0x0004:
            int r1 = r5.next()     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            r2 = 2
            if (r1 == r2) goto L_0x000f
            r3 = 1
            if (r1 == r3) goto L_0x000f
            goto L_0x0004
        L_0x000f:
            if (r1 != r2) goto L_0x0021
            java.lang.String r1 = r5.getName()     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            java.lang.Object r1 = r4.createItemFromTag(r1, r0)     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            r4.rInflate(r5, r1, r0)     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            return r1
        L_0x001d:
            r4 = move-exception
            goto L_0x003c
        L_0x001f:
            r4 = move-exception
            goto L_0x005e
        L_0x0021:
            android.view.InflateException r4 = new android.view.InflateException     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            r0.<init>()     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            java.lang.String r1 = r5.getPositionDescription()     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            r0.append(r1)     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            java.lang.String r1 = ": No start tag found!"
            r0.append(r1)     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            java.lang.String r0 = r0.toString()     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            r4.<init>(r0)     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
            throw r4     // Catch:{ XmlPullParserException -> 0x001f, IOException -> 0x001d }
        L_0x003c:
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
        L_0x005e:
            android.view.InflateException r5 = new android.view.InflateException
            java.lang.String r0 = r4.getMessage()
            r5.<init>(r0, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupdesign.items.SimpleInflater.inflate(org.xmlpull.v1.XmlPullParser):java.lang.Object");
    }

    public abstract Object onCreateItem(String str, AttributeSet attributeSet);

    public final void rInflate(XmlPullParser xmlPullParser, Object obj, AttributeSet attributeSet) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                Object createItemFromTag = createItemFromTag(xmlPullParser.getName(), attributeSet);
                AbstractItemHierarchy abstractItemHierarchy = (AbstractItemHierarchy) obj;
                AbstractItemHierarchy abstractItemHierarchy2 = (AbstractItemHierarchy) createItemFromTag;
                if (abstractItemHierarchy instanceof ItemInflater.ItemParent) {
                    ((ItemInflater.ItemParent) abstractItemHierarchy).addChild(abstractItemHierarchy2);
                    rInflate(xmlPullParser, createItemFromTag, attributeSet);
                } else {
                    throw new IllegalArgumentException("Cannot add child item to " + abstractItemHierarchy);
                }
            }
        }
    }
}
