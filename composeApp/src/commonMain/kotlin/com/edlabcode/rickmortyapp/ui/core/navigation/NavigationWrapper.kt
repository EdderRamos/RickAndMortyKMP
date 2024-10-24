package com.edlabcode.rickmortyapp.ui.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.edlabcode.rickmortyapp.domain.model.CharacterModel
import com.edlabcode.rickmortyapp.ui.datail.CharacterDetailScreen
import com.edlabcode.rickmortyapp.ui.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(mainNavController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen(mainNavController)
        }
        composable<CharacterDetail> { backStackEntry ->
            val arguments = backStackEntry.toRoute<CharacterDetail>()
            val characterModel = Json.decodeFromString<CharacterModel>(arguments.characterModel)
            CharacterDetailScreen(characterModel,
                onBack = { mainNavController.popBackStack() })
        }
    }
}