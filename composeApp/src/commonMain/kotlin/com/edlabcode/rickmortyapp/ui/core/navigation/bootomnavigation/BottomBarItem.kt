package com.edlabcode.rickmortyapp.ui.core.navigation.bootomnavigation


import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.edlabcode.rickmortyapp.ui.core.navigation.Routes
import org.jetbrains.compose.resources.painterResource
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.characters
import rickmortyapp.composeapp.generated.resources.player

sealed class BottomBarItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.Episodes.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.player), contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    ) : BottomBarItem()

    data class Characters(
        override val route: String = Routes.Characters.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(
                painter = painterResource(Res.drawable.characters), contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    ) : BottomBarItem()

}