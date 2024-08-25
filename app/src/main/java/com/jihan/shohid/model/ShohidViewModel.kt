package com.jihan.shohid.model

import com.jihan.shohid.room.Shohid
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShohidViewModel(private val repository: ShohidRepository) : ViewModel() {

    val shoidList: LiveData<List<Shohid>> get() = repository.shohidList
    val randomShohid = repository.randomShohid

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getShohidList()
        }
    }




    fun refreshRandomShohid(){
        viewModelScope.launch (Dispatchers.IO) {
        repository.refreshShohid()
        }
    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getShohidList()
        }
    }


}