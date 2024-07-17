package com.google.android.setupcompat.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.setupcompat.ISetupCompatService;
import com.google.android.setupcompat.util.Logger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final /* synthetic */ class SetupCompatServiceInvoker$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ SetupCompatServiceInvoker f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ SetupCompatServiceInvoker$$ExternalSyntheticLambda0(SetupCompatServiceInvoker setupCompatServiceInvoker, String str, Bundle bundle, int i) {
        this.$r8$classId = i;
        this.f$0 = setupCompatServiceInvoker;
        this.f$1 = str;
        this.f$2 = bundle;
    }

    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                SetupCompatServiceInvoker setupCompatServiceInvoker = this.f$0;
                String str = this.f$1;
                Bundle bundle = this.f$2;
                setupCompatServiceInvoker.getClass();
                Logger logger = SetupCompatServiceInvoker.LOG;
                try {
                    Context context = setupCompatServiceInvoker.context;
                    ISetupCompatService service = SetupCompatServiceProvider.getInstance(context).getService(setupCompatServiceInvoker.waitTimeInMillisForServiceConnection, TimeUnit.MILLISECONDS);
                    if (service != null) {
                        ((ISetupCompatService.Stub.Proxy) service).validateActivity(bundle, str);
                        return;
                    } else {
                        logger.w("BindBack failed since service reference is null. Are the permissions valid?");
                        return;
                    }
                } catch (RemoteException | InterruptedException | TimeoutException e) {
                    logger.e("Exception occurred while " + str + " trying bind back to SetupWizard.", e);
                    return;
                }
            default:
                SetupCompatServiceInvoker setupCompatServiceInvoker2 = this.f$0;
                String str2 = this.f$1;
                Bundle bundle2 = this.f$2;
                setupCompatServiceInvoker2.getClass();
                Logger logger2 = SetupCompatServiceInvoker.LOG;
                try {
                    Context context2 = setupCompatServiceInvoker2.context;
                    ISetupCompatService service2 = SetupCompatServiceProvider.getInstance(context2).getService(setupCompatServiceInvoker2.waitTimeInMillisForServiceConnection, TimeUnit.MILLISECONDS);
                    if (service2 != null) {
                        ((ISetupCompatService.Stub.Proxy) service2).onFocusStatusChanged(bundle2);
                        return;
                    } else {
                        logger2.w("Report focusChange failed since service reference is null. Are the permission valid?");
                        return;
                    }
                } catch (RemoteException | InterruptedException | UnsupportedOperationException | TimeoutException e2) {
                    logger2.e("Exception occurred while " + str2 + " trying report windowFocusChange to SetupWizard.", e2);
                    return;
                }
        }
    }
}
