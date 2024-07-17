package com.google.android.systemui;

import android.content.Context;
import android.provider.DeviceConfig;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.Dumpable;
import com.android.systemui.VendorServices;
import com.google.android.systemui.autorotate.AutorotateDataService;
import com.google.android.systemui.autorotate.AutorotateDataService$$ExternalSyntheticLambda0;
import com.google.android.systemui.coversheet.CoversheetService;
import com.google.android.systemui.elmyra.ElmyraService;
import com.google.android.systemui.elmyra.ServiceConfigurationGoogle;
import com.google.android.systemui.input.TouchContextService;
import com.google.android.systemui.screenprotector.ScreenProtectorNotifierService;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class GoogleServices extends VendorServices {
    public final AutorotateDataService mAutorotateDataService;
    public final Lazy mColumbusStarter;
    public final Context mContext;
    public final Lazy mServiceConfigurationGoogle;
    public final ArrayList mServices = new ArrayList();
    public final UiEventLogger mUiEventLogger;

    public GoogleServices(Context context, Lazy lazy, UiEventLogger uiEventLogger, Lazy lazy2, AutorotateDataService autorotateDataService) {
        this.mContext = context;
        this.mServiceConfigurationGoogle = lazy;
        this.mUiEventLogger = uiEventLogger;
        this.mColumbusStarter = lazy2;
        this.mAutorotateDataService = autorotateDataService;
    }

    public final void addService(Object obj) {
        if (obj != null) {
            this.mServices.add(obj);
        }
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.mServices;
            if (i < arrayList.size()) {
                if (arrayList.get(i) instanceof Dumpable) {
                    ((Dumpable) arrayList.get(i)).dump(printWriter, strArr);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public final void start() {
        Context context = this.mContext;
        addService(new DisplayCutoutEmulationAdapter(context));
        addService(new CoversheetService(context));
        AutorotateDataService autorotateDataService = this.mAutorotateDataService;
        autorotateDataService.mLatencyTracker = LatencyTracker.getInstance(autorotateDataService.mContext);
        autorotateDataService.readFlagsToControlSensorLogging();
        AutorotateDataService$$ExternalSyntheticLambda0 autorotateDataService$$ExternalSyntheticLambda0 = new AutorotateDataService$$ExternalSyntheticLambda0(autorotateDataService);
        autorotateDataService.mDeviceConfig.getClass();
        DeviceConfig.addOnPropertiesChangedListener("window_manager", autorotateDataService.mMainExecutor, autorotateDataService$$ExternalSyntheticLambda0);
        addService(autorotateDataService);
        if (context.getPackageManager().hasSystemFeature("android.hardware.context_hub") && context.getPackageManager().hasSystemFeature("android.hardware.sensor.assist")) {
            addService(new ElmyraService(context, (ServiceConfigurationGoogle) this.mServiceConfigurationGoogle.get(), this.mUiEventLogger));
        }
        if (context.getPackageManager().hasSystemFeature("com.google.android.feature.QUICK_TAP")) {
            addService(this.mColumbusStarter.get());
        }
        if (context.getResources().getBoolean(2131034174)) {
            addService(new ScreenProtectorNotifierService(context));
        }
        if (context.getResources().getBoolean(2131034196)) {
            addService(new TouchContextService(context));
        }
    }
}
