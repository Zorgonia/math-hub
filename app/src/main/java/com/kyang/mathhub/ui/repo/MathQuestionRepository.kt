package com.kyang.mathhub.ui.repo

import javax.inject.Inject

interface MathQuestionRepository {
    fun getNewQuestion(min: Int, max: Int, old: Pair<Int, Int>): Pair<Int, Int>

    fun getAnswer(nums: Pair<Int, Int>): Int
}