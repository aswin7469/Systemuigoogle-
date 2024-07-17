package com.google.android.apps.miphone.aiai.matchmaker.overview.common;

import android.os.Bundle;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppActionSuggestion;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppIcon;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppIconType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppPackage;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$ContentGroup;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Contents;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$SearchSuggestion;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Selection;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ParserParcelables$ParsedViewHierarchy;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ContentRect;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$DebugInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$SetupInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Stats;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BundleUtils {
    public static Bundle createClassificationsRequest(String str, String str2, int i, long j, Bundle bundle, InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext, ContentParcelables$Contents contentParcelables$Contents) {
        String str3;
        Bundle bundle2;
        ContentParcelables$Contents contentParcelables$Contents2;
        Bundle bundle3;
        Bundle bundle4;
        Bundle bundle5;
        String str4;
        String str5;
        Iterator it;
        Bundle bundle6;
        Bundle bundle7;
        String str6;
        Iterator it2;
        String str7;
        Bundle bundle8;
        Bundle bundle9;
        Iterator it3;
        Iterator it4;
        Bundle bundle10;
        ContentParcelables$Contents contentParcelables$Contents3 = contentParcelables$Contents;
        Bundle bundle11 = new Bundle();
        bundle11.putString("PackageName", str);
        bundle11.putString("ActivityName", str2);
        bundle11.putInt("TaskId", i);
        bundle11.putLong("CaptureTimestampMs", j);
        bundle11.putBundle("AssistBundle", bundle);
        Bundle bundle12 = new Bundle();
        String str8 = "id";
        bundle12.putString(str8, contentParcelables$Contents3.id);
        bundle12.putLong("screenSessionId", contentParcelables$Contents3.screenSessionId);
        ArrayList arrayList = null;
        String str9 = "opaquePayload";
        if (contentParcelables$Contents3.contentGroups == null) {
            bundle12.putParcelableArrayList("contentGroups", (ArrayList) null);
            bundle2 = bundle11;
            str3 = str9;
            contentParcelables$Contents2 = contentParcelables$Contents3;
            bundle3 = bundle12;
        } else {
            ArrayList arrayList2 = new ArrayList(((ArrayList) contentParcelables$Contents3.contentGroups).size());
            Iterator it5 = contentParcelables$Contents3.contentGroups.iterator();
            while (it5.hasNext()) {
                ContentParcelables$ContentGroup contentParcelables$ContentGroup = (ContentParcelables$ContentGroup) it5.next();
                if (contentParcelables$ContentGroup == null) {
                    arrayList2.add(arrayList);
                    bundle6 = bundle11;
                    bundle5 = bundle12;
                    str5 = str8;
                    str4 = str9;
                    it = it5;
                } else {
                    Bundle bundle13 = new Bundle();
                    if (contentParcelables$ContentGroup.contentRects == null) {
                        bundle13.putParcelableArrayList("contentRects", arrayList);
                    } else {
                        ArrayList arrayList3 = new ArrayList(((ArrayList) contentParcelables$ContentGroup.contentRects).size());
                        for (SuggestParcelables$ContentRect suggestParcelables$ContentRect : contentParcelables$ContentGroup.contentRects) {
                            if (suggestParcelables$ContentRect == null) {
                                arrayList3.add(arrayList);
                            } else {
                                arrayList3.add(suggestParcelables$ContentRect.writeToBundle());
                            }
                        }
                        bundle13.putParcelableArrayList("contentRects", arrayList3);
                    }
                    if (contentParcelables$ContentGroup.selections == null) {
                        bundle13.putParcelableArrayList("selections", arrayList);
                        bundle6 = bundle11;
                        it = it5;
                    } else {
                        ArrayList arrayList4 = new ArrayList(((ArrayList) contentParcelables$ContentGroup.selections).size());
                        Iterator it6 = contentParcelables$ContentGroup.selections.iterator();
                        while (it6.hasNext()) {
                            ContentParcelables$Selection contentParcelables$Selection = (ContentParcelables$Selection) it6.next();
                            if (contentParcelables$Selection == null) {
                                arrayList4.add(arrayList);
                                bundle10 = bundle11;
                                it4 = it5;
                                it3 = it6;
                            } else {
                                Bundle bundle14 = new Bundle();
                                it4 = it5;
                                it3 = it6;
                                if (contentParcelables$Selection.rectIndices == null) {
                                    bundle14.putIntegerArrayList("rectIndices", (ArrayList) null);
                                    bundle10 = bundle11;
                                } else {
                                    bundle10 = bundle11;
                                    bundle14.putIntegerArrayList("rectIndices", new ArrayList(contentParcelables$Selection.rectIndices));
                                }
                                bundle14.putString(str8, contentParcelables$Selection.id);
                                bundle14.putBoolean("isSmartSelection", contentParcelables$Selection.isSmartSelection);
                                bundle14.putInt("suggestedPresentationMode", contentParcelables$Selection.suggestedPresentationMode);
                                bundle14.putString(str9, contentParcelables$Selection.opaquePayload);
                                SuggestParcelables$InteractionType suggestParcelables$InteractionType = contentParcelables$Selection.interactionType;
                                if (suggestParcelables$InteractionType == null) {
                                    bundle14.putBundle("interactionType", (Bundle) null);
                                } else {
                                    Bundle bundle15 = new Bundle();
                                    bundle15.putInt("value", suggestParcelables$InteractionType.value);
                                    bundle14.putBundle("interactionType", bundle15);
                                }
                                bundle14.putInt("contentGroupIndex", contentParcelables$Selection.contentGroupIndex);
                                arrayList4.add(bundle14);
                            }
                            it5 = it4;
                            it6 = it3;
                            bundle11 = bundle10;
                            arrayList = null;
                        }
                        bundle6 = bundle11;
                        it = it5;
                        bundle13.putParcelableArrayList("selections", arrayList4);
                    }
                    bundle13.putString("text", contentParcelables$ContentGroup.text);
                    bundle13.putInt("numLines", contentParcelables$ContentGroup.numLines);
                    if (contentParcelables$ContentGroup.searchSuggestions == null) {
                        bundle13.putParcelableArrayList("searchSuggestions", (ArrayList) null);
                        bundle5 = bundle12;
                        str5 = str8;
                        str4 = str9;
                    } else {
                        ArrayList arrayList5 = new ArrayList(((ArrayList) contentParcelables$ContentGroup.searchSuggestions).size());
                        Iterator it7 = contentParcelables$ContentGroup.searchSuggestions.iterator();
                        while (it7.hasNext()) {
                            ContentParcelables$SearchSuggestion contentParcelables$SearchSuggestion = (ContentParcelables$SearchSuggestion) it7.next();
                            if (contentParcelables$SearchSuggestion == null) {
                                arrayList5.add((Object) null);
                                bundle7 = bundle12;
                                str7 = str8;
                                str6 = str9;
                                it2 = it7;
                            } else {
                                Bundle bundle16 = new Bundle();
                                ContentParcelables$AppActionSuggestion contentParcelables$AppActionSuggestion = contentParcelables$SearchSuggestion.appActionSuggestion;
                                if (contentParcelables$AppActionSuggestion == null) {
                                    bundle16.putBundle("appActionSuggestion", (Bundle) null);
                                    str7 = str8;
                                    it2 = it7;
                                } else {
                                    Bundle bundle17 = new Bundle();
                                    str7 = str8;
                                    it2 = it7;
                                    bundle17.putString("displayText", contentParcelables$AppActionSuggestion.displayText);
                                    bundle17.putString("subtitle", contentParcelables$AppActionSuggestion.subtitle);
                                    bundle16.putBundle("appActionSuggestion", bundle17);
                                }
                                ContentParcelables$AppIcon contentParcelables$AppIcon = contentParcelables$SearchSuggestion.appIcon;
                                if (contentParcelables$AppIcon == null) {
                                    bundle16.putBundle("appIcon", (Bundle) null);
                                    bundle7 = bundle12;
                                    str6 = str9;
                                } else {
                                    Bundle bundle18 = new Bundle();
                                    str6 = str9;
                                    bundle18.putString("iconUri", contentParcelables$AppIcon.iconUri);
                                    ContentParcelables$AppPackage contentParcelables$AppPackage = contentParcelables$AppIcon.appPackage;
                                    if (contentParcelables$AppPackage == null) {
                                        bundle7 = bundle12;
                                        bundle18.putBundle("appPackage", (Bundle) null);
                                    } else {
                                        bundle7 = bundle12;
                                        switch (contentParcelables$AppPackage.$r8$classId) {
                                            case 0:
                                                bundle9 = new Bundle();
                                                bundle9.putString("packageName", contentParcelables$AppPackage.packageName);
                                                break;
                                            default:
                                                bundle9 = new Bundle();
                                                bundle9.putString("deeplinkUri", contentParcelables$AppPackage.packageName);
                                                break;
                                        }
                                        bundle18.putBundle("appPackage", bundle9);
                                    }
                                    ContentParcelables$AppIconType contentParcelables$AppIconType = contentParcelables$AppIcon.appIconType;
                                    if (contentParcelables$AppIconType == null) {
                                        bundle18.putBundle("appIconType", (Bundle) null);
                                    } else {
                                        Bundle bundle19 = new Bundle();
                                        bundle19.putInt("value", contentParcelables$AppIconType.value);
                                        bundle18.putBundle("appIconType", bundle19);
                                    }
                                    bundle16.putBundle("appIcon", bundle18);
                                }
                                ContentParcelables$AppPackage contentParcelables$AppPackage2 = contentParcelables$SearchSuggestion.executionInfo;
                                if (contentParcelables$AppPackage2 == null) {
                                    bundle16.putBundle("executionInfo", (Bundle) null);
                                } else {
                                    switch (contentParcelables$AppPackage2.$r8$classId) {
                                        case 0:
                                            bundle8 = new Bundle();
                                            bundle8.putString("packageName", contentParcelables$AppPackage2.packageName);
                                            break;
                                        default:
                                            bundle8 = new Bundle();
                                            bundle8.putString("deeplinkUri", contentParcelables$AppPackage2.packageName);
                                            break;
                                    }
                                    bundle16.putBundle("executionInfo", bundle8);
                                }
                                bundle16.putFloat("confScore", contentParcelables$SearchSuggestion.confScore);
                                arrayList5.add(bundle16);
                            }
                            str8 = str7;
                            it7 = it2;
                            str9 = str6;
                            bundle12 = bundle7;
                            ContentParcelables$Contents contentParcelables$Contents4 = contentParcelables$Contents;
                        }
                        bundle5 = bundle12;
                        str5 = str8;
                        str4 = str9;
                        bundle13.putParcelableArrayList("searchSuggestions", arrayList5);
                    }
                    arrayList2.add(bundle13);
                }
                it5 = it;
                str8 = str5;
                str9 = str4;
                bundle12 = bundle5;
                ContentParcelables$Contents contentParcelables$Contents5 = contentParcelables$Contents;
                bundle11 = bundle6;
                arrayList = null;
            }
            bundle2 = bundle11;
            bundle3 = bundle12;
            str3 = str9;
            bundle3.putParcelableArrayList("contentGroups", arrayList2);
            contentParcelables$Contents2 = contentParcelables$Contents;
        }
        SuggestParcelables$Stats suggestParcelables$Stats = contentParcelables$Contents2.stats;
        if (suggestParcelables$Stats == null) {
            bundle4 = null;
            bundle3.putBundle("stats", (Bundle) null);
        } else {
            bundle4 = null;
            bundle3.putBundle("stats", suggestParcelables$Stats.writeToBundle());
        }
        if (contentParcelables$Contents2.debugInfo == null) {
            bundle3.putBundle("debugInfo", bundle4);
        } else {
            bundle3.putBundle("debugInfo", new Bundle());
        }
        bundle3.putString(str3, contentParcelables$Contents2.opaquePayload);
        SuggestParcelables$SetupInfo suggestParcelables$SetupInfo = contentParcelables$Contents2.setupInfo;
        if (suggestParcelables$SetupInfo == null) {
            bundle3.putBundle("setupInfo", (Bundle) null);
        } else {
            bundle3.putBundle("setupInfo", suggestParcelables$SetupInfo.writeToBundle());
        }
        bundle3.putString("contentUri", contentParcelables$Contents2.contentUri);
        Bundle bundle20 = bundle2;
        bundle20.putBundle("Contents", bundle3);
        if (interactionContextParcelables$InteractionContext == null) {
            bundle20.putBundle("InteractionContext", (Bundle) null);
        } else {
            bundle20.putBundle("InteractionContext", interactionContextParcelables$InteractionContext.writeToBundle());
        }
        bundle20.putInt("Version", 4);
        bundle20.putInt("BundleTypedVersion", 3);
        return bundle20;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.os.Bundle createFeedbackRequest(com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch r18) {
        /*
            r0 = r18
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            r2 = 0
            java.lang.String r3 = "FeedbackBatch"
            if (r0 != 0) goto L_0x0012
            r1.putBundle(r3, r2)
            r0 = r1
            goto L_0x0178
        L_0x0012:
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            java.util.List r5 = r0.feedback
            java.lang.String r6 = "feedback"
            if (r5 != 0) goto L_0x0025
            r4.putParcelableArrayList(r6, r2)
            r17 = r1
            r7 = r3
            goto L_0x0165
        L_0x0025:
            java.util.ArrayList r5 = new java.util.ArrayList
            java.util.List r7 = r0.feedback
            int r7 = r7.size()
            r5.<init>(r7)
            java.util.List r7 = r0.feedback
            java.util.Iterator r7 = r7.iterator()
        L_0x0036:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x015f
            java.lang.Object r8 = r7.next()
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$Feedback r8 = (com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$Feedback) r8
            if (r8 != 0) goto L_0x004e
            r5.add(r2)
            r17 = r1
            r16 = r7
            r7 = r3
            goto L_0x0158
        L_0x004e:
            android.os.Bundle r9 = new android.os.Bundle
            r9.<init>()
            java.lang.Object r10 = r8.feedback
            boolean r10 = r10 instanceof com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotFeedback
            if (r10 == 0) goto L_0x0069
            java.lang.String r10 = "feedback#tag"
            r11 = 11
            r9.putInt(r10, r11)
            java.lang.Object r8 = r8.feedback
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotFeedback r8 = (com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotFeedback) r8
            if (r8 != 0) goto L_0x0070
            r9.putBundle(r6, r2)
        L_0x0069:
            r17 = r1
            r16 = r7
            r7 = r3
            goto L_0x013e
        L_0x0070:
            android.os.Bundle r10 = new android.os.Bundle
            r10.<init>()
            java.lang.Object r11 = r8.screenshotFeedback
            boolean r11 = r11 instanceof com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpFeedback
            java.lang.String r12 = "screenshotFeedback#tag"
            java.lang.String r13 = "screenshotFeedback"
            if (r11 == 0) goto L_0x008c
            r11 = 2
            r10.putInt(r12, r11)
            java.lang.Object r11 = r8.screenshotFeedback
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpFeedback r11 = (com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpFeedback) r11
            if (r11 != 0) goto L_0x0092
            r10.putBundle(r13, r2)
        L_0x008c:
            r17 = r1
            r16 = r7
            r7 = r3
            goto L_0x00da
        L_0x0092:
            android.os.Bundle r14 = new android.os.Bundle
            r14.<init>()
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOp r15 = r11.op
            java.lang.String r2 = "value"
            r16 = r7
            java.lang.String r7 = "op"
            if (r15 != 0) goto L_0x00a8
            r17 = r1
            r1 = 0
            r14.putBundle(r7, r1)
            goto L_0x00b7
        L_0x00a8:
            r17 = r1
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            int r15 = r15.value
            r1.putInt(r2, r15)
            r14.putBundle(r7, r1)
        L_0x00b7:
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpStatus r1 = r11.status
            java.lang.String r7 = "status"
            if (r1 != 0) goto L_0x00c2
            r15 = 0
            r14.putBundle(r7, r15)
            goto L_0x00cf
        L_0x00c2:
            android.os.Bundle r15 = new android.os.Bundle
            r15.<init>()
            int r1 = r1.value
            r15.putInt(r2, r1)
            r14.putBundle(r7, r15)
        L_0x00cf:
            java.lang.String r1 = "durationMs"
            r7 = r3
            long r2 = r11.durationMs
            r14.putLong(r1, r2)
            r10.putBundle(r13, r14)
        L_0x00da:
            java.lang.Object r1 = r8.screenshotFeedback
            boolean r1 = r1 instanceof com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotActionFeedback
            if (r1 == 0) goto L_0x0134
            r1 = 3
            r10.putInt(r12, r1)
            java.lang.Object r1 = r8.screenshotFeedback
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotActionFeedback r1 = (com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotActionFeedback) r1
            if (r1 != 0) goto L_0x00ef
            r2 = 0
            r10.putBundle(r13, r2)
            goto L_0x0134
        L_0x00ef:
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.String r3 = "actionType"
            java.lang.String r11 = r1.actionType
            r2.putString(r3, r11)
            java.lang.String r3 = "isSmartActions"
            boolean r11 = r1.isSmartActions
            r2.putBoolean(r3, r11)
            com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$QuickShareInfo r1 = r1.quickShareInfo
            java.lang.String r3 = "quickShareInfo"
            if (r1 != 0) goto L_0x010d
            r11 = 0
            r2.putBundle(r3, r11)
            goto L_0x0131
        L_0x010d:
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
            java.lang.String r12 = "contentUri"
            java.lang.String r14 = r1.contentUri
            r11.putString(r12, r14)
            java.lang.String r12 = "targetPackageName"
            java.lang.String r14 = r1.targetPackageName
            r11.putString(r12, r14)
            java.lang.String r12 = "targetClassName"
            java.lang.String r14 = r1.targetClassName
            r11.putString(r12, r14)
            java.lang.String r12 = "targetShortcutId"
            java.lang.String r1 = r1.targetShortcutId
            r11.putString(r12, r1)
            r2.putBundle(r3, r11)
        L_0x0131:
            r10.putBundle(r13, r2)
        L_0x0134:
            java.lang.String r1 = "screenshotId"
            java.lang.String r2 = r8.screenshotId
            r10.putString(r1, r2)
            r9.putBundle(r6, r10)
        L_0x013e:
            java.lang.String r1 = "id"
            r2 = 0
            r9.putString(r1, r2)
            java.lang.String r1 = "timestampMs"
            r10 = 0
            r9.putLong(r1, r10)
            java.lang.String r1 = "suggestionAction"
            r9.putBundle(r1, r2)
            java.lang.String r1 = "interactionContext"
            r9.putBundle(r1, r2)
            r5.add(r9)
        L_0x0158:
            r3 = r7
            r7 = r16
            r1 = r17
            goto L_0x0036
        L_0x015f:
            r17 = r1
            r7 = r3
            r4.putParcelableArrayList(r6, r5)
        L_0x0165:
            java.lang.String r1 = "screenSessionId"
            long r2 = r0.screenSessionId
            r4.putLong(r1, r2)
            java.lang.String r1 = "overviewSessionId"
            java.lang.String r0 = r0.overviewSessionId
            r4.putString(r1, r0)
            r0 = r17
            r0.putBundle(r7, r4)
        L_0x0178:
            java.lang.String r1 = "Version"
            r2 = 4
            r0.putInt(r1, r2)
            java.lang.String r1 = "BundleTypedVersion"
            r2 = 6
            r0.putInt(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils.createFeedbackRequest(com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch):android.os.Bundle");
    }

    public static Bundle createSelectionsRequest(String str, String str2, int i, long j, InteractionContextParcelables$InteractionContext interactionContextParcelables$InteractionContext, Bundle bundle, ParserParcelables$ParsedViewHierarchy parserParcelables$ParsedViewHierarchy) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("PackageName", str);
        bundle2.putString("ActivityName", str2);
        bundle2.putInt("TaskId", i);
        bundle2.putLong("CaptureTimestampMs", j);
        if (interactionContextParcelables$InteractionContext == null) {
            bundle2.putBundle("InteractionContext", (Bundle) null);
        } else {
            bundle2.putBundle("InteractionContext", interactionContextParcelables$InteractionContext.writeToBundle());
        }
        bundle2.putBundle("AssistBundle", bundle);
        if (parserParcelables$ParsedViewHierarchy == null) {
            bundle2.putBundle("ParsedViewHierarchy", (Bundle) null);
        } else {
            Bundle bundle3 = new Bundle();
            bundle3.putLong("acquisitionStartTime", 0);
            bundle3.putLong("acquisitionEndTime", 0);
            bundle3.putBoolean("isHomeActivity", false);
            bundle3.putParcelableArrayList("windows", (ArrayList) null);
            bundle3.putBoolean("hasKnownIssues", false);
            bundle3.putString("packageName", (String) null);
            bundle3.putString("activityClassName", (String) null);
            bundle3.putBundle("insetsRect", (Bundle) null);
            bundle2.putBundle("ParsedViewHierarchy", bundle3);
        }
        bundle2.putInt("Version", 4);
        bundle2.putInt("BundleTypedVersion", 3);
        return bundle2;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Contents, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r2v10, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$DebugInfo, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.util.List, java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType] */
    /* JADX WARNING: type inference failed for: r6v2 */
    /* JADX WARNING: type inference failed for: r8v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$ContentGroup, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v3, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppActionSuggestion, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppPackage, java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppIcon] */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: type inference failed for: r11v0, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$SearchSuggestion, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r13v3, types: [java.lang.Object, com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppIcon] */
    /* JADX WARNING: type inference failed for: r13v4, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$AppActionSuggestion, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r12v13, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Selection, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.ContentParcelables$Contents, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r6v17 */
    public static ContentParcelables$Contents extractContents(Bundle bundle) {
        SuggestParcelables$DebugInfo suggestParcelables$DebugInfo;
        ContentParcelables$AppIconType contentParcelables$AppIconType;
        Bundle bundle2 = bundle.getBundle("Contents");
        if (bundle2 == null) {
            return new Object();
        }
        ? obj = new Object();
        if (bundle2.containsKey("id")) {
            obj.id = bundle2.getString("id");
        }
        if (bundle2.containsKey("screenSessionId")) {
            obj.screenSessionId = bundle2.getLong("screenSessionId");
        }
        ? r6 = 0;
        if (bundle2.containsKey("contentGroups")) {
            ArrayList parcelableArrayList = bundle2.getParcelableArrayList("contentGroups");
            if (parcelableArrayList == null) {
                obj.contentGroups = null;
            } else {
                obj.contentGroups = new ArrayList(parcelableArrayList.size());
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    Bundle bundle3 = (Bundle) it.next();
                    if (bundle3 == null) {
                        obj.contentGroups.add(r6);
                    } else {
                        List list = obj.contentGroups;
                        ? obj2 = new Object();
                        if (bundle3.containsKey("contentRects")) {
                            ArrayList parcelableArrayList2 = bundle3.getParcelableArrayList("contentRects");
                            if (parcelableArrayList2 == null) {
                                obj2.contentRects = r6;
                            } else {
                                obj2.contentRects = new ArrayList(parcelableArrayList2.size());
                                Iterator it2 = parcelableArrayList2.iterator();
                                while (it2.hasNext()) {
                                    Bundle bundle4 = (Bundle) it2.next();
                                    if (bundle4 == null) {
                                        obj2.contentRects.add(r6);
                                    } else {
                                        obj2.contentRects.add(SuggestParcelables$ContentRect.create(bundle4));
                                    }
                                }
                            }
                        }
                        if (bundle3.containsKey("selections")) {
                            ArrayList parcelableArrayList3 = bundle3.getParcelableArrayList("selections");
                            if (parcelableArrayList3 == null) {
                                obj2.selections = r6;
                            } else {
                                obj2.selections = new ArrayList(parcelableArrayList3.size());
                                Iterator it3 = parcelableArrayList3.iterator();
                                while (it3.hasNext()) {
                                    Bundle bundle5 = (Bundle) it3.next();
                                    if (bundle5 == null) {
                                        obj2.selections.add(r6);
                                    } else {
                                        List list2 = obj2.selections;
                                        ? obj3 = new Object();
                                        if (bundle5.containsKey("rectIndices")) {
                                            obj3.rectIndices = bundle5.getIntegerArrayList("rectIndices");
                                        }
                                        if (bundle5.containsKey("id")) {
                                            obj3.id = bundle5.getString("id");
                                        }
                                        if (bundle5.containsKey("isSmartSelection")) {
                                            obj3.isSmartSelection = bundle5.getBoolean("isSmartSelection");
                                        }
                                        if (bundle5.containsKey("suggestedPresentationMode")) {
                                            obj3.suggestedPresentationMode = bundle5.getInt("suggestedPresentationMode");
                                        }
                                        if (bundle5.containsKey("opaquePayload")) {
                                            obj3.opaquePayload = bundle5.getString("opaquePayload");
                                        }
                                        if (bundle5.containsKey("interactionType")) {
                                            Bundle bundle6 = bundle5.getBundle("interactionType");
                                            if (bundle6 == null) {
                                                obj3.interactionType = r6;
                                            } else {
                                                obj3.interactionType = SuggestParcelables$InteractionType.create(bundle6);
                                            }
                                        }
                                        if (bundle5.containsKey("contentGroupIndex")) {
                                            obj3.contentGroupIndex = bundle5.getInt("contentGroupIndex");
                                        }
                                        list2.add(obj3);
                                    }
                                }
                            }
                        }
                        if (bundle3.containsKey("text")) {
                            obj2.text = bundle3.getString("text");
                        }
                        if (bundle3.containsKey("numLines")) {
                            obj2.numLines = bundle3.getInt("numLines");
                        }
                        if (bundle3.containsKey("searchSuggestions")) {
                            ArrayList parcelableArrayList4 = bundle3.getParcelableArrayList("searchSuggestions");
                            if (parcelableArrayList4 == null) {
                                obj2.searchSuggestions = r6;
                            } else {
                                obj2.searchSuggestions = new ArrayList(parcelableArrayList4.size());
                                Iterator it4 = parcelableArrayList4.iterator();
                                ? r62 = r6;
                                while (it4.hasNext()) {
                                    Bundle bundle7 = (Bundle) it4.next();
                                    if (bundle7 == null) {
                                        obj2.searchSuggestions.add(r62);
                                    } else {
                                        List list3 = obj2.searchSuggestions;
                                        ? obj4 = new Object();
                                        if (bundle7.containsKey("appActionSuggestion")) {
                                            Bundle bundle8 = bundle7.getBundle("appActionSuggestion");
                                            if (bundle8 == null) {
                                                obj4.appActionSuggestion = r62;
                                            } else {
                                                ? obj5 = new Object();
                                                if (bundle8.containsKey("displayText")) {
                                                    obj5.displayText = bundle8.getString("displayText");
                                                }
                                                if (bundle8.containsKey("subtitle")) {
                                                    obj5.subtitle = bundle8.getString("subtitle");
                                                }
                                                obj4.appActionSuggestion = obj5;
                                            }
                                        }
                                        if (bundle7.containsKey("appIcon")) {
                                            Bundle bundle9 = bundle7.getBundle("appIcon");
                                            if (bundle9 == null) {
                                                obj4.appIcon = r62;
                                            } else {
                                                ? obj6 = new Object();
                                                if (bundle9.containsKey("iconUri")) {
                                                    obj6.iconUri = bundle9.getString("iconUri");
                                                }
                                                if (bundle9.containsKey("appPackage")) {
                                                    Bundle bundle10 = bundle9.getBundle("appPackage");
                                                    if (bundle10 == null) {
                                                        obj6.appPackage = r62;
                                                    } else {
                                                        obj6.appPackage = new ContentParcelables$AppPackage(0, bundle10);
                                                    }
                                                }
                                                if (bundle9.containsKey("appIconType")) {
                                                    Bundle bundle11 = bundle9.getBundle("appIconType");
                                                    if (bundle11 == null) {
                                                        obj6.appIconType = null;
                                                    } else {
                                                        int i = bundle11.getInt("value");
                                                        if (i == 0) {
                                                            contentParcelables$AppIconType = ContentParcelables$AppIconType.UNDEFINED;
                                                        } else if (i == 1) {
                                                            contentParcelables$AppIconType = ContentParcelables$AppIconType.URI;
                                                        } else if (i == 2) {
                                                            contentParcelables$AppIconType = ContentParcelables$AppIconType.DRAWABLE;
                                                        } else {
                                                            contentParcelables$AppIconType = null;
                                                        }
                                                        obj6.appIconType = contentParcelables$AppIconType;
                                                    }
                                                }
                                                obj4.appIcon = obj6;
                                            }
                                        }
                                        if (bundle7.containsKey("executionInfo")) {
                                            Bundle bundle12 = bundle7.getBundle("executionInfo");
                                            if (bundle12 == null) {
                                                obj4.executionInfo = null;
                                            } else {
                                                obj4.executionInfo = new ContentParcelables$AppPackage(1, bundle12);
                                            }
                                        }
                                        if (bundle7.containsKey("confScore")) {
                                            obj4.confScore = bundle7.getFloat("confScore");
                                        }
                                        list3.add(obj4);
                                    }
                                    r62 = 0;
                                }
                            }
                        }
                        list.add(obj2);
                    }
                    r6 = 0;
                }
            }
        }
        if (!bundle2.containsKey("stats")) {
            suggestParcelables$DebugInfo = null;
        } else {
            Bundle bundle13 = bundle2.getBundle("stats");
            if (bundle13 == null) {
                suggestParcelables$DebugInfo = null;
                obj.stats = null;
            } else {
                suggestParcelables$DebugInfo = null;
                obj.stats = SuggestParcelables$Stats.create(bundle13);
            }
        }
        if (bundle2.containsKey("debugInfo")) {
            if (bundle2.getBundle("debugInfo") == null) {
                obj.debugInfo = suggestParcelables$DebugInfo;
            } else {
                obj.debugInfo = new Object();
            }
        }
        if (bundle2.containsKey("opaquePayload")) {
            obj.opaquePayload = bundle2.getString("opaquePayload");
        }
        if (bundle2.containsKey("setupInfo")) {
            Bundle bundle14 = bundle2.getBundle("setupInfo");
            if (bundle14 == null) {
                obj.setupInfo = null;
            } else {
                obj.setupInfo = SuggestParcelables$SetupInfo.create(bundle14);
            }
        }
        if (bundle2.containsKey("contentUri")) {
            obj.contentUri = bundle2.getString("contentUri");
        }
        return obj;
    }
}
