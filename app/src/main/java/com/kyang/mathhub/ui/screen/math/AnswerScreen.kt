package com.kyang.mathhub.ui.screen.math

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kyang.mathhub.R
import com.kyang.mathhub.ui.theme.MathHubTheme

@Composable
fun AnswerScreen(
    first: Int,
    second: Int,
    score: Int,
    round: Int,
    maxRound: Int,
    endless: Boolean,
    correct: Boolean,
    answer: String,
    timeLeft: Int,
    timeEnabled: Boolean,
    roundScore: Int,
    realAnswer: String,
    onNext: () -> Unit,
    submitted: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (submitted) {
            Text(text = stringResource(id = if (correct) R.string.question_correct else R.string.question_incorrect), modifier = Modifier.padding(bottom = 16.dp))
            Text(
                text = stringResource(
                    id = R.string.question_multiplication_answer,
                    first,
                    second,
                    realAnswer
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (!correct) {
                Text(text = stringResource(id = R.string.question_your_answer, answer), modifier = Modifier.padding(bottom = 16.dp))
            }

            Spacer(modifier = Modifier.padding(16.dp))

            if (timeEnabled) {
                Text(
                    text = stringResource(
                        id = R.string.question_time_left,
                        timeLeft
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = stringResource(
                        id = R.string.question_score_round,
                        roundScore
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Text(
                text = stringResource(
                    id = R.string.question_score,
                    score
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (endless) {
                Text(
                    text = stringResource(
                        id = R.string.question_round_endless,
                        round
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            } else {
                Text(
                    text = stringResource(
                        id = R.string.question_round_out_of,
                        round,
                        maxRound
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            
            Spacer(modifier = Modifier.padding(16.dp))

            Button(onClick = onNext) {
                Text(
                    stringResource(id = R.string.next_cta),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.wrapContentHeight()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnswerScreenPreview() {
    MathHubTheme {
        AnswerScreen(
            first = 1,
            second = 1,
            score = 0,
            round = 1,
            maxRound = 10,
            endless = true,
            correct = false,
            realAnswer = "1",
            onNext = {},
            answer = "100",
            timeLeft = 1,
            timeEnabled = false,
            roundScore = 20,
            submitted = true,
            modifier = Modifier.fillMaxSize()
        )
    }
}