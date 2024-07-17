package com.google.android.systemui.qs.launcher;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import com.android.systemui.plugins.qs.QSTile;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class LauncherTileService$stub$1 extends Binder implements IInterface {
    public final /* synthetic */ LauncherTileService this$0;

    public LauncherTileService$stub$1(LauncherTileService launcherTileService) {
        this.this$0 = launcherTileService;
        attachInterface(this, "com.google.android.systemui.qs.launcher.ILauncherTileService");
    }

    public final /* bridge */ /* synthetic */ int getMaxTransactionId() {
        return 3;
    }

    public final QSTile getTile(String str, boolean z) {
        Object obj;
        Iterator it = this.this$0.qsHost.getTiles().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((QSTile) obj).getTileSpec(), str)) {
                break;
            }
        }
        QSTile qSTile = (QSTile) obj;
        if (qSTile != null) {
            return qSTile;
        }
        if (str.startsWith("custom(")) {
            Log.w("LauncherTileService", String.format("Can't create a custom tile %s.", Arrays.copyOf(new Object[]{str}, 1)));
            return null;
        }
        QSTile qSTile2 = (QSTile) this.this$0.createdTilesMap.get(str);
        if (qSTile2 != null) {
            return qSTile2;
        }
        if (!z) {
            Log.i("LauncherTileService", String.format("Tile %s should be already created.", Arrays.copyOf(new Object[]{str}, 1)));
            return null;
        }
        QSTile createTile = this.this$0.qsHost.createTile(str);
        if (createTile != null) {
            createTile.setTileSpec(str);
            if (!createTile.isAvailable()) {
                createTile.destroy();
                Log.e("LauncherTileService", String.format("Tile %s is not available.", Arrays.copyOf(new Object[]{str}, 1)));
                return null;
            }
            this.this$0.createdTilesMap.put(str, createTile);
        } else {
            Log.e("LauncherTileService", String.format("The created tile %s is null.", Arrays.copyOf(new Object[]{str}, 1)));
        }
        return createTile;
    }

    /* renamed from: getTransactionName$com$google$android$systemui$qs$launcher$ILauncherTileService$Stub */
    public final String getTransactionName(int i) {
        if (i == 1) {
            return "addTileListener";
        }
        if (i == 2) {
            return "clearTileListeners";
        }
        if (i == 3) {
            return "handleClick";
        }
        if (i != 4) {
            return null;
        }
        return "handleLongClick";
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.google.android.systemui.qs.launcher.ILauncherTileListener$Stub$Proxy, java.lang.Object] */
    /* renamed from: onTransact$com$google$android$systemui$qs$launcher$ILauncherTileService$Stub */
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        ILauncherTileListener iLauncherTileListener;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.qs.launcher.ILauncherTileService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.qs.launcher.ILauncherTileService");
            return true;
        }
        if (i == 1) {
            String readString = parcel.readString();
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder == null) {
                iLauncherTileListener = null;
            } else {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.systemui.qs.launcher.ILauncherTileListener");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof ILauncherTileListener)) {
                    ? obj = new Object();
                    obj.mRemote = readStrongBinder;
                    iLauncherTileListener = obj;
                } else {
                    iLauncherTileListener = (ILauncherTileListener) queryLocalInterface;
                }
            }
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new LauncherTileService$stub$1$addTileListener$1(this, readString, iLauncherTileListener));
            parcel2.writeNoException();
        } else if (i == 2) {
            String readString2 = parcel.readString();
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(this, readString2, 1));
            parcel2.writeNoException();
        } else if (i == 3) {
            String readString3 = parcel.readString();
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(this, readString3, 0));
            parcel2.writeNoException();
        } else if (i != 4) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            String readString4 = parcel.readString();
            parcel.enforceNoDataAvail();
            this.this$0.executor.execute(new LauncherTileService$stub$1$handleClick$1(this, readString4, 2));
            parcel2.writeNoException();
        }
        return true;
    }

    public final IBinder asBinder() {
        return this;
    }
}
