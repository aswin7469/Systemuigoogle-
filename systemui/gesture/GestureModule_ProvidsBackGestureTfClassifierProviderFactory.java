package com.google.android.systemui.gesture;

import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class GestureModule_ProvidsBackGestureTfClassifierProviderFactory implements Provider {
    public static BackGestureTfClassifierProviderGoogle providsBackGestureTfClassifierProvider(String str) {
        return new BackGestureTfClassifierProviderGoogle(str);
    }
}