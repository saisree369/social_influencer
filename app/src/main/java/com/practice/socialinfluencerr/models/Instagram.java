package com.practice.socialinfluencerr.models;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Instagram
{
	@SerializedName("Following")
    @Expose
    private String Following;
	@SerializedName("Media_instagram")
    @Expose
    private List<Media_instagram> Media_instagram;
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
    public void setMedia_instagram(List<Media_instagram> Media_instagram){
        this.Media_instagram = Media_instagram;
    }
    public List<Media_instagram> getMedia_instagram(){
        return this.Media_instagram;
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
