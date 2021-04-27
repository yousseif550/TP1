package com.yousseif.todoyousseifissulahi.network

import com.yousseif.todoyousseifissulahi.tasklist.Task

class TasksRepository {
    private val tasksWebService = Api.tasksWebService

    suspend fun loadTasks(): List<Task>? {
        val response = tasksWebService.getTasks()
        return if (response.isSuccessful ) response.body() else null
    }

    suspend fun removeTask(task: Task): Boolean {
        val response = tasksWebService.deleteTask(task.id)
        return response.isSuccessful
    }

    suspend fun createTask(task: Task): Task? {
        val response = tasksWebService.createTask(task)
        return if (response.isSuccessful ) response.body() else null
    }

    suspend fun updateTask(task: Task): Task? {
        val response = tasksWebService.updateTask(task)
        return if (response.isSuccessful ) response.body() else null
    }

    /*private val _taskList = MutableLiveData<List<Task>>()

    public val taskList: LiveData<List<Task>> = _taskList

    suspend fun refresh() {
        val tasksResponse = tasksWebService.getTasks()
        if (tasksResponse.isSuccessful) {
            val fetchedTasks = tasksResponse.body()
            _taskList.value = fetchedTasks!!
        }

    }


    suspend fun update(task: Task) {
        val tasksResponse = tasksWebService.updateTask(task)
        if (tasksResponse.isSuccessful) {
            val updatedTask = tasksResponse.body()
            val editableList = _taskList.value.orEmpty().toMutableList()
            val position = editableList.indexOfFirst { updatedTask?.id == it.id }
            if (updatedTask != null) {
                editableList[position] = updatedTask
            }
            _taskList.value = editableList
        }
    }*/

}