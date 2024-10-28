package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mrsuricate.pokedex.data.api.model.Language
import fr.mrsuricate.pokedex.domain.model.Type

@Composable
fun DetailPokemonType(
    types: List<Type>,
    lang: Language
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