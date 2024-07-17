package com.google.android.systemui.columbus.legacy;

import com.google.android.systemui.columbus.ColumbusStarter;
import dagger.Lazy;
import java.io.PrintWriter;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class ColumbusServiceWrapper implements ColumbusStarter {
    public final Lazy columbusService;
    public final ColumbusSettings columbusSettings;
    public final ColumbusServiceWrapper$settingsChangeListener$1 settingsChangeListener;
    public boolean started;

    public ColumbusServiceWrapper(ColumbusSettings columbusSettings2, Lazy lazy, Lazy lazy2, Lazy lazy3) {
        this.columbusSettings = columbusSettings2;
        this.columbusService = lazy;
        ColumbusServiceWrapper$settingsChangeListener$1 columbusServiceWrapper$settingsChangeListener$1 = new ColumbusServiceWrapper$settingsChangeListener$1(this);
        this.settingsChangeListener = columbusServiceWrapper$settingsChangeListener$1;
        if (columbusSettings2.isColumbusEnabled()) {
            columbusSettings2.listeners.remove(columbusServiceWrapper$settingsChangeListener$1);
            this.started = true;
            lazy.get();
        } else {
            columbusSettings2.registerColumbusSettingsChangeListener(columbusServiceWrapper$settingsChangeListener$1);
            lazy2.get();
        }
        lazy3.get();
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        if (this.started) {
            ((ColumbusService) this.columbusService.get()).dump(printWriter, strArr);
        }
    }
}
