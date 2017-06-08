package com.gabrielrivera.gotolunch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btnSubscribe, btnUnsubscribe, btnLogToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getExtras() != null) {
            Log.d(TAG, "Seems MainActivity has data");
        }

        btnLogToken = (Button) findViewById(R.id.btn_log_token);
        btnSubscribe = (Button) findViewById(R.id.btn_subscribe);
        btnUnsubscribe = (Button) findViewById(R.id.btn_unsubscribe);

        btnLogToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d(TAG, "Firebase Token: " + token);
                Toast.makeText(getApplicationContext(), "Firebase Token " + token, Toast.LENGTH_LONG).show();
            }
        });
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Subscribing...");
                Log.d(TAG, "Subscribing to topic /topics/test");
                FirebaseMessaging.getInstance().subscribeToTopic("test");
            }
        });
        btnUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Unsubscribing from topic /topics/test");
                FirebaseMessaging.getInstance().unsubscribeFromTopic("test");
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(getIntent().getExtras() != null) {
            Log.d(TAG, "MainActivity has data on newIntent()");
        }
    }
}
