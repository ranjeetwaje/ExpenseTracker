package com.ranjeetwaje.wealthmanagement.repository

import com.ranjeetwaje.wealthmanagement.database.*

class WealthManagementRepository (private val dao: WealthManagementDao, private val categoryDao: CategoryDao, private val transactionTypeDao: TransactionTypeDao) {

    val expenseList = dao.getAllTheExpenses()

    suspend fun insert(entity: WealthManagementEntity): Long {
        return dao.insert(entity)
    }

    suspend fun update(entity: WealthManagementEntity): Int {
        return dao.update(entity)
    }

    val categoryList = categoryDao.getAllCategories()
    suspend fun insert(entity: CategoryOptionEntity): Long {
        return categoryDao.insert(entity)
    }
    suspend fun deleteCategories() {
        categoryDao.delete()
    }

    val transactionTypes = transactionTypeDao.getAllTransactionTypes()
    suspend fun insert(entity: TransactionTypeEntity): Long {
        return transactionTypeDao.insert(entity)
    }

    suspend fun deleteTransactionTypes() {
        transactionTypeDao.delete()
    }


}