package com.kyang.mathhub.domain.model

data class LocalMathHistoryItem(
    val id: Int,
    val timestamp: String,
    val correct: Boolean,
    val question: String,
)
