package com.practice.socialinfluencerr.models;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data
{
	@SerializedName("influencer_menu")
    @Expose
    private ArrayList<Influencer_menu> influencer_menu;

    public void setInfluencer_menu(ArrayList<Influencer_menu> influencer_menu){
        this.influencer_menu = influencer_menu;
    }
    public ArrayList<Influencer_menu> getInfluencer_menu(){
        return this.influencer_menu;
    }

}