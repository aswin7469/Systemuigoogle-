package com.google.android.material.expandable;

import android.view.View;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ExpandableWidgetHelper {
    public boolean expanded = false;
    public int expandedComponentIdHint = 0;
    public final View widget;

    public ExpandableWidgetHelper(ExpandableWidget expandableWidget) {
        this.widget = (View) expandableWidget;
    }
}
