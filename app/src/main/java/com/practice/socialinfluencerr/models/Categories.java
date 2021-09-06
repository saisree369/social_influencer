package com.practice.socialinfluencerr.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categories
{
	@SerializedName("category_3")
    @Expose
    private String category_3;
	@SerializedName("category_2")
    @Expose
    private String category_2;
	@SerializedName("category_1")
    @Expose
    private String category_1;

    public void setCategory_3(String category_3){
        this.category_3 = category_3;
    }
    public String getCategory_3(){
        return this.category_3;
    }
    public void setCategory_2(String category_2){
        this.category_2 = category_2;
    }
    public String getCategory_2(){
        return this.category_2;
    }
    public void setCategory_1(String category_1){
        this.category_1 = category_1;
    }
    public String getCategory_1(){
        return this.category_1;
    }

}