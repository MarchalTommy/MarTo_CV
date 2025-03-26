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

// --- Event ---
sealed interface FormationEvent {
    data object LoadFormations : FormationEvent
}

// --- Effect ---
// Ajouter si nécessaire
// sealed interface FormationEffect { ... }