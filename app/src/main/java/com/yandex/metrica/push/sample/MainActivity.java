package com.yandex.metrica.push.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.yandex.metrica.push.YandexMetricaPush;

/**
 * Main application activity. It will be opened on notification click,
 * if push message is configured to open app.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * You can detect opening default launcher application activity via AppMetrica Push notification using
     * {@link Intent} action.
     * If you don't define deeplink or uri as push notification, default launcher activity will be opened with
     * action defined in {@link YandexMetricaPush#OPEN_DEFAULT_ACTIVITY_ACTION}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (YandexMetricaPush.OPEN_DEFAULT_ACTIVITY_ACTION.equals(action)) {
            //It's push from AppMetrica Push SDK. Got it.
            handlePayload(getIntent());
        }
    }

    /**
     * Open-app push messages can contain user defined payload. It can be extracted from intent
     * as {@code String} with {@link YandexMetricaPush#EXTRA_PAYLOAD} constant.
     */
    private void handlePayload(final Intent intent) {
        String payload = intent.getStringExtra(YandexMetricaPush.EXTRA_PAYLOAD);
        if (!TextUtils.isEmpty(payload)) {
            TextView tvPayload = (TextView) findViewById(R.id.tv_payload);
            tvPayload.setText(payload);
        }
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        handlePayload(intent);
    }
}
