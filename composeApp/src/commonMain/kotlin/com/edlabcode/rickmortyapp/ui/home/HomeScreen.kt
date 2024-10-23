package com.edlabcode.rickmortyapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.edlabcode.rickmortyapp.ui.core.BackgroundPrimaryColor
import com.edlabcode.rickmortyapp.ui.core.BackgroundSecondaryColor
import com.edlabcode.rickmortyapp.ui.core.BackgroundTertiaryColor
import com.edlabcode.rickmortyapp.ui.core.DefaultTextColor
import com.edlabcode.rickmortyapp.ui.core.Green
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.BottomBarItem
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.BottomBarItem.Characters
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.BottomBarItem.Episodes
import com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation.NavigationBottomWrapper
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ricktoolbar

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val items = listOf(Episodes(), Characters())
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigation(items, navController) },
        topBar = { TopBar() }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            NavigationBottomWrapper(
                navController,
                mainNavController
            )
        }
    }

}

@Composable
fun TopBar() {
    Box(
        modifier = Modifier.fillMaxWidth().background(BackgroundPrimaryColor),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(Res.drawable.ricktoolbar),
            contentDescription = null,
            modifier = Modifier.height(100.dp)
                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
        )
    }

}

@Composable
fun BottomNavigation(items: List<BottomBarItem>, navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = BackgroundSecondaryColor,
        contentColor = Green
    ) {
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Green,
                    selectedIconColor = BackgroundTertiaryColor,
                    unselectedIconColor = Green
                ),
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
                label = { Text(item.title, color = DefaultTextColor) },
                alwaysShowLabel = false
            )
        }
    }
}