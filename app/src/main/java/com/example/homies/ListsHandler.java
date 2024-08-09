package com.example.homies;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListsHandler
{
    public ArrayList<Home> homeProfilesList = new ArrayList<>();
    public ArrayList<Person> renterProfilesList = new ArrayList<>();

    public ListsHandler()
    {

    }

    private void saveHomeDataToFirebase()
    {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Save homeProfiles to "homeProfiles" node
        DatabaseReference homeProfilesRef = database.getReference("HomeProfiles");
        homeProfilesRef.setValue(homeProfilesList).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Data was successfully written
                } else {
                    // Handle the error
                    //Log.e("FirebaseError", "Error writing data", task.getException());
                }
            }
        });

    }




    private void savePersonDataToFirebase()
    {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Save renterProfiles to "renterProfiles" node
        DatabaseReference renterProfilesRef = database.getReference("RenterProfilesKey");
        renterProfilesRef.setValue(renterProfilesList);
    }




    public ArrayList<Person> addRenterProfileToList(Person person)
    {
        addRenter(person);
        savePersonDataToFirebase();
        return renterProfilesList;
    }

    public ArrayList<Home> addHomeProfileToList(Home home)
    {
        addHome(home);
        saveHomeDataToFirebase();
        return homeProfilesList;
    }

    public ListsHandler addRenter(Person person)
    {
        renterProfilesList.add(person) ;
        return this;
    }

    public ListsHandler addHome(Home home)
    {
        this.homeProfilesList.add(home) ;
        return this;
    }
}
