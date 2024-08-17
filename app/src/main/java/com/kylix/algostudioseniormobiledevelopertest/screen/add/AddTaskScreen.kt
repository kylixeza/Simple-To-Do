package com.kylix.algostudioseniormobiledevelopertest.screen.add

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.R
import com.kylix.algostudioseniormobiledevelopertest.screen.add.components.DateModalBottomSheet
import com.kylix.algostudioseniormobiledevelopertest.screen.add.components.TextAndHighlightedTextField
import com.kylix.algostudioseniormobiledevelopertest.screen.add.components.TextAndTextFieldSection
import com.kylix.algostudioseniormobiledevelopertest.screen.add.components.TimeModalBottomSheet
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DarkGray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.White
import com.kylix.algostudioseniormobiledevelopertest.utils.convertToTextFieldDate
import com.kylix.algostudioseniormobiledevelopertest.utils.getCurrentDate
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel = koinViewModel(),
    onBack: () -> Unit = { }
) {
    val state by viewModel.addTaskState.collectAsState()

    val dateBottomSheetState = rememberModalBottomSheetState()
    val timeBottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Add New Task",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.Default
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onBack
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = DeepBlue,
                            )
                        }
                    }
                )
                Divider(
                    color = Color(0xFFE0E0E0),
                    thickness = 1.dp
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            TextAndHighlightedTextField(
                text = "Title",
                value = state.title,
                placeholder = "Title Task",
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            ) { value, containHighlightedWord ->
                viewModel.onTitleChanged(value)
                if (containHighlightedWord) viewModel.onDateChanged(date = getCurrentDate().convertToTextFieldDate())
            }

            Spacer(modifier = Modifier.height(12.dp))
            TextAndTextFieldSection(
                text = "Description",
                value = state.description,
                placeholder = "Description Task",
                minSize = 64.dp,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            ) { value ->
                viewModel.onDescriptionChanged(value)
            }

            Spacer(modifier = Modifier.height(12.dp))
            TextAndTextFieldSection(
                text = "Date",
                value = if (state.time.isEmpty()) state.date else "${state.date} ${state.time}",
                placeholder = "Select Date",
                leadingIcon = {
                    Icon(Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF989CA6)
                    )
                },
                needClick = true,
                onClicked = {
                    viewModel.onShowDateBottomSheet()
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    tint = Color(0xFF989CA6),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Time",
                    fontSize = 16.sp,
                    color = Black,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = state.time.isNotEmpty() || state.showTimeBottomSheet,
                    onCheckedChange = {
                        if (state.time.isEmpty() && it) {
                            viewModel.onShowTimeBottomSheet()
                        } else {
                            viewModel.onTimeChanged("")
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = White,
                        checkedTrackColor = DeepBlue,
                        uncheckedThumbColor = White,
                        uncheckedTrackColor = DarkGray
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    onClick = { onBack() },
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, DeepBlue),
                ) {
                    Text(
                        text = "Cancel",
                        color = DeepBlue
                    )
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    onClick = {
                        viewModel.insertTask()
                        onBack()
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepBlue
                    ),
                    enabled = state.title.isNotEmpty() && state.description.isNotEmpty() && state.date.isNotEmpty()
                ) {
                    Text("Save")
                }
            }
        }

        if (state.showDateBottomSheet) {
            DateModalBottomSheet(
                state = dateBottomSheetState,
                onDismissRequest = { viewModel.onHideTimeBottomSheet() },
                onCancel = { viewModel.onHideDateBottomSheet() },
                onSave = {
                    viewModel.onDateChanged(it)
                    viewModel.onHideDateBottomSheet()
                }
            )
        }

        if (state.showTimeBottomSheet) {
            TimeModalBottomSheet(
                state = timeBottomSheetState,
                onDismissRequest = { viewModel.onHideTimeBottomSheet() },
                onCancel = { viewModel.onHideTimeBottomSheet() },
                onSave = {
                    viewModel.onTimeChanged(it)
                    viewModel.onHideTimeBottomSheet()
                }
            )
        }

    }
}