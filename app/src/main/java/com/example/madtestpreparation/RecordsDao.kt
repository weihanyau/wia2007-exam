package com.example.madtestpreparation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecordsDao {
    @Insert
    suspend fun insert(vararg records: Records)

    @Query("SELECT * FROM records ORDER BY day DESC")
    suspend fun getAll(): List<Records>
}