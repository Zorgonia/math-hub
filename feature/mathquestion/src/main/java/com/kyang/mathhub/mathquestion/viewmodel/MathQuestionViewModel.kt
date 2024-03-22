package com.kyang.mathhub.mathquestion.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyang.mathhub.domain.repo.math.MathQuestionRepository
import com.kyang.mathhub.mathquestion.helper.isInt
import com.kyang.mathhub.mathquestion.model.MathOperation
import com.kyang.mathhub.mathquestion.model.MathQuestionEquation
import com.kyang.mathhub.mathquestion.model.MathQuestionSimple
import com.kyang.mathhub.mathquestion.model.MathQuestionUiState
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

    private var lastQuestion: MathQuestionEquation =
        MathQuestionEquation(MathQuestionSimple(-1), MathQuestionSimple(-1), MathOperation.MULTIPLY)

    fun setMin(min: String) {
        if (min.isEmpty() || min.isInt()) {
            _uiState.update { curr ->
                curr.copy(
                    minNum = min,
                    readyToPlay = min.isNotEmpty()
                )
            }
        }
    }

    fun setMax(max: String) {
        if (max.isEmpty() || max.isInt()) {
            _uiState.update { curr ->
                curr.copy(
                    maxNum = max,
                    readyToPlay = max.isNotEmpty()
                )
            }
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
        if (time.isEmpty() || time.isInt()) {
            _uiState.update { curr ->
                curr.copy(
                    maxTime = time,
                    readyToPlay = !curr.timeEnabled || time.isNotEmpty()
                )
            }
        }
    }

    fun setMaxRound(maxRound: String) {
        if (maxRound.isEmpty() || maxRound.isInt()) {
            _uiState.update { curr ->
                curr.copy(
                    maxRound = maxRound,
                    readyToPlay = curr.endless || maxRound.isNotEmpty()
                )
            }
        }
    }

    fun nextQuestion() {
        _uiState.update { curr ->
            lastQuestion = mathQuestionRepository.getNewQuestion(
                curr.minNum.toInt(),
                curr.maxNum.toInt(),
                lastQuestion
            )
            curr.copy(
                first = lastQuestion.first.calculate(),
                second = lastQuestion.second.calculate(),
                answer = "",
                submitted = false,
                correct = false,
                roundScore = 0,
                round = curr.round + 1,
                currTime = if (curr.maxTime.isEmpty()) 99 else curr.maxTime.toInt(),
                roundProgress = 1f
            )
        }
        startTimer()
    }

    fun resetGame() {
        _uiState.update { curr ->
            lastQuestion = mathQuestionRepository.getNewQuestion(
                curr.minNum.toInt(),
                curr.maxNum.toInt(),
                lastQuestion
            )
            curr.copy(
                first = lastQuestion.first.calculate(),
                second = lastQuestion.second.calculate(),
                answer = "",
                submitted = false,
                correct = false,
                round = 1,
                score = 0,
                roundScore = 0,
                currTime = if (curr.maxTime.isEmpty()) 99 else curr.maxTime.toInt(),
                gameOver = false,
                roundProgress = 1f
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
        val gameOver = uiState.value.maxRound.toIntOrNull() == uiState.value.round && !uiState.value.endless
        resetTimer()
        try {
            _uiState.update { curr ->
                val roundScore = mathQuestionRepository.getScore(
                    timeRemaining = curr.currTime,
                    maxTime = curr.maxTime.toInt(),
                    timed = curr.timeEnabled
                )
                if (curr.answer.isNotEmpty() && curr.answer.toInt() == mathQuestionRepository.getAnswer(
                        lastQuestion
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
                val upd = curr.currTime - 1
                curr.copy(
                    currTime = upd,
                    roundProgress = getRoundProgress(upd)
                )
            }
        }
    }

    private fun getRoundProgress(time: Int): Float {
        uiState.value.maxTime.toIntOrNull()?.let { maxTime ->
            return time.toFloat() / maxTime
        }
        return 0f
    }

    private fun startTimer() {
        timerJob?.cancel()
        if (!_uiState.value.timeEnabled) return
        _uiState.value.maxTime.toIntOrNull()?.let { maxTime ->
            timerJob = viewModelScope.launch {
                for (i in 0 until maxTime) {
                    delay(1000)
                    decrementQuestionTimer()
                }
            }
        }

    }

    private fun resetTimer() {
        timerJob?.cancel()
    }

}