package com.jihan.shohid.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {


    @Query("select * from martyr_information order by id asc")
    fun getAll(): List<Shohid>

    @Insert
    suspend fun insertSohid(sohid: List<Shohid>)

}