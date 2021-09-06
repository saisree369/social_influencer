package com.practice.socialinfluencerr

import java.util.HashMap

/**
 * user class to keep track of registered user
 * and their data (profile info)
 */

class Campaign {
    var email: String? = null
        private set
    var brand: String? = null
    var price: String? = null
    var reward: String? = null
    var product_desc: String? = null
    var platforms:String? = null
    var category_selected: String? = null
    var url: String? = null
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
      brand:String, price: String,
        reward: String,product_desc : String,platforms : String,category_selected : String,url: String
    ) {

        this.brand = brand
        this.price = price
        this.reward = reward
        this.product_desc = product_desc
        this.platforms = platforms
        this.category_selected = category_selected
        this.url = url


    }


}