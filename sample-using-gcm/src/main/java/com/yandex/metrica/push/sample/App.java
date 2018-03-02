package com.yandex.metrica.push.sample;

import android.app.Application;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.push.YandexMetricaPush;

public class App extends Application {

    /**
     * Replace API_KEY with your unique API key. Please, read official documentation how to obtain one:
     * https://tech.yandex.com/metrica-mobile-sdk/doc/mobile-sdk-dg/concepts/android-initialize-docpage/
     */
    private static final String API_KEY = your AppMetrica api key...

    @Override
    public void onCreate() {
        super.onCreate();

        //YandexMetrica must be activated before using YandexMetricaPush
        YandexMetrica.activate(this, API_KEY);
        YandexMetrica.enableActivityAutoTracking(this);

        YandexMetricaPush.init(this);
    }
}
