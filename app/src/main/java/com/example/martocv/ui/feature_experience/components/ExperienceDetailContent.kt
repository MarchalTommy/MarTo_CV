package com.example.martocv.ui.feature_experience.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.martocv.R
import com.example.martocv.data.models.Experience


@Composable
fun ExperienceDetailContent(experience: Experience) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Column {
                Text(
                    text = stringResource(experience.title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(experience.company)} - ${stringResource(experience.location)}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${experience.startDate} - ${experience.endDate ?: stringResource(id = R.string.experience_present)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (experience.description != R.string.empty_string) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(experience.description),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }


        if (!experience.projects.isNullOrEmpty()) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.experience_detail_projects_title),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(modifier = Modifier.padding(top = 4.dp, bottom = 12.dp))
            }

            items(experience.projects, key = { it.name }) { project ->
                ProjectItem(project = project)
            }
        }
    }
}