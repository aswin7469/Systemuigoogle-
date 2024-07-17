package com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public class EntitiesData implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Object();
    private final Map bitmapMap;
    private final SuggestParcelables$Entities entities;
    private final Map pendingIntentMap;

    /* renamed from: com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.EntitiesData$1  reason: invalid class name */
    /* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
    public final class AnonymousClass1 implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            return EntitiesData.read(parcel);
        }

        public final Object[] newArray(int i) {
            return new EntitiesData[i];
        }
    }

    private EntitiesData(SuggestParcelables$Entities suggestParcelables$Entities, Map map, Map map2) {
        this.entities = suggestParcelables$Entities;
        this.bitmapMap = map;
        this.pendingIntentMap = map2;
    }

    public static EntitiesData create(SuggestParcelables$Entities suggestParcelables$Entities) {
        return new EntitiesData(suggestParcelables$Entities, new HashMap(), new HashMap());
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Entities] */
    /* JADX WARNING: type inference failed for: r4v11, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ExtrasInfo, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v16, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$DebugInfo, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Entity, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r12v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$EntitySpan, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v40, types: [java.util.List, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Action, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r7v41 */
    /* JADX WARNING: type inference failed for: r13v6, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ActionGroup] */
    /* JADX WARNING: type inference failed for: r7v48 */
    public static EntitiesData read(Parcel parcel) {
        SuggestParcelables$DebugInfo suggestParcelables$DebugInfo;
        Parcel parcel2 = parcel;
        Bundle readBundle = parcel.readBundle();
        ? obj = new Object();
        if (readBundle.containsKey("id")) {
            obj.id = readBundle.getString("id");
        }
        if (readBundle.containsKey("success")) {
            obj.success = readBundle.getBoolean("success");
        }
        List list = null;
        if (readBundle.containsKey("entities")) {
            ArrayList parcelableArrayList = readBundle.getParcelableArrayList("entities");
            if (parcelableArrayList == null) {
                obj.entities = null;
            } else {
                obj.entities = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle = (Bundle) it.next();
                    if (bundle == null) {
                        obj.entities.add(list);
                    } else {
                        List list2 = obj.entities;
                        ? obj2 = new Object();
                        if (bundle.containsKey("id")) {
                            obj2.id = bundle.getString("id");
                        }
                        if (bundle.containsKey("actions")) {
                            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("actions");
                            if (parcelableArrayList2 == null) {
                                obj2.actions = list;
                            } else {
                                obj2.actions = new ArrayList(parcelableArrayList2.size());
                                Iterator it2 = parcelableArrayList2.iterator();
                                ? r7 = list;
                                while (it2.hasNext()) {
                                    Bundle bundle2 = (Bundle) it2.next();
                                    if (bundle2 == null) {
                                        obj2.actions.add(r7);
                                    } else {
                                        List list3 = obj2.actions;
                                        ? obj3 = new Object();
                                        if (bundle2.containsKey("id")) {
                                            obj3.id = bundle2.getString("id");
                                        }
                                        if (bundle2.containsKey("displayName")) {
                                            obj3.displayName = bundle2.getString("displayName");
                                        }
                                        if (bundle2.containsKey("mainAction")) {
                                            Bundle bundle3 = bundle2.getBundle("mainAction");
                                            if (bundle3 == null) {
                                                obj3.mainAction = r7;
                                            } else {
                                                obj3.mainAction = SuggestParcelables$Action.create(bundle3);
                                            }
                                        }
                                        if (bundle2.containsKey("alternateActions")) {
                                            ArrayList parcelableArrayList3 = bundle2.getParcelableArrayList("alternateActions");
                                            if (parcelableArrayList3 == null) {
                                                obj3.alternateActions = r7;
                                            } else {
                                                obj3.alternateActions = new ArrayList(parcelableArrayList3.size());
                                                Iterator it3 = parcelableArrayList3.iterator();
                                                while (it3.hasNext()) {
                                                    Bundle bundle4 = (Bundle) it3.next();
                                                    if (bundle4 == null) {
                                                        obj3.alternateActions.add((Object) null);
                                                    } else {
                                                        obj3.alternateActions.add(SuggestParcelables$Action.create(bundle4));
                                                    }
                                                }
                                            }
                                        }
                                        if (bundle2.containsKey("isHiddenAction")) {
                                            obj3.isHiddenAction = bundle2.getBoolean("isHiddenAction");
                                        }
                                        if (bundle2.containsKey("opaquePayload")) {
                                            obj3.opaquePayload = bundle2.getString("opaquePayload");
                                        }
                                        list3.add(obj3);
                                    }
                                    r7 = 0;
                                }
                            }
                        }
                        if (bundle.containsKey("entitySpans")) {
                            ArrayList parcelableArrayList4 = bundle.getParcelableArrayList("entitySpans");
                            if (parcelableArrayList4 == null) {
                                obj2.entitySpans = null;
                            } else {
                                obj2.entitySpans = new ArrayList(parcelableArrayList4.size());
                                Iterator it4 = parcelableArrayList4.iterator();
                                while (it4.hasNext()) {
                                    Bundle bundle5 = (Bundle) it4.next();
                                    if (bundle5 == null) {
                                        obj2.entitySpans.add((Object) null);
                                    } else {
                                        List list4 = obj2.entitySpans;
                                        ? obj4 = new Object();
                                        if (bundle5.containsKey("rects")) {
                                            ArrayList parcelableArrayList5 = bundle5.getParcelableArrayList("rects");
                                            if (parcelableArrayList5 == null) {
                                                obj4.rects = null;
                                            } else {
                                                obj4.rects = new ArrayList(parcelableArrayList5.size());
                                                Iterator it5 = parcelableArrayList5.iterator();
                                                while (it5.hasNext()) {
                                                    Bundle bundle6 = (Bundle) it5.next();
                                                    if (bundle6 == null) {
                                                        obj4.rects.add((Object) null);
                                                    } else {
                                                        obj4.rects.add(SuggestParcelables$ContentRect.create(bundle6));
                                                    }
                                                }
                                            }
                                        }
                                        if (bundle5.containsKey("selectionId")) {
                                            obj4.selectionId = bundle5.getString("selectionId");
                                        }
                                        if (bundle5.containsKey("rectIndices")) {
                                            obj4.rectIndices = bundle5.getIntegerArrayList("rectIndices");
                                        }
                                        list4.add(obj4);
                                    }
                                }
                            }
                        }
                        if (bundle.containsKey("searchQueryHint")) {
                            obj2.searchQueryHint = bundle.getString("searchQueryHint");
                        }
                        if (bundle.containsKey("annotationTypeName")) {
                            obj2.annotationTypeName = bundle.getString("annotationTypeName");
                        }
                        if (bundle.containsKey("annotationSourceName")) {
                            obj2.annotationSourceName = bundle.getString("annotationSourceName");
                        }
                        if (bundle.containsKey("verticalTypeName")) {
                            obj2.verticalTypeName = bundle.getString("verticalTypeName");
                        }
                        if (bundle.containsKey("annotationScore")) {
                            obj2.annotationScore = bundle.getFloat("annotationScore");
                        }
                        if (bundle.containsKey("contentGroupIndex")) {
                            obj2.contentGroupIndex = bundle.getInt("contentGroupIndex");
                        }
                        if (bundle.containsKey("selectionIndex")) {
                            obj2.selectionIndex = bundle.getInt("selectionIndex");
                        }
                        if (bundle.containsKey("isSmartSelection")) {
                            obj2.isSmartSelection = bundle.getBoolean("isSmartSelection");
                        }
                        if (bundle.containsKey("suggestedPresentationMode")) {
                            obj2.suggestedPresentationMode = bundle.getInt("suggestedPresentationMode");
                        }
                        if (bundle.containsKey("numWords")) {
                            obj2.numWords = bundle.getInt("numWords");
                        }
                        if (bundle.containsKey("startIndex")) {
                            obj2.startIndex = bundle.getInt("startIndex");
                        }
                        if (bundle.containsKey("endIndex")) {
                            obj2.endIndex = bundle.getInt("endIndex");
                        }
                        if (bundle.containsKey("opaquePayload")) {
                            obj2.opaquePayload = bundle.getString("opaquePayload");
                        }
                        if (bundle.containsKey("interactionType")) {
                            Bundle bundle7 = bundle.getBundle("interactionType");
                            if (bundle7 == null) {
                                obj2.interactionType = null;
                            } else {
                                obj2.interactionType = SuggestParcelables$InteractionType.create(bundle7);
                            }
                        }
                        if (bundle.containsKey("shouldStartForResult")) {
                            obj2.shouldStartForResult = bundle.getBoolean("shouldStartForResult");
                        }
                        if (bundle.containsKey("kgCollections")) {
                            obj2.kgCollections = bundle.getStringArrayList("kgCollections");
                        }
                        list2.add(obj2);
                    }
                    list = null;
                }
            }
        }
        if (!readBundle.containsKey("stats")) {
            suggestParcelables$DebugInfo = null;
        } else {
            Bundle bundle8 = readBundle.getBundle("stats");
            if (bundle8 == null) {
                suggestParcelables$DebugInfo = null;
                obj.stats = null;
            } else {
                suggestParcelables$DebugInfo = null;
                obj.stats = SuggestParcelables$Stats.create(bundle8);
            }
        }
        if (readBundle.containsKey("debugInfo")) {
            if (readBundle.getBundle("debugInfo") == null) {
                obj.debugInfo = suggestParcelables$DebugInfo;
            } else {
                obj.debugInfo = new Object();
            }
        }
        if (readBundle.containsKey("extrasInfo")) {
            Bundle bundle9 = readBundle.getBundle("extrasInfo");
            if (bundle9 == null) {
                obj.extrasInfo = null;
            } else {
                ? obj5 = new Object();
                if (bundle9.containsKey("containsPendingIntents")) {
                    obj5.containsPendingIntents = bundle9.getBoolean("containsPendingIntents");
                }
                if (bundle9.containsKey("containsBitmaps")) {
                    obj5.containsBitmaps = bundle9.getBoolean("containsBitmaps");
                }
                obj.extrasInfo = obj5;
            }
        }
        if (readBundle.containsKey("opaquePayload")) {
            obj.opaquePayload = readBundle.getString("opaquePayload");
        }
        if (readBundle.containsKey("setupInfo")) {
            Bundle bundle10 = readBundle.getBundle("setupInfo");
            if (bundle10 == null) {
                obj.setupInfo = null;
            } else {
                obj.setupInfo = SuggestParcelables$SetupInfo.create(bundle10);
            }
        }
        HashMap hashMap = new HashMap();
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo = obj.extrasInfo;
        if (suggestParcelables$ExtrasInfo != null && suggestParcelables$ExtrasInfo.containsBitmaps) {
            parcel2.readMap(hashMap, Bitmap.class.getClassLoader());
        }
        HashMap hashMap2 = new HashMap();
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo2 = obj.extrasInfo;
        if (suggestParcelables$ExtrasInfo2 != null && suggestParcelables$ExtrasInfo2.containsPendingIntents) {
            parcel2.readMap(hashMap2, PendingIntent.class.getClassLoader());
        }
        return create(obj, hashMap, hashMap2);
    }

    public int describeContents() {
        return 0;
    }

    public SuggestParcelables$Entities entities() {
        return this.entities;
    }

    public Bitmap getBitmap(String str) {
        return (Bitmap) this.bitmapMap.get(str);
    }

    public Map getBitmapMap() {
        return this.bitmapMap;
    }

    public PendingIntent getPendingIntent(String str) {
        return (PendingIntent) this.pendingIntentMap.get(str);
    }

    public Map getPendingIntentMap() {
        return this.pendingIntentMap;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle;
        String str;
        Iterator it;
        Iterator it2;
        Iterator it3;
        Iterator it4;
        String str2;
        Iterator it5;
        ArrayList arrayList;
        Iterator it6;
        Parcel parcel2 = parcel;
        SuggestParcelables$Entities suggestParcelables$Entities = this.entities;
        suggestParcelables$Entities.getClass();
        Bundle bundle2 = new Bundle();
        String str3 = "id";
        bundle2.putString(str3, suggestParcelables$Entities.id);
        bundle2.putBoolean("success", suggestParcelables$Entities.success);
        ArrayList arrayList2 = null;
        if (suggestParcelables$Entities.entities == null) {
            bundle2.putParcelableArrayList("entities", (ArrayList) null);
        } else {
            ArrayList arrayList3 = new ArrayList(((ArrayList) suggestParcelables$Entities.entities).size());
            Iterator it7 = suggestParcelables$Entities.entities.iterator();
            while (it7.hasNext()) {
                SuggestParcelables$Entity suggestParcelables$Entity = (SuggestParcelables$Entity) it7.next();
                if (suggestParcelables$Entity == null) {
                    arrayList3.add(arrayList2);
                    str = str3;
                    it = it7;
                } else {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString(str3, suggestParcelables$Entity.id);
                    if (suggestParcelables$Entity.actions == null) {
                        bundle3.putParcelableArrayList("actions", arrayList2);
                        str = str3;
                        it = it7;
                    } else {
                        ArrayList arrayList4 = new ArrayList(((ArrayList) suggestParcelables$Entity.actions).size());
                        Iterator it8 = suggestParcelables$Entity.actions.iterator();
                        while (it8.hasNext()) {
                            SuggestParcelables$ActionGroup suggestParcelables$ActionGroup = (SuggestParcelables$ActionGroup) it8.next();
                            if (suggestParcelables$ActionGroup == null) {
                                arrayList4.add(arrayList2);
                                str2 = str3;
                                it5 = it7;
                                it4 = it8;
                            } else {
                                Bundle bundle4 = new Bundle();
                                it5 = it7;
                                bundle4.putString(str3, suggestParcelables$ActionGroup.id);
                                str2 = str3;
                                bundle4.putString("displayName", suggestParcelables$ActionGroup.displayName);
                                SuggestParcelables$Action suggestParcelables$Action = suggestParcelables$ActionGroup.mainAction;
                                if (suggestParcelables$Action == null) {
                                    it4 = it8;
                                    arrayList = null;
                                    bundle4.putBundle("mainAction", (Bundle) null);
                                } else {
                                    it4 = it8;
                                    arrayList = null;
                                    bundle4.putBundle("mainAction", suggestParcelables$Action.writeToBundle());
                                }
                                if (suggestParcelables$ActionGroup.alternateActions == null) {
                                    bundle4.putParcelableArrayList("alternateActions", arrayList);
                                } else {
                                    ArrayList arrayList5 = new ArrayList(((ArrayList) suggestParcelables$ActionGroup.alternateActions).size());
                                    Iterator it9 = suggestParcelables$ActionGroup.alternateActions.iterator();
                                    while (it9.hasNext()) {
                                        SuggestParcelables$Action suggestParcelables$Action2 = (SuggestParcelables$Action) it9.next();
                                        if (suggestParcelables$Action2 == null) {
                                            it6 = it9;
                                            arrayList5.add((Object) null);
                                        } else {
                                            it6 = it9;
                                            arrayList5.add(suggestParcelables$Action2.writeToBundle());
                                        }
                                        it9 = it6;
                                    }
                                    bundle4.putParcelableArrayList("alternateActions", arrayList5);
                                }
                                bundle4.putBoolean("isHiddenAction", suggestParcelables$ActionGroup.isHiddenAction);
                                bundle4.putString("opaquePayload", suggestParcelables$ActionGroup.opaquePayload);
                                arrayList4.add(bundle4);
                            }
                            it7 = it5;
                            str3 = str2;
                            it8 = it4;
                            arrayList2 = null;
                        }
                        str = str3;
                        it = it7;
                        bundle3.putParcelableArrayList("actions", arrayList4);
                    }
                    if (suggestParcelables$Entity.entitySpans == null) {
                        bundle3.putParcelableArrayList("entitySpans", (ArrayList) null);
                    } else {
                        ArrayList arrayList6 = new ArrayList(((ArrayList) suggestParcelables$Entity.entitySpans).size());
                        Iterator it10 = suggestParcelables$Entity.entitySpans.iterator();
                        while (it10.hasNext()) {
                            SuggestParcelables$EntitySpan suggestParcelables$EntitySpan = (SuggestParcelables$EntitySpan) it10.next();
                            if (suggestParcelables$EntitySpan == null) {
                                arrayList6.add((Object) null);
                                it2 = it10;
                            } else {
                                Bundle bundle5 = new Bundle();
                                it2 = it10;
                                if (suggestParcelables$EntitySpan.rects == null) {
                                    bundle5.putParcelableArrayList("rects", (ArrayList) null);
                                } else {
                                    ArrayList arrayList7 = new ArrayList(((ArrayList) suggestParcelables$EntitySpan.rects).size());
                                    Iterator it11 = suggestParcelables$EntitySpan.rects.iterator();
                                    while (it11.hasNext()) {
                                        SuggestParcelables$ContentRect suggestParcelables$ContentRect = (SuggestParcelables$ContentRect) it11.next();
                                        if (suggestParcelables$ContentRect == null) {
                                            it3 = it11;
                                            arrayList7.add((Object) null);
                                        } else {
                                            it3 = it11;
                                            arrayList7.add(suggestParcelables$ContentRect.writeToBundle());
                                        }
                                        it11 = it3;
                                    }
                                    bundle5.putParcelableArrayList("rects", arrayList7);
                                }
                                bundle5.putString("selectionId", suggestParcelables$EntitySpan.selectionId);
                                if (suggestParcelables$EntitySpan.rectIndices == null) {
                                    bundle5.putIntegerArrayList("rectIndices", (ArrayList) null);
                                } else {
                                    bundle5.putIntegerArrayList("rectIndices", new ArrayList(suggestParcelables$EntitySpan.rectIndices));
                                }
                                arrayList6.add(bundle5);
                            }
                            it10 = it2;
                        }
                        bundle3.putParcelableArrayList("entitySpans", arrayList6);
                    }
                    bundle3.putString("searchQueryHint", suggestParcelables$Entity.searchQueryHint);
                    bundle3.putString("annotationTypeName", suggestParcelables$Entity.annotationTypeName);
                    bundle3.putString("annotationSourceName", suggestParcelables$Entity.annotationSourceName);
                    bundle3.putString("verticalTypeName", suggestParcelables$Entity.verticalTypeName);
                    bundle3.putFloat("annotationScore", suggestParcelables$Entity.annotationScore);
                    bundle3.putInt("contentGroupIndex", suggestParcelables$Entity.contentGroupIndex);
                    bundle3.putInt("selectionIndex", suggestParcelables$Entity.selectionIndex);
                    bundle3.putBoolean("isSmartSelection", suggestParcelables$Entity.isSmartSelection);
                    bundle3.putInt("suggestedPresentationMode", suggestParcelables$Entity.suggestedPresentationMode);
                    bundle3.putInt("numWords", suggestParcelables$Entity.numWords);
                    bundle3.putInt("startIndex", suggestParcelables$Entity.startIndex);
                    bundle3.putInt("endIndex", suggestParcelables$Entity.endIndex);
                    bundle3.putString("opaquePayload", suggestParcelables$Entity.opaquePayload);
                    SuggestParcelables$InteractionType suggestParcelables$InteractionType = suggestParcelables$Entity.interactionType;
                    if (suggestParcelables$InteractionType == null) {
                        bundle3.putBundle("interactionType", (Bundle) null);
                    } else {
                        Bundle bundle6 = new Bundle();
                        bundle6.putInt("value", suggestParcelables$InteractionType.value);
                        bundle3.putBundle("interactionType", bundle6);
                    }
                    bundle3.putBoolean("shouldStartForResult", suggestParcelables$Entity.shouldStartForResult);
                    if (suggestParcelables$Entity.kgCollections == null) {
                        bundle3.putStringArrayList("kgCollections", (ArrayList) null);
                    } else {
                        bundle3.putStringArrayList("kgCollections", new ArrayList(suggestParcelables$Entity.kgCollections));
                    }
                    arrayList3.add(bundle3);
                }
                it7 = it;
                str3 = str;
                arrayList2 = null;
            }
            bundle2.putParcelableArrayList("entities", arrayList3);
        }
        SuggestParcelables$Stats suggestParcelables$Stats = suggestParcelables$Entities.stats;
        if (suggestParcelables$Stats == null) {
            bundle = null;
            bundle2.putBundle("stats", (Bundle) null);
        } else {
            bundle = null;
            bundle2.putBundle("stats", suggestParcelables$Stats.writeToBundle());
        }
        if (suggestParcelables$Entities.debugInfo == null) {
            bundle2.putBundle("debugInfo", bundle);
        } else {
            bundle2.putBundle("debugInfo", new Bundle());
        }
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo = suggestParcelables$Entities.extrasInfo;
        if (suggestParcelables$ExtrasInfo == null) {
            bundle2.putBundle("extrasInfo", bundle);
        } else {
            Bundle bundle7 = new Bundle();
            bundle7.putBoolean("containsPendingIntents", suggestParcelables$ExtrasInfo.containsPendingIntents);
            bundle7.putBoolean("containsBitmaps", suggestParcelables$ExtrasInfo.containsBitmaps);
            bundle2.putBundle("extrasInfo", bundle7);
        }
        bundle2.putString("opaquePayload", suggestParcelables$Entities.opaquePayload);
        SuggestParcelables$SetupInfo suggestParcelables$SetupInfo = suggestParcelables$Entities.setupInfo;
        if (suggestParcelables$SetupInfo == null) {
            bundle2.putBundle("setupInfo", (Bundle) null);
        } else {
            bundle2.putBundle("setupInfo", suggestParcelables$SetupInfo.writeToBundle());
        }
        bundle2.writeToParcel(parcel2, 0);
        SuggestParcelables$ExtrasInfo suggestParcelables$ExtrasInfo2 = this.entities.extrasInfo;
        if (suggestParcelables$ExtrasInfo2 != null) {
            if (suggestParcelables$ExtrasInfo2.containsBitmaps) {
                parcel2.writeMap(this.bitmapMap);
            }
            if (this.entities.extrasInfo.containsPendingIntents) {
                parcel2.writeMap(this.pendingIntentMap);
            }
        }
    }

    public static EntitiesData create(SuggestParcelables$Entities suggestParcelables$Entities, Map map, Map map2) {
        return new EntitiesData(suggestParcelables$Entities, map, map2);
    }
}
