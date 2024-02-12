package com.example.todoapplication

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapplication.databinding.ActivityAddTaskBinding
import com.example.todoapplication.databinding.ActivityListFragmentBinding

class Adaptor(val tasks: List<Task>? = null) : Adapter<Adaptor.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        var context = parent.context
        var inflater = LayoutInflater.from(context)
        var binding = ActivityAddTaskBinding.inflate(inflater, parent, false)
        return TasksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks?.size ?: 0
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val items = tasks?.get(position)?:return
        holder.bind(items)
    }

    class TasksViewHolder(val binding: ActivityAddTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskInput.text = Editable.Factory.getInstance().newEditable(task.title)
            binding.descriptionInput.text = Editable.Factory.getInstance().newEditable(task.description)
            binding.time.text = task.date.toString()
        }

    }


}