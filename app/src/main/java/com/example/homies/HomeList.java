package com.example.homies;

import android.location.Address;
import android.location.Geocoder;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeList
{
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


    private void saveHomeImageToFirebase(String picID) throws FileNotFoundException {
        // Write a message to the database
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Save homeProfiles to "homeProfiles" node
        StorageReference imagesStorageRef = storage.getReference("Images");
        StorageReference homeImagesRef = imagesStorageRef.child("HomeImages");

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
                file.delete();
            }
        });
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

    public ArrayList<Home> addHomeProfileToList(Home home) throws FileNotFoundException {
        addHome(home);
        saveHomeDataToFirebase();
        saveHomeImageToFirebase(home.getProfilePicture());
        return homeProfilesList;
    }

    public HomeList addHome(Home home)
    {
        this.homeProfilesList.add(home) ;
        return this;
    }
}
