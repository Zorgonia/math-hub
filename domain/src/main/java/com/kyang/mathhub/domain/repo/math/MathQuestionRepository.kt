package com.kyang.mathhub.domain.repo.math

interface MathQuestionRepository {
    fun getNewQuestion(min: Int, max: Int, old: Pair<Int, Int>): Pair<Int, Int>

    fun getAnswer(nums: Pair<Int, Int>): Int

    fun getScore(timeRemaining: Int, maxTime: Int, timed: Boolean): Int
}