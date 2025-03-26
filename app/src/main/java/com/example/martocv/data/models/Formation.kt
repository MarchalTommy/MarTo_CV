package com.example.martocv.data.models

data class Formation(
    val startDate: String,
    val endDate: String,
    val institution: String,
    val degree: String,
    val details: String? = null
)
