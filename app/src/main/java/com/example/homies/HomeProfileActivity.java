package com.example.homies;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HomeProfileActivity extends AppCompatActivity
{

    private Uri imageUri;
    private ListsHandler profileLists = new ListsHandler();

    private MaterialTextView main_TXT_text;
    private AppCompatImageView main_IMG_home_logo;
    private TextInputEditText main_ET_city;
    private TextInputEditText main_ET_street;
    private TextInputEditText main_ET_rooms_number;
    private TextInputEditText main_ET_apartment_size;
    private TextInputEditText main_ET_apartment_price;
    private MaterialButton main_BTN_save_input;
    private MaterialTextView main_TXT_add_picture;
    private MaterialButton main_BTN_choose_picture;
    private AppCompatImageView main_IMG_gallery_home_profile_pic;

    ActivityResultLauncher<Intent> resultLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_profile);

        findViews();
        registerResult();
        initViews();

    }



    private void findViews()
    {
        main_TXT_text = findViewById(R.id.main_TXT_text);
        main_IMG_home_logo = findViewById(R.id.main_IMG_home_logo);
        main_ET_city = findViewById(R.id.main_ET_city);
        main_ET_street = findViewById(R.id.main_ET_street);
        main_ET_rooms_number = findViewById(R.id.main_ET_rooms_number);
        main_ET_apartment_size = findViewById(R.id.main_ET_apartment_size);
        main_ET_apartment_price = findViewById(R.id.main_ET_apartment_price);
        main_BTN_save_input = findViewById(R.id.main_BTN_save_input);
        main_TXT_add_picture = findViewById(R.id.main_TXT_add_picture);
        main_BTN_choose_picture = findViewById(R.id.main_BTN_choose_picture);
        main_IMG_gallery_home_profile_pic = findViewById(R.id.main_IMG_gallery_home_profile_pic);



    }
    private void initViews()
    {
        main_BTN_save_input.setOnClickListener(v-> {
            try {
                setLabels(
                        main_ET_city.getText().toString(),
                        main_ET_street.getText().toString(),
                        main_ET_rooms_number.getText().toString(),
                        main_ET_apartment_size.getText().toString(),
                        main_ET_apartment_price.getText().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        main_BTN_choose_picture.setOnClickListener(v->pickImage());

    }

    private void pickImage()
    {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    private void setLabels(String city, String street, String numberOfRooms, String Size, String price) throws IOException {

        Home home = new Home();
        home.setCity(city);
        home.setStreet(street);
        home.setNumberOfRooms(Integer.parseInt(numberOfRooms));
        home.setApartmentSize(Integer.parseInt(Size));
        home.setPrice(Integer.parseInt(price));
        home.isDataSet = true;

        Bitmap bmp =MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        home.setImage(baos.toByteArray());


        profileLists.addHomeProfileToList(home);


        changeActivityToMainActivity();

    }




    private void registerResult()
    {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
        {
            @Override
            public void onActivityResult(ActivityResult result)
            {
                try
                {
                    imageUri = result.getData().getData();
                    main_IMG_gallery_home_profile_pic.setImageURI(imageUri);

                }catch(Exception e){
                    Toast.makeText(HomeProfileActivity.this,"No Image selected",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private void changeActivityToMainActivity()
    {
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
        finish();
    }
}