package com.kyang.mathhub.history.model

import com.kyang.mathhub.domain.model.LocalMathHistoryItem

data class HomeHistoryUiState(
    val data: List<LocalMathHistoryItem> = listOf(),
    val deleteDialogOpen: Boolean = false
)
