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
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sony on 08-02-2017.
 */

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<PostQuotesList>p;
    Context context;


    public CustomListAdapter(Activity activity,List<PostQuotesList>p){
        this.activity = activity;
        this.p =p ;

    }
    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int position) {
        return p.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView =inflater.inflate(R.layout.list_row,null);

        TextView t1 = (TextView)convertView.findViewById(R.id.txtlist);

        PostQuotesList m = p.get(position);
        t1.setText(m.getQuotes());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activityList = (Activity)context;

                FragmentThree fragmrnt3= new FragmentThree();

                Bundle bundleList = new Bundle();
                bundleList.putString("Quote",p.get(position).getQuotes());
                fragmrnt3.setArguments(bundleList);

                FragmentManager fragmentManager = activityList.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Relative_Layout,fragmrnt3);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();

            }
        });
        return convertView;
    }
}
