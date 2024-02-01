package com.example.madtestpreparation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.madtestpreparation.databinding.ActivityRecordBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        val beverages = resources.getStringArray(R.array.Beverages)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "recordDatabase"
        ).build()

        binding.apply {
            setContentView(root)
            spinner.adapter = ArrayAdapter(this@RecordActivity, android.R.layout.simple_spinner_dropdown_item, beverages)

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    category = beverages[position]
                    Log.d("categories", category)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            btnAdd.setOnClickListener {
                val record = Records(day = dayText.text.toString().toInt(), nameOfBeverages = nameText.text.toString(), amount = amountText.text.toString().toInt(), category = category)
                CoroutineScope(Dispatchers.IO).launch {
                    db.recordsDao().insert(record)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}