package com.example.sergbek.pushnotifications.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sergbek.pushnotifications.R;
import com.example.sergbek.pushnotifications.service.RegistrationIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private Button mButtonGetToken;
    private Button mButtonShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineComponents();
        setListeners();

        if (!isStateInternet())
            Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View v) {
        if (v==mButtonGetToken){
            if (checkPlayServices())
                startRegService();
        }

        if (v==mButtonShowList)
            startActivity(new Intent(this,SecondActivity.class));
    }

    private void defineComponents() {
        mButtonGetToken = (Button) findViewById(R.id.btn_AM);
        mButtonShowList= (Button) findViewById(R.id.btn_showList_AM);
    }

    private void setListeners() {
        mButtonGetToken.setOnClickListener(this);
        mButtonShowList.setOnClickListener(this);
    }

    private void startRegService() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.d(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    private boolean isStateInternet(){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

}
