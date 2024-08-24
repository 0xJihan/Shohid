package com.jihan.shohid.model

import NetworkUtils
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jihan.shohid.retrofit.RetrofitServices
import com.jihan.shohid.room.Shohid
import com.jihan.shohid.room.UserDatabase

class ShohidRepository(
    private val retrofitServices: RetrofitServices,
    private val database: UserDatabase,
    private val context: Context
) {

    private val _shohidList = MutableLiveData<List<Shohid>>()
    val shohidList: LiveData<List<Shohid>>
        get() = _shohidList


    suspend fun getShohidList() {

        if (NetworkUtils().isInternetConnected(context)){


        val result = retrofitServices.getAllShoid()

        if (result.body() != null) {
            _shohidList.postValue(result.body()!!.shohidList)

            database.getDao().insertSohid(result.body()!!.shohidList)
        }
            Log.d("jihan khan","Internet Conenected. \n${result.body()!!.shohidList}")

        }



        else {
            val local = database.getDao().getAll()
            _shohidList.postValue(local)
            Log.d("jihan khan","No Internet. Room")
        }

    }
}
