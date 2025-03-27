package com.example.martocv.ui.feature_experience.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.martocv.ui.feature_experience.ExperienceViewModel
import com.example.martocv.ui.feature_experience.components.ExperienceList
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class) // Ajouté pour le Card cliquable
@Composable
fun ExperienceScreen(
    onExperienceClick: (experienceId: String) -> Unit,
    viewModel: ExperienceViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Expériences Professionnelles",
                    style = MaterialTheme.typography.displaySmall
                )
            },
                expandedHeight = 128.dp,
                scrollBehavior = scrollBehavior
            )
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
