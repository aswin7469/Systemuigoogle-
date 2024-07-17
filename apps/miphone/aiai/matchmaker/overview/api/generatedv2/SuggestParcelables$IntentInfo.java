package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SuggestParcelables$IntentInfo {
    public String action;
    public String className;
    public int flags;
    public List intentParams;
    public SuggestParcelables$IntentType intentType;
    public String mimeType;
    public String packageName;
    public String uri;

    /* JADX WARNING: type inference failed for: r1v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$IntentInfo, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r14v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$IntentParam, java.lang.Object] */
    public static SuggestParcelables$IntentInfo create(Bundle bundle) {
        SuggestParcelables$IntentParamType suggestParcelables$IntentParamType;
        Bundle bundle2 = bundle;
        ? obj = new Object();
        int i = 7;
        int i2 = 6;
        SuggestParcelables$IntentType suggestParcelables$IntentType = null;
        if (bundle2.containsKey("intentParams")) {
            ArrayList parcelableArrayList = bundle2.getParcelableArrayList("intentParams");
            if (parcelableArrayList == null) {
                obj.intentParams = null;
            } else {
                obj.intentParams = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle3 = (Bundle) it.next();
                    if (bundle3 == null) {
                        obj.intentParams.add((Object) null);
                    } else {
                        List list = obj.intentParams;
                        ? obj2 = new Object();
                        if (bundle3.containsKey("name")) {
                            obj2.name = bundle3.getString("name");
                        }
                        if (bundle3.containsKey("type")) {
                            Bundle bundle4 = bundle3.getBundle("type");
                            if (bundle4 == null) {
                                obj2.type = null;
                            } else {
                                int i3 = bundle4.getInt("value");
                                if (i3 == 0) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_UNKNOWN;
                                } else if (i3 == 1) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_STRING;
                                } else if (i3 == 2) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_INT;
                                } else if (i3 == 3) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_FLOAT;
                                } else if (i3 == 4) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_LONG;
                                } else if (i3 == 5) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_INTENT;
                                } else if (i3 == i2) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_CONTENT_URI;
                                } else if (i3 == i) {
                                    suggestParcelables$IntentParamType = SuggestParcelables$IntentParamType.INTENT_PARAM_TYPE_BOOL;
                                } else {
                                    suggestParcelables$IntentParamType = null;
                                }
                                obj2.type = suggestParcelables$IntentParamType;
                            }
                        }
                        if (bundle3.containsKey("strValue")) {
                            obj2.strValue = bundle3.getString("strValue");
                        }
                        if (bundle3.containsKey("intValue")) {
                            obj2.intValue = bundle3.getInt("intValue");
                        }
                        if (bundle3.containsKey("floatValue")) {
                            obj2.floatValue = bundle3.getFloat("floatValue");
                        }
                        if (bundle3.containsKey("longValue")) {
                            obj2.longValue = bundle3.getLong("longValue");
                        }
                        if (bundle3.containsKey("boolValue")) {
                            obj2.boolValue = bundle3.getBoolean("boolValue");
                        }
                        if (bundle3.containsKey("intentValue")) {
                            Bundle bundle5 = bundle3.getBundle("intentValue");
                            if (bundle5 == null) {
                                obj2.intentValue = null;
                            } else {
                                obj2.intentValue = create(bundle5);
                            }
                        }
                        if (bundle3.containsKey("contentUri")) {
                            obj2.contentUri = bundle3.getString("contentUri");
                        }
                        list.add(obj2);
                    }
                    i = 7;
                    i2 = 6;
                }
            }
        }
        if (bundle2.containsKey("packageName")) {
            obj.packageName = bundle2.getString("packageName");
        }
        if (bundle2.containsKey("className")) {
            obj.className = bundle2.getString("className");
        }
        if (bundle2.containsKey("action")) {
            obj.action = bundle2.getString("action");
        }
        if (bundle2.containsKey("uri")) {
            obj.uri = bundle2.getString("uri");
        }
        if (bundle2.containsKey("mimeType")) {
            obj.mimeType = bundle2.getString("mimeType");
        }
        if (bundle2.containsKey("flags")) {
            obj.flags = bundle2.getInt("flags");
        }
        if (bundle2.containsKey("intentType")) {
            Bundle bundle6 = bundle2.getBundle("intentType");
            if (bundle6 == null) {
                obj.intentType = null;
            } else {
                int i4 = bundle6.getInt("value");
                if (i4 == 0) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.DEFAULT;
                } else if (i4 == 1) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.COPY_TEXT;
                } else if (i4 == 2) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.SHARE_IMAGE;
                } else if (i4 == 3) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.LENS;
                } else if (i4 == 4) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.SAVE;
                } else if (i4 == 5) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.COPY_IMAGE;
                } else if (i4 == 6) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.SMART_REC;
                } else if (i4 == 7) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.IMAGE_SEARCH;
                } else if (i4 == 8) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.WIFI_CONNECT;
                } else if (i4 == 9) {
                    suggestParcelables$IntentType = SuggestParcelables$IntentType.FEEDBACK_MSG;
                }
                obj.intentType = suggestParcelables$IntentType;
            }
        }
        return obj;
    }

    public final Bundle writeToBundle() {
        Bundle bundle = new Bundle();
        if (this.intentParams == null) {
            bundle.putParcelableArrayList("intentParams", (ArrayList) null);
        } else {
            ArrayList arrayList = new ArrayList(((ArrayList) this.intentParams).size());
            for (SuggestParcelables$IntentParam suggestParcelables$IntentParam : this.intentParams) {
                if (suggestParcelables$IntentParam == null) {
                    arrayList.add((Object) null);
                } else {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("name", suggestParcelables$IntentParam.name);
                    SuggestParcelables$IntentParamType suggestParcelables$IntentParamType = suggestParcelables$IntentParam.type;
                    if (suggestParcelables$IntentParamType == null) {
                        bundle2.putBundle("type", (Bundle) null);
                    } else {
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("value", suggestParcelables$IntentParamType.value);
                        bundle2.putBundle("type", bundle3);
                    }
                    bundle2.putString("strValue", suggestParcelables$IntentParam.strValue);
                    bundle2.putInt("intValue", suggestParcelables$IntentParam.intValue);
                    bundle2.putFloat("floatValue", suggestParcelables$IntentParam.floatValue);
                    bundle2.putLong("longValue", suggestParcelables$IntentParam.longValue);
                    bundle2.putBoolean("boolValue", suggestParcelables$IntentParam.boolValue);
                    SuggestParcelables$IntentInfo suggestParcelables$IntentInfo = suggestParcelables$IntentParam.intentValue;
                    if (suggestParcelables$IntentInfo == null) {
                        bundle2.putBundle("intentValue", (Bundle) null);
                    } else {
                        bundle2.putBundle("intentValue", suggestParcelables$IntentInfo.writeToBundle());
                    }
                    bundle2.putString("contentUri", suggestParcelables$IntentParam.contentUri);
                    arrayList.add(bundle2);
                }
            }
            bundle.putParcelableArrayList("intentParams", arrayList);
        }
        bundle.putString("packageName", this.packageName);
        bundle.putString("className", this.className);
        bundle.putString("action", this.action);
        bundle.putString("uri", this.uri);
        bundle.putString("mimeType", this.mimeType);
        bundle.putInt("flags", this.flags);
        SuggestParcelables$IntentType suggestParcelables$IntentType = this.intentType;
        if (suggestParcelables$IntentType == null) {
            bundle.putBundle("intentType", (Bundle) null);
        } else {
            suggestParcelables$IntentType.getClass();
            Bundle bundle4 = new Bundle();
            bundle4.putInt("value", suggestParcelables$IntentType.value);
            bundle.putBundle("intentType", bundle4);
        }
        return bundle;
    }
}
