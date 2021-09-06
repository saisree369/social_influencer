package com.practice.socialinfluencerr.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Influencers_sub
{
	@SerializedName("gender")
    @Expose
    private String gender;
	@SerializedName("age")
    @Expose
    private String age;
	@SerializedName("followers")
    @Expose
    private int followers;
	@SerializedName("start_date")
    @Expose
    private String start_date;
	@SerializedName("end_date")
    @Expose
    private String end_date;

    public void setGender(String gender){
        this.gender = gender;
    }
    public String getGender(){
        return this.gender;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getAge(){
        return this.age;
    }
    public void setFollowers(int followers){
        this.followers = followers;
    }
    public int getFollowers(){
        return this.followers;
    }
    public void setStart_date(String start_date){
        this.start_date = start_date;
    }
    public String getStart_date(){
        return this.start_date;
    }
    public void setEnd_date(String end_date){
        this.end_date = end_date;
    }
    public String getEnd_date(){
        return this.end_date;
    }

}

