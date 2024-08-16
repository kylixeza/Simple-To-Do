package com.kylix.algostudioseniormobiledevelopertest.data.repository

import android.util.Log
import com.kylix.algostudioseniormobiledevelopertest.data.local.TaskDao
import com.kylix.algostudioseniormobiledevelopertest.model.Task
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TaskRepositoryImpl(
    private val taskDao: TaskDao
): TaskRepository {
    override suspend fun getAllTasks(): Flow<Map<String, List<Task>>> {
        return taskDao.getAllTasks().map { entities ->
            val tasks = entities.map { taskEntity ->
                taskEntity.toTask()
            }
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            Log.d("TaskRepositoryImpl", "$tasks")
            tasks.sortedBy { LocalDate.parse(it.date, formatter) }.groupBy { it.date }
        }
    }

    override suspend fun insertTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }
}