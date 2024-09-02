package com.ralphmarondev.taskify.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(
    onDismissRequest: () -> Unit,
    onDateTimeSelected: (String) -> Unit
) {
    val calendar = remember { Calendar.getInstance() }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    val timePickerState = rememberTimePickerState()

    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 200.dp)
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                TimePicker(
                    state = timePickerState,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ElevatedButton(onClick = onDismissRequest) {
                        Text(
                            text = "CANCEL",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                    Button(
                        onClick = {
                            val selectedDate = datePickerState.selectedDateMillis
                            val selectedHour = timePickerState.hour
                            val selectedMinute = timePickerState.minute

                            if (selectedDate != null) {
                                calendar.timeInMillis = selectedDate
                                calendar.set(Calendar.HOUR_OF_DAY, selectedHour)
                                calendar.set(Calendar.MINUTE, selectedMinute)
                                val dateTimeFormat =
                                    SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault())
                                val formattedDate = dateTimeFormat.format(calendar.time)
                                onDateTimeSelected(formattedDate)
                            }
                        }
                    ) {
                        Text(
                            text = "SAVE",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
            }
        }
    }
}