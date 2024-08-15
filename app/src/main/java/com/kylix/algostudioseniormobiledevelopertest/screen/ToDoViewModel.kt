package com.kylix.algostudioseniormobiledevelopertest.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kylix.algostudioseniormobiledevelopertest.data.Dummy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ToDoViewModel: ViewModel() {

    private val _toDoState = MutableStateFlow(ToDoState())
    val toDoState = _toDoState.asStateFlow()

    init {
        getTasks()
    }

    private fun getTasks() {
        val tasks = Dummy.getTasks()
        Log.d("ToDoViewModel", "$tasks")
        _toDoState.value = _toDoState.value.copy(
            task = tasks
        )
    }
}