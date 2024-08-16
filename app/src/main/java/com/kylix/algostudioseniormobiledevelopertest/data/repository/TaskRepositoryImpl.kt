package com.kylix.algostudioseniormobiledevelopertest.data.repository

import android.util.Log
import com.kylix.algostudioseniormobiledevelopertest.data.local.TaskDao
import com.kylix.algostudioseniormobiledevelopertest.data.local.entitiy.TaskEntity
import com.kylix.algostudioseniormobiledevelopertest.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
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
            tasks.sortedBy { LocalDate.parse(it.date, formatter) }.groupBy { it.date }.map { entry ->
                entry.key to entry.value.sortedBy { it.position }
            }.toMap()
        }
    }

    override suspend fun insertTask(
        title: String,
        description: String,
        date: String,
        time: String?
    ) {
        val currentMaxPosition = taskDao.getMaxPosition()?.first() ?: 0
        val entity = TaskEntity(
            title = title,
            description = description,
            date = date,
            time = time,
            position = currentMaxPosition + 1
        )
        taskDao.insertTask(entity)
    }

    override suspend fun deleteTask(id: Int) {
        taskDao.deleteTask(id)
    }
}