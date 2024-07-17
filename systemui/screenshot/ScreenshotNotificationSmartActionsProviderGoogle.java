package com.google.android.systemui.screenshot;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.screenshot.ScreenshotNotificationSmartActionsProvider;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$FeedbackBatch;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOp;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.FeedbackParcelables$ScreenshotOpStatus;
import com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.SuggestParcelables$InteractionType;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$$ExternalSyntheticLambda0;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$$ExternalSyntheticLambda1;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient$$ExternalSyntheticLambda2;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ScreenshotNotificationSmartActionsProviderGoogle extends ScreenshotNotificationSmartActionsProvider {
    public static final ImmutableMap SCREENSHOT_INTERACTION_TYPE_MAP;
    public static final ImmutableMap SCREENSHOT_OP_MAP;
    public static final ImmutableMap SCREENSHOT_OP_STATUS_MAP;
    public final ContentSuggestionsServiceClient mClient;

    static {
        int i = ImmutableMap.$r8$clinit;
        ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
        builder.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOp.RETRIEVE_SMART_ACTIONS, FeedbackParcelables$ScreenshotOp.RETRIEVE_SMART_ACTIONS);
        builder.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOp.REQUEST_SMART_ACTIONS, FeedbackParcelables$ScreenshotOp.REQUEST_SMART_ACTIONS);
        builder.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOp.WAIT_FOR_SMART_ACTIONS, FeedbackParcelables$ScreenshotOp.WAIT_FOR_SMART_ACTIONS);
        SCREENSHOT_OP_MAP = builder.buildOrThrow();
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder(4);
        builder2.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.SUCCESS, FeedbackParcelables$ScreenshotOpStatus.SUCCESS);
        builder2.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.ERROR, FeedbackParcelables$ScreenshotOpStatus.ERROR);
        builder2.put(ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.TIMEOUT, FeedbackParcelables$ScreenshotOpStatus.TIMEOUT);
        SCREENSHOT_OP_STATUS_MAP = builder2.buildOrThrow();
        ImmutableMap.Builder builder3 = new ImmutableMap.Builder(4);
        builder3.put(ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType.REGULAR_SMART_ACTIONS, SuggestParcelables$InteractionType.SCREENSHOT_NOTIFICATION);
        builder3.put(ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType.QUICK_SHARE_ACTION, SuggestParcelables$InteractionType.QUICK_SHARE);
        SCREENSHOT_INTERACTION_TYPE_MAP = builder3.buildOrThrow();
    }

    public ScreenshotNotificationSmartActionsProviderGoogle(Context context, Executor executor, Handler handler) {
        this.mClient = new ContentSuggestionsServiceClient(context, executor, handler);
    }

    public void completeFuture(Bundle bundle, CompletableFuture completableFuture) {
        if (bundle.containsKey("ScreenshotNotificationActions")) {
            completableFuture.complete(bundle.getParcelableArrayList("ScreenshotNotificationActions"));
        } else {
            completableFuture.complete(Collections.emptyList());
        }
    }

    /* JADX WARNING: type inference failed for: r1v14, types: [com.google.android.apps.miphone.aiai.matchmaker.overview.api.generatedv2.InteractionContextParcelables$InteractionContext, java.lang.Object] */
    public final CompletableFuture getActions(String str, Uri uri, Bitmap bitmap, ComponentName componentName, ScreenshotNotificationSmartActionsProvider.ScreenshotSmartActionType screenshotSmartActionType, UserHandle userHandle) {
        CompletableFuture completableFuture = new CompletableFuture();
        if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
            Bitmap.Config config = bitmap.getConfig();
            Log.e("ScreenshotActionsGoogle", "Bitmap expected: Hardware, Bitmap found: " + config + ". Returning empty list.");
            return CompletableFuture.completedFuture(Collections.emptyList());
        }
        final long uptimeMillis = SystemClock.uptimeMillis();
        Log.d("ScreenshotActionsGoogle", "Calling AiAi to obtain screenshot notification smart actions.");
        String packageName = componentName.getPackageName();
        String className = componentName.getClassName();
        ImmutableMap immutableMap = SCREENSHOT_INTERACTION_TYPE_MAP;
        Object obj = SuggestParcelables$InteractionType.SCREENSHOT_NOTIFICATION;
        Object obj2 = immutableMap.get(screenshotSmartActionType);
        if (obj2 != null) {
            obj = obj2;
        }
        SuggestParcelables$InteractionType suggestParcelables$InteractionType = (SuggestParcelables$InteractionType) obj;
        final CompletableFuture completableFuture2 = completableFuture;
        final String str2 = str;
        AnonymousClass1 r0 = new ContentSuggestionsServiceWrapper$BundleCallback() {
            public final void onResult(Bundle bundle) {
                ScreenshotNotificationSmartActionsProviderGoogle.this.completeFuture(bundle, completableFuture2);
                long uptimeMillis = SystemClock.uptimeMillis() - uptimeMillis;
                Log.d("ScreenshotActionsGoogle", String.format("Total time taken to get smart actions: %d ms", new Object[]{Long.valueOf(uptimeMillis)}));
                ScreenshotNotificationSmartActionsProviderGoogle.this.notifyOp(str2, ScreenshotNotificationSmartActionsProvider.ScreenshotOp.RETRIEVE_SMART_ACTIONS, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus.SUCCESS, uptimeMillis);
            }
        };
        ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.mClient;
        if (!contentSuggestionsServiceClient.isAiAiVersionSupported) {
            r0.onResult(Bundle.EMPTY);
        } else {
            int nextInt = ContentSuggestionsServiceClient.random.nextInt();
            long currentTimeMillis = System.currentTimeMillis();
            contentSuggestionsServiceClient.bundleUtils.getClass();
            Bundle bundle = new Bundle();
            bundle.putInt("CONTEXT_IMAGE_BUNDLE_VERSION_KEY", 1);
            bundle.putBoolean("CONTEXT_IMAGE_PRIMARY_TASK_KEY", true);
            bundle.putString("CONTEXT_IMAGE_PACKAGE_NAME_KEY", packageName);
            bundle.putString("CONTEXT_IMAGE_ACTIVITY_NAME_KEY", className);
            bundle.putLong("CONTEXT_IMAGE_CAPTURE_TIME_MS_KEY", currentTimeMillis);
            bundle.putParcelable("CONTEXT_IMAGE_BITMAP_URI_KEY", uri);
            bundle.putParcelable("android.contentsuggestions.extra.BITMAP", bitmap);
            ? obj3 = new Object();
            obj3.interactionType = suggestParcelables$InteractionType;
            obj3.disallowCopyPaste = false;
            obj3.versionCode = 1;
            obj3.isPrimaryTask = true;
            contentSuggestionsServiceClient.serviceWrapper.asyncExecutor.execute(new ContentSuggestionsServiceClient$$ExternalSyntheticLambda1(contentSuggestionsServiceClient, nextInt, bundle, packageName, className, currentTimeMillis, obj3, userHandle, uri, r0));
        }
        return completableFuture;
    }

    public final void notifyAction(String str, String str2, boolean z, Intent intent) {
        ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.mClient;
        contentSuggestionsServiceClient.getClass();
        ContentSuggestionsServiceClient$$ExternalSyntheticLambda0 contentSuggestionsServiceClient$$ExternalSyntheticLambda0 = new ContentSuggestionsServiceClient$$ExternalSyntheticLambda0(contentSuggestionsServiceClient, str, str2, z, intent);
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = contentSuggestionsServiceClient.serviceWrapper;
        contentSuggestionsServiceWrapperImpl.getClass();
        contentSuggestionsServiceWrapperImpl.loggingExecutor.execute(new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1(contentSuggestionsServiceWrapperImpl, contentSuggestionsServiceClient$$ExternalSyntheticLambda0, str, (FeedbackParcelables$FeedbackBatch) null));
    }

    public final void notifyOp(String str, ScreenshotNotificationSmartActionsProvider.ScreenshotOp screenshotOp, ScreenshotNotificationSmartActionsProvider.ScreenshotOpStatus screenshotOpStatus, long j) {
        ImmutableMap immutableMap = SCREENSHOT_OP_MAP;
        Object obj = FeedbackParcelables$ScreenshotOp.OP_UNKNOWN;
        Object obj2 = immutableMap.get(screenshotOp);
        if (obj2 != null) {
            obj = obj2;
        }
        FeedbackParcelables$ScreenshotOp feedbackParcelables$ScreenshotOp = (FeedbackParcelables$ScreenshotOp) obj;
        ImmutableMap immutableMap2 = SCREENSHOT_OP_STATUS_MAP;
        Object obj3 = FeedbackParcelables$ScreenshotOpStatus.OP_STATUS_UNKNOWN;
        Object obj4 = immutableMap2.get(screenshotOpStatus);
        if (obj4 != null) {
            obj3 = obj4;
        }
        ContentSuggestionsServiceClient contentSuggestionsServiceClient = this.mClient;
        contentSuggestionsServiceClient.getClass();
        ContentSuggestionsServiceClient$$ExternalSyntheticLambda2 contentSuggestionsServiceClient$$ExternalSyntheticLambda2 = new ContentSuggestionsServiceClient$$ExternalSyntheticLambda2(contentSuggestionsServiceClient, str, feedbackParcelables$ScreenshotOp, (FeedbackParcelables$ScreenshotOpStatus) obj3, j);
        ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = contentSuggestionsServiceClient.serviceWrapper;
        contentSuggestionsServiceWrapperImpl.getClass();
        contentSuggestionsServiceWrapperImpl.loggingExecutor.execute(new ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda1(contentSuggestionsServiceWrapperImpl, contentSuggestionsServiceClient$$ExternalSyntheticLambda2, str, (FeedbackParcelables$FeedbackBatch) null));
    }
}
