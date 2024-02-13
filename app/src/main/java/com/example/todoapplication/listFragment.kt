package com.example.todoapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapplication.databinding.ActivityListFragmentBinding

class listFragment : Fragment() {

    lateinit var binding: ActivityListFragmentBinding
    lateinit var taskAdaptor: TaskAdaptor
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

    }

}