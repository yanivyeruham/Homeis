package com.example.homies;

import static com.example.homies.MainActivity.homeList;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
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
        homeAdapter.setHomeContact(new HomeContact() {
            @Override
            public void contactHomeButtonClicked(Home home, int position) {
                // Construct the email content
                String email = home.getMail(); // Assuming Home class has a getContactEmail() method
                String subject = "Inquiry about the home at " + home.getStreet() + ", " + home.getCity();
                String body = "Hello,\n\nI am interested in your property located at " + home.getStreet() + ", " + home.getCity() + ". Please provide more details.\n\nBest regards,\n[Your Name]";

                // Create an Intent to send the email
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                //emailIntent.setType("message/rfc822");
                //emailIntent.setData(Uri.parse("mailto:" + email));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});// Only email apps should handle this
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);

                // Check if there's an app that can handle the intent and start the activity
                if (emailIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(Intent.createChooser(emailIntent,"Send email ..."));
                }
                else {
                    // Notify the user and display an AlertDialog with the email address
                    new AlertDialog.Builder(FavoriteHomesActivity.this)
                            .setTitle("No Email Client Installed")
                            .setMessage("No email app is installed on your device. You can manually copy the email address below:\n\n" + email)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss(); // Close the dialog when OK is clicked
                                }
                            })
                            .setCancelable(false) // Make sure the user has to click OK to dismiss
                            .show();
                }
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
    }
}