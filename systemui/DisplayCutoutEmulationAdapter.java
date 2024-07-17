package com.google.android.systemui;

import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class DisplayCutoutEmulationAdapter {
    public final Context mContext;
    public final AnonymousClass1 mObserver;
    public final IOverlayManager mOverlayManager = IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay"));

    public DisplayCutoutEmulationAdapter(Context context) {
        AnonymousClass1 r0 = new ContentObserver(Handler.getMain()) {
            public final void onChange(boolean z) {
                DisplayCutoutEmulationAdapter.this.update();
            }
        };
        this.mContext = context;
        context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("com.google.android.systemui.display_cutout_emulation"), false, r0, 0);
        update();
    }

    public final void update() {
        Context context = this.mContext;
        String stringForUser = Settings.Global.getStringForUser(context.getContentResolver(), "com.google.android.systemui.display_cutout_emulation", 0);
        if (stringForUser != null) {
            String[] split = stringForUser.split(":", 2);
            try {
                int parseInt = Integer.parseInt(split[0]);
                String str = split[1];
                if (parseInt > PreferenceManager.getDefaultSharedPreferences(context).getInt("com.google.android.systemui.display_cutout_emulation.VERSION", -1)) {
                    if (str.isEmpty() || str.startsWith("com.android.internal.display.cutout.emulation")) {
                        IOverlayManager iOverlayManager = this.mOverlayManager;
                        try {
                            List overlayInfosForTarget = iOverlayManager.getOverlayInfosForTarget("android", 0);
                            for (int size = overlayInfosForTarget.size() - 1; size >= 0; size--) {
                                if (!((OverlayInfo) overlayInfosForTarget.get(size)).packageName.startsWith("com.android.internal.display.cutout.emulation")) {
                                    overlayInfosForTarget.remove(size);
                                }
                            }
                            OverlayInfo[] overlayInfoArr = (OverlayInfo[]) overlayInfosForTarget.toArray(new OverlayInfo[overlayInfosForTarget.size()]);
                            String str2 = null;
                            for (OverlayInfo overlayInfo : overlayInfoArr) {
                                if (overlayInfo.isEnabled()) {
                                    str2 = overlayInfo.packageName;
                                }
                            }
                            if ((!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) && !TextUtils.equals(str, str2)) {
                                for (OverlayInfo overlayInfo2 : overlayInfoArr) {
                                    boolean isEnabled = overlayInfo2.isEnabled();
                                    boolean equals = TextUtils.equals(overlayInfo2.packageName, str);
                                    if (isEnabled != equals) {
                                        try {
                                            iOverlayManager.setEnabled(overlayInfo2.packageName, equals, 0);
                                        } catch (RemoteException e) {
                                            throw e.rethrowFromSystemServer();
                                        }
                                    }
                                }
                            }
                            PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("com.google.android.systemui.display_cutout_emulation.VERSION", parseInt).apply();
                        } catch (RemoteException e2) {
                            throw e2.rethrowFromSystemServer();
                        }
                    } else {
                        Log.e("CutoutEmulationAdapter", "Invalid overlay prefix: ".concat(stringForUser));
                    }
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e3) {
                Log.e("CutoutEmulationAdapter", "Invalid configuration: ".concat(stringForUser), e3);
            }
        }
    }
}
