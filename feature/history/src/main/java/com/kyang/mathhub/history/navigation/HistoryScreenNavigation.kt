package com.kyang.mathhub.history.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.history.ui.screen.HistoryDetailScreen
import com.kyang.mathhub.history.ui.screen.HistoryHomeScreen

@Composable
fun HistoryScreenNavigation() {
    val historyNavController = rememberNavController()
    NavHost(
        navController = historyNavController,
        startDestination = HistoryScreen.HistoryHome.route,
    ) {
        composable(route = HistoryScreen.HistoryHome.route) {
            HistoryHomeScreen(
                onClick = { historyNavController.navigate(HistoryScreen.HistoryDetail.route) },
                modifier = Modifier.fillMaxSize(),
            )
        }
        composable(route = HistoryScreen.HistoryDetail.route) {
            HistoryDetailScreen(
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
