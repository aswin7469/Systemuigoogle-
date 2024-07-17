package com.google.android.systemui.assist.uihints.input;

import com.google.android.systemui.assist.uihints.TouchInsideHandler;
import java.util.Set;
import javax.inject.Provider;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public abstract class NgaInputHandler_Factory implements Provider {
    public static NgaInputHandler newInstance(TouchInsideHandler touchInsideHandler, Set set, Set set2) {
        return new NgaInputHandler(touchInsideHandler, set, set2);
    }
}
