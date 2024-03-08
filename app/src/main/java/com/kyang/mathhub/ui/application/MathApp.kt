package com.kyang.mathhub.ui.application

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.ui.screen.AnswerScreen
import com.kyang.mathhub.ui.screen.HomeScreen
import com.kyang.mathhub.ui.screen.OptionsScreen
import com.kyang.mathhub.ui.screen.QuestionScreen
import com.kyang.mathhub.ui.viewmodel.MathQuestionViewModel
import java.lang.StackWalker.Option

enum class MathAppScreen() {
    MathQuestion,
    MathAnswer,
    MathOptions,
    Home
}

@Composable
fun MathApp(
    viewModel: MathQuestionViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold() { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MathAppScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MathAppScreen.Home.name) {
                HomeScreen(
                    onNextClicked = { navController.navigate(MathAppScreen.MathOptions.name) },
                    modifier = Modifier.fillMaxSize()
                )
            }

            composable(route = MathAppScreen.MathOptions.name) {
                OptionsScreen(
                    onMinChange = { viewModel.setMin(it) },
                    onMaxChange = { viewModel.setMax(it) },
                    minVal = uiState.minNum,
                    maxVal = uiState.maxNum,
                    onNextClicked = {
                        viewModel.resetQuestion()
                        navController.navigate(MathAppScreen.MathQuestion.name)
                    },
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
                        navController.navigate(MathAppScreen.MathAnswer.name)
                    }
                )
            }

            composable(route = MathAppScreen.MathAnswer.name) {
                AnswerScreen(
                    first = uiState.first,
                    second = uiState.second,
                    correct = uiState.correct,
                    answer = uiState.answer,
                    realAnswer = viewModel.getRealAnswer(),
                    onNext = {
                        navController.navigate(MathAppScreen.MathQuestion.name)
                        viewModel.resetQuestion()
                    },
                    submitted = uiState.submitted
                )
            }


        }
    }
}