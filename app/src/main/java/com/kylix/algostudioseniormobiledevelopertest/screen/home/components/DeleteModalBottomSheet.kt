package com.kylix.algostudioseniormobiledevelopertest.screen.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteModalBottomSheet(
    state: SheetState,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onCancel : () -> Unit = {},
    onDelete: () -> Unit = {},
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
                text = "Delete",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Are you sure to delete?",
                fontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(2.dp))
            Divider(
                color = Color(0xFFE0E0E0),
                thickness = 1.dp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 42.dp, start = 16.dp, end = 16.dp),
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
                    border = BorderStroke(1.dp, DeepOrange),
                ) {
                    Text(
                        text = "Cancel",
                        color = DeepOrange
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
                            if (!state.isVisible) onDelete()
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DeepOrange
                    ),
                ) {
                    Text("Delete")
                }
            }
        }
    }
}