package fr.mrsuricate.pokedex.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PokemonName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        textAlign = TextAlign.Center
    )
}