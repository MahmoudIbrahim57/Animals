package com.example.catswebsite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.onItemClickListener {

        private RecyclerView recyclerView;
        private ExampleAdapter exampleAdapter;
        private RequestQueue requestQueue;
        private ArrayList<Exampletem> exampletems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exampletems=new ArrayList<>();
        requestQueue= Volley.newRequestQueue(this);
        parseJson();

    }

    private void parseJson() {
    String URL= "https://pixabay.com/api/?key=11778875-bd18dbcb72c8cc6ad02f06b46&q=animal+love&image_type=photo&pretty=true";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("hits");
                    for(int i=0 ; i<jsonArray.length(); i++){
                        JSONObject hit =jsonArray.getJSONObject(i);
                        String creator =hit.getString("user");
                        String url =hit.getString("webformatURL");
                        int likes =hit.getInt("likes");
                        exampletems.add(new Exampletem(url,creator,likes));
                    }

                    exampleAdapter =new ExampleAdapter(MainActivity.this,exampletems);
                    exampleAdapter.setOnItemClickListener(MainActivity.this);
                    recyclerView.setAdapter(exampleAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            }
        });
requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        Exampletem exampletem=exampletems.get(position);

        intent.putExtra("URL",exampletem.getmImageURL());
        intent.putExtra("creator",exampletem.getmCreator());
        intent.putExtra("likes",exampletem.getmLikes());
            startActivity(intent);
    }
}
