package com.kylix.algostudioseniormobiledevelopertest.di

import com.kylix.algostudioseniormobiledevelopertest.screen.add.AddTaskViewModel
import com.kylix.algostudioseniormobiledevelopertest.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { AddTaskViewModel(get()) }
}