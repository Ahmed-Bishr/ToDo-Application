package com.example.todoapplication

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapplication.databinding.ActivityAddTaskBinding
import com.example.todoapplication.databinding.TaskItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdaptor(var tasks: List<Task>?) : Adapter<TaskAdaptor.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = TaskItemBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks?.size ?: 0
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = tasks?.get(position) ?: return
        holder.bind(item)
    }

    fun updateData(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class TaskViewHolder(val binding : TaskItemBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskName.text = task.title
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            if (task.date != null)
                binding.taskTime.text = simpleDateFormat.format(task.date)
        }

    }

}