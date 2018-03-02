package com.yandex.metrica.push.sample;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.yandex.metrica.push.firebase.MetricaMessagingService;

public class FirebaseMessagingMasterService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // AppMetrica Push автоматически распознает свои сообщения и обработает только их.
        new MetricaMessagingService().processPush(this, remoteMessage);

        // Реализуйте логику отправки сообщений в другие SDK.
    }
}
