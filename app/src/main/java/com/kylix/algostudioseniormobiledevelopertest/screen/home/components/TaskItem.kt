package com.kylix.algostudioseniormobiledevelopertest.screen.home.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.model.Task
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.DeepBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightBlue
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.White
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TaskItem(
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

    Surface(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 6.dp)
            .fillMaxWidth()
            .graphicsLayer(rotationZ = wiggleRotation)
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .animateItemPlacement()
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    onHoldPressed(task.id)
                },
            ),
        color = White
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
                    text = task.time.ifEmpty { "-" },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LightBlue
                )
            }
        }
    }
}