package com.kyang.mathhub.domain.repo.math

import com.kyang.mathhub.model.MathOperation
import com.kyang.mathhub.model.MathQuestion
import com.kyang.mathhub.model.MathQuestionEquation
import com.kyang.mathhub.model.MathQuestionSimple
import javax.inject.Inject
import kotlin.math.min
import kotlin.random.Random

class MathQuestionRepositoryImpl @Inject constructor(

) : MathQuestionRepository {

    override fun getNewQuestion(min: Int, max: Int, old: List<MathQuestion>): MathQuestionEquation {
        var newQuestion = generateQuestion(min, max)
        if (max - min < 3) {
            return newQuestion
        }
        var i = 0
        while (i < 10 && old.any { it == newQuestion }) {
            newQuestion = generateQuestion(min, max)
            i += 1
        }
        if (newQuestion == old.lastOrNull()) {
            newQuestion = generateQuestion(min, max)
        }
        return newQuestion
    }

    private fun generateQuestion(min: Int, max: Int): MathQuestionEquation {
        return MathQuestionEquation(
            MathQuestionSimple(Random.nextInt(min, max + 1)),
            MathQuestionSimple(Random.nextInt(min, max + 1)),
            MathOperation.MULTIPLY,
        )
    }

    override fun getAnswer(equation: MathQuestion): Int {
        return equation.calculate()
    }

    //TODO use cases
    override fun getScore(timeRemaining: Int, maxTime: Int, timed: Boolean): Int {
        if (!timed) return 1
        if (maxTime <= 2) {
            return 100
        }
        return min((((timeRemaining) * 100) / (maxTime - 1)), 100)
    }

}
