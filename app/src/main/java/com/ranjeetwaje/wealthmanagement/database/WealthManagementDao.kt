package com.ranjeetwaje.wealthmanagement.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WealthManagementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wealthManagementEntity: WealthManagementEntity): Long

    @Update
    suspend fun update(wealthManagementEntity: WealthManagementEntity): Int

    @Query("SELECT * FROM wealth_tracker_table")
    fun getAllTheExpenses(): LiveData<List<WealthManagementEntity>>
}