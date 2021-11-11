package com.example.roomhomeworkdefaultrepo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomhomeworkdefaultrepo.room.dao.PostDAO
import com.example.roomhomeworkdefaultrepo.room.dto.PostDTO

@Database(entities = [PostDTO::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDAO() : PostDAO

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "post_tb"
            ).build()


            return instance
        }
    }
}