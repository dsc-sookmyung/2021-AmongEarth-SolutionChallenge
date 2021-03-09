package com.example.amongearth.mypage;



public class Badge {
    private String badgeImage;
    private String badgeName;
    private String getDate;

    public Badge(){}

    public Badge(String badgeImage, String badgeName, String getDate) {
        this.badgeImage = badgeImage;
        this.badgeName = badgeName;
        this.getDate = getDate;
    }

    public String getBadgeImage() {
        return badgeImage;
    }

    public void setBadgeImage(String badgeImage) {
        this.badgeImage = badgeImage;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }
}














