package com.sanjarbek.fitbit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            loadFragment(DashboardFragment())
        }

        val bottomNavigationMenu = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_log -> {loadFragment(DashboardFragment())
                    Log.d("TAG", "onCreate: dash")
                    return@setOnItemSelectedListener true }
                R.id.action_dashboard -> {loadFragment(LogFragment())
                    Log.d("TAG", "onCreate: load")
                    return@setOnItemSelectedListener true}
                else -> true
            }
        }

    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_view,fragment)
        transaction.addToBackStack(null)
        transaction.setReorderingAllowed(true)
        transaction.commit()
    }
}