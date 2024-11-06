package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mrsuricate.pokedex.domain.model.Type
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun DetailPokemonType(
    types: List<Type>,
    lang: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        types.forEach { type ->
            DetailPokemonTypeItem(
                types = type,
                language = lang
            )
        }
    }
}

@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            val types = listOf(
                Type(
                    id = 10,
                    name = mapOf("Feu" to "fr")
                ),
                Type(
                    id = 11,
                    name = mapOf("Eau" to "fr")
                ),
            )
            DetailPokemonType(types = types, lang = "fr")
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            val types = listOf(
                Type(
                    id = 10,
                    name = mapOf("Feu" to "fr")
                ),
                Type(
                    id = 11,
                    name = mapOf("Eau" to "fr")
                ),
            )
            DetailPokemonType(types = types, lang = "fr")
        }
    }
}