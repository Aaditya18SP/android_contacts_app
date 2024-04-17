package com.example.expensemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.IOException;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddFriend extends AppCompatActivity { //implements View.OnClickListener{
    static final int TOGALLERY_REQ_CODE = 200;
    Toolbar toolbar;
    recviewadapter adapter; //= new recviewadapter(this);
    CircleImageView ivprofilepic;
    ImageView ivAddfrndcalendaricon;
    TextView tvFirstname, tvLastname, tvBirthdate, tvPhoneno, tvEmailid, tvAddress, tvInstaid, tvHobby, tvFavmusician, tvFavcolor, tvFavmovie;
    EditText etFirstname, etLastname, etBirthdate, etPhoneno, etWhatsappno, etEmailid, etAddress, etInstaid, etHobby, etFavmusician, etFavcolor, etFavmovie;
    String firstname, lastname, birthdate, phoneno, whatsappno, emailid, address, instaid, hobby, favmusician, favcolor, favmovie;
    Uri profilepic;
    Bitmap bitmap_profilepic;
    CheckBox cbSameWhatsappnoAsPhoneno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);


        toolbar = findViewById(R.id.tbAddfriendactivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);



       /* tvFirstname=findViewById(R.id.tvFirstname);
        tvLastname=findViewById(R.id.tvLastname);
        tvBirthdate =findViewById(R.id.tvBirthdate);
        tvPhoneno=findViewById(R.id.tvPhoneno);
        tvEmailid=findViewById(R.id.tvEmailid);
        tvAddress=findViewById(R.id.tvAddress);
        tvInstaid=findViewById(R.id.tvInstaid);
        tvHobby=findViewById(R.id.tvHobby);
        tvFavmusician=findViewById(R.id.tvFavmusician);
        tvFavcolor=findViewById(R.id.tvFavcolor);
        tvFavmovie=findViewById(R.id.tvFavmovie);

        */

        ivprofilepic = findViewById(R.id.ivProfilepic);
        ivAddfrndcalendaricon = findViewById(R.id.ivAddfrndcalendaricon);

        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etBirthdate = findViewById(R.id.etBirthdate);
        etPhoneno = findViewById(R.id.etPhoneno);
        etWhatsappno = findViewById(R.id.etWhatsappno);
        etEmailid = findViewById(R.id.etEmailid);
        etAddress = findViewById(R.id.etAddress);
        etInstaid = findViewById(R.id.etInstaid);
        etHobby = findViewById(R.id.etHobby);
        etFavmusician = findViewById(R.id.etFavmusician);
        etFavcolor = findViewById(R.id.etFavcolor);
        etFavmovie = findViewById(R.id.etFavmovie);

        cbSameWhatsappnoAsPhoneno = findViewById(R.id.cbSameWhatsappnoAsPhoneno);

        cbSameWhatsappnoAsPhoneno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            //phoneno = etPhoneno.getText().toString();
            //String aftercheckoxclicked_whatsappno=etWhatsappno.getText().toString();
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String aftercheckoxclicked_phoneno = etPhoneno.getText().toString();
                if (b && !aftercheckoxclicked_phoneno.isEmpty()) {
                    etWhatsappno.setText(aftercheckoxclicked_phoneno);
                    whatsappno = aftercheckoxclicked_phoneno;
                } else {
                    Toast.makeText(AddFriend.this, "Enter a phone number first", Toast.LENGTH_SHORT).show();
                }
            }
        });


