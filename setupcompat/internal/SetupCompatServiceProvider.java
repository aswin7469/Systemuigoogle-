package com.google.android.setupcompat.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.setupcompat.ISetupCompatService;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupcompat.util.Logger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class SetupCompatServiceProvider {
    static final Intent COMPAT_SERVICE_INTENT = new Intent().setPackage(PartnerConfigHelper.SUW_PACKAGE_NAME).setAction("com.google.android.setupcompat.SetupCompatService.BIND");
    public static final Logger LOG = new Logger("SetupCompatServiceProvider");
    static boolean disableLooperCheckForTesting = false;
    public static volatile SetupCompatServiceProvider instance;
    public final AtomicReference connectedConditionRef = new AtomicReference();
    public final Context context;
    final ServiceConnection serviceConnection = new ServiceConnection() {
        public final void onBindingDied(ComponentName componentName) {
            SetupCompatServiceProvider.this.swapServiceContextAndNotify(new ServiceContext(State.REBIND_REQUIRED));
        }

        public final void onNullBinding(ComponentName componentName) {
            SetupCompatServiceProvider.this.swapServiceContextAndNotify(new ServiceContext(State.SERVICE_NOT_USABLE));
        }

        /* JADX WARNING: type inference failed for: r1v3, types: [com.google.android.setupcompat.ISetupCompatService$Stub$Proxy, java.lang.Object] */
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ISetupCompatService iSetupCompatService;
            State state = State.CONNECTED;
            if (iBinder == null) {
                state = State.DISCONNECTED;
                SetupCompatServiceProvider.LOG.w("Binder is null when onServiceConnected was called!");
            }
            SetupCompatServiceProvider setupCompatServiceProvider = SetupCompatServiceProvider.this;
            int i = ISetupCompatService.Stub.$r8$clinit;
            if (iBinder == null) {
                iSetupCompatService = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.setupcompat.ISetupCompatService");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof ISetupCompatService)) {
                    ? obj = new Object();
                    obj.mRemote = iBinder;
                    iSetupCompatService = obj;
                } else {
                    iSetupCompatService = (ISetupCompatService) queryLocalInterface;
                }
            }
            setupCompatServiceProvider.swapServiceContextAndNotify(new ServiceContext(state, iSetupCompatService));
        }

        public final void onServiceDisconnected(ComponentName componentName) {
            SetupCompatServiceProvider.this.swapServiceContextAndNotify(new ServiceContext(State.DISCONNECTED));
        }
    };
    public volatile ServiceContext serviceContext = new ServiceContext(State.NOT_STARTED);

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    enum State {
    }

    public SetupCompatServiceProvider(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public static SetupCompatServiceProvider getInstance(Context context2) {
        Preconditions.checkNotNull(context2, "Context object cannot be null.");
        SetupCompatServiceProvider setupCompatServiceProvider = instance;
        if (setupCompatServiceProvider == null) {
            synchronized (SetupCompatServiceProvider.class) {
                try {
                    setupCompatServiceProvider = instance;
                    if (setupCompatServiceProvider == null) {
                        setupCompatServiceProvider = new SetupCompatServiceProvider(context2.getApplicationContext());
                        instance = setupCompatServiceProvider;
                        instance.requestServiceBind();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return setupCompatServiceProvider;
    }

    public static void setInstanceForTesting(SetupCompatServiceProvider setupCompatServiceProvider) {
        instance = setupCompatServiceProvider;
    }

    public CountDownLatch createCountDownLatch() {
        return new CountDownLatch(1);
    }

    public State getCurrentState() {
        return this.serviceContext.state;
    }

    public ISetupCompatService getService(long j, TimeUnit timeUnit) throws TimeoutException, InterruptedException {
        ServiceContext serviceContext2;
        if (disableLooperCheckForTesting || Looper.getMainLooper() != Looper.myLooper()) {
            synchronized (this) {
                serviceContext2 = this.serviceContext;
            }
            switch (serviceContext2.state.ordinal()) {
                case 0:
                    LOG.w("NOT_STARTED state only possible before instance is created.");
                    return null;
                case 1:
                case 5:
                    return null;
                case 2:
                case 4:
                    return waitForConnection(j, timeUnit);
                case 3:
                    return serviceContext2.compatService;
                case 6:
                    requestServiceBind();
                    return waitForConnection(j, timeUnit);
                default:
                    throw new IllegalStateException("Unknown state = " + serviceContext2.state);
            }
        } else {
            throw new IllegalStateException("getService blocks and should not be called from the main thread.");
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final synchronized void requestServiceBind() {
        /*
            r6 = this;
            java.lang.String r0 = "Unable to bind to compat service. "
            monitor-enter(r6)
            monitor-enter(r6)     // Catch:{ all -> 0x0016 }
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$ServiceContext r1 = r6.serviceContext     // Catch:{ all -> 0x0074 }
            monitor-exit(r6)     // Catch:{ all -> 0x0016 }
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$State r1 = r1.state     // Catch:{ all -> 0x0016 }
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$State r2 = com.google.android.setupcompat.internal.SetupCompatServiceProvider.State.CONNECTED     // Catch:{ all -> 0x0016 }
            if (r1 != r2) goto L_0x0018
            com.google.android.setupcompat.util.Logger r0 = LOG     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = "Refusing to rebind since current state is already connected"
            r0.atInfo(r1)     // Catch:{ all -> 0x0016 }
            monitor-exit(r6)
            return
        L_0x0016:
            r0 = move-exception
            goto L_0x0077
        L_0x0018:
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$State r3 = com.google.android.setupcompat.internal.SetupCompatServiceProvider.State.NOT_STARTED     // Catch:{ all -> 0x0016 }
            if (r1 == r3) goto L_0x002a
            com.google.android.setupcompat.util.Logger r1 = LOG     // Catch:{ all -> 0x0016 }
            java.lang.String r3 = "Unbinding existing service connection."
            r1.atInfo(r3)     // Catch:{ all -> 0x0016 }
            android.content.Context r1 = r6.context     // Catch:{ all -> 0x0016 }
            android.content.ServiceConnection r3 = r6.serviceConnection     // Catch:{ all -> 0x0016 }
            r1.unbindService(r3)     // Catch:{ all -> 0x0016 }
        L_0x002a:
            android.content.Context r1 = r6.context     // Catch:{ SecurityException -> 0x004f }
            android.content.Intent r3 = COMPAT_SERVICE_INTENT     // Catch:{ SecurityException -> 0x004f }
            android.content.ServiceConnection r4 = r6.serviceConnection     // Catch:{ SecurityException -> 0x004f }
            r5 = 1
            boolean r0 = r1.bindService(r3, r4, r5)     // Catch:{ SecurityException -> 0x004f }
            if (r0 == 0) goto L_0x0061
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$State r0 = r6.getCurrentState()     // Catch:{ all -> 0x0016 }
            if (r0 == r2) goto L_0x0072
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$ServiceContext r0 = new com.google.android.setupcompat.internal.SetupCompatServiceProvider$ServiceContext     // Catch:{ all -> 0x0016 }
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$State r1 = com.google.android.setupcompat.internal.SetupCompatServiceProvider.State.BINDING     // Catch:{ all -> 0x0016 }
            r0.<init>(r1)     // Catch:{ all -> 0x0016 }
            r6.swapServiceContextAndNotify(r0)     // Catch:{ all -> 0x0016 }
            com.google.android.setupcompat.util.Logger r0 = LOG     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = "Context#bindService went through, now waiting for service connection"
            r0.atInfo(r1)     // Catch:{ all -> 0x0016 }
            goto L_0x0072
        L_0x004f:
            r1 = move-exception
            com.google.android.setupcompat.util.Logger r2 = LOG     // Catch:{ all -> 0x0016 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0016 }
            r3.<init>(r0)     // Catch:{ all -> 0x0016 }
            r3.append(r1)     // Catch:{ all -> 0x0016 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0016 }
            r2.e(r0)     // Catch:{ all -> 0x0016 }
        L_0x0061:
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$ServiceContext r0 = new com.google.android.setupcompat.internal.SetupCompatServiceProvider$ServiceContext     // Catch:{ all -> 0x0016 }
            com.google.android.setupcompat.internal.SetupCompatServiceProvider$State r1 = com.google.android.setupcompat.internal.SetupCompatServiceProvider.State.BIND_FAILED     // Catch:{ all -> 0x0016 }
            r0.<init>(r1)     // Catch:{ all -> 0x0016 }
            r6.swapServiceContextAndNotify(r0)     // Catch:{ all -> 0x0016 }
            com.google.android.setupcompat.util.Logger r0 = LOG     // Catch:{ all -> 0x0016 }
            java.lang.String r1 = "Context#bindService did not succeed."
            r0.e(r1)     // Catch:{ all -> 0x0016 }
        L_0x0072:
            monitor-exit(r6)
            return
        L_0x0074:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0016 }
            throw r0     // Catch:{ all -> 0x0016 }
        L_0x0077:
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.setupcompat.internal.SetupCompatServiceProvider.requestServiceBind():void");
    }

    public void swapServiceContextAndNotify(ServiceContext serviceContext2) {
        Logger logger = LOG;
        State state = this.serviceContext.state;
        State state2 = serviceContext2.state;
        logger.atInfo("State changed: " + state + " -> " + state2);
        this.serviceContext = serviceContext2;
        CountDownLatch countDownLatch = (CountDownLatch) this.connectedConditionRef.getAndSet((Object) null);
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public final ISetupCompatService waitForConnection(long j, TimeUnit timeUnit) {
        ServiceContext serviceContext2;
        AtomicReference atomicReference;
        CountDownLatch countDownLatch;
        ServiceContext serviceContext3;
        synchronized (this) {
            serviceContext2 = this.serviceContext;
        }
        if (serviceContext2.state == State.CONNECTED) {
            return serviceContext2.compatService;
        }
        do {
            atomicReference = this.connectedConditionRef;
            countDownLatch = (CountDownLatch) atomicReference.get();
            if (countDownLatch != null) {
                break;
            }
            countDownLatch = createCountDownLatch();
        } while (!atomicReference.compareAndSet((Object) null, countDownLatch));
        Logger logger = LOG;
        logger.atInfo("Waiting for service to get connected");
        if (countDownLatch.await(j, timeUnit)) {
            synchronized (this) {
                serviceContext3 = this.serviceContext;
            }
            State state = serviceContext3.state;
            logger.atInfo("Finished waiting for service to get connected. Current state = " + state);
            return serviceContext3.compatService;
        }
        requestServiceBind();
        throw new TimeoutException("Failed to acquire connection after [" + j + " " + timeUnit + "]");
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    final class ServiceContext {
        public final ISetupCompatService compatService;
        public final State state;

        public ServiceContext(State state2, ISetupCompatService iSetupCompatService) {
            this.state = state2;
            this.compatService = iSetupCompatService;
            if (state2 == State.CONNECTED) {
                Preconditions.checkNotNull(iSetupCompatService, "CompatService cannot be null when state is connected");
            }
        }

        public ServiceContext(State state2) {
            this(state2, (ISetupCompatService) null);
        }
    }
}
