package com.yousseif.todoyousseifissulahi.tasklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.yousseif.todoyousseifissulahi.R


class TaskListFragment : Fragment() {

    private val taskList = listOf("Task 1", "Task 2","Task 3")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)

        return  rootView
    }

    override fun onViewCreated(
            view: View,
            savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val myAdapter = TaskListAdapter(taskList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = myAdapter
    }
}


