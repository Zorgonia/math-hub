package com.kyang.mathhub.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.ui.screen.tip.TipDetailScreen
import com.kyang.mathhub.ui.screen.tip.TipHomeScreen

@Composable
fun TipScreenNavigation() {
    val tipNavController = rememberNavController()
    NavHost(
        navController = tipNavController,
        startDestination = TipScreen.TipHome.route
    ) {
        composable(route = TipScreen.TipHome.route) {
            TipHomeScreen(
                onClick = { tipNavController.navigate(TipScreen.TipDetail.route) },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = TipScreen.TipDetail.route) {
            TipDetailScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}