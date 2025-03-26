package com.example.martocv.ui.feature_experience

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.martocv.data.source.CvDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExperienceViewModel(private val cvDataSource: CvDataSource) : ViewModel() {

    private val _state = MutableStateFlow(ExperienceState.initial)
    val state: StateFlow<ExperienceState> = _state.asStateFlow()

    init {
        handleEvent(ExperienceEvent.LoadExperiences)
    }

    fun handleEvent(event: ExperienceEvent) {
        when (event) {
            is ExperienceEvent.LoadExperiences -> loadExperiences()
        }
    }

    private fun loadExperiences() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val experiences = cvDataSource.getCvData().experiences
                _state.update {
                    it.copy(isLoading = false, experiences = experiences)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = "Erreur lors du chargement des expériences")
                }
            }
        }
    }
}