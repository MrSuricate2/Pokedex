package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.mrsuricate.pokedex.domain.model.Stats
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.theme.atk
import fr.mrsuricate.pokedex.ui.theme.def
import fr.mrsuricate.pokedex.ui.theme.exp
import fr.mrsuricate.pokedex.ui.theme.hp
import fr.mrsuricate.pokedex.ui.theme.spd

@Composable
fun DetailPokemonStats(stats: List<Stats>, baseExperience: Int) {
    Text(
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
        stats.forEach { pokemonStat ->
            if (pokemonStat.name == "hp") {
                DetailPokemonProgressBarStats(
                    statsType = "HP",
                    stats = pokemonStat.baseStat,
                    color = hp
                )
            }
            if (pokemonStat.name == "attack") {
                DetailPokemonProgressBarStats(
                    statsType = "ATK",
                    stats = pokemonStat.baseStat,
                    color = atk
                )
            }
            if (pokemonStat.name == "defense") {
                DetailPokemonProgressBarStats(
                    statsType = "DEF",
                    stats = pokemonStat.baseStat,
                    color = def
                )
            }
            if (pokemonStat.name == "speed") {
                DetailPokemonProgressBarStats(
                    statsType = "SPD",
                    stats = pokemonStat.baseStat,
                    color = spd
                )
            }
        }
        DetailPokemonProgressBarStats(
            statsType = "EXP",
            stats = baseExperience,
            color = exp
        )
    }
}

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            val stats = listOf(
                Stats(name = "hp", baseStat = 100),
                Stats(name = "attack", baseStat = 100),
                Stats(name = "defense", baseStat = 100),
                Stats(name = "speed", baseStat = 100),
            )
            DetailPokemonStats(stats = stats, baseExperience = 100)
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            val stats = listOf(
                Stats(name = "hp", baseStat = 100),
                Stats(name = "attack", baseStat = 100),
                Stats(name = "defense", baseStat = 100),
                Stats(name = "speed", baseStat = 100),
            )
            DetailPokemonStats(stats = stats, baseExperience = 100)
        }
    }
}