package com.example.amongearth_hackaton.mypage;

public class BadgeData {
    private int badge_image;
    private String badge_name;
    private String get_date;

    public BadgeData(int badge_image, String badge_name , String get_date){
        this.get_date=get_date;
        this.badge_image=badge_image;
        this.badge_name=badge_name;
    }
    public int getBadge_image()
    {
        return this.badge_image;
    }

    public String getBadge_name()
    {
        return this.badge_name;
    }

    public String getDate()
    {
        return this.get_date;
    }

}
