package com.example.expensemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class DisplayFriend extends AppCompatActivity {
    static final int TODIALER_REQ_CODE = 10;
    static final int TOEMAIL_REQ_CODE = 11;
    static final int TOINSTAGRAM_REQ_CODE = 12;
    static final int RETURNTOMAINACTIVITY_RESULT_CODE = 150;
    static final int TOEDITFRIEND_REQ_CODE = 300;

    Toolbar toolbar;
    CircleImageView ivDisplayprofilepic;
    ImageView ivDisplayCallIcon, ivDisplayEmailIcon, ivDisplayInstaIcon, ivDisplayWhatsappIcon;
    TextView tvDisplayFirstLastName, tvDisplayBirthdate, tvDisplayPhoneno, tvDisplayWhatsappno, tvDisplayEmailid, tvDisplayAddress, tvDisplayHobby, tvDisplayFavMusician, tvDisplayFavColor, tvDisplayFavMovie;
    String instaid, firstname, lastname, birthdate, phoneno, whatsappno, emailid, address, hobby, favmusician, favcolor, favmovie;
    int int_profilepic = -1;
    int currentposition;
    Uri pic2;
    Bitmap pic3;

    public String getPathFromURI(Context context, Uri contentUri) {
        Cursor mediacursor = null;
        try {
            String[] datapath = {MediaStore.Images.Media.DATA};
            mediacursor = context.getContentResolver().query(contentUri, datapath, null, null, null);
            int colum_index = mediacursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            mediacursor.moveToFirst();
            return mediacursor.getString(colum_index);

        } finally {
            if (mediacursor != null) {
                mediacursor.close();
            }
        }
    }

    public void getdatafromintent() {
        int_profilepic = getIntent().getIntExtra("profilepic", 0);
        firstname = getIntent().getStringExtra("firstname");
        lastname = getIntent().getStringExtra("lastname");
        birthdate = getIntent().getStringExtra("birthdate");
        phoneno = getIntent().getStringExtra("phoneno");
        whatsappno = getIntent().getStringExtra("whatsappno");
        emailid = getIntent().getStringExtra("emailid");
        address = getIntent().getStringExtra("address");
        instaid = getIntent().getStringExtra("instaid");
        hobby = getIntent().getStringExtra("hobby");
        favmusician = getIntent().getStringExtra("favmusician");
        favcolor = getIntent().getStringExtra("favcolor");
        favmovie = getIntent().getStringExtra("favmovie");


    }

    public void setdatafromintent() {
        tvDisplayFirstLastName.setText(firstname + " " + lastname);

        tvDisplayBirthdate.setText(birthdate);

        tvDisplayPhoneno.setText(phoneno);
        tvDisplayWhatsappno.setText(whatsappno);
        tvDisplayEmailid.setText(emailid);
        tvDisplayAddress.setText(address);
        tvDisplayHobby.setText(hobby);
        tvDisplayFavMusician.setText(favmusician);
        tvDisplayFavColor.setText(favcolor);
        tvDisplayFavMovie.setText(favmovie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_friend);

        toolbar = findViewById(R.id.tbdisplayfriend);
        setSupportActionBar(toolbar);

        currentposition = getIntent().getIntExtra("currentposition", 0);

        ivDisplayprofilepic = findViewById(R.id.ivDisplayprofilepic);

        if ((recviewadapter.friends.get(currentposition)).getBitmap_profilepic() != null) { //getIntent().getData()!=null
            // Uri pic=Uri.parse(getIntent().getStringExtra("profilepic"));
            // Uri pic2=getIntent().getData();
           /* try{
                //ivDisplayprofilepic.setImageURI(Uri.parse(getPathFromURI(getApplicationContext(),pic)));
                //ivDisplayprofilepic.setImageURI(pic2);
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();

            }
            */
            //Toast.makeText(this,pic2.toString(),Toast.LENGTH_LONG).show();

            //GET THE PROFILE PIC FROM THE FRIEND CLASS INSTEAD OF INTENT
            pic3 = (recviewadapter.friends.get(currentposition)).getBitmap_profilepic();
            //ivDisplayprofilepic.setImageURI(pic2);
            ivDisplayprofilepic.setImageBitmap(pic3);
        } else {
            ivDisplayprofilepic.setImageResource((getIntent().getIntExtra("profilepic", 0)));
        }

        //FIND ALL TEXTVIEWS
        tvDisplayFirstLastName = findViewById(R.id.tvDisplayFirstLastName);
        tvDisplayBirthdate = findViewById(R.id.tvDisplayBirthdate);
        tvDisplayPhoneno = findViewById(R.id.tvDisplayPhoneno);
        tvDisplayWhatsappno = findViewById(R.id.tvDisplayWhatsappno);
        tvDisplayEmailid = findViewById(R.id.tvDisplayEmailid);
        tvDisplayAddress = findViewById(R.id.tvDisplayAddress);
        tvDisplayHobby = findViewById(R.id.tvDisplayHobby);
        tvDisplayFavMusician = findViewById(R.id.tvDisplayFavMusician);
        tvDisplayFavColor = findViewById(R.id.tvDisplayFavColor);
        tvDisplayFavMovie = findViewById(R.id.tvDisplayFavMovie);
//FIND ALL THE IMAGEVIEWS
        ivDisplayCallIcon = findViewById(R.id.ivDisplayCallIcon);
        ivDisplayInstaIcon = findViewById(R.id.ivDisplayInstaIcon);
        ivDisplayEmailIcon = findViewById(R.id.ivDisplayEmailIcon);
        ivDisplayWhatsappIcon = findViewById(R.id.ivDisplayWhatsappIcon);


//GET THE DATA
        getdatafromintent();


        //DISPLAY THE DATA
        setdatafromintent();


        ivDisplayCallIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent todialer = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneno));
                //if (todialer.resolveActivity((getPackageManager())) != null) {
                startActivity(todialer);
                //}
            }
        });


        ivDisplayEmailIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toemail = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + emailid));

                // if (toemail.resolveActivity((getPackageManager())) != null) {
                startActivity(toemail);
                //}
            }
        });


        ivDisplayInstaIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String url ="https://www.instagram.com/"+instaid;
                Intent toinstagram = new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(instaid))
                        .setPackage("com.instagram.android");

                //if (toinstagram.resolveActivity((getPackageManager())) != null) {
                startActivity(toinstagram);
                //}


            }
        });

        ivDisplayWhatsappIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.whatsapp.com/send?phone=" + whatsappno;
                Intent towhatsapp = new Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse(url))
                        .setPackage("com.whatsapp");

                if (towhatsapp.resolveActivity((getPackageManager())) != null) {
                    startActivity(towhatsapp);
                }


            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.displayfriendmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuEditFrienditem:
                Intent toeditfriend = new Intent(this, EditFriend.class);
                //SEND THE DATA
                if (int_profilepic == -1) {

                    // toeditfriend.putExtra("profilepic",pic2.toString());
                    //toeditfriend.setData(pic2);
                } else {
                    toeditfriend.putExtra("editintprofilepic", int_profilepic);
                }

                toeditfriend.putExtra("editfirstname", firstname);
                toeditfriend.putExtra("editlastname", lastname);
                toeditfriend.putExtra("editbirthdate", birthdate);
                toeditfriend.putExtra("editphoneno", phoneno);
                toeditfriend.putExtra("editwhatsappno", whatsappno);
                toeditfriend.putExtra("editemailid", emailid);
                toeditfriend.putExtra("editaddress", address);
                toeditfriend.putExtra("editinstaid", instaid);
                toeditfriend.putExtra("edithobby", hobby);
                toeditfriend.putExtra("editfavmusician", favmusician);
                toeditfriend.putExtra("editfavcolor", favcolor);
                toeditfriend.putExtra("editfavmovie", favmovie);

                toeditfriend.putExtra("editcurrentposition", currentposition);


                startActivityForResult(toeditfriend, TOEDITFRIEND_REQ_CODE);
                //intent
                break;
            case R.id.menuDeleteFrienditem:
                AlertDialog.Builder alert_delete = new AlertDialog.Builder(this);
                alert_delete.setTitle("Delete Friend");
                alert_delete.setIcon(R.drawable.ic_delete_black_24dp);
                alert_delete.setMessage("Are you sure you want to delete?");
                alert_delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        recviewadapter.friends.remove(currentposition);
                        MainActivity.adapter.notifyItemRemoved(currentposition);
                        finish();

                    }
                });
                alert_delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert_delete.show();


                break;
            default:
                return false;
        }
        //return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TOEDITFRIEND_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                finish();
                getdatafromintent();
                setdatafromintent();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}

