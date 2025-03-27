package com.example.martocv.ui.feature_formation

import com.example.martocv.data.models.Formation

// --- State ---
data class FormationState(
    val isLoading: Boolean = false,
    val formations: List<Formation> = emptyList(),
    val error: String? = null
) {
    companion object {
        val initial = FormationState()
    }
}

// --- Intent ---
sealed interface FormationIntent {
    data object LoadFormations : FormationIntent
}

// --- Effect ---
sealed interface FormationEffect {
    data class ShowErrorToast(val message: String) : FormationEffect
}