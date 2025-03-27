package com.example.martocv.ui.feature_about.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.martocv.data.models.Hobby


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HobbiesSection(
    hobbies: List<Hobby>,
    onHobbyClick: (Hobby) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Hobbies & Passions",
            style = MaterialTheme.typography.titleLarge
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            hobbies.forEach { hobby ->
                SuggestionChip(
                    onClick = { onHobbyClick(hobby) },
                    label = { Text(hobby.name) }
                )
            }
        }
    }
}