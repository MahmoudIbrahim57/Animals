package com.example.catswebsite;

public class Exampletem {
    private String mImageURL;
    private String mCreator;
    private int mLikes;

    public Exampletem(String mImageURL, String mCreator, int mLikes){
        this.mImageURL=mImageURL;
        this.mCreator=mCreator;
        this.mLikes=mLikes;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }
}
