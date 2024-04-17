package com.example.expensemanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

public class EditFriend extends AppCompatActivity {
    private static final int TOGALLERY_FROM_EDITFRND_REQ_CODE = 300;
    Toolbar toolbar;
    CircleImageView ivEditProfilepic;
    ImageView ivEditCalendarIcon;
    TextView tvEditFirstname, tvEditLastname, tvEditBirthdate, tvEditPhoneno, tvEditEmailid, tvEditAddress, tvEditInstaid, tvEditHobby, tvEditFavmusician, tvEditFavcolor, tvEditFavmovie;
    EditText etEditFirstname, etEditLastname, etEditBirthdate, etEditPhoneno, etEditWhatsappno, etEditEmailid, etEditAddress, etEditInstaid, etEditHobby, etEditFavmusician, etEditFavcolor, etEditFavmovie;
    String firstname, lastname, birthdate, phoneno, whatsappno, emailid, address, instaid, hobby, favmusician, favcolor, favmovie;
    Uri Editprofilepic;
    CheckBox cbEditSameWhatsappnoAsPhoneno;
    int Editprofilepic2 = -1;
    int currentpositionofeditfrnd;
    Bitmap Editprofilepic3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friend);

        toolbar = findViewById(R.id.tbEditfriendactivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        //get the current position

        //FIND THE VIEWS

        ivEditProfilepic = findViewById(R.id.ivEditProfilepic);
        ivEditCalendarIcon = findViewById(R.id.ivEditCalendarIcon);

        etEditFirstname = findViewById(R.id.etEditFirstname);
        etEditLastname = findViewById(R.id.etEditLastname);
        etEditBirthdate = findViewById(R.id.etEditBirthdate);
        etEditPhoneno = findViewById(R.id.etEditPhoneno);
        etEditWhatsappno = findViewById(R.id.etEditWhatsappno);
        etEditEmailid = findViewById(R.id.etEditEmailid);
        etEditAddress = findViewById(R.id.etEditAddress);
        etEditInstaid = findViewById(R.id.etEditInstaid);
        etEditHobby = findViewById(R.id.etEditHobby);
        etEditFavmusician = findViewById(R.id.etEditFavmusician);
        etEditFavcolor = findViewById(R.id.etEditFavcolor);
        etEditFavmovie = findViewById(R.id.etEditFavmovie);

        cbEditSameWhatsappnoAsPhoneno = findViewById(R.id.cbEditSameWhatsappnoAsPhoneno);

        //GET THE DATA FROM INTENT
        // Editprofilepic = getIntent().getData();
        Editprofilepic2 = getIntent().getIntExtra("editintprofilepic", 0);
        firstname = getIntent().getStringExtra("editfirstname");
        lastname = getIntent().getStringExtra("editlastname");
        birthdate = getIntent().getStringExtra("editbirthdate");
        phoneno = getIntent().getStringExtra("editphoneno");
        phoneno = phoneno.substring(4);
        whatsappno = getIntent().getStringExtra("editwhatsappno");
        whatsappno = whatsappno.substring(4);
        emailid = getIntent().getStringExtra("editemailid");
        address = getIntent().getStringExtra("editaddress");
        instaid = getIntent().getStringExtra("editinstaid");
        hobby = getIntent().getStringExtra("edithobby");
        favmusician = getIntent().getStringExtra("editfavmusician");
        favcolor = getIntent().getStringExtra("editfavcolor");
        favmovie = getIntent().getStringExtra("editfavmovie");
        currentpositionofeditfrnd = getIntent().getIntExtra("editcurrentposition", 0);
        //Toast.makeText(this, "Currentpostion" +currentpositionofeditfrnd, Toast.LENGTH_SHORT).show();

        Editprofilepic3 = (recviewadapter.friends.get(currentpositionofeditfrnd)).getBitmap_profilepic();


        //SET THE DATA
        if (Editprofilepic3 != null) {
            ivEditProfilepic.setImageBitmap(Editprofilepic3);
        } else {
            ivEditProfilepic.setImageResource(Editprofilepic2);
            //Toast.makeText(this, "Not bitmap", Toast.LENGTH_SHORT).show();
        }
        etEditFirstname.setText(firstname);
        etEditLastname.setText(lastname);
        etEditBirthdate.setText(birthdate);
        etEditPhoneno.setText(phoneno);
        etEditWhatsappno.setText(whatsappno);
        etEditEmailid.setText(emailid);
        etEditAddress.setText(address);
        etEditInstaid.setText(instaid);
        etEditHobby.setText(hobby);
        etEditFavmusician.setText(favmusician);
        etEditFavcolor.setText(favcolor);
        etEditFavmovie.setText(favmovie);


        //SET THE LISTENERS

        cbEditSameWhatsappnoAsPhoneno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String aftercheckoxclicked_phoneno = etEditPhoneno.getText().toString();
                if (b && !aftercheckoxclicked_phoneno.isEmpty()) {
                    etEditWhatsappno.setText(aftercheckoxclicked_phoneno);
                    whatsappno = aftercheckoxclicked_phoneno;
                } else {
                    Toast.makeText(EditFriend.this, "Enter a phone number first", Toast.LENGTH_SHORT).show();
                }
            }
        });


