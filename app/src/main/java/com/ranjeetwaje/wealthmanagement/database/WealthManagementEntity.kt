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

    var expenseDate: String

)