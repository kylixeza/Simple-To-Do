package com.kylix.algostudioseniormobiledevelopertest.screen.home

import com.kylix.algostudioseniormobiledevelopertest.model.Task

data class HomeState(
    val task: Map<String, List<Task>> = emptyMap(),
    val currentDateWantToDelete: String = "",
    val specificTaskIdWantToDelete: Int = -1,

    val deleteForMultipleItems: Boolean = false,
    val showDeleteModal: Boolean = false,
)
