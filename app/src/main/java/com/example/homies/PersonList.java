package com.example.homies;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
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

    private void savePersonImageToFirebase(String picID) throws FileNotFoundException {
        // Write a message to the database
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Save homeProfiles to "homeProfiles" node
        StorageReference imagesStorageRef = storage.getReference("Images");
        StorageReference homeImagesRef = imagesStorageRef.child("RenterImages");

        StorageReference singleImageRef = homeImagesRef.child(picID);
        File file = new File( "/data/user/0/com.example.homies/cache/temp_image.jpg");

        if (!file.exists()) {
            Log.e("FileError", "File does not exist at the specified path.");
        }
        Uri fileUri = Uri.fromFile(file);
        UploadTask uploadTask = singleImageRef.putFile(fileUri);


        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                file.delete();
            }
        });
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


    public ArrayList<Person> addRenterProfileToList(Person person) throws FileNotFoundException {
        addRenter(person);
        savePersonDataToFirebase();
        savePersonImageToFirebase(person.getProfilePicture());
        return renterProfilesList;
    }



    public PersonList addRenter(Person person)
    {
        renterProfilesList.add(person) ;
        return this;
    }
}
