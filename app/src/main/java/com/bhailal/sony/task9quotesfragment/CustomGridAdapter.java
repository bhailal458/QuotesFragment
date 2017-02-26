package com.bhailal.sony.task9quotesfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sony on 20-02-2017.
 */

public class CustomGridAdapter extends BaseAdapter {

    private Context context;

   private ArrayList<Post> posts;
    private LayoutInflater inflater;
   private int[] img;
   private FragmentTwo fragment2;




    public CustomGridAdapter(Context gridContext, ArrayList<Post> postArrayList, int[] img) {

        this.context = gridContext;
        this.posts = postArrayList;
        this.img =img;
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.grid_row,parent, false);
        }


        TextView t1 = (TextView)convertView.findViewById(R.id.gridText);
        ImageView im = (ImageView)convertView.findViewById(R.id.imageview);

        Post m = posts.get(position);


        t1.setText(m.getName());
        im.setImageResource(img[position]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment2 = new FragmentTwo();

                Bundle bundle = new Bundle();
                bundle.putInt("qoutePos",posts.get(position).getId());
                fragment2.setArguments(bundle);

                Activity activityGrid = (Activity) context;

                FragmentManager fragmentManager = activityGrid.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Relative_Layout,fragment2);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();


            }
        });
       return convertView;
    }
}
