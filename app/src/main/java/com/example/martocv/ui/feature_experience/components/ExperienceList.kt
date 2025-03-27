package com.example.martocv.ui.feature_experience.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martocv.data.models.Experience


@Composable
fun ExperienceList(
    experiences: List<Experience>,
    onExperienceClick: (experienceId: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(experiences, key = { it.id }) { experience ->
            ExperienceItem(
                experience = experience,
                onClick = { onExperienceClick(experience.id) }
            )
        }
    }
}