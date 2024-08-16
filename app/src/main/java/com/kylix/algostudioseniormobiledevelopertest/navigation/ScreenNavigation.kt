package com.kylix.algostudioseniormobiledevelopertest.navigation

sealed class ScreenNavigation(val route: String) {
    data object Home: ScreenNavigation("/home")
    data object AddTask: ScreenNavigation("/add_task")
}