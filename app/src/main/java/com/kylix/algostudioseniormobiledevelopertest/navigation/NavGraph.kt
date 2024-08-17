package com.kylix.algostudioseniormobiledevelopertest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kylix.algostudioseniormobiledevelopertest.screen.add.AddTaskScreen
import com.kylix.algostudioseniormobiledevelopertest.screen.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.Home.route
    ) {
        composable(ScreenNavigation.Home.route) {
            HomeScreen(
                onAddTask = {
                    navController.navigate(ScreenNavigation.AddTask.route)
                }
            )
        }
        composable(ScreenNavigation.AddTask.route) {
            AddTaskScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}