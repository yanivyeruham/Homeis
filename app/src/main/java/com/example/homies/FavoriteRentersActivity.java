package com.example.homies;

import static com.example.homies.MainActivity.personList;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class FavoriteRentersActivity extends AppCompatActivity implements Serializable
{
    private RecyclerView main_LST_renters;
    private ImageButton main_BTN_back;
    public static final String KEY_RENTER_PROFILES = "KEY_RENTER_PROFILES";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_renters);


        findViews();
        initViews();

    }

    private void initViews()
    {
        main_BTN_back.setOnClickListener(v->backToMainActivity());
        RenterAdapter renterAdapter = new RenterAdapter(personList.renterProfilesList);
        //RenterAdapter renterAdapter = new RenterAdapter(personList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_renters.setLayoutManager(linearLayoutManager);
        main_LST_renters.setAdapter(renterAdapter);
        renterAdapter.setRenterCallBack(new RenterCallBack() {
            @Override
            public void favoriteRenterButtonClicked(Person person, int position)
            {
                person.setFavorite(!person.isFavorite());
                renterAdapter.notifyItemChanged(position);
            }

        });
        renterAdapter.setRenterContact(new RenterContact() {
            @Override
            public void contactRenterButtonClicked(Person person, int position) {
                // Construct the email content
                String email = person.getMail(); // Assuming Person class has a getEmail() method
                String subject = "Inquiry about your profile on Homies";
                String body = "Hello " + person.getName() + ",\n\nI am interested in your profile. Let's get in touch!\n\nBest regards";

                // Create an Intent to send the email
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setType("message/rfc822");
                emailIntent.setData(Uri.parse("mailto:" + email)); // Only email apps should handle this
          //      emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
          //      emailIntent.putExtra(Intent.EXTRA_TEXT, body);

                // Check if there's an app that can handle the intent and start the activity
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
                else
                {
                        // Notify the user and display an AlertDialog with the email address
                        new AlertDialog.Builder(FavoriteRentersActivity.this)
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

    private void backToMainActivity()
    {
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
        finish();
    }

    private void findViews()
    {
        main_LST_renters = findViewById(R.id.main_LST_renters);
        main_BTN_back = findViewById(R.id.main_BTN_back);
    }
}