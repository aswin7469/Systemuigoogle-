package com.google.android.systemui.qs.launcher;

import android.view.View;
import com.android.systemui.plugins.qs.QSTile;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LauncherTileService$stub$1$handleClick$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ String $tileSpec;
    public final /* synthetic */ LauncherTileService$stub$1 this$0;

    public /* synthetic */ LauncherTileService$stub$1$handleClick$1(LauncherTileService$stub$1 launcherTileService$stub$1, String str, int i) {
        this.$r8$classId = i;
        this.this$0 = launcherTileService$stub$1;
        this.$tileSpec = str;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                LauncherTileService$stub$1 launcherTileService$stub$1 = this.this$0;
                String str = this.$tileSpec;
                launcherTileService$stub$1.getClass();
                QSTile tile = launcherTileService$stub$1.getTile(str, false);
                if (tile != null) {
                    tile.click((View) null);
                    return;
                }
                return;
            case 1:
                LauncherTileService$stub$1 launcherTileService$stub$12 = this.this$0;
                String str2 = this.$tileSpec;
                launcherTileService$stub$12.getClass();
                QSTile tile2 = launcherTileService$stub$12.getTile(str2, false);
                if (tile2 != null) {
                    List<QSTile.Callback> list = (List) launcherTileService$stub$12.this$0.callbacksMap.get(str2);
                    if (list != null) {
                        for (QSTile.Callback removeCallback : list) {
                            tile2.removeCallback(removeCallback);
                        }
                    }
                    tile2.setListening(launcherTileService$stub$12, false);
                    List list2 = (List) launcherTileService$stub$12.this$0.callbacksMap.get(str2);
                    if (list2 != null) {
                        list2.clear();
                    }
                    if (launcherTileService$stub$12.this$0.createdTilesMap.remove(str2, tile2)) {
                        tile2.destroy();
                        return;
                    }
                    return;
                }
                return;
            default:
                LauncherTileService$stub$1 launcherTileService$stub$13 = this.this$0;
                String str3 = this.$tileSpec;
                launcherTileService$stub$13.getClass();
                QSTile tile3 = launcherTileService$stub$13.getTile(str3, false);
                if (tile3 != null) {
                    tile3.longClick((View) null);
                    return;
                }
                return;
        }
    }
}
