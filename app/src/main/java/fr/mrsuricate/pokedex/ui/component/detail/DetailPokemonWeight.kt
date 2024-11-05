package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun DetailPokemonWeight(weight: Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${weight.div(10)},${weight.rem(10)} KG",
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 2.dp
                ),
            fontSize = 24.sp
        )
        Text(
            text = "Poids",
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 2.dp
                ),
            fontSize = 12.sp
        )
    }
}

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            DetailPokemonWeight(weight = 7)
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            DetailPokemonWeight(weight = 7)
        }
    }
}