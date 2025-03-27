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
import com.example.martocv.data.models.Skill
import com.example.martocv.data.models.SkillCategory


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsSection(
    groupedSkills: Map<SkillCategory, List<Skill>>,
    onSkillClick: (Skill) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        groupedSkills.forEach { (category, skillList) ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = category.toTitle(),
                    style = MaterialTheme.typography.titleLarge
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    skillList.forEach { skill ->
                        SuggestionChip(
                            onClick = { onSkillClick(skill) },
                            label = { Text(skill.name) }
                        )
                    }
                }
            }
        }
    }
}

// Helper extension function for category titles
private fun SkillCategory.toTitle(): String = when (this) {
    SkillCategory.TECHNICAL -> "Compétences Techniques"
    SkillCategory.SOFT -> "Soft Skills"
    SkillCategory.LANGUAGE -> "Langues"
}
