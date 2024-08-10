package com.example.homies;

import static com.example.homies.MainActivity.personList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class FavoriteRentersActivity extends AppCompatActivity implements Serializable
{
    private RecyclerView main_LST_renters;
    public static final String KEY_RENTER_PROFILES = "KEY_RENTER_PROFILES";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_renters);


        findViews();
        initViews();

    }

    private void initViews()
    {
        RenterAdapter renterAdapter = new RenterAdapter(personList.renterProfilesList);
        //RenterAdapter renterAdapter = new RenterAdapter(personList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        main_LST_renters.setLayoutManager(linearLayoutManager);
        main_LST_renters.setAdapter(renterAdapter);
        renterAdapter.setRenterCallBack(new RenterCallBack() {
            @Override
            public void favoriteRenterButtonClicked(Person person, int position)
            {
                person.setFavorite(!person.isFavorite());
                renterAdapter.notifyItemChanged(position);
            }

        });
    }

    private void findViews()
    {
        main_LST_renters = findViewById(R.id.main_LST_renters);
    }
}