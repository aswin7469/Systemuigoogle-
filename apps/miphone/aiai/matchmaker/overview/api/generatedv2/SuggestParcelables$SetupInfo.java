package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class SuggestParcelables$SetupInfo {
    public SuggestParcelables$ErrorCode errorCode;
    public String errorMesssage;
    public List setupFlags;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$SetupInfo, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Flag, java.lang.Object] */
    public static SuggestParcelables$SetupInfo create(Bundle bundle) {
        SuggestParcelables$ErrorCode suggestParcelables$ErrorCode;
        ? obj = new Object();
        if (bundle.containsKey("errorCode")) {
            Bundle bundle2 = bundle.getBundle("errorCode");
            if (bundle2 == null) {
                obj.errorCode = null;
            } else {
                int i = bundle2.getInt("value");
                if (i == 0) {
                    suggestParcelables$ErrorCode = SuggestParcelables$ErrorCode.ERROR_CODE_SUCCESS;
                } else if (i == 1) {
                    suggestParcelables$ErrorCode = SuggestParcelables$ErrorCode.ERROR_CODE_UNKNOWN_ERROR;
                } else if (i == 2) {
                    suggestParcelables$ErrorCode = SuggestParcelables$ErrorCode.ERROR_CODE_TIMEOUT;
                } else if (i == 3) {
                    suggestParcelables$ErrorCode = SuggestParcelables$ErrorCode.ERROR_CODE_NO_SCREEN_CONTENT;
                } else if (i == 4) {
                    suggestParcelables$ErrorCode = SuggestParcelables$ErrorCode.ERROR_CODE_NO_SUPPORTED_LOCALES;
                } else {
                    suggestParcelables$ErrorCode = null;
                }
                obj.errorCode = suggestParcelables$ErrorCode;
            }
        }
        if (bundle.containsKey("errorMesssage")) {
            obj.errorMesssage = bundle.getString("errorMesssage");
        }
        if (bundle.containsKey("setupFlags")) {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("setupFlags");
            if (parcelableArrayList == null) {
                obj.setupFlags = null;
            } else {
                obj.setupFlags = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle3 = (Bundle) it.next();
                    if (bundle3 == null) {
                        obj.setupFlags.add((Object) null);
                    } else {
                        List list = obj.setupFlags;
                        ? obj2 = new Object();
                        if (bundle3.containsKey("name")) {
                            obj2.name = bundle3.getString("name");
                        }
                        if (bundle3.containsKey("value")) {
                            obj2.value = bundle3.getString("value");
                        }
                        list.add(obj2);
                    }
                }
            }
        }
        return obj;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        SuggestParcelables$ErrorCode suggestParcelables$ErrorCode = this.errorCode;
        if (suggestParcelables$ErrorCode == null) {
            bundle.putBundle("errorCode", (Bundle) null);
        } else {
            suggestParcelables$ErrorCode.getClass();
            Bundle bundle2 = new Bundle();
            bundle2.putInt("value", suggestParcelables$ErrorCode.value);
            bundle.putBundle("errorCode", bundle2);
        }
        bundle.putString("errorMesssage", this.errorMesssage);
        if (this.setupFlags == null) {
            bundle.putParcelableArrayList("setupFlags", (ArrayList) null);
        } else {
            ArrayList arrayList = new ArrayList(((ArrayList) this.setupFlags).size());
            for (SuggestParcelables$Flag suggestParcelables$Flag : this.setupFlags) {
                if (suggestParcelables$Flag == null) {
                    arrayList.add((Object) null);
                } else {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("name", suggestParcelables$Flag.name);
                    bundle3.putString("value", suggestParcelables$Flag.value);
                    arrayList.add(bundle3);
                }
            }
            bundle.putParcelableArrayList("setupFlags", arrayList);
        }
        return bundle;
    }
}
