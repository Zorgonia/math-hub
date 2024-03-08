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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kyang.mathhub.R
import com.kyang.mathhub.ui.theme.MathHubTheme

@Composable
fun QuestionScreen(
    first: Int,
    second: Int,
    answer: String,
    onAnswerChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.question_multiplication, first, second)
        )

        Text(text = stringResource(id = R.string.question_equals))

        TextField(value = answer, onValueChange = onAnswerChange, label = {
            Text(text = stringResource(id = R.string.question_answer))
        }, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(onGo = { onSubmit() })
        )

        Button(onClick = onSubmit) {
            Text(
                stringResource(id = R.string.question_answer),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.wrapContentHeight()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionScreenScreenPreview() {
    MathHubTheme {
        QuestionScreen(
            first = 1,
            second = 1,
            onAnswerChange = {},
            onSubmit = {},
            answer = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}