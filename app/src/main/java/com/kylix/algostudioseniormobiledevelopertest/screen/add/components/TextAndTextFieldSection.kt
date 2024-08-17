package com.kylix.algostudioseniormobiledevelopertest.screen.add.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black

@Composable
fun TextAndTextFieldSection(
    text: String = "",
    value: String = "",
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    minSize: Dp = Dp.Unspecified,
    maxSize: Dp = Dp.Unspecified,
    needClick: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
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
            color = Color.Black,
            fontSize = 16.sp
        ),
        readOnly = needClick,
        keyboardOptions = keyboardOptions,
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