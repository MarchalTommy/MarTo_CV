package com.example.martocv.ui.feature_about.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.martocv.ui.feature_about.AboutViewModel
import com.example.martocv.ui.feature_about.components.AboutContent
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    viewModel: AboutViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Column(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Qui suis-je ?",
                    style = MaterialTheme.typography.displaySmall
                )
            },
            scrollBehavior = scrollBehavior
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            when {
                state.isLoading -> LoadingState()
                state.error != null -> ErrorState(error = state.error!!)
                state.personalInfo != null -> {
                    AboutContent(
                        personalInfo = state.personalInfo!!,
                        skills = state.skills,
                        hobbies = state.hobbies,
                        topBarScrollFraction = 1 - scrollBehavior.state.collapsedFraction
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