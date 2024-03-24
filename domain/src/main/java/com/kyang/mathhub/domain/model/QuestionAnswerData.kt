package com.kyang.mathhub.domain.model

import com.kyang.mathhub.model.MathQuestionEquation

data class QuestionAnswerData(
    val userAnswer: String,
    val correctAnswer: String,
    val question: MathQuestionEquation,
    val timeUsed: Int
)
