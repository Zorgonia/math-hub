package com.kyang.mathhub.ui.screen.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kyang.mathhub.R
import com.kyang.mathhub.ui.theme.MathHubTheme

@Composable
fun HistoryHomeScreen(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.history_home_header))
        Button(onClick = onClick) {
            Text(text = "click me")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryHomeScreenPreview() {
    MathHubTheme {
        HistoryHomeScreen(
            onClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}