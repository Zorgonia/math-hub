package com.kyang.mathhub.mathquestion.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kyang.mathhub.feature.mathquestion.R
import com.kyang.mathhub.mathquestion.model.MathQuestionUiState
import com.kyang.mathhub.mathquestion.navigation.MathAppScreen
import com.kyang.mathhub.mathquestion.viewmodel.MathQuestionViewModel
import com.kyang.mathhub.theme.MathHubTheme

@Composable
fun GameEndPage(
    uiState: MathQuestionUiState,
    mathQuestionViewModel: MathQuestionViewModel,
    mathQuestionNavController: NavHostController
) {
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

@Composable
private fun GameEndScreen(
    score: Int,
    round: Int,
    onReset: () -> Unit,
    onSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(id = R.string.game_over),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(
                id = R.string.game_over_you_scored,
                score,
                round * 100
            ),
            modifier = Modifier.padding(16.dp)
        )

        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onReset) {
                Text(text = stringResource(id = R.string.game_over_play_again))
            }

            Button(onClick = onSettings) {
                Text(text = stringResource(id = R.string.game_over_back_to_settings))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GameEndScreenPreview() {
    MathHubTheme {
        GameEndScreen(
            onReset = {},
            onSettings = {},
            score = 0,
            round = 10,
            modifier = Modifier.fillMaxSize()
        )
    }
}