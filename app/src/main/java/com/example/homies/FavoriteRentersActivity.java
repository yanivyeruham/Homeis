package com.example.homies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*public class FavoriteRentersActivity extends AppCompatActivity
{
    private RecyclerView main_LST_renters;

    private ListsHandler profileLists = new ListsHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_renters);

        findViews();
        initViews();

    }

    private void initViews()
    {
        RenterAdapter renterAdapter = new RenterAdapter(profileLists.getRenterProfiles());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_renters.setLayoutManager(linearLayoutManager);
        main_LST_renters.setAdapter(renterAdapter);
        renterAdapter.setRenterCallBack(new CallBacks() {
            @Override
            public void favoriteRenterButtonClicked(RenterProfileActivity renterProfile, int position)
            {
                renterProfile.setFavorite(!renterProfile.isFavorite());
                renterAdapter.notifyItemChanged(position);
            }

            @Override
            public void favoriteHomeButtonClicked(HomeProfileActivity homeProfile, int position) {

            }
        });
    }

    private void findViews()
    {
        main_LST_renters = findViewById(R.id.main_LST_renters);
    }
}*/