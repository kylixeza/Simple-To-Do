package com.kylix.algostudioseniormobiledevelopertest.data.repository

import com.kylix.algostudioseniormobiledevelopertest.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAllTasks(): Flow<Map<String, List<Task>>>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
}