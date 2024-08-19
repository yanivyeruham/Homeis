package com.example.homies;

import static com.example.homies.MainActivity.personList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class RenterProfileActivity extends AppCompatActivity
{
    public static final int MAX_LINES_COLLAPSED = 3;
    public static final int MIN_LINES_COLLAPSED = 1;



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
    private ImageButton main_BTN_back;
    private TextInputEditText main_ET_mail;

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
        main_BTN_back = findViewById(R.id.main_BTN_back);
        main_ET_mail = findViewById(R.id.main_ET_mail);

    }

    private void initViews()
    {
        main_BTN_save_input.setOnClickListener(v-> {
            try {
                setLabels(
                        main_ET_name.getText().toString(),
                        main_ET_gender.getText().toString(),
                        main_ET_age.getText().toString(),
                        main_ET_overview.getText().toString(),
                        main_ET_mail.getText().toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        main_BTN_choose_picture.setOnClickListener(v -> pickImage());
        main_BTN_back.setOnClickListener((v->backToMainActivity()));

    }

    private void backToMainActivity()
    {
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
        finish();
    }

    private void setLabels(String name, String gender, String age, String overview,String mail) throws FileNotFoundException {
        if(name.isEmpty() || gender.isEmpty() || age.isEmpty() || overview.isEmpty()) {
            Toast.makeText(RenterProfileActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Person person = new Person();
        person.setName(name);
        person.setGender(gender);
        person.setMail(mail);
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

        person.setProfilePicture(UUID.randomUUID().toString());

        personList.addRenterProfileToList(person);
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
                    InputStream inputStream = getContentResolver().openInputStream(imageUri);
                    File imageFile = createFileFromInputStream(inputStream, "temp_image.jpg");
                    //main_IMG_gallery_profile_pic.setImageURI(imageUri);
                    main_IMG_person_logo.setImageURI(imageUri);

                }catch(Exception e){
                    Toast.makeText(RenterProfileActivity.this,"No Image selected",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private File createFileFromInputStream(InputStream inputStream, String fileName) {
        File file = null;
        try {
            // Create a temp file in your app's cache directory
            file = new File(getCacheDir(), fileName);

            // Open output stream to write to the file
            OutputStream outputStream = new FileOutputStream(file);

            // Buffer to hold data while copying
            byte[] buffer = new byte[1024];
            int length;

            // Copy data from input stream to output stream
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            // Flush and close the output stream
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public RenterProfileActivity()
    {

    }


}