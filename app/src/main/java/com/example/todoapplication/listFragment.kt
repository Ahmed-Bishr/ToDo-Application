package com.example.todoapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapplication.databinding.ActivityListFragmentBinding
import java.util.Calendar

class listFragment : Fragment() {

    lateinit var binding: ActivityListFragmentBinding
    lateinit var adaptor: TaskAdaptor
    lateinit var calendar: Calendar
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
        adaptor = TaskAdaptor(null)
        binding.listTaskRv.adapter = adaptor
        calendar = Calendar.getInstance() // 2/3/2024 2:10PM 1212313211
        //   2/3/2024 2:12PM                                1212313213
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            calendar.set(Calendar.YEAR, date.year)
            calendar.set(Calendar.MONTH, date.month - 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            Log.e("TAG", "onViewCreated: CALENDAR LIBRARY DATE ${date.month}")
            Log.e("TAG", "onViewCreated: Calendar ${calendar.get(Calendar.MONTH)}")
            calendar.set(Calendar.DAY_OF_MONTH, date.day)
            val tasks = TaskDatabase
                .getInstance(requireContext())
                .getTasksDao()
                .getTasksByDate(calendar.time)
            adaptor.updateData(tasks)
        }
    }
}