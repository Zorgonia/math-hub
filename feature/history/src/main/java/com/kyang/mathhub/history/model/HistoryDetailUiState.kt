package com.kyang.mathhub.history.model

import com.kyang.mathhub.domain.model.LocalMathHistoryItem

data class HistoryDetailUiState(
    val question: LocalMathHistoryItem? = null,
    val historyItems: List<LocalMathHistoryItem> = listOf(),
    val timesAnswered: Int = 0,
    val timesCorrect: Int = 0,
    val timesIncorrect: Int = 0,
)
