package com.practice.socialinfluencerr

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("email")
    @Expose
    var email: String? = null
        private set
    @SerializedName("category_selected")
    @Expose
    var category_selected: String? = null
    @SerializedName("brand")
    @Expose
    var brand: String? = null
    @SerializedName("product_desc")
    @Expose
    var product_desc: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("likes")
    @Expose
    var likes:String? = null
    var views:String? = null

    /*    public HashMap<String, Object> getAsMap(){
        HashMap<String, Object> userAsMap = new HashMap<>();
        userAsMap.put("username",username);
        userAsMap.put("password",password);
        userAsMap.put("age",age);
        userAsMap.put("name",name);
        //Add or remove more key value pair
        return userAsMap;
    }*/

constructor() {}

constructor(
        category_selected : String,brand:String,product_desc : String,url: String,likes:String,views:String
    ) {
        this.category_selected = category_selected
        this.brand = brand
        this.product_desc = product_desc
        this.url = url
        this.likes = likes
        this.views = views
}
/*
    fun setcategory_selected(category_selected: String) {
        this.category_selected = category_selected
    }

    fun getcategory_selected(): String? {
        return category_selected
    }

    fun setbrand(brand: String) {
        this.brand = brand
    }

    fun getbrand(): String? {
        return brand
    }

    fun setproduct_desc(product_desc: String) {
        this.product_desc = product_desc
    }

    fun getproduct_desc(): String? {
        return product_desc
    }

    fun setlikes(likes: String) {
        this.likes = likes
    }

    fun getlikes(): String? {
        return likes
    }

    fun seturl(url: String) {
        this.url = url
    }

    fun geturl(): String? {
        return url
    }
*/
}