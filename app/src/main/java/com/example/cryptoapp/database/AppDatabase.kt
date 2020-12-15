package com.example.cryptoapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var db: AppDatabase? = null
        private const val NAME_DB = "crypto.db"

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (db == null) {
                    db = Room.databaseBuilder(context, AppDatabase::class.java, NAME_DB).build()
                }

            }
        }
    }
}