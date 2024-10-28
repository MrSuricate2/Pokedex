package fr.mrsuricate.pokedex.ui.component.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.mrsuricate.pokedex.domain.model.Pokemon

@Composable
fun DetailPokemonHeight(pokemon: Pokemon) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${pokemon.height.div(10)},${pokemon.height.rem(10)} M",
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 2.dp
                ),
            fontSize = 24.sp
        )
        Text(
            text = "Taille",
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 2.dp
                ),
            fontSize = 12.sp
        )
    }
}