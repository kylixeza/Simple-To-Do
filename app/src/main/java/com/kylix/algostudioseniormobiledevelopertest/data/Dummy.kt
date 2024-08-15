package com.kylix.algostudioseniormobiledevelopertest.data

import com.kylix.algostudioseniormobiledevelopertest.model.Task

object Dummy {

    fun getTasks() = listOf(
        Task(
            id = 1,
            title = "Task 1",
            description = "Description 1",
            date = "2022-08-15",
            time = "10:00"
        ),
        Task(
            id = 2,
            title = "Task 2",
            description = "Description 2",
            date = "2022-08-15",
            time = "11:00"
        ),
        Task(
            id = 3,
            title = "Task 3",
            description = "Description 3",
            date = "2022-08-15",
            time = "12:00"
        ),
        Task(
            id = 4,
            title = "Task 4",
            description = "Description 4",
            date = "2022-08-16",
            time = "13:00"
        ),Task(
            id = 5,
            title = "Task 5",
            description = "Description 5",
            date = "2022-08-16",
            time = "14:00"
        ),
        Task(
            id = 6,
            title = "Task 6",
            description = "Description 6",
            date = "2022-08-17",
            time = "15:00"
        ),
        Task(
            id = 7,
            title = "Task 7",
            description = "Description 7",
            date = "2022-08-17",
            time = "16:00"
        ),
    ).sortedBy { it.date }.groupBy { it.date }
}