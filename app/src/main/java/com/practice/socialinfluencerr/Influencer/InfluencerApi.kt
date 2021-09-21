package com.practice.socialinfluencerr

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface InfluencerApi {

    @GET("clpMQJgdCG?indent=2")
    fun getProfiles(): Call<List<Inf_Profile>>

    companion object {
        operator fun invoke(): InfluencerApi {
            return Retrofit.Builder()
                .baseUrl("https://www.json-generator.com/api/json/get/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(InfluencerApi::class.java)
        }
    }
}