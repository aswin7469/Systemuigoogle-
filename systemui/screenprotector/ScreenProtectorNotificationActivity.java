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

/* compiled from: go/retraceme 2137a22d937c6ed93fd00fd873698000dad14919f0531176a184f8a975d2c6e7 */
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
            Intent intent = new Intent("com.google.android.settings.touch.TOUCH_SENSITIVITY_SETTINGS");
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
        setContentView(2131558973);
        int intExtra = getIntent().getIntExtra("notify_id", 0);
        this.mDisableNotification = (TextView) findViewById(2131362441);
        this.mGotIt = (TextView) findViewById(2131362631);
        this.mGoToSettings = (TextView) findViewById(2131362629);
        this.mMessage = (TextView) findViewById(2131363046);
        this.mTitle = (TextView) findViewById(2131363868);
        this.mDisableNotification.setText(getString(2131953744));
        this.mGotIt.setText(getString(2131953746));
        this.mGoToSettings.setText(getString(2131953745));
        if (intExtra == 2) {
            this.mMessage.setText(getString(2131953743));
        } else {
            this.mMessage.setText(getString(2131953748));
        }
        this.mTitle.setText(getString(2131953747));
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
