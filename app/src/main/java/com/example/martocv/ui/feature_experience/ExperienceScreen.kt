package com.example.martocv.ui.feature_experience

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.martocv.R
import com.example.martocv.data.models.Experience
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class) // Ajouté pour le Card cliquable
@Composable
fun ExperienceScreen(
    onExperienceClick: (experienceId: String) -> Unit,
    viewModel: ExperienceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Expériences Professionnelles") })
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
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(
                    "Erreur: ${state.error}",
                    color = MaterialTheme.colorScheme.error
                )

                state.experiences.isNotEmpty() -> ExperienceList(
                    experiences = state.experiences,
                    onExperienceClick = { experienceId ->
                        onExperienceClick.invoke(experienceId)
                    }
                )

                else -> Text("Aucune expérience à afficher.")
            }
        }
    }
}

@Composable
private fun ExperienceList(
    experiences: List<Experience>,
    onExperienceClick: (experienceId: String) -> Unit // Ajout du callback
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(experiences, key = { it.id }) { experience -> // Utilise le nouvel ID comme clé
            ExperienceItem(
                experience = experience,
                onClick = { onExperienceClick(experience.id) } // Passe l'ID au callback
            )
        }
    }
}

@Composable
private fun ExperienceItem(
    experience: Experience,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(experience.title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${stringResource(experience.company)} - ${stringResource(experience.location)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${experience.startDate} - ${experience.endDate ?: stringResource(id = R.string.experience_present)}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (experience.description != R.string.empty_string) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(experience.description),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}