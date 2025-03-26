package com.example.martocv.data.models

import androidx.annotation.StringRes

enum class SkillCategory {
    TECHNICAL, SOFT, LANGUAGE
}

data class Skill(
    val name: String,
    val category: SkillCategory,
    @StringRes val descriptionResId: Int
)