package com.example.madtestpreparation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Records(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val day: Int?,
    val category: String?,
    val amount: Int?,
    val nameOfBeverages: String?
)