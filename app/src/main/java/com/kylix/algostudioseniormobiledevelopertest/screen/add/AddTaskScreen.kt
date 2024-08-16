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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.R
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DarkGray
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onBack: () -> Unit = { }
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    val dateBottomSheetState = rememberModalBottomSheetState()
    var showDateBottomSheet by remember { mutableStateOf(false) }

    val timeBottomSheetState = rememberModalBottomSheetState()
    var showTimeBottomSheet by remember { mutableStateOf(false) }

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
            TextAndTextFieldSection(
                text = "Title",
                value = title,
                placeholder = "Title Task"
            ) { title = it }
            Spacer(modifier = Modifier.height(12.dp))
            TextAndTextFieldSection(
                text = "Description",
                value = description,
                placeholder = "Description Task",
                minSize = 64.dp
            ) { description = it }
            Spacer(modifier = Modifier.height(12.dp))
            TextAndTextFieldSection(
                text = "Date",
                value = if (time.isEmpty()) date else "$date $time",
                placeholder = "Select Date",
                onValueChange = { date = it },
                leadingIcon = {
                    Icon(Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = Color(0xFF989CA6)
                    )
                },
                needClick = true,
                onClicked = {
                    showDateBottomSheet = true
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
                    checked = time.isNotEmpty() || showTimeBottomSheet,
                    onCheckedChange = {
                        if (time.isEmpty() && it) {
                            showTimeBottomSheet = true
                        } else {
                            time = ""
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

                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepBlue
                    ),
                    enabled = title.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty()
                ) {
                    Text("Save")
                }
            }
        }

        if (showDateBottomSheet) {
            DateModalBottomSheet(
                state = dateBottomSheetState,
                onDismissRequest = { showDateBottomSheet = false },
                onCancel = { showDateBottomSheet = false },
                onSave = {
                    date = it
                    showDateBottomSheet = false
                }
            )
        }

        if (showTimeBottomSheet) {
            TimeModalBottomSheet(
                state = timeBottomSheetState,
                onDismissRequest = { showTimeBottomSheet = false },
                onCancel = { showTimeBottomSheet = false },
                onSave = {
                    time = it
                    showTimeBottomSheet = false
                }
            )
        }

    }
}

@Composable
fun TextAndTextFieldSection(
    text: String = "",
    value: String = "",
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    minSize: Dp = Dp.Unspecified,
    maxSize: Dp = Dp.Unspecified,
    needClick: Boolean = false,
    onClicked: () -> Unit = {  },
    onValueChange: (String) -> Unit = {},
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Black,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFF4F6F6))
            .padding(12.dp)
            .heightIn(min = minSize, max = maxSize),
        textStyle = TextStyle(
            color = Color.Gray,
            fontSize = 16.sp
        ),
        readOnly = needClick,
        decorationBox = { innerTextField ->
            val rows: @Composable () -> Unit  = {
                Row(
                    verticalAlignment = Alignment.Top,
                ) {
                    leadingIcon?.invoke()
                    Spacer(modifier = Modifier.width(8.dp))
                    innerTextField()
                }
                Row(
                    verticalAlignment = Alignment.Top,
                ) {
                    leadingIcon?.invoke()
                    Spacer(modifier = Modifier.width(8.dp))
                    if (value.isEmpty()) {
                        Text(
                            textAlign = TextAlign.Start,
                            text = placeholder,
                            style = TextStyle(color = Color.Gray, fontSize = 16.sp),
                        )
                    }
                }
            }
            if (needClick) {
                Box(
                    modifier = Modifier.clickable { onClicked() },
                ) { rows() }
            } else {
                rows()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    AddTaskScreen()
}