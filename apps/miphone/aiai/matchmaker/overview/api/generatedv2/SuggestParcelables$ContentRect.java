package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SuggestParcelables$ContentRect {
    public int beginChar;
    public int contentGroupIndex;
    public SuggestParcelables$ContentType contentType;
    public String contentUri;
    public int endChar;
    public int lineId;
    public SuggestParcelables$OnScreenRect rect;
    public String text;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ContentRect, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v12, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$OnScreenRect] */
    public static SuggestParcelables$ContentRect create(Bundle bundle) {
        ? obj = new Object();
        SuggestParcelables$ContentType suggestParcelables$ContentType = null;
        if (bundle.containsKey("rect")) {
            Bundle bundle2 = bundle.getBundle("rect");
            if (bundle2 == null) {
                obj.rect = null;
            } else {
                ? obj2 = new Object();
                if (bundle2.containsKey("left")) {
                    obj2.left = bundle2.getFloat("left");
                }
                if (bundle2.containsKey("top")) {
                    obj2.top = bundle2.getFloat("top");
                }
                if (bundle2.containsKey("width")) {
                    obj2.width = bundle2.getFloat("width");
                }
                if (bundle2.containsKey("height")) {
                    obj2.height = bundle2.getFloat("height");
                }
                obj.rect = obj2;
            }
        }
        if (bundle.containsKey("text")) {
            obj.text = bundle.getString("text");
        }
        if (bundle.containsKey("contentType")) {
            Bundle bundle3 = bundle.getBundle("contentType");
            if (bundle3 == null) {
                obj.contentType = null;
            } else {
                int i = bundle3.getInt("value");
                if (i == 0) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_UNKNOWN;
                } else if (i == 1) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_TEXT;
                } else if (i == 2) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_IMAGE;
                } else if (i == 3) {
                    suggestParcelables$ContentType = SuggestParcelables$ContentType.CONTENT_TYPE_BARCODE;
                }
                obj.contentType = suggestParcelables$ContentType;
            }
        }
        if (bundle.containsKey("lineId")) {
            obj.lineId = bundle.getInt("lineId");
        }
        if (bundle.containsKey("contentUri")) {
            obj.contentUri = bundle.getString("contentUri");
        }
        if (bundle.containsKey("contentGroupIndex")) {
            obj.contentGroupIndex = bundle.getInt("contentGroupIndex");
        }
        if (bundle.containsKey("beginChar")) {
            obj.beginChar = bundle.getInt("beginChar");
        }
        if (bundle.containsKey("endChar")) {
            obj.endChar = bundle.getInt("endChar");
        }
        return obj;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        SuggestParcelables$OnScreenRect suggestParcelables$OnScreenRect = this.rect;
        if (suggestParcelables$OnScreenRect == null) {
            bundle.putBundle("rect", (Bundle) null);
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putFloat("left", suggestParcelables$OnScreenRect.left);
            bundle2.putFloat("top", suggestParcelables$OnScreenRect.top);
            bundle2.putFloat("width", suggestParcelables$OnScreenRect.width);
            bundle2.putFloat("height", suggestParcelables$OnScreenRect.height);
            bundle.putBundle("rect", bundle2);
        }
        bundle.putString("text", this.text);
        SuggestParcelables$ContentType suggestParcelables$ContentType = this.contentType;
        if (suggestParcelables$ContentType == null) {
            bundle.putBundle("contentType", (Bundle) null);
        } else {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("value", suggestParcelables$ContentType.value);
            bundle.putBundle("contentType", bundle3);
        }
        bundle.putInt("lineId", this.lineId);
        bundle.putString("contentUri", this.contentUri);
        bundle.putInt("contentGroupIndex", this.contentGroupIndex);
        bundle.putInt("beginChar", this.beginChar);
        bundle.putInt("endChar", this.endChar);
        return bundle;
    }
}
