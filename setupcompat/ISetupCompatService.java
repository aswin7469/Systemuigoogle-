package com.google.android.setupcompat;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public interface ISetupCompatService extends IInterface {

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class Stub extends Binder implements ISetupCompatService {
        public static final /* synthetic */ int $r8$clinit = 0;

        /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
        public final class Proxy implements ISetupCompatService {
            public IBinder mRemote;

            public final IBinder asBinder() {
                return this.mRemote;
            }

            public final void logMetric(int i, Bundle bundle, Bundle bundle2) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.setupcompat.ISetupCompatService");
                    obtain.writeInt(i);
                    _Parcel.m828$$Nest$smwriteTypedObject(obtain, bundle);
                    _Parcel.m828$$Nest$smwriteTypedObject(obtain, bundle2);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public final void onFocusStatusChanged(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.setupcompat.ISetupCompatService");
                    _Parcel.m828$$Nest$smwriteTypedObject(obtain, bundle);
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public final void validateActivity(Bundle bundle, String str) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.setupcompat.ISetupCompatService");
                    obtain.writeString(str);
                    _Parcel.m828$$Nest$smwriteTypedObject(obtain, bundle);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public abstract class _Parcel {
        /* renamed from: -$$Nest$smwriteTypedObject  reason: not valid java name */
        public static void m828$$Nest$smwriteTypedObject(Parcel parcel, Parcelable parcelable) {
            if (parcelable != null) {
                parcel.writeInt(1);
                parcelable.writeToParcel(parcel, 0);
                return;
            }
            parcel.writeInt(0);
        }
    }
}
