package com.kyang.mathhub.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.repo.history.HistoryRepository
import com.kyang.mathhub.history.model.HistoryDetailUiState
import com.kyang.mathhub.history.model.HistoryHomeUiState
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
    private val _homeUiState: MutableStateFlow<HistoryHomeUiState> = MutableStateFlow(HistoryHomeUiState())
    val homeUiState: StateFlow<HistoryHomeUiState> = _homeUiState

    private val _detailUiState: MutableStateFlow<HistoryDetailUiState> = MutableStateFlow(HistoryDetailUiState())
    val detailUiState: StateFlow<HistoryDetailUiState> = _detailUiState

    private fun getAllHistory(){
        viewModelScope.launch {
            val data = repository.retrieveAllHistory()
            _homeUiState.update { curr ->
                curr.copy(
                    data = data
                )
            }
        }
    }

    fun getHistory(question: LocalMathHistoryItem) {
        viewModelScope.launch {
            val data = repository.retrieveByQuestion(question)
            val correct = data.count { it.correct }
            _detailUiState.update { curr ->
                curr.copy(
                    question = question,
                    historyItems = data,
                    timesAnswered = data.size,
                    timesCorrect = correct,
                    timesIncorrect = data.size - correct
                )
            }
        }
    }

    init {
        getAllHistory()
    }
}
