package com.example.homies;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class RenterAdapter extends RecyclerView.Adapter<RenterAdapter.RenterViewHolder>
{
    private final ArrayList <Person> personsList;
    private RenterCallBack renterCallBack;

    public RenterAdapter setRenterCallBack(RenterCallBack renterCallBack) {
        this.renterCallBack = renterCallBack;
        return this;
    }

    public RenterAdapter(ArrayList<Person> personsList)
    {
        this.personsList = personsList;
    }

    @NonNull
    @Override
    public RenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_person_item,parent,false);
        return new RenterViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return personsList == null ? 0 :personsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RenterViewHolder holder, int position)
    {
        Person person = getItem(position);

        StorageReference imageRef = getImageFromFirebase(person.getProfilePicture());

        //ImageLoader.getInstance().load(person.getProfilePicture(),holder.profile_IMG_renter);
        holder.profile_LBL_name.setText("Name: " + person.getName());
        holder.profile_LBL_gender.setText("Gender: " + person.getGender());
        holder.profile_LBL_age.setText("Age: " + String.valueOf(person.getAge()) + " years");
        holder.profile_LBL_personal_info.setText("Personal Info: " + person.getOverview());



        if(person.isFavorite())
        {
        holder.profile_IMG_favorite.setImageResource(R.drawable.heart);
        }
        else {
            holder.profile_IMG_favorite.setImageResource(R.drawable.empty_heart);
        }

        holder.profile_CARD_data.setOnClickListener(v-> {
            ArrayList<ObjectAnimator> animations = new ArrayList<>();
            if (person.isCollapsed()) {
                animations.add(ObjectAnimator
                        .ofInt(holder.profile_LBL_personal_info, "maxLines", holder.profile_LBL_personal_info.getLineCount())
                        .setDuration((Math.max(holder.profile_LBL_personal_info.getLineCount() - RenterProfileActivity.MAX_LINES_COLLAPSED, 0)) * 50L));
            } else {
                animations.add(ObjectAnimator
                        .ofInt(holder.profile_LBL_personal_info, "maxLines", RenterProfileActivity.MAX_LINES_COLLAPSED)
                        .setDuration((Math.max(holder.profile_LBL_personal_info.getLineCount() - RenterProfileActivity.MAX_LINES_COLLAPSED, 0)) * 50L));
            }
            animations.forEach(ObjectAnimator::start);

            person.setCollapsed(!person.isCollapsed());
        });

        // Create a local file where the image will be downloaded
        File localFile = new File("/data/user/0/com.example.homies/cache/",  "downloaded_image.jpg");

        imageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Successfully downloaded data to local file
                // Load the image into the ImageView
                Glide.with(holder.itemView.getContext())
                        .load(localFile)
                        .centerCrop()
                        .into(holder.profile_IMG_renter);
                localFile.delete();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.e("DownloadError", "Image download failed", exception);

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
        StorageReference homeImagesRef = imagesStorageRef.child("RenterImages");
        StorageReference singleImageRef = homeImagesRef.child(picID);



        return singleImageRef;
    }

    private Person getItem(int position)
    {
        return personsList.get(position);
    }

    public class RenterViewHolder extends RecyclerView.ViewHolder
    {
        private final CardView profile_CARD_data;
        private final ShapeableImageView profile_IMG_favorite;
        private final ShapeableImageView profile_IMG_renter;
        private final MaterialTextView profile_LBL_name;
        private final MaterialTextView  profile_LBL_gender;
        private final MaterialTextView  profile_LBL_age;
        private final MaterialTextView  profile_LBL_personal_info;


        public RenterViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_CARD_data = itemView.findViewById(R.id.profile_CARD_data);
            profile_IMG_favorite = itemView.findViewById(R.id.profile_IMG_favorite);
            profile_IMG_renter = itemView.findViewById(R.id.profile_IMG_renter);
            profile_LBL_name = itemView.findViewById(R.id.profile_LBL_name);
            profile_LBL_gender = itemView.findViewById(R.id.profile_LBL_gender);
            profile_LBL_age = itemView.findViewById(R.id.profile_LBL_age);
            profile_LBL_personal_info = itemView.findViewById(R.id.profile_LBL_personal_info);


            profile_IMG_favorite.setOnClickListener(v->{
                if(renterCallBack != null)
                {
                    renterCallBack.favoriteRenterButtonClicked(getItem(getAdapterPosition()),getAdapterPosition());
                }
            });
            
        }
    }
}
