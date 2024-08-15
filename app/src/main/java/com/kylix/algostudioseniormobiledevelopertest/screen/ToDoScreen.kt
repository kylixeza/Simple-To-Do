package com.kylix.algostudioseniormobiledevelopertest.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.model.Task
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DarkGray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Gray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightGray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.White
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ToDoScreen(
    viewModel: ToDoViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state = viewModel.toDoState.collectAsState()

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
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.Default
                )

                Button(
                    onClick = { },
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
                    items(tasks) { task ->
                        TaskItem(task = task)
                    }
                }
            }
        }
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

@Composable
fun TaskItem(
    task: Task,
    modifier: Modifier = Modifier
) {

    var isChecked by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        color = White,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Checkbox(
                    modifier = Modifier.fillMaxHeight(),
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = DeepBlue,
                        uncheckedColor = DeepBlue,
                        checkmarkColor = White
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = task.title,
                    fontSize = 16.sp,
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

@Preview(showBackground = true)
@Composable
fun TaskItem() {
    TaskItem(
        task = Task(
            id = 1,
            title = "Task 1",
            description = "Description 1",
            date = "2022-08-15",
            time = "10:00"
        )
    )
}