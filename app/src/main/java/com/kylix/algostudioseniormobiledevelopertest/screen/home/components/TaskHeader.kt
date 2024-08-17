package com.kylix.algostudioseniormobiledevelopertest.screen.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Gray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TaskHeader(
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Gray)
            .padding(16.dp)
            .animateItemPlacement(),
    ) {
        Text(
            text = date,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Black
        )
    }
}