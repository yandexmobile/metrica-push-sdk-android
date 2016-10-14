package com.yandex.metrica.push.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.yandex.metrica.push.YandexMetricaPush;

public class SilentPushReceiver extends BroadcastReceiver {

    /**
     * Silent push message can contain user defined payload. It can be extracted from intent
     * as {@code String} with {@link YandexMetricaPush#EXTRA_PAYLOAD} constant.
     */
    @Override
    public void onReceive(final Context context, final Intent intent) {
        String payload = intent.getStringExtra(YandexMetricaPush.EXTRA_PAYLOAD);
        StringBuilder sb = new StringBuilder("Silent push received.");
        if (!TextUtils.isEmpty(payload)) {
            sb.append("\nPayload: ").append(payload);
        }
        Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
    }
}
