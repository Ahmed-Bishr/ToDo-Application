package com.example.todoapplication

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapplication.databinding.ActivityAddTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdaptor(var tasks: List<Task>?) : Adapter<TaskAdaptor.TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ActivityAddTaskBinding.inflate(inflater, parent, false)
        return TasksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tasks?.size ?: 0
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val items = tasks?.get(position) ?: return
        holder.bind(items)
    }

    fun upDateData(tasks: List<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class TasksViewHolder(val binding: ActivityAddTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskInput.text = Editable.Factory.getInstance().newEditable(task.title)
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            if (task.date != null)
                binding.date.text = simpleDateFormat.format(task.date)
        }

    }


}