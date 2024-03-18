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
import com.kyang.mathhub.tip.ui.screen.TipDetailScreen
import com.kyang.mathhub.tip.ui.screen.TipHomePage
import com.kyang.mathhub.tip.viewmodel.TipViewModel

@Composable
fun TipScreenNavigation() {
    val tipNavController = rememberNavController()
    val tipViewModel = hiltViewModel<TipViewModel>()
    val tipUIState by tipViewModel.uiState.collectAsState()

    NavHost(
        navController = tipNavController,
        startDestination = TipScreen.TipHome.route
    ) {
        composable(route = TipScreen.TipHome.route) {
            TipHomePage(
                uiState = tipUIState,
                tipViewModel = tipViewModel,
                tipNavController = tipNavController
            )
        }
        composable(route = TipScreen.TipDetail.route) {
            TipDetailScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}