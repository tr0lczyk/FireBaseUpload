package com.olczyk.android.firebaseuploadproject;

public class Upload {

    private String name;
    private String imageUrl;

    public Upload(){

    }

    public Upload(String name, String imageUrl) {
        if(name.trim().equals("")){
            name = "No name";
        }

        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
