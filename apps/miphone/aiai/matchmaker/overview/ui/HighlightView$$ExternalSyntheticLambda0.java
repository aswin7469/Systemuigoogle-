package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final /* synthetic */ class HighlightView$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    public final /* synthetic */ HighlightView f$0;

    public /* synthetic */ HighlightView$$ExternalSyntheticLambda0(HighlightView highlightView) {
        this.f$0 = highlightView;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        HighlightView highlightView = this.f$0;
        int i = HighlightView.$r8$clinit;
        highlightView.getClass();
        if (motionEvent.getAction() != 0) {
            return false;
        }
        highlightView.performClick();
        motionEvent.getX();
        motionEvent.getY();
        Iterator it = highlightView.listeners.iterator();
        if (!it.hasNext()) {
            return true;
        }
        WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }
}
