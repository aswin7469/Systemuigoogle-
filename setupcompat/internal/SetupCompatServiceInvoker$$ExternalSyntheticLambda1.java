package com.google.android.setupcompat.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.setupcompat.ISetupCompatService;
import com.google.android.setupcompat.util.Logger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class SetupCompatServiceInvoker$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SetupCompatServiceInvoker f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ SetupCompatServiceInvoker$$ExternalSyntheticLambda1(SetupCompatServiceInvoker setupCompatServiceInvoker, int i, Bundle bundle) {
        this.f$0 = setupCompatServiceInvoker;
        this.f$1 = i;
        this.f$2 = bundle;
    }

    public final void run() {
        SetupCompatServiceInvoker setupCompatServiceInvoker = this.f$0;
        int i = this.f$1;
        Bundle bundle = this.f$2;
        setupCompatServiceInvoker.getClass();
        Logger logger = SetupCompatServiceInvoker.LOG;
        try {
            Context context = setupCompatServiceInvoker.context;
            ISetupCompatService service = SetupCompatServiceProvider.getInstance(context).getService(setupCompatServiceInvoker.waitTimeInMillisForServiceConnection, TimeUnit.MILLISECONDS);
            if (service != null) {
                ((ISetupCompatService.Stub.Proxy) service).logMetric(i, bundle, Bundle.EMPTY);
                return;
            }
            logger.w("logMetric failed since service reference is null. Are the permissions valid?");
        } catch (RemoteException | IllegalStateException | InterruptedException | TimeoutException e) {
            logger.e("Exception occurred while trying to log metric = [" + bundle + "]", e);
        }
    }
}
