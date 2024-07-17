package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.provider.Settings;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.util.time.SystemClock;
import com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BatteryDefenderNotificationHandler {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long POST_NOTIFICATION_THRESHOLD_MILLIS = Duration.m1721getInWholeMillisecondsimpl(DurationKt.toDuration(10, DurationUnit.MINUTES));
    public final ContextScope backgroundCoroutineScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public int batteryLevel = -1;
    public final Context context;
    public boolean dockDefendEnabled;
    public final Lazy dockDefenderNotification$delegate = LazyKt__LazyKt.lazy(new BatteryDefenderNotificationHandler$dockDefenderNotification$2(this));
    public final Lazy dwellTempDefenderNotification$delegate = LazyKt__LazyKt.lazy(new BatteryDefenderNotificationHandler$dwellTempDefenderNotification$2(this));
    public final GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapper;
    public boolean inDefenderSection;
    public final CoroutineDispatcher mainDispatcher;
    public final NotificationManager notificationManager;
    public final Lazy sharedPreferences$delegate = LazyKt__LazyKt.lazy(new BatteryDefenderNotificationHandler$sharedPreferences$2(this));
    public final SystemClock systemClock;
    public final UiEventLogger uiEventLogger;

    static {
        int i = Duration.$r8$clinit;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.android.systemui.util.time.SystemClock, java.lang.Object] */
    public BatteryDefenderNotificationHandler(Context context2, UiEventLogger uiEventLogger2, Executor executor) {
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        MainCoroutineDispatcher mainCoroutineDispatcher = MainDispatcherLoader.dispatcher;
        ? obj = new Object();
        ? obj2 = new Object();
        this.context = context2;
        this.uiEventLogger = uiEventLogger2;
        this.mainDispatcher = mainCoroutineDispatcher;
        this.notificationManager = (NotificationManager) context2.getSystemService(NotificationManager.class);
        this.googleBatteryManagerWrapper = obj;
        this.systemClock = obj2;
        this.dockDefendEnabled = context2.getResources().getBoolean(2131034197);
        CoroutineDispatcher from = ExecutorsKt.from(executor);
        this.backgroundDispatcher = from;
        this.backgroundCoroutineScope = CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(from, SupervisorKt.SupervisorJob$default()));
    }

    public static final Object access$bypassDefenderMode(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, int i, Continuation continuation) {
        if (i == 4) {
            Settings.Global.putInt(batteryDefenderNotificationHandler.context.getContentResolver(), "dock_defender_bypass", 1);
        } else {
            batteryDefenderNotificationHandler.getClass();
        }
        BatteryDefenderNotificationHandler$bypassDefenderMode$2 batteryDefenderNotificationHandler$bypassDefenderMode$2 = new BatteryDefenderNotificationHandler$bypassDefenderMode$2(i);
        batteryDefenderNotificationHandler.getClass();
        Object withContext = BuildersKt.withContext(batteryDefenderNotificationHandler.backgroundDispatcher, new BatteryDefenderNotificationHandler$withGoogleBattery$2(batteryDefenderNotificationHandler, batteryDefenderNotificationHandler$bypassDefenderMode$2, (Continuation) null), continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (withContext != coroutineSingletons) {
            withContext = unit;
        }
        if (withContext == coroutineSingletons) {
            return withContext;
        }
        return unit;
    }

    public final DwellTempDefenderNotification getDwellTempDefenderNotification() {
        return (DwellTempDefenderNotification) this.dwellTempDefenderNotification$delegate.getValue();
    }

    public final void setDockDefenderEnabled(boolean z) {
        this.dockDefendEnabled = z;
    }
}
