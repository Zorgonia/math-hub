package com.kyang.mathhub.ui.screen

import android.util.Log
import android.view.KeyEvent
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kyang.mathhub.R
import com.kyang.mathhub.ui.components.NumberInputTextField
import com.kyang.mathhub.ui.helper.isInt
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

        NumberInputTextField(value = answer,
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
            onAnswerChange = {},
            onSubmit = {},
            answer = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}