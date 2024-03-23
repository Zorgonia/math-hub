package com.kyang.mathhub.domain.repo.history.mapper

import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.model.MathQuestionEquation
import com.kyang.mathhub.model.db.MathQuestionEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun mapMathQuestionEntityToLocalMathHistoryItem(entity: MathQuestionEntity): LocalMathHistoryItem {
    return LocalMathHistoryItem(
        id = entity.id,
        question = entity.question,
        correct = entity.correct,
        timestamp = entity.timestamp
    )
}

fun mapMathQuestionEquationToMathQuestionEntity(equation: MathQuestionEquation, correct: Boolean): MathQuestionEntity {
    return MathQuestionEntity(
        question = equation.toString(),
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
        correct = correct
    )
}
