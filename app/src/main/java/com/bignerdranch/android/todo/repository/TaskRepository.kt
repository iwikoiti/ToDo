package com.bignerdranch.android.todo.repository

import androidx.lifecycle.LiveData
import com.bignerdranch.android.todo.database.Task
import com.bignerdranch.android.todo.database.TaskDao

class TaskRepository(val taskDao: TaskDao) {
    suspend fun insert(task: Task) = taskDao.insert(task)
    suspend fun updateData(task: Task) = taskDao.update(task)
    suspend fun  deleteItem (task: Task) = taskDao.delete(task)
    suspend fun deleteAll(){
        taskDao.deleteAll()
    }
    fun getAllTasks(): LiveData<List<Task>> = taskDao.getAllTasks()
}