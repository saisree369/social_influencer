package com.practice.socialinfluencerr.models;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Influencer_menu
{
	@SerializedName("category_label")
    @Expose
    private String category_label;
	 @SerializedName("category_icon_url")
    @Expose
    private String category_icon_url;
	 @SerializedName("influencers")
    @Expose
    private List<Influencers> influencers;

    public void setCategory_label(String category_label){
        this.category_label = category_label;
    }
    public String getCategory_label(){
        return this.category_label;
    }
    public void setCategory_icon_url(String category_icon_url){
        this.category_icon_url = category_icon_url;
    }
    public String getCategory_icon_url(){
        return this.category_icon_url;
    }
    public void setInfluencers(List<Influencers> influencers){
        this.influencers = influencers;
    }
    public List<Influencers> getInfluencers(){
        return this.influencers;
    }

}