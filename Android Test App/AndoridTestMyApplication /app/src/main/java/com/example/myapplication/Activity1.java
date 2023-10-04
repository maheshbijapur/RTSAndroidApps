package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myapplication.R;
import com.marketo.Marketo;
import com.marketo.MarketoActionMetaData;

//import com.marketo.MarketoCart;
import com.marketo.MarketoLead;
import com.marketo.errors.MktoException;


public class Activity1 extends Activity {

    private int mCurrentPosition = 0;
    private TextView mBufferingTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_screen);
        final EditText lead_id = findViewById(R.id.lead_email);
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Marketo marketoSdk = Marketo.getInstance(getApplicationContext());
                MarketoLead mLead = new MarketoLead();
                try {
                    mLead.setCity("CITY");
                    mLead.setFirstName("first_Name");
                    mLead.setLastName("lastName");
                    mLead.setEmail( lead_id.getText().toString());
                    mLead.setCustomField("Field","Value");
                    marketoSdk.associateLead(mLead);

                    Toast.makeText(Activity1.this, "Login Successful!", Toast.LENGTH_LONG).show();

//                    //Custom event
//                    MarketoActionMetaData meta = new MarketoActionMetaData();
//                    meta.setActionType("Shopping");
//                    meta.setActionDetails("RedShirt");
//                    meta.setActionLength("20");
//                    meta.setActionMetric("30");
//                    Marketo.reportAction("Add To Cart", meta);
//                    Marketo.reportAll();// date user profile


                } catch (MktoException mEx) {
                    mEx.printStackTrace();
                }

//
                Intent i = new Intent(Activity1.this, Activity2.class);
                startActivity(i);

            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MarketoCart mCart = new MarketoCart();
//                Marketo marketoSdk = Marketo.getInstance(getApplicationContext());
//                mCart.setCartItems(4);
//                mCart.setCartValue(1500);
//                mCart.setCustomFieldString("phonenumber", "testdata");
//                marketoSdk.associateAbandonedCartActivity(mCart);
//                Marketo.reportAll();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private Uri getMedia(String mediaName) {

        if (URLUtil.isValidUrl(mediaName)) {
            // media name is an external URL
            return Uri.parse(mediaName);
        }
        else
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
