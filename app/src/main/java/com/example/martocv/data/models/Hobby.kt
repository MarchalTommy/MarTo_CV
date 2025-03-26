package com.example.martocv.data.models

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class Hobby(
    val name: String,
    @StringRes val descriptionResId: Int,
    val icon: ImageVector? = null
)
