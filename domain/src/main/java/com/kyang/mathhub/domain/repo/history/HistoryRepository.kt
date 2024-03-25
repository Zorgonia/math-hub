package com.kyang.mathhub.domain.repo.history

import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.model.QuestionAnswerData

interface HistoryRepository {
    suspend fun addQuestionToHistory(data: QuestionAnswerData): Boolean

    suspend fun retrieveAllHistory(): List<LocalMathHistoryItem>

    suspend fun retrieveByQuestion(historyItem: LocalMathHistoryItem): List<LocalMathHistoryItem>
}
