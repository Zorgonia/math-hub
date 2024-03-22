package com.kyang.mathhub.domain.repo.math


import com.kyang.mathhub.mathquestion.model.MathQuestion
import com.kyang.mathhub.mathquestion.model.MathQuestionEquation

interface MathQuestionRepository {
    fun getNewQuestion(min: Int, max: Int, old: MathQuestion): MathQuestionEquation

    fun getAnswer(equation: MathQuestion): Int

    fun getScore(timeRemaining: Int, maxTime: Int, timed: Boolean): Int
}