package com.ralphmarondev.taskify.features.updatetask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ralphmarondev.taskify.core.components.DateTimePicker
import com.ralphmarondev.taskify.core.components.DateTimeTextField
import com.ralphmarondev.taskify.core.components.NormalTextField
import com.ralphmarondev.taskify.core.model.Task
import com.ralphmarondev.taskify.features.updatetask.viewmodel.UpdateTaskViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateTaskScreen(
    navController: NavHostController,
    task: Task,
    viewModel: UpdateTaskViewModel = viewModel()
) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val startTime by viewModel.startTime.collectAsState()
    val endTime by viewModel.endTime.collectAsState()

    var startTimeDialog by remember { mutableStateOf(false) }
    var endTimeDialog by remember { mutableStateOf(false) }
    var deleteTask by remember { mutableStateOf(false) }

    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.setTitle(task.title)
        viewModel.setDescription(task.description)
        viewModel.setStartTime(task.startTime)
        viewModel.setEndTime(task.endTime)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Update Task",
                        fontFamily = FontFamily.Monospace
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { deleteTask = !deleteTask }) {
                        Icon(
                            imageVector = Icons.Outlined.DeleteForever,
                            contentDescription = ""
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomAppBar(containerColor = Color.Transparent) {
                Button(
                    onClick = {
                        if (
                            title.isNotEmpty() &&
                            description.isNotEmpty() &&
                            startTime.isNotEmpty() &&
                            endTime.isNotEmpty()
                        ) {
                            val updatedTask = Task(
                                id = task.id,
                                title = title,
                                description = description,
                                startTime = startTime,
                                endTime = endTime,
                                createDate = task.createDate
                            )
                            try {
                                viewModel.updateTask(updatedTask)
                                scope.launch {
                                    snackbarState.showSnackbar(
                                        message = "Task updated successfully."
                                    )
                                    delay(1)
                                    navController.popBackStack()
                                }
                            } catch (ex: Exception) {
                                scope.launch {
                                    snackbarState.showSnackbar(
                                        message = "Failed: ${ex.message}"
                                    )
                                }
                            }
                        } else {
                            scope.launch {
                                snackbarState.showSnackbar(
                                    message = "Please fill in all fields."
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "UPDATE TASK",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 5.dp),
        ) {
            NormalTextField(
                value = title,
                onValueChange = { viewModel.setTitle(it) },
                label = "Title",
                maxLines = 1
            )
            NormalTextField(
                value = description,
                onValueChange = { viewModel.setDescription(it) },
                label = "Description",
                maxLines = 4,
                minLines = 4
            )
            DateTimeTextField(
                value = startTime,
                label = "Start Time",
                onTrailingIconClick = { startTimeDialog = !startTimeDialog }
            )
            DateTimeTextField(
                value = endTime,
                label = "End Time",
                onTrailingIconClick = { endTimeDialog = !endTimeDialog }
            )
        }

        if (startTimeDialog) {
            DateTimePicker(
                onDismissRequest = { startTimeDialog = !startTimeDialog },
                onDateTimeSelected = { startTime ->
                    viewModel.setStartTime(startTime)
                    startTimeDialog = !startTimeDialog
                }
            )
        }
        if (endTimeDialog) {
            DateTimePicker(
                onDismissRequest = { endTimeDialog = !endTimeDialog },
                onDateTimeSelected = { endTime ->
                    viewModel.setEndTime(endTime)
                    endTimeDialog = !endTimeDialog
                }
            )
        }

        if (deleteTask) {
            ModalBottomSheet(
                onDismissRequest = { deleteTask = !deleteTask },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 200.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 200.dp)
                        .padding(15.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Are you sure you want to permanently delete this task?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W500,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ElevatedButton(onClick = { deleteTask = !deleteTask }) {
                            Text(
                                text = "CANCEL",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp
                            )
                        }
                        Button(
                            onClick = {
                                viewModel.deleteTask(task.id)
                                deleteTask = !deleteTask
                                navController.popBackStack()
                            }
                        ) {
                            Text(
                                text = "CONFIRM",
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(900.dp))
                }
            }
        }
    }
}