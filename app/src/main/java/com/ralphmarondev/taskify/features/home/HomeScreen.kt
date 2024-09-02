package com.ralphmarondev.taskify.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PostAdd
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ralphmarondev.taskify.core.navigation.Screens
import com.ralphmarondev.taskify.features.home.components.TaskCardMobile
import com.ralphmarondev.taskify.features.home.components.TaskCardTablet
import com.ralphmarondev.taskify.features.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    // Use LocalConfiguration to determine screen size
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600 // Example threshold for tablets

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isTablet) {
            HomeScreenTablet(
                navController = navController,
                viewModel = viewModel
            )
        } else {
            HomeScreenMobile(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenMobile(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    val tasks by viewModel.tasks.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Taskify", fontFamily = FontFamily.Monospace)
                },
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.Notification) }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifications"
                        )
                    }
                    IconButton(onClick = { navController.navigate(Screens.Settings) }) {
                        Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Screens.NewTask) }
            ) {
                Icon(imageVector = Icons.Outlined.PostAdd, contentDescription = "New Task")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "NEW")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            tasks?.let {
                items(it.size) { index ->
                    TaskCardMobile(
                        task = tasks!![index],
                        navController = navController,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(100.dp)) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTablet(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    val tasks by viewModel.tasks.observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Taskify", fontFamily = FontFamily.Monospace)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
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
                        FloatingActionButton(
                            onClick = { navController.navigate(Screens.NewTask) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = ""
                            )
                        }
                    },
                    content = {
                        IconButton(onClick = { navController.navigate(Screens.Notification) }) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = "Notifications"
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        IconButton(onClick = { navController.navigate(Screens.Settings) }) {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = "Settings"
                            )
                        }
                    }
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    tasks?.let {
                        items(it.size) { index ->
                            TaskCardTablet(
                                task = tasks!![index],
                                navController = navController,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                    item { Spacer(modifier = Modifier.height(100.dp)) }
                }
            }
        }
    )
}