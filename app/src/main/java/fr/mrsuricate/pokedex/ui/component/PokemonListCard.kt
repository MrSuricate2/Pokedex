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
    val pokemonList by homeViewModel.pokemonFlow.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(top = 8.dp)
    ) {
        items(count = pokemonList.size) { index ->
            pokemonList[index].let { pokemon ->
                PokemonCard(
                    detailViewModel = detailViewModel,
                    pokemon = pokemon,
                    lang = lang,
                    navController = navController
                )

                if (index == (pokemonList.size.minus(30))) {
                    // On lance un effet pour charger plus de Pokémon
                    LaunchedEffect(Unit) {
                        homeViewModel.addPokemonToList()
                    }
                }
            }
        }
    }
}