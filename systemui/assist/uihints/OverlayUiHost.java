package com.google.android.systemui.assist.uihints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class OverlayUiHost {
    public boolean mAttached = false;
    public boolean mFocusable = false;
    public WindowManager.LayoutParams mLayoutParams;
    public final AssistUIView mRoot;
    public final WindowManager mWindowManager;

    public OverlayUiHost(Context context) {
        this.mRoot = (AssistUIView) LayoutInflater.from(context).inflate(2131558470, (ViewGroup) null, false);
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }
}
