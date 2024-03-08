package com.kyang.mathhub.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kyang.mathhub.ui.data.MathQuestionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MathQuestionViewModel @Inject constructor(): ViewModel() {

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

    fun resetQuestion() {
        _uiState.update { curr ->
            curr.copy(
                first = Random.nextInt(curr.minNum.toInt(), curr.maxNum.toInt()),
                second = Random.nextInt(curr.minNum.toInt(), curr.maxNum.toInt()),
                answer = "",
                submitted = false,
                correct = false
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
        try {
            _uiState.update { curr ->
                val intAns = curr.answer.toInt()
                if (intAns == curr.first * curr.second) {
                    curr.copy(
                        submitted = true,
                        correct = true
                    )
                } else {
                    curr.copy(
                        submitted = true,
                        correct = false
                    )
                }
            }
        } catch (e: Exception) {
            _uiState.update { curr ->
                curr.copy(
                    submitted = true,
                    correct = false
                )
            }
        }
    }

}