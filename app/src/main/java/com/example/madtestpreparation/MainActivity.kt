package com.example.madtestpreparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.madtestpreparation.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var records :List<Records>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recordDatabase"
        ).build()

        CoroutineScope(Dispatchers.IO).launch {
            records = db.recordsDao().getAll()
            var totalML = 0;

            if(records.isNotEmpty()){
                val firstRecord = records.get(0);
                val currentDay = firstRecord.day;

                for(record in records){
                    if(record.day == currentDay){
                        totalML += record.amount!!
                    }
                }
                binding.totalText.setText(totalML.toString() + "ml")
            }
        }

        binding.apply {
            setContentView(root)

            btnRecord.setOnClickListener {
                val intent = Intent(this@MainActivity, RecordActivity::class.java)
                startActivity(intent)
            }

            btnSummary.setOnClickListener {
                val intent = Intent(this@MainActivity, SummaryActivity::class.java)
                startActivity(intent)
            }

            btnRefresh.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    records = db.recordsDao().getAll()
                    var totalML = 0;

                    if(records.isNotEmpty()){
                        val firstRecord = records.get(0);
                        val currentDay = firstRecord.day;

                        for(record in records){
                            if(record.day == currentDay){
                                totalML += record.amount!!
                            }
                        }
                        binding.totalText.setText(totalML.toString() + "ml")
                    }
                }
            }
        }
    }
}