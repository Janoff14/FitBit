package com.sanjarbek.fitbit

import android.R.attr.data
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.EntryXComparator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LogFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(
            requireActivity(),
            AppDatabase::class.java, "FoodDb"
        ).build()

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        val foodDao = db.foodDao()

//        val lineChart = view.findViewById<BarChart>(R.id.chart)

        var foodArrayList: ArrayList<Food> = ArrayList()
        val entries = ArrayList<BarEntry>()

//        lineChart.setScaleEnabled(true)

        CoroutineScope(Dispatchers.IO).launch {
            foodArrayList = foodDao.getAll() as ArrayList<Food> /* = java.util.ArrayList<com.sanjarbek.fitbit.Food> */
            val adaper = FoodAdapter(requireActivity(), foodArrayList)
//            recycler.adapter = adaper
//            for (food in foodArrayList){
//                entries.add(BarEntry(food.uid.toFloat(), food.calories.toFloat()))
//            }
//            Log.d("TAG", "onViewCreated: ${entries}")
//            val lineDataSet = BarDataSet(entries as List<BarEntry>?, "Label")
//            val linedata = BarData(lineDataSet)
//            lineChart.data = linedata
//            lineChart.invalidate()
        }



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    companion object {

    }
}
