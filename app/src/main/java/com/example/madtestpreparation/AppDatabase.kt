package com.example.madtestpreparation

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Records::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordsDao(): RecordsDao
}