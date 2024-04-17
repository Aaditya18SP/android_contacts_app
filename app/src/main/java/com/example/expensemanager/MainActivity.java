package com.example.expensemanager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int TOADDFRIEND_ACTIVITY_REQ_CODE = 100;

    static recviewadapter adapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.tbMainact);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("@string/app_name");
        // toolbar.setBackgroundColor(getResources().getColor(R.color.yellow));
        // toolbar.setTitleTextColor(getResources().getColor(R.color.black));
//toolbar.setTitleTextAppearance(this,R.style.ToolbarTitleFont);
//toolbar.setElevation(5.0f);


//RECYCLER VIEW
        recyclerView = findViewById(R.id.rvFriendlist);

        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend(R.drawable.avatar_1, "Aaditya", "Pal", "18/08/2003", "+91 8007941369", "+91 8007941369", "adityapal1808@gmail.com", "Ambernath", "https://instagram.com/carswithluke?igshid=YmMyMTA2M2Y=", "cricket and cars", "One direction", "Blue", "None"));
        // friends.add(new Friend(R.drawable.avatar_6,"Sanika","Chalke","18/06/2003","+91 7045572230","+91 7045572230","sanikachalke03@gmail.com","Louiswadi,Thane","https://instagram.com/carswithluke?igshid=YmMyMTA2M2Y=","Dancing","Depends on the mood","black","suspense movies"));
        /*friends.add(new Friend( R.drawable.avatar_6,"Anushka","Dalvi","16/11/2003","+91 7841888785"));
        friends.add(new Friend( R.drawable.avatar_5,"Steven","Thomas","24/08/2003","+91 9324175517"));
        friends.add(new Friend( R.drawable.avatar_6,"Sakshi","Sataye","05/12/2003","+91 9372628184"));

         */
        //friends.add(new Friend( R.drawable.avatar_3,"Steven","Thomas","24/08/2003","9324175517"));


        adapter = new recviewadapter(this);
        adapter.setFriends(friends);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // FLOATING ACTION BUTTON
        fab = findViewById(R.id.fabAddfriend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to add friend activity
                Intent toaddfriendactivity = new Intent(MainActivity.this, AddFriend.class);
                startActivityForResult(toaddfriendactivity, TOADDFRIEND_ACTIVITY_REQ_CODE);
            }
        });


        //GET DATA TO DELETE A CONTACT FROM DISPLAY FRIEND
       /* from_which_activity=getIntent().getStringExtra("from");
        if(from_which_activity=="displayfriendactivity"){
            position_item_to_be_deleted=getIntent().getIntExtra("currentposition2",100);
            recviewadapter.friends.remove((position_item_to_be_deleted));
            adapter.notifyItemRemoved(position_item_to_be_deleted);

        }

        */


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == TOADDFRIEND_ACTIVITY_REQ_CODE) {
            if (resultCode == RESULT_OK) {
                adapter.notifyItemInserted(recviewadapter.friends.size() - 1);
            } else {
                Toast.makeText(this, "NO DATA RECEVIED", Toast.LENGTH_SHORT).show();
                super.onActivityResult(requestCode, resultCode, data);
            }

        }
       /* if(resultCode==150){
            position_item_to_be_deleted=getIntent().getIntExtra("currentposition2",0);
            //recviewadapter.friends.remove((position_item_to_be_deleted));
            adapter.notifyItemRemoved(position_item_to_be_deleted);
        }

        */
    }


}

/*NOTE:
1.LEARNINGS
i.Make the height of recycler view as 0dp in constraint layout when using it with toolbar
ii.always use getMenuInflater().inflate() to add icons to yout toolbar
iii. in edittext, if you want the text to go to the next line automatically then in XML,
        use textMultine value to inputtype
iv.in edittext,if you want the input to always begin with capital letters then in XML,
        use textCapSentences value to inputype attribute
        if multiple inputtypes are there separate it with a pipe '|'
 */



