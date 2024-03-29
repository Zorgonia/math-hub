package com.kyang.mathhub.history.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.kyang.mathhub.domain.model.LocalMathHistoryItem
import com.kyang.mathhub.feature.history.R
import com.kyang.mathhub.history.model.HomeHistoryUiState
import com.kyang.mathhub.history.navigation.HistoryScreen
import com.kyang.mathhub.history.ui.component.AlertDialogComponent
import com.kyang.mathhub.history.ui.component.HistoryHomeHeader
import com.kyang.mathhub.history.ui.component.HistoryItemRowComponent
import com.kyang.mathhub.history.viewmodel.HistoryViewModel
import com.kyang.mathhub.theme.MathHubTheme
import com.kyang.mathhub.theme.Red

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
        onFabClick = { viewModel.openDeleteDialog() },
        deleteDialogOpen = uiState.deleteDialogOpen,
        onDismissDeleteDialog = { viewModel.dismissDeleteDialog() },
        onConfirmDeleteDialog = { viewModel.deleteHistory() }
    )
}

@Composable
private fun HistoryHomeScreen(
    deleteDialogOpen: Boolean,
    onDismissDeleteDialog: () -> Unit,
    onConfirmDeleteDialog: () -> Unit,
    onFabClick: () -> Unit,
    onItemClick: (LocalMathHistoryItem) -> Unit,
    history: List<LocalMathHistoryItem>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(id = R.string.fab_delete_history))
            }
        },
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(padding),
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
            if (deleteDialogOpen) {
                AlertDialogComponent(
                    onDismiss = onDismissDeleteDialog,
                    onConfirm = onConfirmDeleteDialog,
                    title = R.string.alert_history_delete_title,
                    text = R.string.alert_history_delete_text,
                    confirm = R.string.alert_delete,
                    confirmButtonColor = Red
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
            onFabClick = {},
            history = listOf(),
            modifier = Modifier.fillMaxSize(),
            onItemClick = {},
            onConfirmDeleteDialog = {},
            onDismissDeleteDialog = {},
            deleteDialogOpen = false
        )
    }
}
