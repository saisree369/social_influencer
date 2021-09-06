package com.practice.socialinfluencerr

import java.util.HashMap

/**
 * user class to keep track of registered user
 * and their data (profile info)
 */

class User {

    var email: String? = null
        private set
    var fullName: String? = null
    var gender: String? = null
    var profession: String? = null
    var workplace: String? = null
    var phone: String? = null
    var url: String? = null
    private var password: String? = null
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

    constructor(fullName: String, email: String) {
        this.fullName = fullName
        this.email = email
    }

    constructor(
        fullName: String, email: String,gender:String, profession: String,
        workplace: String, phone: String,url: String
    ) {
        this.email = email
        this.fullName = fullName
        this.gender = gender
        this.profession = profession
        this.workplace = workplace
        this.phone = phone
        this.url = url
    }

    fun setpassword(password: String) {
        this.password = password
    }

    fun getpassword(): String? {
        return password
    }
}