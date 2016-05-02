package com.app.fyweddingplanner.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by USER on 3/16/2016.
 */
public class Offers implements Serializable

{
    public int pic;
    public String name;
    public String disciption;
    public String offerName;
    public String filed;
    public String id;
    public ArrayList<String> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisciption() {
        return disciption;
    }

    public void setDisciption(String disciption) {
        this.disciption = disciption;

    }
}

