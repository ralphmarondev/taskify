package com.ralphmarondev.taskify.core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun DateTimeTextField(
    value: String,
    label: String,
    onTrailingIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = { },
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
        trailingIcon = {
            IconButton(onClick = onTrailingIconClick) {
                Icon(
                    imageVector = Icons.Outlined.EditCalendar,
                    contentDescription = ""
                )
            }
        },
        readOnly = true
    )
}

@Composable
fun MobileDateTimeTextField(
    value: String,
    label: String,
    onTrailingIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    maxLines: Int = 1
) {
    OutlinedTextField(
        value = value,
        onValueChange = { },
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
        trailingIcon = {
            IconButton(onClick = onTrailingIconClick) {
                Icon(
                    imageVector = Icons.Outlined.EditCalendar,
                    contentDescription = ""
                )
            }
        },
        readOnly = true
    )
}


@Composable
fun TabletDateTimeTextField(
    value: String,
    label: String,
    onTrailingIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = { /* No-op */ },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 100.dp,
                vertical = 10.dp
            ),
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
        trailingIcon = {
            // Assume you have a trailing icon implementation
            // Adjust the size if needed
            IconButton(onClick = { onTrailingIconClick() }) {
                Icon(imageVector = Icons.Outlined.CalendarToday, contentDescription = null)
            }
        }
    )
}
