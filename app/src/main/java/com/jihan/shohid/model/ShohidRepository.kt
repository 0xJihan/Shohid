package com.jihan.shohid.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jihan.shohid.retrofit.RetrofitServices
import com.jihan.shohid.retrofit.Shohid

class ShohidRepository(private val retrofitServices: RetrofitServices) {

    private val _shohidList = MutableLiveData<List<Shohid>>()
    val shohidList: LiveData<List<Shohid>>
        get() = _shohidList


    suspend fun getShohidList() {
        val result = retrofitServices.getAllShoid()

        if (result.body() != null) {
            _shohidList.postValue(result.body()!!.shohidList)
        }


    }

}