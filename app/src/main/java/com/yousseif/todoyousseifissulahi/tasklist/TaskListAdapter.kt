package com.yousseif.todoyousseifissulahi.tasklist

import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yousseif.todoyousseifissulahi.R

class TaskListAdapter(private val taskList: List<Task>) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    // Déclaration de la variable lambda dans l'adapter:
    var onDeleteTask: ((Task) -> Unit)? = null

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
                    // Utilisation de la lambda dans le ViewHolder:
                    onDeleteTask?.invoke(task)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return taskList.size

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(taskList[position])
    }
}
