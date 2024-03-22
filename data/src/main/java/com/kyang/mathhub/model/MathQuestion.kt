package com.kyang.mathhub.mathquestion.model

import kotlin.math.pow


sealed class MathQuestion() {
    abstract fun calculate(): Int

}

class MathQuestionEquation(
    val first: MathQuestion,
    val second: MathQuestion,
    val operation: MathOperation
) : MathQuestion() {
    override fun calculate(): Int {
        val firstCalculated = first.calculate()
        val secondCalculated = second.calculate()
        return when (operation) {
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

    override fun equals(other: Any?): Boolean {
        return other is MathQuestionEquation && compareQuestion(other)
    }

    private fun compareQuestion(input: MathQuestionEquation): Boolean {
        if (this.operation != input.operation) {
            return false
        }
        return when (this.operation) {
            MathOperation.ADD, MathOperation.MULTIPLY -> {
                (input.first == this.first && input.second == this.second)
                        || (input.first == this.second && input.second == this.first)
            }

            MathOperation.SUBTRACT, MathOperation.DIVIDE, MathOperation.EXPONENT -> {
                (input.first == this.first && input.second == this.second)
            }
        }
    }

    override fun hashCode(): Int {
        var result = first.hashCode()
        result = 31 * result + second.hashCode()
        result = 31 * result + operation.hashCode()
        return result
    }

}

class MathQuestionSimple(val value: Int) : MathQuestion() {
    override fun calculate(): Int {
        return value
    }

    override fun toString(): String {
        return "$value"
    }

}

enum class MathOperation {
    ADD,
    SUBTRACT,
    DIVIDE,
    MULTIPLY,
    EXPONENT
}