package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.text.TextUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.EntitiesData;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Action;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$ActionGroup;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$Entity;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$IntentInfo;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$IntentType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ContentSuggestionsServiceClient {
    public static final Random random = new Random();
    public final BundleUtils bundleUtils;
    public final Context context;
    public final boolean isAiAiVersionSupported;
    public final ContentSuggestionsServiceWrapperImpl serviceWrapper;
    public final UserManager userManager;

    /* renamed from: -$$Nest$smgenerateNotificationAction  reason: not valid java name */
    public static Notification.Action m819$$Nest$smgenerateNotificationAction(Context context2, SuggestParcelables$Entity suggestParcelables$Entity, EntitiesData entitiesData, Uri uri) {
        String str;
        String str2;
        List list = suggestParcelables$Entity.actions;
        Icon icon = null;
        if (list == null) {
            return null;
        }
        int i = Utils.$r8$clinit;
        if (list.isEmpty()) {
            return null;
        }
        List list2 = suggestParcelables$Entity.actions;
        list2.getClass();
        int i2 = 0;
        SuggestParcelables$Action suggestParcelables$Action = ((SuggestParcelables$ActionGroup) ((ArrayList) list2).get(0)).mainAction;
        if (suggestParcelables$Action == null || suggestParcelables$Action.id == null) {
            return null;
        }
        if (uri != null && suggestParcelables$Action.hasProxiedIntentInfo) {
            SuggestParcelables$IntentInfo suggestParcelables$IntentInfo = suggestParcelables$Action.proxiedIntentInfo;
            suggestParcelables$IntentInfo.getClass();
            if (suggestParcelables$IntentInfo.intentType == SuggestParcelables$IntentType.LENS) {
                context2.grantUriPermission("com.google.android.googlequicksearchbox", uri, 1);
            }
        }
        String str3 = suggestParcelables$Action.id;
        str3.getClass();
        Bitmap bitmap = entitiesData.getBitmap(str3);
        String str4 = suggestParcelables$Action.id;
        str4.getClass();
        PendingIntent pendingIntent = entitiesData.getPendingIntent(str4);
        if (pendingIntent == null || bitmap == null) {
            return null;
        }
        String str5 = suggestParcelables$Action.displayName;
        str5.getClass();
        String str6 = suggestParcelables$Action.fullDisplayName;
        str6.getClass();
        String str7 = suggestParcelables$Entity.searchQueryHint;
        str7.getClass();
        String[] strArr = {str5, str6, str7};
        while (true) {
            if (i2 >= 3) {
                str = null;
                break;
            }
            str = strArr[i2];
            if (!TextUtils.isEmpty(str)) {
                break;
            }
            i2++;
        }
        if (str == null) {
            return null;
        }
        RemoteAction remoteAction = new RemoteAction(Icon.createWithBitmap(bitmap), str, str, pendingIntent);
        if (TextUtils.isEmpty(suggestParcelables$Entity.searchQueryHint)) {
            str2 = "Smart Action";
        } else {
            str2 = suggestParcelables$Entity.searchQueryHint;
        }
        str2.getClass();
        if (remoteAction.shouldShowIcon()) {
            icon = remoteAction.getIcon();
        }
        Bundle bundle = new Bundle();
        bundle.putString("action_type", str2);
        bundle.putFloat("action_score", 1.0f);
        return new Notification.Action.Builder(icon, remoteAction.getTitle(), remoteAction.getActionIntent()).setContextual(true).addExtras(bundle).build();
    }

    /* JADX WARNING: type inference failed for: r6v4, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.common.BundleUtils, java.lang.Object] */
    public ContentSuggestionsServiceClient(Context context2, Executor executor, Handler handler) {
        this.context = context2;
        Objects.requireNonNull(handler);
        SuggestController suggestController = new SuggestController(context2, context2, executor, executor);
        SuggestController$$ExternalSyntheticLambda0 suggestController$$ExternalSyntheticLambda0 = new SuggestController$$ExternalSyntheticLambda0(suggestController);
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = suggestController.wrapper;
        contentSuggestionsServiceWrapperImpl.asyncExecutor.execute(suggestController$$ExternalSyntheticLambda0);
        this.serviceWrapper = contentSuggestionsServiceWrapperImpl;
        boolean z = false;
        try {
            if (context2.getPackageManager().getPackageInfo("com.google.android.as", 0).getLongVersionCode() >= 660780) {
                z = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e("Error obtaining package info: ", e);
        }
        this.isAiAiVersionSupported = z;
        this.bundleUtils = new Object();
        this.userManager = (UserManager) context2.getSystemService(UserManager.class);
    }
}
