package com.google.android.setupdesign.items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.setupdesign.items.ItemInflater;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ButtonBarItem extends AbstractItem implements ItemInflater.ItemParent {
    public final ArrayList buttons = new ArrayList();
    public final boolean visible = true;

    public ButtonBarItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void addChild(AbstractItemHierarchy abstractItemHierarchy) {
        if (abstractItemHierarchy instanceof ButtonItem) {
            this.buttons.add((ButtonItem) abstractItemHierarchy);
            return;
        }
        throw new UnsupportedOperationException("Cannot add non-button item to Button Bar");
    }

    public final int getCount() {
        return this.visible ? 1 : 0;
    }

    public final int getLayoutResource() {
        return 2131559103;
    }

    public final boolean isEnabled() {
        return false;
    }

    public final void onBindView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        linearLayout.removeAllViews();
        Iterator it = this.buttons.iterator();
        while (it.hasNext()) {
            ButtonItem buttonItem = (ButtonItem) it.next();
            Button button = buttonItem.button;
            if (button == null) {
                Context context = linearLayout.getContext();
                if (buttonItem.theme != 0) {
                    context = new ContextThemeWrapper(context, buttonItem.theme);
                }
                Button button2 = (Button) LayoutInflater.from(context).inflate(2131559060, (ViewGroup) null, false);
                buttonItem.button = button2;
                button2.setOnClickListener(buttonItem);
            } else if (button.getParent() instanceof ViewGroup) {
                ((ViewGroup) buttonItem.button.getParent()).removeView(buttonItem.button);
            }
            buttonItem.button.setEnabled(buttonItem.enabled);
            buttonItem.button.setText(buttonItem.text);
            buttonItem.button.setId(buttonItem.id);
            linearLayout.addView(buttonItem.button);
        }
        view.setId(this.id);
    }
}
