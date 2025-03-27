package com.example.martocv.ui.feature_experience.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.martocv.R
import com.example.martocv.ui.feature_experience.ExperienceViewModel
import com.example.martocv.ui.feature_experience.components.ExperienceDetailContent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceDetailScreen(
    experienceId: String,
    onBack: () -> Unit,
    viewModel: ExperienceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val experience = state.experiences.find { it.id == experienceId }

    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.experience_detail_title),
                    style = androidx.compose.material3.MaterialTheme.typography.displaySmall
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBack.invoke() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                }
            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                experience == null -> {

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
