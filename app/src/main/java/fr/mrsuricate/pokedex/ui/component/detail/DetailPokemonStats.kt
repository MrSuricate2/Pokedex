package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.mrsuricate.pokedex.data.api.model.PokemonJsonModel
import fr.mrsuricate.pokedex.ui.theme.atk
import fr.mrsuricate.pokedex.ui.theme.def
import fr.mrsuricate.pokedex.ui.theme.exp
import fr.mrsuricate.pokedex.ui.theme.hp
import fr.mrsuricate.pokedex.ui.theme.spd

@Composable
fun DetailPokemonStats(pokemon: PokemonJsonModel) {
    Text(
        modifier = Modifier
            .padding(top = 32.dp),
        fontSize = 24.sp,
        text = "Stats"
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pokemon.stats.forEach { pokemonStat ->
            if (pokemonStat.stat.name == "hp") {
                DetailPokemonProgressBarStats(
                    statsType = "HP",
                    stats = pokemonStat.baseStat,
                    maxStats = if (pokemonStat.baseStat < 150) 150 else 300,
                    color = hp
                )
            }
            if (pokemonStat.stat.name == "attack") {
                DetailPokemonProgressBarStats(
                    statsType = "ATK",
                    stats = pokemonStat.baseStat,
                    maxStats = if (pokemonStat.baseStat < 100) 100 else 200,
                    color = atk
                )
            }
            if (pokemonStat.stat.name == "defense") {
                DetailPokemonProgressBarStats(
                    statsType = "DEF",
                    stats = pokemonStat.baseStat,
                    maxStats = if (pokemonStat.baseStat < 150) 150 else 250,
                    color = def
                )
            }
            if (pokemonStat.stat.name == "speed") {
                DetailPokemonProgressBarStats(
                    statsType = "SPD",
                    stats = pokemonStat.baseStat,
                    maxStats = if (pokemonStat.baseStat < 150) 150 else 250,
                    color = spd
                )
            }
        }
        DetailPokemonProgressBarStats(
            statsType = "EXP",
            stats = pokemon.baseExperience,
            maxStats = if (pokemon.baseExperience < 250) 250 else if (pokemon.baseExperience < 500) 500 else 700,
            color = exp
        )
    }
}