package com.example.homies;

import static com.example.homies.MainActivity.homeList;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class FavoriteHomesActivity extends AppCompatActivity implements Serializable
{
    private RecyclerView main_LST_homes;
    private ImageButton main_BTN_back;
    private MaterialButton main_BTN_locations;
    public static final String KEY_HOME_PROFILES = "KEY_HOME_PROFILES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_homes);

        findViews();
        initViews();

    }

    private void initViews()
    {
        main_BTN_back.setOnClickListener(v->backToMainActivity());
        //main_BTN_locations.setOnClickListener(v->changeActivityToGoogleMaps());

        HomeAdapter homeAdapter = new HomeAdapter(homeList.homeProfilesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_homes.setLayoutManager(linearLayoutManager);
        main_LST_homes.setAdapter(homeAdapter);
        homeAdapter.setRenterCallBack(new HomeCallBack() {

            @Override
            public void favoriteHomeButtonClicked(Home home, int position)
            {
                home.setFavorite(!home.isFavorite());
                homeAdapter.notifyItemChanged(position);
            }
        });
    }

    /*private void changeActivityToGoogleMaps()
    {
        Intent j = new Intent(this, MapsActivity.class);
        startActivity(j);
        finish();
    }*/

    private void backToMainActivity()
    {
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
        finish();
    }

    private void findViews()

    {
        main_LST_homes = findViewById(R.id.main_LST_homes);
        main_BTN_back = findViewById(R.id.main_BTN_back);
        main_BTN_locations = findViewById(R.id.main_BTN_locations);
    }
}