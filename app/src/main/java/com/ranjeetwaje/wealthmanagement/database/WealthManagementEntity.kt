package com.ranjeetwaje.wealthmanagement.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wealth_tracker_table")
data class WealthManagementEntity (

    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,

    var expenseType: String,

    var expensePlace: String,

    var expenseAmount: String,

    var expenseDate: String,

    var category: String,

    var transactionType: String,

    var note: String

)

@Entity(tableName = "category_option_table")
data class CategoryOptionEntity (

    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,

    var category: String

)

@Entity(tableName = "transaction_type_table")
data class TransactionTypeEntity (

    @PrimaryKey(autoGenerate = true)
    var Id: Long = 0L,

    var transaction_type: String

)
