package fr.mrsuricate.pokedex.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import fr.mrsuricate.pokedex.data.api.model.Language
import fr.mrsuricate.pokedex.ui.component.PokemonListCard
import fr.mrsuricate.pokedex.ui.component.topBar.HomeAppBar
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    navController: NavHostController,
    settingViewModel: SettingViewModel
) {

    val lang: Language =
        settingViewModel.selectedLanguage.value ?: Language()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeAppBar(navController = navController)
        }
    ) { innerPadding ->
        PokemonListCard(
            modifier = Modifier.padding(innerPadding),
            homeViewModel = homeViewModel,
            detailViewModel = detailViewModel,
            lang = lang,
            navController = navController
        )
    }
}

