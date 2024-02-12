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
    lateinit var adaptor: Adaptor
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
        adaptor = Adaptor(null)
        binding.listTaskRv.adapter = adaptor
        val tasks = TaskDataBase.getInstance(requireContext()).getDoa().getAllTask()
        adaptor.upDateData(tasks)

    }

}