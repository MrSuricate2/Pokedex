package fr.mrsuricate.pokedex.ui.component.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.mrsuricate.pokedex.domain.model.Pokemon
import fr.mrsuricate.pokedex.ui.component.DisplayPokemon
import fr.mrsuricate.pokedex.ui.component.detail.OrientationPreviews
import fr.mrsuricate.pokedex.ui.component.detail.ThemePreviews
import fr.mrsuricate.pokedex.ui.navigation.Detail
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val detailViewModel: DetailViewModel = koinViewModel()
    val settingViewModel: SettingViewModel = koinViewModel()

    val lang: String = settingViewModel.getSelectedLangue()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier,
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
                url = pokemon.image,
                id = pokemon.id
            )

        }
        pokemon.names[lang]?.let {
            PokemonName(
                name = it
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            val navController = rememberNavController()
            val pokemon = Pokemon(
                id = 1,
                names = mapOf("en" to "Bulbasaur", "fr" to "Bulbizarre"),
                baseExperience = 100,
                height = 10,
                weight = 10,
                image = "",
                types = listOf(),
                stats = mapOf()
            )
            PokemonCard(
                pokemon = pokemon,
                navController = navController
            )
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            val navController = rememberNavController()
            val pokemon = Pokemon(
                id = 1,
                names = mapOf("en" to "Bulbasaur", "fr" to "Bulbizarre"),
                baseExperience = 100,
                height = 10,
                weight = 10,
                image = "",
                types = listOf(),
                stats = mapOf()
            )
            PokemonCard(
                pokemon = pokemon,
                navController = navController
            )
        }
    }
}