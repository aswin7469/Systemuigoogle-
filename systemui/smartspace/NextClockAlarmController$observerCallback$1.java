package com.google.android.systemui.smartspace;

import android.util.Log;
import androidx.appsearch.observer.DocumentChangeInfo;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class NextClockAlarmController$observerCallback$1 {
    public final /* synthetic */ NextClockAlarmController this$0;

    public NextClockAlarmController$observerCallback$1(NextClockAlarmController nextClockAlarmController) {
        this.this$0 = nextClockAlarmController;
    }

    public final void onDocumentChanged(DocumentChangeInfo documentChangeInfo) {
        Log.d("NextClockAlarmCtlr", "onDocumentChanged changeInfo=" + documentChangeInfo);
        NextClockAlarmController nextClockAlarmController = this.this$0;
        Job job = nextClockAlarmController.updateNextAlarmJob;
        if (job != null) {
            job.cancel((CancellationException) null);
        }
        nextClockAlarmController.updateNextAlarmJob = BuildersKt.launch$default(nextClockAlarmController.applicationScope, (CoroutineContext) null, (CoroutineStart) null, new NextClockAlarmController$observerCallback$1$onDocumentChanged$1(nextClockAlarmController, (Continuation) null), 3);
    }
}
