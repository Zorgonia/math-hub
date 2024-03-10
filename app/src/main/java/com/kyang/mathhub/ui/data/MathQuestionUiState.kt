package com.kyang.mathhub.ui.data

data class MathQuestionUiState(
    val first: Int = 0,
    val second: Int = 0,
    val minNum: String = "1",
    val maxNum: String = "20",
    val answer: String = "",
    val submitted: Boolean = false,
    val correct: Boolean = false,
)
