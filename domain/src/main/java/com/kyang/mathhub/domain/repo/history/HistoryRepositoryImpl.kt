package com.kyang.mathhub.domain.repo.history

import android.util.Log
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.domain.model.QuestionAnswerData
import com.kyang.mathhub.domain.repo.history.mapper.mapMathQuestionEntityToLocalMathHistoryItem
import com.kyang.mathhub.domain.repo.history.mapper.mapMathQuestionEquationToMathQuestionEntity
import com.kyang.mathhub.model.MathOperation
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

    private fun reverseQuestionOrder(input: String): String {
        val split = input.split(" ")
        if (split.size == 3) {
            return "${split[2]} ${split[1]} ${split[0]}"
        }
        return input
    }

    private fun isMultiplyOrAdd(input: String): Boolean {
        val split = input.split(" ")
        if (split.size == 3) {
            return split[1] == MathOperation.ADD.rep || split[1] == MathOperation.MULTIPLY.rep
        }
        return false
    }

    private fun isSameReversed(input: String): Boolean {
        val split = input.split(" ")
        if (split.size == 3) {
            return split[2] == split[0]
        }
        return false
    }
}
