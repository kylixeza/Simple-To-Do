package com.kylix.algostudioseniormobiledevelopertest.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kylix.algostudioseniormobiledevelopertest.data.local.Dummy
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

    fun onTaskChecked(id: Int, isChecked: Boolean) {
        val tasks = _toDoState.value.task.mapValues { (date, tasks) ->
            tasks.map { task ->
                if (task.id == id) {
                    task.copy(isSelected = isChecked)
                } else {
                    task
                }
            }
        }
        _toDoState.value = _toDoState.value.copy(
            task = tasks
        )
    }

    fun deleteItemsByDay(day: String) {
        Dummy.deleteItemsByDay(day)
        val tasks = Dummy.getTasks()
        _toDoState.value = _toDoState.value.copy(
            task = tasks
        )
    }
}