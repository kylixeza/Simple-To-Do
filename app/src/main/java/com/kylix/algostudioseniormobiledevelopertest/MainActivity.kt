package com.kylix.algostudioseniormobiledevelopertest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.kylix.algostudioseniormobiledevelopertest.navigation.NavGraph
import com.kylix.algostudioseniormobiledevelopertest.screen.add.AddTaskScreen
import com.kylix.algostudioseniormobiledevelopertest.screen.home.ToDoScreen
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                NavGraph()
            }
        }
    }
}