package com.example.homies;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PersonList
{
    //MainActivity mainClass;
    public ArrayList<Person> renterProfilesList = new ArrayList<>();

    private void savePersonDataToFirebase()
    {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Save renterProfiles to "renterProfiles" node
        DatabaseReference renterProfilesRef = database.getReference("RenterProfilesKey");
        renterProfilesRef.setValue(renterProfilesList);
    }


    public PersonList() {
        //mainClass = mainActivity;
    }

    public void readAllRentersFromDatabase() {
        DatabaseReference renterProfilesRef = FirebaseDatabase.getInstance().getReference("RenterProfilesKey");
        renterProfilesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                renterProfilesList.clear(); // Clear the list to avoid duplicates

                if (!snapshot.exists()) {
                   // mainClass.toast("No renter profiles found");
                    return;
                }

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Person person = dataSnapshot.getValue(Person.class);
                    if (person != null) {
                        renterProfilesList.add(person);
                    } else {
                        //mainClass.toast("Error: Renter data is null");
                    }
                }

                //mainClass.toast("Renters loaded: " + renterProfilesList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //mainClass.toast("Failed to read renters: " + error.getMessage());
            }
        });
    }


    public ArrayList<Person> addRenterProfileToList(Person person)
    {
        addRenter(person);
        savePersonDataToFirebase();
        return renterProfilesList;
    }



    public PersonList addRenter(Person person)
    {
        renterProfilesList.add(person) ;
        return this;
    }
}
