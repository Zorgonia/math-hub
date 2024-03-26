package com.kyang.mathhub.domain.repo.history.mapper

import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.model.QuestionAnswerData
import com.kyang.mathhub.model.MathOperation
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

//fun questionStringToEquation(input: String): MathQuestionEquation? {
//    try {
//        val parts = input.split(" ")
//        if (parts.size == 3) {
//            val first = parts[0].toInt()
//            val second = parts[2].toInt()
//            val op = MathOperation.entries.firstOrNull { it.rep == parts[1] }
//            op?.let { foundOp ->
//                return MathQuestionEquation(MathQuestionSimple(first),MathQuestionSimple(second), foundOp)
//            }
//        }
//        return null
//    } catch (e: Exception) {
//        Log.e("test", "error")
//        return null
//    }
//}

fun reverseQuestionOrder(input: String): String {
    val split = input.split(" ")
    if (split.size == 3) {
        return "${split[2]} ${split[1]} ${split[0]}"
    }
    return input
}

fun isMultiplyOrAdd(input: String): Boolean {
    val split = input.split(" ")
    if (split.size == 3) {
        return split[1] == MathOperation.ADD.rep || split[1] == MathOperation.MULTIPLY.rep
    }
    return false
}

fun isSameReversed(input: String): Boolean {
    val split = input.split(" ")
    if (split.size == 3) {
        return split[2] == split[0]
    }
    return false
}
