package com.kylix.algostudioseniormobiledevelopertest.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.algostudioseniormobiledevelopertest.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TaskRepository
): ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val toDoState = _homeState.asStateFlow()

    init {
        getTasks()
    }

    private fun getTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { tasks ->
                _homeState.value = _homeState.value.copy(
                    task = tasks
                )
            }
        }
    }

    fun onTaskChecked(id: Int, isChecked: Boolean) {
        val tasks = _homeState.value.task.mapValues { (date, tasks) ->
            tasks.map { task ->
                if (task.id == id) {
                    task.copy(isSelected = isChecked)
                } else {
                    task
                }
            }
        }
        _homeState.value = _homeState.value.copy(
            task = tasks
        )
    }

    fun deleteItemsByDay(date: String) {
        viewModelScope.launch {
            val tasksOnThatDate = _homeState.value.task[date]?.filter { it.isSelected } ?: emptyList()
            tasksOnThatDate.forEach { task ->
                repository.deleteTask(task.id)
            }
        }
    }

    fun deleteSpecificTask(id: Int) {
        viewModelScope.launch {
            repository.deleteTask(id)
        }
    }
}