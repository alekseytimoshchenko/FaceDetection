package com.example.facedetection.data.local.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.facedetection.data.local.model.ImageObj
import com.example.facedetection.utils.Constants

/**
 * The Room database that contains the Users table
 */
@Database(entities = [
    ImageObj::class
], version = 1)
abstract class ImageDB : RoomDatabase() {

    abstract fun imageDao(): ImageDao

    companion object {

        @Volatile private var INSTANCE: ImageDB? = null

        fun getInstance(context: Context): ImageDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ImageDB::class.java, Constants.DB_NAME)
                .build()
    }
}