package com.example.homies;

import static com.example.homies.MainActivity.homeList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class FavoriteHomesActivity extends AppCompatActivity implements Serializable
{
    private RecyclerView main_LST_homes;
    public static final String KEY_HOME_PROFILES = "KEY_HOME_PROFILES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_homes);

        findViews();
        initViews();

    }

    private void initViews()
    {
        HomeAdapter homeAdapter = new HomeAdapter(homeList.homeProfilesList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_homes.setLayoutManager(linearLayoutManager);
        main_LST_homes.setAdapter(homeAdapter);
        homeAdapter.setRenterCallBack(new HomeCallBack() {

            @Override
            public void favoriteHomeButtonClicked(Home home, int position)
            {
                home.setFavorite(!home.isFavorite());
                homeAdapter.notifyItemChanged(position);
            }
        });
    }

    private void findViews()
    {
        main_LST_homes = findViewById(R.id.main_LST_homes);
    }
}