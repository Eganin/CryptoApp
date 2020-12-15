package com.example.cryptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapp.data.dao.CoinPriceInfoDao
import com.example.cryptoapp.data.pojo.CoinPriceInfo

@Database(entities = [CoinPriceInfo::class],version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinPriceInfoDao(): CoinPriceInfoDao

    companion object {
        private var db: AppDatabase? = null
        private const val NAME_DB = "crypto.db"

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        NAME_DB
                    ).build()
                db = instance
                return instance
            }
        }

    }
}