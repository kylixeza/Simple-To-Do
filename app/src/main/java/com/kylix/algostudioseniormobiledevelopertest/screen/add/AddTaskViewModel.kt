package com.kylix.algostudioseniormobiledevelopertest.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.algostudioseniormobiledevelopertest.data.repository.TaskRepository
import com.kylix.algostudioseniormobiledevelopertest.utils.convertToFormattedDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val repository: TaskRepository
): ViewModel() {

    private val _addTaskState = MutableStateFlow(AddTaskState())
    val addTaskState = _addTaskState.asStateFlow()

    fun insertTask() {
        viewModelScope.launch {
            val (title, description, date, time) = addTaskState.value
            repository.insertTask(title, description, date.convertToFormattedDate(), time.ifEmpty { null })
        }
    }

    fun onTitleChanged(title: String) {
        _addTaskState.value = _addTaskState.value.copy(title = title)
    }

    fun onDescriptionChanged(description: String) {
        _addTaskState.value = _addTaskState.value.copy(description = description)
    }

    fun onDateChanged(date: String) {
        _addTaskState.value = _addTaskState.value.copy(date = date)
    }

    fun onTimeChanged(time: String) {
        _addTaskState.value = _addTaskState.value.copy(time = time)
    }

    fun onShowDateBottomSheet() {
        _addTaskState.value = _addTaskState.value.copy(showDateBottomSheet = true)
    }

    fun onHideDateBottomSheet() {
        _addTaskState.value = _addTaskState.value.copy(showDateBottomSheet = false)
    }

    fun onShowTimeBottomSheet() {
        _addTaskState.value = _addTaskState.value.copy(showTimeBottomSheet = true)
    }

    fun onHideTimeBottomSheet() {
        _addTaskState.value = _addTaskState.value.copy(showTimeBottomSheet = false)
    }
}