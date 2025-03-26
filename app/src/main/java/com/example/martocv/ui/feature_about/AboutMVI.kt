package com.example.martocv.ui.feature_about

import com.example.martocv.data.models.Hobby
import com.example.martocv.data.models.PersonalInfo
import com.example.martocv.data.models.Skill

// --- State ---
data class AboutState(
    val isLoading: Boolean = true,
    val personalInfo: PersonalInfo? = null,
    val skills: List<Skill> = emptyList(),
    val hobbies: List<Hobby> = emptyList(),
    val error: String? = null
)

// --- Intent ---
sealed class AboutIntent {
    object LoadData : AboutIntent()
}

// --- Effect --- (Pour les actions ponctuelles comme afficher un Toast)
sealed class AboutEffect {
    data class ShowError(val message: String) : AboutEffect()
}