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
    lateinit var taskAdaptor: TaskAdaptor
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
        taskAdaptor = TaskAdaptor(null)
        binding.listTaskRv.adapter = taskAdaptor
        val tasks = TaskDataBase.getInstance(requireContext()).getDoa().getAllTask()
        taskAdaptor.upDateData(tasks)
        calendar = Calendar.getInstance()
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            calendar.set(Calendar.YEAR, date.year)
            calendar.set(Calendar.MONTH, date.month - 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            calendar.set(Calendar.DAY_OF_MONTH, date.day)
            val tasks = TaskDataBase
                .getInstance(requireContext())
                .getDoa()
                .getTaskByDate(calendar.time)
            taskAdaptor.upDateData(tasks)
        }

    }
}