package com.example.martocv.ui.feature_about

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.martocv.R
import com.example.martocv.data.models.Hobby
import com.example.martocv.data.models.PersonalInfo
import com.example.martocv.data.models.Skill
import com.example.martocv.data.models.SkillCategory
import org.koin.androidx.compose.koinViewModel


// Structure pour contenir les infos du dialogue
private data class DialogInfo(
    val title: String,
    @StringRes val descriptionResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    viewModel: AboutViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Qui suis-je ?") })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> LoadingState()
                state.error != null -> ErrorState(error = state.error!!)
                state.personalInfo != null -> {
                    AboutContent(
                        personalInfo = state.personalInfo!!,
                        skills = state.skills,
                        hobbies = state.hobbies
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingState() {
    CircularProgressIndicator()
}

@Composable
private fun ErrorState(error: String) {
    Text(
        text = "Erreur: $error",
        color = MaterialTheme.colorScheme.error,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AboutContent(
    personalInfo: PersonalInfo,
    skills: List<Skill>,
    hobbies: List<Hobby>,
    modifier: Modifier = Modifier
) {
    val groupedSkills = skills.groupBy { it.category }
    // État pour gérer l'affichage et le contenu du dialogue
    var showDialog by remember { mutableStateOf<DialogInfo?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        PersonalInfoSection(personalInfo = personalInfo)
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        SkillsSection(
            groupedSkills = groupedSkills,
            onSkillClick = { skill -> // Lambda pour gérer le clic sur une compétence
                showDialog = DialogInfo(skill.name, skill.descriptionResId)
            }
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        HobbiesSection(
            hobbies = hobbies,
            onHobbyClick = { hobby -> // Lambda pour gérer le clic sur un hobby
                showDialog = DialogInfo(hobby.name, hobby.descriptionResId)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

    // Afficher le dialogue si showDialog n'est pas null
    showDialog?.let { info ->
        InfoDialog(
            title = info.title,
            descriptionResId = info.descriptionResId,
            onDismiss = { showDialog = null } // Masquer le dialogue lors du dismiss
        )
    }
}

@Composable
private fun PersonalInfoSection(personalInfo: PersonalInfo) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) { // Reduced spacing within section
        Text(
            text = personalInfo.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = personalInfo.title,
            style = MaterialTheme.typography.titleLarge,
            // Removed bottom padding, handled by Column spacing
        )
        Text(
            text = personalInfo.summary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun SkillsSection(
    groupedSkills: Map<SkillCategory, List<Skill>>,
    onSkillClick: (Skill) -> Unit // Ajout du paramètre pour le clic
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        groupedSkills.forEach { (category, skillList) ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = category.toTitle(),
                    style = MaterialTheme.typography.titleMedium
                )
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    skillList.forEach { skill ->
                        SuggestionChip(
                            onClick = { onSkillClick(skill) }, // Appelle le lambda lors du clic
                            label = { Text(skill.name) }
                        )
                    }
                }
            }
        }
    }
}

// Helper extension function for category titles (optional but cleaner)
private fun SkillCategory.toTitle(): String = when (this) {
    SkillCategory.TECHNICAL -> "Compétences Techniques"
    SkillCategory.SOFT -> "Soft Skills"
    SkillCategory.LANGUAGE -> "Langues"
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun HobbiesSection(
    hobbies: List<Hobby>,
    onHobbyClick: (Hobby) -> Unit // Ajout du paramètre pour le clic
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Hobbies & Passions",
            style = MaterialTheme.typography.titleMedium
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            hobbies.forEach { hobby ->
                SuggestionChip(
                    onClick = { onHobbyClick(hobby) }, // Appelle le lambda lors du clic
                    label = { Text(hobby.name) }
                )
            }
        }
    }
}

@Composable
private fun InfoDialog(
    title: String,
    @StringRes descriptionResId: Int,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = title) },
        text = { Text(text = stringResource(id = descriptionResId)) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.dialog_close))
            }
        }
    )
}