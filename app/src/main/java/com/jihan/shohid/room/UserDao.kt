package com.jihan.shohid.room


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {


    @Query("select * from martyr_information order by id asc")
    fun getAll(): List<Shohid>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSohid(sohid: List<Shohid>)


    @Query("SELECT * FROM martyr_information ORDER BY RANDOM() LIMIT 1")
    fun getRandomShohid(): Shohid



}