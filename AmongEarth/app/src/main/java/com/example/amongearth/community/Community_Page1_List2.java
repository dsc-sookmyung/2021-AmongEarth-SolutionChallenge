package com.example.amongearth.community;

import android.graphics.drawable.Drawable;

public class Community_Page1_List2 {
    private Drawable image2_icon;
    private String image2_photo;
    private String id2;
    private String date2;
    private String content2;
    private String heart_number2;
    private String userid;
    private String heartflag;

    public Community_Page1_List2(Drawable image2_icon, String image2_photo, String id2, String date2, String content2, String heart_number2, String userid, String heartflag){
        this.image2_icon = image2_icon;
        this.image2_photo = image2_photo;
        this.id2 = id2;
        this.date2 = date2;
        this.content2 = content2;
        this.heart_number2 = heart_number2;
        this.userid = userid;
        this.heartflag = heartflag;

    }

    public Community_Page1_List2() {

    }

    public void setImage2_icon(Drawable image2_icon){ this.image2_icon = image2_icon; }
    public void setImage2_photo(String image2_photo){ this.image2_photo = image2_photo; }
    public void setId2(String id2){ this.id2 = id2; }
    public void setDate2(String date2){ this.date2 = date2; }
    public void setContent2(String content2){ this.content2 = content2; }
    public void setHeart_number2(String heart_number2){ this.heart_number2 = heart_number2; }
    public void setUserid(String userid){ this.userid = userid; }
    public void setHeartflag(String heartflag) { this.heartflag = heartflag; }

    public Drawable getImage2_icon(){ return image2_icon; }
    public String getImage2_photo(){ return image2_photo; }
    public String getId2(){ return id2; }
    public String getDate2(){ return date2; }
    public String getContent2(){ return content2; }
    public String getHeart_number2(){ return heart_number2; }
    public String getUserid() { return userid; }
    public String getHeartflag() { return heartflag; }


}
