package com.kylix.algostudioseniormobiledevelopertest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kylix.algostudioseniormobiledevelopertest.data.local.entitiy.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT MAX(position) FROM task")
    fun getMaxPosition(): Flow<Int>?

    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: Int)
}