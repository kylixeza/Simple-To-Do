package com.kylix.algostudioseniormobiledevelopertest.screen

import com.kylix.algostudioseniormobiledevelopertest.model.Task
import java.util.SortedMap

data class ToDoState(
    val task: Map<String, List<Task>> = emptyMap()
)
