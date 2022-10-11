package com.sanjarbek.fitbit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycler = findViewById<RecyclerView>(R.id.rcv_food)
        var foodArrayList: java.util.ArrayList<Food> = ArrayList()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "FoodDb"
        ).build()

        val foodDao = db.foodDao()

        CoroutineScope(Dispatchers.IO).launch {

            foodArrayList = foodDao.getAll() as ArrayList<Food> /* = java.util.ArrayList<com.sanjarbek.fitbit.Food> */
            val adaper = FoodAdapter(applicationContext, foodArrayList)
            recycler.adapter = adaper
        }



        val button = findViewById<Button>(R.id.btn_addFood)
        button.setOnClickListener {
            val intent = Intent(this, AddFoodActivity::class.java)
            startActivity(intent)
        }
    }
}