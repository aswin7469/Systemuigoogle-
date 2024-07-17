package com.google.android.systemui.screenprotector;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/* compiled from: go/retraceme db998610a30546cfb750cb42d68186f67be36966c6ca98c5d0200b062af37cac */
public class ScreenProtectorNotificationActivity extends Activity implements View.OnClickListener {
    public TextView mDisableNotification;
    public TextView mGoToSettings;
    public TextView mGotIt;
    public TextView mMessage;
    public byte mResponse = 0;
    public TextView mTitle;

    public final void onClick(View view) {
        if (view == this.mGoToSettings) {
            this.mResponse = 2;
            Log.i("ScreenProtectorNotificationActivity", "Button clicked - Go To Settings");
            Bundle bundle = new Bundle();
            bundle.putString(":settings:fragment_args_key", "touch_sensitivity");
            Intent intent = new Intent("android.settings.DISPLAY_SETTINGS");
            intent.addFlags(402653184);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra(":settings:show_fragment_args", bundle);
            startActivity(intent);
        } else if (view == this.mDisableNotification) {
            this.mResponse = 4;
            Log.i("ScreenProtectorNotificationActivity", "Button clicked - Disable Notification");
            ScreenProtectorSharedPreferenceUtils.getSharedPreferences(getApplicationContext()).edit().putBoolean("notification_enabled", false).apply();
        } else if (view == this.mGotIt) {
            this.mResponse = 3;
            Log.i("ScreenProtectorNotificationActivity", "Button clicked -  Got It");
        }
        finish();
    }

    public final void onCreate(Bundle bundle) {
        Window window = getWindow();
        window.addSystemFlags(524288);
        window.setType(2008);
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(2131558967);
        int intExtra = getIntent().getIntExtra("notify_id", 0);
        this.mDisableNotification = (TextView) findViewById(2131362421);
        this.mGotIt = (TextView) findViewById(2131362611);
        this.mGoToSettings = (TextView) findViewById(2131362609);
        this.mMessage = (TextView) findViewById(2131363020);
        this.mTitle = (TextView) findViewById(2131363834);
        this.mDisableNotification.setText(getString(2131953702));
        this.mGotIt.setText(getString(2131953704));
        this.mGoToSettings.setText(getString(2131953703));
        if (intExtra == 2) {
            this.mMessage.setText(getString(2131953701));
        } else {
            this.mMessage.setText(getString(2131953706));
        }
        this.mTitle.setText(getString(2131953705));
        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(getApplicationContext()).edit().putInt("notification_response", -1).apply();
        this.mDisableNotification.setOnClickListener(this);
        this.mGotIt.setOnClickListener(this);
        this.mGoToSettings.setOnClickListener(this);
    }

    public final void onDestroy() {
        Context applicationContext = getApplicationContext();
        ScreenProtectorSharedPreferenceUtils.getSharedPreferences(applicationContext).edit().putInt("notification_response", this.mResponse).apply();
        super.onDestroy();
    }

    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
    }
}
