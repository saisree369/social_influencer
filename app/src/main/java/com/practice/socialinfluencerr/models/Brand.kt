package com.practice.socialinfluencerr

data class Brand(val name: Int = 0, val count: String = "")

data class Content(var profile_pic :String, var name:String,var age:Int,var posts: Int,var followers:Int,var Connects: Int,var id:Int)

data class Example(var results:  Results)

data class Results(var content: ArrayList<Content>)


data class Category(val name: Int = 0, val count: String = "")

data class Connect(val name: Int = 0,val count: String = "")

data class Amount(var profile_pic :String, var name:String,var amount:Int)