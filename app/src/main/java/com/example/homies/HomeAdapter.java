package com.example.homies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

/*public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>
{

    private ListsHandler profileLists = new ListsHandler();
    private CallBacks homeCallBack;

    public HomeAdapter setRenterCallBack(CallBacks homeCallBack) {
        this.homeCallBack = homeCallBack;
        return this;
    }

    public HomeAdapter(ArrayList<HomeProfileActivity> homeProfiles)
    {
    }


    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_home_item,parent,false);
        return new HomeAdapter.HomeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return profileLists.homeProfiles == null ? 0 :profileLists.homeProfiles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position)
    {
        HomeProfileActivity homeProfile = getItem(position);


        ImageLoader.getInstance().load(homeProfile.getProfilePicture(),holder.profile_IMG_home);
        holder.profile_LBL_city.setText(homeProfile.getCity());
        holder.profile_LBL_rooms_number.setText(homeProfile.getNumberOfRooms());
        holder.profile_LBL_street.setText(homeProfile.getStreet());
        holder.profile_LBL_apartment_size.setText(homeProfile.getApartmentSize());
        holder.profile_LBL_price.setText(homeProfile.getPrice());

        if(homeProfile.isFavorite())
        {
            holder.profile_IMG_favorite.setImageResource(R.drawable.heart);
        }
        else {
            holder.profile_IMG_favorite.setImageResource(R.drawable.empty_heart);
        }

    }

    private HomeProfileActivity getItem(int position)
    {
        return profileLists.homeProfiles.get(position);
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


            profile_IMG_favorite.setOnClickListener(v->{
                if(homeCallBack != null)
                {
                    homeCallBack.favoriteHomeButtonClicked(getItem(getAdapterPosition()),getAdapterPosition());
                }
            });

        }
    }

}*/
