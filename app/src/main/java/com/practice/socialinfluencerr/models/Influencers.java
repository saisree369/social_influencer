package com.practice.socialinfluencerr.models;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Influencers
{
	@SerializedName("city")
    @Expose
    private String city;
	@SerializedName("name")
    @Expose
    private String name;
	@SerializedName("title")
    @Expose
    private String title;
	@SerializedName("posts")
    @Expose
    private int posts;
	@SerializedName("age")
    @Expose
    private String age;
	@SerializedName("description")
    @Expose
    private String description;
	@SerializedName("youtube")
    @Expose
    private List<Youtube> youtube;
	@SerializedName("budget")
    @Expose
    private String budget;
	@SerializedName("connects")
    @Expose
    private int connects;
	@SerializedName("brand_name")
    @Expose
    private String brand_name;
	@SerializedName("order")
    @Expose
    private int order;
	@SerializedName("followers")
    @Expose
    private int followers;
	@SerializedName("profile_url")
    @Expose
    private String profile_url;
	@SerializedName("social_media_platforms")
    @Expose
    private String social_media_platforms;
	@SerializedName("influencers_sub")
    @Expose
    private List<Influencers_sub> influencers_sub;
	@SerializedName("brand_url")
    @Expose
    private String brand_url;
	@SerializedName("id")
    @Expose
    private int id;
	@SerializedName("influencer_age")
    @Expose
    private String influencer_age;
	@SerializedName("twitter")
    @Expose
    private List<Twitter> twitter;
	@SerializedName("categories")
    @Expose
    private List<Categories> categories;
	@SerializedName("instagram")
    @Expose
    private List<Instagram> instagram;

    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setPosts(int posts){
        this.posts = posts;
    }
    public int getPosts(){
        return this.posts;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getAge(){
        return this.age;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setYoutube(List<Youtube> youtube){
        this.youtube = youtube;
    }
    public List<Youtube> getYoutube(){
        return this.youtube;
    }
    public void setBudget(String budget){
        this.budget = budget;
    }
    public String getBudget(){
        return this.budget;
    }
    public void setConnects(int connects){
        this.connects = connects;
    }
    public int getConnects(){
        return this.connects;
    }
    public void setBrand_name(String brand_name){
        this.brand_name = brand_name;
    }
    public String getBrand_name(){
        return this.brand_name;
    }
    public void setOrder(int order){
        this.order = order;
    }
    public int getOrder(){
        return this.order;
    }
    public void setFollowers(int followers){
        this.followers = followers;
    }
    public int getFollowers(){
        return this.followers;
    }
    public void setProfile_url(String profile_url){
        this.profile_url = profile_url;
    }
    public String getProfile_url(){
        return this.profile_url;
    }
    public void setSocial_media_platforms(String social_media_platforms){
        this.social_media_platforms = social_media_platforms;
    }
    public String getSocial_media_platforms(){
        return this.social_media_platforms;
    }
    public void setInfluencers(List<Influencers_sub> influencers){
        this.influencers_sub = influencers_sub;
    }
    public List<Influencers_sub> getInfluencers(){
        return this.influencers_sub;
    }
    public void setBrand_url(String brand_url){
        this.brand_url = brand_url;
    }
    public String getBrand_url(){
        return this.brand_url;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setInfluencer_age(String influencer_age){
        this.influencer_age = influencer_age;
    }
    public String getInfluencer_age(){
        return this.influencer_age;
    }
    public void setTwitter(List<Twitter> twitter){
        this.twitter = twitter;
    }
    public List<Twitter> getTwitter(){
        return this.twitter;
    }
    public void setCategories(List<Categories> categories){
        this.categories = categories;
    }
    public List<Categories> getCategories(){
        return this.categories;
    }
    public void setInstagram(List<Instagram> instagram){
        this.instagram = instagram;
    }
    public List<Instagram> getInstagram(){
        return this.instagram;
    }


}