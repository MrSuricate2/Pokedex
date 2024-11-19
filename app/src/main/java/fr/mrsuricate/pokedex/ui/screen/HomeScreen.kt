package fr.mrsuricate.pokedex.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.mrsuricate.pokedex.ui.component.home.PokemonListCard
import fr.mrsuricate.pokedex.ui.component.topBar.HomeAppBar
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    // ViewModel singleton injection
    val homeViewModel: HomeViewModel = koinViewModel()

    // Observe the showHomeScreen LiveData
    val showHomeScreen by homeViewModel.showHomeScreen.observeAsState(false)

    if (showHomeScreen) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HomeAppBar(navController = navController)
            }
        ) { innerPadding ->
            PokemonListCard(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp),
                navController = navController
            )
        }
    } else {
        LandingScreen()
    }
}

