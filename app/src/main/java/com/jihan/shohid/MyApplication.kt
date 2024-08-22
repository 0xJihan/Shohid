package com.jihan.shohid

import android.app.Application
import com.jihan.shohid.model.ShohidRepository
import com.jihan.shohid.retrofit.RetrofitApi
import com.jihan.shohid.retrofit.RetrofitServices
import com.jihan.shohid.room.UserDatabase

class MyApplication : Application() {
    lateinit var repository: ShohidRepository
    override fun onCreate() {
        super.onCreate()

        initialize()
    }

    private fun initialize() {

        val database = UserDatabase.getDatabase(this)

        //retrofit
        val retrofitService = RetrofitApi.getInstance().create(RetrofitServices::class.java)
        repository = ShohidRepository(retrofitService, database, applicationContext)

    }
}
