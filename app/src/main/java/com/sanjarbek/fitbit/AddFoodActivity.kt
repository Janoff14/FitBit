package com.sanjarbek.fitbit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        val btn_register = findViewById<Button>(R.id.btn_register)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "FoodDb"
        ).build()

        val foodDao = db.foodDao()
        btn_register.setOnClickListener {
            val name = findViewById<EditText>(R.id.edt_foodName).text.toString()
            val calories = findViewById<EditText>(R.id.edt_calories).text.toString()

            val food = Food(foodName = name, calories = calories)

            CoroutineScope(Dispatchers.IO).launch {

                foodDao.insertAll(food)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}