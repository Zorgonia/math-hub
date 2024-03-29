package com.kyang.mathhub.mathquestion.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.navigation.NavHostController
import com.kyang.mathhub.feature.mathquestion.R
import com.kyang.mathhub.mathquestion.model.MathQuestionUiState
import com.kyang.mathhub.mathquestion.navigation.MathAppScreen
import com.kyang.mathhub.mathquestion.ui.component.QuestionSettingCheckOption
import com.kyang.mathhub.mathquestion.ui.component.QuestionSettingNumberField
import com.kyang.mathhub.mathquestion.viewmodel.MathQuestionViewModel
import com.kyang.mathhub.theme.MathHubTheme

@Composable
fun OptionsPage(
    uiState: MathQuestionUiState,
    mathQuestionViewModel: MathQuestionViewModel,
    mathQuestionNavController: NavHostController,
) {
    OptionsScreen(
        onMinChange = { mathQuestionViewModel.setMin(it) },
        onMaxChange = { mathQuestionViewModel.setMax(it) },
        minVal = uiState.minNum,
        maxVal = uiState.maxNum,
        maxRound = uiState.maxRound,
        onRoundChange = { mathQuestionViewModel.setMaxRound(it) },
        endless = uiState.endless,
        onEndlessChange = { mathQuestionViewModel.setEndless(it) },
        onNextClicked = {
            mathQuestionViewModel.resetGame()
            mathQuestionNavController.navigate(MathAppScreen.MathQuestion.route)
        },
        maxTime = uiState.maxTime,
        onMaxTimeChange = { mathQuestionViewModel.setMaxTime(it) },
        timeEnabled = uiState.timeEnabled,
        timeEnabledChange = { mathQuestionViewModel.setTimeEnabled(it) },
        modifier = Modifier.fillMaxSize(),
        readyToPlay = uiState.readyToPlay,
    )
}

@Composable
fun OptionsScreen(
    onMinChange: (String) -> Unit,
    onMaxChange: (String) -> Unit,
    maxRound: String,
    onRoundChange: (String) -> Unit,
    endless: Boolean,
    onEndlessChange: (Boolean) -> Unit,
    maxTime: String,
    onMaxTimeChange: (String) -> Unit,
    timeEnabled: Boolean,
    timeEnabledChange: (Boolean) -> Unit,
    minVal: String,
    maxVal: String,
    readyToPlay: Boolean,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {

            QuestionSettingNumberField(
                title = R.string.question_setting_min_num,
                value = minVal,
                onValueChange = onMinChange,
            )

            QuestionSettingNumberField(
                title = R.string.question_setting_max_num,
                value = maxVal,
                onValueChange = onMaxChange,
            )

        }

        QuestionSettingCheckOption(
            optionTitle = R.string.question_setting_endless,
            checked = endless,
            onCheckChange = onEndlessChange,
            value = maxRound,
            onValueChange = onRoundChange,
            inputTitle = R.string.question_setting_num_rounds,
            showOnEnabled = false,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        QuestionSettingCheckOption(
            optionTitle = R.string.question_setting_use_round_timer,
            checked = timeEnabled,
            onCheckChange = timeEnabledChange,
            value = maxTime,
            onValueChange = onMaxTimeChange,
            inputTitle = R.string.question_setting_round_timer,
            showOnEnabled = true,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        Button(onClick = onNextClicked, enabled = readyToPlay) {
            Text(
                stringResource(id = R.string.start_cta),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.wrapContentHeight(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuestionSettingScreenPreview() {
    MathHubTheme {
        OptionsScreen(
            onMinChange = {},
            onMaxChange = {},
            onNextClicked = {},
            maxVal = "",
            minVal = "",
            onEndlessChange = {},
            onRoundChange = {},
            endless = false,
            maxRound = "1",
            maxTime = "10",
            onMaxTimeChange = {},
            timeEnabledChange = {},
            timeEnabled = true,
            readyToPlay = false,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
