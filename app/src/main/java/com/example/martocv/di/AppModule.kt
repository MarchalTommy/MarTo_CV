package com.example.martocv.di

import com.example.martocv.data.source.CvDataSource
import com.example.martocv.data.source.LocalCvDataSource
import com.example.martocv.ui.feature_about.AboutViewModel
import com.example.martocv.ui.feature_experience.ExperienceViewModel
import com.example.martocv.ui.feature_formation.FormationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Single instance of CvDataSource (using LocalCvDataSource implementation)
    single<CvDataSource> { LocalCvDataSource() }

    // Factory for ProfileViewModel
    viewModel { AboutViewModel(cvDataSource = get()) }
    viewModel { ExperienceViewModel(cvDataSource = get()) }
    viewModel { FormationViewModel(cvDataSource = get()) }
}