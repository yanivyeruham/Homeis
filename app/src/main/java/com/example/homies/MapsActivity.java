package com.example.homies;

import static com.example.homies.MainActivity.homeList;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Get the LatLng list from the static homeList
        List<LatLng> latLngList = getLatLngListFromHomes(this, homeList.homeProfilesList);

        if (latLngList.isEmpty()) {
            // Handle the case where no locations were found
            Toast.makeText(this, "No locations found", Toast.LENGTH_SHORT).show();
            return;
        }

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

        for (LatLng latLng : latLngList) {
            mMap.addMarker(new MarkerOptions().position(latLng).title("Home Location"));
            boundsBuilder.include(latLng);
        }

        // Move the camera to show all markers
        int padding = 100; // Offset from edges of the map in pixels
        LatLngBounds bounds = boundsBuilder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }

    private List<LatLng> getLatLngListFromHomes(Context context, List<Home> homes) {
        Geocoder geocoder = new Geocoder(context);
        List<LatLng> latLngList = new ArrayList<>();

        for (Home home : homes) {
            try {
                List<Address> addressList = geocoder.getFromLocationName(String.valueOf(home.getPostalCode()), 1);
                if (addressList != null && !addressList.isEmpty()) {
                    Address location = addressList.get(0);
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    latLngList.add(latLng);
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle exceptions (e.g., logging or showing an error message)
            }
        }

        return latLngList;
    }
}

