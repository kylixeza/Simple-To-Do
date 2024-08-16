package com.kylix.algostudioseniormobiledevelopertest.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.algostudioseniormobiledevelopertest.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoViewModel(
    private val repository: TaskRepository
): ViewModel() {

    private val _toDoState = MutableStateFlow(ToDoState())
    val toDoState = _toDoState.asStateFlow()

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { tasks ->
                _toDoState.value = _toDoState.value.copy(
                    task = tasks
                )
            }
        }
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

    }
}