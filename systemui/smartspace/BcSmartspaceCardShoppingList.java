package com.google.android.systemui.smartspace;

import android.app.smartspace.SmartspaceAction;
import android.app.smartspace.SmartspaceTarget;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.compose.ui.text.input.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.google.android.systemui.smartspace.logging.BcSmartspaceCardLoggingInfo;
import java.util.Locale;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class BcSmartspaceCardShoppingList extends BcSmartspaceCardSecondary {
    public static final int[] LIST_ITEM_TEXT_VIEW_IDS = {2131362887, 2131362888, 2131362889};
    public ImageView mCardPromptIconView;
    public TextView mCardPromptView;
    public TextView mEmptyListMessageView;
    public ImageView mListIconView;
    public final TextView[] mListItems = new TextView[3];

    public BcSmartspaceCardShoppingList(Context context) {
        super(context);
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mCardPromptView = (TextView) findViewById(2131362207);
        this.mEmptyListMessageView = (TextView) findViewById(2131362503);
        this.mCardPromptIconView = (ImageView) findViewById(2131362208);
        this.mListIconView = (ImageView) findViewById(2131362885);
        for (int i = 0; i < 3; i++) {
            this.mListItems[i] = (TextView) findViewById(LIST_ITEM_TEXT_VIEW_IDS[i]);
        }
    }

    public final void resetUi() {
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mEmptyListMessageView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mListIconView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mCardPromptIconView, 8);
        BcSmartspaceTemplateDataUtils.updateVisibility(this.mCardPromptView, 8);
        for (int i = 0; i < 3; i++) {
            BcSmartspaceTemplateDataUtils.updateVisibility(this.mListItems[i], 8);
        }
    }

    public final boolean setSmartspaceActions(SmartspaceTarget smartspaceTarget, BcSmartspaceDataPlugin.SmartspaceEventNotifier smartspaceEventNotifier, BcSmartspaceCardLoggingInfo bcSmartspaceCardLoggingInfo) {
        Bundle bundle;
        SmartspaceAction baseAction = smartspaceTarget.getBaseAction();
        Bitmap bitmap = null;
        if (baseAction == null) {
            bundle = null;
        } else {
            bundle = baseAction.getExtras();
        }
        if (bundle != null) {
            if (bundle.containsKey("appIcon")) {
                bitmap = (Bitmap) bundle.get("appIcon");
            } else if (bundle.containsKey("imageBitmap")) {
                bitmap = (Bitmap) bundle.get("imageBitmap");
            }
            this.mCardPromptIconView.setImageBitmap(bitmap);
            this.mListIconView.setImageBitmap(bitmap);
            if (bundle.containsKey("cardPrompt")) {
                String string = bundle.getString("cardPrompt");
                TextView textView = this.mCardPromptView;
                if (textView == null) {
                    Log.w("BcSmartspaceCardShoppingList", "No card prompt view to update");
                } else {
                    textView.setText(string);
                }
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mCardPromptView, 0);
                if (bitmap != null) {
                    BcSmartspaceTemplateDataUtils.updateVisibility(this.mCardPromptIconView, 0);
                }
                return true;
            } else if (bundle.containsKey("emptyListString")) {
                String string2 = bundle.getString("emptyListString");
                TextView textView2 = this.mEmptyListMessageView;
                if (textView2 == null) {
                    Log.w("BcSmartspaceCardShoppingList", "No empty list message view to update");
                } else {
                    textView2.setText(string2);
                }
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mEmptyListMessageView, 0);
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mListIconView, 0);
                return true;
            } else if (bundle.containsKey("listItems")) {
                String[] stringArray = bundle.getStringArray("listItems");
                if (stringArray.length == 0) {
                    return false;
                }
                BcSmartspaceTemplateDataUtils.updateVisibility(this.mListIconView, 0);
                int i = 0;
                while (true) {
                    if (i >= 3) {
                        break;
                    }
                    TextView textView3 = this.mListItems[i];
                    if (textView3 == null) {
                        Locale locale = Locale.US;
                        RecordingInputConnection$$ExternalSyntheticOutline0.m("Missing list item view to update at row: ", i + 1, "BcSmartspaceCardShoppingList");
                        break;
                    }
                    if (i < stringArray.length) {
                        BcSmartspaceTemplateDataUtils.updateVisibility(textView3, 0);
                        textView3.setText(stringArray[i]);
                    } else {
                        BcSmartspaceTemplateDataUtils.updateVisibility(textView3, 8);
                        textView3.setText("");
                    }
                    i++;
                }
                return true;
            }
        }
        return false;
    }

    public final void setTextColor(int i) {
        this.mCardPromptView.setTextColor(i);
        this.mEmptyListMessageView.setTextColor(i);
        for (int i2 = 0; i2 < 3; i2++) {
            TextView textView = this.mListItems[i2];
            if (textView == null) {
                Locale locale = Locale.US;
                RecordingInputConnection$$ExternalSyntheticOutline0.m("Missing list item view to update at row: ", i2 + 1, "BcSmartspaceCardShoppingList");
                return;
            }
            textView.setTextColor(i);
        }
    }

    public BcSmartspaceCardShoppingList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
