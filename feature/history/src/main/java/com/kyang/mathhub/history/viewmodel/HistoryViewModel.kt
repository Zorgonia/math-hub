package com.kyang.mathhub.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.repo.history.HistoryRepository
import com.kyang.mathhub.history.model.HistoryDetailUiState
import com.kyang.mathhub.history.model.HomeHistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {
    private val _homeUiState: MutableStateFlow<HomeHistoryUiState> = MutableStateFlow(HomeHistoryUiState())
    val homeUiState: StateFlow<HomeHistoryUiState> = _homeUiState

    private val _detailUiState: MutableStateFlow<HistoryDetailUiState> = MutableStateFlow(HistoryDetailUiState())
    val detailUiState: StateFlow<HistoryDetailUiState> = _detailUiState

    private fun getAllHistory(){
        viewModelScope.launch {
            val data = historyRepository.retrieveAllHistory()
            _homeUiState.update { curr ->
                curr.copy(
                    data = data
                )
            }
        }
    }

    fun getHistory(question: LocalMathHistoryItem) {
        viewModelScope.launch {
            val data = historyRepository.retrieveByQuestion(question)
            val correct = data.count { it.correct }
            _detailUiState.update { curr ->
                curr.copy(
                    question = question,
                    historyItems = data,
                    timesAnswered = data.size,
                    timesCorrect = correct,
                    timesIncorrect = data.size - correct,
                    correctAnswer = question.correctAnswer,
                    answerPercent = answerPercent(correct, data.size)
                )
            }
        }
    }

    private fun answerPercent(correct: Int, size: Int): String {
        return "%.${2}f".format(correct.toFloat()/size * 100)
    }

    fun openDeleteDialog() {
        _homeUiState.update { curr ->
            curr.copy(
                deleteDialogOpen = true
            )
        }
    }

    fun dismissDeleteDialog() {
        _homeUiState.update { curr ->
            curr.copy(
                deleteDialogOpen = false
            )
        }
    }
    fun deleteHistory() {
        viewModelScope.launch {
            historyRepository.deleteHistory()
            val data = historyRepository.retrieveAllHistory()
            _homeUiState.update { curr ->
                curr.copy(
                    data = data,
                    deleteDialogOpen = false
                )
            }
        }
    }

    init {
        getAllHistory()
    }
}
