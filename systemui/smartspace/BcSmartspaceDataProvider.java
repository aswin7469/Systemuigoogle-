package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceTarget;
import android.app.smartspace.SmartspaceTargetEvent;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class BcSmartspaceDataProvider implements BcSmartspaceDataPlugin {
    public static final boolean DEBUG = Log.isLoggable(BcSmartspaceDataPlugin.TAG, 3);
    public final Set mAttachListeners = new HashSet();
    public BcSmartspaceDataPlugin.SmartspaceEventNotifier mEventNotifier = null;
    public final Set mSmartspaceTargetListeners = new HashSet();
    public final List mSmartspaceTargets = new ArrayList();
    public final AnonymousClass1 mStateChangeListener = new View.OnAttachStateChangeListener() {
        public final void onViewAttachedToWindow(View view) {
            BcSmartspaceDataProvider.this.mViews.add(view);
            Iterator it = ((HashSet) BcSmartspaceDataProvider.this.mAttachListeners).iterator();
            while (it.hasNext()) {
                ((View.OnAttachStateChangeListener) it.next()).onViewAttachedToWindow(view);
            }
        }

        public final void onViewDetachedFromWindow(View view) {
            BcSmartspaceDataProvider.this.mViews.remove(view);
            Iterator it = ((HashSet) BcSmartspaceDataProvider.this.mAttachListeners).iterator();
            while (it.hasNext()) {
                ((View.OnAttachStateChangeListener) it.next()).onViewDetachedFromWindow(view);
            }
        }
    };
    public final Set mViews = new HashSet();

    public final void addOnAttachStateChangeListener(View.OnAttachStateChangeListener onAttachStateChangeListener) {
        this.mAttachListeners.add(onAttachStateChangeListener);
        Iterator it = ((HashSet) this.mViews).iterator();
        while (it.hasNext()) {
            onAttachStateChangeListener.onViewAttachedToWindow((View) it.next());
        }
    }

    public final BcSmartspaceDataPlugin.SmartspaceView getView(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2131559039, viewGroup, false);
        inflate.addOnAttachStateChangeListener(this.mStateChangeListener);
        return (BcSmartspaceDataPlugin.SmartspaceView) inflate;
    }

    public final void notifySmartspaceEvent(SmartspaceTargetEvent smartspaceTargetEvent) {
        BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier = this.mEventNotifier;
        if (smartspaceEventNotifier != null) {
            smartspaceEventNotifier.notifySmartspaceEvent(smartspaceTargetEvent);
        }
    }

    public final void onTargetsAvailable(List list) {
        if (DEBUG) {
            Log.d(BcSmartspaceDataPlugin.TAG, this + " onTargetsAvailable called. Callers = " + Debug.getCallers(3));
            StringBuilder sb = new StringBuilder("    targets.size() = ");
            sb.append(list.size());
            Log.d(BcSmartspaceDataPlugin.TAG, sb.toString());
            Log.d(BcSmartspaceDataPlugin.TAG, "    targets = " + list.toString());
        }
        List list2 = this.mSmartspaceTargets;
        list2.clear();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SmartspaceTarget smartspaceTarget = (SmartspaceTarget) it.next();
            if (smartspaceTarget.getFeatureType() != 15) {
                list2.add(smartspaceTarget);
            }
        }
        this.mSmartspaceTargetListeners.forEach(new BcSmartspaceDataProvider$$ExternalSyntheticLambda0(this));
    }

    public final void registerListener(BcSmartspaceDataPlugin.SmartspaceTargetListener smartspaceTargetListener) {
        this.mSmartspaceTargetListeners.add(smartspaceTargetListener);
        smartspaceTargetListener.onSmartspaceTargetsUpdated(this.mSmartspaceTargets);
    }

    public final void registerSmartspaceEventNotifier(BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier) {
        this.mEventNotifier = smartspaceEventNotifier;
    }

    public final void unregisterListener(BcSmartspaceDataPlugin.SmartspaceTargetListener smartspaceTargetListener) {
        this.mSmartspaceTargetListeners.remove(smartspaceTargetListener);
    }
}
