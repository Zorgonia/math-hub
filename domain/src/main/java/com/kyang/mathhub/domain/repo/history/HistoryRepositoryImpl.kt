package com.kyang.mathhub.domain.repo.history

import android.util.Log
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.repo.history.mapper.mapMathQuestionEntityToLocalMathHistoryItem
import com.kyang.mathhub.domain.repo.history.mapper.mapMathQuestionEquationToMathQuestionEntity
import com.kyang.mathhub.model.MathQuestionEquation
import com.kyang.mathhub.model.db.MathQuestionDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepositoryImpl @Inject constructor(
    private val dao: MathQuestionDao
): HistoryRepository {
    override suspend fun addQuestionToHistory(question: MathQuestionEquation, correct: Boolean): Boolean {
        try {
            dao.insert(mapMathQuestionEquationToMathQuestionEntity(question, correct))
        } catch (e: Exception) {
            Log.e("error", "${e.message}")
            return false
        }
        return true
    }

    override suspend fun retrieveAllHistory(): List<LocalMathHistoryItem> {
        return dao.getAll().map {
            mapMathQuestionEntityToLocalMathHistoryItem(it)
        }
    }

    override suspend fun retrieveByQuestion(question: MathQuestionEquation): List<LocalMathHistoryItem> {
        return dao.getByQuestion(question.toString()).map {
            mapMathQuestionEntityToLocalMathHistoryItem(it)
        }
    }

}
