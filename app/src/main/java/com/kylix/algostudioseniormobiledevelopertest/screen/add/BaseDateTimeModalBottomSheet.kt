package com.kylix.algostudioseniormobiledevelopertest.screen.add

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.commandiron.wheel_picker_compose.WheelDatePicker
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepBlue
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseDateTimeModalBottomSheet(
    state: SheetState,
    modifier: Modifier = Modifier,
    title: String = "",
    onDismissRequest: () -> Unit = {},
    onCancel : () -> Unit = {},
    onSave: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = state,
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier,
        ) {

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )

            content()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    onClick = {
                        coroutineScope.launch {
                            state.hide()
                        }.invokeOnCompletion {
                            if (!state.isVisible) onCancel()
                        }
                    },
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
                        coroutineScope.launch {
                            state.hide()
                        }.invokeOnCompletion {
                            if (!state.isVisible) onSave()
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepBlue
                    ),
                ) {
                    Text("Save")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateModalBottomSheet(
    state: SheetState,
    onDismissRequest: () -> Unit = {},
    onCancel : () -> Unit = {},
    onSave: (String) -> Unit = {},
) {
    var date by remember { mutableStateOf("") }

    BaseDateTimeModalBottomSheet(
        state = state,
        title = "Set Date",
        onDismissRequest = onDismissRequest,
        onCancel = onCancel,
        onSave = {
            onSave(date)
        }
    ) {
        WheelDatePicker(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textColor = DeepBlue,
            onSnappedDate = {
                date = "${it.dayOfMonth}/${it.monthValue}/${it.year}"
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeModalBottomSheet(
    state: SheetState,
    onDismissRequest: () -> Unit = {},
    onCancel : () -> Unit = {},
    onSave: (String) -> Unit = {},
) {
    var time by remember { mutableStateOf("") }

    BaseDateTimeModalBottomSheet(
        state = state,
        title = "Set Time",
        onDismissRequest = onDismissRequest,
        onCancel = onCancel,
        onSave = { onSave(time) }
    ) {
        WheelTimePicker(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textColor = DeepBlue,
            onSnappedTime = {
                time = "${it.hour}:${it.minute}"
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DateTimeModalBottomSheetPreview() {
    BaseDateTimeModalBottomSheet(
        state = rememberModalBottomSheetState(),
        title = "Add New Task"
    ) {

    }
}