package com.kylix.algostudioseniormobiledevelopertest.screen.add.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Black
import com.kylix.algostudioseniormobiledevelopertest.ui.theme.Lavender

@Composable
fun TextAndHighlightedTextField(
    text: String = "",
    value: String = "",
    placeholder: String = "",
    minSize: Dp = Dp.Unspecified,
    maxSize: Dp = Dp.Unspecified,
    needClick: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onClicked: () -> Unit = {  },
    onValueChange: (String, Boolean) -> Unit = {_, _ ->},
) {
    Text(
        text = text,
        fontSize = 16.sp,
        color = Black,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(modifier = Modifier.height(8.dp))
    HighlightedTextField(
        value = value,
        placeholder = placeholder,
        minSize = minSize,
        maxSize = maxSize,
        keyboardOptions = keyboardOptions,
        onClicked = onClicked,
        onValueChange = { value, containHighlightedWord ->
            onValueChange(value, containHighlightedWord)
        }
    )
}

@Composable
fun HighlightedTextField(
    value: String = "",
    placeholder: String = "",
    minSize: Dp = Dp.Unspecified,
    maxSize: Dp = Dp.Unspecified,
    keyboardOptions: KeyboardOptions,
    onClicked: () -> Unit = {  },
    onValueChange: (String, Boolean) -> Unit = {_, _ -> }
) {
    val highlightedWord = "today"

    BasicTextField(
        value = value,
        onValueChange = {
            val containsHighlightedWord = it.contains(highlightedWord)
            onValueChange(it,containsHighlightedWord)
        },
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
        keyboardOptions = keyboardOptions
    ) { innerTextField ->

        val annotatedString = buildAnnotatedString {
            if (value.contains(highlightedWord)) {
                val wordBeforeHighlight =
                    value.substring(0, value.indexOf(highlightedWord))
                append(wordBeforeHighlight)
                withStyle(style = SpanStyle(background = Lavender, color = Black)) {
                    append(highlightedWord)
                }
                val wordAfterHighlight =
                    value.substring(value.indexOf(highlightedWord) + highlightedWord.length)
                append(wordAfterHighlight)
            } else {
                append(value)
            }

        }

        Row(
            verticalAlignment = Alignment.Top,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            innerTextField()
        }

        Row(
            verticalAlignment = Alignment.Top,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                textAlign = TextAlign.Start,
                text = annotatedString,
                style = TextStyle(color = Color.Black, fontSize = 16.sp),
            )
        }
        Row(
            verticalAlignment = Alignment.Top,
        ) {
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
}