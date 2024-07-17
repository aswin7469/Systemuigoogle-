package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.InflateException;
import java.lang.reflect.Constructor;
import java.util.HashMap;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public final class ItemInflater extends SimpleInflater {
    public static final Class[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    public static final HashMap constructorMap = new HashMap();
    public final Context context;
    public final String defaultPackage;
    public final Object[] tempConstructorArgs = new Object[2];

    /* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
    public interface ItemParent {
        void addChild(AbstractItemHierarchy abstractItemHierarchy);
    }

    public ItemInflater(Context context2) {
        super(context2.getResources());
        this.context = context2;
        this.defaultPackage = Item.class.getPackage().getName() + ".";
    }

    public final Object onCreateItem(String str, AttributeSet attributeSet) {
        String str2;
        String str3 = this.defaultPackage;
        Object[] objArr = this.tempConstructorArgs;
        if (str3 == null || str.indexOf(46) != -1) {
            str2 = str;
        } else {
            str2 = str3.concat(str);
        }
        HashMap hashMap = constructorMap;
        Constructor<?> constructor = (Constructor) hashMap.get(str2);
        Context context2 = this.context;
        if (constructor == null) {
            try {
                constructor = context2.getClassLoader().loadClass(str2).getConstructor(CONSTRUCTOR_SIGNATURE);
                constructor.setAccessible(true);
                hashMap.put(str, constructor);
            } catch (Exception e) {
                throw new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str2, e);
            }
        }
        objArr[0] = context2;
        objArr[1] = attributeSet;
        Object newInstance = constructor.newInstance(objArr);
        objArr[0] = null;
        objArr[1] = null;
        return newInstance;
    }
}
