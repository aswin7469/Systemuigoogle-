package com.google.android.systemui.smartspace;

import android.content.Context;
import android.util.Log;
import androidx.appsearch.platformstorage.GlobalSearchSessionImpl;
import androidx.compose.foundation.text.ValidatingOffsetMapping$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.systemui.settings.UserTracker;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NextClockAlarmController$userChangedCallback$1 implements UserTracker.Callback {
    public final /* synthetic */ NextClockAlarmController this$0;

    public NextClockAlarmController$userChangedCallback$1(NextClockAlarmController nextClockAlarmController) {
        this.this$0 = nextClockAlarmController;
    }

    public final void onBeforeUserSwitching(int i) {
        NextClockAlarmController nextClockAlarmController = this.this$0;
        Context context = nextClockAlarmController.context;
        GlobalSearchSessionImpl globalSearchSessionImpl = null;
        if (context == null) {
            context = null;
        }
        ExifInterface$$ExternalSyntheticOutline0.m("onBeforeUserSwitching newUser=", i, ", oldUser=", context.getUserId(), "NextClockAlarmCtlr");
        NextClockAlarmController$observerCallback$1 nextClockAlarmController$observerCallback$1 = nextClockAlarmController.observerCallback;
        AppSearchController appSearchController = nextClockAlarmController.alarmAppSearchController;
        AlarmAppSearchController alarmAppSearchController = (AlarmAppSearchController) appSearchController;
        if (!((Boolean) alarmAppSearchController.isInitialized.getValue()).booleanValue()) {
            Log.w("AlarmAppSearchCtlr", "Session is not initialized yet!");
        } else {
            try {
                GlobalSearchSessionImpl globalSearchSessionImpl2 = alarmAppSearchController.searchSession;
                if (globalSearchSessionImpl2 == null) {
                    globalSearchSessionImpl2 = null;
                }
                globalSearchSessionImpl2.unregisterObserverCallback(nextClockAlarmController$observerCallback$1);
            } catch (Exception e) {
                Log.w("AlarmAppSearchCtlr", "Failed to  unregister the observer callback.", e);
            }
        }
        AlarmAppSearchController alarmAppSearchController2 = (AlarmAppSearchController) appSearchController;
        StateFlowImpl stateFlowImpl = alarmAppSearchController2.isInitialized;
        if (!((Boolean) stateFlowImpl.getValue()).booleanValue()) {
            Log.w("AlarmAppSearchCtlr", "Session is not initialized yet!");
            return;
        }
        GlobalSearchSessionImpl globalSearchSessionImpl3 = alarmAppSearchController2.searchSession;
        if (globalSearchSessionImpl3 != null) {
            globalSearchSessionImpl = globalSearchSessionImpl3;
        }
        globalSearchSessionImpl.close();
        stateFlowImpl.setValue(Boolean.FALSE);
        if (AlarmAppSearchController.DEBUG) {
            Log.d("AlarmAppSearchCtlr", "Session closed");
        }
    }

    public final void onUserChanged(int i, Context context) {
        boolean z = NextClockAlarmController.DEBUG;
        NextClockAlarmController nextClockAlarmController = this.this$0;
        if (z) {
            Context context2 = nextClockAlarmController.context;
            if (context2 == null) {
                context2 = null;
            }
            StringBuilder m = ValidatingOffsetMapping$$ExternalSyntheticOutline0.m("onUserChanged newUser=", i, ", oldUser=", context2.getUserId(), ", userContext=");
            m.append(context);
            Log.d("NextClockAlarmCtlr", m.toString());
        }
        if (nextClockAlarmController.isUserUnlocked$1()) {
            nextClockAlarmController.updateSession(context);
        }
    }
}
