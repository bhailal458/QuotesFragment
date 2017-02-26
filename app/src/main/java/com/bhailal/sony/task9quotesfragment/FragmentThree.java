package com.bhailal.sony.task9quotesfragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Sony on 25-02-2017.
 */

public class FragmentThree extends Fragment {
    String myqoute;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three,container,false);
        setHasOptionsMenu(true);
        myqoute = getArguments().getString("Quote");
        TextView txtShare = (TextView)view.findViewById(R.id.shareText);
        txtShare.setText(myqoute);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent();
        String quote = intent.getStringExtra("myPosition");


        switch (item.getItemId()){
            case R.id.share:

                Intent i =new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_TEXT,quote);
//                startActivity(Intent.createChooser(i,"SELECT"));


                myqoute = getArguments().getString("Quote");
                intent.getStringExtra("Quote");
                intent.putExtra(Intent.EXTRA_TEXT, myqoute);
                startActivity(Intent.createChooser(intent, "Share Using"));

                return true;



        }



        return super.onOptionsItemSelected(item);

    }
}
