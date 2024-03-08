package com.kyang.mathhub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
    correct: Boolean,
    answer: String,
    realAnswer: String,
    onNext: () -> Unit,
    submitted: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (submitted) {
            Text(text = stringResource(id = if (correct) R.string.question_correct else R.string.question_incorrect))
            Text(
                text = stringResource(
                    id = R.string.question_multiplication_answer,
                    first,
                    second,
                    realAnswer
                )
            )

            if (!correct) {
                Text(text = stringResource(id = R.string.question_your_answer, answer))
            }

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
private fun QuestionScreenScreenPreview() {
    MathHubTheme {
        AnswerScreen(
            first = 1,
            second = 1,
            correct = false,
            realAnswer = "1",
            onNext = {},
            answer = "100",
            submitted = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}