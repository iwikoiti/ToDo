package com.bignerdranch.android.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.todo.database.Task
import com.bignerdranch.android.todo.database.TaskDatabase
import com.bignerdranch.android.todo.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {
    private val taskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository: TaskRepository
    val getAllTasks: LiveData<List<Task>>
    init {
        repository = TaskRepository(taskDao)
        getAllTasks = repository.getAllTasks()
    }
    fun insert(task: Task){
        viewModelScope.launch(Dispatchers.IO){ //асинхронное выполнение ui
            repository.insert(task)
        }
    }
    fun delete(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteItem(task)
        }
    }
    fun update(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(task)
        }
    }
    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}