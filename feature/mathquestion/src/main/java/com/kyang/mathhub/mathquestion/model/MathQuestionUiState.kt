package com.kyang.mathhub.mathquestion.model

data class MathQuestionUiState(
    val first: Int = 0,
    val second: Int = 0,
    val minNum: String = "1",
    val maxNum: String = "20",
    val answer: String = "",
    val submitted: Boolean = false,
    val correct: Boolean = false,
    val score: Int = 0,
    val roundScore: Int = 0,
    val round: Int = 0,
    val currTime: Int = 0,
    val maxTime: String = "5",
    val maxRound: String = "10",
    val timeEnabled: Boolean = true,
    val endless: Boolean = true,
    val gameOver: Boolean = false,
    val readyToPlay: Boolean = true,
    val roundProgress: Float = 1f
)
