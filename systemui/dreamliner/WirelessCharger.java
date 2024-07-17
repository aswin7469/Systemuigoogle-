package com.google.android.systemui.dreamliner;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class WirelessCharger {

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface AlignInfoListener {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ChallengeCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface GetFanInformationCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface GetFanSimpleInformationCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface GetInformationCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface IsDockPresentCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface KeyExchangeCallback {
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface SetFanCallback {
    }

    public abstract void asyncIsDockPresent(IsDockPresentCallback isDockPresentCallback);

    public abstract void challenge(byte b, byte[] bArr, ChallengeCallback challengeCallback);

    public abstract void getFanInformation(byte b, GetFanInformationCallback getFanInformationCallback);

    public abstract void getFanSimpleInformation(byte b, GetFanSimpleInformationCallback getFanSimpleInformationCallback);

    public abstract void getInformation(GetInformationCallback getInformationCallback);

    public abstract void keyExchange(byte[] bArr, KeyExchangeCallback keyExchangeCallback);

    public abstract void registerAlignInfo(AlignInfoListener alignInfoListener);

    public abstract void setFan(byte b, byte b2, int i, SetFanCallback setFanCallback);
}
