package com.example.myapplication;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.marketo.Marketo;

import java.util.Map;


public class MyFCMService extends FirebaseMessagingService {


    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        //Send the tokens to all the SDK's
        //To Marketo as below
        Marketo marketoSdk = Marketo.getInstance(this.getApplicationContext());
        marketoSdk.setPushNotificationToken(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //Put condition to decide which SDK this message belongs to and send the message .
        //For Ex. we are checking for Marketo Payload and passing the message.
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            if (data.get("vs").contains("cid")) {   //You need to find out these unique Payload Identifiers from each Integrated Platform .
                Log.e("PUSH_NOTIFICATION_BELONGS_TO", "MARKETO");
                Marketo marketoSdk = Marketo.getInstance(this.getApplicationContext());
                marketoSdk.showPushNotification(remoteMessage);
            }
            else {
                //Other Platforms code goes here.
            }
                // etc..
        }
    }
}