package com.example.catswebsite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
private ImageView imageView;
private TextView creator,likes;
String imageURL,imagecreator;
int imagelikes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView=findViewById(R.id.image_details);
        creator=findViewById(R.id.text_creator_detalis);
        likes=findViewById(R.id.text_likes_detalis);
        imageURL=getIntent().getStringExtra("URL");
        imagecreator=getIntent().getStringExtra("creator");
        imagelikes=getIntent().getIntExtra("likes",0);

        creator.setText(imagecreator);
        likes.setText("Likes: "+imagelikes);
        Picasso.with(this).load(imageURL).fit().centerInside().into(imageView);

    }
}
