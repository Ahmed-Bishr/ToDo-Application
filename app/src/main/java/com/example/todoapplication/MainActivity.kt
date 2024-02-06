package com.example.todoapplication

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navigationButton.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_tasks -> {
                    pushFragment(listFragment())
                }

                R.id.navigation_settings -> {
                    pushFragment(settingsFragment())
                }
            }

            return@setOnItemSelectedListener true
        }
        binding.navigationButton.selectedItemId = R.id.navigation_tasks // initialize fragment
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.frameLayout.id, fragment).commit()
    }

}




