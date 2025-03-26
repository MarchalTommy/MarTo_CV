package com.example.martocv.data.models

import androidx.annotation.StringRes

data class Project(
    @StringRes val name: Int,
    @StringRes val duration: Int?,
    val durationArgs: List<String>? = null,
    @StringRes val description: Int,
    @StringRes val tasks: List<Int>
)
