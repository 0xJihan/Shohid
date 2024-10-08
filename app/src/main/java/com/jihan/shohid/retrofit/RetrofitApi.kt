package com.jihan.shohid.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApi {

    private val base_url = "http://192.168.0.102/"
    ///private val base_url = "http://127.0.0.1:8080"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}