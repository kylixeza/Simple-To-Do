package com.kylix.algostudioseniormobiledevelopertest.screen.home

import com.kylix.algostudioseniormobiledevelopertest.model.Task

data class HomeState(
    val task: Map<String, List<Task>> = emptyMap()
)
