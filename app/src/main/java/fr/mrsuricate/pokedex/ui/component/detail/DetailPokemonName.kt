package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun DetailPokemonName(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .padding(top = 16.dp)
            .wrapContentSize(),
        fontSize = 32.sp
    )
}

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            DetailPokemonName(name = "Bulbizarre")
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            DetailPokemonName(name = "Bulbizarre")
        }
    }
}