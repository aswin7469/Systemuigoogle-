package com.google.android.material.snackbar;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.SwipeDismissBehavior;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class BaseTransientBottomBar {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* renamed from: com.google.android.material.snackbar.BaseTransientBottomBar$1  reason: invalid class name */
    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class AnonymousClass1 implements Handler.Callback {
        public final boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
                throw null;
            } else if (i != 1) {
                return false;
            } else {
                WindowDecorActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(message.obj);
                throw null;
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public class Behavior extends SwipeDismissBehavior {
        public final BehaviorDelegate delegate;

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.material.snackbar.BaseTransientBottomBar$BehaviorDelegate] */
        public Behavior() {
            ? obj = new Object();
            this.alphaStartSwipeDistance = Math.min(Math.max(0.0f, 0.1f), 1.0f);
            this.alphaEndSwipeDistance = Math.min(Math.max(0.0f, 0.6f), 1.0f);
            this.swipeDirection = 0;
            this.delegate = obj;
        }

        public final boolean canSwipeDismissView(View view) {
            this.delegate.getClass();
            return view instanceof Snackbar$SnackbarLayout;
        }

        public final boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.delegate.getClass();
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked == 1 || actionMasked == 3) {
                    if (SnackbarManager.snackbarManager == null) {
                        SnackbarManager.snackbarManager = new SnackbarManager();
                    }
                    synchronized (SnackbarManager.snackbarManager.lock) {
                    }
                }
            } else if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                if (SnackbarManager.snackbarManager == null) {
                    SnackbarManager.snackbarManager = new SnackbarManager();
                }
                synchronized (SnackbarManager.snackbarManager.lock) {
                }
            }
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class BehaviorDelegate {
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.os.Handler$Callback, java.lang.Object] */
    static {
        new Handler(Looper.getMainLooper(), new Object());
    }
}
