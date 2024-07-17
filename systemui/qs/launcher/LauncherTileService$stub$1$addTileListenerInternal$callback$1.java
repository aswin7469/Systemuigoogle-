package com.google.android.systemui.qs.launcher;

import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.people.PeopleSpaceUtils;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.qs.tileimpl.SubtitleArrayMapping;
import java.util.HashMap;
import java.util.function.Supplier;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class LauncherTileService$stub$1$addTileListenerInternal$callback$1 implements QSTile.Callback {
    public final /* synthetic */ ILauncherTileListener$Stub$Proxy $listener;
    public final /* synthetic */ String $tileSpec;
    public final /* synthetic */ LauncherTileService this$0;

    public LauncherTileService$stub$1$addTileListenerInternal$callback$1(String str, LauncherTileService launcherTileService, ILauncherTileListener$Stub$Proxy iLauncherTileListener$Stub$Proxy) {
        this.$tileSpec = str;
        this.this$0 = launcherTileService;
        this.$listener = iLauncherTileListener$Stub$Proxy;
    }

    public final void onStateChanged(QSTile.State state) {
        QSTile.Icon icon;
        TileState tileState = new TileState();
        tileState.mTileSpec = this.$tileSpec;
        boolean z = false;
        if (state.disabledByPolicy) {
            tileState.mState = 0;
        } else {
            tileState.mState = state.state;
        }
        tileState.mLabel = state.label;
        HashMap hashMap = SubtitleArrayMapping.subtitleIdsMap;
        int subtitleId = SubtitleArrayMapping.getSubtitleId(state.spec);
        LauncherTileService launcherTileService = this.this$0;
        tileState.mSubtitle = state.getSecondaryLabel(state.getStateText(subtitleId, launcherTileService.getApplicationContext().getResources()));
        tileState.mIsTransient = state.isTransient;
        Supplier supplier = state.iconSupplier;
        if (supplier != null) {
            icon = (QSTile.Icon) supplier.get();
        } else {
            icon = state.icon;
        }
        if (icon instanceof QSTileImpl.ResourceIcon) {
            tileState.mIcon = Icon.createWithResource(launcherTileService.getApplicationContext(), ((QSTileImpl.ResourceIcon) icon).mResId);
        } else {
            Bitmap convertDrawableToBitmap = PeopleSpaceUtils.convertDrawableToBitmap(icon.getDrawable(launcherTileService.getApplicationContext()));
            tileState.mIcon = Icon.createWithBitmap(convertDrawableToBitmap);
            if (convertDrawableToBitmap != null) {
                convertDrawableToBitmap.recycle();
            }
        }
        tileState.mSupportClick = !state.disabledByPolicy;
        tileState.mSupportLongClick = state.handlesLongClick;
        if (!(state instanceof QSTile.BooleanState) || ((QSTile.BooleanState) state).forceExpandIcon) {
            z = true;
        }
        tileState.mShowChevron = z;
        tileState.mContentDescription = state.contentDescription;
        tileState.mStateDescription = state.stateDescription;
        try {
            this.$listener.onTileUpdated(tileState);
        } catch (RemoteException e) {
            Log.e("LauncherTileService", "Failed to call onTileUpdated", e);
        }
    }
}
