package com.google.android.systemui.qs.launcher;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.android.systemui.qs.QSHost;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LauncherTileService extends Service {
    public final Map callbacksMap = new LinkedHashMap();
    public final Map createdTilesMap = new LinkedHashMap();
    public final Executor executor;
    public final QSHost qsHost;
    public final LauncherTileService$stub$1 stub = new LauncherTileService$stub$1(this);

    public LauncherTileService(QSHost qSHost, Executor executor2) {
        this.qsHost = qSHost;
        this.executor = executor2;
    }

    public final IBinder onBind(Intent intent) {
        return this.stub;
    }

    public final void onDestroy() {
        this.executor.execute(new LauncherTileService$destroyTilesAndCallbacks$1(this));
        super.onDestroy();
    }
}
