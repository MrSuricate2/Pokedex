package fr.mrsuricate.pokedex.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.mrsuricate.pokedex.ui.component.DisplayPokemon
import fr.mrsuricate.pokedex.ui.component.detail.DetailPokemonHeight
import fr.mrsuricate.pokedex.ui.component.detail.DetailPokemonName
import fr.mrsuricate.pokedex.ui.component.detail.DetailPokemonStats
import fr.mrsuricate.pokedex.ui.component.detail.DetailPokemonType
import fr.mrsuricate.pokedex.ui.component.detail.DetailPokemonWeight
import fr.mrsuricate.pokedex.ui.component.topBar.DetailAppBar
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.theme.colorPrimary
import fr.mrsuricate.pokedex.ui.viewModel.DetailViewModel
import fr.mrsuricate.pokedex.ui.viewModel.SettingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    onGoBack: () -> Unit,
) {
    val settingViewModel: SettingViewModel = koinViewModel()
    val detailViewModel: DetailViewModel = koinViewModel()
    val pokemon = detailViewModel.pokemon
    val lang: String = settingViewModel.selectedLanguage.value ?: "fr"

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            pokemon?.id?.let {
                DetailAppBar(id = it) {
                    onGoBack()
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorPrimary,
                        shape = RoundedCornerShape(
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .wrapContentSize(Alignment.Center)
                ) {
                    pokemon?.image?.let {
                        DisplayPokemon(
                            modifier = Modifier.size(200.dp),
                            url = it,
                            id = pokemon.id
                        )
                    }
                }
            }
            DetailPokemonName(
                pokemon?.names?.get(lang) ?: ""
            )
            pokemon?.types?.let { DetailPokemonType(it, lang) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                pokemon?.weight?.let { DetailPokemonWeight(weight = it) }
                pokemon?.height?.let { DetailPokemonHeight(height = it) }
            }
            pokemon?.stats?.let {
                DetailPokemonStats(
                    stats = it,
                    baseExperience = pokemon.baseExperience
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailPreview() {
    PokedexTheme(darkTheme = true) {
        DetailScreen {

        }
    }
}