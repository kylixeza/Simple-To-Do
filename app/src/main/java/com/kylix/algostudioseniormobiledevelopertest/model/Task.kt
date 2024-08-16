package com.kylix.algostudioseniormobiledevelopertest.model

import com.kylix.algostudioseniormobiledevelopertest.data.local.entitiy.TaskEntity

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val position: Int = 0,
    val isSelected: Boolean = false
) {
    fun toTaskEntity(
        position: Int
    ): TaskEntity {
        return TaskEntity(
            title = title,
            description = description,
            date = date,
            time = time,
            position = position
        )
    }
}
