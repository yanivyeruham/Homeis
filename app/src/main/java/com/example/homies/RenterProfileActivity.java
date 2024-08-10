package com.example.homies;

import android.content.Intent;
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
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

public class RenterProfileActivity extends AppCompatActivity
{
    public static final int MAX_LINES_COLLAPSED = 3;
    public static final int MIN_LINES_COLLAPSED = 1;


    private PersonList profileLists = new PersonList();

    private ShapeableImageView profile_IMG_favorite;
    private ShapeableImageView profile_IMG_renter;
    private MaterialTextView  main_TXT_text;
    private AppCompatImageView main_IMG_person_logo;
    private TextInputEditText main_ET_name;
    private  TextInputEditText main_ET_gender;
    private TextInputEditText main_ET_age;
    private TextInputEditText main_ET_overview;
    private MaterialButton main_BTN_save_input;
    private MaterialTextView main_TXT_add_picture;
    private MaterialButton main_BTN_choose_picture;
    private AppCompatImageView main_IMG_gallery_profile_pic;

    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_profile);

        findViews();
        registerResult();
        initViews();

    }

    private void findViews()
    {
        profile_IMG_favorite = findViewById(R.id.profile_IMG_favorite);
        profile_IMG_renter = findViewById(R.id.profile_IMG_renter);
        main_TXT_text = findViewById(R.id.main_TXT_text);
        main_IMG_person_logo = findViewById(R.id.main_IMG_person_logo);
        main_ET_name = findViewById(R.id.main_ET_name);
        main_ET_gender = findViewById(R.id.main_ET_gender);
        main_ET_age = findViewById(R.id.main_ET_age);
        main_ET_overview = findViewById(R.id.main_ET_overview);
        main_BTN_save_input = findViewById(R.id.main_BTN_save_input);
        main_IMG_gallery_profile_pic = findViewById(R.id.main_IMG_gallery_profile_pic);
        main_TXT_add_picture = findViewById(R.id.main_TXT_add_picture);
        main_BTN_choose_picture = findViewById(R.id.main_BTN_choose_picture);

    }

    private void initViews()
    {
        main_BTN_save_input.setOnClickListener(v->setLabels(
                main_ET_name.getText().toString(),
                main_ET_gender.getText().toString(),
                main_ET_age.getText().toString(),
                main_ET_overview.getText().toString()));

        main_BTN_choose_picture.setOnClickListener(v -> pickImage());

    }

    private void setLabels(String name, String gender, String age, String overview)
    {
        Person person = new Person();
        person.setName(name);
        person.setGender(gender);
        if(age == null)
        {
            person.setAge(0);
        }
        else
        {
            person.setAge(Integer.parseInt(age));
        }
        person.setOverview(overview);
        person.isDataSet = true;

        profileLists.addRenterProfileToList(person);
        changeActivityToMainActivity();
    }

    private void changeActivityToMainActivity()
    {
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
        finish();
    }

    private void pickImage()
    {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
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
                    Uri imageUri = result.getData().getData();
                    main_IMG_gallery_profile_pic.setImageURI(imageUri);

                }catch(Exception e){
                    Toast.makeText(RenterProfileActivity.this,"No Image selected",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public RenterProfileActivity()
    {

    }


}