package com.example.homies;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>
{

    private final ArrayList <Home> HomesList;
    private HomeCallBack homeCallBack;
    private HomeContact homeContact;


    public HomeAdapter setRenterCallBack(HomeCallBack homeCallBack) {
        this.homeCallBack = homeCallBack;
        return this;
    }

    public HomeContact getHomeContact() {
        return homeContact;
    }

    public HomeAdapter setHomeContact(HomeContact homeContact) {
        this.homeContact = homeContact;
        return this;
    }

    public HomeAdapter(ArrayList<Home> homesList)
    {
        HomesList = homesList;
    }


    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_home_item,parent,false);
        return new HomeAdapter.HomeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return HomesList == null ? 0 :HomesList.size();
    }



    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position)
    {

        Home home = getItem(position);

        holder.contact_BTN_contact.setOnClickListener(v -> {
            if (homeContact != null) {
                homeContact.contactHomeButtonClicked(home, position);
            }
        });

        holder.profile_LBL_city.setText("City: " + home.getCity());
        holder.profile_LBL_rooms_number.setText("Number of rooms: " + String.valueOf(home.getNumberOfRooms()));
        holder.profile_LBL_street.setText("Street: " + home.getStreet() );
        holder.profile_LBL_apartment_size.setText("Apartment size: " + String.valueOf(home.getApartmentSize()) + "m");
        holder.profile_LBL_price.setText("Price: " + String.valueOf(home.getPrice()) + "$");


        if(home.isFavorite())
        {
            holder.profile_IMG_favorite.setImageResource(R.drawable.heart);
        }
        else {
            holder.profile_IMG_favorite.setImageResource(R.drawable.empty_heart);
        }

        // Create a local file where the image will be downloaded
        StorageReference imageRef = getImageFromFirebase(home.getProfilePicture());

        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Load the image using Glide
                Glide.with(holder.itemView.getContext())
                        .load(uri)
                        .centerCrop()
                        .error(R.drawable.unavailable_photo) // Optional error image
                        .into(holder.profile_IMG_home);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                holder.profile_IMG_home.setImageResource(R.drawable.unavailable_photo); // Optional error image
            }
        });

    }

    private StorageReference getImageFromFirebase(String picID)
    {
        //String picID = "59b19eec-61e6-466b-b14f-c982df4cc1c5";
        // Get a reference to the storage service, using the default Firebase App
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a reference to the file you want to download
        StorageReference imagesStorageRef = storage.getReference("Images");
        StorageReference homeImagesRef = imagesStorageRef.child("HomeImages");
        StorageReference singleImageRef = homeImagesRef.child(picID);



        return singleImageRef;
    }

    private Home getItem(int position)
    {
        return HomesList.get(position);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder
    {
        private final ShapeableImageView profile_IMG_home;
        private final CardView profile_CARD_data;

        private final ShapeableImageView profile_IMG_favorite;
        private final MaterialTextView profile_LBL_city;
        private final MaterialTextView profile_LBL_rooms_number;
        private final MaterialTextView profile_LBL_street;
        private final MaterialTextView profile_LBL_apartment_size;
        private final MaterialTextView profile_LBL_price;
        private final MaterialButton contact_BTN_contact;


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_IMG_home = itemView.findViewById(R.id.profile_IMG_home);
            profile_CARD_data = itemView.findViewById(R.id.profile_CARD_data);
            profile_IMG_favorite = itemView.findViewById(R.id.profile_IMG_favorite);
            profile_LBL_city = itemView.findViewById(R.id.profile_LBL_city);
            profile_LBL_rooms_number = itemView.findViewById(R.id.profile_LBL_rooms_number);
            profile_LBL_street = itemView.findViewById(R.id.profile_LBL_street);
            profile_LBL_apartment_size = itemView.findViewById(R.id.profile_LBL_apartment_size);
            profile_LBL_price = itemView.findViewById(R.id.profile_LBL_price);
            contact_BTN_contact = itemView.findViewById(R.id.contact_BTN_contact);

            profile_IMG_favorite.setOnClickListener(v->{
                if(homeCallBack != null)
                {
                    homeCallBack.favoriteHomeButtonClicked(getItem(getAdapterPosition()),getAdapterPosition());
                }
            });

        }

    }
}
