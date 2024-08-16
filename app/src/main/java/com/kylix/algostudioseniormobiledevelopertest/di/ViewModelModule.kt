package com.kylix.algostudioseniormobiledevelopertest.di

import com.kylix.algostudioseniormobiledevelopertest.screen.home.ToDoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ToDoViewModel() }
}