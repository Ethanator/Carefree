package com.carefree.carefree;

/**
 * Created by Normn on 4/12/2014.
 */

import android.app.Activity;
        import android.content.Context;
        import android.location.Criteria;
        import android.location.Location;
        import android.location.LocationListener;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.widget.TextView;
        import android.widget.Toast;

public class LocationActivity extends Activity implements LocationListener {
    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;


    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_location);
        latituteField = (TextView) findViewById(R.id.textView);
        longitudeField = (TextView) findViewById(R.id.textView2);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // getting GPS status
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        // Define the criteria how to select the location provider -> use
        // default

        if (!isGPSEnabled && !isNetworkEnabled) {
            latituteField.setText("Location not available");
            longitudeField.setText("Location not available");

            //Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
           // startActivity(intent);
        } else {
            // First get location from Network Provider
            if (isNetworkEnabled) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

                if (locationManager != null) {
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        //lat = location.getLatitude();
                        // lng = location.getLongitude();
                        System.out.println("Provider Network has been selected.");
                        onLocationChanged(location);
                    }
                }
            }
            //get the location by gps
            if (isGPSEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                if (locationManager != null) {
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        //lat = location.getLatitude();
                        // lng = location.getLongitude();
                        System.out.println("Provider GPS Provider has been selected.");
                        onLocationChanged(location);
                    }
                }
            }
        }

       /* Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latituteField.setText("Location not available");
            longitudeField.setText("Location not available");
        }*/
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        int lat = (int) (location.getLatitude());
        int lng = (int) (location.getLongitude());
        latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}