package com.kyang.mathhub.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.ui.screen.history.HistoryDetailScreen
import com.kyang.mathhub.ui.screen.history.HistoryHomeScreen
import com.kyang.mathhub.ui.screen.tip.TipDetailScreen
import com.kyang.mathhub.ui.screen.tip.TipHomeScreen

@Composable
fun HistoryScreenNavigation() {
    val historyNavController = rememberNavController()
    NavHost(
        navController = historyNavController,
        startDestination = HistoryScreen.HistoryHome.route
    ) {
        composable(route = HistoryScreen.HistoryHome.route) {
            HistoryHomeScreen(
                onClick = { historyNavController.navigate(HistoryScreen.HistoryDetail.route) },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = HistoryScreen.HistoryDetail.route) {
            HistoryDetailScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}