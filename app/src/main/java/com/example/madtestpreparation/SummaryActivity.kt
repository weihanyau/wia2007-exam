package com.example.madtestpreparation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.madtestpreparation.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recordDatabase"
        ).build()

        binding.apply {
            setContentView(root)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}