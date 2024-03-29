package com.kyang.mathhub.mathquestion.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.kyang.mathhub.feature.mathquestion.R
import com.kyang.mathhub.mathquestion.navigation.MathAppScreen
import com.kyang.mathhub.theme.MathHubTheme

@Composable
fun HomePage(
    mathQuestionNavController: NavHostController,
) {
    HomeScreen(
        onNextClicked = {
            mathQuestionNavController.navigate(MathAppScreen.MathOptions.route) {
                launchSingleTop = true
            }
        },
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun HomeScreen(
    onNextClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
        )
        Button(onClick = onNextClicked) {
            Text(
                stringResource(id = R.string.start_cta),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MathHubTheme {
        HomeScreen({}, modifier = Modifier.fillMaxSize())
    }
}
