package fr.mrsuricate.pokedex.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fr.mrsuricate.pokedex.ui.component.PokemonListCard
import fr.mrsuricate.pokedex.ui.component.topBar.HomeAppBar
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    // ViewModel singleton injection
    val settingViewModel: SettingViewModel = koinViewModel()
    val lang: String = settingViewModel.selectedLanguage.value ?: "fr"

    // Utilisation de l'état pour suivre si le timer est terminé
    var showHomeScreen by remember { mutableStateOf(false) }

    if (showHomeScreen) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HomeAppBar(navController = navController)
            }
        ) { innerPadding ->
            PokemonListCard(
                modifier = Modifier.padding(innerPadding),
                lang = lang,
                navController = navController
            )
        }
    } else {
        LandingScreen({
            showHomeScreen = true
        })
    }
}

