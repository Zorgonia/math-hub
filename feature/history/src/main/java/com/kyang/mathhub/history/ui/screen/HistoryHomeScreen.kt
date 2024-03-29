package com.kyang.mathhub.history.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.history.model.HomeHistoryUiState
import com.kyang.mathhub.history.navigation.HistoryScreen
import com.kyang.mathhub.history.ui.component.HistoryHomeHeader
import com.kyang.mathhub.history.ui.component.HistoryItemRowComponent
import com.kyang.mathhub.history.viewmodel.HistoryViewModel
import com.kyang.mathhub.theme.MathHubTheme

@Composable
fun HistoryHomePage(
    uiState: HomeHistoryUiState,
    viewModel: HistoryViewModel,
    navController: NavHostController,
) {
    HistoryHomeScreen(
        onItemClick = {
            viewModel.getHistory(it)
            navController.navigate(HistoryScreen.HistoryDetail.route)
        },
        history = uiState.data, modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun HistoryHomeScreen(
    onItemClick: (LocalMathHistoryItem) -> Unit,
    history: List<LocalMathHistoryItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        HistoryHomeHeader()
        LazyColumn {
            items(history, key = { it.id }) { item ->
                HistoryItemRowComponent(
                    data = item,
                    onClick = onItemClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryHomeScreenPreview() {
    MathHubTheme {
        HistoryHomeScreen(
            history = listOf(),
            modifier = Modifier.fillMaxSize(),
            onItemClick = {},
        )
    }
}
