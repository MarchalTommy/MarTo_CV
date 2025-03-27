package com.example.martocv.ui.feature_about.components

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.martocv.data.models.Hobby
import com.example.martocv.data.models.PersonalInfo
import com.example.martocv.data.models.Skill


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AboutContent(
    personalInfo: PersonalInfo,
    skills: List<Skill>,
    hobbies: List<Hobby>,
    topBarScrollFraction: Float,
    modifier: Modifier = Modifier
) {
    val groupedSkills = skills.groupBy { it.category }
    var showDialog by remember { mutableStateOf<DialogInfo?>(null) }
    val backgroundColor = MaterialTheme.colorScheme.background

    val lazyState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        state = lazyState
    ) {
        stickyHeader {
            PersonalInfoSection(
                personalInfo = personalInfo,
                topBarScrollFraction = topBarScrollFraction
            )
            HorizontalDivider(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(top = 32.dp)
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                backgroundColor,
                                Color.Transparent
                            )
                        )
                    )
            )
        }
        item {
            SkillsSection(
                groupedSkills = groupedSkills,
                onSkillClick = { skill ->
                    showDialog = DialogInfo(skill.name, skill.descriptionResId)
                }
            )
        }
        item {
            HobbiesSection(
                hobbies = hobbies,
                onHobbyClick = { hobby ->
                    showDialog = DialogInfo(hobby.name, hobby.descriptionResId)
                }
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    showDialog?.let { info ->
        InfoDialog(
            title = info.title,
            descriptionResId = info.descriptionResId,
            onDismiss = { showDialog = null }
        )
    }
}

// Structure pour contenir les infos du dialogue
private data class DialogInfo(
    val title: String,
    @StringRes val descriptionResId: Int
)