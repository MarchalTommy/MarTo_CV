package com.example.martocv.data.source

import com.example.martocv.R
import com.example.martocv.data.models.CvData
import com.example.martocv.data.models.Experience
import com.example.martocv.data.models.Formation
import com.example.martocv.data.models.Hobby
import com.example.martocv.data.models.PersonalInfo
import com.example.martocv.data.models.Project
import com.example.martocv.data.models.Skill
import com.example.martocv.data.models.SkillCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Interface defining how to get CV data
interface CvDataSource {
    suspend fun getCvData(): CvData
}

// Implementation providing hardcoded data
class LocalCvDataSource : CvDataSource {
    override suspend fun getCvData(): CvData = withContext(Dispatchers.IO) {
        CvData(
            personalInfo = PersonalInfo(
                name = "Tommy MARCHAL",
                title = "Développeur Android",
                address = "125 Avenue Pierre Dumond\n69290 CRAPONNE",
                phone = "06 21 26 88 87",
                email = "marchal.tommy@gmail.com",
                githubUrl = "https://github.com/MarchalTommy",
                linkedinUrl = "https://www.linkedin.com/in/marchal-t/",
                summary = "Fort d'une expérience significative en développement auprès d'entreprises d'envergure, et ayant contribué au succès de projets innovants, je suis un team-player motivé et engagé qui saura apporter une réelle valeur ajoutée à votre équipe."
            ),
            skills = listOf(
                Skill("Android", SkillCategory.TECHNICAL, R.string.skill_desc_android),
                Skill("Kotlin", SkillCategory.TECHNICAL, R.string.skill_desc_kotlin),
                Skill("Java", SkillCategory.TECHNICAL, R.string.skill_desc_java),
                Skill("Jetpack Compose", SkillCategory.TECHNICAL, R.string.skill_desc_compose),
                Skill("MVVM / MVI", SkillCategory.TECHNICAL, R.string.skill_desc_mvvm_mvi),
                Skill(
                    "Modular Architecture",
                    SkillCategory.TECHNICAL,
                    R.string.skill_desc_modular_arch
                ),
                Skill("Gradle", SkillCategory.TECHNICAL, R.string.skill_desc_gradle),
                Skill("Git", SkillCategory.TECHNICAL, R.string.skill_desc_git),
                Skill("CI/CD", SkillCategory.TECHNICAL, R.string.skill_desc_ci_cd),
                Skill("Koin", SkillCategory.TECHNICAL, R.string.skill_desc_koin),
                Skill("Refactoring", SkillCategory.TECHNICAL, R.string.skill_desc_refactoring),
                Skill("Unit Testing", SkillCategory.TECHNICAL, R.string.skill_desc_testing),
                Skill("Travail d'équipe", SkillCategory.SOFT, R.string.skill_desc_teamwork),
                Skill("Communication", SkillCategory.SOFT, R.string.skill_desc_communication),
                Skill("Rigueur", SkillCategory.SOFT, R.string.skill_desc_rigor),
                Skill("Anglais", SkillCategory.LANGUAGE, R.string.skill_desc_english),
                Skill("Allemand", SkillCategory.LANGUAGE, R.string.skill_desc_german),
                // Add more granular skills if desired
            ),
            hobbies = listOf(
                Hobby("Impression 3D", R.string.hobby_desc_3d_printing),
                Hobby("Photographie", R.string.hobby_desc_photography),
                Hobby("Donjons et Dragons", R.string.hobby_desc_dnd),
                Hobby("Randonnée", R.string.hobby_desc_hiking),
                Hobby("Escalade", R.string.hobby_desc_climbing)
                // Add Icons later using androidx.compose.material.icons
            ),
            experiences = listOf(
                Experience(
                    id = "MARTO#2024-07",
                    startDate = "Juillet 2024",
                    endDate = "actif",
                    company = R.string.experience_company_marto,
                    location = R.string.experience_company_marto_location,
                    title = R.string.experience_title_freelance,
                    description = R.string.experience_desc_marto,
                    projects = listOf(
                        Project(
                            name = R.string.project_freelance_cheese_title,
                            duration = R.string.experience_duration_months,
                            durationArgs = listOf("6"), // 2*3 = 6 mois total approx.
                            description = R.string.project_freelance_cheese_desc,
                            tasks = listOf(
                                R.string.project_freelance_cheese_task_archi,
                                R.string.project_freelance_cheese_task_compose,
                                R.string.project_freelance_cheese_task_sdk,
                                R.string.project_freelance_cheese_task_cicd
                            )
                        ),
                    )
                ),
                Experience(
                    id = "EXOMIND#2021-11",
                    startDate = "Novembre 2021",
                    endDate = "Juillet 2024",
                    company = R.string.experience_company_exomind,
                    location = R.string.experience_company_exomind_location,
                    title = R.string.experience_title_dev,
                    description = R.string.experience_desc_exomind,
                    projects = listOf(
                        Project(
                            name = R.string.project_sodexo_title,
                            duration = R.string.experience_duration_months,
                            durationArgs = listOf("9"),
                            description = R.string.project_sodexo_desc,
                            tasks = listOf(
                                R.string.project_sodexo_task_refactor,
                                R.string.project_sodexo_task_modernize,
                                R.string.project_sodexo_task_rebrand
                            )
                        ),
                        Project(
                            name = R.string.project_tdf_title,
                            duration = R.string.experience_duration_months,
                            durationArgs = listOf("6"), // 2*3 = 6 mois total approx.
                            description = R.string.project_tdf_desc,
                            tasks = listOf(
                                R.string.project_tdf_task_compose,
                                R.string.project_tdf_task_pip,
                                R.string.project_tdf_task_refactor,
                                R.string.project_tdf_task_features
                            )
                        ),
                        Project(
                            name = R.string.project_dakar_title,
                            duration = R.string.experience_duration_months,
                            durationArgs = listOf("6"), // 2*3 = 6 mois total approx.
                            description = R.string.project_dakar_desc,
                            tasks = listOf(
                                R.string.project_dakar_task_compose,
                                R.string.project_dakar_task_gradle,
                                R.string.project_dakar_task_features
                            )
                        ),
                        Project(
                            name = R.string.project_total_title,
                            duration = R.string.experience_duration_year_months, // 1 an 6 mois
                            durationArgs = listOf("1", "6"),
                            description = R.string.project_total_desc,
                            tasks = listOf(
                                R.string.project_total_task_modernize,
                                R.string.project_total_task_doc,
                                R.string.project_total_task_archi,
                                R.string.project_total_task_db,
                                R.string.project_total_task_cicd
                            )
                        )
                    )
                )
            ),
            formations = listOf(
                Formation(
                    startDate = "2020-09",
                    endDate = "2021-09",
                    institution = "OpenClassrooms",
                    degree = "Développeur d'application Android",
                    details = "Diplôme RNCP niveau 6. Formation complète sur le développement Android natif, couvrant les fondamentaux, les architectures modernes (MVVM), la gestion des données, les tests et la publication d'applications."
                )
            )
        )
    }
}