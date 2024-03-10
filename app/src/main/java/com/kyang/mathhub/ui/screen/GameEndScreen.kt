package com.kyang.mathhub.ui.screen

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
import com.kyang.mathhub.R
import com.kyang.mathhub.ui.theme.MathHubTheme

@Composable
fun GameEndScreen(
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
                round
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