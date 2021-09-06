package com.practice.socialinfluencerr.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Youtube
{
	@SerializedName("Following")
    @Expose
    private String Following;
	@SerializedName("Media_youtube")
    @Expose
    private List<Media_youtube> Media_youtube;
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
    public void setMedia_youtube(List<Media_youtube> Media_youtube){
        this.Media_youtube = Media_youtube;
    }
    public List<Media_youtube> getMedia_youtube(){
        return this.Media_youtube;
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
