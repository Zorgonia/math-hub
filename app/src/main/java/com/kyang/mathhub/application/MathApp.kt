package com.kyang.mathhub.application

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.ui.screen.AnswerScreen
import com.kyang.mathhub.ui.screen.GameEndScreen
import com.kyang.mathhub.ui.screen.HomeScreen
import com.kyang.mathhub.ui.screen.OptionsScreen
import com.kyang.mathhub.ui.screen.QuestionScreen
import com.kyang.mathhub.ui.viewmodel.MathQuestionViewModel

enum class MathAppScreen() {
    MathQuestion,
    MathAnswer,
    MathOptions,
    MathGameEnd,
    Home
}

@Composable
fun MathApp(
    navController: NavHostController = rememberNavController()
) {
    val viewModel = hiltViewModel<MathQuestionViewModel>()
    val uiState by viewModel.uiState.collectAsState()


    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MathAppScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MathAppScreen.Home.name) {
                HomeScreen(
                    onNextClicked = { navController.navigate(MathAppScreen.MathOptions.name) { launchSingleTop = true } },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = MathAppScreen.MathOptions.name) {
                OptionsScreen(
                    onMinChange = { viewModel.setMin(it) },
                    onMaxChange = { viewModel.setMax(it) },
                    minVal = uiState.minNum,
                    maxVal = uiState.maxNum,
                    maxRound = uiState.maxRound.toString(),
                    onRoundChange = { viewModel.setMaxRound(it) },
                    endless = uiState.endless,
                    onEndlessChange = { viewModel.setEndless(it) },
                    onNextClicked = {
                        viewModel.resetGame()
                        navController.navigate(MathAppScreen.MathQuestion.name)
                    },
                    maxTime = uiState.maxTime.toString(),
                    onMaxTimeChange = { viewModel.setMaxTime(it) },
                    timeEnabled = uiState.timeEnabled,
                    timeEnabledChange = { viewModel.setTimeEnabled(it) },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = MathAppScreen.MathQuestion.name) {
                QuestionScreen(
                    first = uiState.first,
                    second = uiState.second,
                    answer = uiState.answer,
                    onAnswerChange = {viewModel.updateAnswer(it)},
                    onSubmit = {
                        viewModel.answerQuestion()
                    },
                    currTime = uiState.currTime,
                    maxTime = uiState.maxTime,
                    timeEnabled = uiState.timeEnabled,
                    modifier = Modifier.fillMaxSize()
                )
                if (uiState.submitted) {
                    navController.popBackStack()
                    navController.navigate(MathAppScreen.MathAnswer.name)
                }
            }

            composable(route = MathAppScreen.MathAnswer.name) {
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
                    realAnswer = viewModel.getRealAnswer(),
                    onNext = {
                        navController.popBackStack()
                        if (uiState.gameOver) {
                            navController.navigate(MathAppScreen.MathGameEnd.name)
                        } else {
                            viewModel.nextQuestion()
                            navController.navigate(MathAppScreen.MathQuestion.name)
                        }
                    },
                    submitted = uiState.submitted,
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = MathAppScreen.MathGameEnd.name) {
                GameEndScreen(
                    score = uiState.score,
                    round = uiState.maxRound,
                    onReset = {
                        viewModel.resetGame()
                        navController.popBackStack()
                        navController.navigate(MathAppScreen.MathQuestion.name)
                    },
                    onSettings = {
                        navController.popBackStack(MathAppScreen.MathOptions.name, inclusive = false)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}