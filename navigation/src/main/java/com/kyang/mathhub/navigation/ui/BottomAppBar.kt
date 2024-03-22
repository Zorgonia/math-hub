package com.kyang.mathhub.navigation.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import com.kyang.mathhub.navigation.data.BottomNavItem

@Composable
fun BottomBar(
    backStackEntry: NavBackStackEntry?,
    items: List<BottomNavItem>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        items.forEachIndexed { _, bottomNavItem ->
            NavigationBarItem(
                selected = bottomNavItem.route == backStackEntry?.destination?.route,
                onClick = { onClick(bottomNavItem.route) },
                icon = {
                    Icon(bottomNavItem.icon, contentDescription = bottomNavItem.label)
                },
                label = {
                    Text(bottomNavItem.label)
                },
            )

        }
    }
}
