package com.practice.socialinfluencerr.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Twitter
{
	@SerializedName("Following")
    @Expose
    private String Following;
	@SerializedName("Media_twitter")
    @Expose
    private List<Media_twitter> Media_twitter;
	@SerializedName("Post")
    @Expose
    private int Post;
	@SerializedName("Engagement")
    @Expose
    private String Engagement;
	@SerializedName("Followers")
    @Expose
    private String Followers;

    public void setFollowing(String Following){
        this.Following = Following;
    }
    public String getFollowing(){
        return this.Following;
    }
    public void setMedia_twitter(List<Media_twitter> Media_twitter){
        this.Media_twitter = Media_twitter;
    }
    public List<Media_twitter> getMedia_twitter(){
        return this.Media_twitter;
    }
    public void setPost(int Post){
        this.Post = Post;
    }
    public int getPost(){
        return this.Post;
    }
    public void setEngagement(String Engagement){
        this.Engagement = Engagement;
    }
    public String getEngagement(){
        return this.Engagement;
    }
    public void setFollowers(String Followers){
        this.Followers = Followers;
    }
    public String getFollowers(){
        return this.Followers;
    }

}