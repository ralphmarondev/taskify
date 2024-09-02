package com.ralphmarondev.taskify.features.newtask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ralphmarondev.taskify.core.components.DateTimePicker
import com.ralphmarondev.taskify.core.components.DateTimeTextField
import com.ralphmarondev.taskify.core.components.MobileDateTimeTextField
import com.ralphmarondev.taskify.core.components.MobileNormalTextField
import com.ralphmarondev.taskify.core.components.NormalTextField
import com.ralphmarondev.taskify.core.components.TabletDateTimeTextField
import com.ralphmarondev.taskify.core.components.TabletNormalTextField
import com.ralphmarondev.taskify.core.model.Task
import com.ralphmarondev.taskify.features.newtask.viewmodel.NewTaskViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskScreen(
    navController: NavHostController,
    viewModel: NewTaskViewModel = viewModel()
) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val startTime by viewModel.startTime.collectAsState()
    val endTime by viewModel.endTime.collectAsState()

    var startTimeDialog by remember { mutableStateOf(false) }
    var endTimeDialog by remember { mutableStateOf(false) }

    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val isTablet = LocalConfiguration.current.screenWidthDp >= 600

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Task",
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
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
                            val task = Task(
                                title = title,
                                description = description,
                                startTime = startTime,
                                endTime = endTime
                            )
                            try {
                                viewModel.createNewTask(task)
                                scope.launch {
                                    snackbarState.showSnackbar(
                                        message = "Task saved successfully."
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
                        .padding(horizontal = if (isTablet) 100.dp else 15.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "CREATE NEW TASK",
                        fontSize = if(isTablet) 22.sp else 16.sp,
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
            if (isTablet) {
                TabletNormalTextField(
                    value = title,
                    onValueChange = { viewModel.setTitle(it) },
                    label = "Title",
                    maxLines = 1
                )
                TabletNormalTextField(
                    value = description,
                    onValueChange = { viewModel.setDescription(it) },
                    label = "Description",
                    maxLines = 4,
                    minLines = 4
                )
                TabletDateTimeTextField(
                    value = startTime,
                    label = "Start Time",
                    onTrailingIconClick = { startTimeDialog = !startTimeDialog }
                )
                TabletDateTimeTextField(
                    value = endTime,
                    label = "End Time",
                    onTrailingIconClick = { endTimeDialog = !endTimeDialog }
                )
            } else {
                MobileNormalTextField(
                    value = title,
                    onValueChange = { viewModel.setTitle(it) },
                    label = "Title",
                    maxLines = 1
                )
                MobileNormalTextField(
                    value = description,
                    onValueChange = { viewModel.setDescription(it) },
                    label = "Description",
                    maxLines = 4,
                    minLines = 4
                )
                MobileDateTimeTextField(
                    value = startTime,
                    label = "Start Time",
                    onTrailingIconClick = { startTimeDialog = !startTimeDialog }
                )
                MobileDateTimeTextField(
                    value = endTime,
                    label = "End Time",
                    onTrailingIconClick = { endTimeDialog = !endTimeDialog }
                )
            }
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
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskScreenTablet(
    navController: NavHostController,
    viewModel: NewTaskViewModel = viewModel()
) {
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val startTime by viewModel.startTime.collectAsState()
    val endTime by viewModel.endTime.collectAsState()

    var startTimeDialog by remember { mutableStateOf(false) }
    var endTimeDialog by remember { mutableStateOf(false) }

    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New Task", fontFamily = FontFamily.Monospace)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        content = { innerPadding ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                NavigationRail(
                    modifier = Modifier.fillMaxHeight(),
                    header = {
                        ExtendedFloatingActionButton(
                            onClick = {
                                if (
                                    title.isNotEmpty() &&
                                    description.isNotEmpty() &&
                                    startTime.isNotEmpty() &&
                                    endTime.isNotEmpty()
                                ) {
                                    val task = Task(
                                        title = title,
                                        description = description,
                                        startTime = startTime,
                                        endTime = endTime
                                    )
                                    try {
                                        viewModel.createNewTask(task)
                                        scope.launch {
                                            snackbarState.showSnackbar(
                                                message = "Task saved successfully."
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
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PostAdd,
                                contentDescription = "Create Task"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "CREATE")
                        }
                    },
                    content = {
                        // Add more navigation items if needed
                    }
                )

                // Main content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
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
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarState) }
    )

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
}
