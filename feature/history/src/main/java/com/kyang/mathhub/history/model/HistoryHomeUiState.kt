package com.kyang.mathhub.history.model

import com.kyang.mathhub.domain.model.LocalMathHistoryItem

data class HistoryHomeUiState(
    val data: List<LocalMathHistoryItem> = listOf()
)
