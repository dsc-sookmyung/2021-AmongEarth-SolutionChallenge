package com.example.amongearth.mypage;

public class PostData {
    private int post_image;
    private String title;
    private String contents;

    public PostData(int post_image, String title, String contents){
        this.contents=contents;
        this.post_image=post_image;
        this.title=title;
    }
    public int getPost_image()
    {
        return this.post_image;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getContents()
    {
        return this.contents;
    }
}
