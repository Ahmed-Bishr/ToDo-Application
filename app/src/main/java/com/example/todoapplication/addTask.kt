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

class AddTask : BottomSheetDialogFragment() {

    lateinit var binding: ActivityAddTaskBinding
    lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ActivityAddTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set click listeners for buttons
        binding.done.setOnClickListener {
            // Validate task input, description input, and date input
            if (validationTaskInput() && validationDescriptionInput() && validationDateInput()) {
                Data.getInstance(requireContext()).getDoa().insertTask(
                    TaskDataBase(
                        title = binding.taskInput.text.toString(),
                        description = binding.descriptionInput.text.toString(),
                        date = calendar.time,
                        isDone = false
                    )
                )
                dismiss()
            }


        }

        // Initialize calendar instance
        calendar = Calendar.getInstance()

        // Set click listener for date picker
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

        // Set click listener for time picker
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

    // Validate task input
    private fun validationTaskInput(): Boolean {
        if (binding.taskInput.text?.isEmpty() == true || binding.taskInput.text?.isBlank() == true) {
            binding.taskInput.error = "Required Field"
            return false
        } else
            binding.taskInput.error = null
        return true
    }

    // Validate description input
    private fun validationDescriptionInput(): Boolean {
        if (binding.descriptionInput.text?.isEmpty() == true || binding.descriptionInput.text?.isBlank() == true) {
            binding.descriptionInput.error = "Required Field"
            return false
        } else
            binding.descriptionInput.error = null
        return true
    }

    // Validate date input
    private fun validationDateInput(): Boolean {
        if (binding.date.text?.isEmpty() == true || binding.date.text?.isBlank() == true) {
            binding.date.error = "Required Field"
            return false
        } else
            binding.date.error = null
        return true
    }

    // Set date from date picker
    private fun dateFromDatePicker(year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        binding.date.text = "$dayOfMonth/${month + 1}/$year"
    }

    // Set time from time picker
    private fun timeFromTimePicker(hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        var minuteAdjust : String
        if (minute < 10){
            minuteAdjust = "0$minute"
        }else{
            minuteAdjust = "$minute"
        }
        if (hourOfDay == 0) {
            binding.time.text = "${hourOfDay + 12}:$minuteAdjust :am"
        } else if (hourOfDay > 12) {
            binding.time.text = "${hourOfDay - 12}:$minuteAdjust :pm"
        } else if (hourOfDay < 12) {
            binding.time.text = "$hourOfDay:$minuteAdjust :am"
        } else if (hourOfDay == 12) {
            binding.time.text = "${hourOfDay}:$minuteAdjust :pm"
        }
    }
}
