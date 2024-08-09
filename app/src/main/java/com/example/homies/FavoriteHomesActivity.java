package com.example.homies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteHomesActivity extends AppCompatActivity
{
    private RecyclerView main_LST_homes;
    private ListsHandler profileLists = new ListsHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_homes);

        //findViews();
        //initViews();

    }

    //private void initViews()
   // {
        //HomeAdapter homeAdapter = new RenterAdapter(profileLists.getRenterProfiles());
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        //main_LST_homes.setLayoutManager(linearLayoutManager);
        //main_LST_homes.setAdapter(homeAdapter);
        //homeAdapter.setRenterCallBack(new CallBacks() {
           // @Override
           // public void favoriteHomeButtonClicked(HomeProfile homeProfile, int position)
            //{
             //   homeProfile.setFavorite(!homeProfile.isFavorite());
             //   homeAdapter.notifyItemChanged(position);
           // }
       // });
   // }

    private void findViews()
    {
        main_LST_homes = findViewById(R.id.main_LST_homes);
    }
}