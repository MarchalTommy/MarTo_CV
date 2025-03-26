package com.example.martocv.data.models

import androidx.annotation.StringRes

data class Experience(
    val id: String,
    @StringRes val title: Int,
    @StringRes val company: Int,
    @StringRes val location: Int,
    val startDate: String,
    val endDate: String?,
    @StringRes val description: Int,
    val projects: List<Project>? = null
)
