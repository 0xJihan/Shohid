package com.jihan.shohid.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServices {

    @GET("apps/Shohid_Information.json")
    suspend fun getAllShoid(): Response<ShohidList>

}