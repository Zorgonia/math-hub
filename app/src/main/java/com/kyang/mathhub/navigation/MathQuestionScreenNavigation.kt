package com.kyang.mathhub.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.ui.screen.math.AnswerScreen
import com.kyang.mathhub.ui.screen.math.GameEndScreen
import com.kyang.mathhub.ui.screen.math.HomeScreen
import com.kyang.mathhub.ui.screen.math.OptionsScreen
import com.kyang.mathhub.ui.screen.math.QuestionScreen
import com.kyang.mathhub.ui.viewmodel.MathQuestionViewModel

@Composable
fun MathQuestionScreenNavigation() {
    val mathQuestionNavController = rememberNavController()
    val mathQuestionViewModel = hiltViewModel<MathQuestionViewModel>()
    val uiState by mathQuestionViewModel.uiState.collectAsState()

    NavHost(
        navController = mathQuestionNavController,
        startDestination = MathAppScreen.MathHome.route
    ) {
        composable(route = MathAppScreen.MathHome.route) {
            HomeScreen(
                onNextClicked = {
                    mathQuestionNavController.navigate(MathAppScreen.MathOptions.route) {
                        launchSingleTop = true
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = MathAppScreen.MathOptions.route) {

            OptionsScreen(
                onMinChange = { mathQuestionViewModel.setMin(it) },
                onMaxChange = { mathQuestionViewModel.setMax(it) },
                minVal = uiState.minNum,
                maxVal = uiState.maxNum,
                maxRound = uiState.maxRound.toString(),
                onRoundChange = { mathQuestionViewModel.setMaxRound(it) },
                endless = uiState.endless,
                onEndlessChange = { mathQuestionViewModel.setEndless(it) },
                onNextClicked = {
                    mathQuestionViewModel.resetGame()
                    mathQuestionNavController.navigate(MathAppScreen.MathQuestion.route)
                },
                maxTime = uiState.maxTime.toString(),
                onMaxTimeChange = { mathQuestionViewModel.setMaxTime(it) },
                timeEnabled = uiState.timeEnabled,
                timeEnabledChange = { mathQuestionViewModel.setTimeEnabled(it) },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = MathAppScreen.MathQuestion.route) {
            QuestionScreen(
                first = uiState.first,
                second = uiState.second,
                answer = uiState.answer,
                onAnswerChange = { mathQuestionViewModel.updateAnswer(it) },
                onSubmit = {
                    mathQuestionViewModel.answerQuestion()
                },
                currTime = uiState.currTime,
                maxTime = uiState.maxTime,
                timeEnabled = uiState.timeEnabled,
                modifier = Modifier.fillMaxSize()
            )
            if (uiState.submitted) {
                mathQuestionNavController.popBackStack()
                mathQuestionNavController.navigate(MathAppScreen.MathAnswer.route)
            }
        }

        composable(route = MathAppScreen.MathAnswer.route) {
            AnswerScreen(
                first = uiState.first,
                second = uiState.second,
                score = uiState.score,
                round = uiState.round,
                maxRound = uiState.maxRound,
                endless = uiState.endless,
                correct = uiState.correct,
                timeLeft = uiState.currTime,
                timeEnabled = uiState.timeEnabled,
                roundScore = uiState.roundScore,
                answer = uiState.answer,
                realAnswer = mathQuestionViewModel.getRealAnswer(),
                onNext = {
                    mathQuestionNavController.popBackStack()
                    if (uiState.gameOver) {
                        mathQuestionNavController.navigate(MathAppScreen.MathGameEnd.route)
                    } else {
                        mathQuestionViewModel.nextQuestion()
                        mathQuestionNavController.navigate(MathAppScreen.MathQuestion.route)
                    }
                },
                submitted = uiState.submitted,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = MathAppScreen.MathGameEnd.route) {
            GameEndScreen(
                score = uiState.score,
                round = uiState.maxRound,
                onReset = {
                    mathQuestionViewModel.resetGame()
                    mathQuestionNavController.popBackStack()
                    mathQuestionNavController.navigate(MathAppScreen.MathQuestion.route)
                },
                onSettings = {
                    mathQuestionNavController.popBackStack(
                        MathAppScreen.MathOptions.route,
                        inclusive = false
                    )
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}