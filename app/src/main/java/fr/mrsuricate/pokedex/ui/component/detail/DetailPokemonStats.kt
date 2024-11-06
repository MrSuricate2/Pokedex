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
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.theme.atk
import fr.mrsuricate.pokedex.ui.theme.def
import fr.mrsuricate.pokedex.ui.theme.exp
import fr.mrsuricate.pokedex.ui.theme.hp
import fr.mrsuricate.pokedex.ui.theme.spd

@Composable
fun DetailPokemonStats(stats: Map<String, Int>, baseExperience: Int) {
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
        stats.forEach { (statName, statValue) ->
            if (statName == "hp") {
                DetailPokemonProgressBarStats(
                    statsType = "HP",
                    stats = statValue,
                    color = hp
                )
            }
            if (statName == "attack") {
                DetailPokemonProgressBarStats(
                    statsType = "ATK",
                    stats = statValue,
                    color = atk
                )
            }
            if (statName == "defense") {
                DetailPokemonProgressBarStats(
                    statsType = "DEF",
                    stats = statValue,
                    color = def
                )
            }
            if (statName == "speed") {
                DetailPokemonProgressBarStats(
                    statsType = "SPD",
                    stats = statValue,
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
            val stats = mapOf("hp" to 100, "attack" to 100, "defense" to 100, "speed" to 100)
            DetailPokemonStats(stats = stats, baseExperience = 100)
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            val stats = mapOf("hp" to 100, "attack" to 100, "defense" to 100, "speed" to 100)
            DetailPokemonStats(stats = stats, baseExperience = 100)
        }
    }
}