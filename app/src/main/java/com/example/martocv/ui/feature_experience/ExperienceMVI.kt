package com.example.martocv.ui.feature_experience

import com.example.martocv.data.models.Experience

// --- State ---
data class ExperienceState(
    val isLoading: Boolean = false,
    val experiences: List<Experience> = emptyList(),
    val error: String? = null
) {
    companion object {
        val initial = ExperienceState()
    }
}

// --- Intent ---
sealed interface ExperienceIntent {
    data object LoadExperiences : ExperienceIntent
}

// --- Effect ---
sealed interface ExperienceEffect {
    data class ShowErrorToast(val message: String) : ExperienceEffect
}