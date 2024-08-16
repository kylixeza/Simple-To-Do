package com.kylix.algostudioseniormobiledevelopertest.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val position: Int,
    val isSelected: Boolean = false
)
