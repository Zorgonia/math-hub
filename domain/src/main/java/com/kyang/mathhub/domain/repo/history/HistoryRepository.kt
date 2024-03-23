package com.kyang.mathhub.domain.repo.history

import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.model.MathQuestionEquation

interface HistoryRepository {
    suspend fun addQuestionToHistory(question: MathQuestionEquation, correct: Boolean): Boolean

    suspend fun retrieveAllHistory(): List<LocalMathHistoryItem>

    suspend fun retrieveByQuestion(question: MathQuestionEquation): List<LocalMathHistoryItem>
}
