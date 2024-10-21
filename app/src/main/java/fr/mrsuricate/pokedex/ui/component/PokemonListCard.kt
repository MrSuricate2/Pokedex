package fr.mrsuricate.pokedex.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.mrsuricate.pokedex.data.api.model.Language
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.HomeViewModel

@Composable
fun PokemonListCard(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    detailViewModel: DetailViewModel,
    navController: NavHostController,
    lang: Language
) {
    val agentsData by homeViewModel.pokemonData.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(top = 8.dp)
    ) {
        items(count = agentsData.pokemons.size) { index ->
            agentsData.pokemons[index]?.let { pokemon ->
                PokemonCard(
                    detailViewModel = detailViewModel,
                    pokemon = pokemon,
                    lang = lang,
                    navController = navController
                )

                if (index == agentsData.pokemonDisplay - 30) {
                    LaunchedEffect(key1 = index) {
                        homeViewModel.addPokemonToList()
                    }
                }
            }
        }

    }
}