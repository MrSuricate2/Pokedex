package fr.mrsuricate.pokedex.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
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

    // Observe loading state
    val isLoading by homeViewModel.isLoading.collectAsState()

    if (showHomeScreen) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                HomeAppBar(navController = navController)
            }
        ) { innerPadding ->

            // Pass the refresh function to PokemonListCard
            PokemonListCard(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp),
                navController = navController,
                onRefresh = { homeViewModel.refreshData() },
                isRefreshing = isLoading
            )

            // Loading indicator for initial load when no Pokemon are loaded yet
            if (isLoading) {
                val pokemonList by homeViewModel.pokemonFlow.collectAsState()
                if (pokemonList.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    } else {
        LandingScreen()
    }
}

