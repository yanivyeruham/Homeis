package com.example.homies;

import static com.example.homies.MainActivity.homeList;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ImageButton maps_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        findViews();
        initViews();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initViews()
    {
        maps_back.setOnClickListener(v->returnToMainActivity());
    }

    private void returnToMainActivity()
    {
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
        finish();
    }

    private void findViews()
    {
        maps_back = findViewById(R.id.maps_back);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Get the LatLng list from the static homeList
        List<LatLng> latLngList = getLatLngListFromHomes(this);

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

    private List<LatLng> getLatLngListFromHomes(Context context) {
        Geocoder geocoder = new Geocoder(context);
        List<LatLng> latLngList = new ArrayList<>();

        for (Home home : homeList.homeProfilesList)
        {
            LatLng location = getLocationFromAddress(this, home.getStreet(), home.getCity(), String.valueOf(home.getPostalCode()));
            if (location != null )
            {
                latLngList.add(location);
            }
        }

        return latLngList;
    }

    public LatLng getLocationFromAddress(Context context, String street, String city, String postalCode) {
        Geocoder geocoder = new Geocoder(context);
        String addressString = street + ", " + city + ", " + postalCode + ", Israel";

        try {
            List<Address> addressList = geocoder.getFromLocationName(addressString, 1);
            if (addressList != null && !addressList.isEmpty()) {
                Address location = addressList.get(0);
                return new LatLng(location.getLatitude(), location.getLongitude());
            } else {
                // Handle case where no location is found
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException (e.g., log it or show a message)
            return null;
        }
    }

    public void addMarkerWithCustomIcon(GoogleMap map, LatLng location, String title) {
        map.addMarker(new MarkerOptions()
                .position(location)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.id.main_IMG_home_logo)) // Replace with your image
        );
    }
}

