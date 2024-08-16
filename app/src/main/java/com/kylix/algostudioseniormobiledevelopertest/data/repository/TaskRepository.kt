package com.kylix.algostudioseniormobiledevelopertest.data.repository

import com.kylix.algostudioseniormobiledevelopertest.model.Task

interface TaskRepository {
    fun getAllTasks(): List<Task>
    fun insertTask(task: Task)
    fun deleteTask(task: Task)
}