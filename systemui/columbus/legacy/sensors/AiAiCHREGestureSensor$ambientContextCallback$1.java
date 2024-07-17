package com.google.android.systemui.columbus.legacy.sensors;

import android.app.ambientcontext.AmbientContextCallback;
import android.app.ambientcontext.AmbientContextEvent;
import android.frameworks.stats.IStats;
import android.frameworks.stats.VendorAtom;
import android.frameworks.stats.VendorAtomValue;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ServiceManager;
import android.util.Log;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class AiAiCHREGestureSensor$ambientContextCallback$1 implements AmbientContextCallback {
    public final /* synthetic */ SystemClock $clock;
    public final /* synthetic */ AiAiCHREGestureSensor this$0;

    public AiAiCHREGestureSensor$ambientContextCallback$1(SystemClock systemClock, AiAiCHREGestureSensor aiAiCHREGestureSensor) {
        this.$clock = systemClock;
        this.this$0 = aiAiCHREGestureSensor;
    }

    /* JADX WARNING: type inference failed for: r2v8, types: [java.lang.Object, android.frameworks.stats.IStats$Stub$Proxy] */
    public final void onEvents(List list) {
        IInterface iInterface;
        Object obj;
        Log.i("Columbus/GestureSensor", "Received events from AmbientContextManager: " + list);
        Iterator it = list.iterator();
        while (true) {
            iInterface = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((AmbientContextEvent) obj).getEventType() == 3) {
                break;
            }
        }
        AmbientContextEvent ambientContextEvent = (AmbientContextEvent) obj;
        if (ambientContextEvent == null) {
            Log.e("Columbus/GestureSensor", "Receiving events but not EVENT_BACK_DOUBLE_TAP");
            return;
        }
        ((SystemClockImpl) this.$clock).getClass();
        String str = ColumbusMetrics.ISTATS_INSTANCE_NAME;
        VendorAtom vendorAtom = new VendorAtom();
        vendorAtom.reverseDomainName = "";
        vendorAtom.atomId = 100139;
        int i = 1;
        vendorAtom.values = new VendorAtomValue[]{new VendorAtomValue(1, Long.valueOf(System.currentTimeMillis() - ambientContextEvent.getStartTime().toEpochMilli()))};
        try {
            String str2 = ColumbusMetrics.ISTATS_INSTANCE_NAME;
            if (!ServiceManager.isDeclared(str2)) {
                Log.e("Columbus/Metrics", "IStats is not registered");
            } else {
                IBinder waitForDeclaredService = ServiceManager.waitForDeclaredService(str2);
                int i2 = IStats.Stub.$r8$clinit;
                if (waitForDeclaredService != null) {
                    IInterface queryLocalInterface = waitForDeclaredService.queryLocalInterface(IStats.DESCRIPTOR);
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IStats)) {
                        ? obj2 = new Object();
                        obj2.mRemote = waitForDeclaredService;
                        iInterface = obj2;
                    } else {
                        iInterface = (IStats) queryLocalInterface;
                    }
                }
            }
            if (iInterface != null) {
                ((IStats.Stub.Proxy) iInterface).reportVendorAtom(vendorAtom);
                if (ColumbusMetrics.DEBUG) {
                    Log.d("Columbus/Metrics", "Report vendor atom OK, " + vendorAtom);
                }
            }
        } catch (Exception e) {
            Log.e("Columbus/Metrics", "Failed to log atom to IStats service, " + e);
        }
        AiAiCHREGestureSensor aiAiCHREGestureSensor = this.this$0;
        int eventType = ambientContextEvent.getEventType();
        aiAiCHREGestureSensor.getClass();
        if (eventType != 3) {
            i = 0;
        }
        GestureSensor.DetectionProperties detectionProperties = new GestureSensor.DetectionProperties(false);
        GestureController$gestureSensorListener$1 gestureController$gestureSensorListener$1 = aiAiCHREGestureSensor.listener;
        if (gestureController$gestureSensorListener$1 != null) {
            gestureController$gestureSensorListener$1.onGestureDetected(i, detectionProperties);
        }
    }

    public final void onRegistrationComplete(int i) {
        Log.i("Columbus/GestureSensor", "registerObserver completes with status: " + i);
    }
}
