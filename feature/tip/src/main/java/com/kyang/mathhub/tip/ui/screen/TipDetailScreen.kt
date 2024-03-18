package com.kyang.mathhub.tip.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kyang.mathhub.feature.tip.R

@Composable
fun TipDetailScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.tip_detail))
    }
}

@Preview(showBackground = true)
@Composable
private fun TipDetailScreenPreview() {
    com.kyang.mathhub.theme.MathHubTheme {
        TipDetailScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}