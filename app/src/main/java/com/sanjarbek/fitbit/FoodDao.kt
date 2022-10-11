package com.sanjarbek.fitbit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAll(): List<Food>

    @Insert
    fun insertAll(vararg foods: Food)
}