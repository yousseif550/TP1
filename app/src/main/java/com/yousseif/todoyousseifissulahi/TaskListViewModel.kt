package com.yousseif.todoyousseifissulahi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousseif.todoyousseifissulahi.network.Api.tasksWebService
import com.yousseif.todoyousseifissulahi.network.TasksRepository
import com.yousseif.todoyousseifissulahi.tasklist.Task
import kotlinx.coroutines.launch

class TaskListViewModel: ViewModel() {

    private val repository = TasksRepository()
    private val _taskList = MutableLiveData<List<Task>>()
    public val taskList: LiveData<List<Task>> = _taskList

    fun loadTasks() {
        viewModelScope.launch {
            val fetchedTasks = repository.loadTasks()
            _taskList.value = fetchedTasks!!
        }
    }



    fun deleteTask(task: Task) {
        viewModelScope.launch {
            val editableList = _taskList.value.orEmpty().toMutableList()

            val removes = repository.removeTask(task)
            if(removes){
                editableList.remove(task)
                _taskList.value = editableList
            }
        }
    }


    fun editTask(task: Task) {
        viewModelScope.launch {
            val tasksResponse = repository.updateTask(task)!!
            val editableList = _taskList.value.orEmpty().toMutableList()
            val position = editableList.indexOfFirst { tasksResponse.id == it.id }
            editableList[position] = tasksResponse
            _taskList.value = editableList
        }
    }

    fun addTask(task: Task){
        viewModelScope.launch {
            val editableList = _taskList.value.orEmpty().toMutableList()
            val new = repository.createTask(task)!!
            editableList.add(new)
            _taskList.value = editableList

        }
    }

}