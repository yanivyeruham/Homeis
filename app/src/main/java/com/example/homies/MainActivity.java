package com.example.homies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
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

    private AppCompatImageView main_TXT_circle1;
    private AppCompatImageView main_TXT_circle2;
    private AppCompatImageView main_TXT_circle3;
    private AppCompatImageView main_TXT_circle4;
    private AppCompatImageView main_TXT_pluse1;
    private AppCompatImageView main_TXT_pluse2;

    private MaterialButton main_BTN_locations;

    static HomeList homeList = new HomeList();
    static PersonList personList = new PersonList();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeList.readAllHomesFromDatabase();
        personList.readAllRentersFromDatabase();

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

        main_TXT_circle1 = findViewById(R.id.main_TXT_circle1);
        main_TXT_circle2 = findViewById(R.id.main_TXT_circle2);
        main_TXT_circle3 = findViewById(R.id.main_TXT_circle3);
        main_TXT_circle4 = findViewById(R.id.main_TXT_circle4);
        main_TXT_pluse1 = findViewById(R.id.main_TXT_pluse1);
        main_TXT_pluse2 = findViewById(R.id.main_TXT_pluse2);

        main_BTN_locations = findViewById(R.id.main_BTN_locations);

    }

    private void initViews()
    {
        main_BTN_apartment_profile.setOnClickListener(v->changeActivityToHomeProfile());
        main_BTN_person_profile.setOnClickListener(v->changeActivityToRenterProfile());
        main_BTN_favorite_renters_profiles.setOnClickListener(v->changeActivityToFavoritesRenterProfile());
        main_BTN_favorite_home_profiles.setOnClickListener(v->changeActivityToFavoritesHomeProfile());
        main_BTN_locations.setOnClickListener(v->changeActivityToMapsActivity());

    }

    private void changeActivityToMapsActivity()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        finish();
    }


    private void changeActivityToFavoritesHomeProfile()
    {
        Intent intent = new Intent(this, FavoriteHomesActivity.class);
        startActivity(intent);
        finish();
    }

    private void changeActivityToFavoritesRenterProfile()
    {
        Intent intent = new Intent(this, FavoriteRentersActivity.class);
        startActivity(intent);
        finish();
    }




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


    public void toast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }



}