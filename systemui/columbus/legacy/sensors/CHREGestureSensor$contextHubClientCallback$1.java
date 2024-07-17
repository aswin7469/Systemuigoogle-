package com.google.android.systemui.columbus.legacy.sensors;

import android.hardware.location.ContextHubClient;
import android.hardware.location.ContextHubClientCallback;
import android.hardware.location.NanoAppMessage;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.keyguard.ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$GestureDetected;
import com.google.android.systemui.columbus.proto.nano.ColumbusProto$NanoappEvents;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class CHREGestureSensor$contextHubClientCallback$1 extends ContextHubClientCallback {
    public final /* synthetic */ CHREGestureSensor this$0;

    public CHREGestureSensor$contextHubClientCallback$1(CHREGestureSensor cHREGestureSensor) {
        this.this$0 = cHREGestureSensor;
    }

    public final void onHubReset(ContextHubClient contextHubClient) {
        ExifInterface$$ExternalSyntheticOutline0.m("HubReset: ", contextHubClient.getAttachedHub().getId(), "Columbus/GestureSensor");
    }

    public final void onMessageFromNanoApp(ContextHubClient contextHubClient, NanoAppMessage nanoAppMessage) {
        if (nanoAppMessage.getNanoAppId() == 5147455389092024345L) {
            try {
                int messageType = nanoAppMessage.getMessageType();
                if (messageType == 300) {
                    CHREGestureSensor.access$handleGestureDetection(this.this$0, ColumbusProto$GestureDetected.parseFrom(nanoAppMessage.getMessageBody()));
                } else if (messageType != 500) {
                    int messageType2 = nanoAppMessage.getMessageType();
                    Log.e("Columbus/GestureSensor", "Unknown message type: " + messageType2);
                } else {
                    CHREGestureSensor.access$handleNanoappEvents(this.this$0, ColumbusProto$NanoappEvents.parseFrom(nanoAppMessage.getMessageBody()));
                }
            } catch (InvalidProtocolBufferNanoException e) {
                Log.e("Columbus/GestureSensor", "Invalid protocol buffer", e);
            }
        }
    }

    public final void onNanoAppAborted(ContextHubClient contextHubClient, long j, int i) {
        if (j == 5147455389092024345L) {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Nanoapp aborted, code: ", i, "Columbus/GestureSensor");
        }
    }

    public final void onNanoAppLoaded(ContextHubClient contextHubClient, long j) {
        if (j == 5147455389092024345L && this.this$0.isListening) {
            Log.d("Columbus/GestureSensor", "Nanoapp loaded");
            this.this$0.updateScreenState();
            this.this$0.startRecognizer();
        }
    }
}
