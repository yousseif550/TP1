package com.yousseif.todoyousseifissulahi.tasklist

import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yousseif.todoyousseifissulahi.R

object TasksDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
}

class TaskListAdapter() : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TasksDiffCallback) {


    var onDeleteTask: ((Task) -> Unit)? = null
    var onEditTask: ((Task) -> Unit)? = null


    inner class TaskViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.apply {
                val textView =  findViewById<TextView>(R.id.task_title)
                textView.text = task.title
                val descriptionTextView =  findViewById<TextView>(R.id.task_description)
                descriptionTextView.text = task.description

                val imageButton = findViewById<ImageButton>(R.id.imageButton)
                imageButton.setOnClickListener {
                    onDeleteTask?.invoke(task)
                }
                val imageButtonEdit = findViewById<ImageButton>(R.id.imageButtonEdit)
                imageButtonEdit.setOnClickListener {
                    onEditTask?.invoke(task)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
