package com.google.android.systemui.smartspace;

import android.app.PendingIntent;
import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.uitemplatedata.TapAction;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.FalsingManager;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BcSmartSpaceUtil {
    public static FalsingManager sFalsingManager;
    public static BcSmartspaceDataPlugin.IntentStarter sIntentStarter;

    public static String getDimensionRatio(Bundle bundle) {
        if (bundle.containsKey("imageRatioWidth") && bundle.containsKey("imageRatioHeight")) {
            int i = bundle.getInt("imageRatioWidth");
            int i2 = bundle.getInt("imageRatioHeight");
            if (i > 0 && i2 > 0) {
                return i + ":" + i2;
            }
        }
        return null;
    }

    public static Drawable getIconDrawableWithCustomSize(Icon icon, Context context, int i) {
        Drawable drawable;
        if (icon == null) {
            return null;
        }
        if (icon.getType() == 1 || icon.getType() == 5) {
            drawable = new BitmapDrawable(context.getResources(), icon.getBitmap());
        } else {
            drawable = icon.loadDrawable(context);
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, i, i);
        }
        return drawable;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0051 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getLoggingDisplaySurface(float r6, java.lang.String r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r7.hashCode()
            r2 = 3208415(0x30f4df, float:4.495947E-39)
            r3 = -1
            r4 = 1
            r5 = 2
            if (r1 == r2) goto L_0x002f
            r2 = 95848451(0x5b68803, float:1.716516E-35)
            if (r1 == r2) goto L_0x0025
            r2 = 1792850263(0x6adcb957, float:1.3341946E26)
            if (r1 == r2) goto L_0x001b
            goto L_0x0039
        L_0x001b:
            java.lang.String r1 = "lockscreen"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0039
            r7 = r5
            goto L_0x003a
        L_0x0025:
            java.lang.String r1 = "dream"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0039
            r7 = r0
            goto L_0x003a
        L_0x002f:
            java.lang.String r1 = "home"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0039
            r7 = r4
            goto L_0x003a
        L_0x0039:
            r7 = r3
        L_0x003a:
            if (r7 == 0) goto L_0x0051
            if (r7 == r4) goto L_0x0050
            if (r7 == r5) goto L_0x0041
            return r0
        L_0x0041:
            r7 = 1065353216(0x3f800000, float:1.0)
            int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r7 != 0) goto L_0x0049
            r6 = 3
            return r6
        L_0x0049:
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x004f
            return r5
        L_0x004f:
            return r3
        L_0x0050:
            return r4
        L_0x0051:
            r6 = 5
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.smartspace.BcSmartSpaceUtil.getLoggingDisplaySurface(float, java.lang.String):int");
    }

    public static Intent getOpenCalendarIntent() {
        return new Intent("android.intent.action.VIEW").setData(ContentUris.appendId(CalendarContract.CONTENT_URI.buildUpon().appendPath("time"), System.currentTimeMillis()).build()).addFlags(270532608);
    }

    public static void setOnClickListener(View view, SmartspaceTarget smartspaceTarget, SmartspaceAction smartspaceAction, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, String str, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        setOnClickListener(view, smartspaceTarget, smartspaceAction, smartspaceEventNotifier, str, bcSmartspaceCardLoggingInfo, 0);
    }

    public static void setOnClickListener(View view, SmartspaceTarget smartspaceTarget, SmartspaceAction smartspaceAction, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, String str, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo, int i) {
        final String str2 = str;
        if (view == null || smartspaceAction == null) {
            Log.e(str2, "No tap action can be set up");
            return;
        }
        boolean z = smartspaceAction.getExtras() != null && smartspaceAction.getExtras().getBoolean("show_on_lockscreen");
        boolean z2 = smartspaceAction.getIntent() == null && smartspaceAction.getPendingIntent() == null;
        BcSmartspaceDataPlugin.IntentStarter intentStarter = sIntentStarter;
        if (intentStarter == null) {
            intentStarter = new BcSmartspaceDataPlugin.IntentStarter() {
                public final void startIntent(View view, Intent intent, boolean z) {
                    try {
                        view.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException | NullPointerException | SecurityException e) {
                        Log.e(str2, "Cannot invoke smartspace intent", e);
                    }
                }

                public final void startPendingIntent(View view, PendingIntent pendingIntent, boolean z) {
                    try {
                        pendingIntent.send();
                    } catch (PendingIntent.CanceledException e) {
                        Log.e(str2, "Cannot invoke canceled smartspace intent", e);
                    }
                }
            };
        }
        view.setOnClickListener(new BcSmartSpaceUtil$$ExternalSyntheticLambda1(bcSmartspaceCardLoggingInfo, i, z2, intentStarter, smartspaceAction, z, smartspaceEventNotifier, str, smartspaceTarget));
    }

    public static void setOnClickListener(View view, SmartspaceTarget smartspaceTarget, TapAction tapAction, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, String str, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        setOnClickListener(view, smartspaceTarget, tapAction, smartspaceEventNotifier, str, bcSmartspaceCardLoggingInfo, 0);
    }

    public static void setOnClickListener(View view, SmartspaceTarget smartspaceTarget, TapAction tapAction, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, String str, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo, int i) {
        if (view == null || tapAction == null) {
            Log.e(str, "No tap action can be set up");
            return;
        }
        view.setOnClickListener(new BcSmartSpaceUtil$$ExternalSyntheticLambda0(bcSmartspaceCardLoggingInfo, i, str, tapAction, tapAction.shouldShowOnLockscreen(), smartspaceEventNotifier, smartspaceTarget));
    }
}
