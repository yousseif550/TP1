package com.yousseif.todoyousseifissulahi.tasklist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yousseif.todoyousseifissulahi.R
import com.yousseif.todoyousseifissulahi.TaskListViewModel
import com.yousseif.todoyousseifissulahi.network.Api
import com.yousseif.todoyousseifissulahi.network.TasksRepository
import com.yousseif.todoyousseifissulahi.task.TaskActivity
import com.yousseif.todoyousseifissulahi.task.TaskActivity.Companion.ADD_TASK_REQUEST_CODE
import com.yousseif.todoyousseifissulahi.task.TaskActivity.Companion.TASK_KEY
import kotlinx.coroutines.launch
import okhttp3.internal.notify


class TaskListFragment : Fragment() {


    /*private val tasksRepository = TasksRepository()

    private val taskList = mutableListOf(
        Task(id = "id_1", title = "Task 1", description = "description 1"),
        Task(id = "id_2", title = "Task 2"),
        Task(id = "id_3", title = "Task 3")
    )*/

    val myAdapter = TaskListAdapter()

    private val viewModel: TaskListViewModel by viewModels()


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

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = myAdapter

        val addbutton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        addbutton.setOnClickListener{

            val intent = Intent(activity, TaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)

        }

        myAdapter.onDeleteTask = { task ->
            viewModel.deleteTask(task)
        }

        myAdapter.onEditTask = { task ->
            val intent = Intent(context, TaskActivity::class.java)
            intent.putExtra(TaskActivity.TASK_KEY, task)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)
        }


        viewModel.taskList.observe(viewLifecycleOwner) { list ->
            myAdapter.submitList(list)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val task = data?.getSerializableExtra(TaskActivity.TASK_KEY) as? Task

        if ( requestCode == TaskActivity.ADD_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK && task != null) {
            val index  = viewModel.taskList.value?.indexOfFirst { it.id == task.id }
            if (index == -1) {
                viewModel.addTask(task)
            } else {
                viewModel.editTask(task)
            }
        }
    }


    override fun onResume() {
        super.onResume()


        lifecycleScope.launch {

            val userInfo = Api.userService.getInfo().body()!!
            val apiText = view?.findViewById<TextView>(R.id.textView)
            apiText?.text = "${userInfo.firstName} ${userInfo.lastName}"

        }
        viewModel.loadTasks()
    }

}