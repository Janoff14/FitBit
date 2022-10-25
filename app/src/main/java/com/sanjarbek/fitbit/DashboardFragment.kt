package com.sanjarbek.fitbit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.rcv_food)
        var foodArrayList: java.util.ArrayList<Food>

        val db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, "FoodDb"
        ).build()

        val foodDao = db.foodDao()

        CoroutineScope(Dispatchers.IO).launch {

            foodArrayList = foodDao.getAll() as ArrayList<Food> /* = java.util.ArrayList<com.sanjarbek.fitbit.Food> */
            val adaper = FoodAdapter(requireActivity(), foodArrayList)
            recycler.adapter = adaper
        }

        val button = view.findViewById<Button>(R.id.btn_addFood)
        button.setOnClickListener {
            val intent = Intent(requireActivity(), AddFoodActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    companion object {

    }
}