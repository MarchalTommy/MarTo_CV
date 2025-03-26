package com.example.martocv.data.models

data class CvData(
    val personalInfo: PersonalInfo,
    val formations: List<Formation>,
    val skills: List<Skill>,
    val experiences: List<Experience>,
    val hobbies: List<Hobby>
)
