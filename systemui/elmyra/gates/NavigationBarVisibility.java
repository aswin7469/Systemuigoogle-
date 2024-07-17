package com.google.android.systemui.elmyra.gates;

import android.content.Context;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class NavigationBarVisibility extends Gate {
    public final AssistManagerGoogle mAssistManager;
    public final AnonymousClass1 mCommandQueueCallbacks;
    public final int mDisplayId;
    public final List mExceptions;
    public final AnonymousClass2 mGateListener;
    public boolean mIsKeyguardShowing;
    public boolean mIsNavigationGestural;
    public boolean mIsNavigationHidden = false;
    public final KeyguardVisibility mKeyguardGate;
    public final NonGesturalNavigation mNavigationModeGate;

    public NavigationBarVisibility(Context context, Executor executor, CommandQueue commandQueue, AssistManagerGoogle assistManagerGoogle, KeyguardVisibility keyguardVisibility, NonGesturalNavigation nonGesturalNavigation, Set set) {
        super(executor);
        AnonymousClass1 r4 = new CommandQueue.Callbacks() {
            public final void setWindowState(int i, int i2, int i3) {
                boolean z;
                NavigationBarVisibility navigationBarVisibility = NavigationBarVisibility.this;
                if (navigationBarVisibility.mDisplayId == i && i2 == 2) {
                    if (i3 != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z != navigationBarVisibility.mIsNavigationHidden) {
                        navigationBarVisibility.mIsNavigationHidden = z;
                        navigationBarVisibility.notifyListener();
                    }
                }
            }
        };
        AnonymousClass2 r0 = new Gate.Listener() {
            public final void onGateChanged(Gate gate) {
                NavigationBarVisibility navigationBarVisibility = NavigationBarVisibility.this;
                if (gate.equals(navigationBarVisibility.mKeyguardGate)) {
                    navigationBarVisibility.mIsKeyguardShowing = ((KeyguardStateControllerImpl) navigationBarVisibility.mKeyguardGate.mKeyguardStateController).mShowing;
                    return;
                }
                NonGesturalNavigation nonGesturalNavigation = navigationBarVisibility.mNavigationModeGate;
                if (gate.equals(nonGesturalNavigation)) {
                    navigationBarVisibility.mIsNavigationGestural = nonGesturalNavigation.mCurrentModeIsGestural;
                }
            }
        };
        this.mExceptions = new ArrayList(set);
        commandQueue.addCallback((CommandQueue.Callbacks) r4);
        this.mDisplayId = context.getDisplayId();
        this.mAssistManager = assistManagerGoogle;
        this.mKeyguardGate = keyguardVisibility;
        keyguardVisibility.mListener = r0;
        this.mNavigationModeGate = nonGesturalNavigation;
        nonGesturalNavigation.mListener = r0;
    }

    public final boolean isBlocked() {
        if (this.mIsKeyguardShowing) {
            return false;
        }
        if (this.mIsNavigationGestural && this.mAssistManager.mNgaIsAssistant) {
            return false;
        }
        int i = 0;
        while (true) {
            List list = this.mExceptions;
            if (i >= ((ArrayList) list).size()) {
                return this.mIsNavigationHidden;
            }
            if (((Action) ((ArrayList) list).get(i)).isAvailable()) {
                return false;
            }
            i++;
        }
    }

    public final void onActivate() {
        KeyguardVisibility keyguardVisibility = this.mKeyguardGate;
        keyguardVisibility.activate();
        this.mIsKeyguardShowing = ((KeyguardStateControllerImpl) keyguardVisibility.mKeyguardStateController).mShowing;
        NonGesturalNavigation nonGesturalNavigation = this.mNavigationModeGate;
        nonGesturalNavigation.activate();
        this.mIsNavigationGestural = nonGesturalNavigation.mCurrentModeIsGestural;
    }

    public final void onDeactivate() {
        NonGesturalNavigation nonGesturalNavigation = this.mNavigationModeGate;
        nonGesturalNavigation.deactivate();
        this.mIsNavigationGestural = nonGesturalNavigation.mCurrentModeIsGestural;
        KeyguardVisibility keyguardVisibility = this.mKeyguardGate;
        keyguardVisibility.deactivate();
        this.mIsKeyguardShowing = ((KeyguardStateControllerImpl) keyguardVisibility.mKeyguardStateController).mShowing;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mIsNavigationHidden -> ");
        sb.append(this.mIsNavigationHidden);
        sb.append("; mExceptions -> ");
        sb.append(this.mExceptions);
        sb.append("; mIsNavigationGestural -> ");
        sb.append(this.mIsNavigationGestural);
        sb.append("; isActiveAssistantNga() -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mAssistManager.mNgaIsAssistant, "]");
    }
}
