package com.bignerdranch.android.todo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query(value = "DELETE FROM task_table")
    suspend fun deleteAll()

    @Query(value = "SELECT * FROM task_table ORDER BY priority ASC")
    fun getAllTasks(): LiveData<List<Task>>
}