package com.ralphmarondev.taskify.features.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ralphmarondev.taskify.ui.theme.TaskifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    navController: NavHostController
) {
    var newItemsToBottom by remember { mutableStateOf(true) }
    var isDarkMode by remember { mutableStateOf(false) }
    var showNotification by remember { mutableStateOf(false) }

    TaskifyTheme(darkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Settings",
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
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                item { Spacer(modifier = Modifier.height(5.dp)) }
                item {
                    val listOfDisplayOptions = listOf(
                        DisplayOptions(
                            title = "Add new items to bottom",
                            isChecked = newItemsToBottom,
                            onCheckedChange = { newItemsToBottom = !newItemsToBottom }
                        ),
                        DisplayOptions(
                            title = "Dark Mode",
                            isChecked = isDarkMode,
                            onCheckedChange = { isDarkMode = !isDarkMode }
                        )
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Display Options",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.primary
                        )

                        listOfDisplayOptions.forEachIndexed { index, displayOptions ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = displayOptions.title,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W500,
                                    color = MaterialTheme.colorScheme.secondary
                                )
                                Switch(
                                    checked = displayOptions.isChecked,
                                    onCheckedChange = { displayOptions.onCheckedChange() }
                                )
                            }
                        }
                    }
                }
                item { HorizontalDivider(modifier = Modifier.padding(16.dp)) }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "Reminder Defaults",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W500,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Show Notification",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W500,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            Switch(
                                checked = showNotification,
                                onCheckedChange = { showNotification = !showNotification }
                            )
                        }
                    }
                }
            }
        }
    }
}

private data class DisplayOptions(
    val title: String,
    val isChecked: Boolean,
    val onCheckedChange: () -> Unit
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SettingScreenPreview() {
    TaskifyTheme {
        SettingScreen(navController = rememberNavController())
    }
}