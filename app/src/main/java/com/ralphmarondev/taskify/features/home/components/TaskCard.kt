package com.ralphmarondev.taskify.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ralphmarondev.taskify.core.model.Task
import com.ralphmarondev.taskify.core.navigation.Screens

@Composable
fun TaskCardMobile(
    task: Task,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(8f)
            ) {
                Text(
                    text = task.title,
                    maxLines = 1,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = task.description,
                    maxLines = 1,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = task.createDate,
                    maxLines = 1,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.weight(0.2f))
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(
                            Screens.UpdateTask(
                                id = task.id,
                                title = task.title,
                                description = task.description,
                                startTime = task.startTime,
                                endTime = task.endTime,
                                createDate = task.createDate
                            )
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

@Composable
fun TaskCardTablet(
    task: Task,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth(0.8f) // Set a maximum width for better alignment on larger screens
            .padding(horizontal = 24.dp, vertical = 16.dp), // Increased padding for spacious layout
        shape = RoundedCornerShape(16.dp), // Rounded corners for a modern look
        elevation = CardDefaults.cardElevation(8.dp) // Slightly higher elevation for more depth
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(8f)
            ) {
                Text(
                    text = task.title,
                    maxLines = 1,
                    fontSize = 24.sp, // Slightly larger text for tablet
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold // Bold title for emphasis
                )
                Spacer(modifier = Modifier.height(8.dp)) // Space between title and description
                Text(
                    text = task.description,
                    maxLines = 2, // Allow more lines for description on a larger screen
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Created: ${task.createDate}",
                    maxLines = 1,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.weight(0.2f))
            IconButton(
                onClick = {
                    navController.navigate(
                        Screens.UpdateTask(
                            id = task.id,
                            title = task.title,
                            description = task.description,
                            startTime = task.startTime,
                            endTime = task.endTime,
                            createDate = task.createDate
                        )
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Task",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(24.dp) // Adjust icon size for better visibility
                )
            }
        }
    }
}