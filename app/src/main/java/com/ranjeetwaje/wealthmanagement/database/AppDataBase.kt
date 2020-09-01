package com.ranjeetwaje.wealthmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WealthManagementEntity::class, CategoryOptionEntity::class, TransactionTypeEntity::class], version = 6, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract val wealthManagementDao: WealthManagementDao
    abstract val categoryDao: CategoryDao
    abstract val transactionTypeDao: TransactionTypeDao

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