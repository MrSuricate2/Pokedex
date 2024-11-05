package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mrsuricate.pokedex.domain.model.Name
import fr.mrsuricate.pokedex.domain.model.Type
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun DetailPokemonTypeItem(
    types: Type,
    language: String
) {
    Box(
        modifier = Modifier
            .background(
                types.getColor(),
                RoundedCornerShape(16.dp)
            )
    ) {
        types.names.find { it.language == language }?.name?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(
                        horizontal = 32.dp,
                        vertical = 2.dp
                    )
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            val type = Type(
                id = 10,
                name = "fire",
                names = listOf(
                    Name(
                        name = "Feu",
                        language = "fr"
                    )
                )
            )
            DetailPokemonTypeItem(types = type, language = "fr")
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            val type = Type(
                id = 10,
                name = "fire",
                names = listOf(
                    Name(
                        name = "Feu",
                        language = "fr"
                    )
                )
            )
            DetailPokemonTypeItem(types = type, language = "fr")
        }
    }
}