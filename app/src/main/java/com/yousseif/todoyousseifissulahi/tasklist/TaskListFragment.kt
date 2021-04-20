package com.yousseif.todoyousseifissulahi.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yousseif.todoyousseifissulahi.R
import com.yousseif.todoyousseifissulahi.task.TaskActivity
import com.yousseif.todoyousseifissulahi.task.TaskActivity.Companion.ADD_TASK_REQUEST_CODE


class TaskListFragment : Fragment() {

    private val taskList = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id = "id_3", title = "Task 3")
    )


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        return  rootView
    }
    val myAdapter = TaskListAdapter(taskList)

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = myAdapter

        // "implémentation" de la lambda dans le fragment:
        myAdapter.onDeleteTask = { task ->
            taskList.remove(task)
            myAdapter.notifyDataSetChanged()
        }
        val addbutton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addbutton.setOnClickListener{

            val intent = Intent(activity, TaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val task = data?.getSerializableExtra(TaskActivity.TASK_KEY) as? Task

        if ( requestCode == TaskActivity.ADD_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (task != null) {
                taskList.add(task)
            }
        }
        myAdapter.notifyDataSetChanged()

    }



}