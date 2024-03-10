package com.kyang.mathhub.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kyang.mathhub.ui.data.MathQuestionUiState
import com.kyang.mathhub.ui.repo.MathQuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MathQuestionViewModel @Inject constructor(
    private val mathQuestionRepository: MathQuestionRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MathQuestionUiState())
    val uiState: StateFlow<MathQuestionUiState> = _uiState.asStateFlow()

    fun setMin(min: String) {
        try {
            val check = min.toInt()
            _uiState.update { curr ->
                curr.copy(
                    minNum = min
                )
            }
        } catch (e: Exception) {
            Log.e("com.kyang.mathhub", "min must be int")
        }
    }

    fun setMax(max: String) {
        try {
            val check = max.toInt()
            _uiState.update { curr ->
                curr.copy(
                    maxNum = max
                )
            }
        } catch (e: Exception) {
            Log.e("com.kyang.mathhub", "max must be int")
        }
    }

    fun setEndless(endless: Boolean) {
        _uiState.update { curr ->
            curr.copy(
                endless = endless
            )
        }
    }

    fun setMaxRound(maxRound: String) {
        try {
            val check = maxRound.toInt()
            _uiState.update { curr ->
                curr.copy(
                    maxRound = check
                )
            }
        } catch (e: Exception) {
            Log.e("com.kyang.mathhub", "maxRound must be int")
        }
    }

    fun nextQuestion() {
        _uiState.update { curr ->
            val newPair = mathQuestionRepository.getNewQuestion(curr.minNum.toInt(), curr.maxNum.toInt(), Pair(curr.first, curr.second))
            curr.copy(
                first = newPair.first,
                second = newPair.second,
                answer = "",
                submitted = false,
                correct = false,
                round = curr.round + 1
            )
        }
    }

    fun resetGame() {
        _uiState.update { curr ->
            val newPair = mathQuestionRepository.getNewQuestion(curr.minNum.toInt(), curr.maxNum.toInt(), Pair(curr.first, curr.second))
            curr.copy(
                first = newPair.first,
                second = newPair.second,
                answer = "",
                submitted = false,
                correct = false,
                round = 1,
                score = 0,
                gameOver = false
            )
        }
    }

    fun getRealAnswer(): String = "${_uiState.value.first * _uiState.value.second}"

    fun updateAnswer(ans: String) {
        _uiState.update { curr ->
            curr.copy(
                answer = ans
            )
        }
    }

    fun answerQuestion() {
        val gameOver = uiState.value.maxRound == uiState.value.round && !uiState.value.endless
        try {
            _uiState.update { curr ->
                if (curr.answer.isNotEmpty() && curr.answer.toInt() == mathQuestionRepository.getAnswer(Pair(curr.first, curr.second))) {
                    curr.copy(
                        submitted = true,
                        correct = true,
                        score = curr.score + 1,
                        gameOver = gameOver
                    )
                } else {
                    curr.copy(
                        submitted = true,
                        correct = false,
                        gameOver = gameOver
                    )
                }

            }
        } catch (e: Exception) {
            Log.d("test", "exception $e")
            _uiState.update { curr ->
                curr.copy(
                    submitted = true,
                    correct = false
                )
            }
        }
    }

}