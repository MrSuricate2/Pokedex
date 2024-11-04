package fr.mrsuricate.pokedex.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
}

