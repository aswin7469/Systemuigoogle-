package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class DimView$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    public final /* synthetic */ DimView f$0;

    public /* synthetic */ DimView$$ExternalSyntheticLambda0(DimView dimView) {
        this.f$0 = dimView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        DimView dimView = this.f$0;
        int i = DimView.$r8$clinit;
        dimView.getClass();
        if (motionEvent.getAction() != 0) {
            return false;
        }
        Log.i("AiAiSuggestUi", "Handle touch for the background scrim.");
        return true;
    }
}
