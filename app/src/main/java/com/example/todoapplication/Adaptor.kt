package com.example.todoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapplication.databinding.ActivityListFragmentBinding

class Adaptor(var data: List<String>?) : Adapter<Adaptor.tasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tasksViewHolder {
        var context = parent.context
        var inflater = LayoutInflater.from(context)
        var binnding = ActivityListFragmentBinding.inflate(inflater, parent, false)
        return tasksViewHolder(binnding)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: tasksViewHolder, position: Int) {
        var postion = data?.get(position)
    }

    class tasksViewHolder(var binding: ActivityListFragmentBinding) : ViewHolder(binding.root) {


    }


}