//INTENT TO GALLERY
        ivprofilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tophotogallery = new Intent(Intent.ACTION_GET_CONTENT);
                tophotogallery.setType("image/*");
                startActivityForResult(tophotogallery, TOGALLERY_REQ_CODE);
            }
        });

        //increase the width of address input field
        etAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                int max_width;
                max_width = toolbar.getWidth();
                etAddress.setWidth(max_width);
            }
        });

        ivAddfrndcalendaricon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddFriend.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        birthdate = i2 + "/" + i1 + "/" + i;
                        etBirthdate.setText(birthdate);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });
       /* etBirthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

            }
        });

        */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addfrndmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        firstname = etFirstname.getText().toString();
        lastname = etLastname.getText().toString();
        birthdate = etBirthdate.getText().toString();
        phoneno = etPhoneno.getText().toString();
        whatsappno = etWhatsappno.getText().toString();
        emailid = etEmailid.getText().toString();
        address = etAddress.getText().toString();
        instaid = etInstaid.getText().toString();
        hobby = etHobby.getText().toString();
        favmusician = etFavmusician.getText().toString();
        favcolor = etFavcolor.getText().toString();
        favmovie = etFavmovie.getText().toString();


        if (item.getItemId() == R.id.menuSaveitem) {
            //checking for empty fields

            if (firstname.isEmpty()) {
                Toast.makeText(this, "Enter first name", Toast.LENGTH_SHORT).show();
                return false;
            } else if (lastname.isEmpty()) {

                Toast.makeText(this, "Enter last name", Toast.LENGTH_SHORT).show();
                return false;
            } else if (phoneno.isEmpty()) {
                Toast.makeText(this, "Enter phoneno", Toast.LENGTH_SHORT).show();
                return false;
            } else if (whatsappno.isEmpty()) {
                Toast.makeText(this, "Enter whatsappno", Toast.LENGTH_SHORT).show();
                return false;
            } else if (birthdate.isEmpty()) {
                Toast.makeText(this, "Enter birthdate", Toast.LENGTH_SHORT).show();
                return false;
            } else if (emailid.isEmpty()) {
                Toast.makeText(this, "Enter email id", Toast.LENGTH_SHORT).show();
                return false;
            } else if (address.isEmpty()) {
                Toast.makeText(this, "Enter address", Toast.LENGTH_SHORT).show();
                return false;
            } else if (instaid.isEmpty()) {
                Toast.makeText(this, "Enter instagram id", Toast.LENGTH_SHORT).show();
                return false;
            } else if (hobby.isEmpty()) {
                Toast.makeText(this, "Enter Hobby", Toast.LENGTH_SHORT).show();
                return false;
            } else if (favmusician.isEmpty()) {
                Toast.makeText(this, "Enter favourite musician", Toast.LENGTH_SHORT).show();
                return false;
            } else if (favcolor.isEmpty()) {
                Toast.makeText(this, "Enter favourite colour", Toast.LENGTH_SHORT).show();
                return false;
            } else if (favmovie.isEmpty()) {
                Toast.makeText(this, "Enter favourite movie", Toast.LENGTH_SHORT).show();
                return false;
            } else if (profilepic == null) {
                Toast.makeText(this, "select a profile pic ", Toast.LENGTH_SHORT).show();
                return false;
            } else {//form validation

                if (phoneno.length() < 10) {
                    Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (whatsappno.length() < 10) {
                    Toast.makeText(this, "Enter a valid Whatsapp number", Toast.LENGTH_SHORT).show();
                    return false;
                } else if (!emailid.contains("@") || !emailid.contains(".")) {
                    Toast.makeText(this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    phoneno = "+91 " + phoneno;
                    whatsappno = "+91 " + whatsappno;

                    // recviewadapter.friends.add(new Friend(profilepic,firstname,lastname,birthdate,phoneno,whatsappno,emailid,address,instaid,hobby,favmusician,favcolor,favmovie)); //appends to the friend list
                    recviewadapter.friends.add(new Friend(bitmap_profilepic, firstname, lastname, birthdate, phoneno, whatsappno, emailid, address, instaid, hobby, favmusician, favcolor, favmovie)); //BITMAP IMAGE, appends to the friend list

                    setResult(RESULT_OK);
                    Toast.makeText(this, "Friend added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    return true;

                }


            }
            //setResult(RESULT_CANCELED);

            //return true;
            //finish();
        } else {
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TOGALLERY_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                Uri selectedimageUri = data.getData();
                if (selectedimageUri != null) {
                    profilepic = selectedimageUri;
                    try {
                        bitmap_profilepic = MediaStore.Images.Media.getBitmap(this.getContentResolver(), profilepic);
                        ivprofilepic.setImageBitmap(bitmap_profilepic);
                    } catch (IOException e) {
                        Log.d("BITMAP ERROR", e.toString());
                    }

                    //ivprofilepic.setImageURI(profilepic);

                }
            }
        }
    }


}
