package com.edlabcode.rickmortyapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.BottomBarItem
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.BottomBarItem.Characters
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.BottomBarItem.Episodes
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.NavigationBottomWrapper

@Composable
fun HomeScreen() {
    val items = listOf(Episodes(), Characters())
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(items, navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) { NavigationBottomWrapper(navController) }
    }

}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                label = { Text(item.title) },
                alwaysShowLabel = false
            )
        }
    }
}