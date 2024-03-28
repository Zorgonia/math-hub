package com.kyang.mathhub.history.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.history.ui.screen.HistoryDetailPage
import com.kyang.mathhub.history.ui.screen.HistoryHomePage
import com.kyang.mathhub.history.viewmodel.HistoryViewModel

@Composable
fun HistoryScreenNavigation() {
    val historyNavController = rememberNavController()
    val historyViewModel = hiltViewModel<HistoryViewModel>()
    val historyHomeUiState by historyViewModel.homeUiState.collectAsState()
    val historyDetailUiState by historyViewModel.detailUiState.collectAsState()
    NavHost(
        navController = historyNavController,
        startDestination = HistoryScreen.HistoryHome.route,
    ) {
        composable(route = HistoryScreen.HistoryHome.route) {
            HistoryHomePage(
                uiState = historyHomeUiState,
                viewModel = historyViewModel,
                navController = historyNavController
            )
        }
        composable(route = HistoryScreen.HistoryDetail.route) {
            HistoryDetailPage(
                uiState = historyDetailUiState,
                viewModel = historyViewModel,
                navController = historyNavController
            )
        }
    }
}
