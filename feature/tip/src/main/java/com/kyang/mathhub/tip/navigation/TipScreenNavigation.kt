package com.kyang.mathhub.tip.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TipScreenNavigation() {
    val tipNavController = rememberNavController()
    val tipViewModel = hiltViewModel<com.kyang.mathhub.tip.viewmodel.TipViewModel>()
    val tipUIState by tipViewModel.uiState.collectAsState()

    NavHost(
        navController = tipNavController,
        startDestination = TipScreen.TipHome.route
    ) {
        composable(route = TipScreen.TipHome.route) {
            com.kyang.mathhub.tip.ui.screen.TipHomePage(
                uiState = tipUIState,
                tipViewModel = tipViewModel,
                tipNavController = tipNavController
            )
        }
        composable(route = TipScreen.TipDetail.route) {
            com.kyang.mathhub.tip.ui.screen.TipDetailScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}