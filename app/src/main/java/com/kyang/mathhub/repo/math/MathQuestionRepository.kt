package com.kyang.mathhub.repo.math

import javax.inject.Inject

interface MathQuestionRepository {
    fun getNewQuestion(min: Int, max: Int, old: Pair<Int, Int>): Pair<Int, Int>

    fun getAnswer(nums: Pair<Int, Int>): Int

    fun getScore(timeRemaining: Int, maxTime: Int, timed: Boolean): Int
}