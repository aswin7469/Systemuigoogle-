package com.google.android.systemui.qs.launcher;

import com.android.systemui.plugins.qs.QSTile;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LauncherTileService$stub$1$addTileListener$1 implements Runnable {
    public final /* synthetic */ ILauncherTileListener$Stub$Proxy $listener;
    public final /* synthetic */ String $tileSpec;
    public final /* synthetic */ LauncherTileService$stub$1 this$0;

    public LauncherTileService$stub$1$addTileListener$1(LauncherTileService$stub$1 launcherTileService$stub$1, String str, ILauncherTileListener$Stub$Proxy iLauncherTileListener$Stub$Proxy) {
        this.this$0 = launcherTileService$stub$1;
        this.$tileSpec = str;
        this.$listener = iLauncherTileListener$Stub$Proxy;
    }

    public final void run() {
        LauncherTileService$stub$1 launcherTileService$stub$1 = this.this$0;
        String str = this.$tileSpec;
        ILauncherTileListener$Stub$Proxy iLauncherTileListener$Stub$Proxy = this.$listener;
        QSTile tile = launcherTileService$stub$1.getTile(str, true);
        if (tile == null) {
            TileState tileState = new TileState();
            tileState.mUnSupported = true;
            tileState.mTileSpec = str;
            iLauncherTileListener$Stub$Proxy.onTileUpdated(tileState);
            return;
        }
        LauncherTileService launcherTileService = launcherTileService$stub$1.this$0;
        LauncherTileService$stub$1$addTileListenerInternal$callback$1 launcherTileService$stub$1$addTileListenerInternal$callback$1 = new LauncherTileService$stub$1$addTileListenerInternal$callback$1(str, launcherTileService, iLauncherTileListener$Stub$Proxy);
        if (launcherTileService.callbacksMap.get(str) == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(launcherTileService$stub$1$addTileListenerInternal$callback$1);
            launcherTileService$stub$1.this$0.callbacksMap.put(str, arrayList);
        } else {
            List list = (List) launcherTileService$stub$1.this$0.callbacksMap.get(str);
            if (list != null) {
                list.add(launcherTileService$stub$1$addTileListenerInternal$callback$1);
            }
        }
        tile.addCallback(launcherTileService$stub$1$addTileListenerInternal$callback$1);
        tile.setListening(launcherTileService$stub$1, true);
    }
}
