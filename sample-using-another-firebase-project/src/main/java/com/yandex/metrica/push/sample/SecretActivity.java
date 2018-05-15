package com.yandex.metrica.push.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.push.YandexMetricaPush;

/**
 * Activity that can be opened by deeplink.
 * If push message configured to open deeplink (with 'yandexpush' scheme for example), YandexMetricaPush
 * will create a notification that will open on click matching deeplink activity (SecretActivity).
 */
public class SecretActivity extends AppCompatActivity {

    private TextView mTvDeeplink;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        mTvDeeplink = (TextView) findViewById(R.id.tv_deeplink);

        if (savedInstanceState != null) {
            YandexMetrica.reportAppOpen(this);
        }

        Intent intent = getIntent();

        handleDeeplink(intent);
        handlePayload(intent);
    }

    /**
     * Deeplink can be extracted from open intent.
     */
    private void handleDeeplink(final Intent intent) {
        Uri uri = intent.getData();
        if (uri != null && !TextUtils.isEmpty(uri.getHost())) {
            mTvDeeplink.append(String.format("\nDeeplink host: %s", uri.getHost()));
            YandexMetrica.reportEvent("Open deeplink");
        }
    }

    /**
     * Deeplink push message can contain user defined payload. It can be extracted from intent
     * as {@code String} with {@link YandexMetricaPush#EXTRA_PAYLOAD} constant.
     * This is example method how to do this.
     */
    private void handlePayload(final Intent intent) {
        String payload = intent.getStringExtra(YandexMetricaPush.EXTRA_PAYLOAD);
        if (!TextUtils.isEmpty(payload)) {
            mTvDeeplink.append(String.format("\nPayload: %s", payload));
            YandexMetrica.reportEvent("Handle payload");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        YandexMetrica.reportAppOpen(this);
        handleDeeplink(intent);
        handlePayload(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        YandexMetrica.resumeSession(this);
    }

    @Override
    protected void onPause() {
        YandexMetrica.pauseSession(this);
        super.onPause();
    }
}
