package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SuggestParcelables$Action {
    public String dEPRECATEDIconBitmapId;
    public SuggestParcelables$IntentInfo dEPRECATEDIntentInfo;
    public String displayName;
    public String fullDisplayName;
    public boolean hasProxiedIntentInfo;
    public String id;
    public String opaquePayload;
    public SuggestParcelables$IntentInfo proxiedIntentInfo;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Action, java.lang.Object] */
    public static SuggestParcelables$Action create(Bundle bundle) {
        ? obj = new Object();
        if (bundle.containsKey("id")) {
            obj.id = bundle.getString("id");
        }
        if (bundle.containsKey("displayName")) {
            obj.displayName = bundle.getString("displayName");
        }
        if (bundle.containsKey("dEPRECATEDIconBitmapId")) {
            obj.dEPRECATEDIconBitmapId = bundle.getString("dEPRECATEDIconBitmapId");
        }
        if (bundle.containsKey("fullDisplayName")) {
            obj.fullDisplayName = bundle.getString("fullDisplayName");
        }
        if (bundle.containsKey("dEPRECATEDIntentInfo")) {
            Bundle bundle2 = bundle.getBundle("dEPRECATEDIntentInfo");
            if (bundle2 == null) {
                obj.dEPRECATEDIntentInfo = null;
            } else {
                obj.dEPRECATEDIntentInfo = SuggestParcelables$IntentInfo.create(bundle2);
            }
        }
        if (!bundle.containsKey("proxiedIntentInfo")) {
            obj.hasProxiedIntentInfo = false;
        } else {
            obj.hasProxiedIntentInfo = true;
            Bundle bundle3 = bundle.getBundle("proxiedIntentInfo");
            if (bundle3 == null) {
                obj.proxiedIntentInfo = null;
            } else {
                obj.proxiedIntentInfo = SuggestParcelables$IntentInfo.create(bundle3);
            }
        }
        if (bundle.containsKey("opaquePayload")) {
            obj.opaquePayload = bundle.getString("opaquePayload");
        }
        return obj;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.id);
        bundle.putString("displayName", this.displayName);
        bundle.putString("dEPRECATEDIconBitmapId", this.dEPRECATEDIconBitmapId);
        bundle.putString("fullDisplayName", this.fullDisplayName);
        SuggestParcelables$IntentInfo suggestParcelables$IntentInfo = this.dEPRECATEDIntentInfo;
        if (suggestParcelables$IntentInfo == null) {
            bundle.putBundle("dEPRECATEDIntentInfo", (Bundle) null);
        } else {
            bundle.putBundle("dEPRECATEDIntentInfo", suggestParcelables$IntentInfo.writeToBundle());
        }
        SuggestParcelables$IntentInfo suggestParcelables$IntentInfo2 = this.proxiedIntentInfo;
        if (suggestParcelables$IntentInfo2 == null) {
            bundle.putBundle("proxiedIntentInfo", (Bundle) null);
        } else {
            bundle.putBundle("proxiedIntentInfo", suggestParcelables$IntentInfo2.writeToBundle());
        }
        bundle.putString("opaquePayload", this.opaquePayload);
        return bundle;
    }
}
