package com.kyang.mathhub.application

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kyang.mathhub.R
import com.kyang.mathhub.data.BottomNavItem
import com.kyang.mathhub.navigation.HistoryScreenNavigation
import com.kyang.mathhub.navigation.MathQuestionScreenNavigation
import com.kyang.mathhub.navigation.TipScreenNavigation
import com.kyang.mathhub.ui.components.BottomBar

private enum class NavGraphs(val route: String) {
    MathQuestion("question"),
    Tip("tip"),
    MathHistory("history")
}

@Composable
fun MathApp(
    bottomNavController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            BottomBar(
                backStackEntry = navBackStackEntry,
                items =
                listOf(
                    BottomNavItem(
                        label = stringResource(id = R.string.bottom_bar_math),
                        icon = Icons.Filled.Add,
                        route = NavGraphs.MathQuestion.route
                    ),
                    BottomNavItem(
                        label = stringResource(id = R.string.bottom_bar_tip),
                        icon = Icons.Filled.Create,
                        route = NavGraphs.Tip.route
                    ),
                    BottomNavItem(
                        label = stringResource(id = R.string.bottom_bar_history),
                        icon = Icons.Filled.Person,
                        route = NavGraphs.MathHistory.route
                    ),
                ),
                onClick = { route ->
                    bottomNavController.navigate(route) {
                        bottomNavController.graph.findNode(route)?.id?.let {
                            popUpTo(it)
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->

        BottomNavigation(innerPadding = innerPadding, bottomNavController = bottomNavController)
    }
}

@Composable
fun BottomNavigation(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    bottomNavController: NavHostController
) {

    NavHost(
        navController = bottomNavController,
        startDestination = NavGraphs.MathQuestion.route,
        modifier = modifier.padding(innerPadding)
    ) {

        composable(route = NavGraphs.MathQuestion.route) {
            MathQuestionScreenNavigation()
        }
        composable(route = NavGraphs.Tip.route) {
            TipScreenNavigation()
        }
        composable(route = NavGraphs.MathHistory.route) {
            HistoryScreenNavigation()
        }
    }
}