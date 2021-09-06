package com.practice.socialinfluencerr.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Media_youtube
{
	@SerializedName("url_6")
    @Expose
    private String url_6;
	@SerializedName("url_5")
    @Expose
    private String url_5;
	@SerializedName("url_4")
    @Expose
    private String url_4;
	@SerializedName("url_3")
    @Expose
    private String url_3;
	@SerializedName("url_2")
    @Expose
    private String url_2;
	@SerializedName("url_1")
    @Expose
    private String url_1;

    public void setUrl_6(String url_6){
        this.url_6 = url_6;
    }
    public String getUrl_6(){
        return this.url_6;
    }
    public void setUrl_5(String url_5){
        this.url_5 = url_5;
    }
    public String getUrl_5(){
        return this.url_5;
    }
    public void setUrl_4(String url_4){
        this.url_4 = url_4;
    }
    public String getUrl_4(){
        return this.url_4;
    }
    public void setUrl_3(String url_3){
        this.url_3 = url_3;
    }
    public String getUrl_3(){
        return this.url_3;
    }
    public void setUrl_2(String url_2){
        this.url_2 = url_2;
    }
    public String getUrl_2(){
        return this.url_2;
    }
    public void setUrl_1(String url_1){
        this.url_1 = url_1;
    }
    public String getUrl_1(){
        return this.url_1;
    }

}
