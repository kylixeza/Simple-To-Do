package com.kylix.algostudioseniormobiledevelopertest.data.repository

import com.kylix.algostudioseniormobiledevelopertest.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAllTasks(): Flow<Map<String, List<Task>>>
    suspend fun insertTask(
        title: String,
        description: String,
        date: String,
        time: String?
    )
    suspend fun deleteTask(task: Task)
}