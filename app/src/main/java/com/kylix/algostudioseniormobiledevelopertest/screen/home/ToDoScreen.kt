package com.kylix.algostudioseniormobiledevelopertest.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kylix.algostudioseniormobiledevelopertest.model.Task
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Gray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightGray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightOrange
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.White
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel = koinViewModel(),
    onAddTask: () -> Unit = {}
) {
    val state = viewModel.toDoState.collectAsState()

    val deleteModal = rememberModalBottomSheetState()
    var currentDateWantToDelete by remember { mutableStateOf("") }
    var specificTaskIdWantToDelete by remember { mutableIntStateOf(-1) }
    var showDeleteModal by remember { mutableStateOf(false) }

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
                                specificTaskIdWantToDelete = it
                                showDeleteModal = true
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
                                        currentDateWantToDelete = date
                                        showDeleteModal = true
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDeleteModal) {
        DeleteModalBottomSheet(
            state = deleteModal,
            onDismissRequest = { showDeleteModal = false },
            onCancel = { showDeleteModal = false },
            onDelete = {
                if (specificTaskIdWantToDelete == -1) {
                    viewModel.deleteItemsByDay(currentDateWantToDelete)
                } else {
                    viewModel.deleteSpecificTask(specificTaskIdWantToDelete)
                }
                showDeleteModal = false
            }
        )
    }
}

@Composable
fun TaskHeader(
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray)
            .padding(16.dp),
    ) {
        Text(
            text = date,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Black
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    onChecked: (Boolean) -> Unit = {},
    onHoldPressed: (Int) -> Unit = {}
) {
    var playAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(task.isSelected) {
        if (task.isSelected) {
            playAnimation = true
            delay(1000)
            playAnimation = false
        }
    }

    val wiggleRotation by animateFloatAsState(
        targetValue = if (playAnimation) 10f else 0f,
        animationSpec = keyframes {
            durationMillis = 1000
            0f at 0
            -10f at 100
            10f at 200
            -10f at 300
            10f at 400
            0f at 500
        },
        label = "wiggle"
    )

    Card(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    onHoldPressed(task.id)
                },
            )
            .graphicsLayer(rotationZ = wiggleRotation),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.TopCenter
            ) {
                Checkbox(
                    modifier = Modifier.fillMaxSize(),
                    checked = task.isSelected,
                    onCheckedChange = {
                        if (it) playAnimation = true
                        onChecked(it)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = DeepBlue,
                        uncheckedColor = LightBlue,
                        checkmarkColor = White
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = task.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Black
                )
                Text(
                    text = task.time,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBlue
                )
            }
        }
    }
}

@Composable
fun DeleteItemsCard(
    onDelete: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp)
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .shadow(elevation = 2.dp, RoundedCornerShape(12.dp))
                .clickable { onDelete() },
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        top = 24.dp, bottom = 24.dp, start = 8.dp, end = 42.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = "Delete Task",
                    tint = LightOrange
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Delete task",
                    color = LightOrange,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            }
        }
    }
}