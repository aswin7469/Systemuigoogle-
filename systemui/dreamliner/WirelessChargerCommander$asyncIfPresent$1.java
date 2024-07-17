package com.google.android.systemui.dreamliner;

import java.util.Optional;
import java.util.function.Consumer;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
public final class WirelessChargerCommander$asyncIfPresent$1 implements Runnable {
    public final /* synthetic */ Function1 $block;
    public final /* synthetic */ Optional $this_asyncIfPresent;

    public WirelessChargerCommander$asyncIfPresent$1(Optional optional, Function1 function1) {
        this.$this_asyncIfPresent = optional;
        this.$block = function1;
    }

    public final void run() {
        Optional optional = this.$this_asyncIfPresent;
        final Function1 function1 = this.$block;
        optional.ifPresent(new Consumer() {
            public final void accept(Object obj) {
                Function1.this.invoke((WirelessCharger) obj);
            }
        });
    }
}
