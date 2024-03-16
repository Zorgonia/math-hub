package com.kyang.mathhub.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyang.mathhub.data.MathQuestionUiState
import com.kyang.mathhub.repo.math.MathQuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MathQuestionViewModel @Inject constructor(
    private val mathQuestionRepository: MathQuestionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MathQuestionUiState())
    val uiState: StateFlow<MathQuestionUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

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

    fun setTimeEnabled(timeEnabled: Boolean) {
        _uiState.update { curr ->
            curr.copy(
                timeEnabled = timeEnabled
            )
        }
    }

    fun setMaxTime(time: String) {
        try {
            val check = time.toInt()
            _uiState.update { curr ->
                curr.copy(
                    maxTime = check
                )
            }
        } catch (e: Exception) {
            Log.e("com.kyang.mathhub", "maxTime must be int")
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
            val newPair = mathQuestionRepository.getNewQuestion(
                curr.minNum.toInt(),
                curr.maxNum.toInt(),
                Pair(curr.first, curr.second)
            )
            curr.copy(
                first = newPair.first,
                second = newPair.second,
                answer = "",
                submitted = false,
                correct = false,
                roundScore = 0,
                round = curr.round + 1,
                currTime = curr.maxTime
            )
        }
        startTimer()
    }

    fun resetGame() {
        _uiState.update { curr ->
            val newPair = mathQuestionRepository.getNewQuestion(
                curr.minNum.toInt(),
                curr.maxNum.toInt(),
                Pair(curr.first, curr.second)
            )
            curr.copy(
                first = newPair.first,
                second = newPair.second,
                answer = "",
                submitted = false,
                correct = false,
                round = 1,
                score = 0,
                roundScore = 0,
                currTime = curr.maxTime,
                gameOver = false
            )
        }
        startTimer()
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
        resetTimer()
        try {
            _uiState.update { curr ->
                val roundScore = mathQuestionRepository.getScore(
                    timeRemaining = curr.currTime,
                    maxTime = curr.maxTime,
                    timed = curr.timeEnabled
                )
                if (curr.answer.isNotEmpty() && curr.answer.toInt() == mathQuestionRepository.getAnswer(
                        Pair(curr.first, curr.second)
                    )
                ) {
                    curr.copy(
                        submitted = true,
                        correct = true,
                        roundScore = roundScore,
                        score = curr.score + roundScore,
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

    private fun decrementQuestionTimer() {
        if (_uiState.value.currTime == 1) {
            answerQuestion()
        } else {
            _uiState.update { curr ->
                curr.copy(
                    currTime = curr.currTime - 1
                )
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        if (!_uiState.value.timeEnabled) return
        timerJob = viewModelScope.launch {
            for (i in 0 until _uiState.value.maxTime) {
                delay(1000)
                decrementQuestionTimer()
            }
        }
    }

    private fun resetTimer() {
        timerJob?.cancel()
    }

}