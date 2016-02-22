package com.kawakawaplanning.gps_example;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    TextView latTv;
    TextView lonTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
            }
        });
        latTv = (TextView)findViewById(R.id.textView);
        lonTv = (TextView)findViewById(R.id.textView2);
    }

    @Override
    public void onDestroy() {
        locationManager.removeUpdates(this);
    }

    //位置が変わったら呼び出される
    @Override
    public void onLocationChanged(Location location) {
        Log.i("kp", "緯度:" + location.getLatitude());
        Log.i("kp", "経度:" + location.getLongitude());

        latTv.setText("緯度:" + location.getLatitude());
        lonTv.setText("経度:" + location.getLongitude());
        //一度のみなら下の行で停止
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override
    public void onProviderEnabled(String provider){}
    @Override
    public void onProviderDisabled(String provider){}
}
