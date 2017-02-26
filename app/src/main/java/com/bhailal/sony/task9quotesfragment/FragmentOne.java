package com.bhailal.sony.task9quotesfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sony on 25-02-2017.
 */

public class FragmentOne extends Fragment {

    GridView gridview;
    FragmentTwo fragment2 = new FragmentTwo();

    int img[] = {
            R.drawable.life,
            R.drawable.valentinespecial,
            R.drawable.love,
            R.drawable.friendship,
            R.drawable.positive,
            R.drawable.funny,
            R.drawable.motivation
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        gridview = (GridView) view.findViewById(R.id.gridview);

        new GridViewClass().execute("http://rapidans.esy.es/test/getallcat.php");

        return view;

    }

    class GridViewClass extends AsyncTask<String,Void,String>{

        private Exception exception;
        private  ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();

        }


        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection;
            try {


                URL url = new URL(params[0]);//new URL("https://jsonplaceholder.typicode.com/posts");
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;

                } catch (Exception e) {
                    this.exception = e;
                }

            } catch (Exception e) {
                this.exception = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            ArrayList<Post> postArrayList = new ArrayList<>();
//            ArrayList<categories> cats = new ArrayList<>();
            try {
                JSONObject obj1 = new JSONObject(s);
                JSONArray jsonarray = obj1.getJSONArray("data");
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject obj2 = jsonarray.getJSONObject(i);

                    int id = obj2.getInt("id");
                    String name = obj2.getString("name");

                    Post post = new Post();
                    post.setId(id);
                    post.setName(name);
                    postArrayList.add(post);
                }


            } catch (Exception e) {
                this.exception = e;
                Toast.makeText(getActivity(), "No Internet Connection ", Toast.LENGTH_SHORT).show();
            }

            CustomGridAdapter adapter1 = new CustomGridAdapter(getActivity(),postArrayList,img);
            gridview.setAdapter(adapter1);


        }
    }
}


