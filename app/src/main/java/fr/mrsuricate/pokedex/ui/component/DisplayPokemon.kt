package fr.mrsuricate.pokedex.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.size.Size
import coil3.svg.SvgDecoder
import fr.mrsuricate.pokedex.ui.component.detail.OrientationPreviews
import fr.mrsuricate.pokedex.ui.component.detail.ThemePreviews
import fr.mrsuricate.pokedex.ui.theme.PokedexTheme

@Composable
fun DisplayPokemon(
    url: String,
    id: Int,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .decoderFactory(SvgDecoder.Factory())
            .size(Size.ORIGINAL)
            .build()
    )
    Image(
        painter = painter,
        contentDescription = "$id dreamWorld picture",
        modifier = modifier,
    )
}


@ThemePreviews
@Composable
private fun PreviewTheme() {
    PokedexTheme {
        Surface {
            DisplayPokemon(url = "", id = 0)
        }
    }
}

@OrientationPreviews
@Composable
private fun PreviewOrientation() {
    PokedexTheme {
        Surface {
            DisplayPokemon(url = "", id = 0)
        }
    }
}