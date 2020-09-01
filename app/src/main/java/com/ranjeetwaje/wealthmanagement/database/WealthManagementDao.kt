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

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(categoryOptionEntity: CategoryOptionEntity): Long

    @Query("SELECT * FROM category_option_table")
    fun getAllCategories(): LiveData<List<CategoryOptionEntity>>

    @Query("DELETE FROM category_option_table")
    suspend fun delete()
}

@Dao
interface TransactionTypeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionTypeEntity: TransactionTypeEntity): Long

    @Query("SELECT * FROM transaction_type_table")
    fun getAllTransactionTypes(): LiveData<List<TransactionTypeEntity>>

    @Query("DELETE FROM transaction_type_table")
    suspend fun delete()
}