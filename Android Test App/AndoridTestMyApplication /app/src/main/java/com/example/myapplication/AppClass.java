package com.example.myapplication;

import android.app.Application;
import android.app.Notification;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.marketo.Marketo;
import com.marketo.MarketoConfig;
import com.marketo.MarketoLead;
import com.marketo.errors.MktoException;

import marketo.utils.MktoDeviceIdentifier;

//import marketo.utils.MktoDeviceIdentifier;
//import marketo.utils.MktoUtils;


public class AppClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Amazon
        Marketo marketoSdk = Marketo.getInstance(getApplicationContext());
//        marketoSdk.initializeSDK("026-BUL-349","WnYzR2dFcXJtT0MxQXFLQlNpSEQ4NGV);
        marketoSdk.initializeSDK("native", "026-BUL-349", "WnYzR2dFcXJtT0MxQXFLQlNpSEQ4NGV");
        marketoSdk.initializeMarketoPush("350312872033","MKTO");
        MarketoConfig.Notification notification = new MarketoConfig.Notification();
        notification.setNotificationSmallIcon(R.drawable.ic_pixel_icons);
        Marketo.getInstance(this).setNotificationConfig(notification);


        MarketoLead mLead = new MarketoLead();
        try {
            mLead.setCity("bengaluru");
            mLead.setFirstName("mbtestlead");
            mLead.setLastName("b");
            mLead.setEmail("testleadmb@mkto.com");
            mLead.setCustomField("Field","Value");
            marketoSdk.associateLead(mLead);
        } catch (MktoException mEx) {
            mEx.printStackTrace();
        }


/**
 *
 *
 *
        MarketoConfig.SecureMode secureMode = new MarketoConfig.SecureMode();
        secureMode.setAccessKey("");
        secureMode.setEmail("");
        secureMode.setSignature("");
        secureMode.setTimestamp(0L);
        marketoSdk.setSecureSignature(secureMode);
*/

        Log.e("UUID", MktoDeviceIdentifier.getOrCreateUUID(this));
    }
}