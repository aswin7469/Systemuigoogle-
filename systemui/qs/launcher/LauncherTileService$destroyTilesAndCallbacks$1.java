package com.google.android.systemui.qs.launcher;

import com.android.systemui.plugins.qs.QSTile;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LauncherTileService$destroyTilesAndCallbacks$1 implements Runnable {
    public final /* synthetic */ LauncherTileService this$0;

    public LauncherTileService$destroyTilesAndCallbacks$1(LauncherTileService launcherTileService) {
        this.this$0 = launcherTileService;
    }

    public final void run() {
        for (QSTile destroy : this.this$0.createdTilesMap.values()) {
            destroy.destroy();
        }
        this.this$0.createdTilesMap.clear();
        HashSet hashSet = new HashSet(this.this$0.callbacksMap.keySet());
        LauncherTileService launcherTileService = this.this$0;
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            LauncherTileService$stub$1 launcherTileService$stub$1 = launcherTileService.stub;
            launcherTileService$stub$1.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(launcherTileService$stub$1, (String) it.next(), 1));
        }
        this.this$0.callbacksMap.clear();
    }
}