//INTENT TO GALLERY
        ivEditProfilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tophotogalleryfromeditfrnd = new Intent(Intent.ACTION_GET_CONTENT);
                tophotogalleryfromeditfrnd.setType("image/*");
                startActivityForResult(tophotogalleryfromeditfrnd, TOGALLERY_FROM_EDITFRND_REQ_CODE);
            }
        });

        //increase the width of address input field
        etEditAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                int max_width;
                max_width = toolbar.getWidth();
                etEditAddress.setWidth(max_width);
            }
        });

        //TO OPEN THE CALENDAR WHEN CLICKED ON ICON
        ivEditCalendarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditFriend.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        birthdate = day + "/" + month + "/" + year;
                        etEditBirthdate.setText(birthdate);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addfrndmenu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        firstname = etEditFirstname.getText().toString();
        lastname = etEditLastname.getText().toString();
        birthdate = etEditBirthdate.getText().toString();
        phoneno = etEditPhoneno.getText().toString();
        whatsappno = etEditWhatsappno.getText().toString();
        emailid = etEditEmailid.getText().toString();
        address = etEditAddress.getText().toString();
        instaid = etEditInstaid.getText().toString();
        hobby = etEditHobby.getText().toString();
        favmusician = etEditFavmusician.getText().toString();
        favcolor = etEditFavcolor.getText().toString();
        favmovie = etEditFavmovie.getText().toString();


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
            } else if (Editprofilepic3 == null) {

                Toast.makeText(this, "select a profile pic from gallery", Toast.LENGTH_SHORT).show();

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

                    // recviewadapter.friends.set(currentpositionofeditfrnd, new Friend(Editprofilepic, firstname, lastname, birthdate, phoneno, whatsappno, emailid, address, instaid, hobby, favmusician, favcolor, favmovie)); //appends to the friend list
                    recviewadapter.friends.set(currentpositionofeditfrnd, new Friend(Editprofilepic3, firstname, lastname, birthdate, phoneno, whatsappno, emailid, address, instaid, hobby, favmusician, favcolor, favmovie));
                    //setResult(RESULT_OK);
                    Toast.makeText(this, "Friend edited successfully", Toast.LENGTH_SHORT).show();
                    MainActivity.adapter.notifyItemChanged(currentpositionofeditfrnd);
                    setResult(RESULT_OK);
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
        if (requestCode == TOGALLERY_FROM_EDITFRND_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                Uri selectedimageUri = data.getData();
                if (selectedimageUri != null) {
                    Editprofilepic = selectedimageUri;
                    try {
                        Editprofilepic3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Editprofilepic);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ivEditProfilepic.setImageBitmap(Editprofilepic3);
                }
            }
        }
    }


}




