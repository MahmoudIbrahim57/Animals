package com.example.catswebsite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mcontext;
    private ArrayList<Exampletem> mexampeList;
    private onItemClickListener onItemClickListener;

    public interface onItemClickListener{
        void onItemClick(int position );

    }

    public void setOnItemClickListener(onItemClickListener clickListener){
        onItemClickListener=clickListener;
    }
    public ExampleAdapter(Context context,ArrayList<Exampletem> exampeList){
        mcontext=context;
        mexampeList=exampeList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.example_item,parent,false);

        return new ExampleViewHolder(v);
 }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        Exampletem curentItem=mexampeList.get(position);

        String imageURL = curentItem.getmImageURL();
        String creatorName = curentItem.getmCreator();
        int likes=curentItem.getmLikes();

        holder.mtextCreator.setText(creatorName);
        holder.mtextLikes.setText("Likes: "+likes);
        Picasso.with(mcontext).load(imageURL).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mexampeList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;
    public TextView mtextCreator;
    public TextView mtextLikes;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image_view);
            mtextCreator=itemView.findViewById(R.id.text_creator);
            mtextLikes=itemView.findViewById(R.id.text_like);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(onItemClickListener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(position);
                        }

                    }

                }
            });
        }
    }

}
