package com.kyang.mathhub.domain.repo.math

import com.kyang.mathhub.mathquestion.model.MathOperation
import com.kyang.mathhub.mathquestion.model.MathQuestion
import com.kyang.mathhub.mathquestion.model.MathQuestionEquation
import com.kyang.mathhub.mathquestion.model.MathQuestionSimple
import javax.inject.Inject
import kotlin.math.min
import kotlin.random.Random

class MathQuestionRepositoryImpl @Inject constructor(

): MathQuestionRepository {

    override fun getNewQuestion(min: Int, max: Int, old: MathQuestion): MathQuestionEquation {
        var first = Random.nextInt(min, max + 1)
        var second = Random.nextInt(min, max + 1)
        var newQuestion = MathQuestionEquation(MathQuestionSimple(first), MathQuestionSimple(second), MathOperation.MULTIPLY)
        if (max - min < 3) {
            return newQuestion
        }
        var i = 0
        while (i < 10 && newQuestion == old) {
            first = Random.nextInt(min, max + 1)
            second = Random.nextInt(min, max + 1)
            newQuestion = MathQuestionEquation(MathQuestionSimple(first), MathQuestionSimple(second), MathOperation.MULTIPLY)
            i += 1
        }
        return newQuestion
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