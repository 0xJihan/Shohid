package com.jihan.shohid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Shohid::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getDao(): UserDao

    companion object {
        private var INSTANCE: UserDatabase? = null


        fun getDatabase(context: Context): UserDatabase {
            synchronized(this) {


                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, UserDatabase::class.java, "user_database"
                    ).build()
                }

                return INSTANCE!!
            }
        }


    }

}