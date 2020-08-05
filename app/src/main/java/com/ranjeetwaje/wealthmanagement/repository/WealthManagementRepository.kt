package com.ranjeetwaje.wealthmanagement.repository

import com.ranjeetwaje.wealthmanagement.database.WealthManagementDao
import com.ranjeetwaje.wealthmanagement.database.WealthManagementEntity

class WealthManagementRepository (private val dao: WealthManagementDao) {

    val expenseList = dao.getAllTheExpenses()

    suspend fun insert(entity: WealthManagementEntity): Long {
        return dao.insert(entity)
    }

    suspend fun update(entity: WealthManagementEntity): Int {
        return dao.update(entity)
    }

}