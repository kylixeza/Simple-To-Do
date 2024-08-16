package com.kylix.algostudioseniormobiledevelopertest.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kylix.algostudioseniormobiledevelopertest.data.repository.TaskRepository
import com.kylix.algostudioseniormobiledevelopertest.utils.convertToFormattedDate
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val repository: TaskRepository
): ViewModel() {

    fun insertTask(
        title: String,
        description: String,
        date: String,
        time: String?
    ) {
        viewModelScope.launch {
            repository.insertTask(title, description, date.convertToFormattedDate(), time)
        }
    }
}