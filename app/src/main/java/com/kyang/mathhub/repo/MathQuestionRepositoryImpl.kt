package com.kyang.mathhub.repo

import javax.inject.Inject
import kotlin.math.min
import kotlin.random.Random

class MathQuestionRepositoryImpl @Inject constructor(

): MathQuestionRepository {

    override fun getNewQuestion(min: Int, max: Int, old: Pair<Int, Int>): Pair<Int, Int> {
        var first = Random.nextInt(min, max)
        var second = Random.nextInt(min, max)
        if (max - min < 2) {
            return Pair(first, second)
        }
        while (first == old.first || first == old.second || second == old.first || second == old.second) {
            first = Random.nextInt(min, max)
            second = Random.nextInt(min, max)
        }
        return Pair(first, second)
    }

    override fun getAnswer(nums: Pair<Int, Int>): Int {
        return nums.first * nums.second
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