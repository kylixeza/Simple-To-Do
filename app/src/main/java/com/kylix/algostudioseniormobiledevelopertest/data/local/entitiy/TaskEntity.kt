package com.kylix.algostudioseniormobiledevelopertest.data.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kylix.algostudioseniormobiledevelopertest.model.Task

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String? = null,
    @ColumnInfo(name = "position")
    val position: Int
) {
    fun toTask(): Task {
        return Task(
          id, title, description, date, time.orEmpty(), position, false
        )
    }
}
