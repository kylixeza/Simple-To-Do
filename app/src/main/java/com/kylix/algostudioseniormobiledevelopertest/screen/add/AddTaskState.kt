package com.kylix.algostudioseniormobiledevelopertest.screen.add

data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val time: String = "",

    val showDateBottomSheet: Boolean = false,
    val showTimeBottomSheet: Boolean = false
)
