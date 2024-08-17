package com.kylix.algostudioseniormobiledevelopertest.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.LightOrange
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.White

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