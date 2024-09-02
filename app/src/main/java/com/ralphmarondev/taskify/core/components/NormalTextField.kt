package com.ralphmarondev.taskify.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NormalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.secondary
        ),
        label = {
            Text(
                text = label, fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.secondary,
                overflow = TextOverflow.Ellipsis
            )
        },
        maxLines = maxLines,
        minLines = minLines
    )
}

@Composable
fun MobileNormalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.secondary
        ),
        label = {
            Text(
                text = label, fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.secondary,
                overflow = TextOverflow.Ellipsis
            )
        },
        maxLines = maxLines,
        minLines = minLines
    )
}

@Composable
fun TabletNormalTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 100.dp, vertical = 10.dp),
        textStyle = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.W500,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.secondary
        ),
        label = {
            Text(
                text = label,
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Monospace,
                color = MaterialTheme.colorScheme.secondary,
                overflow = TextOverflow.Ellipsis
            )
        },
        maxLines = maxLines,
        minLines = minLines
    )
}
