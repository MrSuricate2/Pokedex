package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mrsuricate.pokedex.data.api.model.Language
import fr.mrsuricate.pokedex.domain.model.Type

@Composable
fun DetailPokemonTypeItem(
    types: Type,
    language: Language
) {
    Box(
        modifier = Modifier
            .background(
                types.getColor(),
                RoundedCornerShape(16.dp)
            )
    ) {
        types.names.find { it.language == language.name }?.name?.let {
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