package com.kylix.algostudioseniormobiledevelopertest.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kylix.algostudioseniormobiledevelopertest.data.local.entitiy.TaskEntity

interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): List<TaskEntity>

    @Query("SELECT MAX(position) FROM task")
    fun getMaxPosition(): Int?

    @Insert
    fun insertTask(task: TaskEntity)

    @Delete
    fun deleteTask(task: TaskEntity)
}