package com.practice.socialinfluencerr.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example1
{
	@SerializedName("status_code")
    @Expose
    private int status_code;
	
	
	@SerializedName("status_message")
    @Expose
    private String status_message;
	
	@SerializedName("data")
    @Expose
    private Data data;

    public void setStatus_code(int status_code){
        this.status_code = status_code;
    }
    public int getStatus_code(){
        return this.status_code;
    }
    public void setStatus_message(String status_message){
        this.status_message = status_message;
    }
    public String getStatus_message(){
        return this.status_message;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }

}