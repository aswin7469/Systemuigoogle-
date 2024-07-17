package com.google.android.systemui.dagger;

import android.view.View;
import com.google.android.systemui.smartspace.DateSmartspaceDataProvider;
import java.util.HashSet;
import java.util.Iterator;
import javax.inject.Provider;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class SystemUIGoogleModule_ProvideDateSmartspaceDataPluginFactory implements Provider {
    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.systemui.smartspace.DateSmartspaceDataProvider] */
    public static DateSmartspaceDataProvider provideDateSmartspaceDataPlugin() {
        ? obj = new Object();
        obj.mViews = new HashSet();
        obj.mAttachListeners = new HashSet();
        obj.mEventNotifier = null;
        obj.mStateChangeListener = new View.OnAttachStateChangeListener() {
            public final void onViewAttachedToWindow(View view) {
                DateSmartspaceDataProvider.this.mViews.add(view);
                Iterator it = ((HashSet) DateSmartspaceDataProvider.this.mAttachListeners).iterator();
                while (it.hasNext()) {
                    ((View.OnAttachStateChangeListener) it.next()).onViewAttachedToWindow(view);
                }
            }

            public final void onViewDetachedFromWindow(View view) {
                DateSmartspaceDataProvider.this.mViews.remove(view);
                Iterator it = ((HashSet) DateSmartspaceDataProvider.this.mAttachListeners).iterator();
                while (it.hasNext()) {
                    ((View.OnAttachStateChangeListener) it.next()).onViewDetachedFromWindow(view);
                }
            }
        };
        return obj;
    }
}
