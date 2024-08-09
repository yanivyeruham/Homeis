package com.example.homies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import com.google.android.material.textview.MaterialTextView;

public class MainActivity extends AppCompatActivity
{
    private ImageButton main_BTN_apartment_profile;
    private ImageButton main_BTN_person_profile ;
    private MaterialTextView main_TXT_home;
    private MaterialTextView main_TXT_person;
    private ImageButton main_BTN_favorite_renters_profiles;
    private ImageButton main_BTN_favorite_home_profiles;
    private MaterialTextView main_TXT_renter_profiles_favorites;
    private MaterialTextView main_TXT_home_profiles_favorites;

    private HomeProfileActivity homeProfile = new HomeProfileActivity();
    private RenterProfileActivity renterProfile = new RenterProfileActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViews();
    }
    private void findViews()
    {
        main_BTN_apartment_profile = findViewById(R.id.main_BTN_apartment_profile);
        main_BTN_person_profile = findViewById(R.id.main_BTN_person_profile);
        main_TXT_home = findViewById(R.id.main_TXT_home);
        main_TXT_person = findViewById(R.id.main_TXT_person);
        main_BTN_favorite_renters_profiles = findViewById(R.id.main_BTN_favorite_renters_profiles);
        main_BTN_favorite_home_profiles = findViewById(R.id.main_BTN_favorite_home_profiles);
        main_TXT_renter_profiles_favorites = findViewById(R.id.main_TXT_renter_profiles_favorites);
        main_TXT_home_profiles_favorites = findViewById(R.id.main_TXT_home_profiles_favorites);
    }

    private void initViews()
    {
        main_BTN_apartment_profile.setOnClickListener(v->changeActivityToHomeProfile());
        main_BTN_person_profile.setOnClickListener(v->changeActivityToRenterProfile());
        //main_BTN_favorite_renters_profiles.setOnClickListener(v->changeActivityToFavoritesRenterProfile());
        //main_BTN_favorite_home_profiles.setOnClickListener(v->changeActivityToFavoritesHomeProfile());

    }

   /* private void changeActivityToFavoritesHomeProfile()
    {
        Intent i = new Intent(this, FavoriteHomesActivity.class);
        startActivity(i);
        finish();
    }

    private void changeActivityToFavoritesRenterProfile()
    {
        Intent i = new Intent(this, FavoriteRentersActivity.class);
        startActivity(i);
        finish();
    }*/

    private void changeActivityToHomeProfile()
    {

        Intent i = new Intent(this, HomeProfileActivity.class);
        startActivity(i);
        finish();
    }

    private void changeActivityToRenterProfile()
    {

        Intent j = new Intent(this, RenterProfileActivity.class);
        startActivity(j);
        finish();

    }


    private void toast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}