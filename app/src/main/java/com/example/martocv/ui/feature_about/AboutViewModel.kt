package com.example.martocv.ui.feature_about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.martocv.data.source.CvDataSource // Importez votre fonction de données
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AboutViewModel(
    private val cvDataSource: CvDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(AboutState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AboutEffect>()
    val effect = _effect.asSharedFlow()

    init {
        handleIntent(AboutIntent.LoadData) // Charger les données au démarrage
    }

    fun handleIntent(intent: AboutIntent) {
        when (intent) {
            is AboutIntent.LoadData -> loadAboutData()
        }
    }

    private fun loadAboutData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val cvData = cvDataSource.getCvData()
                _state.update {
                    it.copy(
                        isLoading = false,
                        personalInfo = cvData.personalInfo,
                        skills = cvData.skills,
                        hobbies = cvData.hobbies
                    )
                }
            } catch (e: Exception) {
                val errorMessage = "Erreur lors du chargement des données: ${e.localizedMessage}"
                _state.update { it.copy(isLoading = false, error = errorMessage) }
                _effect.emit(AboutEffect.ShowError(errorMessage))
            }
        }
    }
}