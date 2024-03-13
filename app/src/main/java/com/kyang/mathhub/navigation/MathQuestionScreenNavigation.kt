package com.kyang.mathhub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.ui.screen.math.AnswerPage
import com.kyang.mathhub.ui.screen.math.GameEndPage
import com.kyang.mathhub.ui.screen.math.HomePage
import com.kyang.mathhub.ui.screen.math.OptionsPage
import com.kyang.mathhub.ui.screen.math.QuestionPage
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
            HomePage(mathQuestionNavController = mathQuestionNavController)
        }

        composable(route = MathAppScreen.MathOptions.route) {
            OptionsPage(
                uiState = uiState,
                mathQuestionViewModel = mathQuestionViewModel,
                mathQuestionNavController = mathQuestionNavController
            )

        }

        composable(route = MathAppScreen.MathQuestion.route) {
            QuestionPage(
                uiState = uiState,
                mathQuestionViewModel = mathQuestionViewModel,
                mathQuestionNavController = mathQuestionNavController
            )
        }

        composable(route = MathAppScreen.MathAnswer.route) {
            AnswerPage(
                uiState = uiState,
                mathQuestionViewModel = mathQuestionViewModel,
                mathQuestionNavController = mathQuestionNavController
            )
        }


        composable(route = MathAppScreen.MathGameEnd.route) {
            GameEndPage(
                uiState = uiState,
                mathQuestionViewModel = mathQuestionViewModel,
                mathQuestionNavController = mathQuestionNavController
            )
        }
    }
}