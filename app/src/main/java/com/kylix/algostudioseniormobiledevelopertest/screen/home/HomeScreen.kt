package com.kylix.algostudioseniormobiledevelopertest.screen.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.screen.home.components.DeleteItemsCard
import com.kylix.algostudioseniormobiledevelopertest.screen.home.components.DeleteModalBottomSheet
import com.kylix.algostudioseniormobiledevelopertest.screen.home.components.TaskHeader
import com.kylix.algostudioseniormobiledevelopertest.screen.home.components.TaskItem
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightGray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.White
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onAddTask: () -> Unit = {}
) {
    val state = viewModel.toDoState.collectAsState()

    val deleteModal = rememberModalBottomSheetState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "To Do List",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default
                )

                Button(
                    onClick = { onAddTask() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepBlue
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            tint = White,
                            contentDescription = "Add Icon",
                        )
                        Text(
                            text = "New Task"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = LightGray)
        ) {
            LazyColumn {
                state.value.task.forEach { (date, tasks) ->
                    stickyHeader {
                        TaskHeader(date = date)
                    }
                    itemsIndexed(
                        tasks,
                        key = { _, task -> task.id }
                    ) { index, task ->
                        TaskItem(
                            task = task,
                            onChecked = { isChecked ->
                                viewModel.onTaskChecked(task.id, isChecked)
                            },
                            onHoldPressed = {
                                viewModel.onShowBottomModalForSingleTask(it)
                            }
                        )
                        if (index == tasks.size - 1) {
                            AnimatedVisibility(
                                visible = tasks.any { it.isSelected },
                                enter = scaleIn(),
                                exit = scaleOut()
                            ) {
                                DeleteItemsCard(
                                    onDelete = {
                                        viewModel.onShowBottomModalForMultipleTask(date)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (state.value.showDeleteModal) {
        DeleteModalBottomSheet(
            state = deleteModal,
            onDismissRequest = { viewModel.onDismissBottomModal() },
            onCancel = { viewModel.onDismissBottomModal() },
            onDelete = {
                Log.d("HomeScree", "SpecificId: ${state.value.specificTaskIdWantToDelete}")
                if (state.value.deleteForMultipleItems) {
                    viewModel.deleteItemsByDay()
                } else {
                    viewModel.deleteSpecificTask()
                }
                viewModel.onDismissBottomModal()
            }
        )
    }
}