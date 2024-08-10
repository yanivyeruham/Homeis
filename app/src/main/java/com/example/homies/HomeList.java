package com.example.homies;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeList
{
    MainActivity mainClass;
    public ArrayList<Home> homeProfilesList = new ArrayList<>();

    public HomeList()
    {
   //     mainClass = mainActivity;
    }

    private void saveHomeDataToFirebase()
    {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Save homeProfiles to "homeProfiles" node
        DatabaseReference homeProfilesRef = database.getReference("HomeProfiles");
        homeProfilesRef.setValue(homeProfilesList);
    }

    public void readAllHomesFromDatabase() {
        DatabaseReference homeProfilesRef = FirebaseDatabase.getInstance().getReference("HomeProfiles");
        homeProfilesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                homeProfilesList.clear(); // Clear the list to avoid duplicates

                if (!snapshot.exists()) {
             //       mainClass.toast("No home profiles found");
                    return;
                }

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Home home = dataSnapshot.getValue(Home.class);
                    if (home != null) {
                        homeProfilesList.add(home);
                    } else {
             //           mainClass.toast("Error: Home data is null");
                    }
                }

          //      mainClass.toast("Homes loaded: " + homeProfilesList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            //    mainClass.toast("Failed to read homes: " + error.getMessage());
            }
        });
    }

    public ArrayList<Home> addHomeProfileToList(Home home)
    {
        addHome(home);
        saveHomeDataToFirebase();
        return homeProfilesList;
    }

    public HomeList addHome(Home home)
    {
        this.homeProfilesList.add(home) ;
        return this;
    }
}
