package com.example.myapplication;


import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.marketo.Marketo;

import java.util.Map;
import java.util.Set;

//import marketo.utils.MktoUtils;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static String TAG = "MyFirebaseMessagingService";
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.e("NEW_TOKEN MyFirebaseMessagingService",TAG);
        Marketo marketoSdk = Marketo.getInstance(this.getApplicationContext());
        marketoSdk.setPushNotificationToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            if (data.get("vs").contains("cid")) {
                //Parse data here
            }
            else {
                //Other SDK's goes here.
            }
        }
        Marketo marketoSdk = Marketo.getInstance(this.getApplicationContext());
        marketoSdk.showPushNotification(remoteMessage);

    }
}