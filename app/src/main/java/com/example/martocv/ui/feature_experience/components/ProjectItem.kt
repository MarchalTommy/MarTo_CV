package com.example.martocv.ui.feature_experience.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.martocv.R
import com.example.martocv.data.models.Project


@Composable
fun ProjectItem(project: Project) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = stringResource(project.name),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            project.duration?.let { durationRes ->
                val durationText = if (!project.durationArgs.isNullOrEmpty()) {

                    stringResource(durationRes, *project.durationArgs.toTypedArray())
                } else {
                    stringResource(durationRes)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = durationText,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(project.description),
                style = MaterialTheme.typography.bodyMedium
            )
            if (project.tasks.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.experience_detail_tasks_title),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    project.tasks.forEach { taskResId ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircleOutline,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = stringResource(id = taskResId),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
            }
        }
    }
}