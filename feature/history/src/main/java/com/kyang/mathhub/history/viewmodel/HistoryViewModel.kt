package com.kyang.mathhub.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyang.mathhub.domain.repo.history.HistoryRepository
import com.kyang.mathhub.history.model.HistoryHomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<HistoryHomeUIState> = MutableStateFlow(HistoryHomeUIState())
    val uiState: StateFlow<HistoryHomeUIState> = _uiState

    private fun getAllHistory(){
        viewModelScope.launch {
            val data = repository.retrieveAllHistory()
            _uiState.update { curr ->
                curr.copy(
                    data = data
                )
            }
        }
    }

    init {
        getAllHistory()
    }
}
