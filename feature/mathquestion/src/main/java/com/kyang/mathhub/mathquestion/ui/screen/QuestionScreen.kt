package com.kyang.mathhub.mathquestion.ui.screen

import android.view.KeyEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kyang.mathhub.feature.mathquestion.R
import com.kyang.mathhub.mathquestion.navigation.MathAppScreen
import com.kyang.mathhub.mathquestion.viewmodel.MathQuestionViewModel
import com.kyang.mathhub.theme.MathHubTheme

@Composable
fun QuestionPage(
    uiState: com.kyang.mathhub.mathquestion.model.MathQuestionUiState,
    mathQuestionViewModel: MathQuestionViewModel,
    mathQuestionNavController: NavHostController
) {
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

@Composable
private fun QuestionScreen(
    first: Int,
    second: Int,
    answer: String,
    onAnswerChange: (String) -> Unit,
    currTime: Int,
    maxTime: Int,
    timeEnabled: Boolean,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.question_multiplication, first, second)
        )

        Text(text = stringResource(id = R.string.question_equals))

        com.kyang.mathhub.mathquestion.ui.component.NumberInputTextField(value = answer,
            onValueChange = onAnswerChange,
            onSubmit = onSubmit,
            label = {
                Text(text = stringResource(id = R.string.question_answer))
            },
            modifier = Modifier
                .onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                        focusRequester.freeFocus()
                        onSubmit()
                        return@onKeyEvent true
                    }
                    false
                }
                .focusRequester(focusRequester)
                .padding(vertical = 16.dp))

        Button(onClick = onSubmit) {
            Text(
                stringResource(id = R.string.question_answer),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.wrapContentHeight()
            )
        }

        if (timeEnabled) {
            CircularProgressIndicator(
                progress = { (currTime.toFloat() / maxTime) },
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }

        //TODO figure out if there's a better way
        LaunchedEffect(first) {
            focusRequester.requestFocus()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionScreenPreview() {
    MathHubTheme {
        QuestionScreen(
            first = 1,
            second = 1,
            currTime = 3,
            maxTime = 10,
            timeEnabled = false,
            onAnswerChange = {},
            onSubmit = {},
            answer = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}