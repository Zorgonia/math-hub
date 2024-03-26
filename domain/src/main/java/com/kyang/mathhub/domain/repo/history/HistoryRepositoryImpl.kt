package com.kyang.mathhub.domain.repo.history

import android.util.Log
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.model.QuestionAnswerData
import com.kyang.mathhub.domain.repo.history.mapper.isMultiplyOrAdd
import com.kyang.mathhub.domain.repo.history.mapper.isSameReversed
import com.kyang.mathhub.domain.repo.history.mapper.mapMathQuestionEntityToLocalMathHistoryItem
import com.kyang.mathhub.domain.repo.history.mapper.mapMathQuestionEquationToMathQuestionEntity
import com.kyang.mathhub.domain.repo.history.mapper.reverseQuestionOrder
import com.kyang.mathhub.model.db.MathQuestionDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepositoryImpl @Inject constructor(
    private val dao: MathQuestionDao
): HistoryRepository {
    override suspend fun addQuestionToHistory(data: QuestionAnswerData): Boolean {
        try {
            dao.insert(mapMathQuestionEquationToMathQuestionEntity(data))
        } catch (e: Exception) {
            Log.e("error", "${e.message}")
            return false
        }
        return true
    }

    override suspend fun retrieveAllHistory(): List<LocalMathHistoryItem> {

        return dao.getAll().map {
            Log.d("test", "$it")
            mapMathQuestionEntityToLocalMathHistoryItem(it)
        }
    }

    override suspend fun retrieveByQuestion(historyItem: LocalMathHistoryItem): List<LocalMathHistoryItem> {
        val questions = dao.getByQuestion(historyItem.question).map {
            mapMathQuestionEntityToLocalMathHistoryItem(it)
        }

        if (isMultiplyOrAdd(historyItem.question) && !isSameReversed(historyItem.question)) {
            val oppositeOrder = dao.getByQuestion(reverseQuestionOrder(historyItem.question)).map {
                mapMathQuestionEntityToLocalMathHistoryItem(it)
            }
            return questions + oppositeOrder
        }

        return questions
    }

}
