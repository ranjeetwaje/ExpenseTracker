package com.ranjeetwaje.wealthmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WealthManagementEntity::class], version = 4, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract val wealthManagementDao: WealthManagementDao

    companion object {

        @Volatile
        private var INSTANCE : AppDataBase? = null

        fun getInstance(context: Context?): AppDataBase {
            synchronized(lock = this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context!!.applicationContext,
                        AppDataBase::class.java,
                        "wealth_management_database"
                    ).build()
                }
                return instance
            }
        }
    }
}