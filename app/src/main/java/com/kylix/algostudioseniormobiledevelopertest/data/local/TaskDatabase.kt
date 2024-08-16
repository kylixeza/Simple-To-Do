package com.kylix.algostudioseniormobiledevelopertest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kylix.algostudioseniormobiledevelopertest.data.local.entitiy.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}