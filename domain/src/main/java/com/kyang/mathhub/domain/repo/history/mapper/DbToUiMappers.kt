package com.kyang.mathhub.domain.repo.history.mapper

import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.model.QuestionAnswerData
import com.kyang.mathhub.model.db.entity.MathQuestionEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun mapMathQuestionEntityToLocalMathHistoryItem(entity: MathQuestionEntity): LocalMathHistoryItem {
    return LocalMathHistoryItem(
        id = entity.id,
        question = entity.question,
        correct = entity.correct,
        timestamp = entity.timestamp,
        timeUsed = entity.timeUsed.toString(),
        correctAnswer = entity.correctAnswer,
        userAnswer = entity.userAnswer,
    )
}

fun mapMathQuestionEquationToMathQuestionEntity(
    data: QuestionAnswerData,
): MathQuestionEntity {
    return MathQuestionEntity(
        question = data.question.toString(),
        timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
        correct = data.correctAnswer == data.userAnswer,
        correctAnswer = data.correctAnswer,
        userAnswer = data.userAnswer,
        timeUsed = data.timeUsed,
    )
}
