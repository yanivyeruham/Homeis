package com.example.homies;

/*import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Retrieve the postal code from intent or directly here
        String postalCode = "10001"; // Replace with actual postal code

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Convert postal code to LatLng
        LatLng location = getLocationFromPostalCode(postalCode);
        if (location != null) {
            // Move the camera to the location when the map is ready
            moveCameraToLocation(location);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Set initial map settings if needed (like zoom level)
    }

    private LatLng getLocationFromPostalCode(String postalCode) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(postalCode, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();
                return new LatLng(latitude, longitude);
            } else {
                // Handle case where no location is found
                System.out.println("No location found for the given postal code.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void moveCameraToLocation(LatLng location) {
        if (mMap != null) {
            // Add a marker at the location and move the camera
            mMap.addMarker(new MarkerOptions().position(location).title("Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15)); // Zoom level 15
        }
    }
}*/