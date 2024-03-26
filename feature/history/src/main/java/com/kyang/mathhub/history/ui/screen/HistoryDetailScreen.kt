package com.kyang.mathhub.history.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.feature.history.R
import com.kyang.mathhub.history.model.HistoryDetailUiState
import com.kyang.mathhub.history.ui.component.HistoryHomeHeader
import com.kyang.mathhub.history.ui.component.HistoryItemRowComponent
import com.kyang.mathhub.history.viewmodel.HistoryViewModel
import com.kyang.mathhub.theme.MathHubTheme

@Composable
fun HistoryDetailPage(
    uiState: HistoryDetailUiState,
    viewModel: HistoryViewModel,
    navController: NavHostController,
) {
    HistoryDetailScreen(
        selected = uiState.question,
        items = uiState.historyItems,
        answered = uiState.timesAnswered,
        answeredCorrect = uiState.timesCorrect,
        answeredIncorrect = uiState.timesIncorrect,
        modifier = Modifier.fillMaxSize(),
        correctAnswer = uiState.correctAnswer,
        correctAnswerPercent = uiState.answerPercent
    )
}

@Composable
private fun HistoryDetailScreen(
    selected: LocalMathHistoryItem?,
    items: List<LocalMathHistoryItem>,
    answered: Int,
    correctAnswer: String,
    correctAnswerPercent: String,
    answeredCorrect: Int,
    answeredIncorrect: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (selected == null) {
            Text(text = stringResource(id = R.string.loading))
        } else {
            Text(
                text = selected.question,
                fontSize = 24.sp,
                modifier = Modifier.padding(vertical = 12.dp),
            )
            Text(
                text = stringResource(
                    id = R.string.history_detail_correct_answer,
                    correctAnswer,
                ),
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Text(
                text = stringResource(
                    id = R.string.history_detail_correct_percent,
                    correctAnswerPercent,
                ),
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Text(
                text = stringResource(id = R.string.history_detail_times_answered, answered),
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Text(
                text = stringResource(
                    id = R.string.history_detail_answered_correct,
                    answeredCorrect,
                ),
                modifier = Modifier.padding(bottom = 12.dp),
            )
            Text(
                text = stringResource(
                    id = R.string.history_detail_answered_incorrect,
                    answeredIncorrect,
                ),
                modifier = Modifier.padding(bottom = 12.dp),
            )

            HistoryHomeHeader()
            LazyColumn {
                items(items, key = { it.id }) { item ->
                    HistoryItemRowComponent(
                        data = item,
                        onClick = { },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryDetailScreenPreview() {
    MathHubTheme {
        HistoryDetailScreen(
            selected = null,
            items = listOf(),
            answeredIncorrect = 0,
            answered = 0,
            answeredCorrect = 0,
            correctAnswerPercent = "",
            correctAnswer = "",
            modifier = Modifier.fillMaxSize(),
        )
    }
}
