package fr.mrsuricate.pokedex.ui.component.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.mrsuricate.pokedex.data.api.model.PokemonJsonModel
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme
import fr.mrsuricate.pokedex.ui.theme.colorPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailAppBar(modifier: Modifier = Modifier, id: Int, onGoBack: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pokédex",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "#${id.toString().padStart(3, '0')}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        },
        colors = topAppBarColors(
            containerColor = colorPrimary
        ),
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onGoBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailAppBarPreview() {
    val pokemon = PokemonJsonModel()
    PokedexTheme {
        DetailAppBar(id = pokemon.id) {

        }
    }
}