package com.kyang.mathhub.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.kyang.mathhub.ui.components.NumberInputTextField
import com.kyang.mathhub.ui.theme.MathHubTheme
import kotlin.math.max

@Composable
fun OptionsScreen(
    onMinChange: (String) -> Unit,
    onMaxChange: (String) -> Unit,
    minVal: String,
    maxVal: String,
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            NumberInputTextField(
                value = minVal, onValueChange = onMinChange,
                label = {
                    Text(stringResource(id = R.string.question_setting_min_num))
                },
                onSubmit = { },
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentHeight()
            )

            NumberInputTextField(
                value = maxVal, onValueChange = onMaxChange,
                label = {
                    Text(stringResource(id = R.string.question_setting_max_num))
                },
                onSubmit = { },
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentHeight()
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 32.dp))
        Button(onClick = onNextClicked) {
            Text(
                stringResource(id = R.string.start_cta),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.wrapContentHeight()
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
            modifier = Modifier.fillMaxSize()
        )
    }
}