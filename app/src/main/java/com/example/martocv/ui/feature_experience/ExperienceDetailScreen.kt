package com.example.martocv.ui.feature_experience

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.martocv.R
import com.example.martocv.data.models.Experience
import com.example.martocv.data.models.Project
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceDetailScreen(
    experienceId: String,
    onBack: () -> Unit,
    viewModel: ExperienceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    // Trouve l'expérience spécifique dans la liste chargée par le ViewModel
    val experience = state.experiences.find { it.id == experienceId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.experience_detail_title)) },
                navigationIcon = {
                    IconButton(onClick = { onBack.invoke() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopStart // Aligne le contenu en haut
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                experience == null -> {
                    // Afficher un message si l'expérience n'est pas trouvée (ou si error != null)
                    Text(
                        "Détails de l'expérience non trouvés.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    ExperienceDetailContent(experience = experience)
                }
            }
        }
    }
}

@Composable
private fun ExperienceDetailContent(experience: Experience) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Afficher les infos générales de l'expérience
        item {
            Column {
                Text(
                    text = stringResource(experience.title),
                    style = MaterialTheme.typography.headlineSmall, // Un peu plus grand pour le détail
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${stringResource(experience.company)} - ${stringResource(experience.location)}",
                    style = MaterialTheme.typography.titleMedium, // Un peu plus grand
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${experience.startDate} - ${experience.endDate ?: stringResource(id = R.string.experience_present)}",
                    style = MaterialTheme.typography.bodyMedium, // Un peu plus grand
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                // Ajoute la description générale si elle existe et n'est pas vide
                if (experience.description != R.string.empty_string) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(experience.description),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Section des projets
        if (!experience.projects.isNullOrEmpty()) {
            item {
                Spacer(modifier = Modifier.height(8.dp)) // Espace avant le titre Projets
                Text(
                    text = stringResource(id = R.string.experience_detail_projects_title),
                    style = MaterialTheme.typography.titleLarge // Titre de section
                )
                HorizontalDivider(modifier = Modifier.padding(top = 4.dp, bottom = 12.dp))
            }

            items(experience.projects, key = { it.name }) { project ->
                ProjectItem(project = project)
            }
        }
    }
}
// Ajoute <string name="present">Présent</string> dans strings.xml

@Composable
private fun ProjectItem(project: Project) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium, // Forme légèrement différente ?
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
                    // Convertit la liste d'args en vararg pour stringResource
                    stringResource(durationRes, *project.durationArgs.toTypedArray())
                } else {
                    stringResource(durationRes)
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = durationText,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.tertiary // Couleur différente pour la durée
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
                    style = MaterialTheme.typography.labelLarge, // Plus petit titre pour les tâches
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
                        Spacer(modifier = Modifier.height(2.dp)) // Petit espace entre les tâches
                    }
                }
            }
        }
    }
}