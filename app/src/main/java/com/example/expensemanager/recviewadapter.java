package com.example.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class recviewadapter extends RecyclerView.Adapter<recviewadapter.ViewHolder> {
    static final int TODISPLAYFRIEND_ACTIVITY_REQ_CODE = 300;
    public static ArrayList<Friend> friends = new ArrayList<Friend>();
    private final Context context;


    public recviewadapter(Context context) {
        this.context = context;
        //this.activity=activity;
    }

    public void setFriends(ArrayList<Friend> friends) {
        recviewadapter.friends = friends;
        //
    }

    public void addfriends(ArrayList<Friend> newfriends) {
        friends.addAll(newfriends);
        //this.notifyItemInserted(friends.size()-1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitemslayout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Friend currentfriend = friends.get(position);
        holder.tvFrienddesc.setText(currentfriend.getFirstname() + " " + currentfriend.getLastname());
        // if(currentfriend.getProfilepic()!=null) {
        if (currentfriend.getBitmap_profilepic() != null) {
            holder.ivProfilepicrecyclerview.setImageBitmap(currentfriend.getBitmap_profilepic());
        } else {
            holder.ivProfilepicrecyclerview.setImageResource(currentfriend.getProfilepic2());
        }

        //SETTING ONCLICK LISTENERS
        holder.llrow.setOnClickListener(new View.OnClickListener() {
            Friend currentfriend = friends.get(position);

            @Override
            public void onClick(View view) {
                Intent todisplayfriend = new Intent(context, DisplayFriend.class);
                if (currentfriend.getProfilepic() != null) {
                    todisplayfriend.putExtra("profilepic", currentfriend.getProfilepic().toString());
                    // todisplayfriend.setData(currentfriend.getProfilepic());
                } else {
                    todisplayfriend.putExtra("profilepic", currentfriend.getProfilepic2());
                }

                todisplayfriend.putExtra("firstname", currentfriend.getFirstname());
                todisplayfriend.putExtra("lastname", currentfriend.getLastname());
                todisplayfriend.putExtra("birthdate", currentfriend.getBirthdate());
                todisplayfriend.putExtra("phoneno", currentfriend.getPhoneno());
                todisplayfriend.putExtra("whatsappno", currentfriend.getWhatsappno());
                todisplayfriend.putExtra("emailid", currentfriend.getEmail());
                todisplayfriend.putExtra("address", currentfriend.getAddress());
                todisplayfriend.putExtra("instaid", currentfriend.getInstaid());
                todisplayfriend.putExtra("hobby", currentfriend.getHobbies());
                todisplayfriend.putExtra("favmusician", currentfriend.getFavmusician());
                todisplayfriend.putExtra("favcolor", currentfriend.getFavcolor());
                todisplayfriend.putExtra("favmovie", currentfriend.getFavmovie());

                todisplayfriend.putExtra("currentposition", position);


                context.startActivity(todisplayfriend);
            }
        });
        if (position != recviewadapter.friends.size()) { //load animation only once
            setAnimation(holder.llrow, position);
        }
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public void setAnimation(View viewtoanimate, int position) {
        Animation slidein = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewtoanimate.startAnimation(slidein);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFrienddesc;
        public CircleImageView ivProfilepicrecyclerview;
        public ImageView ivCallicon;
        LinearLayout llrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llrow = itemView.findViewById(R.id.llrow);
            ivProfilepicrecyclerview = itemView.findViewById(R.id.ivProfilepicrecyclerview);
            ivCallicon = itemView.findViewById(R.id.ivCallicon);
            tvFrienddesc = itemView.findViewById(R.id.tvFrienddesc);

        }
    }
}
