package com.google.android.setupcompat.internal;

import android.os.BaseBundle;
import android.os.PersistableBundle;
import android.util.ArrayMap;
import com.google.android.setupcompat.util.Logger;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public abstract class PersistableBundles {
    public static final Logger LOG = new Logger("PersistableBundles");

    public static void assertIsValid(PersistableBundle persistableBundle) {
        Preconditions.checkNotNull(persistableBundle, "PersistableBundle cannot be null!");
        for (String str : persistableBundle.keySet()) {
            Object obj = persistableBundle.get(str);
            Preconditions.checkArgument(String.format("Unknown/unsupported data type [%s] for key %s", new Object[]{obj, str}), isSupportedDataType(obj));
        }
    }

    public static boolean isSupportedDataType(Object obj) {
        if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof String) || (obj instanceof Boolean)) {
            return true;
        }
        return false;
    }

    public static ArrayMap toMap(BaseBundle baseBundle) {
        if (baseBundle == null || baseBundle.isEmpty()) {
            return new ArrayMap(0);
        }
        ArrayMap arrayMap = new ArrayMap(baseBundle.size());
        for (String next : baseBundle.keySet()) {
            Object obj = baseBundle.get(next);
            if (!isSupportedDataType(obj)) {
                LOG.w(String.format("Unknown/unsupported data type [%s] for key %s", new Object[]{obj, next}));
            } else {
                arrayMap.put(next, baseBundle.get(next));
            }
        }
        return arrayMap;
    }
}
