package com.example.todoapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapplication.databinding.ActivityAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class addTask : BottomSheetDialogFragment() {

    lateinit var binding: ActivityAddTaskBinding
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityAddTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.done.setOnClickListener {
            validationTaskInput()
            validationDescriptionInput()
            validationDateInput()
        }
        calendar = Calendar.getInstance()
        binding.date.setOnClickListener {
            val picker = DatePickerDialog(
                requireContext(),
                { view, year, month, dayOfMonth ->
                    dateFromDatePicker(year, month, dayOfMonth)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            picker.show()
        }

        binding.time.setOnClickListener {
            val picker = TimePickerDialog(
                requireContext(),
                { view, hourOfDay, minute ->
                    timeFromTimePicker(hourOfDay, minute)
                }, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), false
            )
            picker.show()
        }

    }

    private fun validationTaskInput(): Boolean {
        if (binding.taskInput.text?.isEmpty() == true || binding.taskInput.text?.isBlank() == true) {
            binding.taskInput.error = "Required Field"
            return false
        } else
            binding.taskInput.error = null
        return true
    }

    private fun validationDescriptionInput(): Boolean {
        if (binding.descriptionInput.text?.isEmpty() == true || binding.descriptionInput.text?.isBlank() == true) {
            binding.descriptionInput.error = "Required Field"
            return false
        } else
            binding.descriptionInput.error = null
        return true
    }

    private fun validationDateInput(): Boolean {
        if (binding.date.text?.isEmpty() == true || binding.date.text?.isBlank() == true) {
            binding.date.error = "Required Field"
            return false
        } else
            binding.date.error = null
        return true
    }

    private fun dateFromDatePicker(year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        binding.date.text = "$dayOfMonth/${month + 1}/$year"
    }

    private fun timeFromTimePicker(hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        if (hourOfDay == 12) {
            binding.time.text = "${hourOfDay}:$minute :pm"
        } else if (hourOfDay > 12) {
            binding.time.text = "${hourOfDay - 12}:$minute :pm"
        } else if (hourOfDay < 12) {
            binding.time.text = "$hourOfDay:$minute :am"
        }
    }

}