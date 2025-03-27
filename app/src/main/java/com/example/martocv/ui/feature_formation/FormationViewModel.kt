package com.example.martocv.ui.feature_formation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.martocv.data.source.CvDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormationViewModel(private val cvDataSource: CvDataSource) : ViewModel() {

    private val _state = MutableStateFlow(FormationState.initial)
    val state: StateFlow<FormationState> = _state.asStateFlow()

    init {
        handleEvent(FormationIntent.LoadFormations)
    }

    fun handleEvent(event: FormationIntent) {
        when (event) {
            is FormationIntent.LoadFormations -> loadFormations()
        }
    }

    private fun loadFormations() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val formations = cvDataSource.getCvData().formations
                _state.update {
                    it.copy(isLoading = false, formations = formations)
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = "Erreur lors du chargement des formations")
                }
            }
        }
    }
}