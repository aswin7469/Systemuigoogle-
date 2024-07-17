package com.google.android.systemui.backup;

import android.app.backup.BlobBackupHelper;
import android.content.ContentResolver;
import android.os.UserHandle;
import com.android.systemui.backup.BackupHelper;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class BackupHelperGoogle extends BackupHelper {
    public static final List SECURE_SETTINGS_INT_KEYS = CollectionsKt__CollectionsKt.listOf("columbus_enabled", "columbus_low_sensitivity");
    public static final List SECURE_SETTINGS_STRING_KEYS = CollectionsKt__CollectionsKt.listOf("columbus_action", "columbus_launch_app", "columbus_launch_app_shortcut");

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public final class SecureSettingsBackupHelper extends BlobBackupHelper {
        public final ContentResolver contentResolver;
        public final UserHandle userHandle;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SecureSettingsBackupHelper(android.content.ContentResolver r5, android.os.UserHandle r6) {
            /*
                r4 = this;
                kotlin.jvm.internal.SpreadBuilder r0 = new kotlin.jvm.internal.SpreadBuilder
                r1 = 2
                r0.<init>(r1)
                java.util.List r1 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_INT_KEYS
                r2 = 0
                java.lang.String[] r3 = new java.lang.String[r2]
                java.lang.Object[] r1 = r1.toArray(r3)
                r0.addSpread(r1)
                java.util.List r1 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_STRING_KEYS
                java.lang.String[] r2 = new java.lang.String[r2]
                java.lang.Object[] r1 = r1.toArray(r2)
                r0.addSpread(r1)
                java.util.ArrayList r0 = r0.list
                int r1 = r0.size()
                java.lang.String[] r1 = new java.lang.String[r1]
                java.lang.Object[] r0 = r0.toArray(r1)
                java.lang.String[] r0 = (java.lang.String[]) r0
                r1 = 1
                r4.<init>(r1, r0)
                r4.contentResolver = r5
                r4.userHandle = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.backup.BackupHelperGoogle.SecureSettingsBackupHelper.<init>(android.content.ContentResolver, android.os.UserHandle):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            android.util.Log.e("BackupHelper", "Failed to restore ".concat(r5));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x007f, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            android.util.Log.e("BackupHelper", "Failed to restore ".concat(r5));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x008c, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x008f, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0081 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void applyRestoredPayload(java.lang.String r5, byte[] r6) {
            /*
                r4 = this;
                java.util.List r0 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_INT_KEYS
                boolean r0 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r0, r5)
                java.lang.String r1 = "BackupHelper"
                java.lang.String r2 = "Failed to restore "
                if (r0 == 0) goto L_0x004b
                if (r5 == 0) goto L_0x0090
                if (r6 == 0) goto L_0x0090
                int r0 = r5.length()
                if (r0 != 0) goto L_0x0018
                goto L_0x0090
            L_0x0018:
                int r0 = r6.length
                if (r0 != 0) goto L_0x001d
                goto L_0x0090
            L_0x001d:
                java.io.DataInputStream r0 = new java.io.DataInputStream
                java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream
                r3.<init>(r6)
                r0.<init>(r3)
                int r6 = r0.readInt()     // Catch:{ IOException -> 0x003c }
                r0.close()
                android.content.ContentResolver r0 = r4.contentResolver
                android.os.UserHandle r4 = r4.userHandle
                int r4 = r4.getIdentifier()
                android.provider.Settings.Secure.putIntForUser(r0, r5, r6, r4)
                goto L_0x0090
            L_0x003a:
                r4 = move-exception
                goto L_0x0047
            L_0x003c:
                java.lang.String r4 = r2.concat(r5)     // Catch:{ all -> 0x003a }
                android.util.Log.e(r1, r4)     // Catch:{ all -> 0x003a }
                r0.close()
                goto L_0x0090
            L_0x0047:
                r0.close()
                throw r4
            L_0x004b:
                java.util.List r0 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_STRING_KEYS
                boolean r0 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r0, r5)
                if (r0 == 0) goto L_0x0090
                if (r5 == 0) goto L_0x0090
                if (r6 == 0) goto L_0x0090
                int r0 = r5.length()
                if (r0 != 0) goto L_0x005e
                goto L_0x0090
            L_0x005e:
                int r0 = r6.length
                if (r0 != 0) goto L_0x0062
                goto L_0x0090
            L_0x0062:
                java.io.DataInputStream r0 = new java.io.DataInputStream
                java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream
                r3.<init>(r6)
                r0.<init>(r3)
                java.lang.String r6 = r0.readUTF()     // Catch:{ IOException -> 0x0081 }
                r0.close()
                android.content.ContentResolver r0 = r4.contentResolver
                android.os.UserHandle r4 = r4.userHandle
                int r4 = r4.getIdentifier()
                android.provider.Settings.Secure.putStringForUser(r0, r5, r6, r4)
                goto L_0x0090
            L_0x007f:
                r4 = move-exception
                goto L_0x008c
            L_0x0081:
                java.lang.String r4 = r2.concat(r5)     // Catch:{ all -> 0x007f }
                android.util.Log.e(r1, r4)     // Catch:{ all -> 0x007f }
                r0.close()
                goto L_0x0090
            L_0x008c:
                r0.close()
                throw r4
            L_0x0090:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.backup.BackupHelperGoogle.SecureSettingsBackupHelper.applyRestoredPayload(java.lang.String, byte[]):void");
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
            r4.close();
            android.util.Log.e("BackupHelper", "Failed to backup " + r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0043, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
            throw r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
            r5 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
            android.util.Log.e("BackupHelper", "Failed to backup " + r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0085, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
            throw r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x002e, code lost:
            r5 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0030 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final byte[] getBackupPayload(java.lang.String r6) {
            /*
                r5 = this;
                java.util.List r0 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_INT_KEYS
                boolean r0 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r0, r6)
                java.lang.String r1 = "BackupHelper"
                java.lang.String r2 = "Failed to backup "
                r3 = 0
                if (r0 == 0) goto L_0x0047
                android.content.ContentResolver r0 = r5.contentResolver     // Catch:{ SettingNotFoundException -> 0x0089 }
                android.os.UserHandle r5 = r5.userHandle     // Catch:{ SettingNotFoundException -> 0x0089 }
                int r5 = r5.getIdentifier()     // Catch:{ SettingNotFoundException -> 0x0089 }
                int r5 = android.provider.Settings.Secure.getIntForUser(r0, r6, r5)     // Catch:{ SettingNotFoundException -> 0x0089 }
                java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
                r0.<init>()
                java.io.DataOutputStream r4 = new java.io.DataOutputStream
                r4.<init>(r0)
                r4.writeInt(r5)     // Catch:{ IOException -> 0x0030 }
                byte[] r3 = r0.toByteArray()     // Catch:{ IOException -> 0x0030 }
            L_0x002a:
                r4.close()
                goto L_0x0089
            L_0x002e:
                r5 = move-exception
                goto L_0x0043
            L_0x0030:
                r4.close()     // Catch:{ all -> 0x002e }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x002e }
                r5.<init>(r2)     // Catch:{ all -> 0x002e }
                r5.append(r6)     // Catch:{ all -> 0x002e }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x002e }
                android.util.Log.e(r1, r5)     // Catch:{ all -> 0x002e }
                goto L_0x002a
            L_0x0043:
                r4.close()
                throw r5
            L_0x0047:
                java.util.List r0 = com.google.android.systemui.backup.BackupHelperGoogle.SECURE_SETTINGS_STRING_KEYS
                boolean r0 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r0, r6)
                if (r0 == 0) goto L_0x0089
                android.content.ContentResolver r0 = r5.contentResolver
                android.os.UserHandle r5 = r5.userHandle
                int r5 = r5.getIdentifier()
                java.lang.String r5 = android.provider.Settings.Secure.getStringForUser(r0, r6, r5)
                if (r5 != 0) goto L_0x005e
                goto L_0x0089
            L_0x005e:
                java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
                r0.<init>()
                java.io.DataOutputStream r4 = new java.io.DataOutputStream
                r4.<init>(r0)
                r4.writeUTF(r5)     // Catch:{ IOException -> 0x0075 }
                byte[] r3 = r0.toByteArray()     // Catch:{ IOException -> 0x0075 }
            L_0x006f:
                r4.close()
                goto L_0x0089
            L_0x0073:
                r5 = move-exception
                goto L_0x0085
            L_0x0075:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0073 }
                r5.<init>(r2)     // Catch:{ all -> 0x0073 }
                r5.append(r6)     // Catch:{ all -> 0x0073 }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0073 }
                android.util.Log.e(r1, r5)     // Catch:{ all -> 0x0073 }
                goto L_0x006f
            L_0x0085:
                r4.close()
                throw r5
            L_0x0089:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.backup.BackupHelperGoogle.SecureSettingsBackupHelper.getBackupPayload(java.lang.String):byte[]");
        }
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [android.app.backup.BackupHelper, com.google.android.systemui.backup.BackupHelperGoogle$SecureSettingsBackupHelper] */
    public final void onCreate(UserHandle userHandle, int i) {
        super.onCreate(userHandle, i);
        addHelper("systemui.google.secure_settings_backup", new SecureSettingsBackupHelper(getContentResolver(), userHandle));
    }
}
