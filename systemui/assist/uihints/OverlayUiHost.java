package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class OverlayUiHost {
    public boolean mAttached = false;
    public boolean mFocusable = false;
    public WindowManager.LayoutParams mLayoutParams;
    public final AssistUIView mRoot;
    public final WindowManager mWindowManager;

    public OverlayUiHost(Context context, TouchOutsideHandler touchOutsideHandler) {
        AssistUIView assistUIView = (AssistUIView) LayoutInflater.from(context).inflate(2131558469, (ViewGroup) null, false);
        this.mRoot = assistUIView;
        assistUIView.mTouchOutside = touchOutsideHandler;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }
}
