package fr.mrsuricate.pokedex.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import fr.mrsuricate.pokedex.data.api.model.Language
import fr.mrsuricate.pokedex.data.api.model.PokemonJsonModel
import fr.mrsuricate.pokedex.ui.navigation.Detail
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel

@Composable
fun PokemonCard(
    detailViewModel: DetailViewModel,
    pokemon: PokemonJsonModel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    lang: Language
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        onClick = {
            detailViewModel.setPokemon(pokemon)
            navController.navigate(Detail.route)
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            DisplayPokemon(
                modifier = Modifier.size(128.dp),
                pokemon = pokemon
            )

        }
        PokemonName(
            name = pokemon.getSpecies().names.find { it.language == lang.name }?.name ?: ""
        )
    }
}