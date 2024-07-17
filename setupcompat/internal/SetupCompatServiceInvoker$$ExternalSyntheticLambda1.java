package com.google.android.setupcompat.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.setupcompat.ISetupCompatService;
import com.google.android.setupcompat.util.Logger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
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
