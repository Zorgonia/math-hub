package com.kyang.mathhub.mathquestion.model

import kotlin.math.pow


sealed class MathQuestion() {
    abstract fun calculate(): Int
    class MathQuestionEquation(val first: MathQuestion, val second: MathQuestion, val operation: MathOperation): MathQuestion() {
        override fun calculate(): Int {
            val firstCalculated = first.calculate()
            val secondCalculated = second.calculate()
            return when(operation) {
                MathOperation.ADD -> {
                    firstCalculated + secondCalculated
                }

                MathOperation.SUBTRACT -> {
                    firstCalculated - secondCalculated
                }

                MathOperation.DIVIDE -> {
                    firstCalculated / secondCalculated
                }

                MathOperation.MULTIPLY -> {
                    firstCalculated * secondCalculated
                }
                MathOperation.EXPONENT -> {
                    firstCalculated.toDouble().pow(secondCalculated.toDouble()).toInt()
                }
            }
        }
    }
    class MathQuestionSimple(val value: Int): MathQuestion() {
        override fun calculate(): Int {
            return value
        }
    }
}

enum class MathOperation {
    ADD,
    SUBTRACT,
    DIVIDE,
    MULTIPLY,
    EXPONENT
}