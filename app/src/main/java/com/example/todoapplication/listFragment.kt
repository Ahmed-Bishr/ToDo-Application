package com.example.todoapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.todoapplication.databinding.ActivityListFragmentBinding

class listFragment : Fragment() {

    lateinit var binding: ActivityListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityListFragmentBinding.inflate(inflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Kotlin
        val calendarView = binding.calendarView
        val selectedDayIndicator = R.drawable.day_indicator
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Customize the selected day indicator
            calendarView.setBackgroundResource(R.drawable.day_indicator)

        }

    }

}