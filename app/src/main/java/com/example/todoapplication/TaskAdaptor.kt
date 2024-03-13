package com.example.todoapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapplication.databinding.TaskItemBinding
import com.zerobranch.layout.SwipeLayout
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.properties.Delegates

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

    class TaskViewHolder( val binding: TaskItemBinding) : ViewHolder(binding.root) {

        private val swipeLayout: SwipeLayout = binding.swipeLayout
        fun bind(task: Task) {
            var ifClicked : Boolean = false
            binding.taskName.text = task.title
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            if (task.date != null) {
                binding.taskTime.text = simpleDateFormat.format(task.date)
            }

            if (ifClicked) {
                binding.checkButton.background = ContextCompat.getDrawable(binding.root.context, R.drawable.buttom)
                binding.View.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.blue))
                binding.taskName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.blue))
            }
            // Set click listener for the button
            binding.checkButton.setOnClickListener {
                Log.e("TAG", "bind: buttonClick", )
                ifClicked = true
                // Handle button click here
                // For example, change button appearance or perform some action
                binding.checkButton.background = ContextCompat.getDrawable(binding.root.context, R.drawable.done_icon)
                binding.View.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.green))
                binding.taskName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.green))
            }

            swipeLayout.setOnTouchListener { _, event -> handleTouchEvent(swipeLayout, event) }

            binding.leftView.setOnClickListener {
                deleteTask(task)
            }
        }
        private fun handleTouchEvent(swipeLayout: SwipeLayout, event: MotionEvent): Boolean {
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    // Disable RecyclerView scrolling while swiping
                    swipeLayout.parent.requestDisallowInterceptTouchEvent(false)
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Enable RecyclerView scrolling after swipe gesture is finished
                    swipeLayout.parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            return false
        }
        private fun deleteTask(task: Task) {
            // Delete the task from the database
            val database = TaskDatabase.getInstance(binding.root.context)
            val taskDao = database.getTasksDao()
            taskDao.deleteTask(task)
            // Delete the task from the RecyclerView
            val adapter = binding.root.context as? TaskAdaptor
            adapter?.let {
                val taskList = it.tasks?.toMutableList()
                taskList?.remove(task)
                it.updateData(taskList.orEmpty())
            }
        }
    }

}
